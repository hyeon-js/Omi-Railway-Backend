package com.hyeonjs.omirailway;

import org.springframework.stereotype.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
// @RequiredArgsConstructor
public class TrainLocatinService {

    private final TimeTableRepository ttr = new TimeTableRepository();

    public Station[] getLineInfo(String line) {
        String[] list = getStationList(line);
        if (list == null) return null;

        Station[] result = new Station[list.length];
        for (int n = 0; n < list.length; n++) {
            result[n] = new Station(list[n], new ArrayList<Station.Train>(), new ArrayList<Station.Train>());
        }
        return applyTrainList(result, list);
    }

    public String[] getStationList(String line) {
        switch(line) {
            case "main":
            case "honsen":
                return new String[]{"마이바라 (米原)","후지테크마에 (フジテック前)","토리이모토 (鳥居本)","히코네 (彦根)","히코네세리카와 (ひこね芹川)","히코네구치 (彦根口)","타카미야 (高宮)","아마고 (尼子)","토요사토 (豊郷)","에치가와 (愛知川)","고카쇼 (五箇荘)","카와베노모리 (河辺の森)","요카이치 (八日市)","나가타니노 (長谷野)","다이가쿠마에 (大学前)","교세라마에 (京セラ前)","사쿠라가와 (桜川)","아사히오츠카 (朝日大塚)","아사히노 (朝日野)","히노 (日野)","미나쿠치마츠오 (水口松尾)","미나쿠치 (水口)","미나쿠치이시바시 (水口石橋)","미나쿠치조난 (水口城南)","키부카와 (貴生川)"};
            case "yokaichi":
                return new String[]{"요카이치 (八日市)","신요카이치 (新八日市)","타로보구마에 (太郎坊宮前)","이치노베 (市辺)","히라타 (平田)","무사 (武佐)","오미하치만 (近江八幡)"};
            case "taga":
                return new String[]{"타카미야 (高宮)","스크린 (スクリーン)","타가타이샤마에 (多賀大社前)"};
        }
        return null;
    }

    private Station[] applyTrainList(Station[] result, String[] stns) {
        TrainTimeTable[] data = getTimeTable();
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Seoul"));
        int hour = now.getHour();
        if (hour == 0) hour = 24;
        // hour = 12;
        int now_m = 60*hour + now.getMinute();
        ArrayList<Train> list = new ArrayList<>();

        for (TrainTimeTable datum : data) {
            // System.out.println(datum.trainNo + ", " + hour + ":"+now.getMinute());
            // System.out.println(datum.trainNo + ", " + datum.data[0].tm + ", " + datum.data[datum.data.length - 1].tm);
            // System.out.println(datum.trainNo + ", " + now_m);
            // System.out.println(datum.trainNo + ", " + datum.data[0].ts + ", " + datum.data[datum.data.length - 1].ts);
            if (now_m < datum.data[0].ts) continue; //운행이 아직 시작되지 않은 열차
            if (datum.data[datum.data.length - 1].ts < now_m) continue; //운행이 끝난 열차

            String pos = calcTrainPos(now_m, datum.data);
            int index = stn2index(stns, pos);
            // System.out.println(pos + ", " + index);
            if (index == -1) continue; //다른 노선에 있는 열차 
        
            System.out.println(datum.trainNo);

            Station.Train train = new Station.Train(datum.trainNo, datum.terminal);
            if (Integer.parseInt(datum.trainNo) % 2 == 0) {
                result[index].up.add(train);
            }
            else {
                result[index].down.add(train);
            }

        }

        
        return result;
    }

    private String calcTrainPos(int now, TrainTimeTable.Time[] time) {
        for (int n = time.length - 1; n >= 0; n--) {
            if (time[n].ts == now) return time[n].stn;
            if (time[n].ts < now) return time[n + 1].stn;
        }
        return null; //어차피 여긴 실행될 일이 없음
    }

    private int stn2index(String[] stns, String stn) {
        for (int n = 0; n < stns.length; n++) {
            if (stn.equals(stns[n].split(" ")[0])) return n;
        }
        return -1;
    }

    private TrainTimeTable[] getTimeTable() {
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
        int day = now.getDayOfWeek().getValue();
        String fileName = "weekdays.json";
        if (day == 6 || day == 7) fileName = "holidays.json";
        

        String timetableRaw = new TimeTableRepository().read(fileName);
        JSONObject json = new JSONObject(timetableRaw);
        Iterator<String> keys = json.keys();
        ArrayList<TrainTimeTable> data = new ArrayList<>();
        while (keys.hasNext()) {
            String trainNo = keys.next();
            String terminal = null;
            JSONArray timetable;
            if (json.get(trainNo) instanceof JSONObject) {
                JSONObject cache = json.getJSONObject(trainNo);
                terminal = cache.getString("terminal");
                timetable = cache.getJSONArray("tm");
            } else {
                timetable = json.getJSONArray(trainNo);
            }
            TrainTimeTable.Time[] time = new TrainTimeTable.Time[timetable.length()];
            for (int n = 0; n < timetable.length(); n++) {
                JSONObject t = timetable.getJSONObject(n);
                time[n] = new TrainTimeTable.Time(t.getString("stn"), t.getString("tm"));
            }
            data.add(new TrainTimeTable(trainNo, time, terminal));
        }
        return data.toArray(new TrainTimeTable[0]);
    }
    

}

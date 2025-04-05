package com.hyeonjs.omirailway;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

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

        return result;

        // switch(line) {
        //     case "honsen":
        //         return "[\"본선\"]";
        //     case "yokaichi":
        //         return "[\"요카이치선\"]";
        //     case "taga":
        //         return "[\"타가선\"]";
        // }
        
    }

    public String[] getStationList(String line) {
        switch(line) {
            case "honsen":
                return new String[]{"마이바라 (米原)","후지테크마에 (フジテック前)","토리이모토 (鳥居本)","히코네 (彦根)","히코네세리카와 (ひこね芹川)","히코네구치 (彦根口)","타카미야 (高宮)","아마고 (尼子)","토요사토 (豊郷)","에치가와 (愛知川)","고카쇼 (五箇荘)","카와베노모리 (河辺の森)","요카이치 (八日市)","나가타니노 (長谷野)","다이가쿠마에 (大学前)","교세라마에 (京セラ前)","사쿠라가와 (桜川)","아사히오츠카 (朝日大塚)","아사히노 (朝日野)","히노 (日野)","미나쿠치마츠오 (水口松尾)","미나쿠치 (水口)","미나쿠치이시바시 (水口石橋)","미나쿠치조난 (水口城南)","키부카와 (貴生川)"};
            case "yokaichi":
                return new String[]{"요카이치 (八日市)","신요카이치 (新八日市)","타로보구마에 (太郎坊宮前)","이치노베 (市辺)","히라타 (平田)","무사 (武佐)","오미하치만 (近江八幡)"};
            case "taga":
                return new String[]{"타카미야 (高宮)","스크린 (スクリーン)","타가타이샤마에 (多賀大社前)"};
        }
        return null;
    }

}

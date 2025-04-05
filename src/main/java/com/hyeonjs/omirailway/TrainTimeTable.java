package com.hyeonjs.omirailway;

public class TrainTimeTable {
    final public String trainNo, terminal;
    final public Time[] data;

    public TrainTimeTable(String trainNo, Time[] data, String terminal) {
        this.trainNo = trainNo;
        this.data = data;
        if (terminal != null) this.terminal = terminal;
        else this.terminal = data[data.length - 1].stn;
    }

    public static class Time {
        final public String stn, tm;
        final public int ts;

        public Time(String stn, String tm) {
            this.stn = stn;
            this.tm = tm;
            this.ts = t2s(tm);
        }

        private int t2s(String time) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            if (h == 0) h = 24;
            return 60*h + Integer.parseInt(t[1]);
        }
    
    }

}


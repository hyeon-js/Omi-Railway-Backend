package com.hyeonjs.omirailway;

public class TrainTimeTable {
    final public String trainNo;
    final public Time[] data;

    public TrainTimeTable(String trainNo, Time[] data) {
        this.trainNo = trainNo;
        this.data = data;
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
            return 60*Integer.parseInt(t[0]) + Integer.parseInt(t[1]);
        }
    
    }

}


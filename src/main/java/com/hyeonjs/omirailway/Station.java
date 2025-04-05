package com.hyeonjs.omirailway;

import java.util.List;

public class Station {

	private String station;
	public List<Train> up, down;

	public Station(String station, List<Train> up, List<Train> down) {
		this.station = station;
		this.up = up;
		this.down = down;
	}

	public String getStation() {
		return station;
	}
	
	public static class Train {

		private final String trainNo, terminal, type;

		public Train(String trainNo, String terminal) {
			this.trainNo = trainNo;
			this.terminal = terminal;
			if (trainNo.equals("50")) this.type = "쾌속";
			else this.type = "보통";
		}

		public String getTrainNo() {
			return trainNo;
		}

		public String getTerminal() {
			return terminal;
		}

		public String getType() {
			return type;
		}

	}
}

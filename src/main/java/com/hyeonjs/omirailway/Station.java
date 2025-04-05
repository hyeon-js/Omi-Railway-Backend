package com.hyeonjs.omirailway;

import java.util.List;

public class Station {

	private String station;
	private List<Train> up, down;

	public Station(String station, List<Train> up, List<Train> down) {
		this.station = station;
		this.up = up;
		this.down = down;
	}

	public String getStation() {
		return station;
	}
	
	public List<Train> getUp() {
		return up;
	}
	public List<Train> getDown() {
		return down;
	}

	public static class Train {
		private String trainNo, terminal, type;

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

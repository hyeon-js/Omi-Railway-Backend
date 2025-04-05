package com.hyeonjs.omirailway;

public class Station {

	private String station;
	private Train[] up, down;

	public Station(String station, Train[] up, Train[] down) {
		this.station = station;
		this.up = up;
		this.down = down;
	}

	public String getStation() {
		return station;
	}
	
	public Train[] getUp() {
		return up;
	}
	public Train[] getDown() {
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

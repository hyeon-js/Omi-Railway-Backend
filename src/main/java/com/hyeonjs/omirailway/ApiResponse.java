package com.hyeonjs.omirailway;

public class ApiResponse {

	private boolean error;
	private String msg;
	private Station[] data;

	public ApiResponse(String errorMsg) {
		error = true;
		msg = errorMsg;
		data = new Station[0];
	}

	public ApiResponse(Station[] data) {
		error = false;
		msg = "성공";
		this.data = data;
	}

	public boolean getError() {
		return error;
	}

	public String getMsg() {
		return msg;
	}

	public Station[] getData() {
		return data;
	}

}

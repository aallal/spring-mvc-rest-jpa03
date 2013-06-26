package com.serisoft.dto;

import java.util.ArrayList;
import java.util.List;

public class StatusResponse {

	private Boolean success;
	private List<String> messages;

	public StatusResponse(Boolean success, List<String> message) {
		super();
		this.success = success;
		this.messages = message;
	}

	public StatusResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.messages = new ArrayList<String>();
		this.messages.add(message);
	}

	public StatusResponse(Boolean success) {
		super();
		this.success = success;
		this.messages = new ArrayList<String>();
	}

	public StatusResponse() {
		super();
		this.messages = new ArrayList<String>();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (String mess : messages) {
			sb.append(mess + ", ");
		}
		return "StatusResponse [success=" + success + ", message="
				+ sb.toString() + "]";

	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<String> getMessage() {
		return messages;
	}

	public void setMessage(List<String> message) {
		this.messages = message;
	}

}

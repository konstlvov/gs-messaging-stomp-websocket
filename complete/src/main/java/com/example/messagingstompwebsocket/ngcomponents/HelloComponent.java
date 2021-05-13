package com.example.messagingstompwebsocket.ngcomponents;

import org.springframework.stereotype.Component;

@Component
public class HelloComponent {
	private String header;
	private String msg;
	private String greeting;
	private String statusline;

	public String getNewField() {
		return newField;
	}

	public void setNewField(String newField) {
		this.newField = newField;
	}

	private String newField;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getStatusline() {
		return statusline;
	}

	public void setStatusline(String statusline) {
		this.statusline = statusline;
	}
}

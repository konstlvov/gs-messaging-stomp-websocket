package com.example.messagingstompwebsocket;

public class Msg {
	private String type;
	private String text;

	public Msg() {
	}

	public Msg(String type, String text) {
		this.type = type;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Msg object: {type: " + type + ", text: " + text + "}";
	}

}

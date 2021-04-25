package com.example.messagingstompwebsocket;

public class Msg {

	private String elementId;
	private String elementText;

	public Msg(String elementId, String elementText) {
		this.elementId = elementId;
		this.elementText = elementText;
	}

	public String getElementId() {
		return elementId;
	}

	public String getElementText() {
		return elementText;
	}

	@Override
	public String toString() {
		return "Msg object: {elementId: " + elementId + ", elementText: " + elementText + "}";
	}

}

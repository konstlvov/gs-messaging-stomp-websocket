package com.example.messagingstompwebsocket;

public class UpdateComponentFieldMsg {

	private String c; // Angular component
	private String f; // field name within component
	private String v; // new field value

	public UpdateComponentFieldMsg(String angularComponent, String fieldName, String newFieldValue) {
		this.c = angularComponent;
		this.f = fieldName;
		this.v = newFieldValue;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return "UpdateComponentFieldMsg object: {c: " + c + ", f: " + f + ", v: " + v + "}";
	}

}

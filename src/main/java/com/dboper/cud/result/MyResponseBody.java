package com.dboper.cud.result;

public class MyResponseBody {

	private int status=1;
	private String message="ok";
	private Object data;
	
	public MyResponseBody() {
		super();
	}
	
	public MyResponseBody(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}

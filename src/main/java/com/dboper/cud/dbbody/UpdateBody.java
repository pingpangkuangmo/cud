package com.dboper.cud.dbbody;


public class UpdateBody<T> {
	
	private T newObj;
	private T oldObj;
	
	public UpdateBody() {
		super();
	}
	public UpdateBody(T newObj, T oldObj) {
		super();
		this.newObj = newObj;
		this.oldObj = oldObj;
	}
	public T getNewObj() {
		return newObj;
	}
	public void setNewObj(T newObj) {
		this.newObj = newObj;
	}
	public T getOldObj() {
		return oldObj;
	}
	public void setOldObj(T oldObj) {
		this.oldObj = oldObj;
	}
}

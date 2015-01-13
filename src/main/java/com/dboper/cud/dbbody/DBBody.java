package com.dboper.cud.dbbody;

import java.util.HashMap;
import java.util.Map;

import com.dboper.cud.conf.Constants;

public class DBBody {

	private String table;
	private Map<String,Object> map=new HashMap<String,Object>();
	
	public DBBody() {
		super();
	}
	public DBBody(String table, Map<String,Object> map) {
		super();
		this.table = table;
		this.map = map;
	}
	public String getTable() {
		return table;
	}
	public String getFullTable(){
		return Constants.TABLE_PREFIX+table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public Map<String,Object> getMap() {
		return map;
	}
	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
	
	
}

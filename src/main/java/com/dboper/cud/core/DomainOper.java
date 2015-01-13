package com.dboper.cud.core;

import java.util.List;
import java.util.Map;

import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.result.MyResponseBody;


public interface DomainOper {

	public MyResponseBody insert(DBBody body);
	public MyResponseBody insertAndGetId(DBBody dbBody);
	public MyResponseBody insertList(List<DBBody> bodies);
	public MyResponseBody delete(DBBody body);
	public MyResponseBody update(String table_name,Map<String,Object> body,Map<String,Object> params);
	public MyResponseBody deleteById(String table_name,int id);
	public MyResponseBody deleteByForeign(String table_name,String foreign_name,Object foreign_id);
	public Map<Integer,MyResponseBody> deleteBatchById(String table_name,List<Integer> ids);
	public int executeSqlCount(String sql);
}

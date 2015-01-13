package com.dboper.cud.dao;

import java.util.List;
import java.util.Map;

import com.dboper.cud.base.BaseDBBEntity;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public interface BaseDao<T extends BaseDBBEntity>{

	public MyResponseBody insert(T t);
	public MyResponseBody insertList(List<T> ts);
	public MyResponseBody insertAndGetId(T t);
	public MyResponseBody deleteById(int id);
	public MyResponseBody deleteBatchStatusByIds(List<Integer> ids);
	public MyResponseBody deleteStatusById(int id);
	public MyResponseBody deleteStatusByForeign(String foreignKey,Object value);
	public MyResponseBody deleteBatchStatusById(List<Integer> ids);
	public void delateAllData();
	public MyResponseBody update(T t,int id);
	public MyResponseBody update(T t,Map<String,Object> params);
	
	public MyResponseBody insert(DBBody t);
	public MyResponseBody insertListDBBody(List<DBBody> ts);
	public MyResponseBody insertAndGetId(DBBody t);
	public MyResponseBody update(DBBody t,int id);
	public MyResponseBody update(DBBody t,Map<String,Object> params);
	public MyResponseBody update(Map<String,Object> body,Map<String,Object> params);
	public MyResponseBody update(UpdateBody<T> ts);
	public MyResponseBody update(Map<String,Object> body,int id);
	
}

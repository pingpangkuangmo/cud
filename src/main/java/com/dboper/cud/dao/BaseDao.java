package com.dboper.cud.dao;

import java.util.List;
import java.util.Map;

import com.dboper.cud.base.BaseEntity;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public interface BaseDao<T extends BaseEntity>{

	public MyResponseBody insert(T t);
	public MyResponseBody insertList(List<T> ts);
	public MyResponseBody insertAndGetId(T t);
	public MyResponseBody deleteById(Long id);
	public MyResponseBody deleteBatchStatusByIds(List<Long> ids);
	public MyResponseBody deleteStatusById(Long id);
	public MyResponseBody deleteStatusByForeign(String foreignKey,Object value);
	public MyResponseBody deleteForeignStatusByForeign(String foreignTable,String foreignKey,Object value);
	public MyResponseBody deleteBatchStatusById(List<Long> ids);
	public void delateAllData();
	public MyResponseBody update(T t,Long id);
	public MyResponseBody update(T t,Map<String,Object> params);
	
	public MyResponseBody update(String table,Map<String,Object> body,Map<String,Object> params);
	
	public MyResponseBody insert(DBBody t);
	public MyResponseBody insertListDBBody(List<DBBody> ts);
	public MyResponseBody insertAndGetId(DBBody t);
	public MyResponseBody update(DBBody t,Long id);
	public MyResponseBody update(DBBody t,Map<String,Object> params);
	public MyResponseBody update(Map<String,Object> body,Map<String,Object> params);
	public MyResponseBody update(UpdateBody<T> ts);
	public MyResponseBody update(Map<String,Object> body,Long id);
	
	public int executeSqlCount(String sql);
	
}

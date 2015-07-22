package com.dboper.cud.service;

import java.util.List;
import java.util.Map;

import com.dboper.cud.base.BaseEntity;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public interface BaseService<T extends BaseEntity>{
	
	public MyResponseBody insert(DBBody dbBody);
	
	public MyResponseBody insertAndGetId(DBBody dbBody);

	public MyResponseBody insert(T t);
	
	public MyResponseBody insertAndGetId(T t);

	public MyResponseBody deleteStatusById(Long id);

	public MyResponseBody update(UpdateBody<T> ts);
	
	public MyResponseBody update(DBBody dbBody,Long id);
	
	public MyResponseBody update(DBBody dbBody,Map<String,Object> params);
	
	public MyResponseBody update(String table,Map<String,Object> body,Map<String,Object> params);

	public MyResponseBody deleteBatchStatusByIds(List<Long> ids);
}

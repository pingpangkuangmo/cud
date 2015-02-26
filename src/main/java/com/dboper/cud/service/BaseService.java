package com.dboper.cud.service;

import java.util.List;
import java.util.Map;

import com.dboper.cud.base.BaseDBBEntity;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public interface BaseService<T extends BaseDBBEntity>{
	
	public MyResponseBody insert(DBBody dbBody);
	
	public MyResponseBody insertAndGetId(DBBody dbBody);

	public MyResponseBody insert(T t);
	
	public MyResponseBody insertAndGetId(T t);

	public MyResponseBody deleteStatusById(int id);

	public MyResponseBody update(UpdateBody<T> ts);
	
	public MyResponseBody update(DBBody dbBody,int id);
	
	public MyResponseBody update(DBBody dbBody,Map<String,Object> params);

	public MyResponseBody deleteBatchStatusByIds(List<Integer> ids);
}

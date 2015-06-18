package com.dboper.cud.service;

import java.util.List;
import java.util.Map;

import com.dboper.cud.base.BaseEntity;
import com.dboper.cud.dao.BaseDao;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>{
	
	
	@Override
	public MyResponseBody insert(DBBody dbBody) {
		return getBaseDao().insert(dbBody);
	}

	@Override
	public MyResponseBody insert(T t) {
		return getBaseDao().insert(t);
	}

	@Override
	public MyResponseBody deleteStatusById(Long id) {
		return getBaseDao().deleteStatusById(id);
	}

	@Override
	public MyResponseBody update(UpdateBody<T> ts) {
		return getBaseDao().update(ts);
	}
	
	@Override
	public MyResponseBody update(DBBody dbBody, Long id) {
		return getBaseDao().update(dbBody,id);
	}

	@Override
	public MyResponseBody update(DBBody dbBody, Map<String, Object> params) {
		return getBaseDao().update(dbBody,params);
	}

	@Override
	public MyResponseBody deleteBatchStatusByIds(List<Long> ids) {
		return getBaseDao().deleteBatchStatusByIds(ids);
	}
	
	@Override
	public MyResponseBody insertAndGetId(DBBody dbBody) {
		return getBaseDao().insertAndGetId(dbBody);
	}

	@Override
	public MyResponseBody insertAndGetId(T t) {
		return getBaseDao().insertAndGetId(t);
	}

	protected abstract BaseDao<T> getBaseDao();

}

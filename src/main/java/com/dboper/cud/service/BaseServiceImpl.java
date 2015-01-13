package com.dboper.cud.service;

import java.util.List;

import com.dboper.cud.base.BaseDBBEntity;
import com.dboper.cud.dao.BaseDao;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public abstract class BaseServiceImpl<T extends BaseDBBEntity> implements BaseService<T>{
	
	@Override
	public MyResponseBody insert(T t) {
		return getBaseDao().insert(t);
	}

	@Override
	public MyResponseBody deleteStatusById(int id) {
		return getBaseDao().deleteStatusById(id);
	}

	@Override
	public MyResponseBody update(UpdateBody<T> ts) {
		return getBaseDao().update(ts);
	}

	@Override
	public MyResponseBody deleteBatchStatusByIds(List<Integer> ids) {
		return getBaseDao().deleteBatchStatusByIds(ids);
	}
	
	protected abstract BaseDao<T> getBaseDao();

}

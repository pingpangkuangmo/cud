package com.dboper.cud.service;

import java.util.List;

import com.dboper.cud.base.BaseDBBEntity;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;

public interface BaseService<T extends BaseDBBEntity>{

	public MyResponseBody insert(T t);

	public MyResponseBody deleteStatusById(int id);

	public MyResponseBody update(UpdateBody<T> ts);

	public MyResponseBody deleteBatchStatusByIds(List<Integer> ids);
}

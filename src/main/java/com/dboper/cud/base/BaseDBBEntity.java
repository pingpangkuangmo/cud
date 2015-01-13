package com.dboper.cud.base;

import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.DBBodyUtil;
import com.dboper.cud.dbbody.TODBBody;

public abstract class BaseDBBEntity extends BaseStatusEntity implements TODBBody{

	@Override
	public DBBody toInsertDBBody() {
		return DBBodyUtil.toDBBody(getTableName(),this);
	}

	@Override
	public DBBody toUpdateDBBody() {
		return DBBodyUtil.toUpdateDBBody(getTableName(),this);
	}
	
	protected abstract String getTableName();

}

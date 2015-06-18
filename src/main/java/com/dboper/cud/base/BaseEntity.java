package com.dboper.cud.base;

import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.DBBodyUtil;
import com.dboper.cud.dbbody.TODBBody;

public abstract class BaseEntity implements TODBBody{

	private Long id;
	
	@Override
	public DBBody toDBBody() {
		return DBBodyUtil.toDBBody(getTableName(),this);
	}

	@Override
	public DBBody toIdDBBody() {
		return DBBodyUtil.toIdDBBody(getTableName(),this);
	}
	
	protected abstract String getTableName();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

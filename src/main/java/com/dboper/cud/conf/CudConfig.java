package com.dboper.cud.conf;

import org.springframework.stereotype.Component;

@Component
public class CudConfig {

	private String tablePrefix;

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
		Constants.TABLE_PREFIX=tablePrefix;
	}

}

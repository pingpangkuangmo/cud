package com.dboper.cud.util;

import java.sql.Date;
import java.util.Map;
import java.util.Set;

import com.dboper.cud.conf.Constants;

public class MapToStringUtil {
	
	public static String setMapToString(Map<String,Object> map,String join){
		if(map.isEmpty()){
			return "";
		}
		StringBuilder str=new StringBuilder();
		Set<String> keys=map.keySet();
		for(String key:keys){
			Object value=map.get(key);
			String oper="=";
			String tmp_key=key;
			if(!tmp_key.startsWith(Constants.TABLE_PREFIX)){
				tmp_key=Constants.TABLE_PREFIX+tmp_key;
			}
			if(value instanceof String || value instanceof Date){
				str.append(tmp_key+oper+"'"+map.get(key)+"' "+join+" ");
			}else{
				str.append(tmp_key+oper+map.get(key)+" "+join+" ");
			}
		}
		int len=str.length();
		str.delete(len-join.length()-2,len-1);
		return str.toString();
	}
}

package com.dboper.cud.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	
	public static Map<String,Object> getMap(String key,Object value){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(key, value);
		return map;
	}
	
	public static Map<String,Object> getMap(String key1,Object value1,String key2,Object value2){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		return map;
	}
	
	public static boolean isNullOrEmpty(Map<String,Object> map){
		if(map==null || map.size()<1){
			return true;
		}else{
			return false;
		}
	}
}

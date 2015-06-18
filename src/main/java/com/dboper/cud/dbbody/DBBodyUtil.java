package com.dboper.cud.dbbody;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class DBBodyUtil {
	
	public static DBBody toDBBody(String table,Object t){
		DBBody body=new DBBody();
		body.setTable(table);
		body.setMap(toMap(t,table));
		return body;
	}
	
	public static DBBody toIdDBBody(String table,Object t){
		DBBody body=new DBBody();
		body.setTable(table);
		body.setMap(toIdMap(t,table));
		return body;
	} 

	private static Map<String,Object> toIdMap(Object t,String table){
		if (t == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		Map<String,Object> map=new HashMap<String,Object>();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd:pds){  
			     String name=pd.getName();
			     if(name.equals("id")){
			    	 Method m=pd.getReadMethod();
			         Object value=m.invoke(t);
			         if(value!=null){
			        	 map.put(table+".id",value);
			        	 return map;
			         }
			     }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Object:"+t+" mast have id property");
	}
	
	private static Map<String,Object> toMap(Object t,String table){
		if (t == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		Map<String,Object> map=new HashMap<String,Object>();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
	        for(PropertyDescriptor pd:pds){  
	             String name=pd.getName();
	             if(name.equals("id")){
	            	 continue;
	             }
	             name=table+"."+name;
	             Method m=pd.getReadMethod();
	             Object value=m.invoke(t);
	             if(value instanceof Integer){
	            	 if(((Integer)value)>0){
	            		 map.put(name,value);
	            	 }
	             }else if(value instanceof String){
	            	 if(!((String)value).equals("")){
	            		 map.put(name,value);
	            	 }
	             }else if(value instanceof Float){
	            	 if(((Float)value)>0.0f){
	            		 map.put(name,value);
	            	 }
	             }else if(value instanceof Double){
	            	 if(((Double)value)>0.0d){
	            		 map.put(name,String.format("%.2f",value));
	            	 }
	             }else if(value instanceof Date){
	            	 if(value!=null){
	            		 map.put(name,value);
	            	 }
	             }else if(value instanceof Enum){
	            	 map.put(name,value);
	             }else if(value instanceof java.util.Date){
	            	 if(value!=null){
	            		 map.put(name,new Date(((java.util.Date) value).getTime()));
	            	 }
	             }
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return map;
	}
}

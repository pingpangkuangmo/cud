package com.dboper.cud.result;

import org.apache.log4j.Logger;

public class MyResponseBodyUtil {
	
	private static Logger log=Logger.getLogger("console");

	public static MyResponseBody failed(MyResponseBody result,String message){
		if(result==null){
			result=new MyResponseBody();
		}
		log.info(message);
		result.setStatus(0);
		result.setMessage(message);
		return result;
	}
	
	public static MyResponseBody failed(String message){
		MyResponseBody result=new MyResponseBody();
		log.info(message);
		result.setStatus(0);
		result.setMessage(message);
		return result;
	}
	
	public static MyResponseBody success(MyResponseBody result){
		if(result==null){
			result=new MyResponseBody();
		}
		result.setStatus(1);
		return result;
	}
	
	public static MyResponseBody success(){
		MyResponseBody result=new MyResponseBody();
		result.setStatus(1);
		return result;
	}
	
	public static MyResponseBody success(Object data){
		MyResponseBody result=new MyResponseBody();
		result.setData(data);
		result.setStatus(1);
		return result;
	}
}

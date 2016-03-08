package com.dboper.cud.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.dboper.cud.conf.Constants;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.result.MyResponseBody;
import com.dboper.cud.result.MyResponseBodyUtil;
import com.dboper.cud.util.MapToStringUtil;

@Service("cud-domainOper")
public class DomainOperImpl implements DomainOper {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Logger log=Logger.getLogger("console");
	
	public MyResponseBody insert(DBBody body) {
		MyResponseBody result=new MyResponseBody();
		Map<String,Object> map=body.getMap();
		if(map.isEmpty()){
			return MyResponseBodyUtil.failed("没有数据");
		}
		String sql=toSql(body);
		if(!executeSql(sql)){
			return MyResponseBodyUtil.failed("增添失败,请重试");
		}
		return result;
	}
	
	public MyResponseBody insertAndGetId(DBBody dbBody){
		MyResponseBody result=new MyResponseBody();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		final String sql=toSql(dbBody);
		if(sql==null || sql.length()<1){
			return MyResponseBodyUtil.failed("sql构建失败");
		}
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection paramConnection)
						throws SQLException {
					return paramConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				}
			},keyHolder);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return MyResponseBodyUtil.failed(result,"sql="+sql+",执行失败");
		}
		result.setData(keyHolder.getKey().longValue());
		return result;
	}
	
	private String toSql(DBBody dbBody){
		Map<String,Object> map=dbBody.getMap();
		Set<String> keys=map.keySet();
		StringBuilder columns=new StringBuilder();
		StringBuilder values=new StringBuilder();
		for(String key:keys){
			String tmpKey=key;
			if(!tmpKey.contains(Constants.TABLE_PREFIX)){
				tmpKey=Constants.TABLE_PREFIX+tmpKey;
			}
			columns.append(tmpKey+",");
			Object value=map.get(key);
			if(value instanceof String || value instanceof Date || value instanceof Timestamp){
				values.append("'"+map.get(key)+"',");
			}else{
				values.append(value+",");
			}
		}
		columns.deleteCharAt(columns.length()-1);
		values.deleteCharAt(values.length()-1);
		StringBuilder sql=new StringBuilder("insert into ");
		sql.append(dbBody.getFullTable());
		sql.append("("+columns.toString()+") ");
		sql.append("value("+values.toString()+")");
		String tmp=sql.toString();
		log.warn(tmp);
		return tmp;
	}
	
	public MyResponseBody insertList(List<DBBody> bodies) {
		MyResponseBody result=new MyResponseBody();
		int size=bodies.size();
		if(bodies==null || size<1){
			return MyResponseBodyUtil.failed(result,"批量插入的列表为空，无法插入");
		}
		DBBody body=bodies.get(0);
		List<String> keys=new ArrayList<String>();
		String columns=getKeysStr(body,keys);
		StringBuilder values=new StringBuilder();
		for(int i=0;i<size;i++){
			values.append(getValueStr(keys,bodies.get(i)));
			values.append(",");
		}
		values.deleteCharAt(values.length()-1);
		StringBuilder sql=new StringBuilder();
		sql.append("insert into ");
		sql.append(body.getFullTable());
		sql.append(columns);
		sql.append(" values");
		sql.append(values);
		log.warn(sql.toString());
		if(!executeSql(sql.toString())){
			result.setData(bodies);
			return MyResponseBodyUtil.failed(result,"该组在插入时发生异常");
		}
		return result;
	}
	
	public String getKeysStr(DBBody body,List<String> keys){
		StringBuilder values=new StringBuilder("(");
		Map<String,Object> map=body.getMap();
		Set<String> set=map.keySet();
		for(String key:set){
			keys.add(key);
			String tmpKey=key;
			if(!tmpKey.contains(Constants.TABLE_PREFIX)){
				tmpKey=Constants.TABLE_PREFIX+tmpKey;
			}
			values.append(tmpKey+",");
		}
		values.deleteCharAt(values.length()-1);
		values.append(")");
		return values.toString();
	}
	
	private String getValueStr(List<String> keys,DBBody body){
		StringBuilder values=new StringBuilder("(");
		Map<String,Object> map=body.getMap();
		for(String key:keys){
			Object value=map.get(key);
			if(value instanceof Integer || value instanceof Float){
				values.append(value+",");
			}else{
				values.append("'"+map.get(key)+"',");
			}
		}
		values.deleteCharAt(values.length()-1);
		values.append(")");
		return values.toString();
	}
	
	public MyResponseBody delete(DBBody body) {
		MyResponseBody result=new MyResponseBody();
		Map<String,Object> map=body.getMap();
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(body.getFullTable());
		String params=MapToStringUtil.setMapToString(map,"and");
		if(!params.equals("")){
			sql.append(" where ");
			sql.append(params);
		}
		log.warn(sql.toString());
		if(!executeSql(sql.toString())){
			MyResponseBodyUtil.failed(result,"sql="+sql+";删除时发生异常,请重试");
		}
		return result;
	}

	public MyResponseBody update(String table_name,Map<String,Object> body,Map<String,Object> params) {
		MyResponseBody result=new MyResponseBody();
		if(body.isEmpty()){
			return MyResponseBodyUtil.failed(result,"没有数据");
		}
		StringBuilder sql=new StringBuilder("update ");
		sql.append(Constants.TABLE_PREFIX+table_name);
		sql.append(" set ");
		sql.append(MapToStringUtil.setMapToString(body,","));
		String where_str=MapToStringUtil.setMapToString(params,"and");
		if(!where_str.equals("")){
			sql.append(" where ");
			sql.append(where_str);
		}
		log.warn(sql.toString());
		if(!executeSql(sql.toString())){
			return MyResponseBodyUtil.failed(result,"更新发生异常,请重试");
		}
		return result;
	}

	public MyResponseBody deleteById(String table_name, Long id) {
		MyResponseBody result=new MyResponseBody();
		if(deleteByForeign(table_name,"id",id).getStatus()==0){
			MyResponseBodyUtil.failed(result,"删除时发生异常");
		}
		return result;
	}

	public Map<Long,MyResponseBody> deleteBatchById(String table_name,
			List<Long> ids) {
		Map<Long,MyResponseBody> map=new HashMap<Long,MyResponseBody>();
		if(ids==null || ids.size()<1){
			return map;
		}
		for(Long id:ids){
			map.put(id,deleteById(table_name, id));
		}
		return map;
	}

	public MyResponseBody deleteByForeign(String table_name, String foreign_name,
			Object foreign_id) {
		MyResponseBody result=new MyResponseBody();
		if(foreign_name==null || foreign_name.trim().equals("")){
			return MyResponseBodyUtil.failed(result,"没有指定外键");
		}
		if(foreign_id==null){
			return MyResponseBodyUtil.failed(result,"外键值为空");
		}
		if(foreign_id instanceof Integer && (Integer)foreign_id<1){
			return MyResponseBodyUtil.failed(result,"外键值不合法");
		}
		if(foreign_id instanceof String && ((String) foreign_id).trim().equals("")){
			return MyResponseBodyUtil.failed(result,"外键值不合法");
		}
		DBBody body=new DBBody();
		body.setTable(table_name);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(table_name+"."+foreign_name,foreign_id);
		body.setMap(map);
		return delete(body);
	}

	public int executeSqlCount(String sql) {
		int count=-1;
		try {
			count=jdbcTemplate.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	private boolean executeSql(String sql){
		int count=-1;
		count=executeSqlCount(sql);
		if(count<1){
			return false;
		}else{
			return true;
		}
	}
}

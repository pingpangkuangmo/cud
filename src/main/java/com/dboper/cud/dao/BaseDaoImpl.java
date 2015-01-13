package com.dboper.cud.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dboper.cud.base.BaseDBBEntity;
import com.dboper.cud.conf.Constants;
import com.dboper.cud.core.DomainOper;
import com.dboper.cud.dbbody.DBBody;
import com.dboper.cud.dbbody.UpdateBody;
import com.dboper.cud.result.MyResponseBody;
import com.dboper.cud.result.MyResponseBodyUtil;
import com.dboper.cud.util.MapUtils;

public abstract class BaseDaoImpl<T extends BaseDBBEntity> implements BaseDao<T>{

	@Resource(name="cud-domainOper")
	private DomainOper domainOper;
	
	@Override
	public MyResponseBody insert(T t) {
		return insert(getDBbody(t));
	}

	@Override
	public MyResponseBody deleteById(int id) {
		return domainOper.deleteById(getTableName(),id);
	}

	@Override
	public MyResponseBody update(T t, int id) {
		return update(getDBbody(t),id);
	}
	
	@Override
	public MyResponseBody insert(DBBody t) {
		return domainOper.insert(t);
	}

	@Override
	public MyResponseBody update(DBBody t, int id) {
		return update(t,getIdParams(t,id));
	}


	@Override
	public MyResponseBody update(T t, Map<String, Object> params) {
		return update(getDBbody(t), params);
	}

	@Override
	public MyResponseBody update(DBBody t, Map<String, Object> params) {
		return update(t.getMap(),params);
	}
	
	@Override
	public MyResponseBody update(Map<String, Object> body,Map<String, Object> params) {
		String table=getTableName();
		if(table==null || table.length()<1){
			return MyResponseBodyUtil.failed("没有表名");
		}
		return domainOper.update(table,body, params);
	}
	
	@Override
	public MyResponseBody deleteStatusById(int id) {
		return _deleteByStatusById(id, getTableName());
	}

	@Override
	public MyResponseBody deleteBatchStatusById(List<Integer> ids) {
		MyResponseBody result=new MyResponseBody();
		if(ids==null || ids.size()<1){
			return MyResponseBodyUtil.failed(result,"要删除的列表为空");
		}
		List<MyResponseBody> list=new ArrayList<MyResponseBody>();
		result.setData(list);
		String table_name=getTableName();
		for(Integer id:ids){
			list.add(_deleteByStatusById(id,table_name));
		}
		return result;
	}
	
	@Override
	public MyResponseBody update(UpdateBody<T> ts) {
		return update(getDBbody(ts.getNewObj()).getMap(),ts.getOldObj().getId());
	}

	@Override
	public MyResponseBody insertAndGetId(T t) {
		return insertAndGetId(getDBbody(t));
	}

	@Override
	public MyResponseBody insertAndGetId(DBBody t) {
		return domainOper.insertAndGetId(t);
	}

	@Override
	public MyResponseBody deleteStatusByForeign(String foreignKey, Object value) {
		return _deleteByStatusKeyValue(foreignKey, value, getTableName());
	}

	@Override
	public MyResponseBody deleteBatchStatusByIds(List<Integer> ids) {
		MyResponseBody ret=new MyResponseBody();
		Map<Integer,MyResponseBody> map=new HashMap<Integer,MyResponseBody>();
		ret.setData(map);
		for(Integer id:ids){
			map.put(id,deleteStatusById(id));
		}
		return ret;
	}

	@Override
	public MyResponseBody update(Map<String, Object> body, int id) {
		return update(body,MapUtils.getMap(getTableName()+".id",id));
	}

	@Override
	public MyResponseBody insertList(List<T> ts) {
		List<DBBody> bodies=new ArrayList<DBBody>();
		for(T t:ts){
			bodies.add(t.toInsertDBBody());
		}
		return insertListDBBody(bodies);
	}

	@Override
	public MyResponseBody insertListDBBody(List<DBBody> ts) {
		return domainOper.insertList(ts);
	}
	
	@Override
	public void delateAllData() {
		domainOper.executeSqlCount("delete from "+getFullTableName());
	}

	private MyResponseBody _deleteByStatusById(int id,String table_name){
		if(id<1){
			throw new IllegalArgumentException("id="+id+" is not valid");
		}
		return _deleteByStatusKeyValue("id",id,table_name);
	}
	
	private MyResponseBody _deleteByStatusKeyValue(String key,Object value,String table_name){
		return update(MapUtils.getMap(table_name+".status",0),MapUtils.getMap(table_name+"."+key,value));
	}
	
	private DBBody getDBbody(T t){
		return t.toInsertDBBody();
	}
	
	private Map<String, Object> getIdParams(DBBody t, int id) {
		return MapUtils.getMap(getFullTableName()+".id",id);
	}
	
	private String getFullTableName(){
		return Constants.TABLE_PREFIX+getTableName();
	}
	
	protected  abstract String getTableName();

}

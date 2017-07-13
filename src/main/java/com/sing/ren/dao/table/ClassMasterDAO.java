package com.sing.ren.dao.table;import java.util.List;import java.util.Map;import org.apache.commons.collections.MapUtils;import org.springframework.stereotype.Component;import com.sing.ren.dao.BaseDao;import com.sing.ren.pojo.ClassMaster;@Componentpublic class ClassMasterDAO extends BaseDao {	@SuppressWarnings("deprecation")	public List<ClassMaster> query(ClassMaster dto) throws Exception {		logger.debug("ClassMasterDAO queryClassMaster");		return getSqlMapClientTemplate().queryForList(nsForTableStatements("select_count"), dto);	}	@SuppressWarnings("deprecation")	public void insert(ClassMaster dto) throws Exception {		logger.debug("ClassMasterDAO insertClassMaster");		getSqlMapClientTemplate().insert( nsForTableStatements("class_master_insert"), dto);	}	@SuppressWarnings("deprecation")	public void update(ClassMaster dto) throws Exception {		logger.debug("ClassMasterDAO updateClassMaster");		getSqlMapClientTemplate().queryForList(nsForTableStatements("class_master_update"), dto);	}	@SuppressWarnings("deprecation")	public void delete(ClassMaster dto) throws Exception {		logger.debug("ClassMasterDAO ClassMasterDetail");		getSqlMapClientTemplate().queryForList(nsForTableStatements("class_master_delete"), dto);	}	public ClassMaster mapToObject(Map<String, Object> map) throws Exception {		ClassMaster obj = new ClassMaster();		obj.setClassMasterId( MapUtils.getInteger(map, "class_master_id") );		obj.setName( MapUtils.getString(map, "name", "") );		obj.setType( MapUtils.getString(map, "type", "") );		obj.setLevel( MapUtils.getString(map, "level", "") );		obj.setStatus( MapUtils.getString(map, "status", "") );		obj.setPrice( MapUtils.getString(map, "price", "") );		obj.setSummary( MapUtils.getString(map, "summary", "") );		obj.setCount( MapUtils.getString(map, "count", "") );		obj.setRest( MapUtils.getString(map, "rest", "") );		obj.setSDate( MapUtils.getString(map, "s_date", "") );		obj.setEDate( MapUtils.getString(map, "e_date", "") );		obj.setPlace( MapUtils.getString(map, "place", "") );		obj.setTeacherId( MapUtils.getString(map, "teacher_id", "") );		obj.setUpdater( MapUtils.getString(map, "updater", "") );		obj.setUpdateTime( MapUtils.getString(map, "update_time", "") );	return obj;	}}
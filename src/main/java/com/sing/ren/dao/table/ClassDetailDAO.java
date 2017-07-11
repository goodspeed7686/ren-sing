package com.sing.ren.dao.table;import java.util.ArrayList;import java.util.List;import java.util.Map;import org.apache.commons.collections.MapUtils;import org.springframework.stereotype.Component;import com.sing.ren.dao.BaseDao;import com.sing.ren.pojo.ClassDetail;@Componentpublic class ClassDetailDAO extends BaseDao {	@SuppressWarnings("deprecation")	public void insertDB(ClassDetail dto) throws Exception {		logger.debug("ClassDetailDAO insertClassDetail");		getSqlMapClientTemplate().insert( nsForTableStatements("class_detail_insert"), dto);	}	@SuppressWarnings("deprecation")	public void insertDB(Map<String, Object> map) throws Exception {		logger.debug("ClassDetailDAO insertClassDetail");		getSqlMapClientTemplate().insert( nsForTableStatements("class_detail_insert"), map);	}		public List<ClassDetail> getDetail(Map<String, Object> map) throws Exception {		logger.debug("ClassDetailDAO queryClassDetail");//		List<Map<String, Object>> resultMapList = query(map, nsForTableStatements("class_detail_query"));//		//		List<ClassDetail> result = new ArrayList<ClassDetail>();////		for (Map<String, Object> resultMap : resultMapList){//			result.add( mapToObject(resultMap) );//		}		return getSqlMapClientTemplate().queryForList(nsForTableStatements("class_detail_query"), map);	}	public List<ClassDetail> getDetail(ClassDetail map) throws Exception {		logger.debug("ClassDetailDAO queryClassDetail");		return getSqlMapClientTemplate().queryForList(nsForTableStatements("class_detail_query"), map);	}	public void updateDetail(Map<String,Object>map) throws Exception{		logger.debug("ClassDetailDAO updateClassDetail");		update( map,nsForTableStatements("class_detail_update"));	}		public void deleteDB(Map<String, Object> map) throws Exception {		logger.debug("ClassDetailDAO deleteClassDetail");		delete(map, nsForTableStatements("class_detail_delete"));	}	public ClassDetail mapToObject(Map<String, Object> map) throws Exception {		ClassDetail obj = new ClassDetail();		obj.setClassDetailId( MapUtils.getInteger(map, "class_detail_id") );		obj.setClassMasterId( MapUtils.getInteger(map, "class_master_id") );		obj.setStudentId( MapUtils.getString(map, "student_id", "") );		obj.setTeacherId( MapUtils.getString(map, "teacher_id", "") );		obj.setSong( MapUtils.getString(map, "song", "") );		obj.setDate( MapUtils.getString(map, "date", "") );		obj.setTime( MapUtils.getString(map, "time", "") );		obj.setHw( MapUtils.getString(map, "hw", "") );		obj.setTeacherNote( MapUtils.getString(map, "teacher_note", "") );		obj.setStudentNote( MapUtils.getString(map, "student_note", "") );		obj.setUpdater( MapUtils.getString(map, "updater", "") );		obj.setUpdateTime( MapUtils.getString(map, "update_time", "") ); 	return obj;	}}
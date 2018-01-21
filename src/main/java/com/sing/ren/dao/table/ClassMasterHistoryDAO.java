package com.sing.ren.dao.table;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sing.ren.dao.BaseDao;
@Component
public class ClassMasterHistoryDAO extends BaseDao {

	@Override
	public String statementIdForInsert() throws Exception {
		return nsForTableStatements("class_master_history_insert");
	}

	@Override
	public String statementIdForQuery() throws Exception {
		return nsForTableStatements("class_master_history_query");
	}

	@Override
	public String statementIdForUpdate() throws Exception {
		return nsForTableStatements("class_master_history_update");
	}

	@Override
	public String statementIdForUpsert() throws Exception {
		return nsForTableStatements("class_master_history_upsert");
	}

	@Override
	public String statementIdForDelete() throws Exception {
		return nsForTableStatements("class_master_history_delete");
	}

	public void insertDB(Map<String,Object> map) throws Exception {
		logger.debug("ClassMasterHistoryDAO insertClassMasterHistory");
//		updaterAndUpdateTime(map);
		insert(map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryDB(Map<String,Object> map) throws Exception {
		logger.debug("ClassMasterHistoryDAO queryClassMasterHistory");
		return (List<Map<String,Object>>) query(map);
	}

	public void updateDB(Map<String,Object> map) throws Exception{
		logger.debug("ClassMasterHistoryDAO updateClassMasterHistory");
//		updaterAndUpdateTime(map);
		update(map);
	}

	public void deleteDB(Map<String,Object> map) throws Exception {
		logger.debug("ClassMasterHistoryDAO deleteClassMasterHistory");
		delete(map);
	}

	public void upsertDB(Map<String,Object> map) throws Exception {
		logger.debug("ClassMasterHistoryDAO upsertClassMasterHistory");
//		updaterAndUpdateTime(map);
		upsert(map);
	}

}
package com.sing.ren.dao.table;import java.util.List;import java.util.Map;import org.springframework.stereotype.Component;import com.sing.ren.dao.BaseDao;@Componentpublic class PersonDAO extends BaseDao {	@Override	public String statementIdForInsert() throws Exception {		return nsForTableStatements("person_insert");	}	@Override	public String statementIdForQuery() throws Exception {		return nsForTableStatements("person_query");	}	@Override	public String statementIdForUpdate() throws Exception {		return nsForTableStatements("person_update");	}	@Override	public String statementIdForUpsert() throws Exception {		return nsForTableStatements("person_upsert");	}	@Override	public String statementIdForDelete() throws Exception {		return nsForTableStatements("person_delete");	}	public void insertDB(Map<String,Object> map) throws Exception {		logger.debug("PersonDAO insertPerson");		updaterAndUpdateTime(map);		insert(map);	}	@SuppressWarnings("unchecked")	public List<Map<String,Object>> queryDB(Map<String,Object> map) throws Exception {		logger.debug("PersonDAO queryPerson");		return (List<Map<String,Object>>) query(map);	}	public void updateDB(Map<String,Object> map) throws Exception{		logger.debug("PersonDAO updatePerson");		upsert(map);	}	public void deleteDB(Map<String,Object> map) throws Exception {		logger.debug("PersonDAO deletePerson");		delete(map);	}	public void upsertDB(Map<String,Object> map) throws Exception {		logger.debug("PersonDAO upsertPerson");		updaterAndUpdateTime(map);		upsert(map);	}}
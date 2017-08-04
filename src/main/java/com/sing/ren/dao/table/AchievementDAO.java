package com.sing.ren.dao.table;import java.util.List;import java.util.Map;import org.springframework.stereotype.Component;import com.sing.ren.dao.BaseDao;@Componentpublic class AchievementDAO extends BaseDao {	@Override	public String statementIdForInsert() throws Exception {		return nsForTableStatements("achievement_insert");	}	@Override	public String statementIdForQuery() throws Exception {		return nsForTableStatements("achievement_query");	}	@Override	public String statementIdForUpdate() throws Exception {		return nsForTableStatements("achievement_update");	}	@Override	public String statementIdForUpsert() throws Exception {		return nsForTableStatements("achievement_upsert");	}	@Override	public String statementIdForDelete() throws Exception {		return nsForTableStatements("achievement_delete");	}	public void insertDB(Map<String,Object> map) throws Exception {		logger.debug("AchievementDAO insertAchievement");		updaterAndUpdateTime(map);		insert(map);	}	@SuppressWarnings("unchecked")	public List<Map<String,Object>> queryDB(Map<String,Object> map) throws Exception {		logger.debug("AchievementDAO queryAchievement");		return (List<Map<String,Object>>) query(map);	}	public void updateDB(Map<String,Object> map) throws Exception{		logger.debug("AchievementDAO updateAchievement");		upsert(map);	}	public void deleteDB(Map<String,Object> map) throws Exception {		logger.debug("AchievementDAO deleteAchievement");		delete(map);	}	public void upsertDB(Map<String,Object> map) throws Exception {		logger.debug("AchievementDAO upsertAchievement");		updaterAndUpdateTime(map);		upsert(map);	}}
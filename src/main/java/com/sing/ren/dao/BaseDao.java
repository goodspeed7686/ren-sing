package com.sing.ren.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sing.ren.common.CommonTools;
import com.sing.ren.exception.SqlAccessException;
import com.sing.ren.ibatis.BatisRow;
import com.sing.ren.pojo.DAOEntity;

@SuppressWarnings("deprecation")
public class BaseDao extends SqlMapClientDaoSupport implements BaseAccessInterface<Object> {

	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;

	private String updater;
	
//	@Value("#{lmsConf[dbHost]}")
	private String dbHost;
	
//	@Value("#{lmsConf[dbPort]}")
	private String dbPort;
	
//	@Value("#{lmsConf[dbUser]}")
	private String dbUser;
	
//	@Value("#{lmsConf[dbPass]}")
	private String dbPass;
	
//	@Value("#{lmsConf[dbName]}")
	private String dbName;
	
	public BaseDao() {
		super();
	}
	
	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public void setUpdater(String updater) {
		if (!StringUtils.isEmpty(updater)) {
			this.updater = updater;
		}
	}
	
	protected String nsForTableStatements(String stmtId) {
		return "ren.sing.dao.table." + stmtId;
	}
	
	protected String nsForModuleStatements(String stmtId) {
		return "ren.sing.dao.module." + stmtId;
	}
	
	public String statementIdForQuery() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public String statementIdForInsert() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public String statementIdForUpsert() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public String statementIdForUpdate() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public String statementIdForDelete() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public String keyPropertyName() {
		return "id";
	}

	public int batchSize() {
		return 1000;
	}
	
	public static Map<String, Object> paramMap(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}
	
	public List<BatisRow> resultToList(List<Map<String, Object>> uglyResultSet) {
		List<BatisRow> prettyResultSet = new ArrayList<BatisRow>();
		
		for (Map<String, Object> map : uglyResultSet) {
			prettyResultSet.add(new BatisRow(map));
		}
		
		return prettyResultSet;
	}
	
	@Override
	public List<?> query() throws Exception {
		return query(new HashMap<String, Object>());
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<?> query(Object params) throws Exception {
		List<?> queryResult = new ArrayList<>();
		
		try {
			queryResult.addAll( getSqlMapClientTemplate().queryForList(statementIdForQuery(), params) );
		} catch (DataAccessException e) {
			throw new SqlAccessException(e.getRootCause().toString());
		}
		
		return queryResult;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryOne(Map<String, Object> params, String stmtId) throws Exception {
		try {
			Object result = getSqlMapClientTemplate().queryForObject(stmtId, params);
			return (Map<String, Object>)result;
		} catch (DataAccessException e) {
			throw new SqlAccessException(e.getRootCause().toString());
		}
	}
	
	public int rowCount(Map<String, Object> params, String stmtId) {
		try {
			return Integer.parseInt( getSqlMapClientTemplate().queryForObject(stmtId, params).toString() );
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Object insert(Object params) throws Exception {
		return insert(params, statementIdForInsert());
	}
	
	public Object insert(Object params, String stmtId) throws Exception {
		try {
			return getSqlMapClientTemplate().insert(stmtId, params);
		} catch (DataAccessException e) {
			throw new SqlAccessException(e.getRootCause().toString());
		}
	}
	
	public void upsert(Object params) throws Exception {
		upsert(params, statementIdForUpsert());
	}
	
	public void upsert(Object params, String stmtId) throws Exception {
		try {
			getSqlMapClientTemplate().insert(stmtId, params);
		} catch (DataAccessException e) {
			throw new SqlAccessException(e.getRootCause().toString());
		}
	}
	
	public int update(Object params, String stmtId) throws Exception {
		try {
			return getSqlMapClientTemplate().update(stmtId, params);
		} catch (DataAccessException e) {
			throw new SqlAccessException(e.getRootCause().toString());
		}
	}
	
	public void delete(Object params) throws Exception {
		getSqlMapClientTemplate().delete(this.statementIdForDelete(), params);
	}
	
	public Object delete(Object params, String stmtId) throws Exception {
		try {
			return getSqlMapClientTemplate().delete(stmtId, params);
		} catch (DataAccessException e) {
			throw new SqlAccessException(e.getRootCause().toString());
		}
	}
	
	protected void updaterAndUpdateTime(DAOEntity entity) {
		entity.setUpdater("updater");
		entity.setUpdateTime(CommonTools.getCurrentDateTime());
	}
	
	protected void updaterAndUpdateTime(Map<String, Object> map) {
		if (StringUtils.isEmpty(updater)) {
			map.put("updater", "updater");
		} else {
			map.put("updater", this.updater);
		}
		
		map.put("update_time", CommonTools.getCurrentDateTime());
	}
	
	protected Connection getConnection() throws Exception {
		return DriverManager.getConnection(connectionUrl(), dbUser, dbPass);
	}
	
	private String connectionUrl() {
		StringBuffer connectString = new StringBuffer();
		connectString
		.append( String.format("jdbc:mysql://%s:%s/%s", dbHost, dbPort, dbName) )
		.append("?rewriteBatchedStatements=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;autoCommit=false");
		
		return connectString.toString();
	}
}

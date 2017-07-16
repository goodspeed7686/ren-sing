package com.sing.ren.dao.table;import java.util.List;import org.springframework.stereotype.Component;import com.sing.ren.dao.BaseDao;import com.sing.ren.pojo.BillLog;@Componentpublic class BillLogDAO extends BaseDao {	@Override	public String statementIdForInsert() throws Exception {		return nsForTableStatements("bill_log_insert");	}	@Override	public String statementIdForQuery() throws Exception {		return nsForTableStatements("bill_log_query");	}	@Override	public String statementIdForUpdate() throws Exception {		return nsForTableStatements("bill_log_update");	}	@Override	public String statementIdForUpsert() throws Exception {		return nsForTableStatements("bill_log_upsert");	}	@Override	public String statementIdForDelete() throws Exception {		return nsForTableStatements("bill_log_delete");	}	public void insertDB(BillLog pojo) throws Exception {		logger.debug("BillLogDAO insertBillLog");		insert(pojo);	}	@SuppressWarnings("unchecked")	public List<BillLog> queryDB(BillLog pojo) throws Exception {		logger.debug("BillLogDAO queryBillLog");		return (List<BillLog>) query(pojo);	}	public void updateDB(BillLog pojo) throws Exception{		logger.debug("BillLogDAO updateBillLog");		upsert(pojo);	}	public void deleteDB(BillLog pojo) throws Exception {		logger.debug("BillLogDAO deleteBillLog");		delete(pojo);	}	public void upsertDB(BillLog pojo) throws Exception {		logger.debug("BillLogDAO upsertBillLog");		upsert(pojo);	}}
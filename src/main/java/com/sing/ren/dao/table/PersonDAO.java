package com.sing.ren.dao.table;import java.util.List;import java.util.Map;import org.apache.commons.collections.MapUtils;import org.springframework.stereotype.Component;import com.sing.ren.dao.BaseDao;import com.sing.ren.pojo.Person;@Componentpublic class PersonDAO extends BaseDao {	@SuppressWarnings("deprecation")	public List<Person> query(Person dto) throws Exception {		logger.debug("PersonDAO queryPerson");		return getSqlMapClientTemplate().queryForList(nsForTableStatements("person_query"), dto);	}	@SuppressWarnings("deprecation")	public void insert(Person dto) throws Exception {		logger.debug("PersonDAO insertPerson");		getSqlMapClientTemplate().insert( nsForTableStatements("person_insert"), dto);	}	@SuppressWarnings("deprecation")	public void update(Person dto) throws Exception {		logger.debug("PersonDAO updatePerson");		getSqlMapClientTemplate().queryForList(nsForTableStatements("person_update"), dto);	}	@SuppressWarnings("deprecation")	public void delete(Person dto) throws Exception {		logger.debug("PersonDAO PersonDetail");		getSqlMapClientTemplate().queryForList(nsForTableStatements("person_delete"), dto);	}	public Person mapToObject(Map<String, Object> map) throws Exception {		Person obj = new Person();		obj.setPersonId( MapUtils.getString(map, "person_id", "") );		obj.setVirtualAccount( MapUtils.getString(map, "virtual_account", "") );		obj.setName( MapUtils.getString(map, "name", "") );		obj.setNickname( MapUtils.getString(map, "nickname", "") );		obj.setBalance( MapUtils.getString(map, "balance", "") );		obj.setIdNumber( MapUtils.getString(map, "id_number", "") );		obj.setSex( MapUtils.getString(map, "sex", "") );		obj.setBirthday( MapUtils.getString(map, "birthday", "") );		obj.setLocalCalls( MapUtils.getString(map, "local_calls", "") );		obj.setPhone( MapUtils.getString(map, "phone", "") );		obj.setEmail( MapUtils.getString(map, "email", "") );		obj.setSkype( MapUtils.getString(map, "skype", "") );		obj.setCareer( MapUtils.getString(map, "career", "") );		obj.setRecipient( MapUtils.getString(map, "recipient", "") );		obj.setRecAdd( MapUtils.getString(map, "rec_add", "") );		obj.setRecNum( MapUtils.getString(map, "rec_num", "") );		obj.setCompanyTaxId( MapUtils.getString(map, "company_tax_id", "") );		obj.setUpdater( MapUtils.getString(map, "updater", "") );		obj.setUpdateTime( MapUtils.getString(map, "update_time", "") );	return obj;	}}
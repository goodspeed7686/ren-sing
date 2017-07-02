package com.sing.ren.dao.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import com.sing.ren.dao.BaseDao;
import com.sing.ren.pojo.Person;

@Component
public class PersonDAO extends BaseDao {
	
	@SuppressWarnings("deprecation")
	public void insertDB(Person dto) throws Exception {
		logger.debug("PersonDAO insertPerson");
		getSqlMapClientTemplate().insert( nsForTableStatements("person_insert"), dto);
	}

	public List<Person> getAll(Map<String, Object> map) throws Exception {
		logger.debug("PersonDAO getAll");
		List<Map<String, Object>> resultMapList = query(map, nsForTableStatements("person_query"));
		
		List<Person> result = new ArrayList<Person>();
		
		for (Map<String, Object> resultMap : resultMapList){
			result.add( mapToObject(resultMap) );
		}
		
		return result;
	}

	public void deleteDB(Map<String, Object> map) throws Exception {
		logger.debug("PersonDAO deletePerson");
		delete(map, nsForTableStatements("person_delete"));
	}
	
	public Person mapToObject(Map<String, Object> map) throws Exception {
		Person obj = new Person();
		
		obj.setPersonId( MapUtils.getString(map, "person_id", "") );
		obj.setVirtualAccount( MapUtils.getString(map, "virtual_account", ""));
		obj.setName( MapUtils.getString(map, "name", "") );
		obj.setNickname( MapUtils.getString(map, "nickname", "") );
		obj.setBalance( MapUtils.getString(map, "balance", "") );
		obj.setIdNumber( MapUtils.getString(map, "id_number", ""));
		obj.setSex( MapUtils.getString(map, "sex", "") );
		obj.setBirthday( MapUtils.getString(map, "birthday", "") );
		obj.setLocalCalls( MapUtils.getString(map, "local_calls", "") );
		obj.setPhone( MapUtils.getString(map, "phone", ""));
		obj.setEmail( MapUtils.getString(map, "email", "") );
		obj.setSkype( MapUtils.getString(map, "skype", "") );
		obj.setCareer( MapUtils.getString(map, "career", "") );
		obj.setRecipient( MapUtils.getString(map, "recipient", ""));
		obj.setRecAdd( MapUtils.getString(map, "rec_add", "") );
		obj.setRecNum( MapUtils.getString(map, "rec_num", "") );
		obj.setCompanyTaxId( MapUtils.getString(map, "company_tax_id", "") );
		obj.setUpdater( MapUtils.getString(map, "updater", "") );
		obj.setUpdateTime( MapUtils.getString(map, "update_time", ""));
		
		return obj;
	}

}

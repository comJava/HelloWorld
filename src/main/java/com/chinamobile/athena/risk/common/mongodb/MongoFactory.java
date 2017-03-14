/**
 * 
 */
package com.chinamobile.athena.risk.common.mongodb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * @author lihaiyang
 *
 */
public class MongoFactory implements FactoryBean<MongoTemplate>,InitializingBean {
	
	private MongoTemplate mongoTemplate;
	
	private String hosts;
	private String databaseName;
	
	private Mongo mongo;
	private String userName;
	private String password;

	
	@Override
	public MongoTemplate getObject() throws Exception {
		return mongoTemplate;
	}

	@Override
	public Class<?> getObjectType() {
		return MongoTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void afterPropertiesSet() throws Exception {
		if(hosts == null || mongoTemplate!= null){
			return;
		}
		String[] hostArray = hosts.split(",");
		List<ServerAddress>addressList = new ArrayList<ServerAddress>();
		for(String host: hostArray){
			ServerAddress address = new ServerAddress(host.split(":")[0], Integer.parseInt(host.split(":")[1]));
			addressList.add(address);
		}
		if(StringUtils.isEmpty(userName) && StringUtils.isEmpty(password)){
			mongo = new MongoClient(addressList);
		}else{
			mongo=	new MongoClient(addressList,Collections.singletonList(MongoCredential.createCredential(userName, databaseName, password.toCharArray())));
		}
		SimpleMongoDbFactory simpleMongoDbFactory =  new SimpleMongoDbFactory(mongo, databaseName);
		//给构造函数传递null值是为了去掉_class属性 
		DefaultMongoTypeMapper  mapper = new DefaultMongoTypeMapper(null);
		MappingMongoConverter converter = new MappingMongoConverter(simpleMongoDbFactory, new MongoMappingContext());
		converter.setTypeMapper(mapper);
		mongoTemplate = new MongoTemplate(simpleMongoDbFactory,converter);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getHosts() {
		return hosts;
	}

	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}


}

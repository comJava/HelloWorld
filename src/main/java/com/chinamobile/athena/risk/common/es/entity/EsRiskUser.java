package com.chinamobile.athena.risk.common.es.entity;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "risk_user_index", type = "risk_user_type", indexStoreType = "mmapfs", shards = 2, replicas = 0, refreshInterval = "-1")
public class EsRiskUser extends EsBaseEntity  implements Serializable {

	private static final long serialVersionUID = 3576633418920030628L;

	@Id()
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String username;

	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	@JsonProperty("phone_num")
	private String phoneNum;

	@Field(type = FieldType.Object, index = FieldIndex.not_analyzed)
	@JsonProperty("equipment")
	private Map<String, Object> equipment;

	@Field(type = FieldType.Object, index = FieldIndex.not_analyzed)
	@JsonProperty("location")
	private Map<String, Object> location;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Map<String, Object> getEquipment() {
		return equipment;
	}

	public void setEquipment(Map<String, Object> equipment) {
		this.equipment = equipment;
	}

	public Map<String, Object> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}

	@Override
	public String getEsId() {
		return username;
	}


}

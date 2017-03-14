package com.chinamobile.athena.risk.common.es.entity;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "risk_equip_index", type = "risk_equip_type", indexStoreType = "mmapfs", shards = 2, replicas = 0, refreshInterval = "-1")
public class EsRiskEquip extends EsBaseEntity implements Serializable {

    private static final long   serialVersionUID = -6235261987174060119L;

    public static final String  ANDROID          = "A";
    public static final String  IOS              = "I";
    public static final String  SPILT_STRING     = "_";

    /**
     * A表示Android设备,I表示IOS设备
     */
    @Id()
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String esId;
    
    /**
     * A表示Android设备,I表示IOS设备
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String              androidOrIos;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("aImei")
    private String              imei;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("aDevMac")
    private String              devMac;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("aSimNum")
    private String              simNum;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("iIdfa")
    private String              idfa;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("aCpuid")
    private String              cpuid;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("iOpenUdid")
    private String              openUdid;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("iPushToken")
    private String              pushToken;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    @JsonProperty("iIdfv")
    private String              idfv;

    @Field(type = FieldType.Object, index = FieldIndex.not_analyzed)
    private Map<String, Object> equipment;

    public Map<String, Object> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<String, Object> equipment) {
        this.equipment = equipment;
    }

    public String getAndroidOrIos() {
        return androidOrIos;
    }

    public void setAndroidOrIos(String androidOrIos) {
        this.androidOrIos = androidOrIos;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDevMac() {
        return devMac;
    }

    public void setDevMac(String devMac) {
        this.devMac = devMac;
    }

    public String getSimNum() {
        return simNum;
    }

    public void setSimNum(String simNum) {
        this.simNum = simNum;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getCpuid() {
        return cpuid;
    }

    public void setCpuid(String cpuid) {
        this.cpuid = cpuid;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }
    
    public String getEsId() {
		if (ANDROID.equals(androidOrIos)) {
            return ANDROID + SPILT_STRING + cpuid + SPILT_STRING + imei + SPILT_STRING + devMac
                    + SPILT_STRING + simNum;
        } else {
            return IOS + SPILT_STRING + idfa + SPILT_STRING + openUdid + SPILT_STRING + pushToken
                    + SPILT_STRING + idfv;
        }
	}
}

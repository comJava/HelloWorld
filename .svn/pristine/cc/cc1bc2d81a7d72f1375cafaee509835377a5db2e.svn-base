package com.chinamobile.athena.risk.common.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.elasticsearch.annotations.Document;

import com.chinamobile.athena.risk.common.es.entity.EsBaseEntity;


/** 
 * @ClassName UserOperateEsEntity
 * @Description TODO
 * @author lihaiyang
 * @date 2015年10月13日 - 下午1:45:14
 * @version 1.0
 */
@Document(indexName = "risk_operate_index", type = "risk_operate_type", indexStoreType = "mmapfs", shards = 2, replicas = 0, refreshInterval = "-1")
public class UserOperateEsEntity extends EsBaseEntity implements Serializable{
    	
    private static final long serialVersionUID = -7036228479641845815L;
    
    private String username;
    private List<RiskOperate>errorCount;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<RiskOperate> getErrorCount() {
        return errorCount;
    }
    public void setErrorCount(List<RiskOperate> errorCount) {
        this.errorCount = errorCount;
    }
    
    
    @Override
    public String getEsId() {
        return username;
    }
    

}

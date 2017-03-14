package com.chinamobile.athena.risk.common.hbase.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import com.chinamobile.athena.risk.common.hbase.HBaseTemplate;
import com.chinamobile.athena.risk.common.hbase.HTableTemplate;

/**
 * ClassName:SimpleHTableTemplate <br/>
 * Date: 2015年5月3日 下午9:17:44 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class SimpleHTableTemplate implements HTableTemplate {

    private String        tableName;
    private String        columnFamily;
    private int           maxVersions;

    private HBaseTemplate hbaseTemplate;

    private SimpleHTableTemplate() {
        super();
        this.maxVersions = 1;
    }

   
    public String getTableName() {
        return this.tableName;
    }

   
    public int getMaxVersions() {
        return this.maxVersions;
    }


    public String getColumnFamily() {
        return this.columnFamily;
    }

    public HBaseTemplate getHbaseTemplate() {
        return hbaseTemplate;
    }

    public void setHbaseTemplate(HBaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnFamily(String columnFamily) {
        this.columnFamily = columnFamily;
    }

    public void setMaxVersions(int maxVersions) {
        this.maxVersions = maxVersions;
    }

    
    public void create() throws IOException {
        if (!hbaseTemplate.isTableExists(tableName.trim())) {
            hbaseTemplate.createTable(this.tableName, columnFamily, maxVersions);
        }
    }

 
    public void deleteRow(String rowKey) throws IOException {
        this.hbaseTemplate.deleteRow(this.tableName, rowKey);
    }

 
    public void putData(String key, Map<String, String> values) throws IOException {
        this.hbaseTemplate.putData(this.tableName, key, this.columnFamily, values);
    }


    public void putData(String key, String columnFamily, Map<String, String> values) throws IOException {
        this.hbaseTemplate.putData(this.tableName, key, columnFamily, values);
    }


    public Map<String, String> getData(String rowKey) throws IOException {
        return this.hbaseTemplate.getData(this.tableName, rowKey, this.columnFamily);
    }


    public Map<String, String> getData(String rowKey, String columnFamily) throws IOException {
        return this.hbaseTemplate.getData(this.tableName, rowKey, columnFamily);
    }
    
    public String getData(String rowKey, String columnFamily,String qualifier) throws IOException {
    	return this.hbaseTemplate.getData(this.tableName, rowKey, columnFamily,qualifier);
    }

   
    public void putData(List<Put> putList) throws IOException {
        this.hbaseTemplate.putData(this.tableName, putList);
    }


    public boolean isTableExists(String name) throws IOException {
        // TODO Auto-generated method stub
        return this.hbaseTemplate.isTableExists(this.tableName);
    }


    public boolean isColumnFamilyExists(String columnFamily) throws IOException {
        // TODO Auto-generated method stub
        return this.hbaseTemplate.isColumnFamilyExists(this.tableName, columnFamily);
    }


    public void addColumnFamilyNoDisableTable(String columnFamily) throws IOException {
        this.hbaseTemplate.addColumnFamilyNoDisableTable(this.tableName, columnFamily, this.maxVersions);
    }

    public void putDataMapList(List<String> keyList, String columnFamily, List<Map<String, String>> valuesList)
            throws IOException {
        this.hbaseTemplate.putDataMapList(this.tableName, keyList, columnFamily, valuesList);
    }
    

	public ResultScanner getScan(Scan s) throws IOException {
		return this.hbaseTemplate.getScan(tableName,s);
	}
  	
    public void putBatchDataList(List<String> keyList,List<String> columnFamilyList, List<Map<String, String>> valueList)throws Exception {
        this.hbaseTemplate.putBatchDataList(this.tableName, keyList, columnFamilyList, valueList);
    }
    
    public void putBatch(List<String>rowKeys,Map<String, Map<String, Map<String, String>>>map){
        
    }


	public void putBatchDatas(List<String> rowkeyList,
			Map<String, Map<String, Map<String, String>>> valueMap)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

    
    
}

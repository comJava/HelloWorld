package com.chinamobile.athena.risk.common.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 * ClassName:HBaseTemplate <br/>
 * Date: 2015年5月3日 下午9:18:05 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public interface HBaseTemplate {
   
    public void putBatchDataList(String tableName,List<String>keyList,List<String>columnFamilyList,List<Map<String, String>>valueList)throws Exception;
    
    public boolean isTableExists(String name) throws IOException;

    public void dropTable(String name) throws IOException;

    public void deleteRow(String name, String rowKey) throws IOException;

    public void createTable(String name, String columnFamily, int versions) throws IOException;

    public void createTable(String name, String columnFamily) throws IOException;

    public void putData(String tableName, String key, String columnFamily, Map<String, String> values)
            throws IOException;

    public Map<String, String> getData(String tableName, String rowKey, String columnFamily) throws IOException;
    
    public String getData(String tableName, String rowKey, String columnFamily,String qualifier) throws IOException;

    public void addColumnFamilyNoDisableTable(String tableName, String columnFamily, int versions) throws IOException;

    public void addColumnFamily(String tableName, String columnFamily, int versions) throws IOException;

    public boolean isColumnFamilyExists(String tableName, String columnFamily) throws IOException;

    public void putData(String tableName, List<Put> putList) throws IOException;

    /**
     * Description：
     * 
     * @param tableName hbase表名
     * @param keyList 主键列表
     * @param columnFamily 添加列族
     * @param valuesList 批量数据列表
     * @throws IOException
     * @return void
     * @author chuanke_chen@mail.asdc.com.cn
     */

    public void putDataMapList(String tableName, List<String> keyList, String columnFamily,
                               List<Map<String, String>> valuesList) throws IOException;

	public ResultScanner getScan(String tableName, Scan s) throws IOException;

	public void putBatchDatas(String tableName,List<String> rowkeyList,Map<String, Map<String, Map<String, String>>> valueMap)throws IOException;

}

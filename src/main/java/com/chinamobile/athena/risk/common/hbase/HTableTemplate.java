package com.chinamobile.athena.risk.common.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 * ClassName:HTableTemplate <br/>
 * Date: 2015年5月3日 下午9:18:09 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public interface HTableTemplate {

    public String getTableName();

    public int getMaxVersions();

    public String getColumnFamily();

    public void create() throws IOException;

    public void deleteRow(String rowKey) throws IOException;

    public void putData(String key, Map<String, String> values) throws IOException;

    public void putData(String key, String columnFamily, Map<String, String> values) throws IOException;

    public Map<String, String> getData(String rowKey) throws IOException;

    public Map<String, String> getData(String rowKey, String columnFamily) throws IOException;
   
    public String getData(String rowKey, String columnFamily,String qualifier) throws IOException;
    
    @Deprecated
    public void putData(List<Put> putList) throws IOException;

    public boolean isTableExists(String name) throws IOException;

    public boolean isColumnFamilyExists(String columnFamily) throws IOException;

    public void addColumnFamilyNoDisableTable(String columnFamily) throws IOException;
    
    public void putBatchDatas(List<String>rowkeyList,Map<String, Map<String, Map<String, String>>>valueMap)throws IOException;
    /**
     * Description：
     * 
     * @param keyList 主键列表
     * @param columnFamily 添加列族
     * @param valuesList 批量数据列表
     * @throws IOException
     * @return void
     * @author chuanke_chen@mail.asdc.com.cn
     */

    public void putDataMapList(List<String> keyList, String columnFamily, List<Map<String, String>> valuesList)
            throws IOException;

	ResultScanner getScan(Scan s) throws IOException;

}

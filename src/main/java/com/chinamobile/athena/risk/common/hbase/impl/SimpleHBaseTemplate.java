package com.chinamobile.athena.risk.common.hbase.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.hbase.HBaseManager;
import com.chinamobile.athena.risk.common.hbase.HBaseTemplate;
import com.chinamobile.athena.risk.common.logging.LogFactory;

/**
 * ClassName:SimpleHBaseTemplate <br/>
 * Date: 2015年5月3日 下午9:17:38 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class SimpleHBaseTemplate implements HBaseTemplate {

    private final Logger       logger = LogFactory.getLogger(LogCode.RISK_MAIN);
    private final HBaseManager hbaseManager;

    public SimpleHBaseTemplate(HBaseManager hbaseManager) {
        super();
        this.hbaseManager = hbaseManager;
    }


    public boolean isTableExists(String name) throws IOException {
        HBaseAdmin admin = hbaseManager.getHBaseAdmin();
        return admin.tableExists(name);
    }


    public void dropTable(String name) throws IOException {
        if (isTableExists(name)) {
            HBaseAdmin admin = hbaseManager.getHBaseAdmin();
            admin.disableTable(name);
            admin.deleteTable(name);
        } else {
            logger.warn("table : " + name + " does not exists");
        }
    }


    public void deleteRow(String tableName, String rowKey) throws IOException {
        HTableInterface table = null;
        try {
            table = hbaseManager.getHTable(tableName);
            List<Delete> list = new ArrayList<Delete>(1);
            Delete delete = new Delete(rowKey.getBytes());
            list.add(delete);
            table.delete(list);
        } finally {
            if (table != null) {
                table.close();
            }
        }

    }


    public void createTable(String tableName, String columnFamily, int versions) throws IOException {
        HBaseAdmin hBaseAdmin = hbaseManager.getHBaseAdmin();
        final TableName tbName = TableName.valueOf(tableName);
        HTableDescriptor tableDescriptor = new HTableDescriptor(tbName);
        HColumnDescriptor cf = new HColumnDescriptor(columnFamily);
        cf.setMaxVersions(versions);
        cf.setCompressionType(Compression.Algorithm.LZO);
        tableDescriptor.addFamily(cf);
        hBaseAdmin.createTable(tableDescriptor);
    }

    public void createTable(String tableName, String columnFamily) throws IOException {
        this.createTable(tableName, columnFamily, 1);
    }

    public Map<String, String> getData(String tableName, String rowKey, String columnFamily) throws IOException {
        HTableInterface table = null;
        try {
            table = this.hbaseManager.getHTable(tableName);
            Get get = this.buildGet(tableName, rowKey, columnFamily, 1);
            Result result = table.get(get);
            return this.extractResult(result);
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }
    
    public String getData(String tableName, String rowKey, String columnFamily,String qualifier) throws IOException {
    	HTableInterface table = null;
    	try {
    		table = this.hbaseManager.getHTable(tableName);
    		Get get = new Get(Bytes.toBytes("rowKey"));
    		get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
    		Result result = table.get(get);
    		byte[] val = result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
    		return Bytes.toString(val);
    	} finally {
    		if (table != null) {
    			table.close();
    		}
    	}
    }

    protected Map<String, String> extractResult(Result result) {
        Map<String, String> map = new HashMap<String, String>();
        
        for (Cell cell : result.listCells()) {
            map.put(new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        }
        return map;
    }

    protected Get buildGet(String tableName, String rowKey, String columnFamily, int maxVersions) throws IOException {
        Get get = new Get(rowKey.getBytes());
        get.setMaxVersions(maxVersions);
        get.addFamily(columnFamily.getBytes());
        return get;
    }


    public void addColumnFamilyNoDisableTable(String tableName, String columnFamily, int versions) throws IOException {
        HBaseAdmin hBaseAdmin = hbaseManager.getHBaseAdmin();
        HTableDescriptor table = new HTableDescriptor(tableName);
        if (!hBaseAdmin.tableExists(tableName)) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
            hColumnDescriptor.setMaxVersions(versions);
            table.addFamily(hColumnDescriptor);
            hBaseAdmin.createTable(table);
        }
        table = hBaseAdmin.getTableDescriptor(tableName.getBytes());
        if (!table.hasFamily(Bytes.toBytes(columnFamily))) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
            hColumnDescriptor.setMaxVersions(versions);
            table.addFamily(hColumnDescriptor);
            hBaseAdmin.modifyTable(tableName, table);
        }
    }

    public void addColumnFamily(String tableName, String columnFamily, int versions) throws IOException {
        HBaseAdmin hBaseAdmin = hbaseManager.getHBaseAdmin();
        HTableDescriptor table = hBaseAdmin.getTableDescriptor(tableName.getBytes());
        if (!table.hasFamily(Bytes.toBytes(columnFamily))) {
            hBaseAdmin.disableTable(tableName);
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
            hColumnDescriptor.setMaxVersions(versions);
            table.addFamily(hColumnDescriptor);
            hBaseAdmin.modifyTable(tableName, table);
            hBaseAdmin.enableTable(tableName);
        }
    }


    public boolean isColumnFamilyExists(String tableName, String columnFamily) throws IOException {
        // TODO Auto-generated method stub
        HBaseAdmin hBaseAdmin = hbaseManager.getHBaseAdmin();
        HTableDescriptor table = hBaseAdmin.getTableDescriptor(tableName.getBytes());
        return table.hasFamily(Bytes.toBytes(columnFamily));
    }


    public void putData(String tableName, List<Put> putList) throws IOException {
        if (StringUtils.isBlank(tableName)) {
            return;
        }
        HTableInterface table = null;
        try {
            table = this.hbaseManager.getHTable(tableName);
            table.put(putList);
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }

    
   
    public void putData(String tableName, String key, String columnFamily, Map<String, String> values)
            throws IOException {
        if (StringUtils.isBlank(key)) {
            return;
        }
        HTableInterface table = null;

        try {
            table = this.hbaseManager.getHTable(tableName);
            Put put = new Put(key.getBytes());
            byte[] cf = columnFamily.getBytes();
            for (Entry<String, String> entry : values.entrySet()) {
                if (StringUtils.isBlank(entry.getKey())) {
                    continue;
                }
                byte[] kbytes = entry.getKey().getBytes();
                byte[] vbytes = Bytes.toBytes((entry.getValue() == null ? "null" : entry.getValue()));
                put.add(cf, kbytes, vbytes);
            }
            table.put(put);
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }
    /**
     * 往HBase中批量插入数据，其中keyList和columnFamilyList以及valueList大小必须相同
     * 因为rowKey和columnFamily有可能存在一对多的关系和一对一的关系（不确定）
     */
    public void putBatchDataList(String tableName,List<String>keyList,List<String>columnFamilyList,
                                 List<Map<String, String>>valueList)throws Exception{
        if(CollectionUtils.isEmpty(keyList)){
            return;
        }
        logger.info("invoke SimpleHBaseTemplate putBatchDataList method");
        List<Put>putList = new ArrayList<Put>();
        HTableInterface table = null;
        try{
            for(int i =0 ; i < keyList.size(); i++){
                Put put = new Put(Bytes.toBytes(keyList.get(i)));
                String columnFamily = columnFamilyList.get(i);
                for(Entry<String, String>entry:valueList.get(i).entrySet()){
                    if(StringUtils.isEmpty(entry.getKey().trim())){
                        continue;
                    }
                    byte[] keyBytes   = Bytes.toBytes(entry.getKey());
                    byte[] valueBytes = Bytes.toBytes(entry.getValue());
                    put.add(Bytes.toBytes(columnFamily),keyBytes,valueBytes);
                }
                putList.add(put);
            }
            table = this.hbaseManager.getHTable(tableName);
            table.put(putList);
        }finally{
            if(null != table){
                table.close();
            }
        }
    }
    
    public void putDataMapList(String tableName, List<String> keyList, String columnFamily,
                               List<Map<String, String>> valuesList) throws IOException {
        if (CollectionUtils.isEmpty(keyList)) {
            return;
        }
        logger.info("invoke SimpleHBaseTemplate putDataMapList method");
        List<Put> putList = new ArrayList<Put>();
        HTableInterface table = null;

        try {
            for (int i = 0; i < valuesList.size(); i++) {
                // String ipMd5Str = DigestUtils.md5Hex(valuesList.get(i).get("ip"));
                Put put = new Put(keyList.get(i).getBytes());
                for (Entry<String, String> entry : valuesList.get(i).entrySet()) {
                    if (StringUtils.isBlank(entry.getKey())) {
                        continue;
                    }
                    byte[] kbytes = entry.getKey().getBytes();
                    byte[] vbytes = Bytes.toBytes((entry.getValue() == null ? "null" : entry.getValue()));
                    put.add(columnFamily.getBytes(), kbytes, vbytes);
                }
                putList.add(put);
            }

            table = this.hbaseManager.getHTable(tableName);
            table.put(putList);
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }

	public ResultScanner getScan(String tableName,Scan s) throws IOException {
		HTableInterface table = null;
	    try {
	    	table = this.hbaseManager.getHTable(tableName);
	    	 return table.getScanner(s);
	    }finally {
	        if (table != null) {
	            table.close();
	        }
        }
		
	}


	public void putBatchDatas(String tableName, List<String> rowkeyList,
			Map<String, Map<String, Map<String, String>>> valueMap)
			throws IOException {
		// TODO Auto-generated method stub
		
	}


  
    	
   
}

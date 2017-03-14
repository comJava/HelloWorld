package com.chinamobile.athena.risk.common.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.exceptions.HBaseException;

/**
 * 
 * ClassName:HBaseManager <br/>
 * Date: 2015年5月3日 下午9:17:57 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public interface HBaseManager {

  public HBaseAdmin getHBaseAdmin() throws IOException;

  public HTableInterface getHTable(String tableName) throws IOException;

  public void closeConn() throws HBaseException;
  
}

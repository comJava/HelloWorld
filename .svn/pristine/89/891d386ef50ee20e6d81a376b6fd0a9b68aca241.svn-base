package com.chinamobile.athena.risk.common.hbase.impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.exceptions.HBaseException;

import com.chinamobile.athena.risk.common.hbase.HBaseCfg;
import com.chinamobile.athena.risk.common.hbase.HBaseManager;

/**
 * 
 * @author wangbing
 *
 */
public class SimpleHBaseManager implements HBaseManager {
	private Configuration configuration;
	private HBaseAdmin hBaseAdmin;
	private int maxSize;
	private HConnection connection;

	public SimpleHBaseManager(HBaseCfg configure) {
		super();
		maxSize = 100;
		configuration = new Configuration();
		configuration.set("hbase.zookeeper.quorum",
				configure.getZookeeperAddress());
		configuration.set("hbase.zookeeper.property.clientPort",
				configure.getZookeeperPort());
		configuration.set("hbase.master", configure.getHbaseAddress());
		configuration.setInt("hbase.client.retries.number",
				configure.getMaxRetries());
		configuration = HBaseConfiguration.create(configuration);
	}

	public void init() throws HBaseException {
		this.hBaseAdmin = this.createHbaseAdmin();
		this.connection = this.createConnection();
	}

	protected HBaseAdmin createHbaseAdmin() throws HBaseException {
		try {
			return new HBaseAdmin(this.configuration);
		} catch (IOException e) {
			throw new HBaseException(e);
		}
	}

	protected HConnection createConnection() throws HBaseException {
		try {
			      return HConnectionManager.createConnection(configuration);
		} catch (IOException e) {
			throw new HBaseException(e);
		}
	}

	public HBaseAdmin getHBaseAdmin() {
		return this.hBaseAdmin;
	}

	public HTableInterface getHTable(String tableName) throws IOException {
		return connection.getTable(tableName);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void closeConn() throws HBaseException {
		if (connection != null && !connection.isClosed()) {
			try {
				connection.close();
			} catch (Exception e) {
				throw new HBaseException(e);
			}
		}
	}

}

package com.chinamobile.athena.risk.common.zookeeper.watcher;

import org.apache.zookeeper.ZooKeeper;

import com.chinamobile.athena.risk.common.zookeeper.EventWatcher;

/**
 * 
     * ClassName:AbstractEventWatcher <br/> 
     * Date:     2015年5月3日 下午9:43:04 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract class AbstractEventWatcher implements EventWatcher {

	protected ZooKeeper zooKeeper;
	private final String name;
	
	public AbstractEventWatcher(String name) {
		this.name = name;
	}
	
	public void setZooKeeper(ZooKeeper zk) {
		this.zooKeeper = zk;
	}

	public String getName() {
		return name;
	}

	public ZooKeeper getZooKeeper() {
		return zooKeeper;
	}

}

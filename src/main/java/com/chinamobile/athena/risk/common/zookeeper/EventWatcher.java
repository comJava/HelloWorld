package com.chinamobile.athena.risk.common.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
     * ClassName:EventWatcher <br/> 
     * Date:     2015年5月3日 下午9:43:30 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public interface EventWatcher {
	
	public void processEvent(WatchedEvent event);
	
	public void setZooKeeper(ZooKeeper zk);
	
	public boolean init();
}

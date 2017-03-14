package com.chinamobile.athena.risk.common.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;

/**
 * 
 * ClassName:ZooKeeperClient <br/>
 * Date: 2015年5月3日 下午9:43:45 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class ZooKeeperClient implements Watcher {
 private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);

  private final ZooKeeper zk;
  private final List<EventWatcher> watchers;

  public ZooKeeperClient(String host) throws IOException {
    this(host, 60000);
  }

  public ZooKeeperClient(String host, int sessionTimeOut) throws IOException {
    zk = new ZooKeeper(host, sessionTimeOut, this);
    watchers = new CopyOnWriteArrayList<EventWatcher>();
  }


  public List<EventWatcher> getWatchers() {
    return watchers;
  }


  public void registerWatcher(EventWatcher watcher) {
    watcher.setZooKeeper(this.zk);
    this.watchers.add(watcher);
  }

  public void process(WatchedEvent event) {
    for (EventWatcher watcher : watchers) {
      watcher.processEvent(event);
    }
  }


  /**
   * 释放锁
   * 
   * @throws InterruptedException
   * @throws KeeperException
   */
  public void releaseLock(String lockPath) throws InterruptedException, KeeperException {
    List<String> list = zk.getChildren(lockPath, false);
    if (list == null || list.isEmpty()) {
      zk.delete(lockPath, -1);
      logger.info("delete znode : " + lockPath);
    }
  }

  public void createLockPath(String lockPrePath) throws InterruptedException, KeeperException {
    try {
      Stat stat = zk.exists(lockPrePath, false);
      if (stat == null) {
        logger.info("first instance create lockPrePath node : " + lockPrePath);
        String node =
            zk.create(lockPrePath, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info("crate lockPrePath  : " + node);
      } else {
        logger.info("node : " + lockPrePath + " had been created");
      }

    } catch (Exception e) {
      logger.error("create root path error");
      throw new ZooKeeperException(e.getMessage(), e);
    }
  }

  public boolean exists(String path) throws InterruptedException, KeeperException {
    Stat stat = zk.exists(path, false);
    if (stat == null) {
      return false;
    }
    return true;
  }



}

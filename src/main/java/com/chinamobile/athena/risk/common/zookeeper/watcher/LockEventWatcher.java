package com.chinamobile.athena.risk.common.zookeeper.watcher;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.collections.CollectionUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;
import com.chinamobile.athena.risk.common.zookeeper.Locker;
import com.chinamobile.athena.risk.common.zookeeper.ZooKeeperException;

/**
 * ClassName:LockEventWatcher <br/>
 * Date: 2015年5月3日 下午9:43:11 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class LockEventWatcher extends AbstractEventWatcher implements Locker {

    public LockEventWatcher(String name) {
        super(name);
    }

    private static final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);

    private String              path   = "/" + this.getName();

    private CountDownLatch      latch  = new CountDownLatch(1);

    private String              childPath;

    
    public boolean init() {
        leadElection();
        return true;
    }

    private void leadElection() {

        this.createRootPath();
        childPath = path + "/";
        try {
            childPath = this.zooKeeper.create(childPath, new byte[0], Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.info("creaate ephemeral sequential node with path : " + childPath);
            this.detectLeader();
        } catch (Exception e) {
            logger.error("create child path error");
            throw new ZooKeeperException(e.getMessage(), e);
        }
    }

    private void detectLeader() {
        try {
            String leader = this.getLeader();
            if (this.childPath.equals(leader)) {
                logger.info("get lock from zookeeper with path : " + leader);
                this.latch.countDown();
            }

            logger.info("leader is path : " + leader);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    public String getLeader() throws KeeperException, InterruptedException {
        String leader = null;
        List<String> children = this.zooKeeper.getChildren(path, true);
        if (CollectionUtils.isNotEmpty(children)) {
            Collections.sort(children);
            leader = this.path + "/" + children.get(0);
        }
        return leader;
    }

    public void unLock() {
        this.latch.countDown();
    }

   
    public void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void createRootPath() {
        try {
            Stat stat = this.zooKeeper.exists(path, false);
            if (stat == null) {
                logger.info("first instance create node : " + path);
                String node = this.zooKeeper.create(path, new byte[0], Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
                logger.info("crate root path : " + node);
            } else {
                logger.info("node : " + path + " had been created");
            }

        } catch (Exception e) {
            logger.error("create root path error");
            throw new ZooKeeperException(e.getMessage(), e);
        }
    }


    public void processEvent(WatchedEvent event) {
        switch (event.getType()) {

            case NodeChildrenChanged:
                logger.info("NodeChildrenChanged | ZNode: " + event.getPath());
                if (event.getPath().startsWith(path)) {
                    logger.info("Leader may lost, newLeaderElection started.");
                    detectLeader();
                }
                break;

            case NodeCreated:
                logger.info("NodeCreated | ZNode: " + event.getPath());
                break;

            case NodeDataChanged:
                logger.info("CLIENT THREE :: NodeDataChanged | ZNode: " + event.getPath());
                break;

            case NodeDeleted:
                logger.info("NodeDeleted | ZNode: " + event.getPath());
                break;

            case None:
                logger.info("None | ZNode:  " + event.getPath() + " state : " + event.getState());
                break;
            default:
            	  break;
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

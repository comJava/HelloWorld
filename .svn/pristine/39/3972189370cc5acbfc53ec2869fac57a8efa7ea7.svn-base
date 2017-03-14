package com.chinamobile.athena.risk.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
     * ClassName:CacheUtil <br/> 
     * Date:     2015年5月3日 下午9:42:25 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public enum CacheUtil {
	INSTANCE;
	
	private long cachetime;
	private final ReadWriteLock readWriteLock;
	private final Map<String,CacheObject> respository;
	
	CacheUtil(){
		cachetime = 12;
		cachetime = cachetime * 60 * 60 * 1000;
		readWriteLock = new ReentrantReadWriteLock();
		respository = new HashMap<String,CacheObject>();
	}
	
	public Object getObject(String key){
		readWriteLock.readLock().lock();
		try{
			CacheObject cj = respository.get(key);
			if(cj != null && !cj.isExpired(cachetime)){
				return cj.getObject();
			}
		}finally{
			readWriteLock.readLock().unlock();
		}
		return null;
	}
	
	public Object getNoExpiredObject(String key){
		readWriteLock.readLock().lock();
		try{
			CacheObject cj = respository.get(key);
			if(cj != null){
				return cj.getObject();
			}
		}finally{
			readWriteLock.readLock().unlock();
		}
		return null;
	}
	
	public void insertObject(String key, Object object){
		readWriteLock.writeLock().lock();
		try{
			if(object != null && key != null){
				respository.put(key, new CacheObject(object));
			}
		}finally{
			readWriteLock.writeLock().unlock();
		}
	}
	
	public long getCachetime() {
		return cachetime;
	}

	public void setCachetime(long cachetime) {
		this.cachetime = cachetime * 60 * 60 * 1000;
	}

	public static CacheUtil getInstance(){
		return INSTANCE;
	}	
	
	static class CacheObject {
		private final Object object;
		private final long createTime;

		CacheObject(Object obj) {
			createTime = System.currentTimeMillis();
			this.object = obj;
		}
		
		public boolean isExpired(long timeout){
			return (System.currentTimeMillis() - this.createTime) >= timeout;
		}

		public Object getObject() {
			return object;
		}
	}
}




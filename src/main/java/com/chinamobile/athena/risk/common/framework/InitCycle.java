package com.chinamobile.athena.risk.common.framework;

/**
 * 
     * ClassName:InitCycle <br/> 
     * Date:     2015年5月3日 下午9:16:56 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public interface InitCycle {

	/**
	 * invoked when module start
	 * @return
	 */
	boolean init();
	
}

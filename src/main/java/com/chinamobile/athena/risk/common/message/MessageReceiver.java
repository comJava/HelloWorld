package com.chinamobile.athena.risk.common.message;

/**
 * 
     * ClassName:MessageReceiver <br/> 
     * Date:     2015年5月3日 下午9:19:49 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public interface MessageReceiver<T> {

	T receive();

}

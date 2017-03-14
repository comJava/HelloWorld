package com.chinamobile.athena.risk.common.actor;

import akka.actor.ActorSystem;

/**
 * 
     * ClassName:ActorSystemFactory <br/> 
     * Date:     2015年5月3日 下午9:16:01 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class ActorSystemFactory {
	
	public static ActorSystem createActorSystem(String name, String config) {
		 return ActorSystem.create(name);
	}
}

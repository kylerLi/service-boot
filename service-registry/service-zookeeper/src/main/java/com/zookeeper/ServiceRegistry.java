/**
 * 
 * @author leo
 *
 */
package com.zookeeper;

public interface ServiceRegistry {
	
	/**
	 * 
	 * @param serviceName
	 * @param address
	 */
	void registry(String serviceName,String address);

}

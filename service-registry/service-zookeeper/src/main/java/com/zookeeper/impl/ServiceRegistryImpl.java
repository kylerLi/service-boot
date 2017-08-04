package com.zookeeper.impl;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;

import com.zookeeper.ServiceRegistry;


public class ServiceRegistryImpl implements ServiceRegistry,Watcher {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ServiceRegistryImpl.class);
	
	private static final String REGISTRY_PATH = "/registry";
	private static final int TIME_OUT = 5000;
	
	private static CountDownLatch latch = new CountDownLatch(1);
	private ZooKeeper zk;
	public ServiceRegistryImpl(){}
	
	
	public ServiceRegistryImpl(String servers){
		try {
			zk = new ZooKeeper(servers, TIME_OUT, this);
			latch.await();
			logger.info("connect zookeep success...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void registry(String serviceName, String serviceAddress) {
		// TODO Auto-generated method stub
		try {
			//创建根节�?(持久节点)
			String registryPath = REGISTRY_PATH;
			if(zk.exists(REGISTRY_PATH, false) == null){
				zk.create(registryPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				logger.info("create registry persistent node:"+ registryPath +" success");
			}
			
			//创建服务节点(持久节点)
			String servicePath = REGISTRY_PATH+"/"+ serviceName;
			if(zk.exists(servicePath, false)== null){
				zk.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				logger.info("create service node:"+ servicePath +" success");
			}
			
			//创建地址节点
			String addressPath = servicePath + "/address-";
			String addressNode = zk.create(addressPath, serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, 
					CreateMode.EPHEMERAL_SEQUENTIAL);
			logger.info("create address node:{} ==> {} ",addressNode,serviceAddress);

		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

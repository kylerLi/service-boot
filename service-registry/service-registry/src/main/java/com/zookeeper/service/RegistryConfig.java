package com.zookeeper.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zookeeper.ServiceRegistry;
import com.zookeeper.impl.ServiceRegistryImpl;

@Configuration
@ConfigurationProperties(prefix="registry")
public class RegistryConfig {
	private String servers;
	
	public void setServers(String servers){
		this.servers = servers;
	}
	@Bean
	public ServiceRegistry serviceRegistry(){
		return new ServiceRegistryImpl(servers);
	}

}

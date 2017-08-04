package com.zookeeper.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages="com.zookeeper")
public class RegistryApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}
	
	@RequestMapping(name="sayHelloService", method=RequestMethod.GET,path="/sayHello")
	public String sayHello(){
		return "hhaa";
		
	}

}

package com.leo.microservice.rest;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leo.microservice.service.DockerImageService;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@ImportResource({"classpath*:applicationContext.xml"})
public class HelloApplication {
	@Autowired
	private  DockerImageService imageService;
	
	public static void main(String args[]){
		SpringApplication.run(HelloApplication.class, args);
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"applicationContext.xml");
//		imageService = (DockerImageServiceImpl) context.getBean("dockerImageService");
		
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/sayHello")
	public String sayHello(){
		return "hhaa";
		
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/getDockerImages")
	public String getDockerImages(){
		return imageService.getDockerImages();
		
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/getDockerImages")
	public String getDockerImages(@PathParam("params") String params){
		return imageService.getDockerImages()+ params;
		
	}
	
	@PostConstruct
	public void init(){
		imageService.init();
	}
	
}

package com.leo.microservice.service.impl;

import org.springframework.stereotype.Repository;

import com.leo.microservice.service.DockerImageService;

@Repository
public class DockerImageServiceImpl implements DockerImageService{

	public String getDockerImages() {
		// TODO Auto-generated method stub
		return "test1234";
	}

	public void init() {
		// TODO Auto-generated method stub
		System.out.println("docker image init");
	}

}

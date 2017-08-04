package com.leo.microservice.service;

import org.springframework.stereotype.Service;

public interface DockerImageService {
	
	String getDockerImages();
	void init();

}

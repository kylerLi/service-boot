package com.zookeeper.initContext;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.zookeeper.ServiceRegistry;

@Component
public class RegistryContextListener implements ServletContextListener{
	
	@Value("${server.address}")
	private String address;
	
	@Value("${server.port}")
	private int port;
	
	@Autowired
	private ServiceRegistry serviceRegistry;
	
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
		for (RequestMappingInfo  handler: map.keySet()) {
			if(null !=handler.getName())
				serviceRegistry.registry(handler.getName(), String.format("%s:%d", address,port));
			
			
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}

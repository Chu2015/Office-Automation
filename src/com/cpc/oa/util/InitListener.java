package com.cpc.oa.util;

import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cpc.oa.domain.Privilege;
import com.cpc.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//得到spring容器，取出service
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeservice = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		
		//得到显示所需的数据，放入context域中
		List<Privilege> topPrivilegeList = privilegeservice.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		
		List<Privilege> allPrivilegeList = privilegeservice.findAllWithUrl();
		sce.getServletContext().setAttribute("allPrivilegeList", allPrivilegeList);
		System.out.println("------------> 已准备数据 <------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
				
	}

}

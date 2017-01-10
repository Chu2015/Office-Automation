package com.cpc.test;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TestAction extends ActionSupport{
	
	@Override
	public String execute(){
		return ActionSupport.SUCCESS;
	}
}

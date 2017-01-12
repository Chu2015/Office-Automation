package com.cpc.oa.view.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Department;
import com.cpc.oa.domain.Role;
import com.cpc.oa.service.RoleService;
import com.cpc.oa.service.impl.RoleServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	public String list(){
		ActionContext.getContext().put("list", roleService.list());
		return "list";
	}
	
	public String delete(){
		roleService.delete(model.getId());
		System.out.println(ServletActionContext.getRequest().getRequestURL());
		return "toList";
	}
	
	public String addUI(){
		return "saveUI";
	}
	
	public String add(){
		roleService.save(model);
		return "toList";
	}
	
	public String editUI(){
		Role role = roleService.getById(this.model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	
	public String edit(){
		roleService.edit(model);
		return "toList";
	}
	
}

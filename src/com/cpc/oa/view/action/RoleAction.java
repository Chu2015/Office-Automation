package com.cpc.oa.view.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.domain.Role;
import com.cpc.oa.service.RoleService;
import com.cpc.oa.service.impl.RoleServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role>{

	private RoleService  roleService;
	private Role role2 = new Role();
	
	public String list(){
		ActionContext.getContext().put("list", roleService.list());
		return "list";
	}
	
	public String delete(){
		roleService.delete(role2.getId());
		System.out.println(ServletActionContext.getRequest().getRequestURL());
		return "toList";
	}
	
	public String addUI(){
		return "saveUI";
	}
	
	public String add(){
		roleService.save(role2);
		return "toList";
	}
	
	public String editUI(){
		Role role = roleService.getById(this.role2.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	
	public String edit(){
		roleService.edit(role2);
		return "toList";
	}

	/**
	 * 传递role属性
	 * @return
	 */
	public Role getRole() {
		return role2;
	}

	public void setRole(Role role) {
		this.role2 = role;
	}

	@Override
	public Role getModel() {
		return role2;
	}

	
	/**
	 * 从spring中得到service对象
	 * @return
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	
}

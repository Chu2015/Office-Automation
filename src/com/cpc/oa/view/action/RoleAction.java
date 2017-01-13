package com.cpc.oa.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Department;
import com.cpc.oa.domain.Privilege;
import com.cpc.oa.domain.Role;
import com.cpc.oa.service.RoleService;
import com.cpc.oa.service.PrivilegeService;
import com.cpc.oa.service.impl.RoleServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	private Long[] privilegeIds;
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String list(){
		ActionContext.getContext().put("list", roleService.findAll());
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
		Role role = roleService.findById(this.model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	
	public String edit(){
		roleService.update(model);
		return "toList";
	}
	
	public String setPrivilegeUI(){
		Role role = roleService.findById(this.model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		Set<Privilege> privileges = role.getPrivileges();
		privilegeIds = new Long[privileges.size()];
		int index = 0;
		for(Privilege p :  privileges){
			privilegeIds[index++] = p.getId();
		}
		
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		return "setPrivilegeUI";
	}
	
	public String setPrivilege(){
		List<Privilege> privilegeList = privilegeService.findByIds(privilegeIds);
		Role role = roleService.findById(model.getId());
		Set<Privilege> privileges = new HashSet<Privilege>(privilegeList);
		role.setPrivileges(privileges);
		
		roleService.update(role);
		return "toList";
	}
}

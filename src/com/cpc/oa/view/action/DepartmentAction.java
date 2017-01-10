package com.cpc.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.domain.Department;
import com.cpc.oa.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	
	@Resource
	private DepartmentService service;
	
	private Department department = new Department();
	
	public String list(){
		List<Department> list = service.list();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	public String delete(){
		service.delete(getDepartment().getId());
		return "toList";
	}
	
	public String addUI(){
		return "saveUI";
	}
	
	public String add(){
		service.add(department);
		return "toList";
	}
	
	public String editUI(){
		Department d = service.getById(department.getId());
		ActionContext.getContext().getValueStack().push(d);
		return "saveUI";
	}
	
	public String edit(){
		getService().update(getDepartment());
		return "toList";
	}

	@Override
	public Department getModel() {
		return department;
	}

	public DepartmentService getService() {
		return service;
	}

	public void setService(DepartmentService service) {
		this.service = service;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}

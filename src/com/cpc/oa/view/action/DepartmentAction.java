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
	
	private Department model = new Department();
	
	private Long parentId;
	
	public String list(){
		List<Department> list = null;
		if(parentId ==null){
			list = service.findTopList();
		}else{
			list = service.findChildren(parentId);
		}
		
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	public String delete(){
		service.delete(model.getId());
		System.out.println(parentId);
		return "toList";
	}
	
	public String addUI(){
		List<Department> list = service.list();
		ActionContext.getContext().put("departmentList", list);
		return "saveUI";
	}
	
	public String add(){
		Department parent= service.getById(parentId);
		model.setParent(parent);
		service.add(model);
		return "toList";
	}
	
	public String editUI(){
		List<Department> list = service.list();
		ActionContext.getContext().put("departmentList", list);
		
		Department d = service.getById(model.getId());
		ActionContext.getContext().getValueStack().push(d);
		if(d.getParent() != null){
			parentId = d.getParent().getId();
		}
		return "saveUI";
	}
	
	public String edit(){
		Department parent = service.getById(parentId);
		model.setParent(parent);
		getService().update(model);
		return "toList";
	}

	@Override
	public Department getModel() {
		return model;
	}
	public void setModel(Department model) {
		this.model = model;
	}

	public DepartmentService getService() {
		return service;
	}

	public void setService(DepartmentService service) {
		this.service = service;
	}



	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}

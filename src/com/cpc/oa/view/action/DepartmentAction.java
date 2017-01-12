package com.cpc.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Department;
import com.cpc.oa.service.DepartmentService;
import com.cpc.oa.util.DepartmentTreeList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	
	private Long parentId;
	
	public String list(){
		List<Department> list = null;
		if(parentId ==null){
			list = departmentservice.findTopList();
		}else{
			list = departmentservice.findChildren(parentId);
		}
		
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	public String delete(){
		System.out.println(model.getId()+"!!!!!!!!!!!!!!!!!!!!!");
		departmentservice.delete(model.getId());
		System.out.println(parentId);
		return "toList";
	}
	
	public String addUI(){
		
		List<Department> list = departmentservice.findTopList();
		List<Department> treeList = DepartmentTreeList.getTreeList(list);
		
		ActionContext.getContext().put("departmentList", treeList);
		return "saveUI";
	}
	
	public String add(){
		Department parent= departmentservice.findById(parentId);
		model.setParent(parent);
		departmentservice.save(model);
		return "toList";
	}
	
	public String editUI(){
		List<Department> list = departmentservice.findAll();
		ActionContext.getContext().put("departmentList", list);
		
		Department d = departmentservice.findById(model.getId());
		ActionContext.getContext().getValueStack().push(d);
		if(d.getParent() != null){
			parentId = d.getParent().getId();
		}
		return "saveUI";
	}
	
	public String edit(){
		Department parent = departmentservice.findById(parentId);
		model.setParent(parent);
		departmentservice.update(model);
		return "toList";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}

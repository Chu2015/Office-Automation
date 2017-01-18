package com.cpc.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Department;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.util.DepartmentTreeList;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{

	public String list(){
		List<Forum> list = forumService.findAll();
		ActionContext.getContext().put("forumList", list);
		return "list";
	}
	
	public String delete(){
		forumService.delete(model.getId());
		return "toList";
	}
	
	public String addUI(){
		return "saveUI";
	}
	
	public String add(){
		forumService.save(model);
		return "toList";
	}
	
	public String editUI(){
		Forum forum = forumService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}
	
	public String edit(){
		forumService.update(model);
		return "toList";
	}

	public String moveUp(){
		forumService.moveUp(model.getId());
		return "toList";
	}
	public String moveDown(){
		forumService.moveDown(model.getId());
		return "toList";
	}
}

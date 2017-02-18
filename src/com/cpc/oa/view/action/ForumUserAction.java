package com.cpc.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.domain.PageBean;
import com.cpc.oa.domain.Topic;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumUserAction extends BaseAction<Forum>{
	
	
	//显示所有的版块
	public String list(){
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	//显示某一版块下所有的主题
	public String show(){
		Forum forum = forumService.findById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
//		List<Topic> topicList = topicService.findByForum(forum);
//		ActionContext.getContext().put("topicList", topicList);
		
		PageBean pageBean = topicService.getPageBeanByForum(pageNum,pageSize,forum);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}
}
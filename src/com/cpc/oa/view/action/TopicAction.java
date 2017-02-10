package com.cpc.oa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.domain.Reply;
import com.cpc.oa.domain.Topic;
import com.cpc.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{

	private Long forumId;
	
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
	//显示主帖及回帖
	public String show(){
		Topic topic = topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		List<Reply> replyList = replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);
		return "show";
	}
	
	//添加新的帖子的页面
	public String addUI() {
		Forum forum = forumService.findById(forumId);
		ActionContext.getContext().put("forum", forum);
		return"addUI";
	}
	
	//添加新的帖子，回到显示主帖的页面
	public String add(){
		//表单提交后自动封装
		//model.setTitle(title);
		//model.setContent(content);
		model.setForum(forumService.findById(forumId));
		//直接获取的信息
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		//设计业务逻辑的信息，放在service中封装
		topicService.save(model);
		
		return "toShow";
	}


}

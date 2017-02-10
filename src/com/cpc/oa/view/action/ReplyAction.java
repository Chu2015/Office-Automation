package com.cpc.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Reply;
import com.cpc.oa.domain.Topic;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	public Long topicId;
	
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	//添加新恢复的页面
	public String addUI() {
		Topic topic = topicService.findById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	
	//添加回复，回到当前话题页面
	public String add(){
		//model.setContent(content);
		//model.setTitle(title);
		model.setTopic(topicService.findById(topicId));
		
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
	
		replyService.save(model);
		return "toTopicShow";
	}
}

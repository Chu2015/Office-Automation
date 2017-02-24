package com.cpc.oa.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.domain.PageBean;
import com.cpc.oa.domain.Reply;
import com.cpc.oa.domain.Topic;
import com.cpc.oa.domain.User;
import com.cpc.oa.util.QueryHelper;
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
		//实现分页版本1
//		PageBean pageBean = replyService.getPageBeanByTopic(pageNum,pageSize,topic);
//		ActionContext.getContext().getValueStack().push(pageBean);
		//实现分页版本2
//		List parameters = new ArrayList();
//		parameters.add(topic);
//		String hql = "from Reply r where r.topic=? order by r.postTime asc";
//		PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql, parameters);
//		ActionContext.getContext().getValueStack().push(pageBean);
		
		//实现分页最终版
		 new QueryHelper(Reply.class, "r")
		.addCondition( "r.topic=?", topic)
		.addOderProperty( "r.postTime", true)
        .preparePageBean(replyService, pageNum, pageSize);
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

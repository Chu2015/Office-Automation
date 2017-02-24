package com.cpc.oa.view.action;

import java.util.ArrayList;
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
	
	//"0"：全部主题
	//"1"：全部精华贴
	private int viewType = 0;
	//"0"：默认排序
	//"1"：只按最后更新时间排序
	//"2"：只按主题发表时间排序
	//"3"：只按回复数量排序
	private int orderBy = 0;
	//"false"：降序
	//"true"：升序
	private boolean asc = false;
	
	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

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
		//实现分页版本1
//		PageBean pageBean = topicService.getPageBeanByForum(pageNum,pageSize,forum);
//		ActionContext.getContext().getValueStack().push(pageBean);
		//实现分页版本2
//		List parameters = new ArrayList();
//		parameters.add(forum);
//		String hql = "from Topic t where t.forum=? order by(case t.type when 2 then 2 else 0 end) DESC , t.lastUpdateTime DESC";
//		PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql, parameters);
//		ActionContext.getContext().getValueStack().push(pageBean);
//		return "show";
		//实现分页版本3
		List parameters = new ArrayList();
		String hql = " from Topic t where t.forum=? ";
		parameters.add(forum);
		if( viewType == 1 ){
			hql = hql+" and t.type = ? ";
			parameters.add(Topic.TYPE_BEST);
		}
		if(orderBy==1){
			hql += " order by t.lastUpdateTime " + (asc ? "ASC" : "DESC"); 
		}else if (orderBy==2){
			hql += " order by t.postTime " + (asc ? "ASC" : "DESC"); 
		}else if(orderBy==3){
			hql += " order by t.replyCount " + (asc ? "ASC" : "DESC"); 
		}else{
			hql += " order by(case t.type when 2 then 2 else 0 end) DESC , t.lastUpdateTime DESC";
		}
		
		PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql, parameters);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}
}
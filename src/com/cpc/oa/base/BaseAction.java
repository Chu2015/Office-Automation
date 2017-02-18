package com.cpc.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.cpc.oa.domain.User;
import com.cpc.oa.service.DepartmentService;
import com.cpc.oa.service.ForumService;
import com.cpc.oa.service.PrivilegeService;
import com.cpc.oa.service.ReplyService;
import com.cpc.oa.service.RoleService;
import com.cpc.oa.service.TopicService;
import com.cpc.oa.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	/**
	 * 传递model属性
	 * @return
	 */
	protected T model;

	public BaseAction(){
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			this.model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	@Override
	public T getModel() {
		return model;
	}
	/**
	 * 从spring中得到service对象
	 * @return
	 */
	@Resource
	protected DepartmentService departmentservice;
	public DepartmentService getDepartmentservice() {
		return departmentservice;
	}
	public void setDepartmentservice(DepartmentService departmentservice) {
		this.departmentservice = departmentservice;
	}
	
	@Resource
	protected RoleService  roleService;
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Resource
	protected UserService  userService;

	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource
	protected PrivilegeService privilegeService;

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	@Resource
	protected ForumService forumService;

	public ForumService getForumService() {
		return forumService;
	}
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	@Resource
	protected TopicService topicService;

	public TopicService getTopicService() {
		return topicService;
	}
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	@Resource
	protected ReplyService replyService;

	public ReplyService getReplyService() {
		return replyService;
	}
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	public User getCurrentUser() {
		return (User)ActionContext.getContext().getSession().get("user");
	}
	
	//分页所需传入的参数
	protected int pageNum = 1;
	protected int pageSize = 10;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}

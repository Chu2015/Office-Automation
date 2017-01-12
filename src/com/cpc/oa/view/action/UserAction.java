package com.cpc.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cpc.oa.base.BaseAction;
import com.cpc.oa.domain.Department;
import com.cpc.oa.domain.Role;
import com.cpc.oa.domain.User;
import com.cpc.oa.util.DepartmentTreeList;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	/*
	 * 需要传递的参数设置
	 */       
	private Long departmentId;
	private Long[] roleIds;
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	/*
	 * action的方法
	 */
	public String list(){
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
	
	public String delete(){
		userService.delete(model.getId());
		return "toList";
	}
	
	public String addUI(){
		List<Department> departmentList = departmentservice.findTopList();
		List<Department> treeList = DepartmentTreeList.getTreeList(departmentList);
		
		ActionContext.getContext().put("departmentList", treeList);
		
		List<Role> roleList =roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		return "saveUI";
	}
	
	public String add(){
		Department d = departmentservice.findById(departmentId);
		model.setDepartment(d);
		List<Role> roleList = roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		
		userService.save(model);
		return "toList";
	}
	
	public String editUI(){
		/*
		 * 回显所有的部门和岗位
		 */
		List<Department> departmentList = departmentservice.findTopList();
		List<Department> treeList = DepartmentTreeList.getTreeList(departmentList);
		
		ActionContext.getContext().put("departmentList", treeList);
		
		List<Role> roleList =roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		/*
		 * 回显这个User原来信息
		 */
		User user = userService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		/*
		 * 回显这个User原来的部门和岗位
		 */
		departmentId = user.getDepartment().getId();
		roleIds = new Long[user.getRoles().size()];
		int index=0;
		for(Role role :user.getRoles()){
			roleIds[index++] = role.getId();
		}
		return "saveUI";
	}
	
	public String edit(){
		Department d = departmentservice.findById(departmentId);
		model.setDepartment(d);
		List<Role> roleList = roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		
		userService.update(model);
		return "toList";
	}

	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.findById(model.getId());

		// 2，设置要修改的属性（要使用MD5摘要）
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

}

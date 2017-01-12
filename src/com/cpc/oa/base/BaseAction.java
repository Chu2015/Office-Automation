package com.cpc.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.cpc.oa.service.DepartmentService;
import com.cpc.oa.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	/**
	 * 传递role属性
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
	
}

package com.cpc.oa.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;


public class User implements java.io.Serializable{
	
	private Long id;
	private Set<Role> roles = new HashSet<Role>();
	private Department department;

	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明
	
	public boolean hasPrivilegeByName(String name){
		if(isadmin()){
			return true;
		}else{
			for(Role role : roles){
				for(Privilege p: role.getPrivileges()){
					if(p.getName().equals(name)){
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public boolean hasPrivilegeByUrl(String url){
		if(isadmin()){
			return true;
		}
		Collection<String> allUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeList");
		
		url ="/" + url;
		int pos = url.indexOf("?");
		if(pos>-1){
			url = url.substring(0,pos);
		}
		if(url.endsWith("UI")){
			url = url.substring(0,url.length()-2);
		}
		
		if(!allUrls.contains(url)){
			return true;
		}else{
			for(Role role : roles){
				for(Privilege p: role.getPrivileges()){
					if(url.equals(p.getUrl())){
						return true;
					}
				}
			}
		return false;
		}
	}
	
	public boolean isadmin(){
		return "admin".equals(loginName);
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}

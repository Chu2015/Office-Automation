package com.cpc.oa.util;

import java.util.Collection;
import java.util.List;

import com.cpc.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilege extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		User user= (User) ActionContext.getContext().getSession().get("user");
		
		
		String actionName = invocation.getProxy().getActionName();
		String namespace  = invocation.getProxy().getNamespace();
		String url = namespace + actionName;
		
		if(user==null){
			if(actionName.startsWith("user_login")){
				return invocation.invoke();
			}else{
				return "loginUI";
			}
		}
		if(user.hasPrivilegeByUrl(actionName)){
			return invocation.invoke();
		}else{
			return "noPrivilegeError";
		}

	}

}

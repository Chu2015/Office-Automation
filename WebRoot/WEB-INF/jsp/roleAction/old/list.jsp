<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>My JSP 'lsit.jsp' starting page</title>

  </head>
  
  <body>
   	 <s:iterator value="#list">
   	 	<s:property value="rolename"/>
   	 	<s:property value="description"/>
   	 	<s:property value="id"/>
   	 	
   	 	<s:a href="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" >删除</s:a>
   	 	
   	    <s:a href="role_editUI?id=%{id}">修改</s:a>
   	 	<br/>
   	 </s:iterator>
     <%-- <s:debug></s:debug> --%>
	<s:a action="role_addUI">添加</s:a> 
  </body>
</html>

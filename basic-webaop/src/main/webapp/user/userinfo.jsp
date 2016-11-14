<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userinfo.jsp' starting page</title>
    
	<meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		
  		<a href="user/list">列表页面</a>
  	    <hr>
  		
  		get方法测试：
	  	<form action="user/find" method="post">
	  		<div>请输入主键id：<input id="id" name="id" type="text">
		    <input type="submit" value="查询"></div>
	  	</form>
	  	get方法获取的学生信息为：
	    <table>
	    	<tr>
	    		<td>
	    			id
	    		</td>
	    		<td>
	    			姓名
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<c:out value="${user.id}" />
	    		</td>
	    		<td>
	    			<c:out value="${user.username}" />
	    		</td>
	    	</tr>
	    </table>
	    <hr>
	    
	    add方法测试：
	    <form action="user/add" method="post">
		    <table>
		    	<tr>
		    		<td>主键id：</td>
		    		<td><input id="id" name="id" type="text"></td>
		    	</tr>
		    	<tr>
		    		<td>姓名：</td>
		    		<td><input id="username" name="username" type="text"></td>
		    	</tr>
		    </table>
		    <input type="submit" value="提交">
	    </form>
	    add方法返回的主键ID为：
	    <c:out value="${id}" />
	    <hr>
	    
	    
	    update方法测试：
	    <form action="user/update" method="post">
	    	<table>
		    	<tr>
		    		<td>主键id:</td>
		    		<td><input id="id" name="id" type="text"/></td>
		    	</tr>
		    	<tr>
		    		<td>姓名：</td>
		    		<td><input id="username" name="username" type="text"/></td>
		    	</tr>
	    	</table>
	    	<input type="submit" value="提交">
	    </form>
	    
	    delete方法测试：
	    <form action="user/delete" method="post">
	    	<div>请输入主键id：<input id="id" name="id" type="text">
		    <input type="submit" value="删除"></div>
	    </form>
	    <hr>
	    ${delFlag}
  </body>
</html>

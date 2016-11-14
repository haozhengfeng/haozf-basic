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
    
    <title>My JSP 'list.jsp' starting page</title>
    
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
  	
  	<a href="user/toUser">用户页面</a>
  	<hr>
  
  	<form action="user/list" method="post">
		<c:forEach items="${users}" var="user">
			${user.id }------->${user.username }<br>
		</c:forEach>
		
		<c:forEach items="${users2}" var="user">
			${user.id }------->${user.name }<br>
		</c:forEach>
		
		<div class="page">${page}</div>
	</form>
	
	<script type="text/javascript" src="js/libs/jquery/jquery-1.11.3.min.js"></script>
	
  </body>
</html>

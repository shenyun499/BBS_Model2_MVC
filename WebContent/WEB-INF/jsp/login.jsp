<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title>管理员登录</title>
	</head>

	<body>
		<form action="<%=request.getContextPath() %>/login" method="post">
			<input type="hidden" name="action" value="isTrue" />
			用户名：
			<input type="text" name="username" />
			<br> 
			密码：
			<input type="password" name="password" />
			<br>
			<input type="submit" />
		</form>
	</body>

</html>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
if(request.getSession().getAttribute("player")!=null){
	System.out.println("Player is Logged in already");
	response.sendRedirect("main.jsp");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Pong Score Tracking - Login</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>Competition Tracker</h1>
			<h3>Please Sign In</h3>
			<div class="container">
				<form method="POST" action="login" class="form-signin" role="form">
					<input type="text" name="username" class="form-control"
						placeholder="Email address" required autofocus> <input
						type="password" name="password" class="form-control"
						placeholder="Password" required> <label class="checkbox">
					</label>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				</form>

			</div>
			<!-- /container -->
		</div>
	</div>
	</div>
</body>
</html>

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
				<h1 class="logoBase">Head<span class="text-muted">to</span>Head</h1>
				<p><img class="img-thumbnail" src="assets/images/pongGuys.gif" /></p>
				<h3>Please Sign In</h3>
				<div class="row">
					<div class="col-xs-offset-1 col-xs-10 col-sm-offset-4 col-sm-4">
						<form id="signinForm" method="POST" action="login" class="form-signin" role="form">
							<input type="text" name="username" class="form-control"
								placeholder="Email address" required autofocus> <input
								type="password" name="password" class="form-control"
								placeholder="Password" required> <label class="checkbox">
							</label>
							<button class="btn btn-lg btn-primary btn-block">Sign in</button>
						</form>
						<div id="signingInLabel" class="pull-right hidden">Signing In <img src="<%=request.getContextPath()%>/assets/images/loading.gif"/></div>
						<div id="signingInFailedLabel" class="hidden pull-right" style="color:red;"><strong>Signing Failed!  the Email and Password combination you entered is incorrect!</strong></div>
					</div>
				</div>
				<!-- /container -->	
			</div>
		</div>
	
		<script>
			$(document).ready(function() {
				// bind 'myForm' and provide a simple callback function 
				$('#signinForm').ajaxForm(function() {
					return false;
				});
			});
	
			// attach handler to form's submit event 
			$('#signinForm').submit(function() {
				var handleSuccess = function(data) {
					$('#signingInLabel').addClass("hidden");
					if (data.success) {
						$('#signingInFailedLabel').addClass("hidden");
						window.location = 'leaderboards.jsp';
					} else {
						$('#signingInFailedLabel').removeClass("hidden");
					}
				};
				var options = {
					target : '#output1', // target element(s) to be updated with server response 
					beforeSubmit : function() {
						$('#signingInLabel').removeClass("hidden");
						$('#signingInFailedLabel').addClass("hidden");
					}, // pre-submit callback 
					success : handleSuccess, // post-submit callback 
					dataType : 'json', // 'xml', 'script', or 'json' (expected server response type) 
					timeout : 10000
				};
				// submit the form 
				$(this).ajaxSubmit(options);
				// return false to prevent normal browser submit and page navigation 
				return false;
			});
			
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

			ga('create', 'UA-50481107-1', 'headtohead.us');
			ga('send', 'pageview');
		</script>
	</body>
</html>

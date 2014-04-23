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
				<h1>Head 2 Head</h1>
				<h3>Please Sign In</h3>
				<div class="container">
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
			<!-- /container -->	
			</div>
		</div>
		
		<a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#passwordModal">Reset Password</a>

		<div id="passwordModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Reset Password</h4>
					</div>
					<form action="resetPassword" method="post">
					<div class="modal-body">
						<div class="control-group">
							<label for="current_password" class="control-label">Current
								Password</label>
							<div class="controls">
								<input type="password" name="currentPassword">
							</div>
						</div>
						<div class="control-group">
							<label for="new_password" class="control-label">New
								Password</label>
							<div class="controls">
								<input type="password" name="newPassword">
							</div>
						</div>
						<div class="control-group">
							<label for="confirm_password" class="control-label">Confirm
								Password</label>
							<div class="controls">
								<input type="password" name="confirmPassword">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<input type="submit" class="btn btn-primary"/>
					</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	
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
		</script>
	</body>
</html>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.game.*"%>
<%
PlayerProfileJspModel model = new PlayerProfileJspModel(request);
%>
<html>
	<head>
		<title>Head2Head - Profile Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<h4>Profile for <%=model.getLoginData().getPlayer().getName() %></h4>
			<a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#passwordModal">Update Password</a>
	
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
		</div>
	</body>
</html>
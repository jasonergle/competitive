<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.jspmodel.*"%>
<%
	ProfileEditJspModel model = new ProfileEditJspModel(request);
	request.setAttribute("model", model);
%>
<html>
	<head>
		<title>HeadToHead - Profile Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<div class="row">
				<div class="col-xs-11 col-xs-offset-1   col-sm-8 col-sm-offset-2  col-md-6 col-md-offset-3">
					<h4>Profile for <%=model.getLoginName()%>
						<small>
							<a href="#" class="btn btn-sm btn-success pull-right" data-toggle="modal" data-target="#passwordModal">Update Password</a>
						</small>
					</h4>
					<form role="form" 
								onsubmit="return validateForm();" 
								method="post"
								action="<%=request.getContextPath() %>/users/update">
						<input type="hidden" name="userId" value="<%= model.getLoginData().getLogin().getId() %>" />

						<div class="row">
							<div class="col-xs-10 col-sm-6 col-md-6">
								<div class="form-group">
									<label class="form-label" for="first_name">First Name</label>

									<input type="text" name="first_name" id="first_name"
										class="form-control input-lg" placeholder="First Name"
										tabindex="1" maxlength="20"
										value="<%=model.getLoginData().getLogin().getFirstName() %>" />
								</div>
							</div>
							<div class="col-xs-10 col-sm-6 col-md-6">
								<div class="form-group">
									<label class="form-label" for="last_name">Last Name </label>
									<input type="text" name="last_name" id="last_name"
										class="form-control input-lg" placeholder="Last Name"
										tabindex="2" maxlength="20"
										value="<%=model.getLoginData().getLogin().getLastName() %>" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-10 col-sm-6 col-md-6">
								<div class="form-group">
									<label class="form-label" for="display_name">Alias</label>
									<input type="text" name="display_name" id="display_name"
										class="form-control input-lg" placeholder="Display Name"
										tabindex="3" maxlength="25"
										value="<%=model.getLoginData().getLogin().getTitle() %>" />
								</div>
							</div>
							<div class="col-xs-10 col-sm-6 col-md-6">
								<div class="form-group">
									<label class="form-label" for="Phone">Phone</label>
									<input type="text" name="phone" id="phone"
										class="form-control input-lg" placeholder="Phone Number"
										tabindex="4" maxlength="14" 
										value="<%=model.getLoginData().getLogin().getPhone() %>" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-10 col-sm-9">
								<div class="form-group">
									<label class="form-label" for="email">Email</label>
									<input type="email" name="email" id="email"
										class="form-control input-lg" placeholder="Email Address"
										tabindex="5" maxlength="50" 
										value="<%=model.getLoginData().getLogin().getLogin() %>" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2 col-sm-offset-10">
								<div class="form-group">
									<button type="submit" class="btn btn-primary" >Update</button>
								</div>
							</div>
						</div>
				<!-- Add Achievements somewhere near here.
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="form-label" for="achievements"> Earned Achievements </label>
								</div>
							</div>
						</div>
				-->

					</form>
				</div>
			</div>

		
		<div id="passwordModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Reset Password</h4>
					</div>
					<form action="resetPassword" method="post">
					<div class="row">
						<div class="col-xs-12 col-sm-8 col-sm-offset-2">

					<div class="modal-body">
						<div class="control-group">
							<label for="current_password" class="control-label">Current Password</label>
							<div class="controls">
								<input type="password" name="currentPassword"
									class="form-control input-lg" placeholder="Old: password"
									tabindex="6" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label for="new_password" class="control-label">New Password</label>
							<div class="controls">
								<input type="password" name="newPassword"
									class="form-control input-lg" placeholder="New: p@ssW0rd"
									tabindex="7" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label for="confirm_password" class="control-label">Confirm Password</label>
							<div class="controls">
								<input type="password" name="confirmPassword"
									class="form-control input-lg" placeholder="Just Checking..."
									tabindex="8" maxlength="20" />
							</div>
						</div>
					</div>

						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<input type="submit" class="btn btn-primary"/>
					</div>

					</form>
				</div> <!-- /.modal-content -->
			</div> <!-- /.modal-dialog -->
		</div> <!-- /.modal -->
	</div> <!-- /.container -->
	</body>
</html>

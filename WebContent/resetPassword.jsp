<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
int i = 0;
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Head 2 Head - Password Reset</title>
		<link href="assets/libs/bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="modal" id="password_modal">
		    <div class="modal-header">
		        <h3>Change Password <span class="extra-title muted"></span></h3>
		    </div>
		    <div class="modal-body form-horizontal">
		        <div class="control-group">
		            <label for="current_password" class="control-label">Current Password</label>
		            <div class="controls">
		                <input type="password" name="current_password">
		            </div>
		        </div>
		        <div class="control-group">
		            <label for="new_password" class="control-label">New Password</label>
		            <div class="controls">
		                <input type="password" name="new_password">
		            </div>
		        </div>
		        <div class="control-group">
		            <label for="confirm_password" class="control-label">Confirm Password</label>
		            <div class="controls">
		                <input type="password" name="confirm_password">
		            </div>
		        </div>      
		    </div>
		    <div class="modal-footer">
		        <button href="#" class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		        <button href="#" class="btn btn-primary" id="password_modal_save">Save changes</button>
		    </div>
		</div>
	</body>
</html>

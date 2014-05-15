<!DOCTYPE html>
<html>
<head>
<title>Register for Head to Head</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../../header.jsp"></jsp:include>

</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<div id="register" class="tab-pane">
				<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<form role="form" 
							onsubmit="return validateForm();" 
							method="post"
							action="<%=request.getContextPath() %>/createNewAccount">
						<h2>
							Please Sign Up for Head to Head<small>It's free!</small>
						</h2>
						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="first_name" id="first_name"
										class="form-control input-lg" placeholder="First Name"
										tabindex="1" maxlength="20">
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="last_name" id="last_name"
										class="form-control input-lg" placeholder="Last Name"
										tabindex="2" maxlength="20">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="display_name" id="display_name"
										class="form-control input-lg" placeholder="Display Name"
										tabindex="3" maxlength="25">
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="phone" id="phone"
										class="form-control input-lg" placeholder="Phone Number"
										tabindex="4" maxlength="14">
								</div>
							</div>
						</div>
						<div class="form-group">
							<input type="email" name="email" id="email"
								class="form-control input-lg" placeholder="Email Address"
								tabindex="5" maxlength="50">
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="password" name="password" id="password"
										class="form-control input-lg" placeholder="Password"
										tabindex="6" maxlength="20">
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="password" name="password_confirmation"
										id="password_confirmation" class="form-control input-lg"
										placeholder="Confirm Password" tabindex="7" maxlength="20">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-9 col-sm-9 col-md-9">
								By clicking <strong class="label label-primary">Register</strong>,
								you agree to the <a href="#" data-toggle="modal"
									data-target="#t_and_c_m">Terms and Conditions</a> set out by
								this site, including our Cookie Use.
							</div>
						</div>

						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-6 col-md-6 pull-right">
								<input type="submit" value="Register"
									class="btn btn-primary btn-block btn-lg" tabindex="7">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="t_and_c_m" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">Terms & Conditions</h4>
				</div>
				<div class="modal-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Similique, itaque, modi, aliquam nostrum at sapiente consequuntur
						natus odio reiciendis perferendis rem nisi tempore possimus ipsa
						porro delectus quidem dolorem ad.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">I Agree</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script>
	function validateForm(){
		if($("#first_name").val() == ''){
			bootbox.alert("First Name is required");
			$("#first_name").focus();
			return false;
		}
		if($("#last_name").val() == ''){
			bootbox.alert("Last Name is required");
			$("#last_name").focus();
			return false;
		}
		if($("#password").val().length < 5){
			bootbox.alert("Password is required to be 5 characters or longer");
			$("#password").focus();
			return false;
		}
		if($("#password_confirmation").val() != $("#password").val()){
			bootbox.alert("Passwords don't match");
			$("#password_confirmation").focus();
			return false;
		}
		return true;
	}
	</script>
</body>


</html>
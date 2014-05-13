<!DOCTYPE html>
<html>
<head>
<title>New Registration</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../../header.jsp"></jsp:include>

</head>
<body>

<div class="container">
<div class="well">
      <form id="signup" class="form-horizontal" method="post"
				action="success.php">
		<legend>Sign Up</legend>
		<div class="control-group">
	        <label class="control-label">First Name</label>
			<div class="controls">
			    <div class="input-prepend">
				<span class="add-on"><i class="icon-user"></i></span>
					<input type="text" class="input-xlarge" id="fname" name="fname"
								placeholder="First Name">
				</div>
			</div>
		</div>
		<div class="control-group ">
	        <label class="control-label">Last Name</label>
			<div class="controls">
			    <div class="input-prepend">
				<span class="add-on"><i class="icon-user"></i></span>
					<input type="text" class="input-xlarge" id="lname" name="lname"
								placeholder="Last Name">
				</div>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">Email</label>
			<div class="controls">
			    <div class="input-prepend">
				<span class="add-on"><i class="icon-envelope"></i></span>
					<input type="text" class="input-xlarge" id="email" name="email"
								placeholder="Email">
				</div>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">Gender</label>
			<div class="controls">

					<p><div id="gender" name="gender" class="btn-group"
							data-toggle="buttons-radio">
                    <button type="button" class="btn btn-info">Male</button>
                    <button type="button" class="btn btn-info">Female</button>

                  </div>
						</p>

			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">Password</label>
			<div class="controls">
			    <div class="input-prepend">
				<span class="add-on"><i class="icon-lock"></i></span>
					<input type="Password" id="passwd" class="input-xlarge"
								name="passwd" placeholder="Password">
				</div>
			</div>
		</div>
		<div class="control-group">
	        <label class="control-label">Confirm Password</label>
			<div class="controls">
			    <div class="input-prepend">
				<span class="add-on"><i class="icon-lock"></i></span>
					<input type="Password" id="conpasswd" class="input-xlarge"
								name="conpasswd" placeholder="Re-enter Password">
				</div>
			</div>
		</div>

		<div class="control-group">
		<label class="control-label"></label>
	      <div class="controls">
	       <button type="submit" class="btn btn-success">Create <span
								id="IL_AD4" class="IL_AD">My Account</span>
						</button>

	      </div>

	</div>

	  </form>

   </div>
</div>

 <script type="text/javascript">
$(document).ready(function(){

$("#signup").validate({
rules:{
fname:"required",
lname:"required",
email:{
required:true,
email: true
},
passwd:{
required:true,
minlength: 8
},
conpasswd:{
required:true,
equalTo: "#passwd"
},
gender:"required"
},

errorClass: "help-inline"

});
});
</script>
</body>


</html>
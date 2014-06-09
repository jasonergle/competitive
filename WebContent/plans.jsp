<!DOCTYPE html>
<html data-wf-site="535daa50e6ef15215d000418">
<head>
  <meta charset="utf-8">
  <title>HeadToHead :: Plans and Pricing</title>
  <meta name="description" content="Track games, series, seasons or whatever for everyone in your leagues.  Allow everyone to see up to date statistics with real-time standings and custom leader-boards.">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<jsp:include page="header.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="assets/css/pong.webflow.css">


  <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.4.7/webfont.js"></script>
  <script>
    WebFont.load({
      google: {
        families: ["Droid Sans:400,700","Roboto:regular,700,700italic,900","Dosis:regular,700"]
      }
    });
  </script>
</head>
<body>
  <div class="hero-bg">
		<div class="section header jumbotron">
      <div class="container">
        <div class="row">
          <div class="col-sm-4 company-column">
						<div class="company-title"><img src="assets/images/ramHead_l_sq.png" class="h2hlogo"  />HeadtoHead<img src="assets/images/ramHead_r_sq.png" class="h2hlogo" />&nbsp;</div>
          </div>
					<div class="col-md-8 nav-bar">
						<a class="nav-link" href="index.jsp#page-nav-Section-2" target="_self">Search Leagues</a>
						<a class="nav-link register" href="jsp/user/createAccount.jsp">Register</a>
						<a class="nav-link sign-up" href="index.jsp" target="_blank">Sign Up</a>
          </div>
        </div>
      </div>
    </div>
		<div class="space"></div>


	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="assets/images/beerMugs_sm.jpg" alt="...">
					<div class="caption text-center">
						<h2>Cheapskate</h2>
						<p>
							<ul class="lead details list-unstyled">
								<li>Track Rankings</li>
								<li>Show personal stats</li>
								<li>Track improvements and goals</li>
							</ul>
							<h4 class="text-muted monthly">(Free)</h4>
							<h5 class="text-info users">up to 12 players/teams</h5>
						</p>
						<p class="action">
							<a class="btn btn-primary" href="https://headtohead.us/session/signup?free" target="_blank">Sign Up</a>
							<a href="#" class="btn btn-default" role="button">Try it.</a>
						</p>
					</div>
				</div>
			</div><!-- /col -->
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="assets/images/foose1.jpg" alt="...">
					<div class="caption text-center">
						<h2>Thrifty Nickel</h2>
						<p>
							<ul class="lead details list-unstyled">
								<li>Calculate Handicaps</li>
								<li>Add Nicknames</li>
								<li>Earn Awards</li>
							</ul>
							<h4 class="text-muted monthly">($5/mo)</h4>
							<h5 class="text-info users">up to 50 players/teams</h5>
						</p>
						<p class="action">
							<a class="btn btn-primary" href="https://headtohead.us/session/signup?basic" target="_blank">Sign Up</a>
							<a href="#" class="btn btn-default" role="button">Try it.</a>
						</p>
					</div>
				</div>
			</div><!-- /col -->
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="assets/images/pong_wide.jpg" alt="...">
					<div class="caption text-center">
						<h2>Best Friend</h2>
						<p>
							<ul class="lead details list-unstyled">
								<li>Track full leagues</li>
								<li>Export Seasons</li>
								<li>Create Brackets</li>
								<li>Generate Postings in PDF</li>
							</ul>
							<h4 class="text-muted monthly">($20/mo)</h4>
							<h5 class="text-info users">unlimited players/teams</h5>
						</p>
						<p class="action">
							<a class="btn btn-primary" href="https://headtohead.us/session/signup?premium" target="_blank">Sign Up</a>
							<a href="#" class="btn btn-default" role="button">Try it.</a>
						</p>
					</div>
				</div>
			</div><!-- /col -->
		</div><!-- /row -->
	</div><!-- /container -->

</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

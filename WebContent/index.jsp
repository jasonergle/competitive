<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%
IndexJspModel model = new IndexJspModel(request);
request.setAttribute("model", model);
%>
<html>
<head>
<meta charset="utf-8">
<title>Head To Head</title>
<meta name="description"
	content="Track games, series, seasons or whatever for everyone in your leagues.  Allow everyone to see up to date statistics with real-time standings and custom leader-boards.">
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
	<div>
		<div class="hero-bg">
			<div class="section header jumbotron">
				<div class="container">
					<div class="row">
						<div class="col-sm-4 company-column">
							<div class="company-title">
								<img alt="head to head" src="assets/images/ramHead_l_sq.png"
									class="h2hlogo" /> HeadToHead <img alt="head to head"
									src="assets/images/ramHead_r_sq.png" class="h2hlogo" /> &nbsp;
							</div>
						</div>
						<div class="col-sm-8 nav-bar">
							<a class="nav-link" href="#about" target="_self">About</a>
							<a class="nav-link" href="#leaderboards" target="_self">View Leaderboards</a>
							<a class="nav-link register" href="jsp/user/createAccount.jsp">Register</a>
							<a class="nav-link" href="plans.jsp" target="_self">Plans</a>
							<a class="nav-link sign-up" href="signin.jsp">Sign In</a>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<h1>Track your League</h1>
				<div class="subtitle">
					All sized leagues:  From office teams, intramural, to even full sized professionally managed leagues.
				</div>
			</div>
		</div>
	</div>
	<div class="container visible-xs visible-sm">
		<div class="row">
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="snippet-text-section">
					<div class="snippet-title">Most games supported</div>
					<div class="snippet-text">
					We provide support for a wide array of sports and games. We are always growing the list of supported
					games as well.
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="snippet-text-section">
					<div class="snippet-title">Easy entry of scores</div>
					<div class="snippet-text">
					You can determine who can enter scores for matches in your league.  The scores can be entered by
					computer or from your favorite smart phone.  It takes only a few seconds to enter a match.
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="snippet-text-section">
					<div class="snippet-title">Free to use</div>
					<div class="snippet-text">
					Track your leagues matches, view leaderboards or standings for your league, manage it's player, all
					for free!  If you are feeling frisky, we offer more advanced features for a small fee.
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="snippet-text-section">
					<div class="snippet-title">Mobile Device friendly</div>
					<div class="snippet-text">Easy to track and view on the go.</div>
				</div>
			</div>
		</div>
	</div>
	<div class="hidden-xs hidden-sm section">
		<div id="about" class="container">
			<div class="row new-class">
				<div class="col-md-9">
					<h4>League Tracking</h4>
					<div class="section-description">
					Easily track your league's record.  Whether you run a professionally managed sports league and need
					to track league match scores, or you just have an office Ping Pong league and want to keep some 
					records to make the competition more fun and fierce.  You can control who can enter match scores, 
					and leaderboards or standings for the league are updated automatically and available to anyone you
					choose to link them to.
					</div>
				</div>
				<div class="col-md-3">
					<a class="more-link pull-right" href="plans.jsp" target="_self">View More</a>
				</div>
			</div>
			<div class="row snippet-row">
				<div class="col-md-4">
					<div class="snippet-title">Easy to Track a League</div>
					<div class="snippet-text">
					Simply create a league, pick games the league plays, name your teams, and you are ready to start 
					tracking matches.
					</div>
					<a class="w-clearfix w-inline-block snippet" href="#"> 
						<img class="example-image img-rounded" src="assets/images/tennis_sm.jpg"
						alt="tennis balls">
					</a>
				</div>
				<div class="col-md-4">
					<div class="snippet-title">Easy entry of scores</div>
					<div class="snippet-text">
					You can determine who can enter scores for matches in your league.  The scores can be entered by
					computer or from your favorite smart phone.  It takes only a few seconds to enter a match.
					</div>
					<a class="w-clearfix w-inline-block snippet" href="#">
						<img class="example-image img-rounded" src="assets/images/scoreEntry_sm.png"
						alt="Head to Head Score entry">
					</a>
				</div>
				<div class="col-md-4">
					<div class="snippet-title">Free to use</div>
					<div class="snippet-text">
					Track your leagues matches, view leaderboards or standings for your league, manage it's player, all
					for free!  If you are feeling frisky, we offer more advanced features as well.
					</div>
					<a class="w-clearfix w-inline-block snippet" href="plans.jsp">
						<img class="example-image img-rounded" src="assets/images/beerMugs_sm.jpg"
						alt="Beer Mugs">
					</a>
				</div>
			</div>
			<div class="row secondary-row">
				<div class="col-md-8">
					<h4>Simple. With most game types supported</h4>
					<p>We are continuously building more game types to support your
						tracking.&nbsp;&nbsp;We will take requests for any aditional type
						not found in our system already.</p>
						<div class="row">
							<c:forEach var="game" items="${model.games}" varStatus="status" >
								<c:if test="${status.index %4==0 }">
									<c:if test="${status.index >0 }">
										</p> </div>
									</c:if>
									<div class="col-sm-3 col-xs-6"> <p>
								</c:if>
								<a href="game/${game.type.toLowerCase()}">${game.name}</a> <br />
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="hidden-xs col-sm-12 col-md-4">
					<div class="row">
						<div class="col-sm-4 col-md-6">
							<img class="img-example" src="assets/images/flagfbPlaybook.jpg"
								alt="Flag Football Playbook">
						</div>
						<div class="col-sm-4 col-md-6">
							<img class="img-example" src="assets/images/shuffleboard_sm.jpg"
								alt="Shuffleboard">
						</div>
						<div class="clearfix visible-xs"></div>
						<div class="col-sm-4 col-md-6">
							<img class="img-example" src="assets/images/pong_wide.jpg"
								alt="Ping Pong Paddle">
						</div>
					</div>
				</div>
			</div>
			<div class="row secondary-row">
				<div class="col-md-8 col-sm-6">
					<h4>Try <i>Head To Head</i> out for free</h4>
					<p>Easily add teams and/or players and start tracking your
						conquests in a few minutes.&nbsp;&nbsp;Allow team members to log
						games too, allowing you more time to win them.</p>
				</div>
				<div class="col-md-4 col-sm-6 button-column">
					<a class="button" href="jsp/user/createAccount.jsp">Setup your league</a>
				</div>
			</div>
		</div>
	</div>
	<div id="leaderboards" class="content-bg">
		<div class="container">
			<h2>View Popular Public Leaderboards</h2>
		</div>
	</div>
	<div class="section">
		<div class="container">

		</div>
	</div>
	<div class="content-bg two">
		<div class="container">
			<h2 id="page-nav-Section-3">All sized leagues:&nbsp;&nbsp;From
				office teams, intramural, and full sized corporate leagues.</h2>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

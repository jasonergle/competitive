<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>HeadToHead - Admin Page</title>
<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="/navbar.jsp"></jsp:include>
		<div class="container">
			<ul id="tabs" class="nav nav-tabs">
				<li class="active"><a href="#tab_properties" data-toggle="tab">Properties</a></li>
				<li class=""><a href="#tab_participants" data-toggle="tab">Participants</a></li>
				<li class=""><a href="#tab_logins" data-toggle="tab">Logins</a></li>
				<li class=""><a href="#tab_games" data-toggle="tab">Games</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane active" id="tab_properties">
					<form class="form-horizontal">
						<fieldset>
							<legend>League Properties</legend>
							<div class="form-group">
								<label class="col-md-4 control-label" for="name">League
									Name</label>
								<div class="col-md-4">
									<input id="name" name="name" type="text"
										placeholder="Name of League" class="form-control input-md"
										required="required"> <span class="help-block">Give your
										league a name. </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="abbreviation">Abbreviation</label>
								<div class="col-md-1">
									<input id="abbreviation" name="abbreviation" type="text"
										placeholder="abbr" class="form-control input-md" required="required">
									<span class="help-block">Short name for league</span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="public">Is
									Public</label>
								<div class="col-md-4">
									<label class="radio-inline" for="public-0"> <input
										type="radio" name="public" id="public-0" value="Yes"
										checked="checked"> Yes
									</label> <label class="radio-inline" for="public-1"> <input
										type="radio" name="public" id="public-1" value="No">
										No
									</label>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="password">Join
									Password</label>
								<div class="col-md-4">
									<input id="password" name="password" type="text"
										placeholder="Join Password" class="form-control input-md">
									<span class="help-block">Password used to join this
										league</span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="rankingMethods">Ranking
									Methods</label>
								<div class="col-md-4">
									<label class="checkbox-inline" for="rankingMethods-0">
										<input type="checkbox" name="rankingMethods"
										id="rankingMethods-0" value="Leaderboards">
										Leaderboards
									</label> <label class="checkbox-inline" for="rankingMethods-1">
										<input type="checkbox" name="rankingMethods"
										id="rankingMethods-1" value="Standings"> Standings
									</label>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="tab-pane" id="tab_participants">Participants</div>
				<div class="tab-pane" id="tab_logins">Logins</div>	
				<div class="tab-pane" id="tab_games">Games</div>	
			</div>	
		</div>
	</div>
	<script>

	</script>
</body>
</html>
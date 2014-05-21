<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<!DOCTYPE html>
<%
EditLeagueJspModel model = new EditLeagueJspModel(request);
request.setAttribute("model", model);
%>
<html>
<head>
<title>HeadToHead - League Properties</title>
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
				<div class="tab-pane active" id="tab_properties"><%@include file="leagueProperties.jsp" %></div>
				<div class="tab-pane" id="tab_participants"><%@include file="leagueParticipants.jsp" %></div>
				<div class="tab-pane" id="tab_logins"><%@include file="leagueLogins.jsp" %></div>	
				<div class="tab-pane" id="tab_games"><%@include file="leagueGames.jsp" %></div>	
			</div>	
		</div>
	</div>
	<script>
	
	</script>
</body>
</html>
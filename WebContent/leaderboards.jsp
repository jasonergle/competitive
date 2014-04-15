<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%
LeaderboardsJspModel model = new LeaderboardsJspModel(request);
%>
<html>
	<head>
		<title>Head2Head - Main Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<h3><strong><%=model.getLoginData().getCurLeague().getName() %></strong> Player Leaderboards</h3>
			<table id="statTablePlayers" class="table table-striped">
				<thead>
					<tr>
						<th>Player Name</th>
						<th>Wins</th>
						<th>Losses</th>
						<th>Win %</th>
					</tr>
				</thead>
				<tbody>
					<%for(Player player : model.getPlayers()){%>
					<tr>
						<td><a class="" href="viewStats.jsp?player=<%=player.getId() %>"> <%=PlayerManager.getLabelForPlayer(player) %></a></td>
						<td><%=player.getWonPlayerMatches().size()%></td>
						<td><%=player.getLostPlayerMatches().size()%></td>
						<td><%=String.format("%.3f",PlayerManager.getPlayerMatchWinningPercentage(player))%></td>
					</tr>
					<%} %>
				</tbody>
			</table>
			<h3><strong><%=model.getLoginData().getCurLeague().getName() %></strong> Team Leaderboards</h3>
			<table id="statTableTeams" class="table table-striped">
				<thead>
					<tr>
						<th>Team Name</th>
						<th>Wins</th>
						<th>Losses</th>
						<th>Win %</th>
					</tr>
				</thead>
				<tbody>
					<%for(Team team : model.getTeams()){%>
					<tr>
						<td><a class="" href="viewStats.jsp?team=<%=team.getId() %>"> <%=team.getName() %></a></td>
						<td><%=team.getWonTeamMatches().size()%></td>
						<td><%=team.getLostTeamMatches().size()%></td>
						<td><%=String.format("%.3f",TeamManager.getTeamMatchWinningPercentage(team))%></td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
	</body>
</html>

<script>
$( document ).ready(function() {
	
	$('#statTablePlayers').DataTable({
					bFilter: false, 
					bLengthChange: false,
					bPaginate: true,
					aaSorting:[[1, "desc"],[3,"desc"]] });
	
	$('#statTableTeams').DataTable({
		bFilter: false, 
		bLengthChange: false,
		bPaginate: true,
		aaSorting:[[1, "desc"],[3,"desc"]] });
});
</script>
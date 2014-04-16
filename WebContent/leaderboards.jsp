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
			<%for(Game g: model.getAllowedGames()){ %>
				<h3><strong><%=model.getLoginData().getCurLeague().getName() %></strong> <%=g.getName() %> Leaderboards</h3>
				<table id="statTablePlayers" class="statTable table table-striped">
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
							<td></td>
							<td></td>
							<td><%=PlayerManager.getWinningPercentage(player, g) %></td>
						</tr>
						<%} %>
					</tbody>
				</table>
				<br/>
			<%} %>
		</div>
	</body>
</html>

<script>
$( document ).ready(function() {
	
	$('.statTable').DataTable({
					bFilter: false, 
					bLengthChange: false,
					bPaginate: true,
					aaSorting:[[1, "desc"],[3,"desc"]] });
	
});
</script>
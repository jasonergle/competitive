<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.pong.dbo.Player"%>
<%@page import="com.erglesoft.pong.mgr.*"%>
<%@page import="com.erglesoft.login.*"%>
<%@page import="java.util.*"%>
<%
System.out.println("main.jsp BEGIN");
UserLoginData data = UserLoginData.fromHttpSession(request);
System.out.println("main.jsp 1");
PlayerManager pMgr = new PlayerManager(data);
System.out.println("main.jsp 2");
Set<Player> players = pMgr.getAllPlayersForCurrentLeague();
System.out.println("main.jsp 3");
Player curPlayer = data.getPlayer();
System.out.println("main.jsp 4");
%>
<html>
	<head>
		<title>Pong Score Tracking - Main Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
		<h3>Player Data</h3>
		<table id="statTable" class="table table-striped">
			<thead>
				<tr>
					<th>Player Name</th>
					<th>Wins</th>
					<th>Losses</th>
					<th>Win %</th>
				</tr>
			</thead>
			<tbody>
				<%for(Player player : players){%>
				<tr>
					<td><a class="" href="viewStats.jsp?player=<%=player.getId() %>"> <%=PlayerManager.getLabelForPlayer(player) %></a></td>
					<td><%=player.getWonPlayerMatches().size()%></td>
					<td><%=player.getLostPlayerMatches().size()%></td>
					<td><%=PlayerManager.getPlayerMatchWinningPercentage(player)%></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</body>
</html>

<script>
$( document ).ready(function() {
	
	$('#statTable').DataTable({
					bFilter: false, 
					bLengthChange: false,
					bPaginate: false,
					aaSorting:[[1, "desc"],[3,"desc"]] });
});
</script>
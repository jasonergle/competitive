<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="java.util.*"%>
<%
PlayerManager pMgr = new PlayerManager(request);
MatchManager mMgr = new MatchManager(request);
Set<PlayerMatch> matches = mMgr.getPlayerMatchesForCurrentLeague();
Set<TeamMatch> teamMatches = mMgr.getTeamMatchesForCurrentLeague();
%>
<html>
	<head>
		<title>Pong Score Tracking - Main Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
		<h3>Singles Data</h3>
		<table id="singlesTable" class="table table-striped">
			<thead>
				<tr>
					<th>Date</th>
					<th>Game</th>
					<th>Winner</th>
					<th>Loser</th>
					<th>Score</th>
					<th>Creator</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%for(PlayerMatch match : matches){%>
				<tr>
					<td><%=MatchManager.getFormattedDate(match.getMatchDate()) %></td>
					<td><%=match.getGame().getName() %></td>
					<td><a class="" href="viewStats.jsp?player=<%=match.getWinner().getId() %>"> <%=PlayerManager.getLabelForPlayer(match.getWinner()) %></a></td>
					<td><a class="" href="viewStats.jsp?player=<%=match.getLoser().getId() %>"> <%=PlayerManager.getLabelForPlayer(match.getLoser()) %></a></td>
					<td><%=match.getWinnerScore()%>/<%=match.getLoserScore()%></td>
					<td><a class="" href="viewStats.jsp?player=<%=match.getCreator().getId() %>"> <%=PlayerManager.getNameForPlayer(match.getCreator()) %></a></td>
					<td><a class="btn btn-danger btn-xs" href="deleteMatch?id=<%=match.getId() %>">Delete</a></td>
				</tr>
				<%} %>
				
				<%for(TeamMatch match : teamMatches){%>
				<tr>
					<td><%=MatchManager.getFormattedDate(match.getMatchDate()) %></td>
					<td><%=match.getGame().getName() %></td>
					<td><a class="" href="viewStats.jsp?player=<%=match.getWinner().getId() %>"> <%=match.getWinner().getName() %></a></td>
					<td><a class="" href="viewStats.jsp?player=<%=match.getLoser().getId() %>"> <%=match.getLoser().getName()%></a></td>
					<td><%=match.getWinnerScore()%>/<%=match.getLoserScore()%></td>
					<td><a class="" href="viewStats.jsp?player=<%=match.getCreator().getId() %>"> <%=PlayerManager.getNameForPlayer(match.getCreator()) %></a></td>
					<td><a class="btn btn-danger btn-xs" href="deleteMatch?id=<%=match.getId() %>">Delete</a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</body>
	<script>
		$( document ).ready(function() {
			
			$('#singlesTable').DataTable({
							bFilter: true, 
							bLengthChange: true,
							bPaginate: true,
							aaSorting:[[0, "desc"]] });
		});
	</script>
</html>
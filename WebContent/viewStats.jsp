<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.pong.dbo.*"%>
<%@page import="com.erglesoft.pong.mgr.*"%>
<%@page import="java.util.*"%>
<%
Integer playerId = Integer.parseInt(request.getParameter("player"));
PlayerManager pMgr = new PlayerManager(request);
Player player = pMgr.getPlayerById(playerId);
Double playerWinPerc =PlayerManager.getPlayerMatchWinningPercentage(player);
Map<String, Integer> scoredAndAllowed = PlayerManager.getPointsScoredAndAllowed(player);
Map<Player, VersusRecord> opponentInfo = PlayerManager.getOpponentInfo(player);
%>
<html>
	<head>
		<title>Pong Score Tracking - Main Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
		<div>Viewing Data For <strong><%=PlayerManager.getLabelForPlayer(player) %></strong></div>
		<br/>
		<h3>Results</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Type</th>
					<th>Wins</th>
					<th>Losses</th>
					<th>Win %</th>
					<th>PS</th>
					<th>PA</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Ping Pong</td>
					<td><%=player.getWonPlayerMatches().size()%></td>
					<td><%=player.getLostPlayerMatches().size()%></td>
					<td><%=String.format("%1.2f",playerWinPerc)%></td>
					<td><%=String.format("%2.2f",scoredAndAllowed.get("scored")/((double)player.getWonPlayerMatches().size()+player.getLostPlayerMatches().size())) %></td>
					<td><%=String.format("%2.2f",scoredAndAllowed.get("allowed")/((double)player.getWonPlayerMatches().size()+player.getLostPlayerMatches().size())) %></td>
				</tr>
			</tbody>
		</table>
		
		<h3>vs Others</h3>
		<table id="versusSinglesTable" class="table table-striped">
			<thead>
				<tr>
					<th>Opponent</th>
					<th>Wins</th>
					<th>Losses</th>
					<th>Win %</th>
					<th>PS</th>
					<th>PA</th>
					<th>Handycap</th>
				</tr>
			</thead>
			<tbody>
				<%for(Player opponent: opponentInfo.keySet()){ 
					VersusRecord record = opponentInfo.get(opponent);
				%>
					<tr>
						<td><a class="" href="viewStats.jsp?player=<%=opponent.getId() %>"> <%=PlayerManager.getLabelForPlayer(opponent) %></a></td>
						<td><%=record.getWinCnt() %></td>
						<td><%=record.getMatchCnt()-record.getWinCnt()%></td>
						<td><%=(double)record.getWinCnt()/record.getMatchCnt()%></td>
						<td><%=String.format("%2.2f",record.getPointsScored()) %></td>
						<td><%=String.format("%2.2f",record.getPointsAllowed()) %></td>
						<td><%=record.getHandycap() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</body>
</html>

<script>
$( document ).ready(function() {
	
	$('#versusSinglesTable').DataTable({
					bFilter: false, 
					bLengthChange: false,
					bPaginate: false,
					aaSorting:[[1, "desc"],[3,"desc"]] });
});
</script>

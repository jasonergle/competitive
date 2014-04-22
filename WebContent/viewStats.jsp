<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="java.util.*"%>
<%
ViewStatsJspModel model = new ViewStatsJspModel(request);
%>
<html>
	<head>
		<title>Head 2 Head Score Tracking - Main Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<div class="italic">Stats For <strong><%=model.getTargetLabel() %></strong></div>
			<br/>
			<h4>Individual Results</h4>
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
					<%for(Game game: model.getAllowedGames()){ 
						Map<String, Number> wlData = model.getWonLossData(game);%>
					<tr>
						<td><%=game.getName() %></td>
						<td><%=wlData.get("winCnt") %></td>
						<td><%=wlData.get("lossCnt")%></td>
						<td><%=model.getFormattedWinningPercentage(wlData)%></td>
						<td><%=String.format("%1.3f",wlData.get("ps"))  %></td>
						<td><%=String.format("%1.3f",wlData.get("pa")) %></td>
					</tr>
					<%} %>
				</tbody>
			</table>
			
			<h4>Head 2 Head</h4>
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
					<%
						for(Game game: model.getOpponentInfo().keySet()){ 
									Map<Player, Head2HeadRecord> records = model.getOpponentInfo().get(game);
									for(Player opponent: records.keySet()){ 
										Head2HeadRecord record = records.get(opponent);
					%>
							<tr>
								<td><a class="" href="viewStats.jsp?<%=record.getOpponentUrlArg() %>"> <%=PlayerManager.getLabelForPlayer(opponent) %></a></td>
								<td><%=record.getWinCnt() %></td>
								<td><%=record.getMatchCnt()-record.getWinCnt()%></td>
								<td><%=String.format("%1.3f",(double)record.getWinCnt()/record.getMatchCnt())%></td>
								<td><%=String.format("%2.2f",record.getPointsScored()) %></td>
								<td><%=String.format("%2.2f",record.getPointsAllowed()) %></td>
								<td><%=record.getHandycap() %></td>
							</tr>
						<%}
					} %>
				</tbody>
			</table>
		</div>
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

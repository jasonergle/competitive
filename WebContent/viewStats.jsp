<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.mgr.*"%>
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
		<jsp:include page="navbar.jsp"></jsp:include>
		<div>Stats For <strong><%=model.getTargetLabel() %></strong></div>
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
					<td><%=model.getWonMatchCnt()%></td>
					<td><%=model.getLostMatchCnt()%></td>
					<td><%=String.format("%1.2f",model.getWinPerc())%></td>
					<td><%=String.format("%2.2f",model.getScoredAndAllowed().get("scored")/((double)model.getWonMatchCnt()+model.getLostMatchCnt())) %></td>
					<td><%=String.format("%2.2f",model.getScoredAndAllowed().get("allowed")/((double)model.getWonMatchCnt()+model.getLostMatchCnt())) %></td>
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
				<%for(String opponent: model.getOpponentInfo().keySet()){ 
					VersusRecord record = model.getOpponentInfo().get(opponent);
				%>
					<tr>
						<td><a class="" href="viewStats.jsp?<%=record.getOpponentUrlArg() %>"> <%=opponent %></a></td>
						<td><%=record.getWinCnt() %></td>
						<td><%=record.getMatchCnt()-record.getWinCnt()%></td>
						<td><%=String.format("%1.3f",(double)record.getWinCnt()/record.getMatchCnt())%></td>
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

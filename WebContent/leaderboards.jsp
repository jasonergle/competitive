<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.game.*"%>
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
			<%for(Leaderboard board: model.getLeaderboards()){ %>
				<h3><strong><%=board.getTitle() %></strong></h3>
				<table id="statTablePlayers" class="statTable table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Wins</th>
							<th>Losses</th>
							<th>Win %</th>
						</tr>
					</thead>
					<tbody>
						<%for(LeaderboardRow leaderRow : board.getRows()){%>
						<tr>
							<td><a class="" href="viewStats.jsp?<%=leaderRow.getUrlParam() %>"> <%=leaderRow.getLabel() %></a></td>
							<td><%=leaderRow.getWins() %></td>
							<td><%=leaderRow.getLosses() %></td>
							<td><%=leaderRow.getWinPercentage() %></td>
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
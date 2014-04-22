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
				<h5><%=board.getTitle() %></h5>
				<table id="statTablePlayers" class="statTable table table-striped">
					<thead>
						<tr>
							<th>Rank</th>
							<th>Name</th>
							<th>W/L</th>
							<th>Win %</th>
							<th>Score</th>
						</tr>
					</thead>
					<tbody>
						<%int rank =1;
						for(LeaderboardRow leaderRow : board.getRows()){%>
						<tr>
							<td><%=rank++ %></td>
							<td><a class="" href="viewStats.jsp?<%=leaderRow.getUrlParam() %>"> <%=leaderRow.getLabel() %></a></td>
							<td><%=leaderRow.getWins() %>/<%=leaderRow.getLosses() %></td>
							<td><%=String.format("%1.3f",leaderRow.getWinPercentage()) %></td>
							<td><%=String.format("%1.3f",leaderRow.getScore()) %></td>
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
					aaSorting:[[0, "asc"]] });
	
});
</script>
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
		<title>Head to Head - Leaderboards</title>
		<jsp:include page="/header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="/navbar.jsp"></jsp:include>
			<%for(Leaderboard board: model.getLeaderboards()){ %>
				<h5><%=board.getTitle() %></h5>
				<table id="statTablePlayers" class="statTable table table-striped">
					<thead>
						<tr>
							<th style="width:40px;">Rank</th>
							<th>Name</th>
							<th style="width:50px;">W/L</th>
							<th style="width:50px;">Win%</th>
							<th style="width:50px;">Score</th>
						</tr>
					</thead>
					<tbody>
						<%int rank =1;
						for(LeaderboardRow leaderRow : board.getRows()){%>
						<tr>
							<td><%=rank++ %> </td>
							<td><a class="" href="<%=request.getContextPath() %>/viewStats.jsp?<%=leaderRow.getUrlParam() %>"> <%=leaderRow.getLabel() %></a>
								<% if (leaderRow.getLosses() == 0) { %>
								<i class="text-danger fa fa-fire-extinguisher"></i>
								<% } %>
							</td>
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
		
		<script>
		$( document ).ready(function() {
			
			$('.statTable').DataTable({
							bFilter: false, 
							bLengthChange: false,
							bPaginate: true,
							aaSorting:[[0, "asc"]] });
			
		});
		
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
			  ga('create', 'UA-50481107-1', 'headtohead.us');
			  ga('require', 'displayfeatures');
			  ga('send', 'pageview');
		</script>
	</body>
</html>



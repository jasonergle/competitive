<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="java.util.*"%>
<%
ViewMatchesJspModel model = new ViewMatchesJspModel(request);
%>
<html>
	<head>
		<title>Head 2 Head Score Tracking - Match - Page</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<h3>Singles Data</h3>
			<table id="singlesTable" class="table table-striped">
				<thead>
					<tr>
						<th>Date</th>
						<th>Game</th>
						<th>Winner</th>
						<th>Loser</th>
						<th>Creator</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					for(VersusMatch match : model.getMatches()){
					%>
					<tr>
						<td><%=VersusMatchManager.getFormattedDate(match.getMatchDate())%></td>
						<td><%=match.getGame().getName()%></td>
						<td>
							<a class="" href="viewStats.jsp?player=<%=1%>"> 
								<%=model.getWinnerLabel(match)%>
							</a>
						</td>
						<td>
							<a class="" href="viewStats.jsp?player=-1>"> 
								<%=model.getLoserLabel(match)%>
							</a>
						</td>
						<td>
							<a class="" href="viewStats.jsp?player=<%=match.getCreator().getId() %>"> 
								<%=PlayerManager.getNameForPlayer(match.getCreator())%>
							</a>
						</td>
						<td><a class="btn btn-danger btn-xs" href="<%=request.getContextPath() %>/deleteMatch?id=<%=match.getId()%>">Delete</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
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
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="java.util.*"%>
<%
PlayerManager pMgr = new PlayerManager(request);
VersusMatchManager mMgr = new VersusMatchManager(request);
Set<VersusMatch> matches = mMgr.getMatchesForCurrentLeague();
%>
<html>
	<head>
		<title>Pong Score Tracking - Main Page</title>
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
						<th>Score</th>
						<th>Creator</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					for(VersusMatch match : matches){
					%>
					<tr>
						<td><%=VersusMatchManager.getFormattedDate(match.getMatchDate())%></td>
						<td><%=match.getGame().getName()%></td>
						<td>
							<a class="" href="viewStats.jsp?player=<%=VersusMatchManager.getWinningEntry(match).getId()%>"> 
								<%=mMgr.getLabelForEntry(VersusMatchManager.getWinningEntry(match)) %>
							</a>
						</td>
						<td>
							<a class="" href="viewStats.jsp?player=-1>"> 
								Loser Set
							</a>
						</td>
						<td><%=VersusMatchManager.getWinningEntry(match)==null?"N/A":VersusMatchManager.getWinningEntry(match).getScore()%></td>
						<td>
							<a class="" href="viewStats.jsp?player=<%=match.getCreator().getId()%>"> 
								<%=PlayerManager.getNameForPlayer(match.getCreator())%>
							</a>
						</td>
						<td><a class="btn btn-danger btn-xs" href="deleteMatch?id=<%=match.getId()%>">Delete</a></td>
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
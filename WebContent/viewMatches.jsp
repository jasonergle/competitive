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
			<h3>Match Data</h3>
			<table id="singlesTable" class="table table-striped" hidden=true>
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
					<%for(VersusMatch match : model.getMatches()){%>
					<tr>
						<td><%=VersusMatchManager.getFormattedDate(match.getMatchDate())%></td>
						<td><%=match.getGame().getName()%></td>
						<td>
							<%for(VersusEntry entry : model.getWinners(match)){ %>
								<a class="" href="viewStats.jsp?participant=<%=entry.getTeam().getId()%>"> 
									<%=model.getEntryLabel(entry)%>
								</a>
							<%} %>
						</td>
						<td>
							<%for(VersusEntry entry : model.getLosers(match)){ %>
								<a class="" href="viewStats.jsp?participant=<%=entry.getTeam().getId()%>"> 
									<%=model.getEntryLabel(entry)%>
								</a>
							<%} %>
						</td>
						<td>
							<a class="" href="viewStats.jsp?participant=<%=match.getCreator().getId() %>"> 
								<%=match.getCreator().getLastName()%>,<%=match.getCreator().getFirstName()%>
							</a>
						</td>
						<td><%if(model.canDelete(match)){ %><a class="btn btn-danger btn-xs" href="<%=request.getContextPath() %>/deleteMatch?id=<%=match.getId()%>">Delete</a><%} %></td>
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
							iDisplayLength: 50,
							bPaginate: true,
							aaSorting:[[0, "desc"]] });
			$('#singlesTable').show();
		});
	</script>
</html>
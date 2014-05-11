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
		<title>Head To Head - Matches</title>
		<jsp:include page="header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<h5>Match Data <strong><%=model.isHeadToHeadMatches()?model.getTeam1().getName() + " vs. " +model.getTeam2().getName():"" %></strong></h5>
			<table id="matchesTable" 
					class="table table-striped" 
					data-target-team-id='<%=model.isHeadToHeadMatches()?model.getTeam1().getId():"" %>'
					data-is-head-to-head='<%=model.isHeadToHeadMatches() %>'  
					hidden=true>
				<thead>
					<tr>
						<th>Date</th>
						<th>Game</th>
						<th>Winner</th>
						<th>Loser</th>
						<th>Creator</th>
						<%if(!model.isHeadToHeadMatches()){ %>
						<th></th>
						<%} %>
					</tr>
				</thead>
				<tbody>
					<%for(VersusMatch match : model.getMatches()){%>
					<tr class="">
						<td><%=VersusMatchManager.getFormattedDate(match.getMatchDate())%></td>
						<td><%=match.getGame().getName()%></td>
						<td class="winner">
							<%for(VersusEntry entry : model.getWinners(match)){ %>
								<a data-team-id="<%=entry.getTeam().getId()%>" 
									class="" 
									href="viewStats.jsp?participant=<%=entry.getTeam().getId()%>"> 
									<%=model.getEntryLabel(entry)%>
								</a>
							<%} %>
						</td>
						<td class="loser">
							<%for(VersusEntry entry : model.getLosers(match)){ %>
								<a data-team-id="<%=entry.getTeam().getId()%>" 
									class="" href="viewStats.jsp?participant=<%=entry.getTeam().getId()%>"> 
									<%=model.getEntryLabel(entry)%>
								</a>
							<%} %>
						</td>
						<td>
							<a class="" href="viewStats.jsp?participant=<%=match.getCreator().getId() %>"> 
								<%=match.getCreator().getLastName()%>,<%=match.getCreator().getFirstName()%>
							</a>
						</td>
						<%if(!model.isHeadToHeadMatches()){ %>
						<td><%if(model.canDelete(match)){ %><a class="btn btn-danger btn-xs" href="<%=request.getContextPath() %>/deleteMatch?id=<%=match.getId()%>">Delete</a><%} %></td>
						<%} %>
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
			var targetId = $('#matchesTable').attr('data-target-team-id');
			var isHeadToHead = $('#matchesTable').attr('data-is-head-to-head');
			$('#matchesTable tbody tr').each(function(){
				if(isHeadToHead=="true"){
					var winnerId = $('td.winner a[data-team-id]', this).attr('data-team-id');
					if(targetId==winnerId)
						$(this).addClass("success");
					else
						$(this).addClass("danger");
				}
			});
			
			$('#matchesTable').DataTable({
							bFilter: true, 
							bLengthChange: true,
							iDisplayLength: 50,
							bPaginate: true,
							aaSorting:[[0, "desc"]] });
			$('#matchesTable').show();
		});
		
	</script>
</html>
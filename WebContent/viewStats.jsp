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
			<h5>Overall Results</h5>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Type</th>
						<th>Wins</th>
						<th>Losses</th>
						<th>Win %</th>
						<th>PS</th>
						<th>PA</th>
						<th>Handycap</th>
					</tr>
				</thead>
				<tbody>
					<%for(Game game: model.getAllowedGames()){ 
						Map<String, Number> wlData = model.getWonLossData(game);
						if((wlData.get("winCnt").intValue()+wlData.get("lossCnt").intValue())>0){%>
							<tr>
								<td><%=game.getName() %></td>
								<td><%=wlData.get("winCnt") %></td>
								<td><%=wlData.get("lossCnt")%></td>
								<td><%=model.getFormattedWinningPercentage(wlData)%></td>
								<td><%=String.format("%1.3f",wlData.get("ps"))  %></td>
								<td><%=String.format("%1.3f",wlData.get("pa")) %></td>
								<td><%=String.format("%d",(int)(wlData.get("pa").doubleValue()-wlData.get("ps").doubleValue())) %></td>
							</tr>
						<%}
					} %>
				</tbody>
			</table>
			
			<%for(Game game: model.getAllowedGames()){
				Map<Team, Head2HeadRecord> records = model.getOpponentInfo().get(game); 
				if(records.size()>0){%>
					<h5>Head To Head for <%=game.getName() %></h5>
					<table id="h2hTable<%=game.getType() %>" class="table table-striped">
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
							<%for(Team opponent: records.keySet()){ 
								Head2HeadRecord record = records.get(opponent);%>
								<tr>
									<td><a class="" href="<%=request.getContextPath() %>/viewStats.jsp?participant=<%=opponent.getId() %>"> <%=opponent.getName()%></a></td>
									<td><%=record.getWinCnt() %></td>
									<td><%=record.getMatchCnt()-record.getWinCnt()%></td>
									<td><%=String.format("%1.3f",(double)record.getWinCnt()/record.getMatchCnt())%></td>
									<td><%=String.format("%2.2f",record.getPointsScored()) %></td>
									<td><%=String.format("%2.2f",record.getPointsAllowed()) %></td>
									<td><%=record.getHandycap() %></td>
								</tr>
							<%}%>
						</tbody>
					</table>
				<%}
			}%>
		</div>
		<script>
		$( document ).ready(function() {
			
			$('#versusSinglesTable').DataTable({
							bFilter: false, 
							bLengthChange: false,
							bPaginate: false,
							aaSorting:[[1, "desc"],[3,"desc"]] });
		});
		</script>
	</body>
</html>


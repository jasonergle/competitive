<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%
LeaderboardTableJspModel model = new LeaderboardTableJspModel(request);
request.setAttribute("model", model);
%>
<h5>${model.leaderboard.getTitle() }</h5>
<table id="lb-${model.leaderboard.getTitle() }" class="lbTable table table-striped">
	<thead>
		<tr>
			<th style="width: 40px;">Rank</th>
			<th>Name</th>
			<th style="width: 50px;">W/L</th>
			<th style="width: 50px;">Win%</th>
			<th style="width: 50px;">Score</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="row" items="${model.leaderboard.rows}">
			<tr>
				<td>${row.rank}</td>
				<td>
					<a href="${pageContext.servletContext.contextPath}/viewStats.jsp?participant=${row.teamId}">
						${row.getLabel()} 
					</a>
				</td>
				<td>${row.getWins()}/${row.getLosses()}</td>
				<td><fmt:formatNumber type="number" maxIntegerDigits="1" value="${row.getWinPercentage()}" /></td>
				<td><fmt:formatNumber type="number" value="${row.getScore()}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

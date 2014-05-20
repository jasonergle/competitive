<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.game.*"%>
<%
	HomeJspModel model = new HomeJspModel(request);
	request.setAttribute("model", model);
%>
<html>
<head>
<title>Head to Head - Home</title>
<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/navbar.jsp"></jsp:include>

	<div class="container">
		<c:choose>
			<c:when test="${!model.hasCurrentLeague()}">
		     You must be a member of a League to do anything interesting.  You can <ahref'">join
				an existing league</a> or you can <a
					href="${pageContext.servletContext.contextPath}/jsp/league/editLeague.jsp?action=CREATE">create
					a new league</a>. 
			</c:when>

			<c:otherwise>
				<c:forEach var="board" items="${model.getLeaderboards()}">
					<h5>${board.getTitle() }</h5>
					<table id="statTablePlayers" class="statTable table table-striped">
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
							<c:forEach var="row" items="${board.rows}">
								<tr>
									<td>${row.rank}</td>
									<td><a class=""
										href="${pageContext.servletContext.contextPath}/viewStats.jsp?${row.getUrlParam()}">
											${row.getLabel()} </a></td>
									<td>${row.getWins()}/${row.getLosses()}</td>
									<td><fmt:formatNumber type="number" maxIntegerDigits="1"
											value="${row.getWinPercentage()}" /></td>
									<td><fmt:formatNumber type="number" maxIntegerDigits="1"
											value="${row.getScore()}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br />

				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
		$(document).ready(function() {

			$('.statTable').DataTable({
				bFilter : false,
				bLengthChange : false,
				bPaginate : true,
				aaSorting : [ [ 0, "asc" ] ]
			});

		});

		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-50481107-1', 'headtohead.us');
		ga('require', 'displayfeatures');
		ga('send', 'pageview');
	</script>
</body>
</html>



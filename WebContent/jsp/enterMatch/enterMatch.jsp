<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
EnterMatchJspModel model = new EnterMatchJspModel(request);
request.setAttribute("model", model);
request.setAttribute("contextPath", request.getContextPath());
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/header.jsp"></jsp:include>
		<title>Head To Head - Enter Match</title>
	</head>
	<body>
		<div class="container">
			<jsp:include page="/navbar.jsp"></jsp:include>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<c:forEach var="game" items="${model.getAllowedGames()}" varStatus="status">
				<li class="${status.index == 0 ? 'active' : ''} ">
				<a href="#tab_${game.type}" data-toggle="tab">${game.name}</a></li>
				</c:forEach>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<c:forEach var="game" items="${model.getAllowedGames()}" varStatus="status">
					<%@include file="n_vs_n.jsp" %>
				</c:forEach>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/enterMatch.js"></script>
</html>

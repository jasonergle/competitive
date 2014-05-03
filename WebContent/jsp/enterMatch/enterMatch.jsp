<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
EnterMatchJspModel model = new EnterMatchJspModel(request);
request.setAttribute("model", model);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/header.jsp"></jsp:include>
		<title>Head To Head - Enter Match</title>
	</head>
	<body>
		<div class="container">
			<jsp:include page="/navbar.jsp"></jsp:include>
			<c:forEach var="game" items="${model.getAllowedGames()}">
				<%@include file="n_vs_n.jsp" %>
			</c:forEach>
		</div>
	</body>
</html>

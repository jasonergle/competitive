<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
EnterMatchJspModel model = new EnterMatchJspModel(request);
request.setAttribute("model", model);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="header.jsp"></jsp:include>
		<title>Head2Head - Enter Match</title>
	</head>
	<body>
		<div class="container">
			<jsp:include page="navbar.jsp"></jsp:include>
			<c:forEach var="game" items="${model.getAllowedGames()}">
			 	 <div data-game-props='${model.getJsonForGame(game)}'>
			 	 
			 	 </div>
			</c:forEach>
			
			<jsp:include page="jsp/enterMatch/ping_pong.jsp"></jsp:include>
			
			<jsp:include page="jsp/enterMatch/ping_pong_doubles.jsp"></jsp:include>
		</div>
	</body>
</html>

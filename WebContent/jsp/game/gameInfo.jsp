<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%
GameInfoJspModel model = new GameInfoJspModel(request);
request.setAttribute("model", model);
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Head To Head - ${model.game.name} Page</title>
		<jsp:include page="/header.jsp"></jsp:include>
		<meta name="description" content="Description of ${model.game.name}">
		<style>
		#footer {
		   position:absolute;
		   bottom:0;
		   width:100%;
		   height:60px;   /* Height of the footer */
		} 
		</style>
	</head>
	<body>
		
		<div class="container">
			<div>
				<a href="${pageContext.servletContext.contextPath}/">
					<img alt="Head to Head Logo" 
						src="${pageContext.servletContext.contextPath}/assets/images/h2hlogo.png" 
						style="height:50px;padding:5px;">
				</a>
			</div>
			<div id="game-info-main">
				<h4>${model.game.name}</h4>
				<div style="padding:10px;box-shadow: 10px 10px 5px #888888;border: 2px solid;border-radius: 15px;">
				${model.game.description }
				</div>
			</div>
			<div id="game-info-bg" style="background-image: url(${pageContext.servletContext.contextPath}${model.game.backgroundImage});"></div>
			<jsp:include page="/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
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
		<style>
			#main {
   position: relative;
}
#bg {
    content : "";
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    background-image: url(${pageContext.servletContext.contextPath}${model.game.backgroundImage});
    width: 100%;
    height: 100%;
    opacity : 0.2;
    z-index: -1;
}
		</style>
	</head>
	<body>
		<div class="container">
			<form><input type="button" value="Back" onClick="history.go(-1);return true;"></form>
			<div id="main">
				<h4>${model.game.name}</h4>
			
				${model.game.description }
			</div>
			<div id="bg">
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</body>
</html>
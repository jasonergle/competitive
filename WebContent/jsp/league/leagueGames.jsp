<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Supported Games</h4>
<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>Team Size</th>
			<th>Enable</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="game" items="${model.getGames()}">
		<tr>
			<td>${game.name }</td>
			<td>${game.teamSize }</td>
			<td>${"" }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<script>

</script>
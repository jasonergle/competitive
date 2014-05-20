<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Supported Games</h4>
<table class="table table-striped">
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
			<td>
				<input type="checkbox" data-gameId="${game.id}" 
						<c:if test="${model.isGameEnabled(game)}">checked="checked"</c:if>>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<script>

</script>
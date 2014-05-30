<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Individual Players</h4>
<table id="singlePlayerTable" class="table table-striped">
	<thead>
		<tr>
			<th>Name</th>
			<th>Associated Login</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="player" items="${model.getSortedPlayersForLeague()}">
			<tr>
				<td>${player.name }</td>
				<td>${player.associatedLogin.firstName } ${player.associatedLogin.lastName }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h4>Teams</h4>
<table>
	<thead>
		<tr>
			<th></th>
		</tr>
	</thead>
</table>

<script>

</script>
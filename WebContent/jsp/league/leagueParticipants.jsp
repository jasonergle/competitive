<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Participant</h4>
<table id="participantTable" class="table table-striped">
	<thead>
		<tr>
			<th>Name</th>
			<th>Size</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="team" items="${model.getSortedTeamsForLeague()}">
			<tr data-team-id="${team.id }">
				<td>${team.name }</td>
				<td>${team.teamPlayers.size() }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
$('#participantTable').DataTable({
	bFilter: true, 
	bLengthChange: false,
	bPaginate: true,
	aaSorting:[[1, "desc"],[0, "desc"]] });
</script>
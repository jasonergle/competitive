<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Associated Logins</h4>
<table id="leagueLoginsTable" class="table table-striped">
	<thead>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Last Login</th>
			<th>Admin</th>
			<th>Enter Scores</th>
			<th>Delete Association</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="leagueLogin" items="${model.getAssociatedLeagueLogins()}">
		<tr>
			<td>${leagueLogin.login.firstName }</td>
			<td>${leagueLogin.login.lastName }</td>
			<td>${leagueLogin.login.login }</td>
			<td>${leagueLogin.login.lastLoginDate }</td>
			<td>
				<span class="hidden">${leagueLogin.isAdmin}</span>
				<input class="admin-checkbox" type="checkbox" data-league-login-id="${leagueLogin.id}" 
					<c:if test="${leagueLogin.isAdmin}">checked="checked"</c:if>>
			</td>
			<td>
				<span class="hidden">${leagueLogin.canEnterScores}</span>
				<input class="enter-scores-checkbox" type="checkbox" data-league-login-id="${leagueLogin.id}" 
					<c:if test="${leagueLogin.canEnterScores}">checked="checked"</c:if>>
			</td>
			<td><button class="btn btn-danger delete-login" data-league-login-id="${leagueLogin.id}">Delete</button></td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<script>
$(document).ready(function() {
	var $leagueGamesTable = $('#leagueGamesTable');
	
	var gamesCheckbox = $('.games-checkbox', $leagueGamesTable);
	gamesCheckbox.on("click", function(){
		var $checkbox = $(this);
		var isChecked = $checkbox.is(':checked');
		var gameId = $checkbox.attr("data-game-id");
		$checkbox.attr("disabled", true);
		
		$.ajax({
			type: "POST",
			url: $.erglesoft.contextPath+ '/updateLeagueGameAssociation',
			data: { "gameId" : gameId,
					"toAdd": isChecked},
			dataType: 'json',
			success: function(data) {
				if(!data.success){
					$checkbox.prop( "checked", !$checkbox.prop( "checked") );
				}
				$checkbox.removeAttr("disabled");
			}});
	});
	
	$('#leagueLoginsTable').DataTable({
					bFilter: true, 
					bLengthChange: false,
					bPaginate: true,
					aaSorting:[[2, "desc"],[1, "desc"]] });
});
</script>
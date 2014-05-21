<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
			<td><fmt:formatDate value="${model.getAsDate(leagueLogin.login.lastLoginDate)}" pattern="MM/dd/yyyy hh:mm aa"/></td>
			<td>
				<span class="hidden">${leagueLogin.isAdmin}</span>
				<input class="league-login-checkbox" type="checkbox" 
					data-league-login-id="${leagueLogin.id}" data-permission-type="ADMIN"
					<c:if test="${leagueLogin.isAdmin}">checked="checked"</c:if>>
			</td>
			<td>
				<span class="hidden">${leagueLogin.canEnterScores}</span>
				<input class="league-login-checkbox" type="checkbox" 
				data-league-login-id="${leagueLogin.id}" data-permission-type="ENTER_SCORES"
					<c:if test="${leagueLogin.canEnterScores}">checked="checked"</c:if>>
			</td>
			<td>
				<button class="btn btn-xs btn-danger delete-login" data-league-login-id="${leagueLogin.id}">
					Delete
				</button>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<script>
$(document).ready(function() {
	var $leagueLoginsTable = $('#leagueLoginsTable');
	
	var llCheckbox = $('.league-login-checkbox', $leagueLoginsTable);
	llCheckbox.on("click", function(){
		var $checkbox = $(this);
		var isChecked = $checkbox.is(':checked');
		var leagueLoginId = $checkbox.attr("data-league-login-id");
		var permissionType = $checkbox.attr("data-permission-type");
		$checkbox.attr("disabled", true);
		
		$.ajax({
			type: "POST",
			url: $.erglesoft.contextPath+ '/updateLeagueLogins',
			data: { leagueLoginId : leagueLoginId,
					toAdd: isChecked,
					permissionType: permissionType},
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
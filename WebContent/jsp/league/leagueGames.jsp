<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Supported Games</h4>
<table id="leagueGamesTable" class="table table-striped">
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
				<input class="games-checkbox" type="checkbox" data-game-id="${game.id}" 
						<c:if test="${model.isGameEnabled(game)}">checked="checked"</c:if>>
			</td>
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
});
</script>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.erglesoft.pong.dbo.*"%>
<%@page import="com.erglesoft.pong.mgr.*"%>
<%@page import="java.util.*"%>
<%
PlayerManager pMgr = new PlayerManager(request);
Set<Player> players = pMgr.getAllPlayersForCurrentLeague();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="header.jsp"></jsp:include>
		<title>Pong Score Tracking - Enter Match</title>
	</head>
	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<form role="form" action="submitMatch">
			<div class="container">
				<h3>Singles Match</h3>
				<div class="row">
					<div class="form-group col-sm-3">
						<label for="winners"><i class="fa fa-thumbs-o-up"></i> Winner(s)</label>
						<select name="winner" class="form-control pmPlayer1">
							<option value="-1"></option>
							<%for(Player p: players){ %>
							<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
							<%} %>
						</select>
						<select name="winner2" class="form-control pmPlayer2">
							<option value="-1">N/A</option>
							<%for(Player p: players){ %>
							<option value="<%=p.getId() %>"><%=PlayerManager.getNameForPlayer(p) %></option>
							<%} %>
						</select>
						<label for="winners">Winner Score</label>
						<input id="winnerScore" type="number" name="winnerScore" size="2" min="0" max="99" value="21"/>
					</div>

					<div class="form-group col-sm-3">
						<label for="losers"><i class="fa fa-thumbs-o-down"></i> Loser(s)</label>
						<select name="loser" class="form-control pmPlayer1">
							<option value="-1"></option>
							<%for(Player p: players){ %>
							<option value="<%=p.getId() %>"><%=PlayerManager.getNameForPlayer(p) %></option>
							<%} %>
						</select>
						<select name="loser2" class="form-control pmPlayer2">
							<option value="-1">N/A</option>
							<%for(Player p: players){ %>
							<option value="<%=p.getId() %>"><%=PlayerManager.getNameForPlayer(p)%></option>
							<%} %>
						</select>
						<label for="loserScore">Loser Score</label>
						<input id="loserScore" type="number" name="loserScore" size="2" min="0" max="99" value=""/>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<script>

		$(document).ready(function() {
			var DisableOtherGuys = function(playerVal, $otherPlayer) {
				// Reset the other box if already selected this user.
				if ($otherPlayer.val() == playerVal)
					$otherPlayer.val('-1');
				// Clear disabled state.
				$otherPlayer
					.find('option')
					.prop("disabled", false);
				// Disable the chosen player from second player options
				$otherPlayer
					.find('option[value=' + playerVal + ']')
					.prop("disabled", true);
			};

			$('body').on('change', '.pmPlayer1', function() {
				var player = $(this).val()
				, $otherPlayer = $(this).siblings('.pmPlayer2');
				DisableOtherGuys(player, $otherPlayer);
			});
		});
	</script>

	</body>
</html>

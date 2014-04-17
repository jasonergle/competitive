<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%
EnterMatchJspModel model = (EnterMatchJspModel)request.getAttribute("model");
%>
<form role="form" action="submitMatch">
	<h3>Ping Pong Match</h3>
	<div class="row">
		<div class="form-group col-sm-3">
			<label for="winners"><i class="fa fa-thumbs-o-up"></i> Winner(s)</label>
			<select id="winnerSelect" name="winner" class="form-control pmPlayer1">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<select id="winnerSelect2" name="winner2" class="form-control pmPlayer2">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"><%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="winners">Winner Score</label>
			<input id="winnerScore" type="number" name="winnerScore" size="2" min="0" max="99" value="21"/>
		</div>

		<div class="form-group col-sm-3">
			<label for="losers"><i class="fa fa-thumbs-o-down"></i> Loser(s)</label>
			<select id="loserSelect" name="loser" class="form-control pmPlayer1">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"><%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<select id="loserSelect2" name="loser2" class="form-control pmPlayer2">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
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
</form>
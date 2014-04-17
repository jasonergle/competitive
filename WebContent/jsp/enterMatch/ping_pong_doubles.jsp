<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%
EnterMatchJspModel model = (EnterMatchJspModel)request.getAttribute("model");
%>
<form role="form" action="submitMatch">
	<h3>Ping Pong Doubles</h3>
	<div class="row">
		<div class="form-group col-sm-3">
			<label for="entry1"><i class="fa fa-thumbs-o-up"></i> Player 1</label>
			<select id="entry1" name="entry1" class="form-control pmPlayer1">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="entry1">Score</label>
			<input id="score1" type="number" name="score1" size="2" min="0" max="22" value="0"/>
		</div>

		<div class="form-group col-sm-3">
			<label for="entry2"><i class="fa fa-thumbs-o-up"></i> Player 2</label>
			<select id="entry2" name="entry2" class="form-control pmPlayer2">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="entry2">Score</label>
			<input id="score2" type="number" name="score2" size="2" min="0" max="22" value="0"/>
		</div>
	</div>
	<input type="hidden" name="gameType" value="PING_PONG"/>
	<div class="row">
		<div class="form-group col-md-3">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>
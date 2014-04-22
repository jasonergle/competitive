<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%
EnterMatchJspModel model = (EnterMatchJspModel)request.getAttribute("model");
%>
<form id="pingPongForm" role="form" action="submitMatch" method="post">
	<h3>Ping Pong Match</h3>
	<div class="row">
		<div class="form-group col-sm-3">
			<label for="entry1"><i class="fa fa-thumbs-o-up"></i> Player 1</label>
			<select id="pingPongEntry1" name="entry1" class="form-control pingPongEntry">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="entry1">Score</label>
			<input id="pingPongScore1" class="score" style="min-width: 70px;" type="number" name="score1" size="2" min="0" max="22" value="0"/>
			<div class="btn btn-success setMax">Set Max</div>
		</div>

		<div class="form-group col-sm-3">
			<label for="entry2"><i class="fa fa-thumbs-o-up"></i> Player 2</label>
			<select id="pingPongEntry2" name="entry2" class="form-control pingPongEntry">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="entry2">Score</label>
			<input id="pingPongScore2" class="score" style="min-width: 70px;" type="number" name="score2" size="2" min="0" max="22" value="0"/>
			<div class="btn btn-success setMax">Set Max</div>
		</div>
	</div>
	<input type="hidden" name="gameType" value="PING_PONG"/>
	<div class="row">
		<div class="form-group col-md-3">
			<button id = "pingPongFormSubmit" type="submit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>

<script>
$(document).ready(function() {
	var root = $('#pingPongForm');
	
	var DisableOtherGuys = function(playerVal, $theSelect) {
		// Reset the other box if already selected this user.
		$('.pingPongEntry').not($theSelect).each(function(){
			if ($(this).find(":selected").val()== playerVal)
				$(this).val('-1');	
		});
		
		// Disable the chosen player from second player options
		$('.pingPongEntry').not($theSelect).each(function(){
			$(this)
			.find('option[value=' + playerVal + ']')
			.prop("disabled", true);
		});
	};

	$(root).on('change', '.pingPongEntry', function() {
		var player = $(this).val();
		DisableOtherGuys(player, $(this));
		validateForm();
	});
	
	$('.pingPongEntry', root).sort_select_box();
	
	$('.setMax', root).click(function(){
		$(this).prev().val(21);
		$('input.score').not($(this).prev()).val('0');
		validateForm();
	});
	
	$(":input", root).on('input', function(e) {
		 validateForm();
	});
	var validateForm = function(){
		$("#pingPongFormSubmit", root).addClass("btn btn-danger").removeClass("btn-primary").attr("disabled", "disabled");
		var ids = {};
		$('.pingPongEntry').each(function(){
			var id = $(this).val();
			ids[id]=true;
		});
		if(Object.keys(ids).length<2 || ids["-1"]!=undefined)
			return;
		var score1 = parseInt($('#pingPongScore1', root).val());
		var score2 = parseInt($('#pingPongScore2', root).val());
		if(isNaN(score1) || isNaN(score2) || score1 == score2 || score1 > 22 || score2 > 22)
			return;
		$("#pingPongFormSubmit", root).addClass("btn btn-primary").removeClass("btn-danger").removeAttr("disabled");
	};
	validateForm();
});
</script>
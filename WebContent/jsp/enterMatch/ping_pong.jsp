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
			<select id="entry1" name="entry1" class="form-control pmEntry1">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="entry1">Score</label>
			<input id="score1" class="score" type="number" name="score1" size="2" min="0" max="22" value="0"/>
			<div class="btn btn-success setMax">Set Max</div>
		</div>

		<div class="form-group col-sm-3">
			<label for="entry2"><i class="fa fa-thumbs-o-up"></i> Player 2</label>
			<select id="entry2" name="entry2" class="form-control pmEntry2">
				<option value="-1"></option>
				<%for(Player p: model.getPlayers()){ %>
				<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
				<%} %>
			</select>
			<label for="entry2">Score</label>
			<input id="score2" class="score" type="number" name="score2" size="2" min="0" max="22" value="0"/>
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
	var DisableOtherGuys = function(playerVal, $otherSelect) {
		// Reset the other box if already selected this user.
		if ($otherSelect.find(":selected").val()== playerVal)
			$otherSelect.val('-1');
		
		// Clear disabled state.
		$otherSelect
			.find('option')
			.prop("disabled", false);
		
		// Disable the chosen player from second player options
		$otherSelect
			.find('option[value=' + playerVal + ']')
			.prop("disabled", true);
	};

	$('body').on('change', '#entry1', function() {
		var player = $(this).val();
		DisableOtherGuys(player, $('#entry2'));
	});
	$('body').on('change', '#entry2', function() {
		var player = $(this).val();
		DisableOtherGuys(player, $('#entry1'));
	});
	
	$('#entry1').sort_select_box();
	$('#entry2').sort_select_box();
	
	$('.setMax').click(function(){
		$(this).prev().val(21);
		$('input.score').not($(this).prev()).val('0');
		validateForm();
	});
	
	$("#pingPongForm :input").change(function() {
		 validateForm();
	});
	var validateForm = function(){
		$("#pingPongFormSubmit").addClass("btn btn-danger").removeClass("btn-primary").attr("disabled", "disabled");
		var entry1 = parseInt($('#entry1').val());
		var entry2 = parseInt($('#entry2').val());
		if(entry1==-1 || entry2==-1 || entry1 == entry2)
			return;
		var score1 = parseInt($('#score1').val());
		var score2 = parseInt($('#score2').val());
		if(isNaN(score1) || isNaN(score2) || score1 == score2 || score1 > 22 || score2 > 22)
			return;
		$("#pingPongFormSubmit").addClass("btn btn-primary").removeClass("btn-danger").removeAttr("disabled");
	};
	validateForm();
});
</script>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%
EnterMatchJspModel model = (EnterMatchJspModel)request.getAttribute("model");
%>
<form id="pingPongDoublesForm" role="form" action="submitMatch" method="post">
	<input type="hidden" name="gameType" value="PING_PONG_DOUBLES"/>

	<h3>Ping Pong Doubles Match</h3>

	<div class="row">  <!-- p1 row -->
		<div class="col-sm-3">
			<label for="entry1player1"><i class="fa fa-thumbs-o-up"></i> Team 1</label>
			<div class="row">
				<div class=" col-sm-12">
					<select id="pingPongDoublesEntry1player1" name="entry1player1" class="form-control pingPongDoublesEntry">
						<option value="-1"></option>
						<%for(Player p: model.getPlayers()){ %>
						<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
						<%} %>
					</select>
					<select id="pingPongDoublesEntry1player2" name="entry1player2" class="form-control pingPongDoublesEntry">
						<option value="-1"></option>
						<%for(Player p: model.getPlayers()){ %>
						<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
						<%} %>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<label for="entry1">Score</label>
			<div class="row">
				<div class="col-xs-3 col-sm-2">
					<input id="pingPongDoublesScore1" class="score form-control" style="min-width: 70px;" type="number" name="score1" size="2" min="0" max="22" value="0"/>
				</div>
				<div class="col-xs-1">
					<button type="button" class="btn btn-sm btn-success setMax" title="Set Max"><i class="fa fa-lg fa-check-square-o"></i></button>
				</div>
			</div>
		</div>
	</div><!-- p1 row end -->

	<div class="row">  <!-- p2 row -->
		<div class="col-sm-3">
			<label for="entry2player1"><i class="fa fa-thumbs-o-up"></i> Team 2</label>
			<div class="row">
				<div class=" col-sm-12">
					<select id="pingPongDoublesEntry2player1" name="entry2player1" class="form-control pingPongDoublesEntry">
						<option value="-1"></option>
						<%for(Player p: model.getPlayers()){ %>
						<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
						<%} %>
					</select>
					<select id="pingPongDoublesEntry2player2" name="entry2player2" class="form-control pingPongDoublesEntry">
						<option value="-1"></option>
						<%for(Player p: model.getPlayers()){ %>
						<option value="<%=p.getId() %>"> <%=PlayerManager.getNameForPlayer(p) %></option>
						<%} %>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<label for="entry2">Score</label>
			<div class="row">
				<div class="col-xs-3 col-sm-2">
					<input id="pingPongDoublesScore2" class="score form-control" style="min-width: 70px;" type="number" name="score2" size="2" min="0" max="22" value="0"/>
				</div>
				<div class="col-xs-1">
					<button type="button" class="btn btn-sm btn-success setMax" title="Set Max"><i class="fa fa-lg fa-check-square-o"></i></button>
				</div>
			</div>
		</div>
	</div><!-- p2 row end -->

	<div class="row">
		<div class="form-group col-md-3">
			<button id = "pingPongDoublesFormSubmit" type="submit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>

<script>
$(document).ready(function() {
	var $root = $('#pingPongDoublesForm');
	
	$root.on('change', '.pingPongDoublesEntry', function() {
		validateForm();
	});
	
	$('.pingPongDoublesEntry', $root)
		.makeUniqueChoice()
		.sort_select_box();
	
	$('.setMax', $root).click(function(){
		var $rowScore = $(this).closest('.row').find('.score');
		$rowScore.val('21');
		validateForm();
		return false;
	});
	
	$(":input", $root).on('input', function(e) {
		 validateForm();
	});
	var validateForm = function(){
		$("#pingPongDoublesFormSubmit", $root).addClass("btn btn-danger").removeClass("btn-primary").attr("disabled", "disabled");
		var ids = {};
		$('.pingPongDoublesEntry').each(function(){
			var id = $(this).val();
			ids[id]=true;
		});
		if(Object.keys(ids).length<4 || ids["-1"]!=undefined)
			return;
		var score1 = parseInt($('#pingPongDoublesScore1', $root).val());
		var score2 = parseInt($('#pingPongDoublesScore2', $root).val());
		if(isNaN(score1) || isNaN(score2) || score1 == score2 || score1 > 22 || score2 > 22)
			return;
		$("#pingPongDoublesFormSubmit", $root).addClass("btn btn-primary").removeClass("btn-danger").removeAttr("disabled");
	};
	validateForm();
});
</script>

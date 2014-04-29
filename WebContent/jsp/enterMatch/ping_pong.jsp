<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.mgr.*"%>
<%EnterMatchJspModel model = (EnterMatchJspModel)request.getAttribute("model");%>
<form id="pingPongForm" role="form" action="submitMatch" method="post">
	<input type="hidden" name="gameType" value="PING_PONG"/>

	<h3>Ping Pong Match</h3>

	<div class="row">  <!-- p1 row -->
		<div class="col-sm-3">
			<label for="entry1"><i class="fa fa-thumbs-o-up"></i> Player 1</label>
			<div class="row">
				<div class=" col-sm-12">
					<select id="pingPongEntry1" name="entry1" class="form-control pingPongEntry">
						<option value="-1"></option>
						<%for(Team p: model.getTeams()){ %>
						<option value="<%=p.getId() %>"> <%=p.getName()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<label for="entry1">Score</label>
			<div class="row">
				<div class="col-xs-3 col-sm-2">
					<input id="pingPongScore1" class="score form-control" type="number" name="score1" size="2" min="0" max="102" value="0"/>
				</div>
				<div class="col-xs-1">
					<button type="button" class="btn btn-sm btn-success setMax" title="Set Max"><i class="fa fa-lg fa-check-square-o"></i></button>
				</div>
			</div>
		</div>
	</div><!-- p1 row end -->


	<div class="row"> <!-- p2 row -->
		<div class="col-sm-3">
			<label for="entry1"><i class="fa fa-thumbs-o-up"></i> Player 2</label>
			<div class="row">
				<div class=" col-sm-12">
					<select id="pingPongEntry2" name="entry2" class="form-control pingPongEntry">
						<option value="-1"></option>
						<%for(Team p: model.getTeams()){%>
							<option value="<%=p.getId()%>"> <%=p.getName()%></option>
						<%} %>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<label for="entry2">Score</label>
			<div class="row">
				<div class="col-xs-3 col-sm-2">
					<input id="pingPongScore2" class="score form-control" type="number" name="score2" size="2" min="0" max="102" value="0"/>
				</div>
				<div class="col-xs-1">
					<button type="button" class="btn btn-sm btn-success setMax" title="Set Max"><i class="fa fa-lg fa-check-square-o"></i></button>
				</div>
			</div>
		</div>
	</div><!-- p2 row end -->
	<div class="row">
		<div class="form-group col-md-3">
			<button type="button" id="pingPongFormSubmit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>

<script>
$(document).ready(function() {
	var $root = $('#pingPongForm')
		, ajaxXHR = null;

	$root.on('click', '#pingPongFormSubmit', function(event) {
		event.preventDefault();
		var url = "submitMatch"
			, postData = $root.serialize();

		ajaxXHR = $.ajax({
				type: "POST",
				url: url,
				data: postData,
				dataType: 'json',
				error: function(data) {
					console.info("fail");
					// Not sure why this thinks it fails.. but returns ok.
					if (data.status === 200) {
						appendScoreInputs();
						lockScores();
					}
				},
				success: function(data) {
					//$.publish("scoresSaved", [data, widget]);
					// Add new 'col' of scores and lock current ones
					appendScoreInputs();
					lockScores();
				}
			})
			.always(function () {
				ajaxXHR = null;
			});

		return false;
	});

	var lockScores = function() {
		var $scores = $('#pingPongScore1_orig, #pingPongScore2_orig', $root);
		$scores
			.addClass("has-success")
			.removeClass("score")
			.attr("disabled",true)
			.closest('div')
				.addClass("has-success");
	};
	var appendScoreInputs = function() {
		var $scores = $('#pingPongScore1, #pingPongScore2', $root);
		$scores.each( function () {
			var id = $(this).attr('id')
			, $container = $(this).closest('div')
			, $newInputContainer = $container.clone();
			$(this).attr('id', id + "_orig");
			$newInputContainer.insertAfter($container);
		});
	};

	$root.on('change', '.pingPongEntry', function() {
		validateForm();
	});
	
	$('.pingPongEntry', $root)
		.makeUniqueChoice()
		.sort_select_box();
	
	$('.setMax', $root).click(function(){
		var $rowScore = $(this).closest('.row').find('.score');
		$rowScore.val('21');
		validateForm();
		return false;
	});
	
	$($root).on('input', ':input', function(e) {
		 validateForm();
	});
	var validateForm = function(){
		$("#pingPongFormSubmit", $root).addClass("btn btn-danger").removeClass("btn-primary").attr("disabled", "disabled");
		var ids = {};
		$('.pingPongEntry').each(function(){
			var id = $(this).val();
			ids[id]=true;
		});
		if(Object.keys(ids).length<2 || ids["-1"]!=undefined)
			return;
		var score1 = parseInt($('#pingPongScore1', $root).val());
		var score2 = parseInt($('#pingPongScore2', $root).val());
		if(isNaN(score1) || isNaN(score2) || score1 == score2 || score1 > 22 || score2 > 22)
			return;
		$("#pingPongFormSubmit", $root).addClass("btn btn-primary").removeClass("btn-danger").removeAttr("disabled");
	};
	validateForm();
});
</script>

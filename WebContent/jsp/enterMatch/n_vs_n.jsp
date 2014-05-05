<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="entryForm${game.type}" role="form" action="${request.contextPath}/submitMatch" method="post">
	<input type="hidden" name="gameType" value="${game.type}"/>
	<h5>${game.name}</h5>
	<div class="hidden game" data-game-json='${model.getJsonForGame(game)}'></div>
	<c:forEach var="entryNum" begin="1" end="2">
		<div class="row">  <!-- p1 row -->
			<div class="col-sm-3">
				<label for="entry${entryNum}"><i class="fa fa-thumbs-o-up"></i> Entry ${entryNum}</label>
				<div class="row">
					<div class=" col-sm-12">
						<c:choose>
							<c:when test="${game.usesTeams}">
    							<select id="entry${entryNum}_1" name="entry${entryNum}_1" 
										class="form-control matchEntry">
									<option value="-1"></option>  									
									<c:forEach var="team" items="${model.getTeamsForGame(game)}">
										<option value="${team.id}">${team.name}</option>
									</c:forEach>
								</select>
							</c:when>
		
							<c:otherwise>
								<c:forEach var="cnt" begin="1" end="${game.teamSize}">
									<select id="entry${entryNum}_${cnt}" name="entry${entryNum}_${cnt}" 
											class="form-control matchEntry">
										<option value="-1"></option>  									
										<c:forEach var="player" items="${model.players}">
											<option value="${player.id}">${player.name}</option>
										</c:forEach>
									</select>
								</c:forEach>   
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<label for="entry${entryNum}">Score</label>
				<div class="row">
					<div class="col-xs-3 col-sm-2">
						<input id="score${entryNum}${game.type}" class="score form-control" 
								type="number" name="score${entryNum}" size="2" min="0" max="${game.maxScore}" value="0"/>
					</div>
					<div class="col-xs-1">
						<button type="button" class="btn btn-sm btn-success setMax" title="Set Max">
							<i class="fa fa-lg fa-check-square-o"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<div class="row">
		<div class="form-group col-md-3">
			<button type="button" id="formSubmit${game.type}" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>

<script>
$(document).ready(function() {
	var id = '#entryForm${game.type}';
	var $root = $(id)
	var ajaxXHR = null;
	var gameData = JSON.parse($($('div.game', $root)).attr('data-game-json'))

	$root.on('click', '#formSubmit${game.type}', function(event) {
		event.preventDefault();
		var url = "<%=request.getContextPath()%>/submitMatch", postData = $root.serialize();
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
		var $scores = $('#score1${game.type}_orig, #score2${game.type}_orig', $root);
		$scores
			.addClass("has-success")
			.removeClass("score")
			.attr("disabled",true)
			.closest('div')
				.addClass("has-success");
	};
	var appendScoreInputs = function() {
		var $scores = $('#score1${game.type}, #score2${game.type}', $root);
		$scores.each( function () {
			var id = $(this).attr('id')
			, $container = $(this).closest('div')
			, $newInputContainer = $container.clone();
			$(this).attr('id', id + "_orig");
			$newInputContainer.insertAfter($container);
		});
	};
	$root.on('change', '.matchEntry', function() {
		validateForm();
	});
	
	$('.matchEntry', $root)
		.makeUniqueChoice();
	
	$('.setMax', $root).click(function(){
		var $rowScore = $(this).closest('.row').find('.score');
		$rowScore.val(gameData.maxScore);
		validateForm();
		return false;
	});
	
	$($root).on('input', ':input', function(e) {
		 validateForm();
	});
	
	var validateForm = function(){
		$("#formSubmit${game.type}", $root).addClass("btn btn-danger").removeClass("btn-primary").attr("disabled", "disabled");
		var ids = {};
		$('.matchEntry', $root).each(function(){
			var id = $(this).val();
			ids[id]=true;
		});
		if(Object.keys(ids).length<$('.matchEntry', $root).size() || ids["-1"]!=undefined)
			return;
		var score1 = parseInt($('#score1'+gameData.type, $root).val());
		var score2 = parseInt($('#score2'+gameData.type, $root).val());
		if(isNaN(score1) || isNaN(score2) || score1 == score2 || score1 > gameData.absoluteMaxScore || score2 > gameData.absoluteMaxScore)
			return;
		$("#formSubmit"+gameData.type, $root).addClass("btn btn-primary").removeClass("btn-danger").removeAttr("disabled");
	};
	validateForm();
});
</script>

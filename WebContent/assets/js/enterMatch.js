$(document).ready(function() {
	var $forms = $('form[data-game-type]');
	$forms.each( function () {
		var $root = $(this)
			, gameType = $root.data('gameType')
			, ajaxXHR = null
			, gameData = $root.data('gameJson');

		$root.on('click', 'button[name=formSubmit]', function(event) {
			event.preventDefault();
			var url = $root.attr("action")
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
						}
					},
					success: function(data) {
						//$.publish("scoresSaved", [data, widget]);
						// Add new 'col' of scores and lock current ones
						appendScoreInputs();
					}
				})
				.always(function () {
					ajaxXHR = null;
				});

			return false;
		});

		var lockScores = function($scores) {
			$scores
				.addClass("has-success")
				.removeClass("score")
				.attr("disabled",true)
				.closest('div')
					.addClass("has-success");
		};
		var appendScoreInputs = function() {
			var $scores = $('input.score', $root);

			$scores.each( function () {
				var name = $(this).attr('name')
				, $container = $(this).closest('div')
				, $newInputContainer = $container.clone();
				$(this).attr('name', name + "_orig");
				$newInputContainer.insertAfter($container);
				$newInputContainer.find('input.score').val("");
			});
			lockScores($scores);
		};
		
		$('.matchEntry', $root)
			.makeUniqueChoice();
		
		$('.setMax', $root).click(function(){
			var $scores = $('input.score', $root)
				, $rowScore = $(this).closest('.row').find('input.score');

			// Hacky way to make a quick deuce score.
			if ($scores.not($rowScore).val() >= gameData.maxScore) {
				$scores.not($rowScore).val(gameData.maxScore - 1);
				$rowScore.val(gameData.absoluteMaxScore);
			} else {
				$rowScore.val(gameData.maxScore);
			}
			validateForm();
			return false;
		});
		
		$root.on('input', ':input', function(e) {
			 validateForm();
		});
		$root.on('change', '.matchEntry', function() {
			validateForm();
		});
		
		
		var validateForm = function(){
			var ids = {}
				, $submitBtn = $('button[name="formSubmit"]', $root).addClass("btn btn-danger").removeClass("btn-primary").attr("disabled", "disabled");
			$('.matchEntry', $root).each(function(){
				var id = $(this).find(":selected").val();
				ids[id]=true;
			});

			// Check all players are selected
			if(Object.keys(ids).length < $('.matchEntry', $root).size() || ids["-1"]!=undefined)
				return;

			// Validate scores
			var $scores = $('input.score', $root)
				, score1 = parseInt($scores.filter("[data-entry-num=1]").val())
				, score2 = parseInt($scores.filter("[data-entry-num=2]").val());

			if(isNaN(score1) || isNaN(score2) || score1 == score2 || score1 > gameData.absoluteMaxScore || score2 > gameData.absoluteMaxScore)
				return;
			$submitBtn.addClass("btn btn-primary").removeClass("btn-danger").removeAttr("disabled");
		};
		validateForm();
	});
});

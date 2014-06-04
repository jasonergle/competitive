<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="tab_${game.type}"
	class="tab-pane ${status.index == 0 ? 'active' : ''}" >
	<form role="form" method="post"
		data-game-type="${game.type}"
		data-game-json='${model.getJsonForGame(game)}'
		action="${contextPath}/submitMatch" >
	<input type="hidden" name="gameType" value="${game.type}"/>
	<h5>${game.name}</h5>
	<c:forEach var="entryNum" begin="1" end="2">
		<div class="row">  <!-- p1 row -->
			<div class="col-sm-3">
				<label for="entry${entryNum}"><i class="fa fa-thumbs-o-up"></i> Entry ${entryNum}</label>
				<div class="row">
					<div class=" col-sm-12">
						<c:choose>
							<c:when test="${game.usesTeams}">
								<select name="entry${entryNum}"
										class="form-control matchEntry">
									<option value="-1"></option>  									
									<c:forEach var="team" items="${model.getTeamsForGame(game)}">
										<option value="${team.id}">${team.name}</option>
									</c:forEach>
								</select>
							</c:when>
		
							<c:otherwise>
								<c:forEach var="cnt" begin="1" end="${game.teamSize}">
									<select name="entry${entryNum}_${cnt}"
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
						<input name="score${entryNum}" class="score form-control" data-entry-num="${entryNum}"
								type="number" size="2" min="0" max="${game.maxScore}" value=""/>
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
			<button type="button" name="formSubmit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>
</div>

<script>
</script>

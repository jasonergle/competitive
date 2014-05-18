<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>League Properties</h4>
<form id="league-properties" class="form-horizontal" method="post" action="<%=request.getContextPath() %>/saveLeague">
	<fieldset>
		<div class="pull-right">
			<button class="btn btn-danger">Cancel</button>
			<input type="hidden" name="leagueId" value="${model.league.id}">
			<input type="hidden" name="action" value='${model.action}'>
			<input type="submit" class="btn btn-primary" value="Save Properties">
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label" for="name">League Name</label>
			<div class="col-md-4">
				<input id="name" name="name" type="text"
					placeholder="Name of League" class="form-control input-md"
					required="required" value="${model.league.name }"> 
					<span class="help-block">Give your league a name. </span>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="abbreviation">Abbreviation</label>
			<div class="col-md-2">
				<input id="abbreviation" name="abbreviation" type="text"
					placeholder="abbr" class="form-control input-md"
					required="required" value="${model.league.abbr }"> 
					<span class="help-block">Short name for league</span>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="public">Is Public</label>
			<div class="col-md-4">
				<label class="radio-inline" for="isPublic"> 
					<input type="radio" name="public" id="public-0" value="Yes"
						<c:if test="${model.league.isPublic}">checked="checked"</c:if>> Yes
				</label> 
				<label class="radio-inline" for="public-1"> 
					<input type="radio" name="public" id="public-1" value="No" 
						<c:if test="${!model.league.isPublic}">checked="checked"</c:if>> No
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="password">Join
				Password</label>
			<div class="col-md-4">
				<input id="password" name="password" type="text" placeholder="Join Password" 
					class="form-control input-md" value="${model.league.joinPassword }">
				<span class="help-block">Password used to join this league</span>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="rankingMethods">Ranking Methods</label>
			<div class="col-md-4">
				<label class="checkbox-inline" for="enableLeaderboards"> 
					<input type="checkbox" name="enableLeaderboards" id="enableLeaderboards"
						value="Leaderboards" <c:if test="${model.league.enableLeaderboards}">checked="checked"</c:if>> 
						Leaderboards
				</label> 
				<label class="checkbox-inline" for="enableStandings"> 
					<input type="checkbox" name="enableStandings" id="enableStandings""
						value="Standings" <c:if test="${model.league.enableStandings}">checked="checked"</c:if>> 
						Standings
				</label>
			</div>
		</div>
	</fieldset>
</form>

<script>
$(document).ready(function() {

});
</script>
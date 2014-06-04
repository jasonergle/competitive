<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.erglesoft.dbo.*"%>
<%@page import="com.erglesoft.jspmodel.*"%>
<%@page import="com.erglesoft.game.*"%>
<%
HomeJspModel model = new HomeJspModel(request);
request.setAttribute("model", model);
%>
<html>
<head>
<title>Head to Head - Home</title>
<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/navbar.jsp"></jsp:include>

	<div class="container">
		<c:choose>
			<c:when test="${!model.hasCurrentLeague()}">
		     You must be a member of a League to do anything interesting.  You can <a href="">join
				an existing league</a> or you can <a
					href="${pageContext.servletContext.contextPath}/jsp/league/editLeague.jsp?action=CREATE">create
					a new league</a>. 
			</c:when>

			<c:otherwise>
				<div id="reportrange" class="pull-right">
				    <i class="fa fa-calendar fa-lg"></i>
				    <span>Last 30 Days</span> <b class="caret"></b>
				</div>
				<c:forEach var="game" items="${model.getAllowedGames()}">
					<div class="lbContainer" data-league-id="${model.curLeague.id}" data-game-id="${game.id}" >
					
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			var buildTables = function(start, end){
				$('.lbContainer').each(function(){
					var div = $(this);
					var leagueId = $(this).attr("data-league-id");
					var gameId = $(this).attr("data-game-id");
					var postData = { leagueId: leagueId, gameId: gameId, start: start, end: end };
					
					$.ajax({
						url: $.erglesoft.contextPath+'/jsp/leaderboard/leaderboardTable.jsp',
						data: postData,
						dataType: "html",
						success:function(data){
							div.empty();
							div.append(data);
							$('.lbTable', div).DataTable({
								bFilter : false,
								bLengthChange : false,
								bPaginate : true,
								aaSorting : [ [ 0, "asc" ] ]
							});
						}
					});
				});
			};
			buildTables(moment().subtract('days', 29).valueOf(), moment().valueOf());
			
			$('#reportrange').daterangepicker(
			    {
			      ranges: {
			         'Today': [moment(), moment()],
			         'Last 7 Days': [moment().subtract('days', 6), moment()],
			         'Last 30 Days': [moment().subtract('days', 29), moment()],
			         'This Month': [moment().startOf('month'), moment().endOf('month')],
			         'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')],
			         'All Time': [moment("Jan 1, 2000"), moment()]
			      },
			      startDate: moment().subtract('days', 29),
			      endDate: moment()
			    },
			    function(start, end) {
			        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
			        buildTables(start.valueOf(), end.valueOf());
			    }
			);

		});

	</script>
</body>
</html>



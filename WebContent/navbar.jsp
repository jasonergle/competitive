<%@page import="com.erglesoft.dbo.Player"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="com.erglesoft.login.*"%>
<%@page import="java.util.*"%>
<%
UserLoginData userData = UserLoginData.fromHttpSession(request);
Player curPlayer = userData.getPlayer();
PlayerManager pMgr = new PlayerManager();
List<Player> players = null; 
%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" 
				data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<span class="navbar-brand logoBase">Head<span class="text-muted">to</span>Head</span>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<%if(curPlayer!=null){ %>
				<li><a href="leaderboards.jsp">Leaderboards</a></li>
				<li><a href="enterMatch.jsp">Enter Match</a></li>
				<li><a href="viewMatches.jsp">See Matches</a></li>
				<%if(curPlayer.getSuperUserFlag()){ %>
				<li><a href="<%=request.getContextPath() %>/jsp/admin/admin.jsp">Admin</a></li>
				<%} %>
				<%} %>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%if(curPlayer!=null){ %>
				<li>
					<a class="" href="playerProfile.jsp?player=<%=curPlayer.getId() %>">
						<strong>
							<%=PlayerManager.getNameForPlayer(curPlayer) %> @ <%=curPlayer.getCurrentLeague().getName() %>
						</strong>
					</a>
				</li>
				<li>
					<form class="navbar-form navbar-left" action="logout">
						<button class="btn btn-sm btn-danger" type="submit">Logout</button>
					</form>
				</li>
				<%}else{ %>
				<p class="navbar-text">Not Logged In</p>
				<%} %>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container -->
</div>

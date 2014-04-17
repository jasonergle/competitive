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

<div class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" 
				data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<span class="navbar-brand">Head2Head</span>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<%if(curPlayer!=null){ %>
				<li><a href="leaderboards.jsp">Leaderboards</a></li>
				<li><a href="enterMatch.jsp">Enter Match</a></li>
				<li><a href="viewMatches.jsp">See Matches</a></li>
				<%} %>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%if(curPlayer!=null){ %>
				<div>
					<strong>
						<a class="" href="viewStats.jsp?player=<%=curPlayer.getId() %>"> 
							<%=PlayerManager.getNameForPlayer(curPlayer) %> @ <%=curPlayer.getCurrentLeague().getName() %>
						</a>
					</strong>
					&nbsp;
					<a class="btn btn-danger" href="logout">Logout</a>
				</div>
				<%}else{ %>
				<span>Not Logged In</span>
				<%} %>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</div>
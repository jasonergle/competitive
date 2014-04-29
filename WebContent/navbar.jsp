<%@page import="com.erglesoft.dbo.Team"%>
<%@page import="com.erglesoft.jspmodel.NavbarJspModel"%>
<%@page import="com.erglesoft.mgr.*"%>
<%@page import="com.erglesoft.login.*"%>
<%@page import="java.util.*"%>
<%
NavbarJspModel model = new NavbarJspModel(request);
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
				<%if(model.getUserData()!=null){%>
					<li><a href="leaderboards.jsp">Leaderboards</a></li>
					<li><a href="enterMatch.jsp">Enter Match</a></li>
					<li><a href="viewMatches.jsp">See Matches</a></li>
					<%
					if(model.getUserData().getLogin().getSuperUserFlag()){
					%>
					<li><a href="<%=request.getContextPath()%>/jsp/admin/admin.jsp">Admin</a></li>
					<%
					}
					%>
				<%}%>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%if(model.getLoginData()!=null){%>
				<li>
					<a class="" href="playerProfile.jsp?participant=<%=model.getLoginData().getLogin().getId()%>">
						<strong>
							<%=LoginManager.getLabelForLogin(model.getLoginData().getLogin())%>
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

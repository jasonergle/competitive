<%@page import="com.erglesoft.dbo.*"%>
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
			<a href="<%=request.getContextPath() %>/">
				<img src="<%=request.getContextPath() %>/assets/images/h2hlogo.png" style="height:50px;padding:5px;">
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<%if(model.getUserData()!=null){%>
					<li><a href="<%=request.getContextPath() %>/home.jsp">Home</a></li>
					<%if(model.canEnterScores() && model.getLoginData().getCurLeague()!=null){ %>
					<li><a href="<%=request.getContextPath() %>/jsp/enterMatch/enterMatch.jsp">Enter Match</a></li>
					<%} %>
					<%if(model.getLoginData().getCurLeague()!=null){ %>
					<li><a href="<%=request.getContextPath() %>/viewMatches.jsp">See Matches</a></li>
					<%} %>
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
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown">
						<%=model.getCurrentUserString() %> 
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a class="btn btn-sm btn-danger" style="color:white;margin-left:20px;margin-right:20px;" href="<%=request.getContextPath() %>/logout">Logout</a>
							<a class="" href="<%=request.getContextPath() %>/profileEdit.jsp">Manage Profile</a>
						</li>
						<li class="dropdown-header">League</li>
						<%if(model.getLoginData()!=null && model.getLoginData().getCurLeague()!=null && model.getLoginData().getIsLeagueAdmin()){ %>
							<li><a href="<%=request.getContextPath()%>/jsp/league/editLeague.jsp?action=EDIT">Manage <%=model.getLoginData().getCurLeague().getName()%></a></li>
						<%} %>
						<li><a href="<%=request.getContextPath()%>/jsp/league/editLeague.jsp?action=CREATE">Create New League...</a></li>
						<li><a href="#">Join Another League...</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Switch Leagues</li>
						<%for(League l: model.getAvailableLeagues()){ 
							if(l.getId()!=model.getLoginData().getCurLeague().getId()){%>
							<li><a href="<%=request.getContextPath() %>/setCurrentLeague?id=<%=l.getId()%>"><%=l.getName() %></a></li>
						<%}} %>
					</ul>
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

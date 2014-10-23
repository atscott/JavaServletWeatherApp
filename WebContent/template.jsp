<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String thePage = (String) request.getAttribute("jspBody");
	if (thePage == null) {
		thePage = "home.jsp";
		request.setAttribute("pageTitle", "Home");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="shortcut icon" href="/images/favicon.ico"
	type="image/vnd.microsoft.icon" />
<meta name="viewport" content="width=device-width" />
<title>Andrew's Amazing JSPs</title>
<link type="text/css" rel="stylesheet"
	href="css/css_yUjLtNYsOvh2uWKQVPEhmTb6wXTwlc3YlN3PA7MeURI.css"
	media="all" />
<link type="text/css" rel="stylesheet"
	href="css/css_XqTxU_SGajPlzZKaK2KKR1D62m5YOGDLk1kju8d2T6E.css"
	media="all" />
<link type="text/css" rel="stylesheet"
	href="css/css_apmPcLN3EtnvJVuuGdB_BucJEuxe3yWAWm_yuAOMBlg.css"
	media="all" />
<link type="text/css" rel="stylesheet" href="css/format.css" media="all" />
<script type="text/javascript"
	src="js/js_3jHghlMLrjr9xXAC0JufqSSch3oAbkZstSqYdc4uuck.js"></script>
<script type="text/javascript"
	src="js/js_IDBX5SzkJ9gGNq7x-qOE_2DZsexqguTJQGMKvi4w-Uw.js"></script>
<script type="text/javascript"
	src="js/js_iYsPpB3B-cWPXOZpHqdoSUudh3OYEXD053YZrYvXrws.js"></script>
</head>
<body
	class="html not-front not-logged-in no-sidebars page-node page-node- page-node-13 node-type-page">

	<div id="wrapper">
		<header id="header" role="banner">
		<div id="logo">
			<a href="template.jsp" title="Home"><img
				src="http://www.devsaran.com/theme/professional/sites/all/themes/professional_theme/logo.png" /></a>
		</div>
		<h1 id="site-title">
			<a href="/theme/professional/" title="Home">Andrew's Amazing JSPs</a>
		</h1>
		<div id="site-description">A Web Apps Assignment</div>
		<div class="clear"></div>
		<nav id="main-menu" role="navigation"> <a class="nav-toggle"
			href="#">Navigation</a>
		<div class="menu-navigation-container">
			<ul class="menu">
				<li class="first leaf"><a href="/Lab7MVC/CmdServlet?cmd=time">Time</a></li>
				<li class="leaf"><a href="/Lab7MVC/CmdServlet?cmd=weather">Weather</a></li>
				<li class="leaf"><a href="/Lab7MVC/CmdServlet?cmd=requestInfo">RequestInfo</a></li>
				<li class="last leaf"><a href="/Lab7MVC/QuoteServlet">Stocks</a></li>
			</ul>
		</div>
		<div class="clear"></div>
		</nav> <!-- end main-menu --> </header>

		<div id="container">

			<div class="content-sidebar-wrap">
				<div id="content">
					<section id="post-content" role="main"> <%
 	String pageTitle = (String) request.getAttribute("pageTitle");
 	if (pageTitle != null) {
 		out.println("<h1 class=\"page-title\">" + pageTitle + "</h1>");
 	}
 %>
					<div class="region region-content">
						<div id="block-system-main" class="block block-system">

							<jsp:include page='<%=thePage%>' />
						</div>
						<!-- /.block -->
					</div>
					<!-- /.region --> </section>
					<!-- /#main -->
				</div>

			</div>


			<div class="clear"></div>

		</div>
		<div id="footer">
			<div id="copyright">
				<p class="copyright">Copyright &copy; 2013, Andrew Scott</p>
				<p class="credits">
					Theme by <a href="http://www.devsaran.com">Devsaran</a>
				</p>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>
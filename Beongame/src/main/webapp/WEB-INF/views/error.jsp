<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.notice-Error {
	background: #67b11c url("tick.png") 20px 50% no-repeat;
	border: 2px solid #467813;
	border-radius: 5px;
	color: #ff0000;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	padding: 5px 5px 5px 10px;
}

.navbar {
	margin-bottom: 0px;
	border-radius: 0px;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #b2beb5;
	padding: 13px;
}

Button {
	font-size: 15px;
	border-radius: 3px;
	background-color: White;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

.demotbl {
	border: 0px solid #69899F;
}

.demotbl th {
	padding: 15px;
	color: #fff;
}

.demotbl td {
	width: 300px;
	padding: 10px;
	text-align: center;
	vertical-align: top;
	border: 2px solid #000000;
	border-radius: 2px;
	color: #666;
	text-shadow: 1px 1px 1px #fff;
}

body {
	background-image:url("http://www.blkcnd.com/images/works/netco-sports/background.jpg");
	background-color: #cccccc;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="${pageContext.servletContext.contextPath}/index.jsp"><img
					src="${pageContext.servletContext.contextPath}/resources/img/logo.jpg"
					alt="Image" height="42" width="42"></a>
			</div>
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<div class="collapse navbar-collapse" id="myNavbar">
				<div style="padding-top: 5px">
					<ul>
						<li><a href="${contextPath}/user/login"><button type="button" class="btn btn-primary navbar-right">Go Back</button></a></li>
					</ul>
				</div>
			</div>			
		</div>
	</nav>

		<div style="padding-top: 220px; padding-left: 400px; color: Red">
			<h2>${errorMessage}</h2>
		</div>

	<div style="padding-top: 500px; padding-left: 2px">
		<footer class="container-fluid text-center"
			style="color: White; background-color: Black">
			<p>BeOnGame Icon Copyright @ 2017 BeOnGame Bookings.</p>
		</footer>
	</div>
</body>
</html>
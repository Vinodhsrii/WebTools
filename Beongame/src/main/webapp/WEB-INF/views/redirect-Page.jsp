<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
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
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.notice-error {
	background: #67b11c url("tick.png") 20px 50% no-repeat;
	border: 2px solid #467813;
	border-radius: 5px;
	color: #ff0000;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	padding: 15px 20px 15px 50px;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 15px;
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
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
			<div class="collapse navbar-collapse" id="myNavbar">
				<div style="padding-top: 5px">
					<ul>
						<li><a href="${contextPath}"><button type="button"
									class="btn btn-primary navbar-right">Go Back</button></a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<img src="https://sell.toshiba.com/images/sell/login-header.jpg">

	<div class="notice-error">
		<h2>User not recognized. Kindly please login</h2>
	</div>
	<div style="padding-top: 295px">
		<footer class="container-fluid text-center"
			style="color: White; background-color: Black">
			<p>BeOnGame Icon Copyright @ 2017 BeOnGame Bookings.</p>
		</footer>
	</div>
</body>
</html>
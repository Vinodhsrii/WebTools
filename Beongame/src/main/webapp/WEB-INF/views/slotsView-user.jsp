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

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 12px;
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

body {
	background-image:
		url("http://www.blkcnd.com/images/works/netco-sports/background.jpg");
	background-color: #cccccc;
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
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<div class="collapse navbar-collapse" id="myNavbar">
				<div style="padding-top: 5px">
					<ul>
						<li><a href="${contextPath}/user/login"><button
									type="button" class="btn btn-primary navbar-right">Go
									Back</button></a></li>
					</ul>
				</div>
			</div>

		</div>
	</nav>

	<h2 class="text-center">User Portal - List of Available Slots</h2>
	<div style="padding-top: 120px; padding-left: 5px">
		<form class="form-horizontal" method="post">
			<table class="demotbl">
				<tr>
					<td style="background-color: #00bfff; color: Black"><b>ACADEMY
							NAME</b></td>
					<td style="background-color: #00bfff; color: Black"><b>ACADEMY
							LOCATION</b></td>
					<td style="background-color: #00bfff; color: Black"><b>SPORTS
							NAME</b></td>
					<td style="background-color: #00bfff; color: Black"><b>PRICE
					</b></td>
					<td style="background-color: #00bfff; color: Black"><b>SLOT
							NO</b></td>
					<td style="background-color: #00bfff; color: Black"><b>AVAILABILITY</b></td>
					<td style="background-color: #00bfff; color: Black"><b>CHOOSE</b></td>
				</tr>
				<c:forEach var="adv" items="${splist}">
					<tr>
						<td style="color: Black; background-color: White">${adv.academy.academyName}</td>
						<td style="color: Black; background-color: White">${adv.academy.academyLocation}</td>
						<td style="color: Black; background-color: White">${adv.game}</td>
						<td style="color: Black; background-color: White">${adv.price}</td>
						<td style="color: Black; background-color: White">${adv.slotID}</td>
						<td style="color: Black; background-color: White">${adv.availability}</td>
						<c:set var="av" scope="session" value="A" />
						<c:choose>
							<c:when test="${adv.availability == av}">
								<td><a
									href="${pageContext.request.contextPath}/enduser/selectSport?spID=${adv.spID}"
									style="color: Green">Pick</a></td>
							</c:when>
							<c:otherwise>
								<td style="color: Red"><a href="#" style="color: Red">Occupied</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<div style="padding-top: 339px">
		<footer class="container-fluid text-center"
			style="color: White; background-color: Black">
			<p>BeOnGame Icon Copyright @ 2017 BeOnGame Bookings.</p>
		</footer>
	</div>

</body>
</html>
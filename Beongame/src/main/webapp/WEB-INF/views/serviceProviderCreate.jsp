<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
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
	padding: 25px;
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

	<br />
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
				<ul class="nav navbar-nav">
					<li class="active"><a data-toggle="pill" href="#first">Create
							Slots Sports</a></li>
				</ul>
				<div style="padding-top: 5px">
					<ul>
						<li><a href="${contextPath}/user/login"><button type="button" class="btn btn-primary navbar-right">Go Back</button></a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<!-- <div class="tab-content"> -->
		<!-- <div id="first" class="tab-pane fade in active"> -->
		<h2>Service Provider Portal - Manage Sports</h2>
		<br></br>
		<form role="form" class="form-horizontal"
			action="${contextPath}/serviceprovider/sportsCreate" method="post">
			<br></br>
			<div class="form-group">

				<div class="col-md-10">
					<div class="col-md-4">
						<label>AcademyID</label>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" required name="acadID"
							id="price" readonly value="${Academyobj.academyID}" />
					</div>
				</div>

				<div class="col-md-10">
					<div class="col-md-4">
						<label>AcademyName</label>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" required name="acadName"
							id="price" readonly value="${Academyobj.academyName}" />
					</div>
				</div>

				<br></br>
				<div class="col-md-10">
					<div class="col-md-4">
						<label>Sports</label>
					</div>
					<div class="col-md-6">
						<select id="mySelect" name="spname">
							<c:forEach var="adv" items="${sessionScope.spList}">
								<li><a>${adv.gameName}</a></li>
								<option value="${adv.gameName}">${adv.gameName}-</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<br></br>
				<div class="col-md-10">
					<div class="col-md-4">
						<label>Price</label>
					</div>
					<div class="col-md-6">
						<input type="number" class="form-control" required name="spprice"
							id="price" min="1" placeholder="Price" />
					</div>
				</div>
				<br></br>
				<div class="col-md-10">
					<div class="col-md-4">
						<label>Slots Count</label>
					</div>
					<div class="col-md-6">
						<input type="number" class="form-control" required name="spslots"
							id="slots" min="1" />
					</div>
				</div>
				<br></br>
				<div class="col-md-10">
					<div class="col-md-4"></div>
					<div class="col-md-6">
						<input type="submit" value="Create" style="">
					</div>
				</div>
			</div>
		</form>
		<!-- </div> -->
		<!-- </div> -->
	</div>
</body>
</html>

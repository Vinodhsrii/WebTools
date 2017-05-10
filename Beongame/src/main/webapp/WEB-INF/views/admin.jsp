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
	margin-bottom: 0px;
	border-radius: 0px;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 10px;
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

div {
	background-image:
		url("${pageContext.servletContext.contextPath}/index.jsp");
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
					<ul class="nav navbar-nav">
					<li class="active"><a data-toggle="pill" href="#first">Manage
							Academy/Sports</a></li>
					<li><a data-toggle="pill" href="#second">View
							Academy/Sports</a></li>
					<li><a data-toggle="pill" href="#third">Add Service
							Provider</a></li>
				</ul> 
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="tab-content">
			<div id="first" class="tab-pane fade in active">
				<h2 class="text-center">Administration Portal - Manage Sports</h2>
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/adminPage1.jpg">
				<div style="padding-top: 30px; padding-left: 400px">
					<form role="form" class="form-horizontal"
						action="${contextPath}/admin/sportsCreate" method="post">
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<input type="text" class="form-control" required name="spName"
										id="spName" placeholder="Sport Name" />
								</div>
								<div class="col-sm-4">
									<button type="submit" class="btn btn-primary btn-sm">
										Create</button>
								</div>
								<div class="col-md-4">
									<p id="spNameerror"></p>
								</div>
							</div>
							<br></br>
						</div>
					</form>
				</div>
			</div>
			<div id="second" class="tab-pane fade">
				<h2 class="text-center">Administration Portal - View Academy /
					Sports</h2>
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/adminPage1.jpg">
					
					
				<div style="padding-top: 80px; padding-left: 400px">
					<form role="form" class="form-horizontal"
						action="${contextPath}/admin/view" method="post">
						<div class="form-group">
							<div class="row">
								<div class="col-md-6">
									<label class="radio-inline"><input type="radio"
										name="optradio" value="sp" checked="checked">View Available Sports</label> <label
										class="radio-inline"><input type="radio"
										name="optradio" value="ac">View Available Academy</label>
								</div>
								<div class="col-md-6">
									<button type="submit" class="btn btn-primary btn-sm">
										View Now</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div id="third" class="tab-pane fade">
				<h2 class="text-center">Administration Portal - Add Service Providers</h2>
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/adminPage1.jpg"
					width=1200px height=300px>
				<div style="padding-top: 30px;">
					<form role="form" class="form-horizontal"
						action="${contextPath}/admin/serviceprovidersignup" method="post">
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-md-4">
									<input type="text" class="form-control" required id="loginType"
										name="loginType"  readonly value="ServiceProvider" />
								</div>

								<div class="col-md-4">
									<input type="email" class="form-control" id="emailId" required
										name="emailId" placeholder="Email-ID" />
								</div>
								<div class="col-md-4">
									<input type="password" class="form-control" id="password"
										required name="password" placeholder="Password" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-md-4">
									<input type="text" class="form-control" required
										name="firstName" placeholder="First Name" />
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control" required
										name="lastName" placeholder="Last Name" />
								</div>
								<div class="col-md-4">
									<input type="number" class="form-control" required id="age"
										name="age" min="1" placeholder="Age" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">

								<div class="col-md-4">
									<input type="text" class="form-control" required
										name="acadName" id="acadName" placeholder="Academy Name" />
								</div>
								
								<div class="col-md-4">
									<input type="text" class="form-control" required name="acadLoc"
										id="acadLoc" placeholder="Academy Location" />
								</div>

								<div class="col-sm-4">
									<input type="number" class="form-control" id="mobile" required
										name="contactNo" min="1" placeholder="Mobile" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-4">
									<button type="submit" class="btn btn-primary btn-sm"
										style="width: 150px">Create</button>
								</div>
								<div class="col-md-4">
									<p id="spNameerror1"></p>
								</div>
								<div class="col-md-4">
									<p id="spNameerror2"></p>
								</div>								
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="padding-top: 120px ; padding-left: 1px">
		<footer class="container-fluid text-center"
			style="color: White; background-color: Black">
			<p>BeOnGame Icon Copyright @ 2017 BeOnGame Bookings.</p>
		</footer>
	</div>
	<script>
		$('#spName').on('input', function(e) {

			e.preventDefault();
			var aid = $(this).val();
			$.ajax({
				url : '/top/admin/sportsCreates',
				contentType : "application/json; charset=utf-8",
				data : {
					'aid' : aid
				},
				type : 'GET',
				cache : false,
				success : function(response) {
					$('#spNameerror').text(response).css({
						"color" : "Red",
						"font-weight" : "Bold"
					});

				}
			});
		});
		
		$('#emailId').on('input', function(e) {

			e.preventDefault();
			var aid1 = $(this).val();
			$.ajax({
				url : '/top/admin/serviceprovidersignups',
				contentType : "application/json; charset=utf-8",
				data : {
					'aid1' : aid1
				},
				type : 'GET',
				cache : false,
				success : function(response) {
					$('#spNameerror1').text(response).css({
						"color" : "Red",
						"font-weight" : "Bold"
					});

				}
			});
		});
		
		$('#acadName').on('input', function(e) {
			e.preventDefault();
			var aid2 = $(this).val();
			alert(Test);
			$.ajax({
				url : '/top/admin/serviceprovidersignupss',
				contentType : "application/json; charset=utf-8",
				data : {
					'aid2' : aid2
				},
				type : 'GET',
				cache : false,
				success : function(response) {
					$('#spNameerror2').text(response).css({
						"color" : "Red",
						"font-weight" : "Bold"
					});

				}
			});
		});
		
	</script>
</body>
</html>
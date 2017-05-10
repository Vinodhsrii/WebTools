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
.navbar {
	margin-bottom: 0px;
	border-radius: 0px;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #b2beb5;
	padding: 25px;
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
					alt="Image" height="45" width="45"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<!-- 					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Projects</a></li>
					<li><a href="#">Contact</a></li> -->
				</ul>
				<div style="padding-top: 5px">
					<button type="button" class="btn btn-primary navbar-right"
						data-toggle="modal" data-target="#myModal">Login</button>
				</div>
			</div>
		</div>
	</nav>

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/Image1.jpg"
					alt="Image">
				<div class="carousel-caption"></div>
			</div>

			<div class="item">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/image2.jpg"
					alt="Image">
				<div class="carousel-caption"></div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<div class="jumbotron text-center"
		style="color: White; background-color: Black">
		<h1>BeOnGame</h1>
		<p>Free online booking and scheduling for sports Venues</p>
	</div>

	<div class="container">
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-3">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="text-center">Designed for your spaces</h3>
						<p>Whether you have courts, pitches, fields or anything
							similar, managing these kinds of "spaces" can be a challenge.</p>
						<p>How can we let users see what is available?</p>
						<p>How can we flexibly take payment, either online or at the
							venue?</p>
						<p>How can we display bookings on our website?</p>
						<p>How do we manage spaces that overlap or are shared?</p>
						<p>BeOnGame was created to solve these problems and let you
							better manage your venue.</p>
					</div>
					<div class="panel-footer">
						<a class="btn btn-lg btn-block btn-danger" href="#"></a>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3>Feature-rich, yet elegant</h3>
						<p>BeOnGame takes the hassle out of managing your sports
							venue. If you want to save time and reduce administration, look
							no further.</p>

						<p>Manage and show availability</p>
						<p>Take online payments</p>
						<p>Provide a simple way for users to book</p>
						<p>Control your venue from anywhere</p>
						<p>Customize your venue setup</p>
						<p>And much more...</p>
					</div>


					<div class="panel-footer">
						<a class="btn btn-lg btn-block btn-danger" href="#"></a>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3>Trusted worldwide</h3>
						<p>Depended upon by venues around the globe, BeOnGame is the
							leading cloud solution for managing space. Whether you are
							managing squash courts, tennis courts, cricket nets or other
							playing surfaces, you can rely on BeOnGame to simplify the
							booking complexities you face every day.</p>

						<p>Join the thousands of venues already enjoying a smarter way
							to manage their bookings!</p>

					</div>


					<div class="panel-footer">
						<a class="btn btn-lg btn-block btn-danger" href="#"></a>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3>About BeOnGame</h3>
						<p>Beongame is beautifully designed, intelligently
							constructed, and the team top notch. Our profits have already
							increased since we switched this year. If you have a venue, don't
							look anywhere else</p>
					</div>


					<div class="panel-footer">
						<a class="btn btn-lg btn-block btn-danger" href="#"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center"
		style="color: White; background-color: Black">
		<p>BeOnGame Icon Copyright @ 2017 BeOnGame Bookings.</p>
	</footer>


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">SignIn / SignUp</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-8" style="padding-right: 0px;">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs">
								<li class="active"><a href="#Login" data-toggle="tab">SignIn</a></li>
								<li><a href="#Registration" data-toggle="tab">SignUp</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<div class="tab-pane active" id="Login">
									<br></br>
									<form role="form" class="form-horizontal" action="user/login"
										method="post">

										<div class="form-group">
											<div class="col-md-3">
												<select class="form-control" required name="loginType">
													<option>Admin</option>
													<option>User</option>
													<option>ServiceProvider</option>
												</select>
											</div>
											<br></br>
											<div class="col-sm-10">
												<input type="email" class="form-control" required
													name="email" id="email1" placeholder="Email" />
											</div>
										</div>
										<div class="form-group">
											<!--                                     <label for="exampleInputPassword1" class="col-sm-2 control-label">
                                        Password</label> -->
											<div class="col-sm-10">
												<input type="password" class="form-control" required
													name="password" id="exampleInputPassword1"
													placeholder="Password" />
											</div>
										</div>
										<div class="row">
											<div class="col-sm-2"></div>
											<div class="col-sm-10">
												<button type="submit" class="btn btn-primary btn-sm">
													SignIn</button>
												<!-- 												<a href="javascript:;">Forgot your password?</a> -->
											</div>
										</div>
									</form>
								</div>
								<div class="tab-pane" id="Registration">
									<br></br>
									<form role="form" class="form-horizontal" action="user/signup"
										method="post">
										<div class="form-group">
											<div class="col-sm-12">
												<div class="col-md-6">
													<select class="form-control" name="loginType">
														<option>User</option>
													</select>
												</div>
												<div class="col-md-6">
													<input type="number" class="form-control" required id="age"
														name="age" min="1" placeholder="Age" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<div class="col-md-6">
													<input type="text" class="form-control" required
														name="firstName" placeholder="First Name" />
												</div>
												<div class="col-md-6">
													<input type="text" class="form-control" required
														name="lastName" placeholder="Last Name" />
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<div class="col-md-6">
													<input type="email" class="form-control" id="emailId"
														required name="emailId" placeholder="Email-ID" />
												</div>
												<div class="col-sm-6">
													<input type="number" class="form-control" id="mobile"
														required min="1" name="contactNo" placeholder="Mobile" />
												</div>

											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<div class="col-md-6">
													<input type="text" class="form-control" id="userName"
														required name="userName" placeholder="UserName" />
												</div>
												<div class="col-md-6">
													<input type="password" class="form-control" id="password"
														required name="password" placeholder="Password" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-2"></div>
											<div class="col-sm-6">
												<button type="submit" class="btn btn-primary btn-sm">Continue</button>
												<!-- <button type="button" class="btn btn-default btn-sm">Cancel</button> -->
											</div>
											<div class="col-md-4">
												<p id="spNameerror1"></p>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#emailId').on('input', function(e) {
			e.preventDefault();
			var aid1 = $(this).val();
			alert(test);
			$.ajax({
				url : '/top/user/signup',
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
	</script>
</body>
</html>

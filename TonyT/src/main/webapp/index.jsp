<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>JQ Cockpit</title>

<!-- import angular js scripts: -->
<script src="<c:url value="/resources/js/crypto-js.js"/>"></script>
<script src="<c:url value="/resources/js/aes.js"/>"></script>
<script src="<c:url value="/resources/js/angular.js"/>"></script>
<script src="<c:url value="/resources/js/angular-route.min.js"/>"></script>
<script src="<c:url value="/resources/js/angular-animate.min.js"/>"></script>
<script src="<c:url value="/resources/js/angular-aria.min.js"/>"></script>
<script src="<c:url value="/resources/js/angular-material.min.js"/>"></script>
<script src="<c:url value="/resources/js/angular-messages.min.js"/>"></script>
<script src="<c:url value="/resources/js/ngDialog.min.js"/>"></script>
<script src="<c:url value="/resources/js/ui-layout.min.js"/>"></script>
<script src="<c:url value="/resources/js/angular-idle.js"/>"></script>
<script src="<c:url value="/resources/js/Chart.js"/>"></script>
<script src="<c:url value="/resources/js/angular-chart.js"/>"></script>
<script src="<c:url value="/resources/js/select.min.js"/>"></script>
<!-- http://jtblin.github.io/angular-chart.js/ -->

<!-- import ui-grid related scripts: -->
<script src="<c:url value="/resources/js/csv.js"/>"></script>
<script src="<c:url value="/resources/js/pdfmake.js"/>"></script>
<script src="<c:url value="/resources/js/vfs_fonts.js"/>"></script>
<script src="<c:url value="/resources/js/ui-grid.min.js"/>"></script>

<!-- import user defined Angular APP JS-->
<script src="<c:url value="/angularjs/app.js"/>"></script>
<script
	src="<c:url value="/angularjs/controller/cockpit_controller.js"/>"></script>
<script src="<c:url value="/angularjs/service/acars_service.js"/>"></script>

<!-- import CSS -->
<link href="<c:url value='/resources/css/angular-material.css' />"
	rel="stylesheet" type="text/css"></link>

<!-- [30 May 2018] do not import this bootstrap.min.css as it conflicts with angular-material.css 	
<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet" type="text/css"></link>	
-->
<link href="<c:url value='/resources/css/ui-grid.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link
	href="<c:url value='/resources/css/ngDialog-theme-default.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link
	href="<c:url value='/resources/css/ngDialog-theme-plain.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link href="<c:url value='/resources/css/ngDialog.min.css' />"
	rel="stylesheet" type="text/css"></link>

<link href="<c:url value='/resources/css/ui-grid.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link
	href="<c:url value='/resources/css/ngDialog-theme-default.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link
	href="<c:url value='/resources/css/ngDialog-theme-plain.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link href="<c:url value='/resources/css/ngDialog.min.css' />"
	rel="stylesheet" type="text/css"></link>
<link href="<c:url value='/resources/css/div-style.css' />"
	rel="stylesheet" type="text/css"></link>

<!-- .ui-grid-contents-wrapper and .ui-grid-header-cell-wrapper are to solve grid header stretching way to large
reference: https://github.com/angular-ui/ui-grid/issues/4595 -->

<style type="text/css">
body {
	background-image: url('https://cdn.crunchify.com/bg.png');
}

.ui-grid-contents-wrapper {
    height: auto;
}
.ui-grid-header-cell-wrapper {
    height: auto;
}
.column {
  float: left;
  width: 48%;
  padding: 5px;
}

/* Clearfix (clear floats) */
.row::after {
  content: "";
  clear: both;
  display: table;
}
</style>
</head>
<body ng-app="tonytApp" md-colors="{background:'green-100'}">
	<header>
		<div>
			<md-toolbar md-colors="{background:'teal-800'}">
			<div>
				<div align="middle">
					<h2>
						<font size="10" color="#3399ff">A</font> <font size="10"
							color="#33ff77">C</font> <font size="10" color="#000000">A</font>
						<font size="10" color="#000000">R</font> <font size="10"
							color="#000000">S</font>
					</h2>
					<h3>
						<font size="4" color="#ffff80"><i>Aircraft
								Communications, Addressing and Reporting System</i></font>
					</h3>
				</div>
			</div>
			</md-toolbar>
		</div>

		<div align="middle">
			<md-button md-colors="{background:'teal-600'}" href="#cockpitMain">
			<!-- Angular will only intercept URL with # prefix, else the request will be sent to back-end Java controller -->
			Open your ACARS terminal </md-button>
		</div>
	</header>
	<section>
		<div ng-view></div>
	</section>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>Mobilon by FCT</title>
<link href="${pageContext.request.contextPath}/static/styles/style.css"
	rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="jquery.poptrox-0.1.js"></script>
</head>
<body>
	<div id="header" class="container">
		<div id="logo">
			<h1>
				<a href="#">Mobilon</a>
			</h1>
			<p>
				We sell phones(<a href="http://www.freecsstemplates.org"></a>
			</p>
		</div>
		<div id="menu">
			<ul>
				<li><a href="${pageContext.request.contextPath}/">Main</a></li>
				<li><a href="${pageContext.request.contextPath}/products">Products</a></li>
				<li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
				<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
				<li><a href="${pageContext.request.contextPath}/cart">Cart
						- ${sessionScope.userCart.size()}</a></li>
				<c:if test="${sessionScope.user != null}">
					<li><a href='${pageContext.request.contextPath}/login?key'>Log
							out</a></li>
				</c:if>

			</ul>
		</div>
	</div>
	<!-- end #header -->
	<div id="poptrox">
		<!-- start -->
		<ul id="gallery">
			<li><img
				src="${pageContext.request.contextPath}/static/images/img01.jpg"
				title="Phasellus nec erat sit amet nibh pellentesque congue."
				width="200" height="200" alt="" /></li>
			<li><a
				href="${pageContext.request.contextPath}/static/images/img02.jpg"><img
					src="${pageContext.request.contextPath}/static/images/img02.jpg"
					title="Phasellus nec erat sit amet nibh pellentesque congue."
					width="200" height="200" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/static/images/img03.jpg"><img
					src="${pageContext.request.contextPath}/static/images/img03.jpg"
					title="Phasellus nec erat sit amet nibh pellentesque congue."
					width="200" height="200" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/static/images/img04.jpg"><img
					src="${pageContext.request.contextPath}/static/images/img04.jpg"
					title="Phasellus nec erat sit amet nibh pellentesque congue."
					width="200" height="200" alt="" /></a></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
		<br class="clear" />
		<script type="text/javascript">
			$('#gallery').poptrox();
		</script>
		<!-- end -->
	</div>
	<div id="page">
		<div id="bg1">
			<div id="bg2">
				<div id="bg3">
					<div id="content">
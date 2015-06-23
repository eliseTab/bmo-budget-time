<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Budget Time</title>
		<link href="/BudgetTime/css/style.css" type="text/css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
	</head>
	<body>
		<div id="page">
			<div id="banner" class="inverted_color">
				<div id="index_screen">
					<img src="/BudgetTime/images/logo.png"/>
					
					<div id="list_buttons">
						<h4 class="list_button list_index_button" id="index_new_user">New User</h4>
						<h4 class="list_button list_index_button" id="index_continue">Continue</h4>
					</div>
				</div>
			</div>
			<div id="content">
				<div id="nav_content">
					<ul id="navlinks"></ul>
					<div id="circle"><img src="/BudgetTime/images/circle.png"/></div>
				</div>
				<div id="cross_button"> <img src="/BudgetTime/images/cross_button.png"/> </div>
				<%@ include file="side_content.html" %>
			</div>
			<div id="bottom_buttons">
				<div class="bottom_button"></div>
				<div class="bottom_button"></div>
			</div>
		</div>
	</body>
	<script src="/BudgetTime/jquery-1.11.3.js"></script>
	<script src="/BudgetTime/script.js" type="text/javascript"></script>
</html>
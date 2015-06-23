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
				<div id="banner_continue_content">
					<h2>******************</h2>
					<h2>Continue</h2>
					<form class="banner_form" method="post" action="continue.do">
						<label><span class="banner_form_label">Username:</span>
							<input type="text" name="username" class="banner_text_field inverted_color inverted_color_border banner_input_font" autofocus required/>
						</label> <br />
						<label><span class="banner_form_label">Password:</span>
							<input type="password" name="password" class="banner_text_field inverted_color inverted_color_border banner_input_font" required/>
						</label>
						<div class="banner_form_buttons">
							<input class="banner_form_button regular_color regular_color_border banner_input_font" type="submit" value="&#10004;" />
							<input class="banner_form_button index_cancel_button regular_color regular_color_border banner_input_font" type="button" value="&#10008;" />
						</div>
					</form>
					<h2>******************</h2>
					<% if(request.getAttribute("error") != null) { %>
						<div class="error">
							<span id="<%=request.getAttribute("error")%>" style="white-space:pre"></span>
						</div>
					<% } %>
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
	<script src="/BudgetTime/typed.js" type="text/javascript"></script>
	<script src="/BudgetTime/script.js" type="text/javascript"></script>
</html>
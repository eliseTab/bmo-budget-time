<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Budget Time</title>
		<link href="/BudgetTime/css/style.css" type="text/css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
	</head>
	<body>
		<%-- <h1 id="title">It's BMO Budget Time!</h1> --%> 
		<div id="page">
			<div id="banner" class="regular_color">
				<h1 class="banner_face">^_^</h1>
				<%@ include file="banner_action.jsp" %>
			</div>
			<div id="content">
				<%@ include file="nav_content.html" %>
				<div id="content_table">
					<table id="envelope_balance_table">
						<thead>
							<tr>
								<th>Envelope</th>
								<th>Balance</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${envelopeList}" var="envelope">
							<tr>
								<td>${envelope.name}</td>
								<td class="center">${envelope.balance}</td>
							</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th class="total">TOTAL</th>
								<th class="total">${totalBalance}</th>
							</tr>
						</tfoot>
					</table>
				</div>
				<%@ include file="side_content.html" %>
			</div>
			<%@ include file="bottom.jsp" %>
		</div>
	</body>
	<script src="/BudgetTime/jquery-1.11.3.js"></script>
	<script src="/BudgetTime/typed.js" type="text/javascript"></script>
	<script src="/BudgetTime/script.js" type="text/javascript"></script>
</html>
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
				<h1 class="banner_face" id="envelope_icon">&#9993;<span id="banner_face_mouth"> 3 </span>&#9993;</h1>
				<div id="banner_envelope_balance">
					<span>${selectedEnvelope.name} Balance:</span> 
					<h2 id="envelope_balance">${selectedEnvelope.balance}</h2>
				</div>							
				<%@ include file="banner_action.jsp" %>
			</div>
			<div id="content">
				<%@ include file="nav_content.html" %>
				<div id="content_table">
					<label>Envelope:
						<select id="envelope_list" class="content_input_font content_list">
							<option disabled selected>Select envelope</option>
							<c:forEach items="${envelopeList}" var="envelope">
								<option>${envelope.name}</option>
							</c:forEach>
						</select>
					</label><br />
					<c:if test="${selectedEnvelope != null}">
						${selectedEnvelope.name} Latest Activities:
						<table id="activity_table">
							<thead>
								<tr>
									<th>Activity</th>
									<th class="activity_amount">Amount</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${selectedEnvelope.transactionList}" var="transaction">
								<tr>
									<td>${transaction.activity}</td>
									<td class="center">${transaction.amount}</td>
									<td class="center">${transaction.date}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
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
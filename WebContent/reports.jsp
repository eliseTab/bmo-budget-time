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
				<h1 class="banner_face">$<span id="banner_face_mouth"> ^ </span>$</h1>
				<div id="banner_report">
					Report
				</div>
				<%@ include file="banner_action.jsp" %>
			</div>
			<div id="content">
				<%@ include file="nav_content.html" %>
				<div id="content_table">
					<form>
						<table id="report_bottom_table">
							<thead>
								<tr>
									<th colspan="2">Pick a Report!</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Type:</td>
									<td class="center">
										<select id="report_list" class="content_input_font content_list">
											<option value="income_list">Income List</option>
											<option value="expenses_list">Expenses List</option>
											<option value="expenses_percentage">Expenses Percentage</option>
											<option value="envelope_balance">Envelope Balance</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>Envelope:</td>
									<td class="center">
										<select id="report_envelope_list" class="content_input_font content_list" disabled>
											<c:forEach items="${envelopeList}" var="envelope">
												<option>${envelope.name}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td>Period:</td>
									<td class="center">
										<select class="content_input_font content_list">
											<option value="daily">Daily</option>
											<option value="monthly">Monthly</option>
											<option value="yearly">Yearly</option>
										</select>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<th colspan="2">
										<div id="report_submit_container"><input type="submit" id="report_submit" class="content_input_font" value="Pull Report" /></div>
									</th>
								</tr>
							</tfoot>
						</table>
					</form>
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
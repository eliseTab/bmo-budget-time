<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="banner_add_income_content">
	<h2>******************</h2>
	<h2>Add Income</h2>
	<form id="add_income_form" class="banner_form" method="post" action="action.do">
		<input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
		<input type="hidden" name="form" value="Add Income"/>
		<label><span class="banner_form_label">Amount:</span>
			<input type="text" name="amount" id="income_amount" class="banner_text_field regular_color regular_color_border banner_input_font" autofocus required/>
		</label> <br />
		<label><span class="banner_form_label">Envelope:</span>
			<input type="hidden" name="envelope" value="Income" />
			<span class="banner_text_field regular_color_border">Income</span>
		</label>
		<label><span class="banner_form_label">Note:</span>
			<input type="text" name="note" class="banner_text_field regular_color regular_color_border banner_input_font"/>
		</label>
		<div class="banner_form_buttons">
			<input class="banner_form_button inverted_color inverted_color_border banner_input_font" type="submit" value="&#10004;" />
			<input class="banner_form_button banner_cancel_button inverted_color inverted_color_border banner_input_font" type="button" value="&#10008;" />
		</div>
	</form>
	<h2>******************</h2>
	<div id="add_income_error" class="banner_error">
		<span id="add_income_error_message"></span>
	</div>
</div>
<div id="banner_add_expense_content">
	<h2>******************</h2>
	<h2>Add Expense</h2>
	<form id="add_expense_form" class="banner_form" method="post" action="action.do">
		<input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
		<input type="hidden" name="form" value="Add Expense"/>
		<label><span class="banner_form_label">Amount:</span>
			<input type="text" name="amount" id="expense_amount" class="banner_text_field regular_color regular_color_border banner_input_font" autofocus required/>
		</label> <br />
		<label><span class="banner_form_label">Envelope:</span>
			<select name="envelope" class="banner_text_field regular_color regular_color_border banner_input_font">
				<c:forEach items="${envelopeList}" var="envelope">
					<option>${envelope.name}</option>
				</c:forEach>
			</select>
		</label>
		<label><span class="banner_form_label">Note:</span>
			<input type="text" name="note" class="banner_text_field regular_color regular_color_border banner_input_font"/>
		</label>
		<div class="banner_form_buttons">
			<input class="banner_form_button inverted_color inverted_color_border banner_input_font" type="submit" value="&#10004;" />
			<input class="banner_form_button banner_cancel_button inverted_color inverted_color_border banner_input_font" type="button" value="&#10008;" />
		</div>
	</form>
	<h2>******************</h2>
	<div id="add_expense_error" class="banner_error">
		<span id="add_expense_error_message"></span>
	</div>
</div>
<div id="banner_transfer_funds_content">
	<h2>******************</h2>
	<h2>Transfer Funds</h2>
	<form id="transfer_funds_form" class="banner_form" method="post" action="action.do">
		<input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
		<input type="hidden" name="form" value="Transfer Funds"/>
		<label><span class="banner_form_label">Amount:</span>
			<input type="text" name="amount" id="transfer_amount" class="banner_text_field regular_color regular_color_border banner_input_font" autofocus required/>
		</label> <br />
		<label><span class="banner_form_label">From Env:</span>
			<select id="source_envelope_list" name="source_envelope" class="banner_text_field regular_color regular_color_border banner_input_font">
				<c:forEach items="${envelopeList}" var="envelope">
					<option>${envelope.name}</option>
				</c:forEach>
			</select>
		</label>
		<label><span class="banner_form_label">To Env:</span>
			<select id="dest_envelope_list" name="dest_envelope" class="banner_text_field regular_color regular_color_border banner_input_font">
				<c:forEach items="${envelopeList}" var="envelope">
					<option>${envelope.name}</option>
				</c:forEach>
			</select>
		</label>
		<div class="banner_form_buttons">
			<input id="transfer_submit" class="banner_form_button inverted_color inverted_color_border banner_input_font" type="submit" value="&#10004;" />
			<input class="banner_form_button banner_cancel_button inverted_color inverted_color_border banner_input_font" type="button" value="&#10008;" />
		</div>
	</form>
	<h2>******************</h2>
	<div id="transfer_funds_error" class="banner_error small_banner_error_font">
		<span id="transfer_funds_error_message"></span><br />
		<span id="transfer_funds_error_message_two"></span><br />
	</div>
</div>
<div id="banner_add_envelope_content">
	<h2>******************</h2>
	<h2>Add Envelope</h2>
	<form id="add_env_form" class="banner_form" method="post" action="action.do">
		<input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
		<input type="hidden" name="form" value="Add Envelope"/>
		<label><span class="banner_form_label">Env Name:</span>
			<input type="text" id="new_envelope" name="envelope" class="banner_text_field regular_color regular_color_border banner_input_font" autofocus required/>
		</label> <br />
		<label><span class="banner_form_label">Amount:</span>
			<input type="text" name="amount" id="env_amount" class="banner_text_field regular_color regular_color_border banner_input_font" value="0.00"/>
		</label>
		<div class="banner_form_buttons">
			<input id="add_envelope_submit" class="banner_form_button inverted_color inverted_color_border banner_input_font" type="submit" value="&#10004;" />
			<input class="banner_form_button banner_cancel_button inverted_color inverted_color_border banner_input_font" type="button" value="&#10008;" />
		</div>
	</form>
	<h2>******************</h2>
	<div id="add_env_error" class="banner_error small_banner_error_font">
		<span id="add_env_error_message"></span><br />
		<span id="add_env_error_message_two"></span>
	</div>
</div>
<div id="banner_remove_envelope_content">
	<h2>******************</h2>
	<h2>Remove Envelope</h2>
	<form class="banner_form" method="post" action="action.do">
		<input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
		<input type="hidden" name="form" value="Remove Envelope"/>
		<label><span class="banner_form_label">Env:</span>
			<select name="envelope" class="banner_text_field regular_color regular_color_border banner_input_font" autofocus>
				<c:forEach items="${envelopeList}" var="envelope">
					<c:if test="${envelope.name != 'Income'}">
						<option>${envelope.name}</option>
					</c:if>
				</c:forEach>
			</select>
		</label>
		<div class="banner_form_buttons">
			<input class="banner_form_button inverted_color inverted_color_border banner_input_font" type="submit" value="&#10004;" />
			<input class="banner_form_button banner_cancel_button inverted_color inverted_color_border banner_input_font" type="button" value="&#10008;" />
		</div>
	</form>
	<h2>******************</h2>
</div>
<div id="banner_exit_menu_content" class="inverted_color">
	<h2>******************</h2>
	<h2>Leaving BMO? :(</h2>
	<br />
	<div id="list_buttons">
		<h4 class="list_button list_exit_button" id="no_exit">No way in Ooo!</h4>
		<h4 class="list_button list_exit_button" id="exit">Save and Exit</h4>
	</div>
	<h2>******************</h2>
</div>									
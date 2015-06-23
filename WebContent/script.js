if($('#hello').length > 0){
	$('#hello').typed({
		strings: ['It\'s...^1000'],
	    typeSpeed: 50,
	    backDelay: 500,
	    //loop: true,
	    // defaults to false for infinite loop
	    loopCount: false,
	    cursorChar: "_",
	    callback: function(){
	    	$('#hello_container').hide();
	    	$('#hello_logo').slideDown('slow', function(){
	    		$('#hello_sword').fadeIn('slow');
	    		$('#hello_sword').animate({
	        	    left: "+=50"
	        	  }, 500, function() {
	        		  $('#greeting').delay(1000).fadeOut('slow', function(){
	        			  $(location).attr('href','content.do');
	        		  });
	        	  }
	    		);
	    	});
	    }
	});
}
$('.list_index_button').on('click', function(){
	$indexButtonId = $(this).attr('id');
	if($indexButtonId == 'index_new_user')
		$(location).attr('href','register.jsp');
	else if($indexButtonId == 'index_continue')
		$(location).attr('href','continue.jsp');
});
$('.index_cancel_button').on('click', function(){
	$(location).attr('href','index.jsp');
});
$(document).ready(function(){
	var $path = $(location).attr('pathname');
	var $filename= $path.split('/').pop().split('.').shift();
	$('li.navlink').removeClass('selected');
	$('li#'+$filename).addClass('selected');
});
$('.sublink').on('click', function(){
	$('#banner').children().hide();
	$sublinkId = $(this).attr('id');
	if($sublinkId == 'add_income_sublink')
		$('#banner_add_income_content').show();
	else if($sublinkId == 'add_expense_sublink')
		$('#banner_add_expense_content').show();
	else if($sublinkId == 'transfer_funds_sublink')
		$('#banner_transfer_funds_content').show();
	else if($sublinkId == 'add_envelope_sublink')
		$('#banner_add_envelope_content').show();
	else if($sublinkId == 'remove_envelope_sublink')
		$('#banner_remove_envelope_content').show();
});
$('#add_income_form').submit(function(event){
	if(!$.isNumeric($('#income_amount').val())){
		$('#add_income_error').show();
		$('#add_income_error_message').typed({
			strings: ['Please use numeric input for the amount :o'],
		    typeSpeed: 50,
		    backDelay: 1000,
		    loopCount: false,
		    cursorChar: "_"	   
		});
		event.preventDefault();
	}	
})
$('#add_expense_form').submit(function(event){
	if(!$.isNumeric($('#expense_amount').val())){
		$('#add_expense_error').show();
		$('#add_expense_error_message').typed({
			strings: ['Please use numeric input for the amount :o'],
		    typeSpeed: 50,
		    backDelay: 1000,
		    loopCount: false,
		    cursorChar: "_"	   
		});
		event.preventDefault();
	}	
})
$('#transfer_funds_form').submit(function(event) {
	if(!$.isNumeric($('#transfer_amount').val())){
		$('#transfer_funds_error').show();
		$('#transfer_funds_error_message').typed({
			strings: ['Please use numeric input for the amount :o'],
		    typeSpeed: 50,
		    backDelay: 1000,
		    loopCount: false,
		    cursorChar: "_"	   
		});
		event.preventDefault();
	}
	if($('#source_envelope_list').val() == $('#dest_envelope_list').val()){
		$('#transfer_funds_error').show();
		$('#transfer_funds_error_message_two').typed({
			strings: ['Envelopes should not be the same :o'],
		    typeSpeed: 50,
		    backDelay: 1000,
		    loopCount: false,
		    cursorChar: "_"	   
		});
		event.preventDefault();
	}	
});
$('#add_env_form').submit(function(event){
	if(!$.isNumeric($('#env_amount').val())){
		$('#add_env_error').show();
		$('#add_env_error_message').typed({
			strings: ['Please use numeric input for the amount :o'],
		    typeSpeed: 50,
		    backDelay: 1000,
		    loopCount: false,
		    cursorChar: "_"	   
		});
		event.preventDefault();
	}
	var isNewName = true;
	$('#source_envelope_list option').each(function(){
		if($('#new_envelope').val() == $(this).text()){
			isNewName = false;
			return false;
		}
	});	
	if(!isNewName){
		$('#add_env_error').show();
		$('#add_env_error_message_two').typed({
			strings: ['Envelope already exists :o'],
		    typeSpeed: 50,
		    backDelay: 1000,
		    loopCount: false,
		    cursorChar: "_"	   
		});
		event.preventDefault();
	}
});
$('.banner_cancel_button').on('click', function(){
	$bannerContent = $(this).parent().parent().parent();
	$bannerContent.find('input[type=text], input[type=password]').val('');
	$('.banner_error').hide();
	$bannerContent.find('select').each(function(){
		$(this).val($(this).children(':first').val());
	});
	$bannerContentParent = $bannerContent.parent();
	$bannerContentParent.children().hide();
	$bannerContentParent.children(':first').show();
});
$('#envelope_list').change(function() {
	$selectedEnvelope = $('#envelope_list option:selected').text();
	$(location).attr('href','envelopes.do?envelope=' + $selectedEnvelope);
});
if($('#envelope_balance').text().length > 0){
	$('#banner').children().hide();
	$('#banner_envelope_balance').show();
}
$('#report_list').change(function() {
    if($(this).val() == 'envelope_balance')
    	$('#report_envelope_list').attr('disabled', false);
    else
    	$('#report_envelope_list').attr('disabled', true);
});
$('.bottom_button_action').on('click', function(){
	$('#banner').children().hide();
	$('#banner_exit_menu_content').show();
});
$('.list_exit_button').on('click', function(){
	$exitButtonId = $(this).attr('id');
	if($exitButtonId == 'no_exit'){
		$bannerContent = $(this).parent().parent().parent();
		$bannerContent.children().hide();
		$bannerContent.children(':first').show();
	}
	else if($exitButtonId == 'exit'){
		$(location).attr('href','exit.do');
	}
});
$('#register_error').typed({
	strings: ['Username already exists!\nPlease try another.'],
    typeSpeed: 50,
    backDelay: 1000,
    loopCount: false,
    cursorChar: "_",
    //callback: function() {foo();}
});
$('#password_error').typed({
	strings: ['Incorrect password!'],
    typeSpeed: 50,
    backDelay: 1000,
    loopCount: false,
    cursorChar: "_",
    //callback: function() {foo();}
});
$('#unknown_user_error').typed({
	strings: ['BMO doesn\'t recognize you.\nPlease <a href="register.jsp">register.</a>'],
    typeSpeed: 50,
    backDelay: 1000,
    loopCount: false,
    cursorChar: "_",
    //callback: function() {foo();}
});
if($('#bye').length > 0){
	$('#bye_logo').fadeIn('medium');
	$('#bye_sword').fadeIn('medium').delay(500).animate({left: "-=50"}, 500, 
		function(){
			$('#bye_sword').fadeOut('slow');
			$('#bye_logo').slideUp('slow', function(){
				$('#bye_container').show()
				$('#bye').typed({
					strings: ['Bye!',':(^1000'],
				    typeSpeed: 50,
				    backSpeed: 50,
				    backDelay: 1000,
				    loopCount: false,
				    cursorChar: "_",
				    callback: function(){
				    	$(location).attr('href','index.jsp');
				    }
				});
			});
			
		}
	);
}

/*
 * SimpleModal Confirm Modal Dialog
 * http://simplemodal.com
 *
 * Copyright (c) 2013 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */

jQuery(function ($) {
	$('#confirm-dialog input.confirm, #confirm-dialog a.exit_confirm').click(function (e) {
		e.preventDefault();

		// example of calling the confirm function
		// you must use a callback function to perform the "yes" action
		confirm("Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page?", function () {
			
		});
	});
	
	$('#confirm-dialog a.help_confirm').click(function (e) {
		e.preventDefault();

		// example of calling the confirm function
		// you must use a callback function to perform the "yes" action
		help_confirm("Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page?", function () {
			
		});
	});
	
	$('#confirm-dialog a.pause_confirm').click(function (e) {
		e.preventDefault();

		// example of calling the confirm function
		// you must use a callback function to perform the "yes" action
		pause_confirm("Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page?", function () {
			
		});
	});
	
	$('#confirm-dialog a.quit_confirm').click(function (e) {
		e.preventDefault();

		// example of calling the confirm function
		// you must use a callback function to perform the "yes" action
		quit_confirm("Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page?", function () {
			
		});
	});
});


function confirm(message, callback) {
	
	$('#confirm').modal({
		closeHTML: '<button class="btn btn-default" id="star">Confirm</button>',
		position: ["20%"],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			var modal = this;

			$('.message', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				
			
				modal.close(); // or $.modal.close();

			});
			
		}

	});
	
   
	
	$('#star').click(function(){
		//	//alert(" STar is Clicked ");
			////alert(' page value is: '+$('#hideValue').val());
		//	//alert(' in Star CLcied ');
		//alert("goint to existSectionAnswersVerify()");
		$('#index').val(0);
		goToNewSection('fromConfrom');
		exitSectionAnswersverify();
		
 	
	 
		});
	 
	
	
	
	}

function exit_return(){
	
	$.modal.close();
}

function quit_return(){
	
	$.modal.close();
}

function saveTest()
{
	var sectiontime =$("#sectionTime").val($('#countdown').html()); 
	$.ajax({ 
  		type : "POST",
  		url :"quitTestResult?questionid="+$("#questionid").val()+"&index="+$("#index").val()+"&time="+$("#sectionTime").val()+"&status=INCOMPLETE",
  		dataType : 'json',
  		success : function(data,textStatus,jqXHR){  
  			showExitpage();
		},
		error:function(data,textStatus,errorThrown){
			//alert("Error occured while process the request"+e);
		}
  	}); 
	modal.close();
}
function help_confirm(message, callback) {
	
	$('#confirm').modal({
		closeHTML: '<button class="btn btn-default" id="">Confirm</button>',
		position: ["20%"],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			var modal = this;

			$('.message', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				modal.close(); // or $.modal.close();

			});
			
		}

	});
	
	
}

function pause_confirm(message, callback) {
	$('#confirm').modal({
		closeHTML: '<button class="btn btn-default" id="pause">Confirm</button>',
		position: ["20%"],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			var modal = this;

			$('.message', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				modal.close(); // or $.modal.close();

			});
		}
	
	});
	
	$('#pause').click(function(){
		
		Example2.Timer.toggle();
		Example1.Timer.toggle();
	});
	
}

function quit_confirm(message, callback) {
	
	$('#confirm').modal({
		closeHTML: '<button class="btn btn-default" id="quit">Confirm</button>',
		position: ["20%"],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			var modal = this;

			$('.message', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				modal.close(); // or $.modal.close();

			});
			
		}

	});
	
	
$('#quit').click(function(){
		//	//alert(" STar is Clicked ");
			//alert("quiting the test"+$("#index").val());
			var sectiontime =$("#sectionTime").val($('#countdown').html());
			//alert("sectiontime"+$("#sectionTime").val());
			
	$.ajax({ 
  		type : "POST",
  		url :"quitTestResult?questionid="+$("#questionid").val()+"&index="+$("#index").val()+"&time="+$("#sectionTime").val()+"&status=COMPLETE",
  		dataType : 'json',
  		success : function(data,textStatus,jqXHR){  
  			$('#questionsForm').attr('action','full_length_result');
			$('#questionsForm').submit(); 
		},
		error:function(data,textStatus,errorThrown){
			//alert("Error occured while process the request"+e);
		}
  	}); 
	
		//showExitpage();
			//$('#row').html("Start to take a new Test");
			
		});
	
}


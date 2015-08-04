function getExitSectionPage(){

	$.ajax({ 
  		type : "POST",
  		url :"Exit-Section-Page.action",
  		success : replaceDivWithExistSectionPage,
  		error: errorAtGettingExistSectionPage
  	}); 
	
}


function replaceDivWithExistSectionPage(replaceDivWithExistSectionPage){
	
	$('#overlayText').html( replaceDivWithExistSectionPage);
	
	
}


function errorAtGettingExistSectionPage(){
	
	
	
}

function goToNewSection(gatePass){
	////alert($('#questionsData').val());
	if($('#sectionNumber').val()==2) $('#breakTime').val(600);
	else $('#breakTime').val(60);
	
	
	$.ajax({ 
  		type : "POST",
  		url :"one-minute-break-Page.action",
  		success : replaceDivWithNewSectionPage,
  		error: errorAtGettingNewExistSectionPage
  	}); 
	
}

function replaceDivWithNewSectionPage(replaceDivWithNewSectionPage){

	$('#question_div').html( replaceDivWithNewSectionPage);
	$('#_quit,#_exit,#showCalc,#_review,#_mark,#_help,#_back,#_next,#_pause,#_timer').hide(); 
	$('#_Continue').show();
	$('#_buttonsArea').removeClass('col-lg-6 col-lg-offset-6').addClass('col-lg-1 col-lg-offset-11');
	$.APP.startTimer('cd'); // For Timer Start 
	
	
}


function  errorAtGettingNewExistSectionPage(errorAtGettingNewExistSectionPage){
	
	////alert("Error is comming from New Exit Section page");
	
	
}


function returnFromBackQuestion(){
	
	
	
	$.ajax({ 
  		type : "POST",
  		url :"Start-Practice-Test.action",
  		success : replaceDivWithOldSectionPage,
  		error: errorAtGettingOldExistSectionPage
  	}); 	
}
function replaceDivWithOldSectionPage(replaceDivWithOldSectionPage){
	
	
	
	$('#question_div').html(replaceDivWithOldSectionPage);
}

function errorAtGettingOldExistSectionPage(errorAtGettingOldExistSectionPage){
	////alert("Error is comming the Old Section ");
	
	
}

function exitSectionAnswersverify(){
	 var dt=$('#questionsForm').serialize();
	 //alert("Calling");
	//alert("Section Number while submitting : "+$('#sectionNumber').val());
	 
	 if($('#sectionNumber').val()==5)
		{
		 //alert("Calling from IF");
			$.ajax({ 
		  		type : "POST",
		  		url :"submitFullLengthTest-Exit-Section-Answers.action?currentsectionstatus='COMPLETE'",
		  		data:dt, 
		  		success : function success(){
		  			// //alert("calling from if success");
		  			  $('#questionsForm').attr('action','full_length_result.action');
			  	      $('#questionsForm').submit(); 
		  		},
		  		error:error
		  	});
			   
		}
	 else
		 {
		 //alert("Calling from else ");
	$.ajax({ 
  		type : "POST",
  		url :"submitFullLengthTest-Exit-Section-Answers.action?currentsectionstatus='COMPLETE'",
  		data:dt, 
  		success : successes,
  		error:error
  	}); 	
	
		 }
	
}

function successes(){
	
	
}
function error(){
	
	////alert("Error Answer Data");
	
}
function returnConfirm(){
	
	 $.modal.close();
}

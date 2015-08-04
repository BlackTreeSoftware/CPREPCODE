/*
 * This js file mainly used for the first section as either quant or verbal
 */

$(document).ready(function(){
//  alert('haiii sf sdfdfsafadasfa');
	generateWhichSection();
	
	$('#_Continue').click(function(){
		
		$.APP.resetTimer();
		goToInstructions();
		
	});
	
	$('.instructions').click(function(){
		
		//alert("Instructions called");
		
		$('a.btn.instructions').hide();
		$('a.btn.please').show();
		//$(this).removeClass('instructions');
		
	});
	
});


function goToInstructions(){ 

	$.ajax({ 
  		type : "POST",
  		url :"getNextSectionInstructions.action",
  		success : setNextSectionInstructions,
  		error: errorOccuredAtNextSection
  	});
	
}


function setNextSectionInstructions(instructionsHtmlScript){
	 
	//alert("html script : "+instructionsHtmlScript);
	//alert("Start Section :"+$('#startSection').val()+"   Section Number : "+$('#sectionNumber').val());
	$('#_reviewBtn').hide();
	$('#question_div').html(instructionsHtmlScript);
	if($('#startSection').val()==81){
		 
		if((parseInt($('#sectionNumber').val())+1)%2==0){
			//alert('Not 86:'+" "+$('#sectionNumber').val());
			$('#_quantInstructions').hide();
			 $('#_verbalInstructions').show();
			 $('#sectionTimerValue').val(30*6000);
		}
		else{
			$('#_verbalInstructions').hide();
			$('#_quantInstructions').show();
			$('#sectionTimerValue').val(35*6000);
		}
	}
	else{
		
		
		if((parseInt($('#sectionNumber').val())+1)%2==0){
			//alert('Not 81:'+" "+$('#sectionNumber').val());
			$('#_verbalInstructions').hide();
			$('#_quantInstructions').show();
			$('#sectionTimerValue').val(35*6000);
		}
		else{
			$('#_quantInstructions').hide();
			$('#_verbalInstructions').show();
			$('#sectionTimerValue').val(30*6000);
		}
	}
	
	/*if($('#ins').html()=="resume")
		{
		$('#_quantInstructions').hide();
		$('#_verbalInstructions').hide();
		
		}*/
	
	hideButtons();
	
	
}

function hideButtons(){
	
	$('#_Continue,.less-hr,a.btn.please,#_buttonsArea').hide();
	$('a.btn.instructions').show();
}

function generateWhichSection(){
	
	$.ajax({ 
  		type : "POST",
  		url :"getWhichSection.action",
  		dataType:"json",
  		success : setStartingSection,
  		error: errorOccuredAtWhichFunction
  	}); 
}

function setStartingSection(data){ 
	
	var sectionNumber=data.currentSection; 
	$('#sectionnumber').html('Section '+sectionNumber+' of '+$('#totalSections').val());
	//$('#sectionNumber').val(sectionNumber);
	$('#startSection').val(data.quantOrVerbal);
	if($('#startSection').val()=='81'){
		$('#_verbalInstructions').hide();
		$('#sectionTimerValue').val(35*6000);
	}
	else{
		$('#_quantInstructions').hide();
		$('#sectionTimerValue').val(30*6000);
	}
	//alert($('#startSection').val()); 
}

function errorOccuredAtWhichFunction(){
	
}

function errorOccuredAtNextSection(){
	
}
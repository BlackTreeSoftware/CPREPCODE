 $.fn.initialDocument= function() {
	  $('#accessTypeId').prop("checked",true);
	  $('#singleansradioId').prop("checked",true);  
	  $('#multipleAnsDivId').hide();
	  $('#activeId').prop("checked",true);
	  $('#questionId').val(0);
	  $("#skillId").select2({
			maximumSelectionSize: 3,
			placeholder:"Please Select skills"
		});
	  $('#multipleradioButton').prop("enable",true); 
	  $('#selectInansradioId').prop("enable",true); 
		
};
$.fn.mulitipleansradioClick= function() {
	$('#multipleAnsDivId').show();
	$('#singleAnsChoiceDivId').hide();
	$('#sinlgeAnsAnswerDivId').hide();
	$('#singleAnswer').val('');
	$('#sinlgechoiceRadiobtn').prop('checked',true);
	$('#singleAnswer').val('');
	$('input[name=sinlgechoiceRadiobtn]:radio').prop('checked',false);
	 $.fn.makingsingleAnsdivReset();
};
$.fn.selectInansradioClick= function() {
	$('#multipleAnsDivId').hide();
	$('#singleAnsChoiceDivId').hide();
	$('#sinlgeAnsAnswerDivId').show();
	$('#singleAnswer').attr("readonly",false); 
	$('#sinlgeAnsAnswerDivId').addClass('col-lg-6 col-lg-offset-5');
	$('#answerId').removeClass();
	$('#answerId').addClass('col-lg-2 control-label');
	
	 $.fn.makingsingleAnsdivReset();
	 $.fn.makingMultipleAnsdivReset();
	
	
	
};
$.fn.singleansradioClick= function() {
	$('#multipleAnsDivId').hide();
	$('#singleAnsChoiceDivId').show();
	$('#sinlgeAnsAnswerDivId').show();
	$.fn.makingMultipleAnsdivReset();	 
	$('input[name=multipleradioButton]:checkbox').prop('checked',false);
	$('#answerId').removeClass();
	$('#answerId').addClass('col-lg-2 col-lg-offset-1 control-label');
	$('#singleAnswer').attr("readonly",true); 
	$('#sinlgeAnsAnswerDivId').removeClass();
	$('#sinlgeAnsAnswerDivId').addClass('col-lg-6');
	
};
$.fn.savenewQuestion= function() { 
	//alert("from save new question ");
	  var question = CKEDITOR.instances['question'].getData();
	  var solutionText = CKEDITOR.instances['solutionText'].getData();
	   $('#questionId1ans').val(question);
	  $('#solutionTextId1').val(solutionText);
	  
$.ajax({
	type : 'POST',
	url : 'newReadingComprehensionQuestion.action',
	data : $( "#form" ).serialize(),
	dataType : 'JSON',
    success :function(result){
    	 //alert(result.result);
		if(result.result=="success")
			{
			  toastr.success("Reading Comprehenstion Question Saved Successfully");
			  $.fn.resetFunction();
			}
		else if(result.result=="update")
			{
			  toastr.success("Reading Comprehenstion Question Updated Successfully"); 
			  $.fn.resetFunction();
			  $('#submit').html("Submit");
			}
		
		else
			{
		  toastr.error("Reading Comprehenstion Question not saved please try again later"); 
			}
	},
	error : function(e){
		 toastr.error("Error"); 
	 
	}
});
 
};
$.fn.mulitpleRadiobuttonClick= function(e) {
	  
	if(e.checked)
	{ 
		 if($('#questionId').val()!=0)
		  {
			  
			 for(var k=1;k<=6;k++)
			  {
				 var ansIds="";
                 for(var j=1;j<=6;j++)
	             {
	 
                    ansIds="#answer"+j;
   
                   if($(ansIds).val().length==0)
	                    	{ 
	                      	break;
		                      }  
	
	                      } 
 

              var id='#'+e.value;
		      $(ansIds).val($(id).val());
		      $(id).attr('readonly', 'readonly'); 
		     break; 
		     } 
			 }//if
		 else{ 
	  var id='#'+e.value;
 
	  for(var k=1;k<=6;k++)
		  {
		  var ansIds='#answer'+k;
		  if($(ansIds).val().length==0)
			  { 
			  if($(id).val().length!=0)
				  {
	      $(ansIds).val($(id).val());
	      $(id).attr('readonly', 'readonly');
				  }
			  else
				  {
				  e.checked=false;
				  $(id).prop('readonly', false);
				  }
	  break;
		  }
		  }
	  
		 }//else
	  
	  
	  
	}
else
	{
	  var id='#'+e.value;
	  for(var k=1;k<=6;k++)
	  {
	  var ansIds='#answer'+k; 
	  if($(ansIds).val().length==0)continue;
	  else if($(id).val().trim()==$(ansIds).val().trim())
		  {
		  $(ansIds).val('');
		  $(id).prop('readonly', false);
		  break;
	}
	  }
	}
	
	 
	  
};
$.fn.singleRadiobuttonClick= function(curr,prev) {
	   
	 var id='#'+curr.value;  //alert($(id).val().length);
	 if($(id).val().length!=0)
		 { 
	     $('#singleAnswer').val($(id).val());
	     $(id).attr("readonly",true);
	    
	     if(prev!=''){	    	 
	     var preId='#'+prev.value; 	   
	     $(preId).attr("readonly",false);
	     }
		 }
	 else
		 {
		 $('input[name=sinlgechoiceRadiobtn]:radio').prop('checked',false);
		   }
	 if(curr.value==prev.value)
		 {
		 $(id).prop('checked',true);
		 $(id).attr("readonly",true);
		 }
	 
	 
	 if($('#questionId').val()!=0)
	 {
 
	
	 for(var i=1;i<=5;i++)
		 {
		 var options='#option'+i; var radio="#singleRadio"+i;
		 
		 if($(options).val().trim()==$('#singleAnswer').val().trim())
			 { 
			 $(radio).prop('checked', true);
			 $(options).attr('readonly', true);
			 
			 }
		 else
			 {
		     $(options).attr('readonly', false);
			 }
		 }
	  
	 }
  
	 
	   
};
  
$.fn.resetFunction= function() {
	  $("#questionId").val("0");	
	  $('#accessTypeId').prop("checked",true);
	  $('#referralId').prop("checked",false);
	  $('#singleansradioId').prop("checked",true);  
	  $('#multipleAnsDivId').hide();
	  $('#singleAnsChoiceDivId').show();
	  $('#sinlgeAnsAnswerDivId').show();
	  $('#activeId').prop("checked",true);
	  $("#testId").val($("#testId option:first").val());
	  $('input[type=text]').val(''); 
	  $("#directions").val("");	
	  $("#passagetypeId").val($("#passagetypeId option:first").val());
	  $("#passageId").val($("#passageId option:first").val());
	  CKEDITOR.instances['question'].setData('');
	  CKEDITOR.instances['solutionText'].setData(''); 
	  $('input[name=sinlgechoiceRadiobtn]:radio').prop('checked',false);		 
	  $('input[name=multipleradioButton]:checkbox').prop('checked',false); 
	  $("#singleAnswer").val("");	
	  $("#skillId").select2('val','');
	  $("#difficultyId").val($("#difficultyId option:first").val());
	  $.fn.makingsingleAnsdivReset();
      $.fn.makingMultipleAnsdivReset();
      $('#multipleansradioId').attr('disabled', false);
      $('#selectInansradioId').attr('disabled', false);
      $('#singleansradioId').prop("disabled",false); 
      $('#sinlgeAnsAnswerDivId').removeClass();
      $('#sinlgeAnsAnswerDivId').addClass('col-lg-6');      
	  $('#answerId').removeClass();
	  $('#answerId').addClass('col-lg-2 col-lg-offset-1 control-label');
	  $('#singleAnswer').attr("readonly",true);
	  
		 
      
      
	 
};
$.fn.passageTypeOnChange= function(param) {

	 var data1=param;	  
	 if(data1==-1)
	 {
		 data1="ALL";
		 }
     $.getJSON('getPassageData.action', {
   	  questionType : data1
     }, function(jsonResponse) {
   	  var list=jsonResponse.result; 
   	  var select = $('#passageId');
         select.find('option').remove();
         $("#passageId").get(0).options[$("#passageId").get(0).options.length] = new Option("Select", -1);
         $.each(list, function(index, item) {	        	 
         $("#passageId").get(0).options[$("#passageId").get(0).options.length] = new Option(item.passage_title, item.passage_id);
         });
     
     });
};
     $.fn.editRc= function() {
    	// alert("Calling");
    	 
    	 if($('#accessType1').val()=='FREE')
	    	{ 
	    	$("input[name='accessType']").prop('checked', true);
	    	}
	    else
	    	{
	    	$("input[name='accessType']").prop('checked', false);
	    	}
 $('#directions').val($('#directions1').val());

if($('#referral1').val()=='YES')
	{
	
	$("input[name='referral']").prop('checked', true);
	}
else
	{
	$("input[name='referral']").prop('checked', false);
	}
if($('#status1').val()=='ACTIVE')
	{
	 $('#activeId').prop("checked",true);
	}
else
	{
	 $('#inActiveId').prop("checked",true);
	}
 
CKEDITOR.instances['question'].setData($('#questionId1ans').val());
CKEDITOR.instances['solutionText'].setData($('#solutionTextId1').val());


	 if($('#questionType1').val()=="Single Choice")
	 {
		 $('#multipleansradioId').attr('disabled', true);$('#selectInansradioId').attr('disabled', true);
		  $('#singleansradioId').prop("checked",true);  
		 $('#multipleAnsDivId').hide();
	 }
	 else if($('#questionType1').val()=="Multiple Choice"){
		 $('#singleansradioId').attr('disabled', true);$('#selectInansradioId').attr('disabled', true);
		   $('#multipleansradioId').prop("checked",true); 
		   $('#singleAnsChoiceDivId').hide();
			$('#sinlgeAnsAnswerDivId').hide(); 
	 }
	 else
		 {
		 $('#singleansradioId').attr('disabled', true);$('#multipleansradioId').attr('disabled', true);
		 $('#selectInansradioId').prop("checked",true); 
		 $('#multipleAnsDivId').hide();
		 $('#singleAnsChoiceDivId').hide();
		 $('#singleAnswer').attr("readonly",false);
		 } 
	 if($('#singleansradioId').prop('checked'))
		 { 
		
		 for(var i=1;i<=5;i++)
			 {
			 var options='#option'+i; var radio="#singleRadio"+i;
			// alert($(options).val().trim() +$('#singleAnswer').val().trim());
			 if($(options).val().trim()==$('#singleAnswer').val().trim())
				 {
				// alert($(options).val().trim() +$('#singleAnswer').val().trim());
				 $(radio).prop('checked', true);
				 $(options).attr('readonly', true);
				 break;
				 }
			 }
		  
		 }
	 if($('#multipleansradioId').is(':checked'))
		 {
		// alert("hi");
		 for(var j=1;j<=6;j++)
		 {  
			 var answers="#answer"+j;//alert($(answers).val());
			 for(var i=1;i<=6;i++)
			 {
				 var choicescheck='#multicheckId'+i;var choices="#multipleChoiceId"+i;//alert($(choices).val().trim()+"\t "+$(answers).val().trim());
				 if($(choices).val().trim()==$(answers).val().trim())
				 {
					 $(choicescheck).prop('checked',true);
					 $(choices).attr('readonly',true);
				 }
				 
			 }
		 }
		 }
	 if($('#selectInansradioId').is(':checked')){
		    $('#sinlgeAnsAnswerDivId').addClass('col-lg-6 col-lg-offset-5');
			$('#answerId').removeClass();
			$('#answerId').addClass('col-lg-2 control-label'); 
	 }
	  
	 
	 
     };
     
     
     
     
     
     $.fn.makingsingleAnsdivReset= function(param) {
    	    $('#option1').val('');$('#option2').val('');$('#option3').val('');
    		$('#option4').val('');$('#option5').val('');
    		$('#option1').attr("readonly",false);$('#option2').attr("readonly",false);$('#option3').attr("readonly",false);
    		$('#option4').attr("readonly",false);$('#option5').attr("readonly",false);
    };
    $.fn.makingMultipleAnsdivReset= function(param) {
    	$('#multipleChoiceId1').val('');$('#multipleChoiceId2').val('');$('#multipleChoiceId3').val('');$('#multipleChoiceId4').val('');
    	$('#multipleChoiceId5').val('');$('#multipleChoiceId6').val('');
    	$('#multipleChoiceId1').attr("readonly",false);$('#multipleChoiceId2').attr("readonly",false);$('#multipleChoiceId3').attr("readonly",false);
    	$('#multipleChoiceId4').attr("readonly",false);$('#multipleChoiceId5').attr("readonly",false);$('#multipleChoiceId6').attr("readonly",false);
     
    	$('#answer1').val('');$('#answer2').val('');$('#answer3').val('');$('#answer4').val('');
    	$('#answer5').val('');$('#answer6').val('');
  
};
$.fn.validateforInsertion= function(param) {

	   var question = CKEDITOR.instances['question'].getData();
		  var solutionText = CKEDITOR.instances['solutionText'].getData();
		  $('#questionId1ans').val(question);
		  $('#solutionTextId1').val(solutionText);
	if($('#testId').val()==-1)
		{
		 toastr.error("please select Test Name");
		}
	else
		{
		if(($('#questionTitle').val()==""||$('#questionTitle').val().length==0)||$('#questionTitle').val().indexOf(" ")==0)
			{
			 toastr.error("please Enter Question Title");
			}
		else
			{
			 if($('#directions').val()=="" || $('#directions').val().length==0||$('#directions').val().indexOf(" ")==0)
			 {  
				 toastr.error("Please Enter Directions"); 
			 }
			 else
				 {
				 if($('#passagetypeId').val()==-1)
					 {
					 toastr.error("Please Select Passage Type"); 
					 }
				 else
					 {
					 if($('#passageId').val()==-1)
						 {
						 toastr.error("Please Select Passage"); 
						 }
					 else
						 {
						 if($('#questionId1ans').val()=="" || $('#questionId1ans').val().length==0||$('#questionId1ans').val().indexOf(" ")==0)
						 {  
							 toastr.error("Please Enter Question"); 
						 }
						 else
							 {
							  var b=false;
							 if($("#singleansradioId").is(":checked"))
								 {
								 var k=0;
								 for(var i=1;i<=5;i++)
									 {
									
									 var id='#option'+i;
									 if($(id).val()==""||$(id).val().length==0)
										 {
										++k;
										 }
									
									 }
								 if(k>0)
								 {
								 toastr.error("Please Enter All Choices"); 
								 }
							 else
								 {
								 if($('#singleAnswer').val()==""||$('#singleAnswer').length==0||$('#singleAnswer').val().indexOf(" ")==0)
									 {
									 toastr.error("Please Choose the Answer");
									 }
								 else
									 b=true;
								 
								 }
								 }
							 else if( $("#multipleansradioId").is(":checked"))
								 {
								 var k=0;
								 for(var i=1;i<=6;i++)
								 {
								 var id='#multipleChoiceId'+i;
								
								 if($(id).val()==""||$(id).val().length==0)
									 {
									 ++k;
									
									 }
								 
									 }
								 if(k>0)
								 {
								 toastr.error("Please Enter All Choices"); 
								 }
							 else
								 {
								 var k=0;
								 for(var i=1;i<=6;i++)
								 {
								 var id='#answer'+i;
								
								 if($(id).val()==""||$(id).val().length==0)
									 {
									 ++k;
								
									 } 
								 }
								 if(k>4)
									 {
									 toastr.error("Please Select Multiple Answers"); 
									 }
								 else
									 b=true;
								  
								 }
								 }
							 else if($('#selectInansradioId').is(":checked"))
								 {
								 if($('#singleAnswer').val()==""||$('#singleAnswer').length==0)
								 {
								 toastr.error("Please Enter the Answer");
								 }
								 else
									 b=true;
								 }
							  
							 if(b)
								 {
								  var skills=$( "#skillId" ).val();										 
								   if(skills==null)
										 {
										 toastr.error("Please Select Atleast 1 Important Skills"); 
										 }
								   else
									   {
 
									 
 
									   /*if($('#difficultyId').val()==-1)
 
										   {
										   toastr.error("Please Select Difficulty Level"); 
										   }
									   else{*/
										   
										   
										    if(solutionText==""||solutionText.length==0)
											 { 
											 toastr.error("Please Enter Solution Text"); 
											 }
										 else
											 {
											 if($('#solutionVideo').val()==""||$('#solutionVideo').val().length==0||$('#solutionVideo').val().indexOf(" ")==0)
												 {
												 toastr.error("Please Enter Solution Video URL"); 
												 }
											   if(/^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/|www\.)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/.test($('#solutionVideo').val())){
												   $.fn.savenewQuestion();
												} else {
													 toastr.error("Please Enter Valid URL for Solution Video ");
												}
											 
											 }  
										   //}
									   
									   }  
								 
								 }
							 
							 
							 }
						 }
					 
					 }
				 }
			
			}
		}

};
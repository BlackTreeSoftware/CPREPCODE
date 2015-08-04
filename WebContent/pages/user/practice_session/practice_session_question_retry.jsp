<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/css/practice.css" type="text/css" />
<link rel="stylesheet" href="assets/css/practiceSession/customSetup.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/practiceSession/pageSetup.css" type="text/css" />
  
  <script>
				
				$(document).ready(function(){
					

					/* Calcy Script Code Start */
					
					$('#main').hide();
					$('#showCalc').click(function(e) {
				    $('#main').toggle('fade');    
				    });
					
					
				    var key = null;
				    
				    
					
					 $('#transfer').click(function(){  
					 if($('.text-keyup').length==2)
						 {
							 if(isNaN($('#Result').val()))
							 {
							  $('#retryAnswer').attr('disabled',true);
							  $('#retryAnswer').fadeTo('fast', 0.5);
							 }
							 else
							 {
								 $('#retryAnswer').removeAttr('disabled');
								 $("#retryAnswer").fadeTo('fast', 1,'swing'); 
							   
							 }
						 $('#fillin_answer_one').val($('#Result').val());
						 }
					 if($('.text-keyup').length==3)
						 {
						 
						  if( $('#fillin_answer_one').val()=='')
							  {
							  $('#fillin_answer_one').val($('#Result').val());
							  }
						  else if($('#fillin_answer_two').val()=='')
							  {
							  $('#fillin_answer_two').val($('#Result').val());
							  }
						  if(isNaN($('#fillin_answer_one').val())||isNaN($('#fillin_answer_two').val()))
							{
							  $('#retryAnswer').attr('disabled',true);
							  $('#retryAnswer').fadeTo('fast', 0.5);
					    	}
						  else
							  {
							  $('#retryAnswer').removeAttr('disabled');
							  $("#retryAnswer").fadeTo('fast', 1,'swing');
							  } 
						 } 
					});  
					
					 
					
				    $(".clean").click(function () {
				        $('.input').val("");

				    });
				    
				   

				    $(".Show").click(function () {
				        var EText = $('#Result').val();
				        if (EText != "0") {
				            var val1 = EText;
				            var ButtonVal = $(this);
				            var val2 = ButtonVal.text();
				            var Res = val1 + val2;
				            $('#Result').val(Res);
				        } else {
				            $('#Result').val();
				        }
				    });
				   
				        var interRes = null;
				        var operator;
				        $('.operators').click(function (e) {
				        	
				            var value1 = $('#Result').val();
				            if (interRes != null) {
				                var result = ApplyOperation(interRes, value1, operator);
				                interRes = result;
				            } else {
				                interRes = value1;
				            }
				            operator = $(this).text();
				            $('input').val("");
				        });
				        $('#Result').keypress(function (e) {
				            if ((e.keyCode == 61)) {
				                var op = operator;
				                var res;
				                var value2 = $('#Result').val();
				                if ((value2 != "")) {
				                    var data = value2.split("+");
				                    if (data.length > 2) res = ApplyOperation(interRes, data[data.length - 1], op);
				                    else res = ApplyOperation(interRes, data[1], op);
				                } else {
				                    res = interRes;
				                }
				                $('#Result').val(res);
				                interRes = null;
				            } else if ((e.keyCode == 43) || (e.keyCode == 45) || (e.keyCode == 42) || (e.keyCode == 47)) {
				                var value1 = $('#Result').val();
				                var inter = (interRes != null);
				                if (inter) {
				                    var op = operator;
				                    var data = value1.split("+");
				                    if (data.length > 2) {
				                        operator = String.fromCharCode(e.keyCode);
				                        result = ApplyOperation(interRes, data[data.length - 1], op);
				                        interRes = result;
				                    } else {
				                        operator = String.fromCharCode(e.keyCode);
				                        result = ApplyOperation(interRes, data[1], op);
				                        interRes = result;
				                    }
				                } else {
				                    interRes = value1;
				                }
				                operator = String.fromCharCode(e.keyCode);
				                $('.input').text("");
				            }
				        });
				        $('#Calculate').click(function (e) {
				        	
				        	
				        	var op = operator;
				            var res;
				            var value2 = $('#Result').val();
				            if ((value2 != "")) {
				                res = ApplyOperation(interRes, value2, op);
				            } else {
				                res = interRes;
				            }
				            $('#Result').val(res);
				            interRes = null;
				        });
				   

				    function ApplyOperation(value1, value2, operator) {
				        var res;
				       
				        switch (operator) {
				            case "+":
				                res = addition(value1, value2);
				                break;
				            case "-":
				                res = subtraction(value1, value2);
				                break;
				            case "*":
				                res = multiplication(value1, value2);
				                break;
				            case "/":
				                res = division(value1, value2);
				                break;
				        }
				        return res;
				    }

				    function addition(first, second) {
				        var a = parseFloat(first);
				        var b = parseFloat(second);
				        var total = a + b;
				        return total;
				    }

				    function subtraction(first, second) {
				        var a = parseFloat(first);
				        var b = parseFloat(second);
				        var sub = a - b;

				        return sub;
				    }

				    function multiplication(first, second) {
				        var a = parseFloat(first);
				        var b = parseFloat(second);
				        var product = a * b;

				        return product;
				    }

				    function division(first, second) {
				        var a = parseFloat(first);
				        var b = parseFloat(second);
				        var divi = a / b;
				        return divi;
				    }

					/* Calcy Script Cide End */
					
				});
				</script>
		<script>
		$(document).ready(function(){
			
			
			$('#solutionArea').hide();
			//alert("in data table");

		   /*   $('#example').dataTable()
				  .columnFilter({
					aoColumns: [ null,{ type: "select", values: [ 'Correct', 'Wrong'], selected: ''  },{ type: "text" },{ type: "text" },{ type: "number" },null,null,null,
		             { type: "select", values: [ 'Marked', 'UnFlagged'], selected: ''  },null
						]

				}); */
			 //**Validations for Numeric entry questions		
			 
			$('#retryAnswer').attr('disabled',true);
		    $('.text-keyup').keyup(function(){   
			    var typeid=$('#type_id').val();  
					if(typeid==10 || typeid==16  ){  //**Validations for Single Numeric entry  questions
					    var charCode = $('#fillin_answer_one').val();  
						   if (isNaN(charCode))
							{ 
							 $('#retryAnswer').attr('disabled',true);   
							 $('#retryAnswer').fadeTo('fast', 0.5);
							 return false;
							}
						   else if(charCode=='')
							   {
							   $('#retryAnswer').attr('disabled',true); 
							   $('#retryAnswer').fadeTo('fast', 0.5);  
							   return false;
							   }
						   else {
								$('#retryAnswer').removeAttr('disabled');
								 $("#retryAnswer").fadeTo('fast', 1,'swing');
						   }
					}
					else if( typeid==13  || typeid==17 ){ //**Validations for Double Numeric entry  questions
					    var answer1 = $('#fillin_answer_one').val();
					    var answer2=  $('#fillin_answer_two').val();  
					    if (isNaN(answer1) || isNaN(answer2))
						{ 
						$('#retryAnswer').attr('disabled',true);  
						  $('#retryAnswer').fadeTo('fast', 0.5);
						return false;
						}
					    else if(answer1==''||answer2=='')
						   {
					    	$('#retryAnswer').attr('disabled',true);   
					    	  $('#retryAnswer').fadeTo('fast', 0.5);
						    return false;
						   }
					    else 
						{ 
							$('#retryAnswer').removeAttr('disabled'); 
							 $("#retryAnswer").fadeTo('fast', 1,'swing');
						} 
					} 
			     return true; 
				 
			 }); 
				 
				 
		   //**Validations for Single and Multiple answer questions
				$('.optionsCheck').click(function()
						 {   
					     var typeid=$('#type_id').val();   
					   //** Validations for text completion single...
					     if(typeid==1)
					    	 {
					    	  //  alert("TC length:"+$(".optionsCheck_TcOne:checked").length);
						    	var Length=$(".optionsCheck_TcOne:checked").length; 
						    	 if(Length==0)
							    	  {
							    	  $('#retryAnswer').attr('disabled',true); 
							    	  $('#retryAnswer').fadeTo('fast', 0.5);
							    	  }
							      else
							    	  {  
							    	  $('#retryAnswer').removeAttr('disabled');
							    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
							    	  }
					    	 }
					     //** Validations for text completion double...
					     if(typeid==2)
				    	 { 
					    	var Length_single=$(".optionsCheck_TcOne:checked").length;
					    	var Length_double=$(".optionsCheck_TcTwo:checked").length;
					    	
					    	 if(Length_single==0||Length_double==0)
						    	  {
						    	  $('#retryAnswer').attr('disabled',true); 
						    	  $('#retryAnswer').fadeTo('fast', 0.5);
						    	  }
						      else
						    	  {  
						    	  $('#retryAnswer').removeAttr('disabled');
						    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
						    	  }
				    	 }
					     //** Validations for text completion triple...
					     if(typeid==3)
				    	 { 
					    	var Length_single=$(".optionsCheck_TcOne:checked").length;
					    	var Length_double=$(".optionsCheck_TcTwo:checked").length;
					    	var Length_triple=$(".optionsCheck_TcThree:checked").length;
					    	
					    	 if(Length_single==0||Length_double==0||Length_triple==0)
						    	  {
						    	  $('#retryAnswer').attr('disabled',true); 
						    	  $('#retryAnswer').fadeTo('fast', 0.5);
						    	  }
						      else
						    	  {  
						    	  $('#retryAnswer').removeAttr('disabled'); 
						    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
						    	  }
				    	 }
					     else if(typeid==4||typeid==5||typeid==6||typeid==7||typeid==8||typeid==9||typeid==11||typeid==12||typeid==15)
					    	 { 
					    	 
					    	     var Length=$(".optionsCheck:checked").length;  
						    	 if(Length==0)
						    	  {
						    	  $('#retryAnswer').attr('disabled',true); 
						    	  $('#retryAnswer').fadeTo('fast', 0.5);
						    	  }
						      else
						    	  { 
						    	  $('#retryAnswer').removeAttr('disabled'); 
						    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
						    	  } 
					    	 }
					        
						 }); 
		});
		
		
		
		function datatable(){
			
			 $('#example').dataTable()
			  .columnFilter({
				aoColumns: [ null,{ type: "select", values: [ 'Correct', 'Wrong'], selected: ''  },{ type: "text" },{ type: "text" },{ type: "number" },null,null,null,
	             { type: "select", values: [ 'Marked', 'UnFlagged'], selected: ''  },null
					]

			});
		}

		</script>

<script type="text/javascript">
	
	function flagQuestion()
	 { 
		
		var typeid=$('#type_id').val();
		$('#answers').val('-1');
		if(typeid==10 || typeid==16  ){
			
		    var answer = $('#fillin_answer_one').val();	
		    //alert(answer);
		    $('#answers').val(answer);
			
		}
		else if( typeid==13  || typeid==17 ){
			var ans=[];
		    var answer1 = $('#fillin_answer_one').val();
		    var answer2=  $('#fillin_answer_two').val();
		    ans.push(answer1);
		    ans.push(answer2);
		    $('#answers').val(ans); 
		}
		else{  
		var array = [];
		var checkboxvalues;
		$('input[name=b]:checked').each(
				function() {			
					if($(this).val()!=-1){
					array.push($(this).val());
					}
				});
	//alert(array.length);
		
	if(array.length>0){
		
			checkboxvalues = array
					.join(',')
					+ ",";
			$('#answers').val(array);
	}
	else{
		
		//alert('Please select answer to submit');
	}
		}
			alert($('#answers').val());
		
		    var flag_status="";
			var guess_status="";
			var skip_status="";
			if(!$('#flagStatus').hasClass('active'))
				{
				flag_status="FLAG";
				$('#flagStatus').val("FLAG");
				}
			else
				{
				flag_status="UNFLAG";
$('#flagStatus').val("UNFLAG");
				}
			
			 if($('#guessMarked').is(':checked'))
				{
				 
				 guess_status="GUESS";
				} 
		     else
				{ 
		    	 guess_status="CONFIDENT";
				}  
				 if($('#skipped_status').val()=='')
				 {
				 skip_status="";
				 }
			   else
				 {
				 skip_status=$('#skipped_status').val();
				 }
			
			var timespent=document.getElementById('timeSpent').innerHTML; 
			//alert(timespent);
			var dt=$('#questionsForm').serialize();
		//	alert(dt);
			$.ajax({  
				//url :"submitPraciceSessionAnswer.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status+"&skipStatus="+skip_status,
				url :"retryQuestion.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status+"&skipStatus="+skip_status,
				data:dt,
				datatype : 'json',
				success : datasuccess,
				error : dataerror
			});
			
			function datasuccess(){
				
			}
			function dataerror(){
				
			}
	 }
	
	</script>

<script type="text/javascript">
$(document).ready(function(){ 
   //alert("in retry");
	 $('#totalTime').hide();
	 $('#done').hide();
	getPracticeSessionQuestion();
	//startCount(); 
	$('#solutionArea').hide(); 
	//$('#submitAnswer').attr('disabled',true); 
			 $('.optionsCheck').click(function()
					 {
				     var Length=$(".optionsCheck:checked").length; 
				      if(Length==0)
				    	  {
				    	  $('#retryAnswer').attr('disabled',true);
				    	  $('#retryAnswer').fadeTo('fast', 0.5);
				    	  }
				      else
				    	  { 
				    	  $('#retryAnswer').removeAttr('disabled');
				    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
				    	  }
					 });
			

	

	 
	
	$('#quizMode').click(function(){ 
	 if($('#quizMode').is(':checked'))
		 {
		  var Length=$(".optionsCheck:checked").length;  
	      if(Length==0)
	    	  {
	    	  $('#retryAnswer').attr('disabled',true);
	    	  $('#retryAnswer').fadeTo('fast', 0.5);
	    	  }
	      else
	    	  { 
	    	  $('#retryAnswer').removeAttr('disabled');
	    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
	    	  } 
		 }
	 else
		 {  
		          var Length=$(".optionsCheck:checked").length;  
			      if(Length==0)
			    	  {
			    	  $('#retryAnswer').attr('disabled',true);
			    	  $('#retryAnswer').fadeTo('fast', 0.5);
			    	  }
			      else
			    	  { 
			    	  $('#retryAnswer').removeAttr('disabled');
			    	  $("#retryAnswer").fadeTo('fast', 1,'swing');
			    	  } 
		 }
	});
	
	$('#flagStatus').click(function(){ 
		var flag_status="";
		var guess_status="";
		if(!$('#flagStatus').hasClass('active'))
			{
			flag_status="FLAG";
			$('#flagStatus').val("FLAG");
			}
		else
			{
			flag_status="UNFLAG";
			$('#flagStatus').val("UNFLAG");
			}
		 if($('#guessMarked').is(':checked'))
			{
			 //alert('guessed');
			 guess_status="GUESS";
			} 
	     else
			{ 
	    	 guess_status="CONFIDENT";
			}  
		var timespent=document.getElementById('timeSpent').innerHTML; 
	//	var dt=$('#questionsForm').serialize();
	/* 	$.ajax({  
			url :"retryQuestion.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status,
			data:dt,
			datatype : 'json'
		}); */
	});
	
});


function getPracticeSessionQuestion(){
	var qid = $('#questionid_retry').val();
	//alert(qid);

	var index=$('#index').val();
	//alert('index'+index);
	var nextIndex = parseInt(index);
	var lastIndex;
	nextIndex = nextIndex + 1;
	
	$.ajax({ 
		type : "POST",
		url :"Get-Practice-Session-Questions-Json.action",
		datatype:'json',
		success : successes,
		error:error
	});
	function successes(data,status){ 	
		$.each(JSON.parse(data), function(key, val) {			
			if (val.Question_id == qid) {		
				//alert("Question ID "+qid);
				$('#index').attr('value', nextIndex);
				//alert(val.TypeId);
				typeid=val.TypeId;
				$('#questionid').val(val.Question_id);
				$('#question').val(val.Question);
				$('#quantityOne').val(val.QuantityA);
				$('#quantityTwo').val(val.QuantityB);
				$('#categoryName').val(val.Category_name);
	    	 	 var choices=(val.Choices);
			     var choice1 = choices[0];
			     var choice2 = choices[1];
			     var choice3 = choices[2];
			     var choice4 = choices[3];
			     var choice5 = choices[4];
			     var choice6 = choices[5];
			     var choice7 = choices[6];
			     var choice8 = choices[7];
			     var choice9 = choices[8];
			     $('#choiceOne').val(choice1);
				 $('#choiceTwo').val(choice2);
				 $('#choiceThree').val(choice3);
			     $('#choiceFour').val(choice4);
				 $('#choiceFive').val(choice5);
				 $('#choiceSix').val(choice6);
				 $('#choiceSeven').val(choice7);
				 $('#choiceEight').val(choice8);
				 $('#choiceNine').val(choice9);	 
			 
				 var answers=(val.Answers);
				 var ans1=answers[0];
				 var ans2=answers[1];
				 var ans3=answers[2];
				 $('#answerOne').val(ans1);
				 $('#answerTwo').val(ans2);
				 $('#answerThree').val(ans3);
				 
				 $('#questionanswers').val(val.Answers);
				 
				 
				  /* Skills  */
				  
				 var skills=(val.Skills);
				 $('#skillOne').val(skills[0]);
				 $('#skillTwo').val(skills[1]);
				 $('#skillThree').val(skills[2]);		 
				 $('#difficulty_level').val(val.Difficulty_name);   
				 $('#difficulty_id').val(val.Difficulty_id);
			     $('#directions_page').val(val.Question_directions);
				 $('#skillidOne').val(val.Skillid1);
				 $('#skillidTwo').val(val.Skillid2);
				 $('#skillidThree').val(val.Skillid3);
				 $('#solution_text').val(val.Solution_text);
				 $('#user_id').val(val.User_id);
				 $('#test_no').val(val.Test_no);
				 $('#activity_id').val(val.Activity_id);
				 $('#access_type').val(val.Access_type);
				 $('#section_id').val(val.Section_id);
				 $('#category_id').val(val.Category_id);
				 $('#type_id').val(val.TypeId);
				 $('#qtitle').val(val.Question_title);
				 $('#average_time').val(val.Avg_time);
				 
				 $('#questionImageSource').val(val.Graph);
				 getQuestionsBasedOnType(qid,typeid);
			} 
				
		});	
	}
	function error(error){
		alert(error);
	}
}
function getQuestionsBasedOnType(ques_id,type_id){
	$.ajax({ 
		type : "POST",
		url :"Get-Retry-Questions-display.action",	
		data :{
			question_id : ques_id,
			type_id : type_id
		},
		success : datasuccess,
		error:dataerror
	});
	function datasuccess(htmlScript,status){ 
		/*  $('#totalTime').hide();
		 $('#done').hide(); */
		$('#question_div').html(htmlScript);
		$('#questiontext').html($('#question').val());
		$('#qunatityA').html($('#quantityOne').val());
		$('#qunatityB').html($('#quantityTwo').val());
		$('#choice1').html($('#choiceOne').val());
		$('#choice2').html($('#choiceTwo').val());
		$('#choice3').html($('#choiceThree').val());
		$('#choice4').html($('#choiceFour').val());
		$('#choice5').html($('#choiceFive').val());
		$('#choice6').html($('#choiceSix').val());
		$('#choice7').html($('#choiceSeven').val());
		$('#choice8').html($('#choiceEight').val());
		$('#choice9').html($('#choiceNine').val());
		
		$('#skill1').html($('#skillOne').val());
		$('#skill2').html($('#skillTwo').val());
		$('#skill3').html($('#skillThree').val());
		$('#diff').html($('#difficulty_level').val());
		$('#directions').html($('#directions_page').val());
		$('#skillid1').html($('#skillidOne').val());
		$('#skillid2').html($('#skillidTwo').val());
		$('#skillid3').html($('#skillidThree').val());
		$('#solution_text_question').html($('#solution_text').val());
		$('#question_title').html($('#qtitle').val());
		$('#answer1').html($('#AnswerOne').val());
		$('#answer2').html($('#AnswerTwo').val());
		$('#answer3').html($('#AnswerThree').val());
		$('#passagetext').html($('#passage').val());
		//alert($('#skillidOne').val()+"::"+$('#skillidTwo').val()+"::"+$('#skillidThree').val()+"::"+$('#solution_text').val());	
		
		
		$('#choice11').val($('#choiceOne').val());
		$('#choice22').val($('#choiceTwo').val());
		$('#choice33').val($('#choiceThree').val());
		$('#choice44').val($('#choiceFour').val());
		$('#choice55').val($('#choiceFive').val());
		$('#choice66').val($('#choiceSix').val());
		$('#choice77').val($('#choiceSeven').val());
		$('#choice88').val($('#choiceEight').val());
		$('#choice99').val($('#choiceNine').val());
		$('#cat_name').html($('#categoryName').val());
		//alert("category name:::"+$('#cat_name').val());
		$('#imageSource').attr('src',$('#questionImageSource').val());
		$('#questionno').html("Question 1 of 1" );
		getPerformanceBar();
		
	}
	function dataerror(error){
		alert(error);
	}
	
	
	
	
	
} 
 

function submitAnswer(){
	var typeid=$('#type_id').val();
	$('#answers').val('-1');
	if(typeid==10 || typeid==16  ){
		
	    var answer = $('#fillin_answer_one').val();	
	 //   alert(answer);
	    $('#answers').val(answer);
		
	}
	else if( typeid==13  || typeid==17 ){
		var ans=[];
	    var answer1 = $('#fillin_answer_one').val();
	    var answer2=  $('#fillin_answer_two').val();
	    ans.push(answer1);
	    ans.push(answer2);
	    $('#answers').val(ans); 
	}
	else{  
	var array = [];
	var checkboxvalues;
	$('input[name=b]:checked').each(
			function() {			
				if($(this).val()!=-1){
				array.push($(this).val());
				}
			});
//alert(array.length);
	
if(array.length>0){
	
		checkboxvalues = array
				.join(',')
				+ ",";
		$('#answers').val(array);
}
else{
	
	//alert('Please select answer to submit');
}
	}
		//alert($('#answers').val());
		var flag_status="";  
	
	  if($('#flagStatus').hasClass('active'))
		{
		 flag_status="FLAG";
		 $('#flagStatus').val("FLAG");
		 
		} 
	else
		{
		flag_status="UNFLAG";
		$('#flagStatus').val("UNFLAG");
		}    
	  
	 // alert('Val '+flag_status);
	  if($('#guessMarked').is(':checked'))
		{
		// alert('guessed');
		 guess_status="GUESS";
		} 
     else
		{ 
    	 guess_status="CONFIDENT";
		} 
	 $('#guessStatus').val(guess_status); 
	  
	var timespent=document.getElementById('timeSpent').innerHTML; 
	var dt=$('#questionsForm').serialize(); 	
	//alert(dt);
	//alert(($('#submitAnswer').text()));
	if($('#retryAnswer').text()=='Submit Answer')
	{
		$.ajax({  
			url :"retryQuestion.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status,
			data:dt,
			datatype:'json',
			success : success,
			error:error
		}); 
	}	 
	if($('#retryAnswer').text()=='Review')
	{ 
		/* /* var test_num=$('#testNum').val();
		var dt=$('#questionsForm').serialize(); 
		//alert("test num :::"+test_num); */
		/*$.ajax({  
			type : 'POST',
			url :"resultAjax.action?testno="+test_num,
			success : pagesuccess,
			error : pageerror
			
		});
		 */
		 
		// alert('in review');
		 window.location = "result.action?testno="+'<s:property value="#session.test_no"/>'+"";
		 
		$( "#retrylink" ).click();
		 
		
	}
	function success(data,status){ 		
		  if($('#quizMode').is(':checked'))
			{
			$('#retryAnswer').text("Review").removeClass("btn-success").addClass("btn-primary"); 
			$('#solutionArea').show();
			} 
		  else
			 { 
			  $('#retryAnswer').text("Review").removeClass("btn-success").addClass("btn-primary"); 
			 }
		  
		
	}
	function error(error){ 
	} 
	
	function pagesuccess(htmlScript,status){ 
		//alert('in success'+htmlScript);
		$('#result').html(htmlScript);
		  datatable();
	}
	function pageerror(error){ 
		alert('error'+error);
	}

}
 
 
 
 
function getPerformanceBar(){
	
	$('#nextButton').removeAttr('disabled');
	 /*** Reset Timer ***/
	Example1.resetStopwatch();
	Example1.Timer.toggle();
	
	/** Difficulty Rating level  **/
	//alert("B4 remove");
 	$('#half').remove();
 	$('#replace').html('<div id="half" class="half-stars"></div>');
 	//alert("A4 remove");
	$('div#half').raty({
		start: $('#difficulty_level').val(),
		readOnly: true,
	});
	 
	
	/**Got IT Percentage and average Time  **/
	var percentage = $('#gotitPercentage').val();
	var avgTime= $('#avgTime').val();
	$('#gotIt').html(percentage);
	$('#avg').html(avgTime);	
	$('#percentageDiv').css("width",percentage+"%");
	
	
	
	/** Performance of last five Questions  **/
	
	 var foo = '[' + $('#chart').val() + ']';
	 var coo =  '[' + $('#col').val() + ']';
	 //Sparkline Values to render
	 var values = JSON.parse(foo);
	 var col = JSON.parse(coo);
	// Draw a sparkline for the #sparkline element
		$('#sparkline').sparkline(
			values,
			{
				type: "bar",
				barWidth: 30,
				barSpacing: 10,
				height: 70,
				minValue: 30,
				// Map the offset in the list of values to a name to use in the tooltip
				tooltipFormat: '{{value}} secs',
				colorMap: col
			}
		);
		
		
		
	
		
		
} 
 
 
</script>
<!-- Calcy CSS Start -->
  
  <link href="assets/plug-ins/calculator/Site.css" rel="stylesheet" />
  <link href="assets/plug-ins/calculator/calcyPageLevelCSS.css" rel="stylesheet" />
  <link href="assets/plug-ins/calculator/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
  
 <script src="assets/plug-ins/calculator/jquery.min.js"></script> 
 <!-- <script src="js/calculator/jquery-ui.min.js" type="text/javascript"></script> -->
 <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>

 
  <!-- Calcy CSS End -->
<script>
  $(function() {
    $( ".draggable" ).draggable({  containment: "content", cursor: "crosshair"});
  });
 </script>
</head>
<body>
<form style="display:none" id="questionsForm">

<s:hidden id="questionid_retry"  value="%{#request.question_id}" />
<s:hidden id="testNum" name="testt_no"  value="%{#request.test_no}" />

<s:hidden id="sectionidvalue" name="sectionidavlue"  value="%{#request.sectionid}" />
<s:hidden id="typeidvalue" name="typeidvalue" value="%{#request.typeeid}" />

<s:hidden id="flagstatus" name="flagsta" />

<s:hidden id="questionid"  value="0" name="questionsUploadBO.question_id"/> 
<s:hidden id="directions_page"  value="0"/>

<s:hidden id="question" value="0" name="questionsUploadBO.question"/>
<s:hidden id="questiontitle" value="0" name="questionsUploadBO.question_title"/>
<s:hidden id="questiondirections" value="0" name="questionsUploadBO.question_directions"/>
<s:hidden id="choiceOne" value="0" name="choice1"/>
<s:hidden id="choiceTwo" value="0" name="choice2"/>
<s:hidden id="choiceThree" value="0" name="choice3"/>
<s:hidden id="choiceFour" value="0" name="choice4"/>
<s:hidden id="choiceFive" value="0" name="choice5"/>
<s:hidden id="choiceSix" value="0" name="choice6"/>
<s:hidden id="choiceSeven" value="0" name="choice7"/>
<s:hidden id="choiceEight" value="0" name="choice8"/>
<s:hidden id="choiceNine" value="0" name="choice9"/>
 <s:hidden id="skillOne" value="0" name="skillid1"/>
<s:hidden id="skillTwo" value="0" name="skillid2"/>
<s:hidden id="skillThree" value="0" name="skillid3"/> 
<s:hidden id="quantityOne" value="0" name="questionsUploadBO.quantityA"/>
<s:hidden id="quantityTwo" value="0" name="questionsUploadBO.quantityB"/>
<s:hidden id="difficulty_level" value="0" name="diffname"/>

<s:hidden id="difficulty_id" value="0" name="questionsUploadBO.difficulty_id"/>
<s:hidden id="solution_text" value="0" name="questionsUploadBO.solution_text"/>
<s:hidden id="skillidOne" value="0" name="questionsUploadBO.skillid1"/>
<s:hidden id="skillidTwo" value="0" name="questionsUploadBO.skillid2"/>
<s:hidden id="skillidThree" value="0" name="questionsUploadBO.skillid3"/>
<s:hidden id="userid" value="0" name="questionsUploadBO.user_id"/>
<s:hidden id="test_no" value="0" name=""/>
<s:hidden id="activity_id" value="0" name=""/>
<s:hidden id="access_type" value="0" name=""/>
<s:hidden id="section_id" value="0" name="questionsUploadBO.section_id"/>
<s:hidden id="test_master_id" value="0" name=""/>
<s:hidden id="category_id" value="0" name="questionsUploadBO.category_id"/>
<s:hidden id="type_id" value="0" name="questionsUploadBO.typeId"/>
<s:hidden id="lesson_id" value="1" name="questionsUploadBO.lesson_id"/>
<s:hidden id="average_time" value="0" name=""/>
<s:hidden id="passage" value="0" name=""/>
<s:hidden id="index" value="0" name=""/>
<s:hidden id="choices" value="0" name="questionsUploadBO.choices"/>
<s:hidden id="questionanswers" value="0" name="answers"/>
<s:hidden id="answers" value="-1" name="questionsUploadBO.answers"/>
<s:hidden id="guessStatus" value="CONFIDENT" name="questionsUploadBO.guessed"/>	
<s:hidden id="categoryName" value="0" name="questionsUploadBO.category_name"/>
<s:hidden id="questionImageSource"></s:hidden> 
</form>

<script type="text/javascript">
//*************************** result page start *******************************
function result()
{
	//alert('in result DISPALy');
	 $.ajax({
			type : 'POST',
			url : 'result.action',
			
			
		    success :function(result){
		    	     //alert(result);
		    		 $('#result').html(result);
		    	},
			error : function(e){	
			
				alert(e);
			}
		}); 		
}

//*************************** result page End *******************************

</script>

	

             <section id="content"  id="vbox">
				<section class="vbox">
						<section class="scrollable wrapper">
							<!---Close Notes Div for xs device--->		
							
						<div class="row" id="result">
							
							<div class="col-sm-95 headr-padding" id="sideTab">
									<!------------------------------------------------------->
									<div class="panel panel-default">
									
                                       
										<div class="panel-body">
											<div class="row xs-options" >
												<div class="col-xs-12 col-xs-offset-0 ">
													<div class="col-xs-2 col-xs-offset-0">
														<a class="btn btn-link-toggler flag" data-toggle="button">
															<i class="fa fa-flag-o text flag-icon"></i>
															<i class="fa fa-flag text-active flag-icon" ></i>
														</a>
													</div>
													<div class="col-xs-5 col-xs-offset-5">                                                     
														<a href="#" class="btn btnn btn-default btn-s-sm btn-test pull-right">Done Practicing</a>  
													</div>
												</div>

											</div>
											
											<div class="row flag-hide">
													<div class="col-xs-2 col-sm-2 p-r">
													<p class="question-category" id="questionno"> Question 0 of 0</p>
													
												</div>
												<!---CLOSED FOR Question Category--->
											
												<div class="col-xs-10 col-sm-10 text-right">
													<div class="main-nav-test">
														<ul>
															<!--<li class="hidden-xs"> <a href="#" class="btn btnn  btn-primary btn-test">Help</a></li>-->
															<%-- <li data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to hide timer">
																<a class="btn btn-link-toggler flag active p-r" data-toggle="button"  >
																	<div class="text-active"><i class="fa fa-clock-o toggle-clock-icon" ></i>
																		<span class="btn-test" style="vertical-align: text-top;">
																			Total time :
																			<b id="totalTime" title="">00:00:00</b>
																		</span>
																	</div>
																	<div class="text"> <i class="fa fa-clock-o toggle-clock-text"></i></div>
																</a>
															</li> --%>
															
															<li data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to hide timer">
																<a class="btn btn-link-toggler flag active" data-toggle="button" style="padding-right:0px;">
																	<div class="text-active" ><i class="fa fa-clock-o toggle-clock-icon" ></i>
																		<span class="btn-test" style="vertical-align: text-top;">This Question : <b id="timeSpent" title="">00:00:00</b></span>
																	</div>
																	<div class="text"> <i class="fa fa-clock-o toggle-clock-text"></i></div>
																</a>
															</li>
															
															<li> <a href="#" class="btn btnn btn-default total-cals-hide btn-test" id="showCalc"><img src="assets/images/icons/cals.png" class="img-responsive d-b-in" 
                                                            style="padding-bottom:2px;" width="16px" height="16px"/>
                                                            &nbsp;<span class="cals-hidden">Calculator</span></a></li>
															
															<li data-toggle="tooltip" data-placement="top" title="" data-original-title="mark for review"> 				
																<a href="#"  onclick="flagQuestion();" class="btn btn-link-toggler flag-hide p-l" data-toggle="button" id="flagStatus">
																	<i class="fa fa-flag-o text flag-icon"></i>
																	<i class="fa fa-flag text-active flag-icon" ></i>
																</a>
															</li>
														</ul>
													</div>
												</div><!---col close--->
											</div><!---Row closed--->                         
											<div class="clearfix"></div>

											<hr class="md-less-hr">


<div class="row optionsCheck choiceChecked text-keyup cal-focus" id="question_div">
</div>


                                            <!---Solutions area start-->
                                            <div class="row solutions practice-content" id="solutionArea">
                                            <hr class="bookmark">    
                                            	<div class="col-xs-12 " >
                                                <p class="text-center text-success-dk"><strong>Explanation</strong></p>   
                                                </div>
                                            			<div class="col-xs-12 col-lg-10  m-t-5" >	
														 <p id="solution_text_question">                     
                                                          </p>																																			
												</div>     
                                            </div>
                                            <!---Solutions area End-->
										</div><!--panel body close-->
										
										
										<div class="col-xs-12 padd-panel">
                                        <div class="i-checks side-left">
                                                                      <label>
                                                                      <input type="checkbox"  name="a" id="quizMode" value="option7">
                                                                      <i></i>
                                                                      Show explaination after submission
                                                                      </label>
                                                                      </div>
                                                                        
										<div class="i-checks side-right">
                                                                      <label>
                                                                      <input type="checkbox"  name="a" id="guessMarked" value="option8">
                                                                      <i></i>
                                                                      Mark as guessed
                                                                      </label>
                                                                      </div>
                                                                     
                                        </div>
                                       <div class="clearfix"></div>
										
										
										
										<footer class="panel-footer bg-light lter margin-question answers-md">
											<ul class="practice-footer m-n">
<%-- 												<li class="previous"><a href="result.action?testno=<s:property value="#session.test_no"/>" class="btn btn-sm btn-primary">Done Practicing</a></li> --%>

 
                                                <li class="next"><a href="javaScript:submitAnswer()" id="retryAnswer" class="btn btn-sm btn-success"  >Submit Answer</a></li>
                                             
 
												<!-- <li class="next disabled"><a href="javaScript:getPracticeSessionQuestion()" id="nextButton" class="btn btn-sm btn-primary m-r-10">Skip Question</i></a></li> -->

                                                

												
											</ul>
										</footer>
									</div><!--Panel default close-->
								</div><!---Panel row close--->
                                

								<!-----------------Options for xs------------------------->
								<hr class=" xs-answer-options">

								<div class="row xs-answer-options">
									<div class="col-xs-12">
										<div class="row xs-options">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right">
													<a href="#" class="btn btnn btn-sm btn-info">A </a>  
													<a href="#" class="btn btnn btn-sm btn-info">B</a>   
													<a href="#" class="btn btnn btn-sm btn-info">C</a>   
													<a href="#" class="btn btnn btn-sm btn-info">D</a> 
												</div>      
											</div>
										</div>
										
										<div class="row xs-options">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right">
												<!-- 	<a href="#" class="btn btnn btn-sm btn-primary">Skip Question</i> </a> -->
												   <a href="javaScript:submitAnswer()"  id="retryAnswer" class="btn btnn btn-sm btn-success">Submit Answer</a>
												  
												</div>      
											</div>
										</div>
									</div>
								</div>
								
								<!------------------End of options for xs-------------------------------------->

								<div class="col-sm-35 sider"><!---- START OF SIDEBAR FOR SKILLS --->
									<div class="row sider-header">
										<div class="col-sm-2" style="">
											<a href="#" class="btn-sider"> <i class="fa fa-chevron-circle-right btn-chevron" style="color:#1ccacc;" id="tog"></i> </a>
										</div>
									</div><!--------ROW CLOSE-------->
					  
									<div class="row">
										<div class="col-sm-12 col-sm-offset-0 skills-set" id="_skill">
											<section class="panel panel-default">
												<header class="panel-heading skills-panel-padding">Skills</header>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-12 skills-set">                        
															<ul>
																<li id="skill1"></li>
																<li id="skill2"></li>
																<li id="skill3"></li>
															</ul>
														</div><!--PANEL BODY CLOSE--->
													</div><!--COL CLOSE-->
												</div><!--ROW CLOSE--->
											</section><!---PANEL HEADER CLOSE--->
										</div><!--CLOSE OF SKILL SET-->
									</div><!--ROW CLOSE OF SKILL SET-->
									
								<%-- 	<hr class="m-t" style="">
									
									<div class="row main-text-for-practice-test" id="_difficulty">
										<div class="col-sm-12 col-sm-offset-0 skills-set">
											<div class="h5 text-center difficulty-level-margin">Difficulty level</div>
											<center><div id="half" class="half-stars"></div></center>
											<s:if test="diff==2">
											<center><div id="half" class="half-stars"></div></center>
											</s:if> 
											   
											<!-- <center><div id="half" class="half-stars"></div></center> -->
										</div><!--CLOSE OF SKILL SET-->              
									</div><!--ROW CLOSE OF SKILL SET-->
									
									<hr> --%>
									<hr style="margin-top: 25px;" class="m-t">
									
									<div class="row main-text-for-practice-test" id="_difficulty">
										<div class="col-sm-12 col-sm-offset-0 skills-set">
											<div class="h5 text-center difficulty-level-margin">Difficulty level</div>
											<center id="replace"><div id="half" class="half-stars"></div></center>
											<%-- <s:if test="diff==2">
											<center><div id="half" class="half-stars"></div></center>
											</s:if> --%> 
											   
											<!-- <center><div id="half" class="half-stars"></div></center> -->
										</div><!--CLOSE OF SKILL SET-->              
									</div><!--ROW CLOSE OF SKILL SET-->
									
									<hr>
								 
										<div class="row main-text-for-practice-test" id="_progress">
										<div class="col-sm-6 col-sm-offset-0 text-center" >
											<h5 class="m-b" >Got it right</h5>
											<p class="h5 text-success-dker" id="gotIt"><s:property value="#request.gotItPercent"/>%</p>
											
											<div class="progresss progress-ssm m-t-sm">
												<div class="progress-bar bg-primary" data-toggle="tooltip" data-original-title="10%" id="percentageDiv"></div>
											</div>
										</div>
									
										<div class="col-sm-6 col-sm-offset-0 text-center main-text-for-practice-test">
											<h5 class="m-b">Average Time</h5>
											<p class="badge badgy bg-success" style="" id="avg"><i class="fa fa-clock-o"></i><s:property value="#request.avgTime"/></p>
										</div>
										
									</div><!--CLOSE OF SKILL SET-->
								
									<hr class="less">
					  
									<div class="row" id="_graph">
										<div class="col-lg-12">
											<section class="panel panel-default">
												<header class="panel-heading">Performance on Last Q's</header>													
												<div class="panel-body text-center">
													<!--<div class="sparkline inline" data-type="bar" data-height="193" data-bar-width="12" data-bar-spacing="10" data-stacked-bar-color="['#e33244', '#eee']">5:5,8:4,12:5,10:6,11:7,12:2,8:6,9:3,5:5,4:9</div>-->
													<div id="sparkline" style=""></div>
												</div>
											</section>
										</div>
									</div>
									
									
	
	
								</div><!----SIDEBAR ROW CLOSE--->

						</div>
						
						</section>
	<div id="main" class="draggable ui-widget-content" style="margin-left:35%;margin-top:2%">   
   <header class=" bg-primary text-center font-bold" style="border-radius:0px;">Calculator</header> 
    <input id="Result" name="Result" class="input form-control" />
   
    <div id="keys">       
    <div id="FirstRow" >
        <div class="m-b-sm" style="width:150%;">
        <div class="btn-group btn-group-justified">
		    <button id="ClearAll" type="reset" value="CE" class="clean btn btn-primary ce">CE</button>
            <button id="Clear" type="reset" value="C" class="clean btn btn-primary ce">C</button>            
            <button id="transfer" type="button" value="transfer" class="transferclass btn btn-info trans">Use</button>
        </div><!--button group close-->
        </div><!--button group close-->
        </div>
        
        <div id="SecondRow">
        
        <div class="m-b-sm" style="width:150%;">
        <div class="btn-group btn-group-justified">
            <button id="One" type="button" value="1" class="Show btn btn-primary butns">1</button>
            <button id="Two" type="button" value="2" class="Show btn btn-primary butns">2</button>
            <button id="Three" type="button" value="3" class="Show btn btn-primary butns">3</button>
            <button id="Sub" type="button" value="-" class="operators operand btn btn-primary butnss">-</button>
         </div><!--button group close-->
        </div><!--button group close-->
             
        </div>
        
        <div id="ThirdRow">
        <div class="m-b-sm" style="width:150%;">
        <div class="btn-group btn-group-justified">
        
            <button id="Four" type="button" value="4" class="Show btn btn-primary butns">4</button>
            <button id="Five" type="button" value="5" class="Show btn btn-primary butns">5</button>
            <button id="six" type="button" value="6" class="Show btn btn-primary butns">6</button>
            <button id="Mul" type="button" value="*" class="operators operand btn btn-primary butnss">*</button>
            
        </div><!--button group close-->
        </div><!--button group close-->    
        </div>
        
        <div id="FourthRow">
        <div class="m-b-sm" style="width:150%;">
        <div class="btn-group btn-group-justified">
            <button id="Seven" type="button" value="7" class="Show btn btn-primary lter butns">7</button>
            <button id="Eight" type="button" value="8" class="Show btn btn-primary butns">8</button>
            <button id="Nine" type="button" value="9" class="Show btn btn-primary butns">9</button>
            <button id="Divide" type="button" value="/" class="operators operand btn btn-primary butnss">/</button>
        </div><!--button group close-->
        </div><!--button group close-->       
        </div>
        
        <div id="FifthRow">
        <div class="m-b-sm" style="width:150%;">
        <div class="btn-group btn-group-justified">
            <button id="Zero" type="button" value="0" class="Show btn btn-primary butns">0</button>
            <button id="Dot" type="button" value="." class="Show btn btn-primary butns">.</button>
            <button id="Add" type="button" value="+" class="operators operand btn btn-primary butns">+</button>
            <button id="Calculate" type="button" value="=" class="operand btn btn-primary butnss">=</button>
        </div><!--button group close-->
        </div><!--button group close-->      
        </div>     
        
    </div>
    </div>
					</section>
					   <a id ="retrylink" href="result.action?testno=<s:property value="#session.test_no"/>" class="btn btnn btn-default btn-s-sm btn-test pull-right"></a>
					<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
				</section>

				

				

 <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>   
     <!-- Ratings plugin -->
	<!-- <script src="js/rating-plugin-practice/jquery.raty.min.js"></script>-->
	<!-- Rating Scripts Start -->
	<script type="text/javascript" src="assets/js/ratingstar/js/jquery-migrate-1.2.1.min.js"></script> 
	 <script type="text/javascript" src="assets/js/ratingstar/js/jquery.raty.min.js"></script> 
	
	
	<!-- Rating Star Script End -->
	<!-- Timer Script -->
	<%--  <script type="text/javascript" src="assets/plug-ins/timer/timer.js"></script> --%>
	<script type="text/javascript" src="assets/plug-ins/jquery-timer/jquery.timer.js"></script>
    <script type="text/javascript" src="assets/plug-ins/jquery-timer/res/demo.js"></script>
	<!-- Sparkline Chart -->
	<script src="assets/js/charts/sparkline/jquery.sparkline.min.js"></script>
	<script>		
	$(document).ready(function(e) {
		//alert("cal document is calling@@@@@@@@@@@@@@@@");
		
	    
	
$('.btn-sider').click(function(e) {
			
			
			$('#_skill,#_difficulty,#_progress,#_graph').toggle();
			
			if($('#tog').hasClass('fa-chevron-circle-right')){
			$('#tog').removeClass('fa-chevron-circle-right').addClass('fa-chevron-circle-left');
			}
			else{
			$('#tog').removeClass('fa-chevron-circle-left').addClass('fa-chevron-circle-right');
			}
			$('.col-sm-95').toggleClass('col-sm-95-fuller');
			$('.col-sm-35').toggleClass('col-sm-35-fuller');
			
			$('.sider').toggleClass('no-sider');
		});
		
		 $('#totalTime').hide();
		 $('#done').hide();
		// Sparkline Values to render
	    var foo = '[' + $('#chart').val() + ']';
		var coo =  '[' + $('#col').val() + ']';
		
		//alert(foo +" : "+ col);
	
		 //Sparkline Values to render
		//var values = [0,100.00,100.00,80.00,80.00,66.67];
		
		var values = JSON.parse(foo);
		var col = JSON.parse(coo);
		
		//alert(values+":"+coo+col);
		
		// Draw a sparkline for the #sparkline element
		$('#sparkline').sparkline(
			values,
			{
				type: "bar",
				barWidth: 30,
				barSpacing: 10,
				height: 70,
				minValue: 30,
				// Map the offset in the list of values to a name to use in the tooltip
				tooltipFormat: '{{value}} secs',
				colorMap: col
			}
		);


		
	
		
		
		
	});
	
	
	
	</script>
	
	
</body>

</html>
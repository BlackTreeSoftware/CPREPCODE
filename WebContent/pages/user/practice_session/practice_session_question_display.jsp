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
  <link rel="stylesheet" href="assets/css/practiceSession/pageSetup.css" type="text/css" />
 
  
 
<script type="text/javascript">
	
	$(document).ready(function(){
		 $("#nav").hide();
		  //$("#tour_header").hide();
		  $('#tour_header').click(function(e) {
			toastr.warning("Use Quit Test Button to Activate Links ", "use Quit Test"); 
	    e.preventDefault();    
	});
		$('#submitAnswer').click(function(){
			$('#guessMarked').attr('checked',false);
		});
		
		
		
		
		$('#submitAnswer').fadeTo('fast', 0.5);  
		$('#done_practice').fadeTo('fast', 0.5); 
		//$('#nextButton').removeAttr('disabled'); 
		 $("#nextButton").fadeTo('fast', 1,'swing');
		//alert('Time limit:'+$('#timeLimitInPage').val());
		function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
	   $(document).on("keydown", disableF5);
 
	if($('#timeLimitInPage').val()!="No Limit")
 
	  
	 
	{
     		//call setIntervalTimer here to submit a from
     		//A count-down timer will be added here instead of normal one
     		
     		$('#setTime').html("<b id=countdown>00:00:00</b> <input type=hidden name='startTime' value='60000'/>");
	}
	else $('#setTime').html("<b id=totalTime>00:00:00</b>");
	
  //**Validations for Numeric entry questions		
    $('.text-keyup').keyup(function(){  
	    var typeid=$('#type_id').val();  
			if(typeid==10 || typeid==16  ){  //**Validations for Single Numeric entry  questions
			    var charCode = $('#fillin_answer_one').val();  
			
			//alert("char code"+charCode);
			
				   if (isNaN(charCode))
					{ 
					  // alert("not a anumbere");
					   $('.text-keyup').val().replace(/[^a-z]/, '');
					   
					  // $('.text-keyup').replaceWith($('.first'));
					 $('#submitAnswer').attr('disabled',true);   
					$('#submitAnswer').fadeTo('fast', 0.5);
					 return false;
					}
				   else if(charCode=='')
					   {
					   $('#submitAnswer').attr('disabled',true);   
					   $('#nextButton').removeAttr('disabled'); 
					   $("#nextButton").fadeTo('fast', 1,'swing'); 
					   $("#submitAnswer").fadeTo('fast', 0.5);
					   return false;
					   }
				   else {
					  	$('#submitAnswer').removeAttr('disabled');
				    	$('#nextButton').attr('disabled',true);	 
				    	$("#nextButton").fadeTo('fast', 0.5);
				    	$("#submitAnswer").fadeTo('fast', 1,'swing'); 
				   }
			}
			else if( typeid==13  || typeid==17 ){ //**Validations for Single Double entry  questions
			    var answer1 = $('#fillin_answer_one').val();
			    var answer2=  $('#fillin_answer_two').val();  
			    if (isNaN(answer1) || isNaN(answer2))
				{ 
				$('#submitAnswer').attr('disabled',true);  
				$('#submitAnswer').fadeTo('fast', 0.5);
				return false;
				}
			    else if(answer1==''||answer2=='')
				   {
			    	$('#submitAnswer').attr('disabled',true); 
			    	$('#submitAnswer').fadeTo('fast', 0.5);
					$('#nextButton').removeAttr('disabled'); 
					 $("#nextButton").fadeTo('fast', 1,'swing');
				    return false;
				   }
			    else 
				{ 
					$('#submitAnswer').removeAttr('disabled');
			    	$('#nextButton').attr('disabled',true);	 
			    	 $("#nextButton").fadeTo('fast', 0.5);
			    	 $("#submitAnswer").fadeTo('fast', 1,'swing'); 
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
					    	  $('#submitAnswer').attr('disabled',true);
					    	  $('#submitAnswer').fadeTo('fast', 0.5);
					    	  $('#nextButton').removeAttr('disabled'); 
					    	  $("#nextButton").fadeTo('fast', 1,'swing'); 
					    	  }
					      else
					    	  {  
					    	  $('#submitAnswer').removeAttr('disabled');
					    	  $("#submitAnswer").fadeTo('fast', 1,'swing'); 
					    	  $('#nextButton').attr('disabled',true);  
					    	  $("#nextButton").fadeTo('fast', 0.5);
					    	  }
			    	 }
			     //** Validations for text completion double...
			     if(typeid==2)
		    	 { 
			    	var Length_single=$(".optionsCheck_TcOne:checked").length;
			    	var Length_double=$(".optionsCheck_TcTwo:checked").length;
			    	
			    	 if(Length_single==0||Length_double==0)
				    	  {
				    	  $('#submitAnswer').attr('disabled',true);
				    	  $('#submitAnswer').fadeTo('fast', 0.5);
				    	  $('#nextButton').removeAttr('disabled'); 
				    	  $("#nextButton").fadeTo('fast', 1,'swing'); 
				    	  }
				      else
				    	  {  
				    	  $('#submitAnswer').removeAttr('disabled');
				    	  $("#submitAnswer").fadeTo('fast', 1,'swing'); 
				    	  $('#nextButton').attr('disabled',true);
				    	  $("#nextButton").fadeTo('fast', 0.5);
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
				    	  $('#submitAnswer').attr('disabled',true);
				    	  $('#submitAnswer').fadeTo('fast', 0.5);
				    	  $('#nextButton').removeAttr('disabled'); 
				    	  $("#nextButton").fadeTo('fast', 1,'swing'); 
				    	  }
				      else
				    	  {  
				    	  $('#submitAnswer').removeAttr('disabled');
				    	  $("#submitAnswer").fadeTo('fast', 1,'swing'); 
				    	  $('#nextButton').attr('disabled',true);  
				    	  $("#nextButton").fadeTo('fast', 0.5);
				    	  }
		    	 }
			     else if(typeid==4||typeid==5||typeid==6||typeid==7||typeid==8||typeid==9||typeid==11||typeid==12||typeid==15)
			    	 { 
			    	 
			    	     var Length=$(".optionsCheck:checked").length;  
				    	 if(Length==0)
				    	  {
				    	  $('#submitAnswer').attr('disabled',true);
				    	  $('#submitAnswer').fadeTo('fast', 0.5);
				    	  $('#nextButton').removeAttr('disabled'); 
				    	  $("#nextButton").fadeTo('fast', 1,'swing'); 
				    	  }
				      else
				    	  { 
				    	  $('#submitAnswer').removeAttr('disabled');
				    	  $("#submitAnswer").fadeTo('fast', 1,'swing'); 
				    	  $('#nextButton').attr('disabled',true); 
				    	  $("#nextButton").fadeTo('fast', 0.5);
				    	  } 
			    	 }
			        
				 }); 
		
	});
	
	
	
	function flagQuestion()
	 { 
		
		var typeid=$('#type_id').val();
		$('#answers').val('-1');
		if(typeid==10 || typeid==16  ){
			
		    var answer = $('#fillin_answer_one').val();	
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
		
		
		    var flag_status="";
			var guess_status="";
			var skip_status="";
			if(!$('#flagStatus').hasClass('active'))
				{
				flag_status="FLAG";
				}
			else
				{
				flag_status="UNFLAG";
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
				url :"submitPraciceSessionAnswer.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status+"&skipStatus="+skip_status,
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
	
	function skipQuestion()
	 {
	    $('#skipped_status').val('SKIPPED'); 
	    $('#free_type').attr('checked',false);
		submitAnswer();
	 }
	
	</script>

<script type="text/javascript">
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
			  $('#submitAnswer').attr('disabled',true);
			  $('#submitAnswer').fadeTo('fast', 0.5);
			 }
			 else
			 {
				 $('#submitAnswer').removeAttr('disabled');
				 $("#submitAnswer").fadeTo('fast', 1,'swing');
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
			  $('#submitAnswer').fadeTo('fast', 0.5);
			  $('#submitAnswer').attr('disabled',true);
	    	}
		  else
			  {
			  $('#submitAnswer').removeAttr('disabled');
			  $("#submitAnswer").fadeTo('fast', 1,'swing');
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
    $(function (e) {
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
	
	 $('#check_answers').hide();
	 $('#radio_answers').hide();
	getPracticeSessionQuestion();
	$('#solutionArea').hide();   
	 
	
	$('#flagStatus').click(function(){ 
		var flag_status="";
		var guess_status="";
		if(!$('#flagStatus').hasClass('active'))
			{
			flag_status="FLAG";
			}
		else
			{
			flag_status="UNFLAG";
			}
		 if($('#guessMarked').is(':checked'))
			{
			// alert('guessed');
			 guess_status="GUESS";
			} 
	     else
			{ 
	    	 guess_status="CONFIDENT";
			}  
		var timespent=document.getElementById('timeSpent').innerHTML; 
		var dt=$('#questionsForm').serialize();
		$.ajax({  
			url :"submitPraciceSessionAnswer.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status,
			data:dt,
			datatype : 'json'
		});
	});
	
});


function getPracticeSessionQuestion(){
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
	  //  alert(nextIndex);	   
	     var qid,typeid;
	     
	     var diffLevel;
	     $('#questionsData').val(data);
	     
	     $.each(JSON.parse(data), function(key, val) {   	
	    	lastIndex=key; 
	    	lastIndex=parseInt(lastIndex)+1;
	    	$('#lastindex').attr('value', lastIndex);
	     });   
	     
	     if($('#adaptiveModeInPage').val()=='YES')
			{  
	    	 diffLevel=isLevelExists(data,3); 
	    	 var questionIdWithDiff=diffLevel.split("@");
	    	 var question_id=questionIdWithDiff[1];
	    	 $.each(JSON.parse(data), function(key, val) {
	    		 if (val.Question_id == question_id) {
	    			$('#index').attr('value', nextIndex); 
	    			qid=val.Question_id; 
	 				typeid=val.TypeId;
	 				$('#questionid').val(val.Question_id);
	 				$('#question').val(val.Question);
	 				$('#quantityOne').val(val.QuantityA);
	 				$('#quantityTwo').val(val.QuantityB);	
	 				$('#categoryName').val(val.Category_name);
	 				/* $('#category').val(val.CategoryName); */
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
	 			   // alert(choice9+":::"+choice1+"::::"+choice2+":::"+choice3+"::::"+choice4+""+choice5+"::::"+choice6+":::"+choice7+"::::"+choice8);     
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
	 				 if($('#type_id').val()==7 || $('#type_id').val()==6 || $('#type_id').val()==9 || $('#type_id').val()==12){
	 					
	 					 $('#check_answers').show();
	 					 $('#radio_answers').hide();
	 				 }
	 				 else{
	 					 $('#check_answers').hide();
	 					 $('#radio_answers').show();
	 					 
	 				 }
	 				 $('#lesson_id').val(val.Lesson_id);
	 				 $('#qtitle').val(val.Question_title);
	 				 $('#average_time').val(val.Avg_time);
	 				 
	 				 $('#graphPath').val(val.Graph); 
					 $('#questionImageSource').val( $('#graphPath').val());
	 				 getQuestionsBasedOnType(qid,typeid);
	    		 }
	    	 });
			}
	     else
	    	 {
			 
			 $.each(JSON.parse(data), function(key, val) {   
				if (val.Question_index == nextIndex) {				
					$('#index').attr('value', nextIndex);
					//alert(val.TypeId);
					//alert(val.Question_id);
					qid=val.Question_id;
					typeid=val.TypeId;
					$('#questionid').val(val.Question_id);
					$('#question').val(val.Question);
					$('#quantityOne').val(val.QuantityA);
					$('#quantityTwo').val(val.QuantityB);	
					$('#categoryName').val(val.Category_name);
					// $('#category').val(val.CategoryName); 
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
				   //  alert(choice9+":::"+choice1+"::::"+choice2+":::"+choice3+"::::"+choice4+""+choice5+"::::"+choice6+":::"+choice7+"::::"+choice8);     
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
					 
					 
					  // Skills 
					  
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
					 if($('#type_id').val()==7 || $('#type_id').val()==6 || $('#type_id').val()==9 || $('#type_id').val()==12){
						
						 $('#check_answers').show();
						 $('#radio_answers').hide();
					 }
					 else{
						 $('#check_answers').hide();
						 $('#radio_answers').show();
						 
					 }
				 $('#lesson_id').val(val.Lesson_id);
					 $('#qtitle').val(val.Question_title);
					 $('#average_time').val(val.Avg_time);
					 
					 $('#graphPath').val(val.Graph); 
					 $('#questionImageSource').val( $('#graphPath').val());
					 $('#passage').val(val.Passage);
					 getQuestionsBasedOnType(qid,typeid);
				} 
				
				else if(nextIndex >= lastIndex){
					//alert('Last Page'+lastIndex);
				} 
				
			});	 
	    	 }
	
	}
	function error(error){
		alert(error);
	}
}



/** Next Question Retrieval from Questions List ***/
 function getPracticeSessionNextQuestion(){
	var index=$('#index').val();	
	var nextIndex = parseInt(index);
	var lastIndex;
	nextIndex = nextIndex + 1;	  
	var qid,typeid;
    var data = $('#questionsData').val();   
	$.each(JSON.parse(data), function(key, val) {				
			if (val.Question_index == nextIndex) {				
				$('#index').attr('value', nextIndex);
				//alert(val.TypeId);
				//alert(val.Question_id);
				qid=val.Question_id;
				typeid=val.TypeId;
				$('#questionid').val(val.Question_id);
				$('#question').val(val.Question);
				$('#quantityOne').val(val.QuantityA);
				$('#quantityTwo').val(val.QuantityB);	
				$('#categoryName').val(val.Category_name);
				/* $('#category').val(val.CategoryName); */
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
			   //  alert(choice9+":::"+choice1+"::::"+choice2+":::"+choice3+"::::"+choice4+""+choice5+"::::"+choice6+":::"+choice7+"::::"+choice8);     
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
				 $('#lesson_id').val(val.Lesson_id);
				 $('#qtitle').val(val.Question_title);
				 $('#average_time').val(val.Avg_time);
				 
				 $('#graphPath').val(val.Graph);  
			  	 $('#questionImageSource').val( $('#graphPath').val());
			     $('#passage').val(val.Passage);
				 getQuestionsBasedOnType(qid,typeid);
			} 
			
			else if(nextIndex >= lastIndex){
				//alert('Last Page'+lastIndex); 
				$('#flagStatus').addClass('active');
			}
		});	
	
}


function getQuestionsBasedOnType(ques_id,type_id){
	$.ajax({ 
		type : "POST",
		url :"Get-Practice-Session-Questions-display.action",	
		data :{
			question_id : ques_id,
			type_id : type_id
		},
		success : datasuccess,
		error:dataerror
	});
	function datasuccess(htmlScript,status){ 	
		$('#question_div').html(htmlScript);
		$('#category').html($('#category').val());
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
		
		$('#choice111').val($('#choiceOne').val());
		$('#choice222').val($('#choiceTwo').val());
		$('#choice333').val($('#choiceThree').val());
		$('#choice444').val($('#choiceFour').val());
		$('#choice555').val($('#choiceFive').val());
		$('#choice666').val($('#choiceSix').val());
		$('#choice777').val($('#choiceSeven').val());
		$('#choice888').val($('#choiceEight').val());
		$('#choice999').val($('#choiceNine').val());
		
		$('#choice1111').val($('#choiceOne').val());
		$('#choice2222').val($('#choiceTwo').val());
		$('#choice3333').val($('#choiceThree').val());
		$('#choice4444').val($('#choiceFour').val());
		$('#choice5555').val($('#choiceFive').val());
		$('#choice6666').val($('#choiceSix').val());
		$('#choice7777').val($('#choiceSeven').val());
		$('#choice8888').val($('#choiceEight').val());
		$('#choice9999').val($('#choiceNine').val());
		
		var index = $('#index').val();
		$('#questionno').html("Question "+index+" of "+$('#lastindex').val() );
		$('#lastQuestion').val(index);
		
		$('#imageSource').attr('src',$('#questionImageSource').val()); 
		getPerformanceBar();
		
	}
	function dataerror(error){
		alert(error);
	}
	
	
	
	
	
} 
 

function submitAnswer(){
	//alert($('#adaptiveModeInPage').val());
	$('#nextButton').attr('disabled',true);
	 $("#nextButton").fadeTo('fast', 0.5);
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

	
if(array.length>0){
	
		checkboxvalues = array
				.join(',')
				+ ",";
		$('#answers').val(array);
}
else{	
$('#answers').val("-1");	
}
}
	
		var flag_status="";  
		var skip_status="";
	  if($('#flagStatus').hasClass('active'))
		{
		 flag_status="FLAG";
		} 
	else
		{
		flag_status="UNFLAG";
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
		 if($('#skipped_status').val()=='')
		 {
		 skip_status="";
		 }
	   else
		 {
		 skip_status=$('#skipped_status').val();
		 }
	var timespent=document.getElementById('timeSpent').innerHTML; 
	var dt=$('#questionsForm').serialize(); 	
	
	
	$('#done_practice').removeAttr('disabled'); 
	$("#done_practice").fadeTo('fast', 1,'swing');
	if($('#submitAnswer').text()=='Submit Answer')
	{
		
		$.ajax({  
			url :"submitPraciceSessionAnswer.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status+"&skipStatus="+skip_status,
			data:dt,
			datatype:'json',
			success : success,
			error:error
		}); 
	}
	if($('#submitAnswer').text()=='Next Question')
	{ 
		//alert('Last Question:'+$('#lastQuestion').val()+"\tTest:"+$('#testno_practice').val())
		if($('#index').val()==$('#lastindex').val())
		{
		   result($('#testno_practice').val());
		}
		else
		{
		   if($('#adaptiveModeInPage').val()=='YES')
			{ 
				getAdaptiveQuestion();
			}
			else
			{
				getPracticeSessionNextQuestion();
			}  	 
			$('#submitAnswer').text("Submit Answer").removeClass("btn-primary").addClass("btn-success");		
			$('#submitAnswer').attr('disabled',true);
			 $('#submitAnswer').fadeTo('fast', 0.5);
			$('#quizMode').attr('checked',false);
			$('#guessMarked').attr('checked',false);
			$('#solutionArea').hide(); 
		}	
		 
	}
	 
	function success(data,status){   
		
	 
		$('#result').val(JSON.parse(data).result);
		$('#skipped_status').val('');
		
		 //$('#nextButton').removeAttr('disabled');
   	   
		  if($('#quizMode').is(':checked'))
			{
			  
				/*** Stope the timer ***/
				Example1.Timer.toggle();  
				
			$('#submitAnswer').text("Next Question").removeClass("btn-success").addClass("btn-primary");
			 $('#submitAnswer').fadeTo('fast',1,'swing');
			$('#nextButton').attr('disabled',true); 
			 $("#nextButton").fadeTo('fast', 0.5);
			$('#solutionArea').show(); 
			}
		else
			{
			 //alert('Last Question:'+$('#lastQuestion').val()+"\tTest:"+$('#testno_practice').val()); 
			if($('#index').val()==$('#lastindex').val())
				{
				   result($('#testno_practice').val());
				}
			else
				{ 
			  if($('#adaptiveModeInPage').val()=='YES')
				{
					getAdaptiveQuestion();
				}
				else
				{
					getPracticeSessionNextQuestion();
				} 
			  $('#submitAnswer').fadeTo('fast', 0.5);
				$('#submitAnswer').attr('disabled',true); 
				 $('#submitAnswer').fadeTo('fast', 0.5);
				$('#flagStatus').removeClass('active'); 
				$('#submitAnswer').text("Submit Answer").removeClass("btn-primary").addClass("btn-success"); 
				$('#solutionArea').hide();
				$('#Result').val('');
				$('#free_type').attr('checked',false);
				} 
			}     
	}
	function error(error){ 
		
	} 

}

//function to return the difficulty level for the first time
 function isLevelExists(data,level)
 {  
	var diffLevel=level; 
	var result=false;
	var question_id=0;
	 $.each(JSON.parse(data), function(key, val) {  
		 if(!result){
		 if(val.Difficulty_name==level)
			 {
			  result=true;  
			  question_id=val.Question_id;
			 //alert('Level:'+level);
			 }
		 }
	 });
	 
	 if(result)
		 {
		  // difficulty level exists  
		 return diffLevel+"@"+question_id;  
		 }
	 else
		 { 
		 //Recursion until finds the next difficluty Level
		 diffLevel=parseInt(level)+1; 
		 return isLevelExists(data,diffLevel); 
		 }   
	//return diffLevel;
 }
 
//function to return the next difficulty level
function isNextLevelExists(data,level,questionsList,ans_result)
{  
	var diffLevel=level; 
	var result=false;
	var questionid=0; 
	for(var i=0;i<questionsList.length;i++)
	{ 
		
		 $.each(JSON.parse(data), function(key, val) {  
			 if(!result){
				 if(val.Question_id==questionsList[i]){  
					 if(val.Difficulty_name==level)
					 { 
					  result=true; 
					  questionid=val.Question_id; 
					 }
				 } 
			 }
		 });
	} 
	 if(result)
		 {
		  // difficulty level exists  
		 return diffLevel+"@"+questionid;  
		 }
	 else
		 { 
		 //Recursion until finds the next difficluty Level
		 if(ans_result=='CORRECT')
		 {
			
			 diffLevel=(level+1);
			 if(diffLevel==6)
				{ 
				// diffLevel=4;
				 diffLevel= isLastLevelExists(data,5,questionsList); 
				}  
		 }
		 if(ans_result=='INCORRECT')
		 {
		 diffLevel=(level-1);	
		 if(diffLevel==0)
			 {
			 // diffLevel=1; 
			  diffLevel=isFirstLevelExists(data,1,questionsList);
			 }
		
		 }
		 return isNextLevelExists(data,diffLevel,questionsList,ans_result); 
		 }   
	 }	

//Function to check if first level exists 
//If exists returns first level,if not returns next highest level
function isFirstLevelExists(data,level,questionsList)
{
	var result=false;
	var diffLevel=level; 
	for(var i=0;i<questionsList.length;i++)
	{
	 $.each(JSON.parse(data), function(key, val) { 
		 if(val.Question_id==questionsList[i])
			 {
			 if(val.Difficulty_name==level)
			 { 
			  result=true;  
			 }
			 }
	 }); 
	}
	if(result)
		{
		return diffLevel;
		}
	else 
		{ 
		 diffLevel=parseInt(level)+1; 
		 return isFirstLevelExists(data,diffLevel,questionsList); 
		}
}

//Function to check if last level exists 
//If exists returns last level,if not returns next lowest level
function isLastLevelExists(data,level,questionsList)
{
	var result=false;
	var diffLevel=level; 
	for(var i=0;i<questionsList.length;i++)
	{
	 $.each(JSON.parse(data), function(key, val) { 
		 if(val.Question_id==questionsList[i])
			 {
			 if(val.Difficulty_name==level)
			 { 
			  result=true;  
			 }
			 }
	 }); 
	}
	if(result)
		{
		return diffLevel;
		}
	else 
		{ 
		 diffLevel=parseInt(level)-1; 
		 return isLastLevelExists(data,diffLevel,questionsList); 
		}
}
	 
 
function getPerformanceBar(){
	
	$('#nextButton').removeAttr('disabled');
	 $("#nextButton").fadeTo('fast', 1,'swing'); 
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
	 //alert(values);
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

<script type="text/javascript">
 
//function to get next question based on difficluty level (For Adaptive Mode)
function getAdaptiveQuestion()
{
	// alert('Result :'+$('#result').val()+"\t Question NO:"+$('#questionid').val());
	var result=$('#result').val();
	var nextLevel;
	var questionData= $('#questionsData').val();
	var diffLevel=$('#difficulty_level').val();
	var questionsList = []; 
	var previousQuestions=$('#answeredQuestions').val();
	$('#answeredQuestions').val(previousQuestions+","+$('#questionid').val());
	var answeredList=$('#answeredQuestions').val().split(',');
	
	$.each(JSON.parse(questionData), function(key, val) {	 
		    questionsList.push(val.Question_id); 
	});
	
	for(var i=0;i<answeredList.length;i++)
	 { 
		for(var j=0;j<questionsList.length;j++)
			{  
			 if(answeredList[i]==questionsList[j])
				 { 
				 questionsList.splice(j,1);
				 }
			}
	 }	  
	if(result=='CORRECT')
	{
		 
		if((parseInt(diffLevel)+1)==6)
			{
			 nextLevel=isNextLevelExists(questionData,5,questionsList,result);
			}
		else
			{
			 nextLevel=isNextLevelExists(questionData,parseInt(diffLevel)+1,questionsList,result);
			 //alert('Next Level:'+nextLevel);
			}
		 
		displayAdaptiveQuestion(questionData,nextLevel);
		 //alert('Next Level:'+nextLevel);
	}	
	if(result=='INCORRECT')
	{
		if((parseInt(diffLevel)-1)==0)
			{
			nextLevel=isNextLevelExists(questionData,1,questionsList,result);
			}
		else
			{
			nextLevel=isNextLevelExists(questionData,parseInt(diffLevel)-1,questionsList,result); 
			}  
		 alert('Next Level:'+nextLevel);
		 displayAdaptiveQuestion(questionData,nextLevel);
	}
}

//
function displayAdaptiveQuestion(questionData,level)
{
	 var questionWithDiffLevel=level.split('@');
	 var difflevel=questionWithDiffLevel[0];
	 var question_id=questionWithDiffLevel[1];
	 var index=$('#index').val();	
	 var nextIndex = parseInt(index); 
	 nextIndex = nextIndex + 1;	   
	 $.each(JSON.parse(questionData), function(key, val) {	
		
		 if(val.Question_id==question_id)
			 {
			 
			    $('#index').attr('value', nextIndex); 
				//alert(val.TypeId);
				//alert(val.Question_id);
				qid=val.Question_id;
				typeid=val.TypeId;
				$('#questionid').val(val.Question_id);
				$('#question').val(val.Question);
				$('#quantityOne').val(val.QuantityA);
				$('#quantityTwo').val(val.QuantityB);	
				$('#categoryName').val(val.Category_name);
				// $('#category').val(val.CategoryName); 
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
			   //  alert(choice9+":::"+choice1+"::::"+choice2+":::"+choice3+"::::"+choice4+""+choice5+"::::"+choice6+":::"+choice7+"::::"+choice8);     
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
				 
				 
				  // Skills 
				  
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
				 if($('#type_id').val()==7 || $('#type_id').val()==6 || $('#type_id').val()==9 || $('#type_id').val()==12){
					
					 $('#check_answers').show();
					 $('#radio_answers').hide();
				 }
				 else{
					 $('#check_answers').hide();
					 $('#radio_answers').show();
					 
				 }
				 $('#lesson_id').val(val.Lesson_id);
				 $('#qtitle').val(val.Question_title);
				 $('#average_time').val(val.Avg_time);
				 $('#questionImageSource').val('assets/images/graphs/Desert.jpg');
				 getQuestionsBasedOnType(qid,typeid);
			 }
	 });
} 
</script>

<script type="text/javascript">
//*************************** result page start *******************************
 
	function result(test_no)
	{  
		$('#submitAnswer').fadeTo('fast', 0.5);
		$('#submitAnswer').attr('disabled',true);
	    $('#questionsForm').attr('action','result.action');
		$('#questionsForm').submit();  
	}
 

//*************************** result page End *******************************

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
 	
<body>
<form style="display:none" id="questionsForm" method="post">
<s:hidden id="questionsData"  value="0"/>
<s:hidden id="directions_page"  value="0"/>
<s:hidden id="questionid"  value="0" name="questionsUploadBO.question_id"/>
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
<%-- <s:hidden id="testno" name="testno" value="#session.test_no"/> --%>

<input id="testno_practice"  value="<s:property value="#session.test_no"/>" name="testNo">
<s:hidden id="activity_id" value="0" name=""/>
<s:hidden id="access_type" value="0" name=""/>
<s:hidden id="section_id" value="0" name="questionsUploadBO.section_id"/>
<s:hidden id="test_master_id" value="0" name=""/>
<s:hidden id="category_id" value="0" name="questionsUploadBO.category_id"/>
<s:hidden id="type_id" value="0" name="questionsUploadBO.typeId"/>
<s:hidden id="lesson_id" value="1"  name="questionsUploadBO.lesson_id"/>
<s:hidden id="average_time" value="0" name=""/>
<s:hidden id="passage" value="0" name=""/>
<s:hidden id="index" value="0" name=""/>
<s:hidden id="choices" value="0" name="questionsUploadBO.choices"/>
<s:hidden id="questionanswers" value="0" name="answers"/>
<s:hidden id="answers" value="-1" name="questionsUploadBO.answers"/>
<s:hidden id="guessStatus" value="CONFIDENT" name="questionsUploadBO.guessed"/>	
<s:hidden id="categoryName" value="0" name="questionsUploadBO.category_name"/>
<s:hidden id="lastindex" value="0"/>

<s:hidden id="adaptive" value="Adaptive"/>	

<s:hidden id="result"	value="-1"></s:hidden>	
<s:hidden id="listOfQuestions"	value="-1,295"></s:hidden>	
<s:hidden id="lastQuestion"></s:hidden>
<s:hidden id="questionImageSource"></s:hidden>
<input type="hidden" id="adaptiveModeInPage" value="${adaptiveModeInPage}"></input>
<input type="hidden" id="timeLimitInPage" value="${timeLimitInPage}"></input>
<s:hidden id="answeredQuestions" value="-1"></s:hidden>	
<s:hidden id="graphPath"></s:hidden>	

</form>



<section id="content"  id="vbox">
				<section class="vbox">
						<section class="scrollable wrapper">
							<!---Close Notes Div for xs device--->		
							
							<div class="col-sm-95 headr-padding" id="sideTab" style="padding-left:0px;padding-right:5px">
									<!------------------------------------------------------->
									<div class="panel panel-default">
										
										<div class="panel-body">
											<div class="row xs-options" >
												<div class="col-xs-12 col-xs-offset-0 ">
													<div class="col-xs-2 col-xs-offset-0">
														<a href="#"  onclick="flagQuestionMobile();" class="btn btn-link-toggler flag" data-toggle="button" id="flagStatusMobile">
															<i class="fa fa-flag-o text flag-icon"></i>
															<i class="fa fa-flag text-active flag-icon" ></i>
														</a>
													</div>
													<div class="col-xs-5 col-xs-offset-5">                                                     
													<!-- 	<a href="#" class="btn btnn btn-default btn-s-sm btn-test pull-right">Done Practicing</a>   -->
														<a href="result.action?testno=<s:property value="#session.test_no"/>" class="btn btnn btn-default btn-s-sm btn-test pull-right" >Done Practicing</a>
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
															<li data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to hide timer">
																<a class="btn btn-link-toggler flag active p-r" data-toggle="button" style="color:#788288"  >
																	<div class="text-active"><i class="fa fa-clock-o toggle-clock-icon" ></i>
																		<span class="btn-test" style="vertical-align: text-top;">
																			Total time :
																			<!-- <b id="totalTime" title="">00:00:00</b> -->
																			<!-- <b id="countdown">05:00:00</b> -->
																			<b id="setTime"></b>
																		</span>
																	</div>
																	<div class="text"> <i class="fa fa-clock-o toggle-clock-text"></i></div>
																</a>
															</li>
															
															<li data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to hide timer">
																<a class="btn btn-link-toggler flag active" data-toggle="button" style="padding-right:0px;color:#788288">
																	<div class="text-active" ><i class="fa fa-clock-o toggle-clock-icon" ></i>
																		<span class="btn-test" style="vertical-align: text-top;">Question Time : <b id="timeSpent" title="">00:00:00</b></span>
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
												<li class="previous"><a href="result.action?testno=<s:property value="#session.test_no"/>" class="btn btn-sm btn-primary" id="done_practice">Done Practicing</a></li>

 
                                                <li class="next"><a href="javaScript:submitAnswer()" id="submitAnswer" class="btn btn-sm btn-success"  >Submit Answer</a></li>
 
												<li class="next"><a href="javaScript:skipQuestion()" id="nextButton" class="btn btn-sm btn-primary m-r-10">Skip Question</i></a></li>

                                               

												
											</ul>
										</footer>
										
    
									</div><!--Panel default close-->
								</div><!---Panel row close--->

								<div class="col-sm-35 sider"><!---- START OF SIDEBAR FOR SKILLS --->
									<div class="row sider-header">
										<div class="col-sm-2" style="">
											<a href="#" class="btn-sider"> <i class="fa fa-chevron-circle-right btn-chevron" style="color:#1ccacc;" id="tog"></i> </a>
										</div>
									</div><!--------ROW CLOSE-------->
					  
									<div class="row">
										<div class="col-sm-12 col-sm-offset-0 skills-set" id="_skill">
										<div class="h5 text-center difficulty-level-margin"><p id="cat_name">Reading Comprhension</p></div>
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
											<p class="h5 text-success-dker" id="gotIt">%<s:property value="#request.gotItPercent"/></p>
											
											<div class="progresss progress-ssm m-t-sm">
												<div class="progress-bar bg-primary" data-toggle="tooltip" data-original-title="<s:property value="#request.gotItPercent"/>%" id="percentageDiv"></div>
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
												<header class="panel-heading">Performance of Last 5 Q's</header>
													
											<div class="panel-body text-center">
													<!--<div class="sparkline inline" data-type="bar" data-height="193" data-bar-width="12" data-bar-spacing="10" data-stacked-bar-color="['#e33244', '#eee']">5:5,8:4,12:5,10:6,11:7,12:2,8:6,9:3,5:5,4:9</div>-->
													<div id="sparkline" style=""></div>
												</div>
											</section>
										</div>
									</div>
									
									
	
	
								</div><!----SIDEBAR ROW CLOSE--->

						
						
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
					<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
				</section>
<input type="hidden" id="skipped_status"/> 
<%-- <script src="assets/js/jquery.min.js"></script> --%>


  <!-- Scripts for practice Session -->
    <%--  <!--<script src="assets/js/jquery.min.js"></script>-->
	<!-- Bootstrap -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- App -->
	<script src="assets/js/app.js"></script>    --%>
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
		$('#submitAnswer').attr('disabled',true); 
		$('#done_practice').attr('disabled',true); 
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
		
		$('#show_notes').hide();
		$(".buttonfornotes").click(function () {
			// Set the effect type
			var effect = 'slide';
			// Set the options for the effect type chosen
			var options = { direction:'up' };
			// Set the duration (default: 400 milliseconds)
			var duration = 400;
			$('#show_notes').toggle(effect, options, duration);
		});		
		$("#notes_section").click(function () {
			// Set the effect type
			//var effect = 'slide';
			// Set the options for the effect type chosen
			var options = { direction:'left' };
			// Set the duration (default: 400 milliseconds)
			var duration = 400;
			$(this).toggle(effect, options, duration);
		});
	});
	
	
	
	</script>
	<script type="text/javascript">
	function submitAnswerMobile(){
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
		
	}
		}
			
			var flag_status="";  
			var skip_status="";
		  if($('#flagStatusMobile').hasClass('active'))
			{
			 flag_status="FLAG";
			} 
		else
			{
			flag_status="UNFLAG";
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
			 if($('#skipped_status').val()=='')
			 {
			 skip_status="";
			 }
		   else
			 {
			 skip_status=$('#skipped_status').val();
			 }
		var timespent=document.getElementById('timeSpent').innerHTML; 
		var dt=$('#questionsForm').serialize(); 	
		//alert(dt);
		//alert(($('#submitAnswer').text()));
		if($('#submitAnswerMobile').text()=='Submit Answer')
		{
			$.ajax({  
				url :"submitPraciceSessionAnswer.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status+"&skipStatus="+skip_status,
				data:dt,
				datatype:'json',
				success : success,
				error:error
			}); 
		}
		if($('#submitAnswerMobile').text()=='Next Question')
		{ 
			getPracticeSessionQuestion();
			$('#submitAnswerMobile').text("Submit Answer").removeClass("btn-primary").addClass("btn-success");
			$('#nextButtonMobile').attr('disabled',true);
			$('#solutionArea').hide();
		}
		 
		function success(data,status){   
			   $('#skipped_status').val('');
			  if(!$('#quizMode').is(':checked'))
				{
				$('#submitAnswerMobile').text("Next Question").removeClass("btn-success").addClass("btn-primary");
				$('#solutionArea').show();
				}
			else
				{
				
				getPracticeSessionQuestion();
				$('#submitAnswerMobile').text("Submit Answer").removeClass("btn-primary").addClass("btn-success");
				$('#solutionArea').hide();
				$('#nextButtonMobile').removeAttr('disabled'); 
				
				}     
		}
		function error(error){ 
		} 

	}
	
	function flagQuestionMobile()
	 { 
		
		var typeid=$('#type_id').val();
		$('#answers').val('-1');
		if(typeid==10 || typeid==16  ){
			
		    var answer = $('#fillin_answer_one').val();	
		//    alert(answer);
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
			var guess_status="";
			if(!$('#flagStatusMobile').hasClass('active'))
				{
				flag_status="FLAG";
				}
			else
				{
				flag_status="UNFLAG";
				}
			
			 if($('#guessMarked').is(':checked'))
				{
				 
				 guess_status="GUESS";
				} 
		     else
				{ 
		    	 guess_status="CONFIDENT";
				}  
			
			var timespent=document.getElementById('timeSpent').innerHTML; 
			var dt=$('#questionsForm').serialize();
			$.ajax({  
				url :"submitPraciceSessionAnswer.action?flagStatus="+flag_status+"&timeSpent="+timespent+"&guessStatus="+guess_status,
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
	
	function skipQuestionMobile()
	 {
	    $('#skipped_status').val('SKIPPED'); 
		submitAnswerMobile();
	 }
	</script>
	
	</head>
	
</body>
</html>
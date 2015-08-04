  function  getFullLenthTestQuestions()
  {   
	 
  	var index=$('#index').val();
  	var nextIndex = parseInt(index);
  	var lastIndex;
  	nextIndex = nextIndex + 1; 
  	hideReviewButtons();
    	var sectionNumber=parseInt($('#sectionNumber').val())+1;
		$('#sectionnumber').html('Section '+sectionNumber+' of '+$('#totalSections').val());
		$('#sectionNumber').val(sectionNumber);
		if(sectionNumber>5)
			{
			alert("You have Successfully Completed the Test");
			
			}
		else{
		
  	$.ajax({ 
  		type : "POST",
  		url :"Get-Full-Test-Questions-Json.action?section="+$('#startSection').val()+'&currentSection='+$('#sectionNumber').val(),
  		datatype:'json',
  		success : successes,
  		error:error
  	});
  	
		}
  	
  	function successes(data,status){   
 
  		 $('#questionsData').val(data);
  	     $('#updatedAnswers').val(data); 
  	     
  	     $('#_buttonsArea').removeClass("col-lg-1 col-lg-offset-11").show();
  		 var qid,typeid;
  		 
  		 $.each(JSON.parse(data), function(key, val) {   	
  		    	lastIndex=key; 
  		    	lastIndex=parseInt(lastIndex)+1;
  		    	$('#lastindex').attr('value', lastIndex);
  		     });   
  	    	$.each(JSON.parse(data), function(key, val) {   
  	    		if (val.Question_index == nextIndex) {
  	    			$('#index').attr('value', nextIndex);
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
  					  
  					/* var skills=(val.Skills); 
  					 $('#skillOne').val(skills[0]);
  					 $('#skillTwo').val(skills[1]);
  					 $('#skillThree').val(skills[2]);	*/	 
  					 
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
  	    		
  	    	});
  	    	
  	    	
  	    	Example2.resetCountdown();
  	    	Example2.Timer.toggle();
  	    	
  	}
  	
  	    function error(error){
  			alert(error);
  		}
   
    
   
    
    
  	
  	
  } 
  

 /* 
  function  resumeTest()
  { 
	  alert("hai");
	  var index=$('#resumequestionIndex').val();
	  alert("Resume Index    "+index);
	  	var nextIndex = parseInt(index);
	  	var lastIndex;
	  	nextIndex = nextIndex; 
	  	
	  	var sectionNumber=parseInt($('#resumeSectionNumber').val());
	  	alert("Sction No"+sectionNumber);
		$('#sectionnumber').html('Section '+sectionNumber+' of '+$('#totalSections').val());
		$('#sectionNumber').val(sectionNumber);
		
  	$.ajax({ 
  		type : "POST",
  		url :"resume-Practice-Test",
  		datatype:'json',
  		success : successes,
  		error:error
  	});
  	function successes(data,status){   
  		
    	  alert("The data is"+data);
    		 $('#questionsData').val(data);
    	     $('#updatedAnswers').val(data); 
    		 var qid,typeid;
    		 
    		 $.each(JSON.parse(data), function(key, val) {   	
    		    	lastIndex=key; 
    		    	lastIndex=parseInt(lastIndex)+1;
    		    	$('#lastindex').attr('value', lastIndex);
    		     });   
    	    	$.each(JSON.parse(data), function(key, val) {   
    	    		if (val.Question_index == nextIndex) {
    	    			$('#index').attr('value', nextIndex);
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
    	    		
    	    	});
    	    	
    	    	
    	    	Example2.resetCountdown();
    	    	Example2.Timer.toggle();
    	    	
    	}
    	
    	    function error(error){
    			alert(error);
    		}
     
      
     
  	
	  
  } 
  
  */
  
  

  
  //**********************  RESUME TEST QUESTIONS*******************************************************
  function  resumeTest()
  { 
	  //alert("hai"+$('#testPattern').val());
	  $('#startSection').val(($('#testPattern').val().split("_")[0]=='Q')?81:86);
	  var index=$('#resumequestionIndex').val();
	  //alert("Resume Index    "+index);
	  	var nextIndex = parseInt(index);
	  	var lastIndex;
	  	nextIndex = nextIndex; 
	  	
	  	var sectionNumber=parseInt($('#resumeSectionNumber').val());
	  	//alert("Sction No"+sectionNumber);
		$('#sectionnumber').html('Section '+sectionNumber+' of '+$('#totalSections').val());
		$('#sectionNumber').val(sectionNumber);
		
		if($('#sectionStatus').val()=='COMPLETE'){
			goToInstructions();
		}
		else{
			$.ajax({ 
		  		type : "POST",
		  		url :"resume-Practice-Test",
		  		datatype:'json',
		  		success : successes,
		  		error:error
		  	});
		} 
  	
  	function successes(data,status){   
  		
    	  alert("The data is"+data);
    		 $('#questionsData').val(data);
    	     $('#updatedAnswers').val(data); 
    		 var qid,typeid;
    		 
    		 $.each(JSON.parse(data), function(key, val) {   	
    		    	lastIndex=key; 
    		    	lastIndex=parseInt(lastIndex)+1;
    		    	$('#lastindex').attr('value', lastIndex);
    		     });   
    	    	$.each(JSON.parse(data), function(key, val) {   
    	    		if (val.Question_index == nextIndex) {
    	    			$('#index').attr('value', nextIndex);
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
    					  
    					/* var skills=(val.Skills); 
    					 $('#skillOne').val(skills[0]);
    					 $('#skillTwo').val(skills[1]);
    					 $('#skillThree').val(skills[2]);	*/	 
    					 
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
    	    		
    	    	});
    	    	
    	    	alert('Rem time'+parseInt($('#timeRemaining').val().split(":")[2]));
   	  		    $('#countdown').html($('#timeRemaining').val());
   	  		    var a = (parseInt($('#timeRemaining').val().split(":")[1])*6000)+(parseInt($('#timeRemaining').val().split(":")[2]*100));
   	  		   // alert('Timer : '+a);
   	  		    $('#sectionTimerValue').val(parseInt(a));
   	  		    
    	    	Example2.resetCountdown();
    	    	Example2.Timer.toggle();
   	  			
    	}
    	
    	    function error(error){
    			alert(error);
    		}
     
      
     
  	
	  
  } 
  
  
  //**********************  RESUME TEST QUESTIONS*******************************************************  
  

  function getQuestionsBasedOnType(ques_id,type_id){
	  	$.ajax({ 
	  		type : "POST",
	  		url :"Get-Full-Test-Questions-Display.action",	
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
	  		$('.btn-group-justified,.less-hr,#_timer,#_quantInstructions').show(); 
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
	  		/*$('#skill1').html($('#skillOne').val());
	  		$('#skill2').html($('#skillTwo').val());
	  		$('#skill3').html($('#skillThree').val());*/
	  		$('#diff').html($('#difficulty_level').val());
	  		$('#directions').html($('#directions_page').val());
	  		/*$('#skillid1').html($('#skillidOne').val());
	  		$('#skillid2').html($('#skillidTwo').val());
	  		$('#skillid3').html($('#skillidThree').val());*/
	  		$('#solution_text_question').html($('#solution_text').val());
	  		$('#question_title').html($('#qtitle').val());
	  		$('#answer1').html($('#AnswerOne').val());
	  		$('#answer2').html($('#AnswerTwo').val());
	  		$('#answer3').html($('#AnswerThree').val());
	  		$('#passagetext').html($('#passage').val());
	  		//alert($('#skillidOne').val()+"::"+$('#skillidTwo').val()+"::"+$('#skillidThree').val()+"::"+$('#solution_text').val());	 
	  		
	  		
	  		 var splitter = ". ";
			  $('.select-in-passage').each(function() { 
			      var sentences = $(this)
			          .text()
			          .replace(/([^.!?]*[^.!?\s][.!?]['"]?)(\s|$)/g, 
			                   '<span class="sentence">$1</span>$2');
			      $(this).html(sentences);
			  }); 
			  
			  words = $(".select-in-passage").text().split(". ");
			  swords = $(".select-in-passage").text().split(". ");
			  lineno = "";
			 $(".select-in-passage").html("");
			  for(var i=0; i< (words.length-1); i++){
			   // $("#passagetext").append("<span class='textPart' id='paragraph_sentence_'>"+words[i]+"</span>"+ splitter ); 
				  $(".select-in-passage").append("<span class='textPart' id='paragraph_sentence_"+i+"'>"+words[i]+"</span>"+ splitter );
			  } 
			  for(i=0;i< (swords.length-1); i++){
				  
			  }
			  /*$(".select-in-passage").delegate(".textPart", "click", function(){
			      var flagStatus="";
			      for(i=0;i< (swords.length-1); i++){ 
					    if(swords[i]==$(this).text()){ 
					    	 lineno = i;  
					    	 if($('#previousSelection').val()!=''||$('#previousSelection').val()!='undefined')
					    		 {
					    		 $(".textPart").removeClass("select-in-paragraph"); 
					    		 }
					    	 $('#previousSelection').val((lineno+1));
					    	 //alert($(this).text());
					    	 $('#selectedText').val($(this).text());
					    	 $(this).addClass("select-in-paragraph"); 
					    }  
			      	}  
			       
			      if($('#_mark').hasClass("active"))
				  { 
				   flagStatus="FLAG";
				  }
			     else
				  { 
				  flagStatus="UNFLAG";
				  } 
				  var ans=JSON.parse($('#updatedAnswers').val()); 
				  var array=[];
					 for(var i=0;i<ans.length;i++){
						 if(ans[i].Question_index==index){ 
							 array.push((lineno+1));
							 ans[i].UserAnswer=array;  
							 ans[i].IsFlagged=flagStatus;  
						 }
					 }  
					 $('#updatedAnswers').val(JSON.stringify(ans));    
			  });  */
			  
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
	 
	  		
	  		var index = $('#index').val(); 
	  		var updatedAnswers=$('#updatedAnswers').val(); 
            $.each(JSON.parse(updatedAnswers), function(key, val) { 
		  			 if(val.Question_index==index)
		  				 {   
		  				   var answers= val.UserAnswer;   
		  				   if(val.TypeId==1||val.TypeId==2||val.TypeId==3)
		  					   {
		  					     if(val.TypeId==1)
		  					       { 
		  					    	for(var i=0;i<answers.length;i++)
				  					  {
		  					    		  var tc_two_answers=answers[i].split("_");
			  							  var ans1=tc_two_answers[0];
			  							  var ans2=tc_two_answers[1];  
			                              $("input[class=optionsCheck_TcOne]").each(function () { 
			                            	  if($(this).val()==ans1)
			                            		  {
			                            		  $(this).attr('checked', true);
			                            		  }
			                              });
				  					  }  
		  					       }
			  					  if(val.TypeId==2)
		  					       { 
			  						for(var i=0;i<answers.length;i++)
				  					  { 
			  							  var tc_two_answers=answers[i].split("_");
			  							  var ans1=tc_two_answers[0];
			  							  var ans2=tc_two_answers[1]; 
			  							  if(ans2=="1")
			  								  {
			  								 $("input[class=optionsCheck_TcOne]").each(function () { 
				                            	  if($(this).val()==ans1)
				                            		  {
				                            		  $(this).attr('checked', true);
				                            		  }
				                              });
			  								  }
				  							if(ans2=="2")
			  								  {
			  								 $("input[class=optionsCheck_TcTwo]").each(function () { 
				                            	  if($(this).val()==ans1)
				                            		  {
				                            		  $(this).attr('checked', true);
				                            		  }
				                              });
			  								  }
			                             
				  					  } 
		  					       }
			  					  if(val.TypeId==3)
		  					       {   
			  						for(var i=0;i<answers.length;i++)
				  					  {  
			  							 var tc_three_answers=answers[i].split("_");
			  							 var ans1=tc_three_answers[0];
			  							 var ans2=tc_three_answers[1]; 
			  							  if(ans2=="1")
			  								  {
			  								 $("input[class=optionsCheck_TcOne]").each(function () { 
				                            	  if($(this).val()==ans1)
				                            		  {
				                            		  $(this).attr('checked', true);
				                            		  }
				                              });
			  								  }
			  							if(ans2=="2")
			  								  {
			  								 $("input[class=optionsCheck_TcTwo]").each(function () { 
				                            	  if($(this).val()==ans1)
				                            		  {
				                            		  $(this).attr('checked', true);
				                            		  }
				                              });
			  								  }
			  							if(ans2=="3")
			  								  {
			  								 $("input[class=optionsCheck_TcThree]").each(function () { 
				                            	  if($(this).val()==ans1)
				                            		  {
				                            		  $(this).attr('checked', true);
				                            		  }
				                              });
			  								  }
			                             
				  					  } 
		  					       }
		  					   }
		  				   else
		  					   {
				  					 if(val.TypeId==10||val.TypeId==16)
				  					{
				  					   $('#fillin_answer_one').val(answers[0]);
				  					}
				  				  if(val.TypeId==13||val.TypeId==17)
				  					 {
					  					$('#fillin_answer_one').val(answers[0]);
					  					$('#fillin_answer_two').val(answers[1]);
				  					 }  
				  				  if(val.TypeId==4||val.TypeId==5||val.TypeId==6||val.TypeId==7||val.TypeId==8||val.TypeId==9||val.TypeId==11||val.TypeId==12||val.TypeId==15)
				  					  {
					  					  for(var i=0;i<answers.length;i++)
					  					  {
				                              //alert('answers:'+answers[i]);
				                              $("input[class=optionsCheck]").each(function () { 
				                            	  if($(this).val()==answers[i])
				                            		  {
				                            		  $(this).attr('checked', true);
				                            		  }
				                              });
					  					  }  
				  					  }
				  				  if(val.TypeId==14){    
				  					 /* var a=parseInt(answers[0])-1; 
				  					  *  $('#paragraph_sentence_'+a).addClass("select-in-paragraph");  */ 
				  					  var swords = $(".select-in-passage").text().split(". ");
				  					   for(var i=0;i<swords.length;i++){
				  						   if(swords[i]==val.Reading_comprehension_text){
				  							 $('#paragraph_sentence_'+i).addClass("select-in-paragraph");  
				  						   }
				  						   else{
				  							   var a=parseInt(answers[0])-1; 
						  					   $('#paragraph_sentence_'+a).addClass("select-in-paragraph");
				  						   }
				  						    
				  					   } 
				  				  }
		  					   }
		  				  
		  				 
			  				if(val.IsFlagged=='FLAG')
			  				  {
			  					$('#_mark').addClass('active'); 
			  				  }
			  				if(val.IsFlagged=='UNFLAG'||val.IsFlagged=='')
		  					  { 
		  					   $('#_mark').removeClass('active');
		  					  } 
		  				 } 
		  		});	   
	  		$('#questionno').html("Question "+index+" of "+$('#lastindex').val() );
	  		if(index==1)
			  {
			  $('#_back').attr('disabled',true);
			  $('#_back').fadeTo('fast', 0.5);
			  }
		  else
			  {
			  $('#_back').removeAttr('disabled');
			  $("#_back").fadeTo('fast', 1,'swing');
			  } 
	  		$('#lastQuestion').val(index);	  		
	  		$('#imageSource').attr('src',$('#questionImageSource').val());  
	  	}
	  	function dataerror(error){
	  		alert(error);
	  	} 
	  }
  
  function NextQuestion()
  {  
	
	  $('#remainingTime').val($('#countdown').html());
	  var usertime=$('#timeSpent').html();
	  var array=[];
	  var type_id=$('#type_id').val();   
	  if(type_id==1||type_id==2||type_id==3){ 
			  if(type_id==1){
				  $('input[class=optionsCheck_TcOne]:checked').each(
							function() { 
								 array.push($(this).val()+"_1");  
							}); 
			  }
			  if(type_id==2){
				  $('input[class=optionsCheck_TcOne]:checked').each(
							function() { 
								 array.push($(this).val()+"_1");  
							}); 
				  $('input[class=optionsCheck_TcTwo]:checked').each(
							function() { 
								 array.push($(this).val()+"_2");  
							}); 
			  }
			  if(type_id==3){
				  $('input[class=optionsCheck_TcOne]:checked').each(
							function() { 
								 array.push($(this).val()+"_1");  
							}); 
				  $('input[class=optionsCheck_TcTwo]:checked').each(
							function() { 
								 array.push($(this).val()+"_2");  
							});	  
				  $('input[class=optionsCheck_TcThree]:checked').each(
							function() { 
								 array.push($(this).val()+"_3");  
							});
			  } 
	  }
	  else
		  {   
			  if(type_id==10||type_id==16)
			  { 
			  var ans1=$('#fillin_answer_one').val(); 
			  array.push(ans1);  
			  }
		  if(type_id==13||type_id==17)
			  { 
			     var ans1=$('#fillin_answer_one').val();
				 var ans2=$('#fillin_answer_two').val(); 
				 array.push(ans1); 
				 array.push(ans2);  
			  }
		  
		  if(type_id==4||type_id==5||type_id==6||type_id==7||type_id==8||type_id==9||type_id==11||type_id==12||type_id==15)
			  {
			  $('input[class=optionsCheck]:checked').each(
						function() { 
							 array.push($(this).val());  
						}); 
			  } 
		  if(type_id==14){ 
			 var swords = $(".select-in-passage").text().split(". "); 
			   for(var i=0;i< (swords.length-1); i++){ 
				   if($('#selectedText').val()==swords[i])
					    array.push(""+(i+1));
			  } 
		  }
		  }
			  
	 
	  var index=$('#index').val();  
     //var userAnwers=$('#userAnswers').val();   
	   var userAnwers=array; 
	  var newJson=JSON.parse($('#updatedAnswers').val()); 
	  var isAnswered=""; 
	  //var isFlagged=$('#markStatus').val(); 
	  var isFlagged=""; 
	  if(array.length==0)
		  {
		  isAnswered='UNANSWERED';
		  }
	  else
		  {
		  isAnswered='ANSWERED';
		  } 
	     if($("#_mark").hasClass("active"))
		  { 
	    	 isFlagged='FLAG';
		  }
		 else
		  {
			 isFlagged='UNFLAG';
		  }  
	  for (var i=0; i<newJson.length; i++) {
		  if (newJson[i].Question_index == index) {
			  newJson[i].UserAnswer=userAnwers;
			  newJson[i].IsAnswered=isAnswered;
			  newJson[i].IsFlagged=isFlagged;
			  newJson[i].User_time=usertime;
		  }
		}
	  //alert(JSON.stringify(newJson));
	   
	 // $('#remainingTime').val($('#countdown').html());
	  $('#updatedAnswers').val(JSON.stringify(newJson)); 
	  var a=JSON.parse($('#updatedAnswers').val());  
	   
	  var dt=$('#questionsForm').serialize();
	  var index=$('#index').val();
	  // alert("In Next Question"+$('#questionid').val());
	   		$.ajax({  
	   		type : "POST",
			url :"submitFullLengthTest.action", 
			data:dt,
			datatype:'json',
			success : success,
			error:error
		}); 
		function success(data,status){ 
			var condition=JSON.parse(data).isLastIndex;
			if(condition=='NO')
				{
				getFullTestNextQuestion();
				}
			else
				{
				//getFullTestNextQuestion(); 
				$.ajax({ 
			  		type : "POST",
			  		url :"Exit-Section-Page.action",
			  		success : function(replaceDivWithExistSectionPage){ 
			  			$('#overlayText').html( replaceDivWithExistSectionPage);},
			  		error: errorAtGettingExistSectionPage
			  	});   
				 
				 confirm("Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page Continue to the SimpleModal Project page?", function () {
						
					});
				}  
			$('#teststartstatus').val('true');
		}
		function error(error){ 
			alert('Error:'+error);
		}
  }
  
  function getFullTestNextQuestion()
  {
	  var nextOrPrevious='next';
	  displayQuestion(nextOrPrevious);
  }
  function PreviousQuestion()
  {     
	  $('#remainingTime').val($('#countdown').html());
	  var dt=$('#questionsForm').serialize(); 
	   		$.ajax({  
	   		type : "POST",
			url :"submitFullLengthTest.action", 
			data:dt,
			success : success,
			error:error
		}); 
		function success(data,status){ 
			 var nextOrPrevious='previous';
			 displayQuestion(nextOrPrevious);
		}
		function error(error){ 
			alert('Error:'+error);
		}
  } 
  
  function displayQuestion(nextOrPrevious)
  { 
	    Example1.resetStopwatch();
	    Example1.Timer.toggle();
	    var index=$('#index').val();	
		var nextIndex = parseInt(index); 
		if(nextOrPrevious=='next')
			{
			nextIndex = nextIndex + 1;
			}
		else if(nextOrPrevious=='previous')
			{
			nextIndex = nextIndex - 1;
			} 
		else if(nextOrPrevious=='return')
		{
		nextIndex = nextIndex;
		} 
		else
			{
			nextIndex = nextOrPrevious.split("-")[1]; 
			}  
		var qid,typeid;
	    var data = $('#questionsData').val();   
		$.each(JSON.parse(data), function(key, val) {				
				if (val.Question_index == nextIndex) {				
					$('#index').attr('value', nextIndex);  
					qid=val.Question_id;
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
					  
					/* var skills=(val.Skills);
					 $('#skillOne').val(skills[0]);
					 $('#skillTwo').val(skills[1]);
					 $('#skillThree').val(skills[2]);*/		
					 
					 $('#difficulty_level').val(val.Difficulty_name);  
					 var index = $('#index').val();
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
				}); 
  }
 
  $(document).ready(function(){  
	  $('.optionsCheck').click(function(){
		  var array=[];
		  var index=$('#index').val();
		   var flagStatus="";
		  $('input[class=optionsCheck]:checked').each(
					function() { 
						 array.push($(this).val());  
					}); 
			 var ans=JSON.parse($('#updatedAnswers').val());    
				  if($('#_mark').hasClass("active"))
					  { 
					   flagStatus="FLAG";
					  }
				  else
					  { 
					  flagStatus="UNFLAG";
					  } 
			  for(var i=0;i<ans.length;i++){
				 if(ans[i].Question_index==index){ 
					 ans[i].UserAnswer=array; 
					 ans[i].IsFlagged=flagStatus;
				 }
			 } 
			 $('#updatedAnswers').val(JSON.stringify(ans));  
		  $('#userAnswers').val(array);
	      });  
	  $('#_mark').click(function(){
		  if(!$(this).hasClass("active"))
			  {
			  $('#markStatus').val('FLAG');
			  }
		  else
			  {
			  $('#markStatus').val('UNFLAG');
			  } 
	  }); 
	 $('.text-keyup').keyup(function(){ 
		 var flagStatus="";
		 var index=$('#index').val();
		 var array=[];
		 if($('.text-keyup').length==2)
			 {
			  var ans1=$('#fillin_answer_one').val(); 
			  array.push(ans1); 
			  $('#userAnswers').val(array);
			 }
		 if($('.text-keyup').length==3)
		 {
			 var ans1=$('#fillin_answer_one').val();
			 var ans2=$('#fillin_answer_two').val(); 
			 array.push(ans1); 
			 array.push(ans2); 
			 $('#userAnswers').val(array);
		 }
		 if($('#_mark').hasClass("active"))
		  { 
		   flagStatus="FLAG"; 
		  }
		  else
			  { 
			  flagStatus="UNFLAG"; 
			  } 
		 var ans=JSON.parse($('#updatedAnswers').val()); 
		 for(var i=0;i<ans.length;i++){
			 if(ans[i].Question_index==index){ 
				 ans[i].UserAnswer=array; 
				 ans[i].IsFlagged=flagStatus;
			 }
		 } 
		 $('#updatedAnswers').val(JSON.stringify(ans));  
	  });   
	 $('.optionsCheck_TcOne,.optionsCheck_TcTwo,.optionsCheck_TcThree').click(function(){ 
		 var array=[];
		 var index=$('#index').val();
		 var flagStatus="";
		  $('input[class=optionsCheck_TcOne]:checked').each(
					function() { 
						 array.push($(this).val()+"_1");  
					}); 
		  $('input[class=optionsCheck_TcTwo]:checked').each(
					function() { 
						 array.push($(this).val()+"_2");  
					}); 
		  $('input[class=optionsCheck_TcThree]:checked').each(
					function() { 
						 array.push($(this).val()+"_3");  
					});  
			  if($('#_mark').hasClass("active"))
			  { 
			   flagStatus="FLAG";
			  }
		     else
			  { 
			  flagStatus="UNFLAG";
			  } 
			  var ans=JSON.parse($('#updatedAnswers').val()); 
				 for(var i=0;i<ans.length;i++){
					 if(ans[i].Question_index==index){ 
						 ans[i].UserAnswer=array; 
						 ans[i].IsFlagged=flagStatus; 
					 }
				 } 
				 $('#updatedAnswers').val(JSON.stringify(ans));  
	 }); 
	/* $('.select-in-passage').click(function(){
		 alert('clicked');
	 });*/
	 
  }); 
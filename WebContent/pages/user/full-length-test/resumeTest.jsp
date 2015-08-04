<!DOCTYPE html>
<html lang="en" class="app">
<%@taglib prefix="s" uri="/struts-tags" %>
<head>  
 
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <!-- CSS for verbal questions -->
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/practice.css" type="text/css" />
 <!-- CSS for verbal questions -->
<!-- For Question Index -->
  
 <link href="assets/css/full-length-test/QuestionArea.css" rel="stylesheet" />
	  
	<!-- Calcy CSS Start -->
  
  <link href="assets/plug-ins/calculator/Site.css" rel="stylesheet" />
  <link href="assets/plug-ins/calculator/calcyPageLevelCSS.css" rel="stylesheet" />
  <link href="assets/plug-ins/calculator/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
  
 <script src="assets/plug-ins/calculator/jquery.min.js"></script> 
 
 <script src="assets/js/full-length-test/marking.js"></script>
 <!-- <script src="js/calculator/jquery-ui.min.js" type="text/javascript"></script> -->
 <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>

 
  <!-- Calcy CSS End -->
  <!-- Overlay Css & Scripts -->
  <link type='text/css' href='assets/plug-ins/overlay/confirm.css' rel='stylesheet' media='screen' />
  <!-- Overlay Css & Scripts -->
<script>
  $(function() {
    $( ".draggable" ).draggable({  containment: "content", cursor: "crosshair"});
  });
 </script>
	
	
  
  
  <script type="text/javascript">
  
  
  $(document).ready(function(){
	  $('#timerDiv').hide(); 
      $('#_back').attr('disabled',true);
	  $('#answeredQuestions').val(0);
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
				 }
				 else
				 {
					 $('#submitAnswer').removeAttr('disabled');
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
				  $('#submitAnswer').attr('disabled',true);
		    	}
			  else
				  {
				  $('#submitAnswer').removeAttr('disabled');
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
	
	 
		
		// alert('im from doucument Ready');
	  if($('#timeLimitInPage').val()=="on")
		{
	     		//call setIntervalTimer here to submit a from
	     		//A count-down timer will be added here instead of normal one
	     		
	     		$('#setTime').html("<b id=countdown>00:00:00</b> <input type=hidden name='startTime' value='60000'/>");
		}
		else $('#setTime').html("<b id=countdown>00:00:00</b>");
	  
	  
	
	  $(".fadeOut").click(function() {
		  $("#setTime").toggle();
		  if($(this).text()=="Hide Timer")	
		  $(this).text("ShowTime").removeClass("btn-success").addClass("btn-primary");
		  else
			  $(this).text("Hide Timer").removeClass("btn-primary").addClass("btn-default"); 
	  });
	   
  }); 
  
  </script>
  
  <script>
  
  $(document).ready(function(){
	  
	 $('.btn-group-justified,.less-hr,#_timer,#_goToQuestion,#_returnFromReview,#_Continue,#_BackSection').hide(); 
	 
	 
  });
  </script>
</head>
<body class="">
<form style="display:none" id="questionsForm" method="post">
<s:hidden id="questionsData"  value="0"  />
<s:hidden id="directions_page"  value="0"/>
<s:hidden id="questionid"  value="0" name="questionid"/>
<s:hidden id="answers" value="-1" name="useransweredvalue"/>
<s:hidden id="updatedAnswers" name="totalquestions"></s:hidden>

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
<s:hidden id="index" value="0" name="questionIndex"/>
<s:hidden id="choices" value="0" name="questionsUploadBO.choices"/>
<s:hidden id="questionanswers" value="0" name="answers"/>

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
<s:hidden id="answeredQuestions"></s:hidden>	
<s:hidden id="graphPath"></s:hidden>
	 
<s:hidden id="gotoQuestion"></s:hidden>
<s:hidden id="userAnswers"></s:hidden>
<s:hidden id="markStatus"></s:hidden>
<s:hidden id="sectionNumber" value="0" name="sectionNumber"></s:hidden>
<s:hidden id="resumeSectionNumber" value="%{#session.resumeBean.sectionid}"></s:hidden>
<s:hidden id="resumequestionIndex" value="%{#session.resumeBean.questionIndex}"></s:hidden>
<s:hidden id="totalSections" value="5"></s:hidden>
<s:hidden id="usertime"  name="usertime"></s:hidden>

<s:hidden id="sectionTime"  name="sectionTime"></s:hidden>
<s:hidden id="breakTime"></s:hidden>



<!-- For Start Section  -->
<s:hidden id="startSection"></s:hidden>
<s:hidden id="sectionTimerValue"></s:hidden>
<!-- For Start Section  -->


</form>

  <section class="vbox">
     
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
				
                <!---Start of Full-length-test row--->
                <div class="row">
                
         
            		<div class="col-xs-12">
                    
                    <div class="panel panel-default">
                    
                            <div class="panel-body">
                            
                            <div class="row">
               
               				 <div class="col-xs-12 col-sm-12 col-lg-6 col-lg-offset-6 " id="_buttonsArea">
                
                			 <div class="m-b-sm full-test-sm m-b-0">
                			 
		                	 <div class="btn-group btn-group-justified" id='confirm-dialog'>
		                	 
		                   
                                                            
                          	 <a href="#" class="btn btn-default btn-test quit_confirm" id="_quit" onclick="quitTest()"> Quit<br> Test</a>
		                 
                             <a href="#" class="btn btn-default btn-test exit_confirm" id="_exit" onclick="getExitSectionPage()"> Exit <br> section</a>
                             
                             <a href="#" class="btn btn-default btn-test pause_confirm" id="_pause" onclick='Example2.Timer.toggle();Example1.Timer.toggle();'> Pause </a>
                             
                              <a href="#" class="btn btn-default btn-test" id="showCalc">Calcy</a>
		                 
                             <a class="btn btn-default btn-test" onclick="getReviewPage()" id="_review"> Review <br>
                              <i class="fa fa-edit hide-icons-f-test"></i>
                             </a>
                                      <!-- javascript:marking()  -->             
                          	 <a href="#" class="btn btn-default btn-test" data-toggle="button" id="_mark" onclick="marking()">Mark <br>
                          	  <i class="fa fa-square-o hide-icons-f-test text "></i>
							  <i class="fa fa-check-square-o hide-icons-f-test text-active " ></i>
                             </a>
                              
                             <a href="#" class="btn btn-default btn-test help_confirm" id="_help" onclick="helping()"> Help<br>
                              <i class="fa fa-question-circle hide-icons-f-test"></i>
                             </a>
                          
                             <a href="javaScript:PreviousQuestion();" class="btn btn-primary btn-test" id="_back">
                              <i class="fa fa-backward hide-icons-f-test"></i> Back
                             </a>
                             
                             <a href="javaScript:NextQuestion();" class="btn btn-success btn-test" id="_next">
                              Next <i class="fa fa-forward hide-icons-f-test"></i>
                             </a>
                             
                             <!-- Return & Continue for review -->
                             
                             <a href="#" class="btn btn-primary btn-test" id="_returnFromReview" onclick="returnFromReview()">
                              <i class="fa fa-backward hide-icons-f-test"></i> Return
                             </a>
                             
                             <a href="#" class="btn btn-success btn-test" id="_goToQuestion" onclick="goToQuestion()">
                              Goto <br> Question <i class="fa fa-forward hide-icons-f-test"></i>
                             </a>
                             
                              <a href="#" class="btn btn-success btn-test" id="_Continue">
                              Continue <i class="fa fa-forward hide-icons-f-test"></i>
                             </a>
                             
                            
                          <!-- Return & Continue for review -->
                            
		                </div>
		              </div>
                                                                    
               		 </div> <!--btn-links-->   
                       
                     </div> <!--btn-row-close--> 
                   
                        <div class="row" id="_timer">
                        <div class="col-xs-3 col-sm-3 question_index"><p id="questionno"></p></div>
                        
                        <div class="col-xs-3 col-sm-3 question_index"><p id="sectionnumber"></p></div>
                    	<div class="col-xs-6 col-sm-6 timer_index">
                                     
                     	<div class="pull-right p-t-10 full-test-sm">
                        <a href="#" class="btn btn-xs btn-test btn-default fadeOut">Hide Timer</a>
                        <p class="h5 d-b-in p-l-5" style="line-height:1;"><b id="setTime"></b></p>
                        </div><!--pull right class close-->
                         <div class="pull-right p-t-10 full-test-sm" id="timerDiv">
                        <a href="#" class="btn btn-xs btn-test btn-default">Question time</a>
                        <p class="h5 d-b-in p-l-5"  style="line-height:1;"><b id="timeSpent" title="">00:00:00</b></p>
                       
                        </div><!--pull right class close-->
                        <div class="clearfix"></div>
                        
                        </div>

                        </div> <!--Hide row timer close--> 
                        
                        
                        
                        
                        		  <hr class="less-hr">
                        		 
<div class="well-practice practice-content optionsCheck text-keyup optionsCheck_TcOne optionsCheck_TcTwo optionsCheck_Three" id="question_div">

										
												
                                                
                                               
											   
											   <div class="row"><!--row start main-->
             <div class="col-lg-12" >
             
             <div class="panel panel-default">
            
             <div class="panel-body">
            
             
             
             
             <div class="row" id="_verbalInstructions">
             <div class="col-sm-8 col-sm-offset-2 ">
            
            
             <p  style="font-size: 36px; margin-top:30px;" class="font-bold text-center">GRE FULL LENGTH TEST</p>
             
             <ul>
             <li><p align="left"> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is <br>
             	that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing <br>             	             	             	
              </p>
              </li>
              
              <li>
               <p align="left"> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is <br>
             	that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing <br>
             	             	
              </p>
              </li>
              </ul>
            <br>
          	 <center><a class="btn btn-success" href="javaScript:resumeTest();">Resume Test</a></center>
            
             </div>
            
             </div>
             
             
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
											   
                                                
										
						</div>					
									
                                    		<!---single num entr-->
                        
                        </div> <!---Row question area close-->
                        

                        
                           
                              
                    </div> <!--Panel body close-->
                    
                    </div> <!---Panel-close--->
                    
                    </div> <!--Main col close-->
                      
                      
                      
                <!-- Overlay  Code -->
             
          <div id="confirm">

               
                      

                <div class="header"><span id="status_btn">Confirm</span></div>
                  
<div id="overlayText" >
                
                
                </div>   
                </div>
               
        <!-- Overlay  Code -->
        
        
       
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
         
        </section>
     
 
<script type="text/javascript" src="assets/plug-ins/jquery-timer/jquery.timer.js"></script>
<script type="text/javascript" src="assets/plug-ins/jquery-timer/res/demo1.js"></script>
<script src="assets/plug-ins/timer/jquery.countdown360.js" type="text/javascript"></script>
<script src="assets/js/full-length-test/review.js"></script>
<script src="assets/js/full-length-test/instructions.js"></script> 
<script src="assets/js/full-length-test/existSection.js"></script> 

 
  <script type='text/javascript' src="assets/js/full-length-test/retrieval_navigations.js"></script>
  
</body>
</html>
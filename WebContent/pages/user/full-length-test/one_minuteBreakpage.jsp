<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
   
 <!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script> -->
  <!-- <script type="text/javascript" src="assets/plug-ins/counter/jquery.countdownTimer.js"></script>  -->  
  <link rel="stylesheet" type="text/css" href="assets/plug-ins/counter/jquery.countdownTimer.css" />
  <link rel="stylesheet" href="assets/css/review.css" type="text/css" /> 
<!-- <script src="assets/plug-ins/timer/jquery.countdown360.js" type="text/javascript"></script> -->
<!-- <script src="assets/js/app.js"></script>  
  <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="assets/js/app.plugin.js"></script>  -->
 <script type="text/javascript" src="assets/plug-ins/jquery-start-stop-timer/breakTimer.js"></script>
  
  
</head>
<body class="">
   
                        <div class="row">
                    	<div class="col-xs-12">
                      
                      
                        <div class="clearfix"></div>
                        </div>

                       
                       
                       		
								<div class="row mt-5-per mb-5-per">
											<div class="well-practice practice-content ">
												<div class="col-xs-12 col-sm-10 col-sm-offset-1  col-lg-8 col-lg-offset-2 m-t" >
													 <center><span  id="m_timer" class="style colorDefinition size_lg"><!-- <span id="cd_h">00</span>: --><span id="cd_m">00</span>:<span id="cd_s">00</span></span></center>
													 <hr>
                                                <p>you still have time to write your response. As long as there is time remaining. You can keep writinh or revising your response		
                                                </p>
												<p>Click <strong > Return</strong> to keep writing or revising. Click <strong> Continue</strong> to leave this question.</p>
												<p>Once you leave this question you <strong>Will Not</strong> be able to return to it</p>
                                               
                                                <!-- <script>
                                                    $(function(){
                                                        $('#m_timer').countdowntimer({
                                                            minutes :$('#breakTime').val(),
                                                            size : "lg"
                                                        });
                                                    });
                                                    
                                                    function timeComplete(){
                                                    	
                                                    	if(!$('#_timer').is(':visible') && !$('.please').is(':visible') && !$('a.btn.instructions').is(':visible')){
                                                    	goToInstructions();
                                                    	//alert("Time completed Goto Instructions called");
                                                    	}
                                                    	//alert("Time completed");
                                                    	
                                                    }
                                                </script> -->
                                                
                                                 
												<!-- If time completed it calls goToInstructions(); in breakTimer.js -->
												</div>
                                                
                        </div><!---Row closed for well practice-->
                        
                        </div><!---Row question area close-->
                            
                    </div><!--Panel body close-->
                    
                   
               

</body>
</html>
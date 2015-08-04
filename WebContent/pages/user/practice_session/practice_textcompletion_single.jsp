<!DOCTYPE html>
 <%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en" class="app">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/practice.css" type="text/css" />
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
 
</head>

											<div class="well-practice practice-content">
											
											<s:hidden id="chart"	value="%{#request.chart}"></s:hidden>
									<s:hidden id="col"	value="%{#request.col}"></s:hidden>
									<s:hidden id="gotitPercentage"	value="%{#request.gotItPercent}"></s:hidden>
									<s:hidden id="avgTime"	value="%{#request.avgTime}"></s:hidden>
												<p class="text-center m-b"><strong>Instructions : </strong><label id="directions"></label></p>

												<div class="col-xs-12 col-lg-10 col-lg-offset-1 m-t-5" >
													
														 <p id="questiontext">
                                                         
                                                          </p>
																																					
												</div>
                                                
                                                
                                                <!-----------Single answer area---->
												
                                                	<div class="row  ">               
                                                       <div class="col-sm-4 col-sm-offset-4 col-xs-8 col-xs-offset-2">
                                                         
                                                            <div class="row text-blank-t-m">
                                                            
                                                                <div class="col-xs-12 col-lg-8 col-lg-offset-2 options" >
                                                                
                                                                <form>
                                                                    <input type="radio" id="choice11" name="b" value="all"  class="optionsCheck_TcOne">
                                                                    <label for="choice11" id="choice1"></label>
                                                                    
                                                                    <input type="radio" id="choice22" name="b" value="false" class="optionsCheck_TcOne">
                                                                    <label for="choice22" id="choice2"></label>
                                                                     
                                                                    <input type="radio" id="choice33" name="b" value="true" class="optionsCheck_TcOne">
                                                                    <label for="choice33" id="choice3"></label>
                                                                    
                                                                    <input type="radio" id="choice44" name="b" value="true" class="optionsCheck_TcOne">
                                                                    <label for="choice44" id="choice4"></label>
                                                                    
                                                                    <input type="radio" id="choice55" name="b" value="true" class="optionsCheck_TcOne">
                                                                    <label for="choice55" id="choice5"></label> 
                                                                     
                                                                </form>       
                                                              
                                                                </div>
                                                          
                                                            </div>
                                                     
                                                       </div><!--Question Answers area close-->
                                                    
                                                    
												</div>
                                                 <!-----------Single answer area End ---->
                                                
                                                
                                               
                                                
                                                
                                                
											</div><!--Question Answers area close-->
											
										<div class="row xs-answer-options" id="check_answers" style='z-index:666'>
									     <div class="col-xs-12">	
										<div class="row xs-options">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right">
													<a href="javaScript:skipQuestionMobile()" id="nextButtonMobile" class="btn btnn btn-sm btn-primary">Skip Question</i> </a>
												    <a href="javaScript:submitAnswerMobile()" id="submitAnswerMobile" class="btn btnn btn-sm btn-success">Submit Answer</a>
												</div>      
											</div>
										</div>
										</div>
										</div>

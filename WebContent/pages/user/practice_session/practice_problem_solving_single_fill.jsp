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
												<p class="text-center m-b"><strong>Instructions : </strong><label id="directions"></label>	</p>

												<div class="col-xs-12 col-lg-10 col-lg-offset-1 m-t-5" >
													
														 <p id="questiontext">
                                                          
                                                          </p>
																																					
												</div>
                                                
                                                
                                                <!-----------Single answer area---->
												
                                                       <div class="col-xs-4 col-xs-offset-4">
                                                                        
                                                            <div>
                                                                <input type="text" name = "b" id="fillin_answer_one" class="form-control text-keyup" >
                                                            </div>
                                                                                
                                                    
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

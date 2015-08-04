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
												
                                                	<div class="row">               
                                                       <div class="col-xs-12 col-lg-10 col-lg-offset-1 m-t-5">
													   <div class="radio i-checks">
															<label>
																<span class="visible-for-label">A.</span><input id="choice11" type="radio" name="b" class="optionsCheck"><i class="hide-for-label"></i><label id="choice1"></label>	
															</label>
														</div>
														
                                                        <div class="radio i-checks">
															<label>
																<span class="visible-for-label">B.</span><input id="choice22" type="radio" name="b" class="optionsCheck"><i class="hide-for-label"></i><label id="choice2"></label>	
															</label>
														</div>
                                                        
                                                        <div class="radio i-checks">
															<label>
																<span class="visible-for-label">C.</span><input id="choice33" type="radio" name="b" class="optionsCheck"> <i class="hide-for-label"></i><label id="choice3"></label>	
															</label>
														</div>
                                                        
                                                        <div class="radio i-checks">
															<label>
																<span class="visible-for-label">D.</span><input id="choice44" type="radio" name="b" class="optionsCheck"><i class="hide-for-label"></i><label id="choice4"></label>	
															</label>
														</div>
														
														<div class="radio i-checks">
															<label>
																<span class="visible-for-label">E.</span><input  id="choice55" type="radio" name="b" class="optionsCheck"><i class="hide-for-label"></i><label id="choice5"></label>	
															</label>
														</div>
													</div>
												</div>
                                                 <!-----------Single answer area End ---->
                                                
                                                
                                               
                                                
                                                
                                                
											</div><!--Question Answers area close-->
											
										<hr class=" xs-answer-options" >
								<div class="row xs-answer-options" style='z-index:666'>
									<div class="col-xs-12">
										<div class="row xs-options">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right">
												 <form>
                                                                                <input type="radio" id="choice111" name="radios" value="all"  >
                                                                                <label for="choice111">A</label>
                                                                                
                                                                                <input type="radio" id="choice222" name="radios" value="true">
                                                                                <label for="choice222">B</label>
                                                                                
                                                                                <input type="radio" id="choice333" name="radios" value="true">
                                                                                <label for="choice333">C</label>
                                                                                
                                                                                <input type="radio" id="choice444" name="radios" value="true">
                                                                                <label for="choice444">D</label>
                                                                                
                                                                                 <input type="radio" id="choice555" name="radios" value="true">
                                                                                <label for="choice555">C</label>
                                                                                
                                                                                <input type="radio" id="choice666" name="radios" value="true">
                                                                                <label for="choice666">D</label>
                                                                                
                                                                            </form> 
                                                                            </div>      
											</div>
										</div>
										
										<div class="row xs-options">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right xs-ap">
												   <a href="javaScript:skipQuestionMobile()" id="nextButtonMobile" class="btn btnn btn-sm btn-primary">Skip Question</i> </a>
												    <a href="javaScript:submitAnswerMobile()" id="submitAnswerMobile" class="btn btnn btn-sm btn-success">Submit Answer</a>
												</div>      
											</div>
										</div>
									</div>
								</div>

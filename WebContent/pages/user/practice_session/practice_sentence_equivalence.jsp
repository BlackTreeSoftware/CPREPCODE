
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
												<p class="text-center m-b" ><strong>Instructions : </strong><label id="directions"></label></p>

												<div class="col-xs-12 col-lg-6 m-t" >
													<p id="questiontext">
														
													</p>
												</div>
                                              
                                                <div class="col-xs-12 col-lg-5 col-lg-offset-1 m-b" >
                 
                                                        <div class="row">
                                                        
                                                        <div class="col-xs-12">
                                                                            
                                                                        
                                                               <div class="form-group">
                                                                   
                                                                   
                                                                   
                                                                   
                                                                    <div class=" i-checks ">
                                                                      <label>
                                                                        <span class="visible-for-label">A.</span><input type="checkbox" id="choice11"  name="b" value="option1" class="choiceChecked optionsCheck">
                                                                        <i class="hide-for-label"></i>
                                                                       <label id="choice1"></label>
                                                                      </label>
                                                                    </div>
                                                                     
                                                                    <div class=" i-checks ">
                                                                      <label>
                                                                        <span class="visible-for-label">B.</span><input type="checkbox" id="choice22"  name="b" value="option2" class="choiceChecked optionsCheck">
                                                                        <i class="hide-for-label"></i>
                                                                        <label id="choice2"></label>
                                                                      </label>
                                                                    </div>
                                                                    
                                                                    <div class=" i-checks ">
                                                                      <label>
                                                                        <span class="visible-for-label">C.</span><input type="checkbox" id="choice33"  name="b" value="option3" class="choiceChecked optionsCheck">
                                                                        <i class="hide-for-label"></i>
                                                                       <label id="choice3"></label>
                                                                      </label>
                                                                    </div>
                                                                    
                                                                    <div class=" i-checks">
                                                                      <label>
                                                                        <span class="visible-for-label">D.</span><input type="checkbox" id="choice44" name="b" value="option4" class="choiceChecked optionsCheck">
                                                                        <i class="hide-for-label"></i>
                                                                        <label id="choice4"></label>
                                                                      </label>
                                                                    </div>
                                                                    
                                                                    <div class=" i-checks ">
                                                                      <label>
                                                                        <span class="visible-for-label">E.</span><input type="checkbox" id="choice55"  name="b" value="option5" class="choiceChecked optionsCheck">
                                                                        <i class="hide-for-label"></i>
                                                                        <label id="choice5"></label>	
                                                                      </label>
                                                                    </div>
                                                                    
                                                              
                                                                    
                                                               </div>
                                                              
                                                            </div>
                                                            
                                                        </div>
                 
              									 </div><!-----eND of Sentence Equivalence checkboxes----->
  											</div><!--practice content well end-->
											
									
                                    		<!---single num entr-->
                                    	<hr class=" xs-answer-options" >

								
								<div class="row xs-answer-options" style='z-index:666'>
									<div class="col-xs-12">
										<div class="row xs-options">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right">
												 <form>
                                                                                 <input type="checkbox" id="choice1111" name="radios" value="all"  >
                                                                                <label for="choice1111">A</label>
                                                                                
                                                                                <input type="checkbox" id="choice2222" name="radios" value="true">
                                                                                <label for="choice2222">B</label>
                                                                                
                                                                                <input type="checkbox" id="choice3333" name="radios" value="true">
                                                                                <label id="choice3333">C</label>
                                                                                
                                                                                <input type="checkbox" id="choice4444" name="radios" value="true">
                                                                                <label id="choice4444">D</label>
                                                                                
                                                                                <input type="checkbox" id="choice5555" name="radios" value="true">
                                                                                <label id="choice5555">E</label>
                                                                                
                                                                             
                                                                                
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
                                    
									
					
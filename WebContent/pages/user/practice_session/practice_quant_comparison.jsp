<!DOCTYPE html>
 <%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Application</title>
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
													<p id=questiontext>
													</p>
													<img  class="img-responsive img-center" id="imageSource"> 
													
													<div class="col-xs-12 text-center" >
														<span class="col-xs-6" style="display:inline-block;padding-left: 0px;"><strong>Column A</strong></span>
														<span class="col-xs-6" style="display:inline-block;padding-right: 0px;"><strong>Column B</strong></span>
														<hr class="less">
													</div>
													<div class="clearfix"></div>
													
													<hr class=" m-ten">
													
													<div class="col-xs-12 text-center" >
														<span class="col-xs-6 d-b-in p-l" id="qunatityA" ></span>
														<span class="col-xs-6 d-b-in p-l"  id="qunatityB" ></span>
													</div>
												</div>           
                                                <br>
                                                <br>
                                                <div class="col-xs-12 col-lg-6">
                                                <hr class=" hr-hidden">	
                                                	<!-- <p class="qarea m-left"> This is question area</p> -->
													<div class="form-group m-left">
													   <div class="radio i-checks">
															<label>
																<span class="visible-for-label">A.</span><input type="radio" id="choice11" name="b" class="optionsCheck" value=""><i class="hide-for-label"></i><label id="choice1" class="choiceChecked"  name="choice"></label>
															</label>
														</div>
														
                                                        <div class="radio i-checks">
															<label>
																<span class="visible-for-label">B.</span><input type="radio" id="choice22" name="b" class="optionsCheck" value=""><i class="hide-for-label"></i><label id="choice2" class="choiceChecked" name="choice"></label>
															</label>
														</div>
                                                        
                                                        <div class="radio i-checks">
															<label>
																<span class="visible-for-label">C.</span><input type="radio" id="choice33" name="b" class="optionsCheck" value=""><i class="hide-for-label"></i><label id="choice3" class="choiceChecked"  name="choice"></label>
															</label>
														</div>
                                                        
                                                        <div class="radio i-checks">
															<label>
																<span class="visible-for-label">D.</span><input type="radio" id="choice44" name="b" value="" class="optionsCheck"><i class="hide-for-label"></i><label id="choice4" class="choiceChecked"  name="choice"></label>
															</label>
														</div>
													</div>
												</div>
												
                                            
												<!------------Quant comparsion end------------>
                                                
											</div><!--practice content well end-->

                                          

	<!-----------------Options for xs------------------------->
								<hr class=" xs-answer-options" >

								
								<div class="row xs-answer-options" style='z-index:666'>
									<div class="col-xs-12">
										<div class="row xs-options" style="margin-bottom:11px;">
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
                                                                                
                                                                            </form> 
                                                                            </div>      
											</div>
										</div>
										
										<div class="row xs-options" style="margin-bottom:11px;">
											<div class="col-lg-12">
												<div class="btn-group btn-group-justified pull-right xs-ap">
												   <a href="javaScript:skipQuestionMobile()" id="nextButtonMobile" class="btn btnn btn-sm btn-primary">Skip Question</i> </a>
												    <a href="javaScript:submitAnswerMobile()" id="submitAnswerMobile" class="btn btnn btn-sm btn-success">Submit Answer</a>
												</div>      
											</div>
										</div>
									</div>
								</div>        
											
								
		<!------------------End of options for xs-------------------------------------->
 
	
</body>
</html>
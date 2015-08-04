<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
 
</head>
<body>


											<div class="well-practice practice-content">
											
											<s:hidden id="chart"	value="%{#request.chart}"></s:hidden>
									<s:hidden id="col"	value="%{#request.col}"></s:hidden>
									<s:hidden id="gotitPercentage"	value="%{#request.gotItPercent}"></s:hidden>
									<s:hidden id="avgTime"	value="%{#request.avgTime}"></s:hidden>
												<p class="text-center m-b" ><strong>Instructions : </strong><label id="directions"></label></p>

												<div class="col-xs-12 col-lg-6 m-t" >
													
													<img class="img-responsive img-center" id="imageSource">
												</div>
                                                    
                                                 <div class="col-xs-12 col-lg-6">
                                                <hr class=" hr-hidden">	
                                                	<p class="qarea m-left" id="questiontext"></p>
													<div class="form-group m-left">           
                                                    <div class="col-xs-4 col-xs-offset-4">
                                                                        
                                                            <div>
                                                                <input type="text" class="form-control text-keyup" id="fillin_answer_one" >
                                                            </div>
                                                                                
                                                    
                                                        </div>
                                                        </div>
                                                        </div>
                                                        
                                             
												<!------------Quant comparsion end------------>                 
											</div><!--practice content well end-->
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

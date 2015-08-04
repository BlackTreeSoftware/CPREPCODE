<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html lang="en" class="app">
<head>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<title>Crunchprep | GRE Web Application</title>
  <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css"/>
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css"/>
  <link rel="stylesheet" href="assets/css/full-length-test/reports/reports.css" type="text/css"/>
</head>
<body>

	
	<section id="content">
					<section class="vbox">          
						<section class="scrollable wrapper">
            				
                            <section class="panel">
							<section class="panel-body">
                            <div class="container-fluid">
                                <!--header-->
                                <div class="row" style="border-bottom:rgba(0,0,0,0.1) 1px solid; margin-bottom:10px;"><div class="col-sm-12"><h4>Overall Results</h4></div></div>
                                <!--header close-->
                            </div>
                            
                            <div class="container">                             
                                  <div class="row" style="border-bottom:rgba(0,0,0,0.1) 1px solid; margin-bottom:10px;">                                    
                                    <div class="col-md-5 col-sm-4">                                
                                 	   		 
											<div class="col-xs-12 col-sm-6">  
                                            	<center>  <div id="chart2" style="min-width: 150px; height:160px;  max-width:160px; margin: 0 auto"></div></center>
                                            </div>
                                             
                                             <div class="col-xs-12 col-sm-6 m-t-45">                                              		
                                                   
                                                    <div class="row text-center">                                          	  
                                                    <div class="col-sm-9 col-xs-6 font-18">
                                                    <span class="text-success">Correct</span>
                                                    </div> 
                                                    <div class="col-sm-3 col-xs-4 font-18">  
                                                    <span class="text-success" id="correct"></span>  
                                                    </div>
                                                    </div><!--internal row end-->
                                                    
                                                    <div class="row m-t-10 text-center">  
                                                    <div class="col-sm-9 col-xs-6 font-18">                                                                                                 
                                                    <span class="text-danger">In Correct</span>
                                                    </div>
                                                    <div class="col-sm-3 col-xs-4 font-18">  
                                                    <span class="text-danger"  id="incorrect"></span>
                                                    </div><!--internal row end-->
                                                    </div>  
                                              </div><!--internal col end-->
                                              
                                  	</div><!--col 1 closed--->
                                    
                                    
                                        <div class="col-md-6 col-sm-8">   
                                     	 <div class="row">
                                                                                
                                              <div class="col-xs-12 col-sm-6 m-t-45 text-center">      
                                                    <span class="font-18 text-primary"><i class="fa fa-clock-o"></i> Total time</span>
                                                    <h3 class="text-success-lt" id="totaltime"></h3>
                                              </div><!--internal col end-->
                                         
                                         <div class="col-xs-12 col-sm-6 text-center m-t-45 text-left">   
                                        	<span class="font-18 m-t-10 text-primary"> <i class="fa fa-question"></i> Guessed</span> <span class="font-18 m-l-30 text-primary" id="guessed"></span>
                                            <br>
                                            <span class="font-18 text-danger"> <i class="fa fa-flag"></i> Flagged</span> <span class="font-18 m-l-30 text-danger" id="flagged"> </span>
										  </div>
                                         		
                                         
                              		 	</div><!--internal row closed-->
                                       </div><!--col end-->
                                          
                                       </div><!--Main row closed-->
                                                                          
                              	</div><!--container close for first line-->
                                
                                <div class="container m-t-15">
                                	<div class="row">
                                		<div class="col-xs-12">
                                		 	 <center>
                                             <div class="row font-18 p-b-10">                                             
                                                 <div class="col-xs-4 col-sm-2 col-sm-offset-3">
                                                 	Avg Answered
                                                 </div>
                                             
                                                 <div class="col-xs-4 col-sm-2">
                                                 	Quant
                                                 </div>
                                                                                              
                                                 <div class="col-xs-4 col-sm-2">
                                                 	Verbal
                                                 </div>                                             
                                             </div>
                                          	  
                                               <div class="row font-18 p-b-10">                                             
                                                 <div class="col-xs-4 col-sm-2 col-sm-offset-3 text-success">
                                                 	Correct
                                                 </div>
                                             
                                                 <div class="col-xs-4 col-sm-2 text-success" id="quantCorrectTime">
                                                 	
                                                 </div>
                                                                                              
                                                 <div class="col-xs-4 col-sm-2 text-success" id="verbalCorrectTime">
                                                 	
                                                 </div>                                             
                                             </div>
                                             
                                             <div class="row font-18 p-b-10">                                             
                                                 <div class="col-xs-4 col-sm-2 col-sm-offset-3 text-danger">
                                                 	In Correct
                                                 </div>
                                             
                                                 <div class="col-xs-4 col-sm-2 text-danger" id="quantIncorrectTime">
                                                 	
                                                 </div>
                                                                                              
                                                 <div class="col-xs-4 col-sm-2 text-danger" id="verbalIncorrectTime">
                                                 	
                                                 </div>                                             
                                             </div>
                                             
                                             </center>
                                		</div>
                                	</div>
                                </div><!--container close for second line-->
                            </section><!--section panel body-->
                            </section><!--section panel close-->
							
                           <!--Data table Start -->   		
            	<div id="demo">

                            <section class="panel panel-default">
                                <header class="panel-heading">
                                  Performance Summary
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>
                               <div class="table-responsive">
                               <div class="table-responsive">
                                  <table class="table table-striped m-b-none" data-ride="datatables" id="table">
                                    <thead>
                                      <tr>
                                        <th>Test Name</th>
                                        <th>Test Date</th>
                                        <th>Analysis</th>
                                      
                                      </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="performanceSummary" status="status">
                                    <tr><s:if test="testId==1">
                                    	<td><a href=result?testno=<s:property value="testno"/>><s:property value="testName"/></a></td>
                                    	</s:if>
                                    	<s:else>
                                    	
                                    <td><a href=full_length_result?reviewTestNumber=<s:property value="testno"/>><s:property value="testName"/></a></td>
                                    	
                                    	</s:else>
                                    	<td> <s:property value="testDate"/></td>
                                    	<s:if test="testId==1">
                                    	<td>
										<span class="table-inline b-r pr-10"> Correct : <span class="font-bold text-muted"><s:property value="correctPercentage"/></span>%</span> <span class="table-inline pl-10">  Incorrect : <span class="font-bold text-success"> <s:property value="incorrectPercentage"/> </span>%</span>                                    	
                                    	</td>
                                    	</s:if>
                                    	<s:else>
                                    	<td>
										<span class="table-inline b-r pr-10"> Quant : <span class="font-bold text-muted"><s:property value="quantScore"/></span></span> <span class="table-inline pl-10">  Verbal : <span class="font-bold text-success"> <s:property value="verbalScore"/>  </span></span>                                    	
                                    	</td>
                                    	</s:else>
                                    </tr>
                                    </s:iterator>
                                    </tbody> 
                                  </table>
                                </div>
						</div>
						</section>	
                        </div>
          
				<!--Data table End -->
								
						</section>
					</section>
					<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
				</section>
			</section>



 <script src="assets/js/full-length-test/reports/overallperformance.js"></script>
 <script src="assets/js/highcharts.js"></script>
 <script src="assets/js/datatables/jquery.dataTables.min.js"></script>



</body>
</html>

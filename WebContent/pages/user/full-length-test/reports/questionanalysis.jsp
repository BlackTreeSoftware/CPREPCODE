<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css"/> -->
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" /> 
<link rel="stylesheet" href="assets/css/skill_data/jquery.dataTables.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css"/>
  <link rel="stylesheet" href="assets/css/full-length-test/reports/reports.css" type="text/css"/>
   
  
  
</head>
<body>
<s:hidden value="%{qaData}" id="qaData"></s:hidden>
<s:hidden value="%{avgTimeData}" id="fastSlowData"></s:hidden>
<s:hidden value="%{qaDataTable}" id="qaDataTable"></s:hidden>
 <s:hidden value="%{#request.filterReasons}" id="filterReasons"/>
<section id="content">
					<section class="vbox">          
						<section class="scrollable wrapper">
            				
                            <section class="panel">
							<section class="panel-body">
                            <div class="container-fluid">
                               <!--header-->
                                <div class="row" style="border-bottom:rgba(0,0,0,0.1) 1px solid; margin-bottom:10px;"><div class="col-sm-12"><h4>Question Analysis  </h4></div></div>
                                <!--header close-->
                            </div>
                            
                            <div class="container-fluid">                             
                                  <div class="row">                                    
                                    <div class="col-md-6 col-sm-6 b-r">                                    
                                    <div class=" m-b-20 m-t-20 text-center p-b-10 h2 font-bold text-primary"> Total Questions : <span class="text-info-dk" id="_totalQuestions"></span> </div>            
                                    <div class="row">
                                   
                                  
                                    	<div class="col-xs-12 col-sm-3 col-sm-offset-2">  
                                            	<center>  <div id="chart3" style="min-width: 150px; height:160px;  max-width:160px; margin: 0 auto"></div></center>
                                            </div>
                                             
                                             <div class="col-xs-12 col-sm-6 m-t-45">                                              		
                                                   
                                                    <div class="row p-b-10">                                          	  
                                                    <div class="col-sm-4 col-xs-4 col-xs-offset-2 font-18">
                                                    <span class="text-success">Solved</span>
                                                    </div> 
                                                    <div class="col-sm-3 col-xs-4 font-18">  
                                                    <span class="text-success" id="_solvedQuestions"></span>  
                                                    </div>
                                                    </div><!--internal row end-->
                                                    
                                                    <div class="row">  
                                                    <div class="col-sm-4 col-xs-4 col-xs-offset-2 font-18">                                                                                                 
                                                    <span class="text-danger">Left</span>
                                                    </div>
                                                    <div class="col-sm-3 col-xs-4 font-18">  
                                                    <span class="text-danger" id="_leftQuestions"></span>
                                                    </div><!--internal row end-->
                                                    </div>  
                                              </div><!--internal col end-->
                                     
                                     </div>         <!--row end-->
                                                  
                                  	 </div><!--col-sm closed--->
                                    
                                        <div class="col-md-6 col-sm-6 ">   
                                         <div class=" m-b-20 m-t-20 text-center p-b-10 h2 text-primary font-bold"> Total Answered : <span class="text-info-dk" id="_totalAnswered"> </span> </div>  
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-3 col-sm-offset-2">  
                                            	<center>  <div id="chart2" style="min-width: 150px; height:160px;  max-width:160px; margin: 0 auto"></div></center>
                                            </div>
                                                          <div class="col-xs-12 col-sm-6 m-t-45">                                              		
                                                                <div class="row p-b-10">                                          	  
                                                                <div class="col-sm-5 col-xs-4 col-xs-offset-2 font-18">
                                                                <span class="text-success">Correct</span>
                                                                </div> 
                                                                <div class="col-sm-3 col-xs-4 font-18">  
                                                                <span class="text-success" id="_correctQuestions"></span>  
                                                                </div>
                                                                </div><!--internal row end-->
                                                                
                                                                <div class="row">  
                                                                <div class="col-sm-5 col-xs-4 col-xs-offset-2 font-18">                                                                                                 
                                                                <span class="text-danger">In correct</span>
                                                                </div>
                                                                <div class="col-sm-3 col-xs-4 font-18">  
                                                                <span class="text-danger" id="_incorrectQuestions"></span>
                                                                </div><!--internal row end-->
                                                                </div>  
                                                          </div><!--internal col end-->
                                                                    
                                                     
                                                </div><!--Internal row closed-->                                        		                                        
                                        </div><!--main col 6 closed-->                                          
                                                                                		
                                             
                              		 </div><!--row closed for first line-->
                                     <hr>
                              	</div><!--container close for first line-->
                                
                                <div class="container-fluid p-b-20 p-t-20">
                                	<div class="row">
                                    
                                		 <div class="col-md-6">   
                                        
                                        	<div class="row">                                        
                                            <div class="col-xs-4 col-xs-offset-4 font-bold font-18 p-b-20">
                                             Quant
                                            </div>
                                            
                                            <div class="col-xs-4 font-18 p-b-20 font-bold">
                                             Verbal
                                            </div>
                                            
                                            </div>                               			
											
                                            <div class="row m-t-10">
                                            <div class="col-xs-4 col-sm-3 col-sm-offset-1  font-18">
                                            Fastest<br><br>
                                           
                                            <div class="mt-480 p-t-10">Slowest</div>
                                            </div>
                                            
                                            <div class="col-xs-4 col-sm-4">
                                            <div class="h5 font-bold text-success " id="qcfTitle"></div>
                                            <div class="text-success m-t-5"> Correct  <span class=" m-l-20" id="qcf"></span></div><br>
                                            <div class="h5 font-bold text-danger p-t-10" id="qcsTitle"></div>
                                            <div class="text-success"> Correct  <span class="m-l-20" id="qcs"></span></div><br>
                                            </div>
                                            
                                            <div class="col-xs-4 col-sm-4">
                                            <div class="h5 font-bold text-success " id="vcfTitle"></div>
                                            <div class="text-success m-t-5"> Correct  <span class=" m-l-20" id="vcf"></span></div><br>
                                            <div class="h5 font-bold text-danger p-t-10" id="vcsTitle"></div>
                                            <div class="text-success"> Correct  <span class="m-l-20" id="vcs"></span></div><br>
                                            </div>                                           
                                            
                                            </div>                                            
                                                                                                     
                               			</div><!---col6 closed--->
                                        
                                        
                                        
                                        <div class="col-md-6">   
                                        
                                        	<div class="row">                                        
                                            <div class="col-xs-4 col-xs-offset-4 font-bold font-18 p-b-20">
                                             Quant
                                            </div>
                                            
                                            <div class="col-xs-4 font-18 p-b-20 font-bold">
                                             Verbal
                                            </div>
                                            
                                            </div>                               			
											
                                            <div class="row m-t-10">
                                            <div class="col-xs-4 col-sm-3 col-sm-offset-1  font-18">
                                            Fastest<br><br>
                                           
                                            <div class="mt-480 p-t-10">Slowest</div>
                                            </div>
                                            
                                            <div class="col-xs-4 col-sm-4">
                                            <div class="h5 font-bold text-success" id="qwfTitle"></div>
                                            <div class="text-danger m-t-5"> Wrong  <span class=" m-l-20" id="qwf"></span></div><br>
                                            <div class="h5 font-bold text-danger p-t-10" id="qwsTitle"></div>
                                            <div class="text-danger"> Wrong  <span class="m-l-20" id="qws"></span></div><br>
                                            </div>
                                            
                                            <div class="col-xs-4 col-sm-4">
                                            <div class="h5 font-bold text-success " id="vwfTitle"></div>
                                            <div class="text-danger m-t-5"> Wrong  <span class=" m-l-20" id="vwf"></span></div><br>
                                            <div class="h5 font-bold text-danger p-t-10" id="vwsTitle"></div>
                                            <div class="text-danger"> Wrong  <span class="m-l-20" id="vws"></span></div><br>
                                            </div>                                           
                                            
                                            </div>
                                            
                                                                                                     
                               			</div><!---col6 closed--->
                                    </div>
                                </div>
                                
                            </section><!--section panel body-->
                            </section><!--section panel close-->
							
                            <!--Data table Start -->   		
            	<div id="demo">

                            <section class="panel panel-default">
                                <header class="panel-heading">
                                  Question Analysis 
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>
                               <div class="table-responsive">
                               <div class="table-responsive">
                                 
                                  
                                  <table id="example1" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th></th>
						<th>Result</th>
						<th>Question Category</th>
						<th>Guessed</th>
						<th>Flagged</th>
						<th>Date</th>
						<th>Reason</th>
						<th>Retry</th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th></th>
						<th>All</th>
						<th>All</th>
						<th>All</th>
						<th>All</th>
						<th></th>
						<th>All</th>
						<th></th>
					</tr>
				</tfoot>
				
			</table>
                                </div>
						</div>
						</section>	
                        </div>
          
				<!--Data table End -->   
								
						</section>
					</section>
	<script src="assets/js/full-length-test/reports/questionanalysis.js"></script>
 	<script src="assets/js/highcharts.js"></script>
  	<%-- <script src="assets/js/datatables/jquery.dataTables.min.js"></script>  --%>
  	<script src="assets/js/filter-dataTable/jquery.dataTables.js"></script>
  	<script src="assets/js/filter-dataTable/jquery.dataTables.columnFilter.js"></script>
</body>
</html>
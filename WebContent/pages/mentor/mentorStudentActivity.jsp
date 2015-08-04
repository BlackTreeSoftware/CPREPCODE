<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
 <script src="assets/js/hicharts/highcharts.js"></script>  
<script type="text/javascript">
 $(document).ready(function(){

 var oTable = $('#table').dataTable({

											"bProcessing" : true,

											"sDom" : "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
											"sPaginationType" : "full_numbers",
											"aoColumns" : [ {
												"mData" : "columnname1"
											}, {
												"mData" : "columnname2"
											}, {
												"mData" : "columnname3"
											}, {
												"mData" : "columnname4"
											}

											]
											

										});
 
 
  var oTable = $('#lessons').dataTable( {

	    "bProcessing": true,

	   

	    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
	    "sPaginationType": "full_numbers",
	    "aoColumns": [
	      { "mData": "columnname1" },
	      { "mData": "columnname2" },
	      { "mData": "columnname3" },
	      { "mData": "columnname4" }
	    ]
	  } );
  
  var oTable = $('#fulllengthtable').dataTable( {

	    "bProcessing": true,

	   

	    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
	    "sPaginationType": "full_numbers",
	    "aoColumns": [
	      { "mData": "columnname1" },
	      { "mData": "columnname2" },
	      { "mData": "columnname3" },
	      { "mData": "columnname4" },
	      { "mData": "columnname5" }
	    ]
	  } );
  
  var Originaldata=JSON.parse($('#Originaldata').val());
	var xAxisData=JSON.parse($('#xAxisData').val());
	 $.fn.getHighchart(xAxisData,Originaldata);
	  $.fn.datatableColumns();
 
 
 });
 
 $.fn.getHighchart= function(xAxisData,Originaldata) {
	  $('#container').highcharts({
		  
           title: {
               text: 'Practice Session Summary',
               x: -20 //center
           },
           subtitle: {
               text: '',
               x: -20
           },
           xAxis: {
           	 
               categories: xAxisData
              //categories:['2014-06-10 10:07:36.0-Practice Test','2014-06-11 10:07:36.0-Practice Test','2014-06-12 10:07:36.0-Practice Test','2014-06-13 10:07:36.0-Practice Test','2014-06-14 10:07:36.0-Practice Test','2014-06-15 10:07:36.0-Practice Test','2014-06-16 10:07:36.0-Practice Test']
           },
           yAxis: {
               title: {
                   text: 'Marks Percentage(%)'
               },
               plotLines: [{
                   value: 0,
                   width: 1,
                   color: '#808080'
               }]
               
           },
           tooltip: {
               valueSuffix: '%',
               headerFormat: '{point.x}: <b>{point.y}%</b><br/>',
               pointFormat: '<a href="http://www.google.com" style="color:blue"> Review & Analysis</a>'
           },
           legend: {
               layout: 'vertical',
               align: 'right',
               verticalAlign: 'middle',
               borderWidth: 0
           },
           series: [{
               name: "Practice Session",
               data:Originaldata
               //data:[12,14,16,18,15,78,96]
           } ],
            
       });
   };
 
 </script>

</head>
<body>
 
      <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">                                            		
                           
                		<div class="row" style="padding-left:0px;">              
						  <div class="col-sm-12 ">
						  
						  <section class="panel panel-default">
								<header class="panel-heading bg-light" >
								<ul class="nav nav-tabs nav-justified">
									<li class="active"><a href="#lesson" data-toggle="tab">Lessons</a></li>
									<li class=""><a href="#practice" data-toggle="tab">Practice Session</a></li>   
									<li class=""><a href="#fulllength" data-toggle="tab">Full Length</a></li>   									
								  </ul>
								</header>
								<div class="panels-body">
								  <div class="tab-content">
									<div class="tab-pane active" id="lesson">
									
										<div class="panels-body">
														<div class="table-responsive">
                                                          <table class="table table-striped m-b-none" data-ride="datatables" id="lessons">
                                                            <thead>
                                                              <tr>
                                                                <th width="20%">S.No</th>
                                                                <th width="20%">Lessons Taken</th>
                                                                <th width="25%">Time</th>
                                                                <th width="25%">Total Time Taken</th>                                                                
                                                              </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:iterator value="lessonsList" status="row">
                                           
                                           					<tr>
                                           					<td><s:property value="#row.index+1" /></td>
															<td><s:property value="lesson_name" /></td> 
															<td><s:property value="lesson_date" /></td>
															<td><s:property value="lesson_timespent" /></td>
																							 
												 

											</tr>
                                           </s:iterator>              
                                                            </tbody>
                                                          </table>
                                                        </div>										 
										</div>
									</div>
									<!-------MATH TAB END----->
									<div class="tab-pane" id="practice">
									
										<div class="panels-body">
										<s:hidden value="%{#request.xAxisData}" id="xAxisData"/> <s:hidden value="%{#request.Originaldata}" id="Originaldata"/>
										<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto;width:80%"></div><br/><br/>
													  <div class="table-responsive">
                                                           <table class="table table-striped m-b-none" data-ride="datatables" id="table">
														<thead>
															<tr>
												<th>S.No</th>
												<th>Session Name</th> 
												<th>Date</th>
												<th>Correct(%)</th>
															</tr>
														</thead>
														<tbody>
															
																 <s:iterator value="practiceSummaryTable_List" status="row">
                                           
                                           <tr>
                                           <s:hidden id="testno"  value="%{test_no}"/>
                                            <td><s:property value="#row.index+1" /></td>
												<td><s:property value="practiseSessioName" /></td> 
												<td><s:property value="test_date" /></td>
												<td><s:property value="sessionCorrectPercentage" /></td>
																							 
												 

											</tr>
                                           </s:iterator>                 
																


															
														</tbody>
													</table>
                                                        </div>						
										 </div>
								
									</div>          

								   <div class="tab-pane" id="fulllength">
									
										<div class="panels-body">
													   <div class="table-responsive">
                                                          <table class="table table-striped m-b-none" data-ride="datatables" id="fulllengthtable">
                                                            <thead>
                                                              <tr>
                                                                <th width="20%">Rendering engine</th>
                                                                <th width="25%">Browser</th>
                                                                <th width="25%">Platform(s)</th>
                                                                <th width="15%">Engine version</th>
                                                                <th width="15%">CSS grade</th>
                                                              </tr>
                                                            </thead>
                                                            <tbody>
                                                            </tbody>
                                                          </table>
                                                        </div>									
										 </div>	
										 
									</div>                    
									
								  </div>
								</div>
							  </section>
             
                      
                          
					
                    
                    

                <!--Nestable table close-->
                
              </div>
              </div><!--row close for upper menus-->
              
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
      </section>
		<script src="assets/js/datatables/jquery.dataTables.min.js"></script>
</body>
</html>
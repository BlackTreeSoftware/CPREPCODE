
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>
        <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
        <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
       
        
        <script src="assets/js/hicharts/highcharts.js"></script>        
        <script src="assets/js/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
         <script src="assets/js/datatables/demo.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			//alert(' document ready ');
			var Originaldata=JSON.parse($('#Originaldata').val());
			var xAxisData=JSON.parse($('#xAxisData').val());
			//alert(' Jos Data is; '+Originaldata+'\n Data is; '+  xAxisData);
			/* //$('#hicharts-button path:first').addCSS('display:none');
			$('#button').click(function(){
				
				
				 $.getJSON('getpracticesummaryChart.action',function(jsonResponse) {					 
					  var xAxisData=jsonResponse.xAxisData;
					  var Originaldata=jsonResponse.Originaldata;
					  var sessionNames=jsonResponse.sessionNames;
					 // alert(Originaldata+"\tsample"+xAxisData);
			  	   $.fn.getHighchart(xAxisData,Originaldata,sessionNames); 
			     });
				
			}); */
			  $.fn.getHighchart(xAxisData,Originaldata);
			  $.fn.datatableColumns();
			/*   $('#review').click(function(){
				  document.form.action="result.action";
				  form.action.submit();
			  }); */
			  
			  
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
    $.fn.datatableColumns = function () {
        var oTable = $('#table').dataTable({

            "bProcessing": true,

            "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
            "sPaginationType": "full_numbers",
            "aoColumns": [{
                    "mData": "columnname1"
                }, {
                    "mData": "columnname2"
                }, {
                    "mData": "columnname3"
                }, {
                    "mData": "columnname4"
                }, {
                    "mData": "columnname5"
                } 

            ]
        });
    };
     
    
    function review(testno){
    	alert(testno);
    	$('#test_no').val(testno);
    	$('#form').attr('action','result.action');
    	$('#form').submit();
    }
    
    
      	</script>
	</head>
	  <body class="">

        <section id="content">
            <section class="vbox">
                <section class="scrollable wrapper">
					 
                    <div class="row" style="padding-top:10px">
                        <!--row start main-->
                        <div class="col-lg-12">

                            <div class="panel panel-default">
                                <div class="panel-heading"> <span class="panel-title">Practice Session Summary</span> 
                                </div>
                                <div class="panel-body"> 
                                                 
                        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div><br/><br/>
                        <s:hidden value="%{#request.xAxisData}" id="xAxisData"/> <s:hidden value="%{#request.Originaldata}" id="Originaldata"/>
                                                    <s:form id="form" name="form" theme="simple">
                                                      <s:hidden id="test_no" name="testno" value=""/>
                                                    <center><span style="font-weight:bold">Total Practice Session History</span></center>
                                              <div class="table-responsive" id="table_data">

                                                            <table class="table table-striped m-b-none" id="table">
										<thead>
											<tr>
											    <th>S.No</th>
												<th>Session Name</th> 
												<th>Date</th>
												<th>Correct(%)</th>
												<th>Review</th>											 
												 


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
												<td><%-- <s:submit value="Review" id="review"/> --%><a href="#" onclick="review('<s:property value="test_no" />');" id="review" class="btn btn-default">Review</a></td>											 
												 

											</tr>
                                           </s:iterator>                           
										
									 
										
										</tbody>
 
									</table>
									<%-- <s:property value="practiceSummaryTable_List.size()"/> --%>
								</div>
                                                      
                                                    </s:form>

                                               
                                              
                                          
                                         
                                       

                                    
                                </div>
                                <!--panel body close-->
                            </div>
                            <!--panel head close-->

                        </div>
                        <!--div close-->
                    </div>
                    <!--row start main End-->

                </section>
            </section>

        </section>
	
	</body>
</html>

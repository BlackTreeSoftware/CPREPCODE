<!DOCTYPE HTML>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		<script type="text/javascript" src="../../../assets/js/jquery.min.js"></script>
		   <link rel="stylesheet" href="../../../assets/js/datatables/datatables.css" type="text/css" />
        <link rel="stylesheet" href="../../../assets/css/custom.css" type="text/css" />
        <link rel="stylesheet" href="../../../assets/js/datatables/jquery.dataTables.min.js" type="text/css" />  
        <script src="../../../assets/js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			//$('#hicharts-button path:first').addCSS('display:none');
			$('#button').click(function(){
				
				
				 $.getJSON('getpracticesummaryChart.action',function(jsonResponse) {					 
					  var xAxisData=jsonResponse.xAxisData;
					  var Originaldata=jsonResponse.Originaldata;
					  var sessionNames=jsonResponse.sessionNames;
					 // alert(Originaldata+"\tsample"+xAxisData);
			  	   $.fn.getHighchart(xAxisData,Originaldata,sessionNames); 
			     });
				
			});
			 });
		
		$.fn.getHighchart= function(xAxisData,Originaldata,sessionNames) {
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
                headerFormat: '<span style="color:{series.color}"> </span>{series.name}{point.x}: <b>{point.y}%</b><br/>',
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
            } ],
             
        });
    };
    var x = 10;
    var o = { x: 15 };
     
    function f()
    {
        //alert(this.x);
    }
     
    f();
    f.call(o);
      	</script>
	</head>
	<body>
	<input type="button" id="button" value="click">
	 
<script src="../../../assets/js/hicharts/highcharts.js"></script>
  <script src="../../../assets/js/datatables/demo.js"></script>
 

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<div class="table-responsive" id="table_data">
                
									<table class="table table-striped m-b-none" id="table">
										<thead>
											<tr>
											    <th>S.No</th>
												<th>Session Name</th> 
												<th>Date</th>
												<th>%Correct</th>
												<th>Review</th>											 
												 


											</tr>
										</thead>
										<tbody>
										   
                                           <s:iterator value="practiceSummaryTable_List" status="row">
                                           
                                           <tr>
                                            <td><s:property value="#row.index+1" /></td>
												<td><s:property value="practiseSessioName" /></td> 
												<td><s:property value="test_date" /></td>
												<td><s:property value="sessionCorrectPercentage" /></td>
												<td><input type="button" value="Review"/></td>											 
												 


											</tr>
                                           </s:iterator>                             
										
									 
										
										</tbody>
 
									</table>
								</div>

	</body>
</html>

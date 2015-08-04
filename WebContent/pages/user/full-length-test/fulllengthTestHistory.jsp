
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>
        <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css"/>
        <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
        <script src="assets/js/hicharts/highcharts.js"></script>        
        <script src="assets/js/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
         <script src="assets/js/datatables/demo.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var global;
			
			  var formData = $('#formId2').serialize();
			  
				 $.ajax({
					type : 'POST',
					url : 'fulllengthTestHistoryJsonData.action', 	
					data : formData,
					dataType:'json',
					success :  function deleteSuccess(data, status) {			
						  global=data;
						 alert("in succes : "+data.jsonTableData.isDataAvilable);
						  highchartDisplay();
						  fullLengthHistoryTableData();
						
						},
					    error : function deleteFailed(errorIs) {
						alert(' Failed ' + errorIs.responseText);
					}

				}); 
			
				 
				 function highchartDisplay(){
				 
					   /*  alert("highchartsDisplay:::::::::::::::::::::::::::::::::::::::::"+global);
					    alert("Dates::::::::::::::::::::::::::::::"+global.dates.toString());
					    alert("Quant ::::::::::::::::::::::::::::::"+global.quant);
					    alert("Verbal::::::::::::::::::::::::::::::"+global.verbal);
					   alert("categories::::::::::::::::::::::::::::::"+JSON.parse('['+global.quant+']')); */
				        $('#container').highcharts({
				            title: {
				                text: 'Full Length Test History',
				                x: -20 //center
				            },
				           /*  subtitle: {
				                text: 'Source: WorldClimate.com',
				              
				                x: -20
				            }, */
				            xAxis: {
				              categories: global.dates
				             /* categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
			                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']  */
				               
				            },
				            yAxis: {
				                title: {
				                    text: 'Correct Answers Percentage(%)'
				                },
				                plotLines: [{
				                    value: 0,
				                    width: 1,
				                    color: '#808080'
				                }]
				            },
				            tooltip: {
				            	   valueSuffix: '%',
				                   headerFormat: '<b>{series.name}:',
				                   pointFormat: '<b>{point.y}</b><br/>'/* '<a href="http://www.google.com" style="color:blue"> Review & Analysis</a>' */
				            },
				            legend: {
				                layout: 'vertical',
				                align: 'right',
				                verticalAlign: 'middle',
				                borderWidth: 0
				            },
				            series: [{
				                name: 'Quant',
				               // data: global.quant
				               data: JSON.parse('['+global.quant+']')
				               /*  data: [7.0, 6.9, 9.5, 14.5, 15.2, 21.5, 25.2, 2.5, 23.3, 18.3, 13.9, 9.6] */
				            }, {
				                name: 'Verbal',
				                data: JSON.parse('['+global.verbal+']')
				               /*  data: [7.0, 6.9, 9.5, 14.5, 15.2, 21.5, 25.2, 2.5, 23.3, 18.3, 13.9, 9.6] */
				            }/* ,{
				                name: 'Skipped_Quant',
				                data: JSON.parse('['+global.skipped_quant+']')
				                //data: [7.0, 6.9, 9.5, 14.5, 15.2, 21.5, 25.2, 2.5, 23.3, 18.3, 13.9, 9.6]
				            },{
				                name: 'Skipped_Verbal',
				                data: JSON.parse('['+global.skipped_verbal+']')
				                //data: [7.0, 6.9, 9.5, 14.5, 15.2, 21.5, 25.2, 2.5, 23.3, 18.3, 13.9, 9.6]
				            } */]
				        });
				    
				    
				 }
				 function fullLengthHistoryTableData(){
					 
					  //alert("Fulllength History Table data is calling:::::::::::::::::::::::::::::::::::::::::::");
					 
					 // var data=result;
					   //alert(' data is: '+data);
					 // var jsonData=JSON.parse(global.tableData);
					  // alert(' Json Data in History is: '+global.jsonTableData.isDataAvilable);
					   if(global.jsonTableData.isDataAvilable){
					   var tableData=global.jsonTableData.tableData;
					     var userId =tableData.user_id;
					     var testNo
					   //alert(" table Data is: "+tableData);
					   var content = '';
					   content += '<tbody>';
					   $('#history_table').find("tr:gt(0)").remove();
					   $.each(tableData,function(index,value){
						  // alert(" index is; "+index+" vlaue is: "+value.verbal_correct_pecentage);
					    index=index+1;
					   // alert('Index is: '+index+" value is: "+value.empName);
					    
					    content += '<tr align="center" id="' + index + '">';
					    content += '<td >'+index+'</td>';  
					    content += '<td>'+value.session_name+'</td>';
					    content += '<td>'+value.TEST_DATE+'</td>';
					    content += '<td>'+value.Quant_correct_pecentage+'</td>';
					    content += '<td>'+value.verbal_correct_pecentage+'</td>';
					    content += '<td>'+value.Skipped_Quant_Count+'</td>';
					    content += '<td>'+value.Skipped_Verbal_Count+'</td>';
					   content += '<td><input type="button" onclick=review('+value.test_no+') value="Review"></td>'; 
					    content +='</tr>';
					    
					   });
					   content += '</tbody>';
					   $('#history_table').append(content);
					   }else{
					    
					   }
					   
					  }
					
					 
		 });
    
		
		 function review(testno){
		    	alert(" test Number is::::::::::::::::::::::::::::::::::::::::::::::::::::: "+testno);
		    	$('#testNumber').val(testno);
		    	$('#target').trigger('click');
		    }
		
		
      	</script>
	</head>
	
	  <body class="" id="formId2">

        <section id="content">
            <section class="vbox">	
                <section class="scrollable wrapper">
					 
                    <div class="row" style="padding-top:10px">
                        <!--row start main-->
                        <div class="col-lg-12">

                            <div class="panel panel-default">
                                <div class="panel-heading"> <span class="panel-title">Full Length Test History</span> 
                                </div>
                                <div class="panel-body"> 
                                                 
                        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div><br/><br/>
                           <s:hidden id="test_no" name="testno" value=""/>
                           <s:hidden id="user_Id" name ="user_Id" value=""/>
                            <s:form name="form" theme="simple">
                                 <s:hidden id="test_no" name="testno" value=""/>
                                  <center><span style="font-weight:bold">Total Full Length Test History</span></center>
                                              <!-- <div class="table-responsive" id="table_data"> -->
                                 <div>
                                 
                                  <table class="table table-striped m-b-none" id="history_table">
										<thead>
											<tr>
											    <th>S.No</th>
												<th>Session Name</th> 
												<th>Date</th>
												<th>Quant_Questions_Correct(%)</th>
												<th>Verbal_Questions_Correct(%)</th>
												<th>Skipped_Quant_Count</th>
												<th>Skipped_Verbal_Count</th>
												<th>Review</th>	
																				 
												 
											</tr>
										</thead>
										<tbody>
										</tbody>
										</table>
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
<form action="full_length_result">
<s:hidden id="testNumber" name="reviewTestNumber"/>
<s:submit id="target"/>
</form>
        </section>
	
	</body>
</html>

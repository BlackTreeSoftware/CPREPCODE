<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@taglib prefix="s" uri="/struts-tags" %>
 <html>
<head>
 
<link rel="stylesheet" href="assets/css/practice.css" type="text/css" />    
  
  <!--Customised Css for review module-->
  <link rel="stylesheet" href="assets/css/review.css" type="text/css" /> 
  
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
  <!-- PAGE LEVEL SCRIPTS -->
 
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<!-- <link rel="stylesheet" href="js/filter-dataTable/css/demo_table.css" type="text/css" />
<link rel="stylesheet" href="js/filter-dataTable/css/themes/base/jquery-ui.css" type="text/css" />
<link rel="stylesheet" href="js/filter-dataTable/css/themes/smoothness/jquery-ui-1.7.2.custom.css" type="text/css" /> -->
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" /> 
<link rel="stylesheet" href="assets/css/skill_data/jquery.dataTables.css" type="text/css" />
<!--<link rel="stylesheet" href="js/filter-dataTable/css/demo_table.css" type="text/css" />
<link rel="stylesheet" href="js/filter-dataTable/css/themes/base/jquery-ui.css" type="text/css" />-->
<!--<link rel="stylesheet" href="js/filter-dataTable/css/themes/smoothness/jquery-ui-1.7.2.custom.css" type="text/css" />-->

<style>

.lesson-content p{font-family:Verdana, Arial, sans-serif; font-size:15px; padding-left:40px; padding-right:40px;}

@media screen and (max-width:1024px) {										 									 
									 .font-15{font-size:14px;}									
									}

@media screen and (max-width:800px) {										 
									 .lesson-content p{font-family:Verdana, Arial, sans-serif; font-size:15px; padding-left:5px; padding-right:5px;}
									 .font-15{font-size:13px;}									
									}

</style>

 <script src="assets/js/jquery.min.js"></script>
 <script src="assets/js/highcharts.js"></script>
	<script type="text/javascript">
   $(document).ready(function(){ 
		//alert("DOC START");
		 var foo = '[' + $('#resultJson').val() + ']';
		// alert("FOO : "+foo);
		 var values = JSON.parse(foo);
		 var cat_select = JSON.parse($('#catagory_select').val());
		  alert('Cat List2 : '+cat_select);
		
     var table = $('#expandable-table').DataTable( {
     "data": values
     
     /* ajax:"ResultAction.action"  */,
     "columns": [
         {
             "class":          'details-control',
             "orderable":      false,
             "data":           null,
             "defaultContent": ''
         },
         { "data": "result" },
         { "data": "quesTitle" },
         { "data": "topic" },
         { "data": "type" },
         
         { "data": "date" }, 
       
         { "data": "reason" },
         { "data": "retry" }
     ],                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
     "order": [[1, 'asc']]
 } );                                                                                                                     
     
    // Add event listener for opening and closing details
    $('#expandable-table tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );
	
	//$('#expandable-table_length,#expandable-table_filter').hide();
	//cat_select
    $('#expandable-table').dataTable().columnFilter({
    	 
        aoColumns: [ null,
                     { type: "select", values: [ 'Correct', 'Wrong'], selected: ''  },
                     { type:"select", values:cat_select}                     
		   ]
	 });
    
    //Full Length Test Result Drill down chart is  begin:::::::::::::::::::::::::::::::::::::::::::
    
    var global;
	
	  var formData = $('#body1').serialize();
	  
		 $.ajax({
			type : 'POST',
			url : 'fulllengthTestResultPieChartJsonData.action', 	
			data : formData,
			dataType:'json',
			success :  function deleteSuccess(data, status) {
				     // //alert("success data is calling ::::::::::::::::::::::::::::::");
				  global=data;
				  
				  
				 
				 fulllengthTestPieChart();
			    fullLengthTest_scatter_BobleChart();
				  
				},
			    error : function deleteFailed(errorIs) {
			    //	//alert("Error data calling:::::::::::::::::::::::::::::::::::::::::::::::::");
				////alert(' Failed ' + errorIs.responseText);
			}

		}); 
	
		 function fulllengthTestPieChart() {
		     //alert("pie is calling:::::::::::::::::::::");
			    
			    //alert("Total Correct :::::::::::::::::::"+JSON.parse('['+global.toatal_correct_percentage+']'));
			    //alert("Total INCorrect :::::::::::::::::::"+JSON.parse('['+global.toatal_Incorrect_percentage+']'));
			    //alert("Total quant_Correct :::::::::::::::::::"+JSON.parse('['+global.quant_correct_percentage+']'));
			    //alert("Total verbal_Correct :::::::::::::::::::"+JSON.parse('['+global.verbal_correct_percentage+']'));
			    //alert("Total quant_InCorrect :::::::::::::::::::"+JSON.parse('['+global.quant_incorrect_percentage+']'));
			    //alert("Total verbal_InCorrect :::::::::::::::::::"+JSON.parse('['+global.verbal_incorrect_percentage+']'));
			    //alert("Total sKIPPED :::::::::::::::::::"+JSON.parse('['+global.skipped_percentage+']'));
			    //alert("Json Parse Data for Skipped question::::::::"+JSON.parse('['+global.quant_skipped_percentage+','+global.verbal_skipped_percentage+']')),
			    //alert("Json Parse Data for Incorrect question::::::::"+JSON.parse('['+global.quant_incorrect_percentage+','+global.verbal_incorrect_percentage+']')),
			    //alert("Json Parse Data for correct question::::::::"+JSON.parse('['+global.quant_correct_percentage+','+global.verbal_correct_percentage+']')),
			    //alert("JsonData no perseing:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");


			    //alert("Total Correct :::::::::::::::::::"+global.toatal_correct_percentage);
			    //alert("Total INCorrect :::::::::::::::::::"+global.toatal_Incorrect_percentage);
			    //alert("Total quant_Correct :::::::::::::::::::"+global.quant_correct_percentage);
			    //alert("Total verbal_Correct :::::::::::::::::::"+global.verbal_correct_percentage);
			    //alert("Total quant_InCorrect :::::::::::::::::::"+global.quant_incorrect_percentage);
			    //alert("Total verbal_InCorrect :::::::::::::::::::"+global.verbal_incorrect_percentage);
			    //alert("Total Skipped :::::::::::::::::::"+global.skipped_percentage); 

			    //alert("Total Correct :::::::::::::::::::"+global.toatal_correct_percentage);
			    //alert("Total INCorrect :::::::::::::::::::"+global.toatal_Incorrect_percentage);
			    //alert("Total quant_Correct :::::::::::::::::::"+global.quant_correct_percentage);
			    //alert("Total verbal_Correct :::::::::::::::::::"+global.verbal_correct_percentage);
			    //alert("Total quant_InCorrect :::::::::::::::::::"+global.quant_incorrect_percentage);
			    //alert("Total verbal_InCorrect :::::::::::::::::::"+global.verbal_incorrect_percentage);
			    //alert("Total Skipped :::::::::::::::::::"+global.skipped_percentage); 
			   ////alert("Json Parse Data for Skipped question::::::::"+JSON.parse('['+global.quant_incorrect_percentage+','+global.verbal_incorrect_percentage+']')),

			   
	        var colors = Highcharts.getOptions().colors,
	            categories = ['Correct', 'Incorrect','Skipped'],
	            name = 'Full length Test Results',
	            data = [{
	                    y: JSON.parse(global.toatal_correct_percentage),
	                    color: colors[4],
	                    drilldown: {
	                        name: 'Correct',
	                        categories: ['Quant Correct', 'Verbal Correct'] ,
	                        data:JSON.parse('['+global.quant_correct_percentage+','+global.verbal_correct_percentage+']'),
	                       // data: [10.85, 7.35],
	                        color: colors[4]
	                    }
	                }, {
	                    y:  JSON.parse(global.toatal_Incorrect_percentage),
	                    color: colors[3],
	                    drilldown: {
	                        name: 'Incorrect',
	                        categories: ['Quant Inorrect', 'Verbal Incorrect'],
	                        //data:[JSON.parse('['+global.quant_incorrect_percentage+']'),JSON.parse('['+global.verbal_incorrect_percentage+']')],
	                         data:JSON.parse('['+global.quant_incorrect_percentage+','+global.verbal_incorrect_percentage+']'),
	                        //data: [0.20, 0.83],
	                       color: colors[3]
	                    } 
	                },{
	                    y: JSON.parse(global.skipped_percentage),
	                    color: colors[1],
	                    drilldown: {
	                        name: 'total_skipped',
	                        categories: [' Quant Skipped Percentage','Verbal Skipped Percentage'],
	                        data: JSON.parse('['+global.quant_skipped_percentage+','+global.verbal_skipped_percentage+']'),
	                        color: colors[1]
	                    }
	                }];
	    
	    
	        // Build the data arrays
	        var browserData = [];
	        var versionsData = [];
	        for (var i = 0; i < data.length; i++) {
	    
	            // add browser data
	            browserData.push({
	                name: categories[i],
	                y: data[i].y,
	                color: data[i].color
	            });
	    
	            // add version data
	            for (var j = 0; j < data[i].drilldown.data.length; j++) {
	                var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
	                versionsData.push({
	                    name: data[i].drilldown.categories[j],
	                    y: data[i].drilldown.data[j],
	                    color: Highcharts.Color(data[i].color).brighten(brightness).get()
	                });
	            }
	        }
	    
	        // Create the chart
	        $('#container-pie').highcharts({
	            chart: {
	                type: 'pie'
	            },
	            title: {
	                text: 'Full Length Test Result Pie Chart'
	            },
	            yAxis: {
	                title: {
	                    text: 'Total percent market share'
	                }
	            },
	            plotOptions: {
	                pie: {
	                    shadow: false,
	                    center: ['50%', '50%']
	                }
	            },
	            tooltip: {
	        	    valueSuffix: '%'
	            },
	            series: [{
	                name: 'Browsers',
	                data: browserData,
	                size: '60%',
	                dataLabels: {
	                    formatter: function() {
	                        return this.y > 5 ? this.point.name : null;
	                    },
	                    color: 'white',
	                    distance: -30
	                }
	            }, {
	                name: 'marks',
	                data: versionsData,
	                size: '80%',
	                innerSize: '60%',
	                dataLabels: {
	                    formatter: function() {
	                        // display only if larger than 1
	                        return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'%'  : null;
	                    }
	                }
	            }]
	        });
	   
	   
	   
		
	   
	   

	}
	
    
    
    //Full Length Test Result Drill down chart is end:::::::::::::::::::::::::::::::::::::::::::::
    	
    	
    	
    	//Full Length Test Result Scatter_Boble Chart is Start:::::::::::::::::
    		
    		
    		
    		
    		
	    var global1;
	  //var formData = $('#body1').serialize();
	  
		 $.ajax({
			type : 'POST',
			url : 'fullLengthTestResultScatterBoobleChadrtJsonData.action', 	
			data : formData,
			dataType:'json',
			success :  function deleteSuccess(data, status) {


				  global1=data;
				//  //alert("GlobalData:::::::::::"+global1);
			
			    fullLengthTest_scatter_BobleChart();
				  
				},
			    error : function deleteFailed(errorIs) {
			    //	//alert("Error data calling:::::::::::::::::::::::::::::::::::::::::::::::::");
				////alert(' Failed ' + errorIs.responseText);
			}

		}); 
	
    		
		 function fullLengthTest_scatter_BobleChart() {

			   alert("Full length test scatter bobble chart is calling ::::::::::::::::::::::::");
			   alert("Quant_INCorrectData"+JSON.parse('['+global1.quant_Incorrect+']'));
			   alert("Quant_Correct:::::"+JSON.parse('['+global1.quant_correct+']'));
			   alert("Verbal_InCorrect:::::::::"+JSON.parse('['+global1.verbal_Incorrect+']'));
			   alert("Verbal_Correct:::::::::::::::"+JSON.parse('['+global1.verbal_correct+']'));
			 //alert("Correct:::::::::::::::::"+global1.correct);
			 //alert("Correct:::::::::::::::::"+global1.Incorrect);
			 //alert("After parse Json data:::::::::::");
			
			 //alert("Correct:::::::::::::::::"+'['+global1.correct+']');
			 //alert("Correct:::::::::::::::::"+'['+global1.Incorrect+']');
			 
			/*  //alert(' correct questions is: '+global1.quant_correct_percentage);
			 
					 //alert(' correct questions is: '+global1.quant_correct_percentage);
			 	//alert("Scatter bobble chasrt is calling ");
				//alert("User_Time::::::::::::::::::::::::::::::::::::"+'['+global1.time+']');
				//alert("Quant_Correct::::::::::::::::::::::::::::::::::::"+'['+global1.quant_correct_percentage+']');
				//alert("VERBAL_CORRECT::::::::::::::::::::::::::::::::::::"+'['+global1.verbal_correct_percentage+']');
				//alert("QUANT_INCORRECT::::::::::::::::::::::::::::::::::::"+'['+global1.quant_incorrect_percentage+']');
				//alert("VERBAL_INCORRECT::::::::::::::::::::::::::::::::::::"+'['+global1.verbal_incorrect_percentage+']');
			    //alert("After Json praseing the data :::::::::::::::::::::");
				//alert("Quant Correct percetage::::::::::::::::::::::::::::::::::::"+JSON.parse('['+'['+global1.quant_incorrect_percentage+','+globl1.time+']'+']'));

					 //alert(' correct questions is: '+global1.quant_correct_percentage);
			 	//alert("Scatter bobble chasrt is calling ");
				//alert("User_Time::::::::::::::::::::::::::::::::::::"+'['+global1.time+']');
				//alert("Quant_Correct::::::::::::::::::::::::::::::::::::"+'['+global1.quant_correct_percentage+']');
				//alert("VERBAL_CORRECT::::::::::::::::::::::::::::::::::::"+'['+global1.verbal_correct_percentage+']');
				//alert("QUANT_INCORRECT::::::::::::::::::::::::::::::::::::"+'['+global1.quant_incorrect_percentage+']');
				//alert("VERBAL_INCORRECT::::::::::::::::::::::::::::::::::::"+'['+global1.verbal_incorrect_percentage+']');
			    //alert("After Json praseing the data :::::::::::::::::::::");
				//alert("Quant Correct percetage::::::::::::::::::::::::::::::::::::"+JSON.parse('['+'['+global1.quant_incorrect_percentage+','+globl1.time+']'+']'));
				 

				//var st_crt = $("#stack_correct").val();	
				//var st_wrg = $("#stack_wrong").val(); 
				
				  /* var st_crt = "[[74.0, 65.6], [75.3, 71.8], [93.5, 80.7], [16.5, 2.6], [17.2, 8.8], [11.5, 4.8], [14.0, 6.4], [84.5, 8.4], [15.0, 42.0], [84.0, 11.6]]  ";
				var st_wrg = "[[74.0, 65.6], [75.3, 71.8], [93.5, 80.7], [16.5, 2.6], [17.2, 8.8], [11.5, 4.8], [14.0, 6.4], [84.5, 8.4], [15.0, 42.0], [84.0, 11.6]]  "; */  
				////alert('Correct : '+st_crt+'  Wrong : '+st_wrg);
				
				$('#container-fulllengthTestBobblechart').highcharts({
			        chart: {
			            type: 'scatter',
			            zoomType: 'xy'
			        },
			        title: {
			            text: 'Correct Incorrect'
			        },
			        subtitle: {
			            text: ''
			        },
			        xAxis: {
			            title: {
			                enabled: true,
			                text: 'User time'
			                	
			            },
			            startOnTick: true,
			            endOnTick: true,
			            showLastLabel: true,
			            min:0
			        },
			        yAxis: {
			            title: {
			                text: 'Percentage of People answered correct '
			                	
			            },
			            min: 0
			       
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'left',
			            verticalAlign: 'top',
			            x: 100,
			            y: 70,
			            floating: true,
			            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
			            borderWidth: 1
			        },
			        plotOptions: {
			            scatter: {
			                marker: {
			                    radius: 5,
			                    states: {
			                        hover: {
			                            enabled: true,
			                            lineColor: 'rgb(100,100,100)'
			                        }
			                    }
			                },
			                states: {
			                    hover: {
			                        marker: {
			                            enabled: false
			                        }
			                    }
			                },
			                tooltip: {
			                    headerFormat: '<b>{series.name}</b><br>',
			                    pointFormat: '{point.x} sec, {point.y} %'
			                }
			            }
			        },
			        series: [{
			            name: 'Quant Incorrect',
			            color: 'rgba(223, 83, 83, .5)',
			           //  data: global1.quant_incorrect_percentage
			             //data: JSON.parse('['+'['+global1.quant_incorrect_percentage+','+global1.time+']'+']')
			            //data: [[4, 0], [2, 0], [1, 1], [0, 0]]
			             data:JSON.parse('['+global1.quant_Incorrect+']')

			        } , {
			            name: 'Quant Correct',
			            color: 'rgba(26, 174, 136, .5)',
			          // data: global1.quant_correct_percentage
			           //  data: JSON.parse(global1.quant_correct_percentage)
			          //data: [[74.0, 74.0], [74.0, 74.0], [93.5, 80.7], [1.5, 2.6], [1.2, 5.1], [1.5, 4.8], [14.0, 3.2], [4.5, 8.4], [15.0, 42.0], [84.0, 11.6]]
                       data:JSON.parse('['+global1.quant_correct+']')
			        },{
			            name: 'Verbal Incorrect',
			            color: 'rgba(223, 83, 83, .5)',
			            data: JSON.parse('['+global1.verbal_Incorrect+']')
			            // data : global1.parent_Verbal_INCorrect
				   
			           // data: JSON.parse('['+'['+global1.verbal_incorrect_percentage+','+global1.time+']'+']')
			         //  data: [[74.0, 65.6], [75.3, 71.8], [3.5, 80.7], [6.5, 2.6], [7.2, 8.8], [11.5, 4.8], [4.0, 6.4], [4.5, 8.4], [15.0, 42.0], [84.0, 11.6]]

			        },{
			            name: 'Verbal Correct',
			            color: 'rgba(26, 174, 136, .5)',
			            data: JSON.parse('['+global1.verbal_correct+']')
			           // data:  JSON.parse('['+'['+global1.verbal_correct_percentage+','+global1.time+']'+']')
			          // data: [[71.0, 5.6], [75.3, 71.8], [3.5, 0.7], [1.5, 2.6], [10.2, 6.8], [12.5, 2.8], [14.0, 6.4], [84.5, 8.4], [15.0, 42.0], [84.0, 11.6]]

			        }]
			    });
				
				
				
				}	
						
		//Full Length Test Result scatter Booble Chart is End::::::::::::::::::::::::::::::
		
	});
	
	
	
	
	function format ( d ) {
		var his = d.hisdiv.replace(/@/g, '\"');
		var result = "<i class='fa fa-2x fa-check-circle text-success'></i> <span class='h3 text-success p-l-10'>"+d.result+"</span>";
		if(d.result=="Wrong") result = "<i class='fa fa-2x fa-times-circle text-danger'></i> <span class='h3 text-danger p-l-10'>"+d.result+"</span>";
	//	//alert(his);
			return '<div class="row" style="padding:12px;>'+
		    '<div class="col-xs-12">'+
			'<div class="col-xs-12"><span class="text-dkr"><h4 class="m-t-0">'+d.section_name+' / '+d.category_name+' / Difficulty : '+d.difficulty+'</h4></span> </div>'+
			'<div class="col-xs-12"><span class="font-15 text-dkr">You have answered this question</span> </div>'+
            '<div class="col-lg-8 col-sm-8 col-xs-12">'+
			'<hr><div class="row p-b-10"> <div class="col-xs-6">'+result+''+
			'<p class="font-15 m-t-5">You are among <span class="font-bold text-success">'+d.percentCorrect+'%</span> people who got this question correct</p></div> '+
			' <div class="col-xs-6"><i class="fa fa-2x fa-clock-o text-primary"></i> <span class="h3 text-primary p-l-10">'+d.user_time+' </span> <p class="font-15 m-t-5">Your pace is better than others pace: <span class="font-bold text-primary">'+d.avg_time+'</span></p></div> </div> '+
			'<hr class="hr-less">'+
			' <div class="row p-t-10"> <div class="col-xs-12"> <span class="col-xs-4 font-15"> <span class="font-bold text-success p-r-10">'+d.guessPercentage+'%</span> Guessed</span>'+ '<span class="col-xs-4 font-15 "> <span class="font-bold text-success p-l-10 p-r-10">'+d.guessIncorrectPercentage+'%</span> Guessed Incorrectly</span>'+'<span class="col-xs-4 font-15"> <span class="font-bold text-success p-l-10 p-r-10">'+d.flaggedPercentage+'%</span> Flagged</span></div></div>'+
			'</div>'+			
			'<div class="col-lg-4 col-sm-4 col-xs-12 text-primary">'+ 
			'<hr> <p class="h4 m-t-15">Question attempt history</p>'+
			'<hr class="hr-less">'+his+'</div>'
			;
		}
	
		
$(function () {
	
	var quant =$('#datacount').val();
	var verbal =$('#datacount1').val();
	//alert("quant Data : "+quant);
	//alert("Verbal Data : "+verbal);
	var correctCount=[];
	var wrongCount=[];
	var skipCount=[];
	var totalCount=[];
	
	
	var correctCount1=[];
	var wrongCount1=[];
	var skipCount1=[];
	var totalCount1=[];

	
	
	var userPace =$('#userPace').val();
	var userPace1 =$('#userPace1').val();
	
	////alert("User Pace Time   "+userPace1);
	var question_title=[];
	var user_time=[];
	var avg_time=[];
	
	var question_title1=[];
	var user_time1=[];
	var avg_time1=[];
	var sectionid=$('#sectionid').val();
	var sectionid1=$('#sectionid1').val();

////alert("Section Id 2 is  "+sectionid1);
	
	if(JSON.parse(quant).isDataAvailable){
	
	
	$.each(JSON.parse(JSON.parse(quant).percentageCount), function(key, val) {	
		//alert("entering into loop");
		 correctCount.push(val.CorrectCount1);
		// //alert("Correct count   "+val.CorrectCount1);
		// correctCount.push(val.CorrectCount2);
		 correctCount.push(val.CorrectCount3);
		 correctCount.push(val.CorrectCount4);
			if(sectionid!= 2){
		 correctCount.push(parseInt(val.CorrectCount5)+parseInt(val.CorrectCount6));
		 correctCount.push(val.CorrectCount7);
			}
			else
				{
				correctCount.push(val.CorrectCount5);
	            correctCount.push(val.CorrectCount6);
	            correctCount.push(val.CorrectCount9);
				}
		
		 
		 
		 wrongCount.push(val.WrongCount1);
		//wrongCount.push(val.WrongCount2);
		 wrongCount.push(val.WrongCount3);
		 wrongCount.push(val.WrongCount4);
		
			 
		if(sectionid!= 2){
	     wrongCount.push(parseInt(val.WrongCount5)+parseInt(val.WrongCount6));
		 wrongCount.push(val.WrongCount7);
		 
		}
		else
			{
			wrongCount.push(val.WrongCount5);
			wrongCount.push(val.WrongCount6);	
			wrongCount.push(val.WrongCount9);	
			}
		/*  if(sectionid == 2)
		 wrongCount.push(val.WrongCount8); */
		// wrongCount.push(val.WrongCount8);
		
		 totalCount.push(val.TotalCount1);
		
		 
		// //alert("in corect count calculation"+parseInt(val.TotalCount1)+"toal "+parseInt(parseInt(val.TotalCount1)-(parseInt(val.CorrectCount1)+parseInt(val.WrongCount1))));
		// totalCount.push(val.TotalCount2);
		 totalCount.push(val.TotalCount3);
		 totalCount.push(val.TotalCount4);
		 totalCount.push(val.TotalCount5);
		 totalCount.push(val.TotalCount6);
		 if(sectionid!= 2){
		 totalCount.push(val.TotalCount7);
		 }
		 else
			 {
			 totalCount.push(val.TotalCount9);
			 }
		// //alert(totalCount+"correctcount"+correctCount+"wrongcount"+wrongCount);
		 skipCount.push(parseInt(parseInt(val.TotalCount1)-(parseInt(val.CorrectCount1)+parseInt(val.WrongCount1))));
		 skipCount.push(parseInt(parseInt(val.TotalCount3)-(parseInt(val.CorrectCount3)+parseInt(val.WrongCount3))));
		 skipCount.push(parseInt(parseInt(val.TotalCount4)-(parseInt(val.CorrectCount4)+parseInt(val.WrongCount4))));
		 
		var s= parseInt(parseInt(val.TotalCount5)-(parseInt(val.CorrectCount5)+parseInt(val.WrongCount5)));
		var s1=parseInt(parseInt(val.TotalCount6)-(parseInt(val.CorrectCount6)+parseInt(val.WrongCount6)));
		 
		// //alert("in s valueeeeeeeeeeee"+s+"in 5 value"+s1);
		 if(sectionid!=2)
			 {
		 skipCount.push(parseInt(s)+parseInt(s1));
		 skipCount.push(parseInt(parseInt(val.TotalCount7)-(parseInt(val.CorrectCount7)+parseInt(val.WrongCount7))));
			 }
		 else
			 {
			 skipCount.push(parseInt(s));
			 skipCount.push(parseInt(s1));
			 skipCount.push(parseInt(parseInt(val.TotalCount9)-(parseInt(val.CorrectCount9)+parseInt(val.WrongCount9))));
			 }
		 
			 
		 //skipCount.push(parseInt(parseInt(val.TotalCount6)-(parseInt(val.CorrectCount6)+parseInt(val.WrongCount6))));
	
		/*  if(sectionid == 2)
		 totalCount.push(val.TotalCount8); */
			 
		/* if(sectionid==2)
			{
			//alert("in skipppp");
			 skipCount.push(parseInt(parseInt(val.TotalCount8)-(parseInt(val.CorrectCount8)+parseInt(val.WrongCount8))));
			} */
		
		// //alert(skipCount);
		
	}); 
	//alert("ending if");
	}
	//alert("before Verbal");
	if(JSON.parse(verbal).isDataAvailable){
		//alert("Entered in vaerbal");
	$.each(JSON.parse(JSON.parse(verbal).percentageCount), function(key, val) {	
		
		 correctCount1.push(val.CorrectCount1);
		// correctCount.push(val.CorrectCount2);
		 correctCount1.push(val.CorrectCount3);
		 correctCount1.push(val.CorrectCount4);
			if(sectionid1!= 2){
		 correctCount1.push(parseInt(val.CorrectCount5)+parseInt(val.CorrectCount6));
		 correctCount1.push(val.CorrectCount7);
			}
			else
				{
				correctCount1.push(val.CorrectCount5);
	            correctCount1.push(val.CorrectCount6);
	            correctCount1.push(val.CorrectCount9);
				}
		
		 
		 
		 wrongCount1.push(val.WrongCount1);
		//wrongCount.push(val.WrongCount2);
		 wrongCount1.push(val.WrongCount3);
		 wrongCount1.push(val.WrongCount4);
		
			 
		if(sectionid1!= 2){
	     wrongCount1.push(parseInt(val.WrongCount5)+parseInt(val.WrongCount6));
		 wrongCount1.push(val.WrongCount7);
		 
		}
		else
			{
			wrongCount1.push(val.WrongCount5);
			wrongCount1.push(val.WrongCount6);	
			wrongCount1.push(val.WrongCount9);	
			}
       /* if(sectionid1 == 2)
		 wrongCount1.push(val.WrongCount8); */
		// wrongCount.push(val.WrongCount8);
		
		 totalCount1.push(val.TotalCount1);
		
		 
		// ////alert("in corect count calculation"+parseInt(val.TotalCount1)+"toal "+parseInt(parseInt(val.TotalCount1)-(parseInt(val.CorrectCount1)+parseInt(val.WrongCount1))));
		// totalCount.push(val.TotalCount2);
		 totalCount1.push(val.TotalCount3);
		 totalCount1.push(val.TotalCount4);
		 totalCount1.push(val.TotalCount5);
		 totalCount1.push(val.TotalCount6);
		 if(sectionid1!= 2){
		 totalCount1.push(val.TotalCount7);
		 }
		 else
			 {
			 totalCount1.push(val.TotalCount9);
			 }
		// //alert(totalCount+"correctcount"+correctCount+"wrongcount"+wrongCount);
		 skipCount1.push(parseInt(parseInt(val.TotalCount1)-(parseInt(val.CorrectCount1)+parseInt(val.WrongCount1))));
		 skipCount1.push(parseInt(parseInt(val.TotalCount3)-(parseInt(val.CorrectCount3)+parseInt(val.WrongCount3))));
		 skipCount1.push(parseInt(parseInt(val.TotalCount4)-(parseInt(val.CorrectCount4)+parseInt(val.WrongCount4))));
		 
		var s= parseInt(parseInt(val.TotalCount5)-(parseInt(val.CorrectCount5)+parseInt(val.WrongCount5)));
		var s1=parseInt(parseInt(val.TotalCount6)-(parseInt(val.CorrectCount6)+parseInt(val.WrongCount6)));
		 
		// //alert("in s valueeeeeeeeeeee"+s+"in 5 value"+s1);
		 if(sectionid1!=2)
			 {
		 skipCount1.push(parseInt(s)+parseInt(s1));
		 skipCount1.push(parseInt(parseInt(val.TotalCount7)-(parseInt(val.CorrectCount7)+parseInt(val.WrongCount7))));
			 }
		 else
			 {
			 skipCount1.push(parseInt(s));
			 skipCount1.push(parseInt(s1));
			 skipCount1.push(parseInt(parseInt(val.TotalCount9)-(parseInt(val.CorrectCount9)+parseInt(val.WrongCount9))));
			 }
		 
			 
		 //skipCount.push(parseInt(parseInt(val.TotalCount6)-(parseInt(val.CorrectCount6)+parseInt(val.WrongCount6))));
	
		/*  if(sectionid == 2)
		 totalCount.push(val.TotalCount8); */
			 
		/* if(sectionid==2)
			{
			//alert("in skipppp");
			 skipCount.push(parseInt(parseInt(val.TotalCount8)-(parseInt(val.CorrectCount8)+parseInt(val.WrongCount8))));
			} */
		
		// //alert(skipCount);
		 //alert("Ending in vaerbal");
	});
	}
	////alert(correctCount+ " : "+wrongCount+"    :   "+JSON.parse(JSON.parse(userPace).timeDifference));
	//alert("before userPace1");
	if(JSON.parse(userPace1).isDataAvailable){
	
	$.each(JSON.parse(JSON.parse(userPace1).timeDifference), function(key, val) {	
	////alert("in user space graph");	
		var value = val.category1;
		var string = value.split("@");
		
		var value1 = val.category2;
		var string1 = value1.split("@");
		
		var value2 = val.category3;
		var string2 = value2.split("@");
		
		var value3 = val.category4;
		var string3 = value3.split("@");
		
		var value4 = val.category5;
		var string4 = value4.split("@");
		
		var value5 = val.category6;
		var string5 = value5.split("@");
		
		var value6 = val.category7;
		var string6 = value6.split("@");
			

	/* 	var value7 = val.category8;
		var string7 = value7.split("@"); */
		if(sectionid1==2)
		{	
			
		var value8 = val.category9;
		var string8 = value8.split("@");
		}
		
		
		if(sectionid1!=2)
			{
		//	//alert("userrrrrrrr in section id1");
			user_time1.push(string[0]);
			avg_time1.push(string[1]);
			
			//user_time.push(string1[0]);
		//	avg_time.push(string1[1]);
			
			user_time1.push(string2[0]);
			avg_time1.push(string2[1]);
			
			user_time1.push(string3[0]);
			avg_time1.push(string3[1]);
			
			var userTime =parseInt(string4[0])+parseInt(string5[0]);
			
			
			var avgTime = parseInt(string4[1])+parseInt(string5[1]);
			//alert(userTime+"  "+avgTime);
			user_time1.push(userTime);
			avg_time1.push(avgTime);				
			
			user_time1.push(string6[0]);
			avg_time1.push(string6[1]);
			
			}
		
		else if(sectionid1==2)
			{
		//	//alert("userrrrrrrr in section id 2");
			user_time1.push(string[0]);
			avg_time1.push(string[1]);
			
			//user_time.push(string1[0]);
		//	avg_time.push(string1[1]);
			
			user_time1.push(string2[0]);
			avg_time1.push(string2[1]);
			
			user_time1.push(string3[0]);
			avg_time1.push(string3[1]);
			
			user_time1.push(string4[0]);
			avg_time1.push(string4[1]);
			
			user_time1.push(string5[0]);
			avg_time1.push(string5[1]);
			
			user_time1.push(string8[0]);
			avg_time1.push(string8[1]);
			}
		
	});
	
	
	}
	//alert("before userPace");
	if(JSON.parse(userPace).isDataAvailable){
	$.each(JSON.parse(JSON.parse(userPace).timeDifference), function(key, val) {	
		////alert("in user space graph");	
		
		////alert("userpace 2    section   "+sectionid);
			var value = val.category1;
			var string = value.split("@");
			
			var value1 = val.category2;
			var string1 = value1.split("@");
			
			var value2 = val.category3;
			var string2 = value2.split("@");
			
			var value3 = val.category4;
			var string3 = value3.split("@");
			
			var value4 = val.category5;
			var string4 = value4.split("@");
			
			var value5 = val.category6;
			var string5 = value5.split("@");
			
			var value6 = val.category7;
			var string6 = value6.split("@");
				

		/* 	var value7 = val.category8;
			var string7 = value7.split("@"); */
			if(sectionid==2)
			{	
				
			var value8 = val.category9;
			var string8 = value8.split("@");
			}
			
			
			if(sectionid!=2)
				{
			//	//alert("userrrrrrrr in section id1");
				user_time.push(string[0]);
				avg_time.push(string[1]);
				
				//user_time.push(string1[0]);
			//	avg_time.push(string1[1]);
				
				user_time.push(string2[0]);
				avg_time.push(string2[1]);
				
				user_time.push(string3[0]);
				avg_time.push(string3[1]);
				
				var userTime =parseInt(string4[0])+parseInt(string5[0]);
				
				
				var avgTime = parseInt(string4[1])+parseInt(string5[1]);
				
				user_time.push(userTime);
				avg_time.push(avgTime);				
				
				user_time.push(string6[0]);
				avg_time.push(string6[1]);
				
				}
			
			else if(sectionid==2)
				{
			//	//alert("userrrrrrrr in section id 2");
				user_time.push(string[0]);
				avg_time.push(string[1]);
				
				//user_time.push(string1[0]);
			//	avg_time.push(string1[1]);
				
				user_time.push(string2[0]);
				avg_time.push(string2[1]);
				
				user_time.push(string3[0]);
				avg_time.push(string3[1]);
				
				user_time.push(string4[0]);
				avg_time.push(string4[1]);
				
				user_time.push(string5[0]);
				avg_time.push(string5[1]);
				
				user_time.push(string8[0]);
				avg_time.push(string8[1]);
				}
			
		});
	
	}
	 var correctData=0, wrongData=0, skippData=0,correctData1=0,wrongData1=0,skippData1=0;
	if(JSON.parse(quant).isDataAvailable){
	  correctData=JSON.parse('['+correctCount+']');
	 ////alert("correct coutn final"+correctCount);
	  wrongData=JSON.parse('['+wrongCount+']');
	 ////alert("Wrong coutn final"+wrongCount);
	  skippData=JSON.parse('['+skipCount+']');
	 ////alert("skipp coutn final"+skipCount);
	 /* // var totalData=JSON.parse('['+totalCount+']'); */
	}
	if(JSON.parse(verbal).isDataAvailable){
	   correctData1=JSON.parse('['+correctCount1+']');
	 // //alert("correct coutn is"+correctCount);
	  wrongData1=JSON.parse('['+wrongCount1+']');
  skippData1=JSON.parse('['+skipCount1+']');
	}
	
	////alert('['+question_title+']');
	////alert('['+user_time+']');
	////alert('['+avg_time+']');
	
	 var questionTitleData = JSON.parse(('['+question_title+']'));
	 var userTimeData=0,avgTimeData=0,userTimeData1=0,avgTimeData1=0;
	 if(JSON.parse(userPace).isDataAvailable){
	  userTimeData=(JSON.parse('['+user_time+']'));
	  avgTimeData=(JSON.parse('['+avg_time+']'));
	 }
	 if(JSON.parse(userPace1).isDataAvailable){
	  userTimeData1=(JSON.parse('['+user_time1+']'));
	  avgTimeData1=(JSON.parse('['+avg_time1+']'));
	 }
	 
	 
	 ////alert(' CorrectCount values is: '+questionTitleData+'\n\t after Modifications '+userTimeData+" : "+avgTimeData);
	var jsoncategories =  $('#categories').val() ;
	var jsoncategories1 =  $('#categories1').val() ;
	////alert(jsoncategories);
	/* var categories = JSON.parse(jsoncategories);
	//alert(categories); */
	////alert("Hai it is calling"+skippData+"    "+skipCount);
	$('#container').highcharts({
            chart: {
                type: 'column'
            },
			//colors: ['#f7a35c','#90ed7d'],
			colors: ['#736F6E','#e33244','#1aae88'],
            title: {
                text: 'Category Wise Performance'
            },
            xAxis: {
                categories: JSON.parse(jsoncategories)
            },
            yAxis: {
                min: 0,
                title: {
                    text: '% of Practice Session'
                }
            },
            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                shared: true
            },
            plotOptions: {
                column: {
                    stacking: 'percent',
					dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
 						   style: {
                            textShadow: '0 0 3px black, 0 0 3px black'
                        }
                    }
                }
            },
                series: [{
                	
                    name: 'Skipped',
                    data:  skippData//[0,0,0,0,0]
                },{
                name: 'Wrong',
                data: wrongData//[5,0,0,0,0]
            }, {
            	
                name: 'Correct',
                data: correctData//[2,0,0,0,0] //correctData
            } ]
        });
		
	$('#container2').highcharts({
        chart: {
            type: 'column'
        },
		//colors: ['#f7a35c','#90ed7d'],
		colors: ['#736F6E','#e33244','#1aae88'],
        title: {
            text: 'Category Wise Performance'
        },
        xAxis: {
            categories: JSON.parse(jsoncategories1)
        },
        yAxis: {
            min: 0,
            title: {
                text: '% of Practice Session'
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
            shared: true
        },
        plotOptions: {
            column: {
                stacking: 'percent',
				dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
						   style: {
                        textShadow: '0 0 3px black, 0 0 3px black'
                    }
                }
            }
        },
            series: [{
            	
                name: 'Skipped',
                data: skippData1
            },{
            name: 'Wrong',
            data: wrongData1
        }, {
        	
            name: 'Correct',
            data: correctData1
        } ]
    });
		
        $('#container3').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Time Spent Analysis'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: JSON.parse(jsoncategories)
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Time Taken'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} secs</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: 'User Pace',
                data: userTimeData
    
            }, {
                name: 'Others Pace',
                data: avgTimeData
    
            }]
        });
        
        
        $('#container4').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Time Spent Analysis'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: JSON.parse(jsoncategories1)
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Time Taken'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} secs</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: 'User Pace',
                data: userTimeData1
    
            }, {
                name: 'Others Pace',
                data: avgTimeData1
    
            }]
        });
        
        
        
    });
    
function getQuestionsBasedOnType(ques_id,type_id){
	////alert('in question :'+ques_id+"type id ::::"+type_id);
	////alert('');
	
	$('#retryForm').attr('action','Get-Retry-Questions-display.action?question_id='+ques_id+'&type_id='+type_id);
	$('#retryForm').submit();
	
	
	 
} 

function getFullSessionQuestion(ques_id,type_id,sectionid,testno){
	////alert('in session question'+ques_id);
	
	$('#quesid').val(ques_id);
	$('#typeid').val(type_id);
	$('#sectionno').val(sectionid);
	$('#retrytestno').val(testno);
	$('#retryForm').attr('action','Get-Full-Session-Retry-Questions.action');
	$('#retryForm').submit();
	
	
}

function reason(userid,qid,testid,reasonid)
{
	////alert('Reson Iam Userid:'+userid+' ,QID: '+qid+', Tesno: '+testid+' ,RID : '+reasonid); 
	var urlt = 'updateReason.action?userid='+userid+'&qid='+qid+'&testid='+testid+'&reasonid='+reasonid;
	////alert('URL : '+urlt);
	 $.ajax({
			type : 'POST',
			url : urlt,
						
		    success :function(result){
		    //alert("Ok"+result);
		    },
			error : function(e){	
			//alert('Error'+e);
			}
		    
		}); 
	
}

		</script>
		
 
		
 </head>
 <body id="body1">
       <input type="hidden" id="pdfid" name="fullResultsBO.pdfValue" value=""/>
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
				
			<s:hidden id="categories" value="%{#request.categories}"/>
			<s:hidden id="categories1" value="%{#request.categories1}"/>
			<s:hidden id="datacount" value="%{#request.datacount}"/>
			<s:hidden id="datacount1" value="%{#request.datacount1}"/>
			<s:hidden id="sectionid" value="%{#request.sectionid}"/>
			<s:hidden id="sectionid1" value="%{#request.sectionid1}"/>
			<s:hidden id="userPace" value="%{#request.userPace}"/>
			<s:hidden id="userPace1" value="%{#request.userPace1}"/>	
			<s:hidden id="stack_correct" value="%{#request.stack_correct}"/>	
			<s:hidden id="stack_wrong" value="%{#request.stack_wrong}"/>
			<s:hidden id="catagory_select" value="%{#request.catagory_select}"/>		
			
			 
				
			 
                <!---Start of main row--->
                <div class="row main-box">
                
                <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                                          
				        <div class="panel-heading no-border bg-success lt text-center">
                        <div class="h5 padder-v text-white">Qunat Score</div>  
                          <span class="main_text text-white">
                          <%-- <s:property value="fullResultsBO.avg_percentcorrect"/>% --%>
                          <%= request.getAttribute("totalQuantScaledScore") %>
                          </span>
                          <div class="h5 padder-v text-white">(<%= request.getAttribute("quantPercentile") %>% of Correct)</div>      
                        </div>                                                
                      </div>
                    </div><!--End Your percent correct-->
                   
                    <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                   		<div class="panel-heading no-border bg-danger lt text-center">
                        <div class="h5 padder-v text-white">Total Score</div>  
                          <span class="main_text text-white">
                          <%--  <s:property value="fullResultsBO.avg_userpace"/> --%>
                          <%= request.getAttribute("totalScore") %>
                          </span>
                          <div class="h5 padder-v text-white">(out of 340)</div>   
                        </div>                                                
                      </div>
                    </div><!--End Your AVG PACE-->
                   
            	    <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                   		<div class="panel-heading no-border bg-dark lt text-center">
                        <div class="h5 padder-v text-white">Verbal Score</div>  
                          <span class="main_text text-white">
                           <%= request.getAttribute("totalVerbalScaledScore") %>  
                          </span>
                          <div class="h5 padder-v text-white">(<%= request.getAttribute("verbalPercentile") %>% of Correct)</div>         
                        </div>
                        </div>
                    </div><!--End Others' Average Pace -->
                
                </div>
                <!--End of main Boxes row-->
                
             
             
             	<!---------->
             	<div class="row">
                <div class="col-lg-6">
                  <section class="panel panel-default">
                    <header class="panel-heading">Stacked</header>
                    
                    <div class="panel-body text-center">
                    
                    				
                                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                   
                    
                    
                    </div>  
                      
                      
                      
                  </section>
                </div>
              
                <div class="col-lg-6" >
                  <section class="panel panel-default">
                    <header class="panel-heading">Stacked</header>
                    
                    <div class="panel-body text-center">
                    
                    				
                                 <div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                   
                    
                    
                    </div>  
                      
                      
                      
                  </section>
                </div>
              </div>
              
              
              <div class="row">
                <div class="col-lg-6">
                  <section class="panel panel-default">
                    <header class="panel-heading">Stacked</header>
                    
                    <div class="panel-body text-center">
                    
                    				
                                <div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                   
                    
                    
                    </div>  
                      
                      
                      
                  </section>
                </div>
              
                <div class="col-lg-6" >
                  <section class="panel panel-default">
                    <header class="panel-heading">Stacked</header>
                    
                    <div class="panel-body text-center">
                    
                    				
                               <div id="container4" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                   
                    
                    
                    </div>  
                      
                      
                      
                  </section>
                </div>
              </div>
             
             
             
                 
             	<!--- NEW CHARTS ************* START------->
             	<div class="row">
                <div class="col-lg-6">
                  <section class="panel panel-default">
                   
                    
                    <div class="panel-body text-center">
                                <div id="container-fulllengthTestBobblechart" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                    </div>  
                      
                 </section>
                </div>
              
                <div class="col-lg-6" >
                  <section class="panel panel-default">
                    
                    
                    <div class="panel-body text-center">
                            <div id="container-pie" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                    </div>  

                  </section>
                </div>
              </div>
             
             <!--- NEW CHARTS ************* END------->
       
		 <!--Data table Start -->   		
            	<div id="demo">

                            <section class="panel panel-default">
                                <header class="panel-heading">
                                  DataTables 
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" ></i> 
                                </header>
                               <div class="table-responsive">
                                <table id="expandable-table" class="table table-striped display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>Result</th>
                                                <th width="20%">Category</th>
                                                <th>Topic</th>
                                                <th>Type</th> 																		
												<th>Date</th>
												<th width="20%">Reason      .</th>	
												<th>Retry</th>											
												
 
                                            </tr>
                                        </thead>
                                 
                                        <tfoot>
                                            <tr>
                                                <th></th>
                                                <th>Result</th>
                                                <th width="20%">Category</th>
                                                <th></th>
                                                <th></th>
												<th></th>											
												<th></th>
												<th></th>												
 
                                            </tr>
                                        </tfoot>
                                    </table>
						</div>
						</section>	
                        </div>
          
				<!--Data table End -->            </section>
           
           <form id="retryForm" name="retryForm">
        
           
           <s:hidden id="quesid" name="ques_id" value=""/>
           <s:hidden id="typeid" name="type_id" value=""/>
           <s:hidden id="testno" name="retrytestno" value="%{#request.test_no}"/>
           <s:hidden id="sectionno" name="sectionnumber"/>          
           
           
         <!--  **********************  Score Pediction STtart******************************** -->
          
           <s:hidden id="quantScore"  value="%{#request.quantScore}"/>
            <s:hidden id="quantPercentile"  value="%{#request.test_no}"/>
            <s:hidden id="verbalScore"  value="%{#request.verbalScore}"/>
            <s:hidden id="verbalPercentile"  value="%{#request.verbalPercentile}"/>
            <s:hidden id="totalScore"  value="%{#request.totalScore}"/>
            <s:hidden id="testno"  value="%{#request.test_no}"/>
           
                                
      <!--     **********************  Score Pediction END********************************                 -->        
                                
           <s:hidden id="resultJson"	value="%{#request.result}"></s:hidden>
           
           </form> 
            
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
   
  <script src="assets/js/filter-dataTable/jquery.dataTables.js"></script>

<script src="assets/js/filter-dataTable/jquery.dataTables.columnFilter.js"></script>
 

		
 

   </body>
   </html>

  
 
  
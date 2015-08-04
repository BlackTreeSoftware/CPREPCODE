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
		
	
		
$(function () {
	
	var datacount =$('#datacount').val();
	var correctCount=[];
	var wrongCount=[];
	var skipCount=[];
	var totalCount=[];

	
	
	var userPace =$('#userPace').val();
	var question_title=[];
	var user_time=[];
	var avg_time=[];
	var sectionid=$('#sectionid').val();

//	alert("1st alert : "+userPace+"     :    "+datacount);
	
	/* $.each(JSON.parse(JSON.parse(datacount).percentageCount), function(key, val) {	
	
		 correctCount.push(val.CorrectCount1);
	//	 correctCount.push(val.CorrectCount2);
		 correctCount.push(val.CorrectCount3);
		 correctCount.push(val.CorrectCount4);
		 correctCount.push(val.CorrectCount5);
		 correctCount.push(val.CorrectCount6);
		 correctCount.push(val.CorrectCount7);
		 
		 if(sectionid == 2){
		 correctCount.push(val.CorrectCount8);
		 }
		 
		 
		 wrongCount.push(val.WrongCount1);
		 wrongCount.push(val.WrongCount2);
		 wrongCount.push(val.WrongCount3);
		 wrongCount.push(val.WrongCount4);
		 wrongCount.push(val.WrongCount5);
		 wrongCount.push(val.WrongCount6);
		 wrongCount.push(val.WrongCount7);
		 if(sectionid == 2)
		 wrongCount.push(val.WrongCount8);
		// wrongCount.push(val.WrongCount8);
		
		 totalCount.push(val.TotalCount1);
		 totalCount.push(val.TotalCount2);
		 totalCount.push(val.TotalCount3);
		 totalCount.push(val.TotalCount4);
		 totalCount.push(val.TotalCount5);
		 totalCount.push(val.TotalCount6);
		 totalCount.push(val.TotalCount7);
		 if(sectionid == 2)
		 totalCount.push(val.TotalCount8);
	
		 
		
		
	}); */
	
	
	$.each(JSON.parse(JSON.parse(datacount).percentageCount), function(key, val) {	
		
		 correctCount.push(val.CorrectCount1);
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
		// correctCount.push(val.CorrectCount6);
		
		/*  if(sectionid == 2){
		 correctCount.push(val.CorrectCount8);
		 } */
		 
		 
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
		
		 
		// alert("in corect count calculation"+parseInt(val.TotalCount1)+"toal "+parseInt(parseInt(val.TotalCount1)-(parseInt(val.CorrectCount1)+parseInt(val.WrongCount1))));
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
		// alert(totalCount+"correctcount"+correctCount+"wrongcount"+wrongCount);
		 skipCount.push(parseInt(parseInt(val.TotalCount1)-(parseInt(val.CorrectCount1)+parseInt(val.WrongCount1))));
		 skipCount.push(parseInt(parseInt(val.TotalCount3)-(parseInt(val.CorrectCount3)+parseInt(val.WrongCount3))));
		 skipCount.push(parseInt(parseInt(val.TotalCount4)-(parseInt(val.CorrectCount4)+parseInt(val.WrongCount4))));
		 
		var s= parseInt(parseInt(val.TotalCount5)-(parseInt(val.CorrectCount5)+parseInt(val.WrongCount5)));
		var s1=parseInt(parseInt(val.TotalCount6)-(parseInt(val.CorrectCount6)+parseInt(val.WrongCount6)));
		 
		// alert("in s valueeeeeeeeeeee"+s+"in 5 value"+s1);
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
			alert("in skipppp");
			 skipCount.push(parseInt(parseInt(val.TotalCount8)-(parseInt(val.CorrectCount8)+parseInt(val.WrongCount8))));
			} */
		
		// alert(skipCount);
		
	});
	
	//alert(correctCount+ " : "+wrongCount+"    :   "+JSON.parse(JSON.parse(userPace).timeDifference));
	$.each(JSON.parse(JSON.parse(userPace).timeDifference), function(key, val) {	
	//alert("in user space graph");	
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
		//	alert("userrrrrrrr in section id1");
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
		//	alert("userrrrrrrr in section id 2");
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
	

	 var correctData=JSON.parse('['+correctCount+']');
	 // alert("correct coutn is"+correctCount);
	 var wrongData=JSON.parse('['+wrongCount+']');
	 var skippData=JSON.parse('['+skipCount+']');
	 /* // var totalData=JSON.parse('['+totalCount+']'); */
	
	
	//alert('['+question_title+']');
	//alert('['+user_time+']');
	//alert('['+avg_time+']');
	
	 var questionTitleData = JSON.parse(('['+question_title+']'));
	 var userTimeData=(JSON.parse('['+user_time+']'));
	 var avgTimeData=(JSON.parse('['+avg_time+']')); 
	 
	 
	 //alert(' CorrectCount values is: '+questionTitleData+'\n\t after Modifications '+userTimeData+" : "+avgTimeData);
	var jsoncategories =  $('#categories').val() ;	 
	//alert(jsoncategories);
	/* var categories = JSON.parse(jsoncategories);
	alert(categories); */
	$('#container').highcharts({
            chart: {
                type: 'column'
            },
			//colors: ['#f7a35c','#90ed7d'],
			colors: ['#e33244','#1aae88','#736F6E'],
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
                name: 'Wrong',
                data: wrongData
            }, {
            	
                name: 'Correct',
                data: correctData
            }, {
            	
                name: 'Skipped',
                data: skippData
            }]
        });
		
		
		
        $('#container1').highcharts({
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
    });
    
function getQuestionsBasedOnType(ques_id,type_id){
	//alert('in question :'+ques_id+"type id ::::"+type_id);
	//alert('');
	
	$('#retryForm').attr('action','Get-Retry-Questions-display.action?question_id='+ques_id+'&type_id='+type_id);
	$('#retryForm').submit();
	
	
	 
} 

function getPracticeSessionQuestion(ques_id,type_id,sectionid,testno){
	//alert('in session question'+ques_id);
	
	$('#quesid').val(ques_id);
	$('#typeid').val(type_id);
	$('#sectionno').val(sectionid);
	$('#retrytestno').val(testno);
	$('#retryForm').attr('action','Get-Practice-Session-Retry-Questions.action');
	$('#retryForm').submit();
	
	
}

		</script>
		
 
       
  
  <script type="text/javascript">
	
	
	$(function () {
		
	var st_crt = $("#stack_correct").val();	
	var st_wrg = $("#stack_wrong").val(); 
	
	  /* var st_crt = "[[74.0, 65.6], [75.3, 71.8], [93.5, 80.7], [16.5, 2.6], [17.2, 8.8], [11.5, 4.8], [14.0, 6.4], [84.5, 8.4], [15.0, 42.0], [84.0, 11.6]]  ";
	var st_wrg = "[[74.0, 65.6], [75.3, 71.8], [93.5, 80.7], [16.5, 2.6], [17.2, 8.8], [11.5, 4.8], [14.0, 6.4], [84.5, 8.4], [15.0, 42.0], [84.0, 11.6]]  "; */  
	//alert('Correct : '+st_crt+'  Wrong : '+st_wrg);
	
	$('#container-stack').highcharts({
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
                text: ' Average time'
                	
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
            name: 'incorrect',
            color: 'rgba(223, 83, 83, .5)',
            data:  JSON.parse(st_wrg)

        }, {
            name: 'correct',
            color: 'rgba(26, 174, 136, .5)',
            data: JSON.parse(st_crt)

        }
                
                ]
    });
	
	
	
			
			
			
			////////////////////////////////////////////
			
		
	
	
	
	
	
});

	
	 function reason(userid,qid,testid,reasonid)
	 {
		alert('Reson Iam Userid:'+userid+' ,QID: '+qid+', Tesno: '+testid+' ,RID : '+reasonid); 
		var urlt = 'updatePraceticeReason.action?userid='+userid+'&qid='+qid+'&testid='+testid+'&reasonid='+reasonid;
		alert('URL : '+urlt);
		 $.ajax({
				type : 'POST',
				url : urlt,
							
			    success :function(result){
			    alert("Ok"+result);
			    },
				error : function(e){	
				alert('Error'+e);
				}
			    
			}); 
		
	 }
  </script>
		
 </head>
 <body id="body1">
       <input type="hidden" id="pdfid" name="resultsBO.pdfValue" value=""/>
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
				
			<s:hidden id="categories" value="%{#request.categories}"/>
			<s:hidden id="datacount" value="%{#request.datacount}"/>
			<s:hidden id="sectionid" value="%{#request.sectionid}"/>
			<s:hidden id="userPace" value="%{#request.userPace}"/>	
			<s:hidden id="stack_correct" value="%{#request.stack_correct}"/>	
			<s:hidden id="stack_wrong" value="%{#request.stack_wrong}"/>	
			
			 
				
			 
                <!---Start of main row--->
                <div class="row main-box">
                
                <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                                          
				        <div class="panel-heading no-border bg-success lt text-center">
                        <div class="h5 padder-v text-white">Your Percent Correct</div>  
                          <span class="main_text text-white">
                          <s:property value="resultsBO.avg_percentcorrect"/>%
                          </span>
                          <div class="h5 padder-v text-white"><s:property value="resultsBO.correct"/> / <s:property value="resultsBO.total_qsns"/></div>      
                        </div>                                                
                      </div>
                    </div><!--End Your percent correct-->
                   
                    <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                   		<div class="panel-heading no-border bg-danger lt text-center">
                        <div class="h5 padder-v text-white">Your Average Pace</div>  
                          <span class="main_text text-white">
                           <s:property value="resultsBO.avg_userpace"/>
                          </span>
                          <div class="h5 padder-v text-white"><s:property value="resultsBO.total_userpace"/> (total)</div>   
                        </div>                                                
                      </div>
                    </div><!--End Your AVG PACE-->
                   
            	    <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                   		<div class="panel-heading no-border bg-dark lt text-center">
                        <div class="h5 padder-v text-white">Others' Average Pace</div>  
                          <span class="main_text text-white">
                           <s:property value="resultsBO.avg_otherspace"/>
                          </span>
                          <div class="h5 padder-v text-white"><s:property value="resultsBO.total_otherspace"/>(total)</div>         
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
                    
                    				
                               <div id="container1" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                   
                    
                    
                    </div>  
                      
                      
                      
                  </section>
                </div>
              </div>
             
             
             
                 
             	<!--- NEW CHARTS ************* START------->
             	<div class="row">
                <div class="col-lg-6">
                  <section class="panel panel-default">
                   
                    
                    <div class="panel-body text-center">
                                <div id="container-stack" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
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
                                                <th>Question Title</th>
                                                <th>Topic</th>
                                                <th>Type</th>
												<th>Difficulty</th>
												<th>Guessed</th>
												<th>Flag</th>
												<th>Date</th>												
												<th width="15%">Reason</th>
												<th>Retry</th>
                                            </tr>
                                        </thead>
                                 
                                        <tfoot>
                                            <tr>
                                                <th></th>
                                                <th>Result</th>
                                                <th>Question Title</th>
                                                <th>Topic</th>
                                                <th>Type</th>
												<th></th>
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
                                
                                
                                
           <s:hidden id="resultJson"	value="%{#request.result}"></s:hidden>
           
           </form> 
            
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
   
  <script src="assets/js/filter-dataTable/jquery.dataTables.js"></script>

<script src="assets/js/filter-dataTable/jquery.dataTables.columnFilter.js"></script>
<script type="text/javascript">

	$(document).ready(function(){ 
		
		//alert("DOC START");
		 var foo = '[' + $('#resultJson').val() + ']';
		// alert("FOO : "+foo);
		 var values = JSON.parse(foo);
		 
		// alert(values);
		
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
            { "data": "difficulty" },
            { "data": "guessed" },
            { "data": "flag" },
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
	
	
	
	//Drill chart is Start::::::::::::::::::::::::::::::::::
		
		
		
		  var global;
	
	  var formData = $('#body1').serialize();
	  
		 $.ajax({
			type : 'POST',
			url : 'practiseSessionResultPieChartJsonData.action', 	
			data : formData,
			dataType:'json',
			success :  function deleteSuccess(data, status) {
				     alert("success data is calling ::::::::::::::::::::::::::::::");
				  global=data;
				  
				  
				 
				 practiseSessionPieChart();
			  
				  
				},
			    error : function deleteFailed(errorIs) {
			    alert("Error data calling:::::::::::::::::::::::::::::::::::::::::::::::::");
				//alert(' Failed ' + errorIs.responseText);
			}

		}); 
		
		
		function practiseSessionPieChart(){
			 
			 alert("Practise Session Pie cahrt is fucntion is calling :::::::::::::::::::::::::::::::::");
	        var colors = Highcharts.getOptions().colors,
	            categories = ['Correct', 'Incorrect', /* 'Guessed ', 'Flagged', */'Skipped'],
	            name = 'Browser brands',
	            data = [{
	                    y: JSON.parse(global.toatal_correct_percentage),
	                    color: colors[2],
	                    drilldown: {
	                        name: 'toatal_correct',
	                        categories: ['Confident_Correct', 'Guess_Correct', 'Flag_Correct'],
	                       data:JSON.parse('['+global.confident_correct_percentage+', '+global.guess_correct_percentage+','+global.flag_correct_percentage+']'),
	                        color: colors[2]
	                    }
	                }, {
	                    y: JSON.parse(global.toatal_Incorrect_percentage),
	                    color: colors[3],
	                    drilldown: {
	                        name: 'total_incorrect',
	                        categories: ['Confident_Incorrect', 'Guess_Incorrect', 'Flag_Incorrect'],
	                        data: JSON.parse('['+global.confident_incorrect_percentage+', '+global.guess_incorrect_percentage+', '+global.flag_incorrect_percentage+']'),
	                        color: colors[3]
	                    }
	                },{
	                    y: JSON.parse(global.total_skipped_percentage),
	                    color: colors[4],
	                    drilldown: {
	                        name: 'total_incorrect',
	                        categories: ['Skipped_Questions'],
	                        data: JSON.parse('['+global.total_skipped_percentage+']'),
	                        color: colors[4]
	                    }
	                }/* , {
	                    y: global.total_guessed_percentage,
	                    color: colors[2],
	                    drilldown: {
	                        name: 'guess',
	                      categories: ['Total_Guessed'],
	                        data: [0.12],
	                        color: colors[2]
	                    } 
	                }, {
	                    y: global.total_flagged_percentage,
	                    color: colors[3],
	                     drilldown: {
	                        name: 'flagged',
	                        categories: ['Total_Flagged'],
	                        data: [4.55],
	                        color: colors[3]
	                    } 
	                },*/  /*  {
	                    y: global.total_skipped_percentage,
	                    color: colors[4],
	                     drilldown: {
	                        name: 'skipped',
	                       categories: ['Total_Skipped'],
	                        data: JSON.parse('['+global.total_skipped_percentage+']'), 
	                        color: colors[4]
	                    }  
	                } */  ];
	    
	    
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
	                text: 'Browser market share, April, 2011'
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
	                name: 'Versions',
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
		
		
		
		
	//Drill Chart is End::::::::::::::::::::::::::::::::::::	
	
	
	
	
	
	});
	
	
	
	
	function format ( d ) {
		var his = d.hisdiv.replace(/@/g, '\"');
		var result = "<i class='fa fa-2x fa-check-circle text-success'></i> <span class='h3 text-success p-l-10'>"+d.result+"</span>";
		if(d.result=="INCORRECT") result = "<i class='fa fa-2x fa-times-circle text-danger'></i> <span class='h3 text-danger p-l-10'>"+d.result+"</span>";
	//	alert(his);
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
		
		$(document).ready(function(){
			$( "#nav" ).prop( "disabled", true );
		
		$('#expandable-table').dataTable().columnFilter({
         aoColumns: [ null,{ type: "select", values: [ 'Correct', 'Wrong'], selected: ''  },{ type: "text" },{ type: "text" },{ type: "number" },{ type: "text" }, { type: "select", values: [ 'CONFIDENT', 'GUESS'], selected: ''  },





             { type: "select", values: [ 'UNFLAG', 'FLAG'], selected: ''  },null
		   ]
	 });
		
		
		
		




}); 


</script>

   </body>
   </html>

  
 
  
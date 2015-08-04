  <%@taglib prefix="s" uri="/struts-tags" %>
 
 
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
		
		
        <%-- <script src="assets/js/filter-dataTable/jquery.dataTables.js" type="text/javascript"></script>

        <script src="assets/js/filter-dataTable/jquery-ui.js" type="text/javascript"></script>

        <script src="assets/js/filter-dataTable/jquery.dataTables.columnFilter.js" type="text/javascript"></script> --%>
        
         <%-- <script src="assets/js/filter-dataTable/data-t-expandable.js" type="text/javascript"></script> --%>  

		<%-- <script type="text/javascript">
		$(document).ready(function(){

		     $('#expandable-table').dataTable()
				  .columnFilter({
					aoColumns: [ null,{ type: "select", values: [ 'Correct', 'Wrong'], selected: ''  },{ type: "text" },{ type: "text" },{ type: "number" },null,null,null,
		             { type: "select", values: [ 'Marked', 'UnFlagged'], selected: ''  },null
						]

				});
		     
		     var table = $('#expandable-table').DataTable( {
		    	 "data": [
		    	          {
		    	            "name": "Tiger Nixon",
		    	            "position": "System Architect",
		    	            "salary": "$320,800",
		    	            "start_date": "2011/04/25",
		    	            "office": "Edinburgh",
		    	            "extn": "5421"
		    	          }],
		         "columns": [
		             {
		                 "class":          'details-control',
		                 "orderable":      false,
		                 "data":           null,
		                 "defaultContent": ''
		             },
		             { "data": "name" },
		             { "data": "position" },
		             { "data": "office" },
		             { "data": "salary" }
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
		 	
		 	$('#expandable-table_length,#expandable-table_filter').hide();
		});
		
		function format ( d ) {
		    
					
					return '<div class="row" style="padding:12px;>'+
				    '<div class="col-xs-12">'+
					'<div class="col-xs-12"><span class="text-dkr"><h4 class="m-t-0">Quantitative / GRE Numeric Entry / Difficulty : Medium</h4></span> </div>'+
					'<div class="col-xs-12"><span class="font-15 text-dkr">You have answered this question</span> </div>'+
		            '<div class="col-lg-8 col-sm-8 col-xs-12">'+
					'<hr><div class="row p-b-10"> <div class="col-xs-6"><i class="fa fa-2x fa-check-circle text-success"></i> <span class="h3 text-success p-l-10">CORRECT</span> <p class="font-15 m-t-5">You are among <span class="font-bold text-success">14%</span> people who got this question correct</p></div>  <div class="col-xs-6"><i class="fa fa-2x fa-clock-o text-primary"></i> <span class="h3 text-primary p-l-10">01 <span class="h5">min</span> 52 <span class="h5">sec</span></span> <p class="font-15 m-t-5">Your pace is better than others pace: <span class="font-bold text-primary">01 min 52 sec</span></p></div> </div> '+
					'<hr class="hr-less">'+
					' <div class="row p-t-10"> <div class="col-xs-12"> <span class="col-xs-4 font-15"> <span class="font-bold text-success p-r-10">41%</span> Guessed</span>'+ '<span class="col-xs-4 font-15 "> <span class="font-bold text-success p-l-10 p-r-10">59%</span> Guessed Incorrectly</span>'+'<span class="col-xs-4 font-15"> <span class="font-bold text-success p-l-10 p-r-10">13%</span> Flagged</span></div></div>'+
					'</div>'+
					
					'<div class="col-lg-4 col-sm-4 col-xs-12 text-primary">'+ 
					'<hr> <p class="h4 m-t-15">Question attempt history</p><hr class="hr-less"><span class="font-15"><i class="fa fa-check-circle text-success"></i><span class="p-l-10 p-r-10">Correct</span><i class="fa fa-arrow-right"></i> <i class="fa fa-clock-circle"></i> <span class="p-l-10 p-r-10">01 min 52 sec (Jul 23<sup>rd</sup>, 23:11:20)</span></span>'+
					'<br><span class="font-15"><i class="fa fa-times-circle text-danger"></i><span class="p-l-10 p-r-10 text-danger"> Wrong</span><i class="fa fa-arrow-right"></i> <i class="fa fa-clock-circle"></i> <span class="p-l-10 p-r-10">01 min 52 sec (Jul 23<sup>rd</sup>, 23:11:20)</span></span>'           					                 	
					'</div>'
					;
				}

		</script> --%>
		<script type="text/javascript">
		
	
		
$(function () {
	
	var datacount =$('#datacount').val();
	var correctCount=[];
	var wrongCount=[];
	var totalCount=[];

	
	
	var userPace =$('#userPace').val();
	var question_title=[];
	var user_time=[];
	var avg_time=[];
	var sectionid=$('#sectionid').val();

//	alert("1st alert : "+userPace+"     :    "+datacount);
	
	$.each(JSON.parse(JSON.parse(datacount).percentageCount), function(key, val) {	
	
		 correctCount.push(val.CorrectCount1);
		 correctCount.push(val.CorrectCount2);
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
	
		 
		
		
	});
	
	//alert(correctCount+ " : "+wrongCount+"    :   "+JSON.parse(JSON.parse(userPace).timeDifference));
	$.each(JSON.parse(JSON.parse(userPace).timeDifference), function(key, val) {	
		
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
		
		
		
		
		
		//alert(string[0]+" : "+string[1]);
		user_time.push(string[0]);
		avg_time.push(string[1]);
		
		user_time.push(string1[0]);
		avg_time.push(string1[1]);
		
		user_time.push(string2[0]);
		avg_time.push(string2[1]);
		
		user_time.push(string3[0]);
		avg_time.push(string3[1]);
		
		user_time.push(string4[0]);
		avg_time.push(string4[1]);
		
		user_time.push(string5[0]);
		avg_time.push(string5[1]);
		
		user_time.push(string6[0]);
		avg_time.push(string6[1]);
		
		
		if(sectionid == 2){
		var value7 = val.category8;
		var string7 = value7.split("@");
		user_time.push(string7[0]);
		avg_time.push(string7[1]);
		}
	
		
		
	});
	

	 var correctData=JSON.parse('['+correctCount+']');
	 var wrongData=JSON.parse('['+wrongCount+']');
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
			colors: ['#f7a35c','#90ed7d'],
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
	
	
	/* $.ajax({ 
		type : "POST",
		url  :"Get-Retry-Questions-display.action",	
		data :{
			question_id : ques_id,
			type_id : type_id
		},
		success : datasuccess,
		error:dataerror
	});
	function datasuccess(htmlScript,status){ 
		$('#content').html(htmlScript);
	}
	function dataerror(error){
		alert(error);
	} */
} 

function getPracticeSessionQuestion(ques_id,type_id){
	//alert('in session question'+ques_id);
	
	$('#quesid').val(ques_id);
	$('#typeid').val(type_id);
	
	$('#retryForm').attr('action','Get-Practice-Session-Retry-Questions.action');
	$('#retryForm').submit();
	
	
}


		</script>
</head>
	<body>	
		    
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
             
       
                
                
             	<!--Data table Start -->   		
            <%-- 	<div id="demo">

                            <section class="panel panel-default">
                                <header class="panel-heading">
                                  DataTables 
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>
                               <div class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
					  <th>Q.No</th>                    
                      <th>Result</th>
					  <th>Question Title</th>
					  <th>Section</th>
					  <th>Subject</th>
					  <th>Difficulty</th>
					  <th>Your Pace</th>
					  <th>Other's Pace</th>					  
					  <th>Flaging</th>   
					   <th>Retry Action</th>           
		</tr>
	</thead>
	<tfoot>
		<tr>

					  <th></th>                    
                      <th>Result</th>
					  <th>Question Title</th>
					  <th>Section</th>
					  <th>Subject</th>
					  <th></th>
					  <th></th>
					  <th></th>					  
					  <th>Flag</th> 
					  <th></th>      
		</tr>
	</tfoot>
	<tbody>
		    <%=request.getAttribute("result").toString() %>
		
		
	</tbody>
</table>
</div>
		</section>	</div> --%>
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
           <s:hidden id="testno" name="test_no" value="%{#request.test_no}"/>
           <s:hidden id="resultJson"	value="%{#request.result}"></s:hidden>
           <s:hidden id="categories" value="%{#request.categories}"/>
			<s:hidden id="datacount" value="%{#request.datacount}"/>
			<s:hidden id="sectionid" value="%{#request.sectionid}"/>
			<s:hidden id="userPace" value="%{#request.userPace}"/>	
           
           </form> 
            
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
      
  <script src="assets/js/filter-dataTable/jquery.dataTables.js"></script>

<script src="assets/js/filter-dataTable/jquery.dataTables.columnFilter.js"></script>
<script type="text/javascript">

	$(document).ready(function(){ 
		
		alert("DOC START");
		 var foo = '[' + $('#resultJson').val() + ']';
		 alert("FOO : "+foo);
		 var values = JSON.parse(foo);
		 
		 alert(values);
		
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
	
	$('#expandable-table_length,#expandable-table_filter').hide();
	});
	
	
	function format ( d ) {
		var his = d.hisdiv.replace(/@/g, '\"');
		alert(his);
			return '<div class="row" style="padding:12px;>'+
		    '<div class="col-xs-12">'+
			'<div class="col-xs-12"><span class="text-dkr"><h4 class="m-t-0">Quantitative / GRE Numeric Entry / Difficulty : '+d.difficulty+'</h4></span> </div>'+
			'<div class="col-xs-12"><span class="font-15 text-dkr">You have answered this question</span> </div>'+
            '<div class="col-lg-8 col-sm-8 col-xs-12">'+
			'<hr><div class="row p-b-10"> <div class="col-xs-6"><i class="fa fa-2x fa-check-circle text-success"></i> <span class="h3 text-success p-l-10">'+d.result+'</span> '+
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
		
		
		
		$('#expandable-table').dataTable().columnFilter({
aoColumns: [{type: "select",values: [ 'B', 'B', 'C' ]},{type: "select",values: [ 'A', 'B', 'C' ]},{type: "select",values: [ 'A', 'B', 'C' ]},{type: "select",values: [ 'A', 'B', 'C' ]},{type: "select",values: [ 'A', 'B', 'C' ]}]
 
	 });
		});
</script>

				<!--Data table End -->
</body>
</html>
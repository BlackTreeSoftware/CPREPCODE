<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/practice.css" type="text/css" />  
  
  <!---Progress bar with text--->
  <link rel="stylesheet" type="text/css" href="assets/plug-ins/review-progress-bar/prettify/prettify.min.css">
  <link rel="stylesheet" type="text/css" href="assets/plug-ins/review-progress-bar/bootstrap-progressbar-3.1.0.css">  
  
  <!--Customised Css for review module-->
  <link rel="stylesheet" href="assets/css/review.css" type="text/css" /> 
  
   
 
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/css/skill_data/skilldata.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" /> 
<link rel="stylesheet" href="assets/css/skill_data/jquery.dataTables.css" type="text/css" />


<script src="assets/js/jquery.min.js"></script>


<script src="assets/js/jquery.dataTables.js"></script>
 <%-- <script src="assets/plug-ins/skill-data-table/skill-data-table.js"></script> --%>
<script src="assets/plug-ins/skill-data-table/jquery.dataTables.columnFilter.js"></script>

<script type='text/javascript' src="assets/plug-ins/review-progress-bar/prettify/prettify.min.js"></script>
<script type='text/javascript' src="assets/plug-ins/review-progress-bar/boots/bootstrap-3.1.0.min.js"></script>
<script type='text/javascript' src="assets/plug-ins/review-progress-bar/bootstrap-progressbar.js"></script>


</head>
<body>
  <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
				<s:hidden id="skillDataID" value="%{#request.skillData}"/>
				<s:hidden id="quantskillDataID" value="%{#request.quantSkillData}"/>
				<s:hidden id="verbalskillDataID" value="%{#request.verbalSkillData}"/>
				<s:hidden id="verbalSkills" value="%{#request.verbalSkills}"/>
				<s:hidden id="quantSkills" value="%{#request.quantSkills}"/>
				
                <!---Start of main row--->
                <%-- <div class="row main-box">
                
                <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                                          
				        <div class="panel-heading no-border bg-success lt text-center">
                        <div class="h5 padder-v text-white">Your Percent Correct</div>  
                          <span class="main_text text-white">
                           200%
                          </span>
                          <div class="h5 padder-v text-white">10 / 20</div>      
                        </div>                                                
                      </div>
                    </div><!--End Your percent correct-->
                   
                    <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                   		<div class="panel-heading no-border bg-danger lt text-center">
                        <div class="h5 padder-v text-white">Your Average Pace</div>  
                          <span class="main_text text-white">
                           0:1:54
                          </span>
                          <div class="h5 padder-v text-white">100:00:04 (total)</div>   
                        </div>                                                
                      </div>
                    </div><!--End Your AVG PACE-->
                   
            	    <div class="col-md-4 col-sm-4">
                      <div class="panel b-a">  
                   		<div class="panel-heading no-border bg-dark lt text-center">
                        <div class="h5 padder-v text-white">Others' Average Pace</div>  
                          <span class="main_text text-white">
                           0:54:42
                          </span>
                          <div class="h5 padder-v text-white">100:00:04 (total)</div>         
                        </div>
                        </div>
                    </div><!--End Others' Average Pace -->
                
                </div> --%>
                <!--End of main Boxes row-->
             
             	<!---------->
             	
            <!--Data table Start -->   		
            	<div id="demo">
            	       
                            <section class="panel panel-default">
                                <header class="panel-heading">
                                  Quantitative Section
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>
                               <div class="table-responsive">
                                <table id="expandable-table" class="table table-striped display expandable-table" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                              
                                                <th>Skill</th>
                                                <th class="questionsanswered">Questions Answered</th>
                                                <th class="questionsanswered">Avg Time</th>                                                
                                                <th class="accuracy">Accuracy</th>
                                            </tr>
                                        </thead>
                                 
                                        <tfoot>
                                            <tr> 
                                                <th></th>
                                                <th class="questionsanswered"></th>
                                                <th class="questionsanswered">
                                                <select id="timeRange">
								<option value="All">All</option>
								<option value="1">&lt;1</option>
								<option value="2">1-3</option>
								<option value="3">&gt;3</option>
				             </select>
                                                </th>                                                
                                                <th class="accuracy"></th>  
                                            </tr>
                                        </tfoot> 
                                    </table>
						</div>
						</section>	
						
						
						<br>
						
						<section class="panel panel-default">
                                <header class="panel-heading">
                                 Verbal Section
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>
                               <div class="table-responsive">
                                <table id="expandable-table1" class="table table-striped display expandable-table1" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                              
                                                <th>Skill</th>
                                                <th class="questionsanswered">Questions Answered</th>
                                                <th class="questionsanswered">Avg Time</th>                                                
                                                <th class="accuracy">Accuracy</th>
                                            </tr>
                                        </thead>
                                 
                                        <tfoot>
                                            <tr> 
                                                <th></th>
                                                <th class="questionsanswered"></th>
                                                <th class="questionsanswered">
                                                 <select id="timeRangeVerbal">
								<option value="All">All</option>
								<option value="1">&lt;1</option>
								<option value="2">1-3</option>
								<option value="3">&gt;3</option>
				             </select>
                                                </th>                                                
                                                <th class="accuracy"></th>
                                            </tr>
                                        </tfoot> 
                                    </table>
						</div>
						</section>	
                        </div>
          
				<!--Data table End -->
            </section>
          </section>
          <!-- <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a> -->
        </section>
      </section>
      <div id="progressbarId">
      <div class="progress">
      <div class="progress-bar progress-bar-success" role="progressbar" aria-valuetransitiongoal="92"></div>
      </div>
      <script type="text/javascript">$(document).ready(function(){ $(".progress-bar").progressbar({display_text: "fill"});});</script>
      </div>
     <script type="text/javascript"> 
     var status = "0";
      $(document).ready(function() {   
    	 
    	  $('#timeRange').change(function(){ 
    		  status = "0";
    		  table1.draw();
   		  });
    	  
    	 
    	  
    	    var skillData=$('#quantskillDataID').val(); 
    	   // alert('Quant Skills:'+$('#quantSkills').val()+"\n Verbal Skills:"+$('#verbalSkills').val());
    	     var dd= JSON.parse(skillData);
    	    var table1 = $('#expandable-table').DataTable( {
    	        "data": dd,
    	        "columns": [  
    	            { "data": "skill" },
    	            { "data": "totalAnswered" },
    	           { "data": "averageTime" },   
    				{ "data": "accuracy" }
    	        ],
    	        "order": [[1, 'asc']]
    	    } );
    		//$('#expandable-table_length,#expandable-table_filter').hide(); 
    			 	 
    		 /*  $('.expandable-table').dataTable().columnFilter({
    			 aoColumns: [{type: "select",values:JSON.parse($('#quantSkills').val())},{ type: "text" },{ type: "text" }]
    			 	 });    */
    	} );
      
      
      
      </script> 
      
      <script>
      /* Custom filtering function which will search data in column four between two values */
	  $.fn.dataTable.ext.search.push(
	  	function( settings, data, dataIndex ) { 
	  		var min = "";//$('#min').val() * 1;
	  		var max = "1000";//$('#max').val() * 1;
	  		var condition = "";
	  		if(status=="1") condition = $('#timeRangeVerbal').val();
	  		else condition = $('#timeRange').val();
	  		//alert("Condition : "+condition+"\t Status : "+status);
	  		if(condition=="1"){
	  			min = "";
	  			max = "1";
	  		}
	  		else if(condition=="2"){
	  			min = "1";
	  			max = "3";
	  		}
	  		else if(condition=="3"){
	  			min = "3";
	  			max = "";
	  		}
	  		
	  		var time = data[2];
	  		var timeInSeconds=time.split(':'); 
	  		var time = parseFloat( timeInSeconds[1] ) ||0; // use data for the age column  
	  		 
	  		if ( ( min == '' && max == '' ) ||
	  			 ( min == '' && time <= max ) ||
	  			 ( min <= time && '' == max ) ||
	  			 ( min < time && time < max ) )
	  		{
	  			//alert("Entered true");
	  			return true;
	  		}
	  		return false;
	  	}
	  );
     
      $(document).ready(function() {  
    	  
    	  $('#timeRangeVerbal').change(function(){  
    		  status="1";
    		  table.draw();
    		  
   		  });
    	   
    	  var skillData=$('#verbalskillDataID').val(); 
 	     var dd= JSON.parse(skillData);
      var table = $('#expandable-table1').DataTable( {
	        "data": dd,
	        "columns": [  
	            { "data": "skill" },
	            { "data": "totalAnswered" },
	            { "data": "averageTime" },            
				{ "data": "accuracy" }
	        ],
	        "order": [[1, 'asc']]
	    } );
		//$('#expandable-table1_length,#expandable-table1_filter').hide();
	     
	    // Add event listener for opening and closing details
	   
			 	/* $('.expandable-table1').dataTable().columnFilter({
					 aoColumns: [{type: "select",values: JSON.parse($('#verbalSkills').val())},{ type: "text" }]
					 	 }); */ 
      });
      </script>
</body>
</html>
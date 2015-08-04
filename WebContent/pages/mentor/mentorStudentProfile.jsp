<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title> -->

<!-- eta charset="utf-8" />
  <title>Crunchprep | GRE Web Application</title> -->
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
 
    <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
    <link rel="stylesheet" href="assets/css/mentor/mentor.css" type="text/css" /> 
  
     <!---Progress bar with text--->
  <link rel="stylesheet" type="text/css" href="assets/plug-ins/review-progress-bar/prettify/prettify.min.css">
  <link rel="stylesheet" type="text/css" href="assets/plug-ins/review-progress-bar/bootstrap-progressbar-3.1.0.css">  
  <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
  <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
  
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
                <div class="row">
                
                  <div class="col-xs-12">
                     
                    <div class="panel">
                     
                     <div class="panel-body">
                     
                     <div class="row">
                     <div class="col-xs-12">
                     
                      <div class="col-md-2 m-t-md">
                       <center>                        
                           <img src="<s:property value="%{#session.user.avatar_path}"/>"   class="img-responsive img-pro" />
                      
                       <br/>
                       <br/>
                       <button type="button"  id="send" class="btn btn-primary"><i class="fa fa-mail-forward"></i> Send a Mail</button>
                      <%--  <a href="mentorSendingMail.action?mentorBO.user_id=<s:property value='%{#request.mentorBO.user_id}'/>" id="send" class="btn btn-primary"><i class="fa fa-mail-forward"></i> Send a Mail</a> --%>
                       </center>
                       </div><!--Profile image close-->
                         
                     <div class="col-md-3 m-t-lg text-details">
                    
                     <form class="form-horizontal" method="post" >
                     
                     <input type="hidden" id="uid" value="<s:property value='%{#request.mentorBO.user_id}'/>"/>
                    	<div class="form-group">
                      	<div class="col-sm-4 control-label font-bold">Name</div>
                      	<div class="col-sm-8 label-name h5">
                       ${mentorBO.studentName}
                      	</div>
                    	</div>
                       
                        <div class="form-group">
                      	<div class="col-sm-4 control-label font-bold">Join Date</div>
                      	<div class="col-sm-8 label-name h5">
                       ${mentorBO.start_date}                       
                      	</div>
                    	</div>
                        
                        <div class="form-group">
                      	<div class="col-sm-4 control-label font-bold">End Date</div>
                      	<div class="col-sm-8 label-name h5">
                        ${mentorBO.end_date}                       
                      	</div>
                    	</div>
                        
                        <div class="form-group">
                      	<div class="col-sm-4 control-label font-bold">Exam Date</div>
                      	<div class="col-sm-8 label-name h5">
                       ${mentorBO.test_date}
                      	</div>
                    	</div>
                    </form>
        			
                     </div><!--Email other details col close-->
        
                     <div class="col-md-3 m-t-lg">
        			 
                    
                      <div class="panel ">
                        <div class="panel-heading no-border  lt text-center">                          
                           <div class="h1 m-t font-bold text-success">340</div>
                             <small class="text-muted  m-b">Projected Score</small>                         
                           </div>
                        <div class="padder-v text-center clearfix">                            
                           <div class="col-xs-6 b-r">
                            <div class="h3 font-bold">${mentorBO.previous_score}</div>
                             <small class="text-muted">Previous Score</small>
                           </div>
                          <div class="col-xs-6">
                            <div class="h3 font-bold">${mentorBO.target_score}</div>
                              <small class="text-muted">Target Score</small>
                           </div>
                        </div>
                      </div>
                      
                    </div>
                    
                    <div class="col-md-4">
                     	<section>                        
                            <div class="panel-body flot-legend">                         
                                <div id="flot-pie"  style="height:240px"></div>                  
                            </div>
	                    </section>
                    </div><!--Pie chart close-->
                     
                    </div> <!--Internal col close-->
                    </div> <!--Internal row close-->
                     
                </div><!--Panel body close-->
                                          
                </div><!--Panel close-->
                 
                
                </div><!--End of main colxs-->
                
                </div><!--End of main row-->


				<div class="row">
                <div class="col-xs-12">
                			
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
                                                            <th>Skill</th>
                                                            <th class="questionsanswered">Questions Answered</th>
                                                            <th class="questionsanswered">                   <select id="timeRange">
								<option value="All">All</option>
								<option value="1">&lt;1</option>
								<option value="2">1-3</option>
								<option value="3">&gt;3</option>
				             </select></th>                                              
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
                                                <th>Skill</th>
                                                <th class="questionsanswered">Questions Answered</th>
                                                <th class="questionsanswered">Avg Time</th>                                                  
                                                <th class="accuracy"></th>
                                            </tr>
                                        </tfoot> 
                                    </table>
						</div>
						
						</section>	
                                    </div>
          
						<!--Data table End -->
                            
                            
                
                
                </div><!--Data table col end-->
                </div><!--Data table row end-->


    
              
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
       
        <script src="assets/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  <!-- Sparkline Chart -->
  <script src="assets/js/charts/sparkline/jquery.sparkline.min.js"></script>  
  <!-- App -->
  <script src="assets/js/charts/flot/jquery.flot.min.js"></script>
  <script src="assets/js/app.js"></script>  
  <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="assets/js/app.plugin.js"></script> 
  
  <script src="assets/js/jquery.dataTables.js"></script>
  
  <script src="assets/plug-ins/skill-data-table/jquery.dataTables.columnFilter.js"></script>
    
 <script type='text/javascript' src="assets/plug-ins/review-progress-bar/prettify/prettify.min.js"></script>
 <script type='text/javascript' src="assets/plug-ins/review-progress-bar/boots/bootstrap-3.1.0.min.js"></script>
 <script type='text/javascript' src="assets/plug-ins/review-progress-bar/bootstrap-progressbar.js"></script>
 
  <script src="assets/js/charts/easypiechart/jquery.easy-pie-chart.js"></script>  
  <script src="assets/js/charts/flot/jquery.flot.pie.min.js"></script>  
  <script src="assets/js/charts/flot/jquery.flot.min.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.tooltip.min.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.spline.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.pie.min.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.resize.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.grow.js"></script>
  <script src="assets/js/charts/flot/demo.js"></script>
    
    
    <script type="text/javascript">
        $(document).ready(function(){  
        	 var skillData=$('#quantskillDataID').val();  
      	     var dd= JSON.parse(skillData);
        	 var table = $('#expandable-table').DataTable( {
     	        "data": dd,
     	        "columns": [  
     	    	            { "data": "skill" },
     	    	            { "data": "totalAnswered" },
     	    	            { "data": "averageTime" },            
     	    				{ "data": "accuracy" }
     	    	        ],
     	        "order": [[1, 'asc']]
     	    } );
        	 
        	  $('#expandable-table_length,#expandable-table_filter').hide(); 
        	  
        	  $('#timeRange').change(function(){  
        		  table.draw();
       		  }); 
        	  
        	  
        	  $.fn.dataTable.ext.search.push(
        			  	function( settings, data, dataIndex ) { 
        			  		var min = "";//$('#min').val() * 1;
        			  		var max = "1000";//$('#max').val() * 1;
        			  		var condition = "";
        			  		condition = $('#timeRange').val();
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
        });
    </script>
    <script>
    $(document).ready(function(){ 
    	$('.expandable-table').dataTable().columnFilter({
    	    aoColumns: [{type: "select",values:JSON.parse($('#quantSkills').val())},{ type: "text" }
    	                ]
    	         }); 
    });
    </script>
    
     <script>
      $(document).ready(function() { 
    	 
    	  var skillData=$('#verbalskillDataID').val(); 
 	     var dd= JSON.parse(skillData);
      var table1 = $('#expandable-table1').DataTable( {
	        "data": dd,
	        "columns": [  
	            { "data": "skill" },
	            { "data": "totalAnswered" },
	            { "data": "averageTime" },            
				{ "data": "accuracy" }
	        ],
	        "order": [[1, 'asc']]
	    } );
		$('#expandable-table1_length,#expandable-table1_filter').hide();
	     
	    // Add event listener for opening and closing details 
		 
			 	$('.expandable-table1').dataTable().columnFilter({
					 aoColumns: [{type: "select",values: JSON.parse($('#verbalSkills').val())},{ type: "text" },{ type: "text" }]
					 	 }); 
      });
      
      
      
      toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "positionClass": "toast-top-right",
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
	  
	  
	  
	  $('#send').click(function() {
		  //alert("hai"+$("#uid").val());
		  
		  $.ajax({
				type : 'POST',
				url : 'mentorSendingMail.action?mentorBO.user_id='+$("#uid").val(),
				dataType : 'json',
				success : function(	data) {
					
					////alert("Mail Sent Staus \n"+data.mailSentStatus);
					////alert(textStatus);
					////alert(data.data);
					if (data.message == "Mail Sent Successfully") {
						toastr.success("Mail Sent Successfully");
					} else if (data.message == "Doesnt Have Mentor") {

						toastr.error("Mail Sending Fail");
					}
					

				},
				error : function actionFailed(data) {
					//alert(data);
					alert("Error");
				}

			});
		  
	  });
	  
      
      
      
      </script>
 

</body>
</html>
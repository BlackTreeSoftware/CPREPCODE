<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />

<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  .color1{color:red}
  .color2{color: green}
  </style>
<script src="assets/js/jquery.min.js"></script>
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>

<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
 
  <script type="text/javascript">
  
  $(document).ready(function(){
	  
	 
	  var oTable = $('#table').dataTable( {

		    "bProcessing": true,

		   

		    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
		    "sPaginationType": "full_numbers",
		    "aoColumns": [
		      { "mData": "columnname1" },
		      { "mData": "columnname2" },
		      { "mData": "columnname3" },
		      { "mData": "columnname4" },
		      { "mData": "columnname5" },
		      { "mData": "columnname6" }
		    ]
		  } );
	  
	  
	 
  });
	
	  
	 
 
  
  </script>
  <link href="assets/css/mentor/mentorstudentlist.css" rel="stylesheet" type="text/css"/>
  
<%-- <style>
  	/*This is for anchor tag with green color*/
    table .btn{padding:0px !important;}
    a.text-success:hover{color:#158b6c;}
	
	a.text-primary:link{color:#2796de;}
	a.text-primary:hover{color:#0d5e92; text-decoration:underline;}
	   
	a.green{color:#179877;} 
	a.green:hover,.green:active{color:#47a447; text-decoration:underline;} 
	
	a.link-lesson{color:#006666;}
	a.link-lesson:hover,link-lesson:active{color:#060; text-decoration:underline;}
	
	a.bt-table:hover, bt-table:active{text-decoration:none}
	
  </style>
  
   --%>
  
</head>
<body>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             
             <div class="panel-body">
     
             
         
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
               <div class="table-responsive" id="table_data">
                <form id="form2" method="post">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                      
                        <th>Student Name</th>
                        <th>Last Login in Days</th>
                        <th>LessonTaken ProgressBar</th>
                        <th>QuestionsTaken ProgressBar</th>
                        <th>Progress</th>
                        <th>Activity</th>
                        
                       
                         
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="studentProgressBarList" status="status" var="i">
                    <tr>
									<s:if test="#i.noLastLoginDays > 7">
								<td><a href="viewProgress.action?mentorBO.user_id=<s:property value='user_id'/>" class="btn btn-link bt-table text-primary color1"><s:property value="studentName"/></a></td>
							</s:if>
							<s:else>
								<td><a href="viewProgress.action?mentorBO.user_id=<s:property value='user_id'/>" class="btn btn-link bt-table text-primary color2"><s:property value="studentName"/></a></td>
							</s:else>
										
										
										<td><a href="mentorStudentLogsList.action?mentorBO.user_id=<s:property value='user_id'/>" class="btn btn-link bt-table text-primary"><s:property value="noLastLoginDays"/></a></td>
										<td>
    			   	 <div class="progress progress-sm progress-striped  active">
                          <div class="progress-bar progress-bar-success" data-toggle="tooltip" data-original-title="<s:property value="lessonsTaken"/>/<s:property value="totalLessons"/>" style="width: <s:property value="percentageLessions"/>%"></div>
                     </div>  
    			   	  </td>
					<td>
  				   	
    			   	 <div class="progress progress-sm progress-striped  active">
                          <div class="progress-bar progress-bar-success" data-toggle="tooltip" data-original-title="<s:property value="questionsTaken"/>/<s:property value="totalQuestions"/>" style="width: <s:property value="percentageQuestions"/>%"></div>
                     </div>  
    			   	  </td>		
										<td><a href="viewSectionProgress.action?mentorBO.user_id=<s:property value='user_id'/>" class="btn btn-link bt-table text-success"><i class="fa fa-bar-chart-o"></i> Progress</a></td>
										<td><a href="Mentor-Student-Activity.action?mentorBO.user_id=<s:property value='user_id'/>" class="btn btn-link bt-table text-success"><i class="fa fa-folder"></i> View Activity</a></td>
										
										
										
										
										
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  </form>
                  
                
                </div>
                
                </section>
               
              </section><!--sec close for data table-->
              
              
             
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
    <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	

</body>
</html>
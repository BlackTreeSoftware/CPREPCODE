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
		      { "mData": "columnname6" },
		      { "mData": "columnname7" },
		      { "mData": "columnname8" },
		      { "mData": "columnname9" }
		    
		      
		      
		      
		    ]
		  } );
	  
	  
	 
  });
	
	  
	 
 
  
  </script>
</head>
<body>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Student Details</span>  </div>
             <div class="panel-body">
     
             
             
            
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="table_data">
                <form id="form2">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Student Name</th>
                        <th>Email</th>
                        <th>LessionsTaken</th>
                        <th>TotalLessions</th>
                        <th>QuestionsTaken</th>
                        <th>TotalQuestions</th>
                        <!-- <th>PercentageLessions</th>
                        <th>PercentageQuestions</th> -->
                        <!-- <th></th> -->
                        <th width="50%">LessonTaken ProgressBar  </th>
                         <th width="50%">QuestionsTaken ProgressBar  </th>
                        
                        
                       
                         
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="studentProgressBarList" status="row">
									<tr >
										
										<td><s:property value="#row.index+1"/></td>
										<td><s:property value="studentName"/></td>
										<td><s:property value="email"/></td>
										<td><s:property value="lessonsTaken"/></td>
										<td><s:property value="totalLessons"/></td>
										<td><s:property value="questionsTaken"/></td>
										<td><s:property value="totalQuestions"/></td>
										<%-- <td><s:property value="percentageLessions"/></td>
										<td><s:property value="percentageQuestions"/></td> --%>
										<%-- <td><s:property value="lessonsTaken"/>/<s:property value="totalLessons"/></td> --%>
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
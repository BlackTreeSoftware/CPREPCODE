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
		      { "mData": "columnname2" }
		    ]
		  } );
	  
	  
	
	  
	  
  });
  
  function mentorStudents(){
	 // alert("hai"+$("#mentor").val());
	  
	  var formData = $('#form1').serialize();
	  
	   $.ajax({
		  type : 'POST',
		  url  : 'getMentorStudentAccounts',
		  data : formData,
		 				
			 success:function(data){ 
					
				 $('#table_data').html(data);
				 
	    		
	    		 
				},
				error:function(e){
					
				}
		 });   
	  
  }
  
  
 
  
  </script>
</head>
<body>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Mentor Accounts</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-4 col-lg-offset-4" style="padding-top:40px;">
             
              <form method="post" class="bs-example form-horizontal" theme="simple" id="form1" >
              
                       <div class="form-group">
                     	 <label class="col-sm-4 control-label">Mentor List </label>
                      	<div class="col-sm-8">
                        <s:select  headerKey="-1" headerValue="Select" name="adminBO.id" class="form-control m-b" id="mentor" onchange="mentorStudents()" list="mentorList" listValue="name" listKey="id" cssClass="form-control m-b">
                           <!-- <option value="-1">--Select--</option> -->
                          
                        </s:select>
                        </div>
                        </div>
                       
                      
        	  </form>
        	  <center> <span id="error"></span></center>
              
              <hr>
                      
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="table_data">
                <form id="form2">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Student Name</th>
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="mentorStudentList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="studentName"/></td>
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  </form>
                  
                
                </div>
                
                </section>
               
              </section><!--sec close for data table-->
              
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
             
             
             
             
             
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
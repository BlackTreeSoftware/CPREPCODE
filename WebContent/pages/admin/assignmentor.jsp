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
	  

	  var oTable = $('#table').dataTable( {

		    "bProcessing": true,

		   

		    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
		    "sPaginationType": "full_numbers",
		    "aoColumns": [
		      { "mData": "columnname1" },
		      { "mData": "columnname2" },
		      { "mData": "columnname3" },
		      { "mData": "columnname4" }
		    ]
		  } );
	  
	  
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=userid]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=userid]').each(function () {
	    			//alert("calling");
	    			//alert($(this).attr("id"));
	    			  $(this).prop('checked',false);
	    	});
	    	}
	        
	    });
	    

	  $('#1').click(function() {
		 // alert("hai  "+$('#1').val());
		  var val=$('#1').val();
		 /*  $.getJSON('getMentors.action',
					 {'mantortypeId':$(this).val()},
					 function(input){ 
						 $("#mentor option").remove();
						 $("#mentor").append(input.mentors);
					 }); */
					 
					 
					 
		  $.ajax({
			  type : 'POST',
			  url  : 'getMentors.action?mantortypeId='+val,			
			 				
				 success:function(data){ 
						
					 $('#data').html(data);		 
		    		
					},
					error:function(e){
						
					}
			 }); 
	  });
	  
	  $('#2').click(function() {
		  //alert("hai  "+$('#2').val());
		  /* $.getJSON('getMentors.action',
					 {'mantortypeId':$(this).val()},
					 function(input){ 
						 $("#mentor option").remove();
						 $("#mentor").append(input.mentors);
					 }); */
					 
					 var val=$('#2').val();
					 
		  $.ajax({
			  type : 'POST',
			  url  : 'getMentors.action?mantortypeId='+val,			
			 				
				 success:function(data){ 
						
					 $('#data').html(data);
					 
		    		
		    		 
					},
					error:function(e){
						
					}
			 }); 
	  });
	  
	  $('#assign').click(function() {
		 // alert("hai");
		  
		if($('#mentor').val()==-1){
					  
					  alert("select Mentor");
					  return false;
					  
				  }else if($('[name="userid"]:checked').length == 0) {
					  alert("select Student to Assign Mentor");
					  return false;
				  }
					  
					  
					  else{
		  
		  var formData = $('#form1').serialize();
		  
		  $.ajax({
			  type : 'POST',
			  url  : 'assignMentors.action',
			  data : formData,
			 				
				 success:function(data){ 
						
					 $('#table_data').html(data);
					 
		    		
		    		 
					},
					error:function(e){
						
					}
			 }); 
		  
	  }
		  
		  
	  });
	  
	  
	  $('#delete').click(function() {
		  //alert("hai");
		  if($('#mentor').val()==-1){
			  
			  alert("select Mentor");
			  return false;
			  
		  }else if($('[name="userid"]:checked').length == 0) {
			  alert("select Student to Delete Mentor");
			  return false;
		  }else{
		  
		  var formData = $('#form1').serialize();
		  
		  $.ajax({
			  type : 'POST',
			  url  : 'deleteMentors.action',
			  data : formData,
			 				
				 success:function(data){ 
						
					 $('#table_data').html(data);
					 
		    		
		    		 
					},
					error:function(e){
						
					}
			 });  
		  }
		  
	  });
	  
	  
	  $('#update').click(function() {
		 // alert("hai");
		  if($('#mentor').val()==-1){
			  
			  alert("select Mentor");
			  return false;
			  
		  }else if($('[name="userid"]:checked').length == 0) {
			  alert("select Student to Update Mentor");
			  return false;
		  }else{
		  
		  var formData = $('#form1').serialize();
		  
		   $.ajax({
			  type : 'POST',
			  url  : 'updateMentors.action',
			  data : formData,
			 				
				 success:function(data){ 
						
					 $('#table_data').html(data);
					 
		    		
		    		 
					},
					error:function(e){
						
					}
			 });   
		  }
		  
	  });
	  
	  
	  
	 
	  
	  
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
             <div class="panel-heading"> <span class="panel-title"> Assign Mentor To Student</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-4 col-lg-offset-4" style="padding-top:40px;">
             
            <!--  -->
              
                       
                       
                          <div class="form-group">
                       				 <label class="col-sm-4 control-label">Mentor Type</label>
                        			
                        			
                        			 <div class="radioo i-checks col-lg-3">
                                  <label>
                                    <input type="radio" name="mantortypeId" value="1" id="1" checked="checked">
                                    <i></i>
                                   Quant 
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-3">
                                  <label>
                                    <input type="radio" name="mantortypeId" value="2" id="2">
                                    <i></i>
                                    Verbal
                                  </label>
                                </div>   
                       
                                
                                  
                            
                        </div>
                        
                        </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
                  
              <div id="data">      
                    
                     <form method="post"  theme="simple" id="form1" enctype="multipart/form-data">     
               <div class="row">
             <div class="col-lg-4 col-lg-offset-4" style="padding-top:20px;">
                        
                        
                        <div class="form-group">
                     	 <label class="col-sm-4 control-label">Mentor List </label>
                      	<div class="col-sm-8">
                        <s:select  headerKey="-1" headerValue="Select" name="mentorBO.mentorId" class="form-control m-b" id="mentor" list="mentorlist" listValue="mentorName" listKey="mentorId" cssClass="form-control m-b">
                           <!-- <option value="-1">--Select--</option> -->
                          
                        </s:select>
                        </div>
                        </div>
                      
        	
        	  
              </div><!-- col for skill level close -->
             
             </div><!-- Inner row1 close -->
             
             
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="table_data">
                
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Student</th>
                        <th>Mentor</th>
                        
                        <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="finalList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="studentName"/></td>
										<td><s:property value="mentorName"/></td>										
										<td class="chck" style="padding-bottom: 1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value="<s:property value="user_id"/>" name="userid"/><i></i></label></td>
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  
                  
                
                </div>
                
                </section>
                
              </section><!--sec close for data table-->
              
           
                      
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
             </form>
             
             
             </div>
             
             <center><button type="button" class="btn btn-success" id="assign">Assign</button>
                <button type="button" class="btn btn-del" id="update">Update</button>
                <button type="button" class="btn btn-danger" id="delete">Delete</button></center>
             
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
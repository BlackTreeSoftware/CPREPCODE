<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />   
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<script src="assets/js/select2/select2.min.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script type="text/javascript"> </script>
<script src="assets/js/parsley/parsley.min.js"></script>
<script src="assets/js/parsley/parsley.extend.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {     
		 $("#hide_type").hide(); 
		 
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
   	       /*  toastr.success("Success Message", "Success"); */
		 
 	var oTable = $('#admin1').dataTable( {

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

		     			$('#update').hide();
						$('#confirm').click(function(){	
							
							var name=$('#admin_name').val();	 ;
							var email=$('#email').val();
							var password=$('#password').val();
							var contact=$('#contact').val();
							 var emailReg = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
				    		  var valid = emailReg.test(email); 
							
							if(name=='' ||name.indexOf(" ")==0){
								 $("#error_name").fadeIn();
								 $('#error_name').html("Please enter  name");
								 $("#error_name").css("color", "red");
								 setTimeout(function(){ $('#error_name').fadeOut(),$("#error_name").fadeOut("slow"); }, 3500);
								 $('#admin_name').focus();
							}
							else if(name.length>50)
							{ 
							toastr.error("Name lenght should not be more than 50 characters"); 
							$('#admin_name').val('');
							}
							else if(email=='' ||email.indexOf(" ")==0) {
								$("#error_email").fadeIn();
								 $('#error_email').html("Please enter  email");
								 $("#error_email").css("color", "red");
								 setTimeout(function(){ $('#error_email').fadeOut(),$("#error_email").fadeOut("slow"); }, 3500);
								 $('#email').focus();
							}
							else if(!valid)
								{
								toastr.error("Please Enter a Valid Email Id");
								$('#email').val('');
								}
							else if(password==''||password.indexOf(" ")==0) {
								$("#error_password").fadeIn();
								 $('#error_password').html("Please enter  password");
								 $("#error_password").css("color", "red");
								 setTimeout(function(){ $('#error_password').fadeOut(),$("#error_password").fadeOut("slow"); }, 3500);
								 $('#password').focus();
							}
							else if(contact==''||contact .indexOf(" ")==0) {
								$("#error_contact").fadeIn();
								 $('#error_contact').html("Please enter  contact");
								 $("#error_contact").css("color", "red");
								 setTimeout(function(){ $('#error_contact').fadeOut(),$("#error_contact").fadeOut("slow"); }, 3500);
								 $('#contact').focus();
							}
						 
							else{
							var templateRadioId=$('#status').val();
							var name=$('#admin_name').val();
							var contact=$('#contact').val();
							var email=$('#email').val();
							var password=$('#password').val();
							var status=$('input:radio[name="adminRegistrationBO.status"]:checked').val();
							var type=$('input:radio[name="adminRegistrationBO.role"]:checked').val();
							var section=$('input:radio[name="adminRegistrationBO.section_name"]:checked').val();
							$('#status').attr('value',status);	
							$('#role').attr('value',type);
							$('#section_name').attr('value',section);
									  $.ajax({
											type : 'POST',
											url : 'saveAdminRegistration.action',
										    data :'adminRegistrationBO.admin_name='+name+'&adminRegistrationBO.contact='+contact+'&adminRegistrationBO.email='+email+'&adminRegistrationBO.password='+password+'&adminRegistrationBO.status='+status+'&adminRegistrationBO.role='+type+'&adminRegistrationBO.section_name='+section,
											dataType : 'JSON',
										    success :successInfo,
										    error : errorInfo
										}); 
							}
							            function successInfo(result,status){
								            if(status=="success")
								            toastr.success("Registration Done");
								            refresh();
								            return true;
							            }
							  
							            function errorInfo(result){
							            	toastr.error("Error Message", 'Registration Unsuccessfull');
								            return false;
								   	   }	
						/* 	} */
						});  
						
						$('#update').click(function(){	
							
							var name=$('#admin_name').val();
							var email=$('#email').val();
							var password=$('#password').val();
							var contact=$('#contact').val();
							if(name=='' ||name.indexOf(" ")==0){
								 $("#error_name").fadeIn();
								 $('#error_name').html("Please enter  name");
								 $("#error_name").css("color", "red");
								 setTimeout(function(){ $('#error_name').fadeOut(),$("#error_name").fadeOut("slow"); }, 3500);
								 $('#admin_name').focus();
							}
							else if(email=='' ||email.indexOf(" ")==0) {
								$("#error_email").fadeIn();
								 $('#error_email').html("Please enter  email");
								 $("#error_email").css("color", "red");
								 setTimeout(function(){ $('#error_email').fadeOut(),$("#error_email").fadeOut("slow"); }, 3500);
								 $('#email').focus();
							}
							else if(password==''||password.indexOf(" ")==0) {
								$("#error_password").fadeIn();
								 $('#error_password').html("Please enter  password");
								 $("#error_password").css("color", "red");
								 setTimeout(function(){ $('#error_password').fadeOut(),$("#error_password").fadeOut("slow"); }, 3500);
								 $('#error_password').focus();
							}
							else if(contact==''||contact .indexOf(" ")==0) {
								$("#error_contact").fadeIn();
								 $('#error_contact').html("Please enter  contact");
								 $("#error_contact").css("color", "red");
								 setTimeout(function(){ $('#error_contact').fadeOut(),$("#error_contact").fadeOut("slow"); }, 3500);
								 $('#error_contact').focus();
							}
							else{
							
							var templateRadioId=$('#status').val();
							var name=$('#admin_name').val();
							var contact=$('#contact').val();
							var email=$('#email').val();
							var id=$('#admin_id').val();
							var password=$('#password').val();
							var status=$('input:radio[name="adminRegistrationBO.status"]:checked').val();
							var type=$('input:radio[name="adminRegistrationBO.role"]:checked').val();
							var section=$('input:radio[name="adminRegistrationBO.section_name"]:checked').val();
							$('#status').attr('value',status);	
							$('#role').attr('value',type);
							$('#section_name').attr('value',section);
									  $.ajax({
											type : 'POST',
											url  : 'updateAdminRegistration.action',
										    data :'adminRegistrationBO.admin_name='+name+'&adminRegistrationBO.contact='+contact+'&adminRegistrationBO.email='+email+'&adminRegistrationBO.password='+password+'&adminRegistrationBO.status='+status+'&adminRegistrationBO.role='+type+'&adminRegistrationBO.section_name='+section+'&adminRegistrationBO.admin_id='+id,
											dataType : 'JSON',
										    success :successInfo,
										    error : errorInfo
										}); 
						 	}
							            function successInfo(result,status){
								            if(status=="success")
								            	toastr.success("Updated Successfully!!!");
								            refresh();
								            return true;
							            }
							  
							            function errorInfo(result){
							            	toastr.error("Error Message", 'Please update properly');
								            return false;
								   	   }	
						});  
						
						$('#delete').click(function(){	
							
							 var checked = $("input[id=select]:checked").length;
				                if (checked == 0) {

				                    toastr.error("please select atleast single Question to Delete");
				                } 
				                else
				                	{
							
							var id=$('#policy_id').val();
							var array=[];
							var checkboxvalues;
							$('input[id=select]:checked').each(function(){
								array.push($(this).val());
							});
							checkboxvalues=array.join(',') + ",";
									  $.ajax({
											type : 'POST',
											url : 'deleteAdminRegistration.action',
										    data :'checkboxvalues='+checkboxvalues,
											dataType : 'JSON',
										    success :successInfo,
										    error : errorInfo
										}); 
							            function successInfo(result,status){
								            if(status=="success")
								            toastr.success("Deleted !!!");
								            refresh();
								            return true;
							            }
							  
							            function errorInfo(result){								            
							            	toastr.success("Deleted !!!");
							            	refresh();
								            return false;
								   	   }
				                	}
						}); 
						
						$('#contentcreator').click(function() {
				                $("#hide_type").hide();  
				        }); 
						 $('#mentor').click(function() {
						        $("#hide_type").show();        
						 }); 
						 $('#admin').click(function() {
						        $("#hide_type").hide();        
						 }); 
						 
						 
						$("#formt").on("submit",function(e){
							
							alert("Hi");
							e.preventDefault();
						});
						
						 
					});
	  
	        function passwordVerification(){
		  	var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,16}/;   
		    var password=$('#password').val();
		    if(!re.test(password)){
		    	alert("Password Must have One Capital Letter, One Special Symbol and One Numeric with Atlease 8 characters in Length");
		    	document.getElementById("password").value="";		    	
		    	return false;	  
		    }		  
	        }

            function cnfpwdValidation(){
	        var pwd = document.getElementById("password").value;
	        var cpwd = document.getElementById("confirmpassword").value;
	        if(pwd!=cpwd){
		        alert("Password & Confirm Password Doesn't Match");
		        document.getElementById("password").value="";
		        document.getElementById("confirmpassword").value="";
		        return false;  
	      } 
          }
            
            function setDetails(name,email,contact,status,section,role,id) {
            	$('#confirm').hide();
        		$('#update').show();
        		$('#admin_name').val(name);
        		$('#email').val(email);
        		$('#contact').val(contact);
        		$('#admin_id').val(id);
        		
        		   if(status=='ACTIVE'){
        			$('#active').prop("checked",true);
        			}
        			if(status=='INACTIVE'){
        				
        				$('#inactive').prop("checked",true);
        			}
        		
        		if(role=='Mentor'){
        			$("#hide_type").show();
        			$('#mentor').prop("checked",true);	
        			if(section=='Quantitative'){
        				$('#quant').prop("checked",true);
            		}
            		else if(section=='Verbal'){
            			$('#verbal').prop("checked",true);
            		}
            		else 
            			$('#both').prop("checked",true);
        		}
        		else if(role=='Content Creator'){
        			 $("#hide_type").hide();
        			 $('#contentcreator').prop("checked",true);
        		}
        		else if(role=='Admin'){
       			     $("#hide_type").hide();
       			  $('#admin').prop("checked",true);
       		    }
        		
        		
        	} 
            
            function refresh(){
        		$.ajax({
        			type : 'POST',
        			url  : 'RefreshAdminRegistration.action',
        		    success :successInfo,
        		    error : errorInfo
        		}); 
                function successInfo(result,status){
                    if(status=="success")
                    $('#confirm').show();
            		$('#update').hide();
                    $('#ajax-admin').html(result);
                    return true;
                }

                function errorInfo(result){
                    alert("Error");
                    return false;
           	   }	
        		
        	}
            function isNumberKey(evt){
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
          
</script>
<body>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title">Admin Registration</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              
             
              <form id="formt" class="bs-example form-horizontal" action="AdminRegistration.action" method="post" data-validate="parsley">
                             
                             
                              <div class="form-group">
                       				 <label class="col-lg-2 col-lg-offset-3 control-label">Role
                       				 </label>
                        			 <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" id="admin" value="ADMIN" checked name="adminRegistrationBO.role">
                                    <i></i>
                                   Admin 
                                  </label>
                                 
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" id="contentcreator" name="adminRegistrationBO.role" value="CONTENT CREATOR" >
                                    <i></i>
                                    Content Creator
                                  </label>
                                </div>   
                                
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" id="mentor" name="adminRegistrationBO.role" value="MENTOR" >
                                    <i></i>
                                    Mentor
                                  </label>
                                </div>              
                            
                        </div>
                             
                           
                           <div class="form-group">
                          <label class="col-lg-4 control-label">Name</label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" placeholder="Name" id="admin_name" name="adminRegistrationBO.admin_name" data-required="true" data-type="alpha">                            
                          </div>
                          <small  id="error_name" style="color:red"></small>
                        </div>
                        
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Email ID</label>
                          <div class="col-lg-8">
                            <input type="email" class="form-control" placeholder="Email" id="email" name="adminRegistrationBO.email" >                            
                          </div>
                           <small id="error_email" style="color:red"></small>
                        </div>
                        
                        
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Password</label>
                          <div class="col-lg-8">
                            <input type="Password" class="form-control" placeholder="Password" id="password" name="adminRegistrationBO.password" onchange="passwordVerification()" >                            
                          </div>
                           <small id="error_password" style="color:red"></small>
                        </div>
                        
                      <!--   <div class="form-group">
                          <label class="col-lg-4 control-label">Confirm Password</label>
                          <div class="col-lg-8">
                            <input type="Password" class="form-control" placeholder="Confirm Password" id="confirmpassword" name="adminRegistrationBO.password" onchange="javascript:cnfpwdValidation()" required="required">                            
                          </div>
                        </div> -->
                        
                         <div class="form-group">
                          <label class="col-lg-4 control-label">Contact No</label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" placeholder="Contact No" id="contact" name="adminRegistrationBO.contact" data-required="true" data-type="phone" maxlength="10" onkeypress='return isNumberKey(event)'>                            
                          </div>
                           <small id="error_contact" style="color:red"></small>
                        </div>
                             
                             
                             
                        <div class="form-group" id="hide_type">
                       				 <label class="col-lg-2 col-lg-offset-3 control-label">Section</label></label>
                        			 <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" id="quant" name="adminRegistrationBO.section_name" value="Quantitative" checked>
                                    <i></i>
                                   Quant
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" id="verbal" name="adminRegistrationBO.section_name" value="Verbal" >
                                    <i></i>
                                    Verbal
                                  </label>
                                </div>   
                                
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" id="both" name="adminRegistrationBO.section_name" value="Both" >
                                    <i></i>
                                    Both
                                  </label>
                                </div>              
                            
                        </div>
                          <div class="form-group">
                       				 <label class="col-lg-2 col-lg-offset-3 control-label">Status</label>
                        
                            <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" id="active" value="ACTIVE" checked name="adminRegistrationBO.status">
                            <i></i>
                           Active
                          </label>
                        </div>
                        
                       <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" id="inactive" value="INACTIVE" name="adminRegistrationBO.status">
                            <i></i>
                           In Active
                          </label>
                        </div>
                        
                        </div>
                           <input type="hidden" class="form-control" id="status" name="adminRegistrationBO.status">
                           <input type="hidden" class="form-control" id="role" name="adminRegistrationBO.role">
                           <input type="hidden" class="form-control" id="section_name" name="adminRegistrationBO.section_name">
                           <input type="hidden" class="form-control" id="role_id" name="adminRegistrationBO.role_id">
                           <input type="hidden" class="form-control" id="section_id" name="adminRegistrationBO.section_id">
                           <input type="hidden" class="form-control" id="admin_id" name="adminRegistrationBO.admin_id">

                                              
                          <div class="form-group">
                          <div class="col-lg-offset-6 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn btn-success" id="confirm">Submit</button>
                         
                            <button type="button" class="btn btn-primary" id="update">Update</button>
                          </div>
                          <div class="col-lg-offset-1 col-lg-1">
                            <button type="reset" class="btn btn-danger" id="reset">Reset</button>
                          </div>                                                
                          </div><!--form group close-->
        	              </form>
              
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
          		
                
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="ajax-admin">
                <table class="table table-striped m-b-none" id="admin1">
                    <thead>
                        <tr>
                        <th>Sr.No</th>
                        <th>Select</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Section</th>
                        <th>Contact</th>
                        <th>Status</th>
                        <th>Edit</th>
                        </tr>
                     </thead>
                     <tbody>
                     <s:iterator value="adminRegistrationList" status="row"> 
                     <tr>
                     <td><s:property value="#row.index+1"/></td>
                     <td><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="admin_id"/>'><i></i></label></td>
                     <td><s:property value="admin_name"/></td>
                     <td><s:property value="email"/></td>
                     <td><s:property value="role" /></td>
                     <td><s:property value="section_name" /></td>
                     <td><s:property value="contact" /></td>
                     <td><s:property value="status" /></td>
                     <td><a class="btn btn-default" href="javascript:setDetails('<s:property value="admin_name"/>','<s:property value="email"/>','<s:property value="contact" />','<s:property value="status"/>','<s:property value="section_name" />','<s:property value="role" />','<s:property value="admin_id"/>')"  >Edit</a></td>
                     </tr>
                    </s:iterator>
                    </tbody>
                    </table> 
                    
                </div>
                
              </section>
                <center><a href="#" class="btn btn-del" id="delete">Delete selected</a></center>
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
<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
<script src="assets/js/datatables/demo.js"></script>
</body>
</html>
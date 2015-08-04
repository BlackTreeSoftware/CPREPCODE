<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="s" uri="/struts-tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
<script src="assets/plug-ins/ckeditor/ckeditor.js"></script>   

<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>

<script>

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
			      { "mData": "columnname4" },
			      { "mData": "columnname5" },
			      { "mData": "columnname6" }
			    
			    ]
			  } );
		

	 $('#templateTitleId,#templateSubjectId').keyup(
				function() {
					valid = "true";
					var uname = document.getElementById("templateTitleId").value;		
					var regExp = /^[a-zA-Z]/; // allow only letters
					var iChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?";
					if (!regExp.test(uname)) {
						//bootbox.alert("Enter Alphabets Only");
						//validation("Enter Alphabets Only");
						//alert("Error");
					document.getElementById("templateTitleId").value="";
					document.getElementById("templateTitleId").focus();
					return false;
					} else{
						for ( var i = 0; i < document.getElementById("templateTitleId").value.length; i++) {
							if (iChars.indexOf(document.getElementById("templateTitleId").value.charAt(i)) != -1) {
								//bootbox.alert("Enter Alphabets Only");
								document.getElementById("templateTitleId").value="";
								document.getElementById("templateTitleId").focus();
								return false;

							}
						}
					}
					
					
					var uname = document.getElementById("templateSubjectId").value;		
					var regExp = /^[a-zA-Z]/; // allow only letters
					var iChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?";
					if (!regExp.test(uname)) {
						//bootbox.alert("Enter Alphabets Only");
						//validation("Enter Alphabets Only");
						//alert("Error");
					document.getElementById("templateSubjectId").value="";
					document.getElementById("templateSubjectId").focus();
					return false;
					} else{
						for ( var i = 0; i < document.getElementById("templateSubjectId").value.length; i++) {
							if (iChars.indexOf(document.getElementById("templateSubjectId").value.charAt(i)) != -1) {
								//bootbox.alert("Enter Alphabets Only");
								document.getElementById("templateSubjectId").value="";
								document.getElementById("templateSubjectId").focus();
								return false;

							}
						}
					}
					
				});

	  
	   $('#delete').click(function(){
		   var childBoxLength=$(".childBox:checked").length; 
		   if(childBoxLength==0){
			   
			   toastr.warning("No  Template Seleced");
			   
		   }
			 else{
			  var formData = $('#formId2').serialize();
			  
				 $.ajax({
					type : 'POST',
					url : 'Mailmaster-Delete.action', 
					data : formData,
					success :  function deleteSuccess(data, status) {
						 $('#table_data').html(data);
						
						 resetValidation();
			    		
			    	},
					error : function deleteFailed(errorIs) {
						 toastr.error(' Failed ' + errorIs.responseText);
					}

				});
				 
				
			 }
			  
		  });
	   
	   

		
	 
}); 
	
 function resetValidation(){
	  
      $('#templateId').val('0');
      $('#templateTitleId').val('');
	  $('#templateSubjectId').val('');
	  CKEDITOR.instances['editorId'].setData("");
	  $("#optionsRadio1").prop("checked",true);
	  $('#submitId').text("Submit").removeClass("btn-primary").addClass("btn-success");
 }
 
 
   function formValidation(){
	
	      
		   
			var templateTitle=$('#templateTitleId').val();
			var templateSubject=$('#templateSubjectId').val();
			var templateRadioId=$('#mailTemplateStatus').val();
			var status=$(':radio:checked').val();
			var ckeditorId=CKEDITOR.instances['editorId'].getData();
			var templateIdd=$('#templateId').val();
			 
			$('#mailTemplateStatus').attr('value',status);
			
			     if(templateTitle==''){
			    	 toastr.error("Enter Template Title");
			    	 return false;
			    	 
			    	 
			     }else if(templateSubject==''){
			    	 toastr.error("Enter Template Subject");
			        return false;
			     }else if(ckeditorId==''){
			    	 
			    	 toastr.error("Enter Template Body");
			    	 return false;
			    	   
            }else{
			     
		          $.ajax({
							type : 'POST',
							url : 'Mailmaster-Template.action',
						    data :'mailmasterTemplateBO.template_id='+templateIdd+'&mailmasterTemplateBO.template_title='+templateTitle+'&mailmasterTemplateBO.template_subject='+templateSubject+'&mailmasterTemplateBO.template_body='+ckeditorId+'&mailmasterTemplateBO.template_status='+status,
						    success :function(result){
						    	 // $('#resetId').trigger("click");
						    		$('#table_data').html(result);
						    		$('#maincheckbox').click(function(event) { 
						    			   $('.childBox').prop('checked',$(this).prop('checked'));
						    			   
						    			 
						    		    });
						    		    
						    		resetValidation();
						    		
						    		
						    	},
							error : function(e){			
								 toastr.error("error is raised"+e.responseText);
							        $('#errorBlock').append(e.responseText);
							}
						}); 
						
					
			     }
			  }
  
  

  </script>
  <script type="text/javascript">
  
  function setDetails(template_id,template_title,template_subject,template_body,template_status){
	  
      //alert(template_title+" "+template_id+"   "+template_subject+" "+template_body+" "+template_status);
	
      
	
		$('#templateId').val(template_id);
		$('#templateTitleId').val(template_title);
	
		$('#templateSubjectId').val(template_subject);
		
		 CKEDITOR.instances['editorId'].setData(template_body);
		$('#mailTemplateStatus').val(template_status);
		var status1=template_status;
	
		if(status1=='ACTIVE'){
		
		$('input:radio[id=optionsRadio1]').prop("checked",true);
		//document.getElementById("optionsRadio1").checked=true;
		}
		else{
			
			$('input:radio[id=optionsRadio2]').prop("checked",true);
		}
		
		$('#submitId').text("Update").removeClass("btn-success").addClass("btn-primary");
		
		
	 }
 
 
  
  </script>
  <script type="text/javascript">
     CKEDITOR.replace('editorId'); 
</script> 
  
</head>

<body>

 <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Message Template Page</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form  action="Mailmaster-Template.action" method="post" class="bs-example form-horizontal" id="formId" theme="simple">
                       <input type="hidden" id="templateId" name="mailmasterTemplateBO.template_id" value="" />
                          <div class="form-group">
                          <label class="col-lg-2 col-lg-offset-4 control-label">Message Title</label>
                          <div class="col-lg-5">
                            <input type="text" class="form-control" placeholder="Message Title" name="mailmasterTemplateBO.template_title" id="templateTitleId" maxlength="50"/>                            
                          </div>
                          </div> 
                          
                          <div class="form-group">
                          <label class="col-lg-2 col-lg-offset-4 control-label">Message Subject</label>
                          <div class="col-lg-5">
                            <input type="text" class="form-control" placeholder="Message Subject" name="mailmasterTemplateBO.template_subject" id="templateSubjectId" maxlength="50"/>                            
                          </div>
                          </div>       
                       
                       	
                          	<div class="form-group">
                          				<label class="col-lg-2 control-label">Message body</label>
                          				<div class="col-lg-10">
                            				<textarea id="editorId" name="mailmasterTemplateBO.template_body" class="ckeditor form-control"  rows="10" cols="80"> </textarea>
											<script>CKEDITOR.replace('mailmasterTemplateBO.template_body'); </script>
                          				</div>
                        	</div>
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-4 control-label">Status</label></label>
                        <div class="radioo i-checks col-lg-2">
                          <label class="control-label">
                            <input type="radio"  id="optionsRadio1" value="ACTIVE" checked name="mailmasterTemplateBO.template_status">
                             <i></i>
                            Active 
                          </label>
                        </div>
                        
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio"  id="optionsRadio2" value="INACTIVE" name="mailmasterTemplateBO.template_status">
                            <i></i>
                            
                            In active
                          </label>
                        </div>
                        </div>
                              <input type="hidden" value="" id="mailTemplateStatus" name="mailmasterTemplateBO.template_status"/>                             
                       
                                              
                        <div class="form-group">
                          <div class="col-lg-offset-6 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn  btn-success" onclick="formValidation()" id="submitId">Submit</button>
                           <!-- <input type="submit"  style="display: none;" id="submitForm"/>  -->
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="button" class="btn  btn-danger" onclick="resetValidation()">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
                <center> <span id="error"></span></center> 
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             
             
                 
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                 <div class="table-responsive" id="table_data">
                
                  <form id="formId2">
                  <table class="table table-striped m-b-none"  id="table">
                     <thead>
                      <tr>
                        <th><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th></th>
                        <th>S.N Testing o</th>
                        <th>Template Title</th>
                        <th>Template Subject</th>
                       <!--  <th width="25%">Template Body</th> -->
                        <th>Template Status</th>
                        <th></th>
                        
           
                         
                         </tr>
                    </thead>		
                     
					<tbody>
				
					
				  <s:iterator value="mailmasterTemplateList" status="row"> 
                    <tr>
                    <td><label class="checkbox m-n i-checks"><input type="checkbox" id="select"  name="template_ids" class="childBox" value='<s:property value="template_id"/>'><i></i></label></td>
                    <td><s:property value="#row.index+1"/></td>
                     <td><s:property value="template_title"/></td>
                     <td><s:property value="template_subject" /></td>
                    <%--  <td width="25%"><s:property value="template_body" /></td> --%>
                     <td><s:property value="template_status" /></td>
                     <td>
                     
                     <a class="btn btn-default" href="javascript:setDetails('<s:property value="template_id"/>','<s:property value="template_title"/>','<s:property value="template_subject"/>','<s:property value="template_body"/>','<s:property value="template_status"/>')">Edit</a>
                         </td>
                      </tr>
                    </s:iterator> 
						 
						 
					</tbody>
                  </table>
                  </form>
                </div>
                
                
                </section>
               <center><button type="button" class="btn btn-del" id="delete">Delete selected</button></center>
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
          <div id="errorBlock"></div>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
<script src="assets/js/datatables/jquery.dataTables.min.js"></script>


</body>
</html>
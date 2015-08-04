<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	 
	 
	 if($('#successMsg').val()!='' && $('#successMsg').val().length>0 ){
			
			 toastr.success($('#successMsg').val(), 'Success Info');
		}	

	if($('#errorMsg').val()!='' && $('#errorMsg').val().length>0 ){
		
		 toastr.error($('#errorMsg').val(), 'Error Info');
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
	  
	
	  
	  $('#maincheckbox').click(function(event) { 
		   $('.childBox').prop('checked',$(this).prop('checked'));
	  });
	  
	    
});


</script>
</head>
<body>
<input type="hidden" id="successMsg" value="${successMsg}"/>
<input type="hidden" id="errorMsg" value="${errorMsg}" />

        <div class="table-responsive" id="table_data">
                
                  <form id="formId2">
                  <table class="table table-striped m-b-none"  id="table">
                     <thead>
                      <tr>
                        <th><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                        <th>S.No</th>
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
                
</body>
</html>
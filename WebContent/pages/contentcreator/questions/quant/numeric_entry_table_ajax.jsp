<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script> --%>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<%-- <script src="assets/js/jquery.min.js"></script> --%>
<script type="text/javascript">
$(document).ready(function() { 
//alert("hai");


	var oTable = $('#sample2').dataTable( {

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
	 		      { "mData": "columnname9" },
	 		      { "mData": "columnname10" }
	 		      
	 		    ]
	 		  } );
	 		  
	
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
		// Display an success toast, with a title
		 toastr.success($('#successMsg').val(), 'Success Info');
	}	

	if($('#errorMsg').val()!='' && $('#errorMsg').val().length>0 ){
	// Display an error toast, with a title
	toastr.error($('#errorMsg').val(), 'Error Info');
	}  
	

	        });
</script>
</head> 
<body>
<div class="table-responsive">
<input type="hidden" id="successMsg" value="${successMsg}"/>
<input type="hidden" id="errorMsg" value="${errorMsg}" />	
               <form id="form1" method="post" action="" >
                  <table class="table table-striped b-t b-light" id="sample2">
                    <thead>
                      <tr>
                        <th ><label class="checkbox m-n i-checks"><input type="checkbox"><i></i></label></th>
                        <th >#</th>
                        <th >Test</th>
                        <th >Section</th>
                        <th >Category</th>
                        <th >Type</th>
                        
                        <th >Question Title</th>
                        <th >Access</th>
                        <th >Status</th>
                        <th ></th>
                        
                      </tr>
                    </thead>
                    <tbody>
                    
                     <s:iterator value="#request.data" status="status">
                     
                    <tr>
                    <td><label class="checkbox m-n i-checks"><input type="checkbox" name="deleteRecords" value="<s:property value="question_id"/>"><i></i></label></td>
                    <td><s:property value="#status.index+1"/></td>
                    <td><s:property value="test_name"/></td>
                    <td><s:property value="section_name"/></td>
                    <td><s:property value="category_name"/></td>
                    <td><s:property value="question_type"/></td>
                    
                    <td><s:property value="question_title"/></td>
                    <td><s:property value="access_type"/></td>
                    <td><s:property value="status"/></td>
                    <td><a href="javascript:update('<s:property value="question_id"/>')" class="btn btn-default" >Edit</a> </td>
                   
                    </tr>
                               
                    </s:iterator> 
                    
                    </tbody>
                  </table>
                  </form>
                </div>
<script type="text/javascript">
       
        </script>
</body>

</html>
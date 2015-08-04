<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<title>Insert title here</title>
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
		      { "mData": "columnname4" }
		      
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
	  
	  
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=orientation_id]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=orientation_id]').each(function () {
	    			//alert("calling");
	    			//alert($(this).attr("id"));
	    			  $(this).prop('checked',false);
	    	});
	    	}
	        
	    });
	    
	    
});


</script>
</head>
<body>
<input type="hidden" id="successMsg" value="${successMsg}"  />
<input type="hidden" id="errorMsg" value="${errorMsg}" />
    <div class="table-responsive" id="table_data">
                <form id="form2">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>                        
                        <th>S.No</th>
                        <th>Question</th>
                        <th class="text_center">Edit</th>
                        <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="orientationList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><%-- <s:property value="question"/> --%>
										<s:text name="question"/></td>
										
										<td align="center"><a class="btn btn-default" 	href="javascript:editDetails('<s:property value="orientation_question_id"/>',
										'<s:property value="question_type"/>','<s:property value="question"/>','<s:property value="instructions"/>',
										'<s:property value="choice1"/>','<s:property value="choice2"/>','<s:property value="choice3"/>',
										'<s:property value="choice4"/>','<s:property value="choice5"/>','<s:property value="choice6"/>',
										'<s:property value="choice7"/>','<s:property value="choice8"/>','<s:property value="choice9"/>','<s:property value="choice10"/>')">Edit</a>
										
										</td>
										<td class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value="<s:property value="orientation_question_id"/>" name="orientation_id"/><i></i></label></td>
										 
										
										
									</tr>
					</s:iterator>
                    </tbody>
                  </table>
                  </form>
                
                </div>
</body>
</html>
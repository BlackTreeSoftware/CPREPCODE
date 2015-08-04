<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<title>AddDataInterpretation</title>

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
	      { "mData": "columnname4" },
	      { "mData": "columnname5" },
	      { "mData": "columnname6" },
	      { "mData": "columnname7" },
	      { "mData": "columnname8" }
	    
	    ]
	  } );
	
	 if($('#successMsg').val()!='' && $('#successMsg').val().length>0 ){
			
			 toastr.success($('#successMsg').val(), 'Success Info');
		}	

	if($('#errorMsg').val()!='' && $('#errorMsg').val().length>0 ){
		
		 toastr.error($('#errorMsg').val(), 'Error Info');
	}  

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
			<table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                          <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                        <th>S.No</th>
                        <th>Graph Title</th>
                        <th>Graph</th>
                        <th>Difficulty Level</th>
                        <th>Access Type</th>
                         <th>Graph Status</th>
                        <th class="text_center">Edit</th>
                       
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="dataInterpretionList" status="status">
									<tr >
										<td class="chck" style="padding-bottom: 1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" name="dataId" class="childBox"  value='<s:property value="graph_id"/>'><i></i></label></td>
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="graph_title"/></td>
						                <td><s:text name="graph"/></td>
						                <td><s:property value="difficulty_name"/></td> 
						                <td><s:property value="access_type"/></td>
						                <td><s:property value="graph_status"/></td>
										<td align="center">
										<a href="javascript:setDetails('<s:property value="graph_id"/>','<s:property value="graph_title"/>','<s:property value="graph"/>','<s:property value="difficulty"/>','<s:property value="access_type"/>','<s:property value="graph_status"/>')"><button type="button" class="btn  btn-default" >Edit</button></a></td>
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
		</form>
	</div>

</body>
</html>
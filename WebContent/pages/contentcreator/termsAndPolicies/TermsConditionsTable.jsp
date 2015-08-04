<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {	
	//alert("hai");
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
	
 		var oTable = $('#policy').dataTable( {

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
    <body>
                 <div class="table-responsive" id="ajax-table">
                  <table class="table table-striped m-b-none" data-ride="datatables" id="policy">
                     <thead>
                      <tr>
                        <th>S.No</th>
                        <th>Select</th>
                        <th>Terms</th>
                        <th>Conditions</th>
                        <th>Status</th>
                        <th></th>
                        </tr>
                    </thead>		

					<tbody>
						 <s:iterator value="policyBOList" status="row"> 
                    <tr>
                     <td><s:property value="#row.index+1"/></td>
                     <td><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="policy_id"/>'><i></i></label></td>
                     <td><s:text name="policy_terms"/></td>
                     <td><s:text name="policy_conditions"/></td>
                     <td><s:property value="status" /></td>
                     <td><a class="btn btn-default" href="javascript:setDetails('<s:property value="policy_terms"/>','<s:property value="policy_conditions"/>','<s:property value="policy_id"/>','<s:property value="status"/>')" >EDIT</a></td>
                     
                     </tr>
                    </s:iterator> 
					</tbody>
                  </table>
                </div>
</body>
</html>
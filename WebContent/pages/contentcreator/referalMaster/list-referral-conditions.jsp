<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var oTable = $('#referral')
								.dataTable(
										{

											"bProcessing" : true,

											"sDom" : "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
											"sPaginationType" : "full_numbers",
											"aoColumns" : [ {
												"mData" : "columnname1"
											}, {
												"mData" : "columnname2"
											}, {
												"mData" : "columnname3"
											}, {
												"mData" : "columnname4"
											},
											{
												"mData" : "columnname5"
											},
											{
												"mData" : "columnname6"
											}

											]
										});
						
						  $('#selectAll').click(function() {
							//  alert("checked");	
						    	if($('#selectAll').prop('checked')) {
						    		// alert("checked");		    		 
						    		
						    		$('input[id=select]').each(function () {
						    			//alert("calling");
						    			// alert($(this).attr("id"));
						    			  $(this).prop('checked', true);
						    	});
						    	} else {
						    		// alert("Un checked");
						    		$('input[id=select]').each(function () {
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



 <div class="table-responsive" id="referral-condition-panel">
 <form id="deleteConditions">
                  <table id="referral" class="table table-striped m-b-none" data-ride="datatables">
                    <thead>
                      <tr>
                      <th><label class="checkbox m-n i-checks"><input  type="checkbox" id="selectAll" name="selectAll"/><i></i></label> </th>
                        <th>Sr.No</th>
                        <th>Condition Title</th>
                        <th>Condition</th>
                          <th>Status</th>
                        <th>Edit</th>	
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="conditionBOs" status="status">
                    <tr>
                     <td><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="condition_id"/>' name="deleterecords"/><i></i></label></td>
                    <td><s:property value="#status.index+1"/></td>
                    <td><s:property value="condition_title"/></td>
                    <td><s:property value="condition"/></td>
                    <td><s:property value="status"/></td>
                     <td><a class="btn btn-default" href="javascript:setDetails('<s:property value="condition_title"/>','<s:property value="condition"/>','<s:property value="status"/>','<s:property value="condition_id"/>')">EDIT</a></td>
                   
                    </tr>
                    </s:iterator>
                    </tbody>
                  </table>
                </form>
                </div>
                </body>
</html>
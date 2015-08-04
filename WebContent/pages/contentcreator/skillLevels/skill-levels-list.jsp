<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//alert("hai");
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
						
						  $('#selectAll').click(function() {
							//  alert("checked");	
						    	if($('#selectAll').prop('checked')) {
						    		 //alert("checked");		    		 
						    		
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

	

			<div class="table-responsive" id="skill-levels">
				<table id="table" class="table table-striped m-b-none"
					>
					<thead>
						<tr>
							<th><label class="checkbox m-n i-checks"><input type="checkbox" class="form-control" id="selectAll"
									><i></i></label></th>
							<th>Sr.No</th>
							<th>Skill</th>
							<th><center>Edit</center></th>

						</tr>
					</thead>
					<tbody>
						<s:iterator value="skills" status="status">
							<tr id="tr<s:property value="skill_id"/>">
								<td><label class="checkbox m-n i-checks"><input type="checkbox" class="form-control" id="select"
									value='<s:property value="skill_id"/>'><i></i></label></td>
								<td><s:property value="#status.index+1" /></td>
								<td><s:property value="skill_name" /></td>
								<td align="center"><a class="btn btn-default" href="javascript:setDetails('<s:property value="skill_name"/>','<s:property value="skill_id"/>','<s:property value="SectionId"/>')">Edit</a>
								</td>

							</tr>


						</s:iterator>
					</tbody>
				</table>

			</div>
		
		<!--col for DataTable close-->

	

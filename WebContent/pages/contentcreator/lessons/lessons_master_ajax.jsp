<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		      { "mData": "columnname4" },
		      { "mData": "columnname5" },
		      { "mData": "columnname6" },
		      { "mData": "columnname7" }
		      
		    ]
		  } );
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=delete]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=delete]').each(function () {
	    			//alert("calling");
	    			//alert($(this).attr("id"));
	    			  $(this).prop('checked',false);
	    	});
	    	}
	        
	    });
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
   <!-- Input HiddenFields -->
  <input type="hidden" name="successMsg" value="${succesMsg}" id="successMsg">
   <input type="hidden" name="errorMsg" value="${errorMsg}" id="errorMsg">
  
       <div class="table-responsive" id="table_data">
                  <table class="table table-striped m-b-none"  id="table">
                     <thead>
                      <tr>
                       <th><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                        <th >S.No</th>
                        <th >Section</th>
                        <th >Lesson</th>
                        <th  style="display : none">LessonId</th>
                        <th  style="display : none">sectionId</th>
                        <th >Edit</th>
                       
                         
                         </tr>
                    </thead>		

					<tbody>
						 <s:iterator value="lessons_list" status="row"> 
                    <tr>
                     <td  ><label class="checkbox m-n i-checks"><input type="checkbox" name="delete" id="<s:property value="#row.index+1"/>"/><i></i></label></td> 
                     <td ><s:property value="#row.index+1"/></td>
                     <td ><s:property value="sectionName"/></td>
                     <td ><s:property value="lessonName" /></td>
                     <td style="display : none" id="lesson<s:property value="#row.index+1"/>"><s:property value="lessonId" /></td>
                       <td style="display : none"><s:property value="sectionId"/></td>
                     <td ><button type="button" class="btn btn-sm btn-default" onclick="editTable('<s:property value="sectionId"/>','<s:property value="lessonName" />','<s:property value="lessonId" />')">EDIT</button></td>
                     
                      </tr>
                    </s:iterator> 
					  
						 
					</tbody>
                  </table>
                
                </div>
</body>
</html>
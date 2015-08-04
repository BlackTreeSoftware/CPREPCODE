<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />

<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
<script src="assets/js/jquery.min.js"></script>
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
    <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
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
		      { "mData": "columnname4" }
		    ]
		  } );
	  
	  
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=userid]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=userid]').each(function () {
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

<input type="hidden" id="successMsg" value="${successMsg}"/>
<input type="hidden" id="errorMsg" value="${errorMsg}" />

        
                
                <div class="table-responsive" id="table_data">
                
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Student</th>
                        <th>Mentor</th>
                        
                        <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="finalList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="studentName"/></td>
										<td><s:property value="mentorName"/></td>										
										<td class="chck" style="padding-bottom: 1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value="<s:property value="user_id"/>" name="userid"/><i></i></label></td>
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  
                  
                
                </div>
                
               
             

</body>
</html>

<%@ taglib prefix="s" uri="/struts-tags" %>    
 
<%-- <script src="assets/js/jquery.min.js"></script>  --%>
<script src="assets/js/datatables/jquery.dataTables.min.js"></script> 
<%-- <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script> --%>
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<!-- <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />  
<link rel="stylesheet" href="assets/js/chosen/chosen.css" type="text/css" />
<link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" /> -->
<!-- <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>  -->

<%-- <script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script src="assets/js/chosen/chosen.jquery.min.js"></script>
<script src="assets/js/select2.1/select2.min.js"></script> --%>
<%-- <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>  --%>
<script type="text/javascript">
        $(document).ready(function() {  
        	
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
        	
         var oTable = $('#sample').dataTable( {

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
 		      { "mData": "columnname10" }, 
 		      { "mData": "columnname11" },
 		    ]
 		  } ); 
         
         if ($('#successInfo').val() != '' && $('#successInfo').val().length > 0) { 
 			toastr.success($('#successInfo').val(),'Success Info');
 		}
 		if ($('#errorInfo').val() != '' && $('#errorInfo').val().length > 0) {
 			toastr.error($('#errorInfo').val(),'Error Info');
 		} 
        }); 
</script>
 
 
         <%--  <section class="vbox">
            <section class="scrollable padder"> --%>
              
                <%-- <section class="panel panel-default">
                <header class="panel-heading">
                  Problem Solving Questions  
                  <i class="fa fa-info-sign text-muted" data-toggle="	tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                </header>
                <div class="row wrapper">
                  <div class="col-sm-5 m-b-xs">
                      <a href="#" class="btn btn-rounded btn-sm btn-success" onclick="addRecord()">Add Record</a>  
                      <a href="#" class="btn btn-rounded btn-sm btn-danger" onclick="deleteRecord()">Delete Record</a>              
                  </div>
                  <div class="col-sm-4 m-b-xs">
                    
                  </div>
                  <div class="col-sm-3">
                    
                  </div>
                </div> --%>
               <div class="table-responsive">
               <input type="hidden" id="successInfo" name="" value="${successMsg}" />
               <input type="hidden" id="errorInfo" name="" value="${errorMsg}" />    
               <form id="problem_solving_table">
                  <table class="table table-striped b-t b-light" id="sample">
                    <thead>
                      <tr>
                        <th ><label class="checkbox m-n i-checks"><input type="checkbox" ><i></i></label></th>
                        <th >#</th>
                        <th >Test</th>
                        <th >Section</th>
                        <th >Category</th>
                        <th >Type</th>   
                        <th >Question Title</th>
                        <th >Access</th>
                        <th >Referral Status</th>
                        <th >Status</th>
                        <th ></th>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="questionsList" status="status" id="queslist">  
                     <tr>
                    <td><label class="checkbox m-n i-checks"><input type="checkbox" name="recordsToDelete" value="<s:property value="question_id"/>"><i></i></label></td>
                    <td><s:property value="#status.index+1"/></td>
                    <td><s:property value="test_name"/></td>
                    <td><s:property value="section_name"/></td>
                    <td><s:property value="category_name"/></td> 
                    <td><s:property value="question_type"/></td>  
                    <td><s:property value="question_title"/></td> 
                    <s:if test="#queslist.refferal == 'YES'">
                      <td></td> 
                      <td>Referral</td> 
                    </s:if>
                    <s:else>
                      <td><s:property value="access_type"/></td>
                      <td></td>
                    </s:else>
                    <td><s:property value="status"/></td> 
                    <td><a href="#" class="btn  btn-default" onclick="editRecord(<s:property value="question_id"/>)">Edit</a> </td>  
                    </tr>
                    </s:iterator> 
                    
                    </tbody>
                  </table>
                  </form>
                </div>

             <%--  </section>  --%> 
          <%--   </section>
          </section>   --%>
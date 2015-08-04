  <%@taglib prefix="s" uri="/struts-tags"%> 
   <%-- <script src="assets/js/datatables/jquery.dataTables.min.js"></script> 
   <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" /> --%>
   <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
  <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
  <script type="text/javascript">
  $(document).ready(function () {  
	  var oTable = $('#sublessons_table_ajax').dataTable( {

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
	  if ($('#success').val() != '' && $('#success').val().length > 0) {
			toastr.success($('#success').val(),'Success Info');
		}
		if ($('#error').val() != '' && $('#error').val().length > 0) {
			toastr.error($('#error').val(),'Error Info');
		}
  });
  
</script> 
  <style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
  <div class="row" >
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
              <input type="hidden" id="success" name="" value="${successMsg}" />
              <input type="hidden" id="error" name="" value="${errorMsg}" />    
             <form id="deleteForm">
             <section class="scrollable padder">
             
              <section class="panel panel-default">                
                
                 <div class="table-responsive">
                  <table class="table table-striped m-b-none" id="sublessons_table_ajax" >
                     <thead>
                     <tr>   
                        <th>Lesson</th>
                        <th>Skill 1</th>
                        <th>Skill 2</th>
                        <th>Skill 3</th>
                        <th>Edit</th>  
                        <th>Delete</th>
                      </tr>
                    </thead>		

					<tbody>
						 
                    <s:iterator value="subLessons">
                    <tr> 
                    <td><s:property value="sub_lessonname"/></td>
                    <td><s:property value="skillname1"/></td>
                    <td><s:property value="skillname2"/></td> 
                    <td><s:property value="skillname3"/></td>   
                    <td><button class="btn btn-default" type="button" onclick="editRecord('<s:property value="sectionId"/>','<s:property value="lessonId"/>','<s:property value="sub_lessonname"/>','<s:property value="lesson_text"/>','<s:property value="lesson_video"/>','<s:property value="access_type"/>','<s:property value="status"/>','<s:property value="skillid1"/>','<s:property value="skillid2"/>','<s:property value="skillid3"/>','<s:property value="sublesson_id"/>','<s:property value="parent_id"/>','<s:property value="parent_lesson_name"/>','<s:property value="lesson_type"/>')">Edit</button></td>
                    <td class="chck" style="padding-top:3px"><label class="checkbox m-n i-checks"><input type="checkbox" value="<s:property value="sublesson_id"/>" name="subLessonBO.recordsToDelete"><i></i></label></td>  
                    </tr>
                    </s:iterator>   
					</tbody>
                  </table>
                </div>
                
                </section>
                 <center><a href="#" class="btn btn-del" onclick="deleteSubLessons()">Delete selected</a></center>
              </section><!--sec close for data table-->
              </form>
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div>
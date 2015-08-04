<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddDataInterpretation Graph</title>

<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
<script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script src="assets/plug-ins/ckfinder/ckfinder.js"></script>      
<script src="assets/js/jquery.min.js"></script>
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>



<script type="text/javascript">

$(document).ready(function(){
	//alert("Hi");
	 $('#checkBoxValue').attr('value','PAID');
	 //$('#resetId').click();
	 //alert("111");
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
	
	$('#difficultyLevls').append($('#difficultyLevel').val());
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


	
	$('#graphtitleId').keyup(
			function() {
				var actualScore = $("#graphtitleId")
				.val();
		var len = actualScore.length;
		 
				if (this.value.match(/[^a-zA-Z” ”]/g)) {
					this.value = this.value.replace(
							/[^a-zA-Z” ”]/g, '');
					toastr.warning("Numeric Characters are not accepted");
					 $('#graphtitleId').focus();
				}
				else if (len > 50) {
					$("#graphtitleId").val('');
					//$('#contact').attr('value','');
					toastr.warning("Graph Name should not exceed 50 characters");
				}
			});

$('#delete').click(function(){
	
	 var childBoxLength=$(".childBox:checked").length; 
	 //  alert("checkboxlength:"+childBoxLength);
	   if(childBoxLength==0){
		   
		   toastr.warning("No  Template Seleced");
		   
	   }
	  else{
	  var formData = $('#formId2').serialize();
	  
		 $.ajax({
			type : 'POST',
			url : 'DataInterpretion-Delete.action', 
			data : formData,
			success :  function deleteSuccess(data, status) {
				//alert(data);
				 $('#table_data').html(data);
				 $('#templateTitleId').val("");
	    		 $('#templateSubjectId').val("");
	    		 $('#maincheckbox').click(function(event) { 
	    			   $('.childBox').prop('checked',$(this).prop('checked'));
	    		 });
	    			
	    		
				},
			    error : function deleteFailed(errorIs) {
				alert(' Failed ' + errorIs.responseText);
			}

		});
		 
		
	 }
	  
	 
	
});

$('#resetId').click(function(){
	 // alert("Hi");
	  $('#graphtitleId').val('');
	  CKEDITOR.instances['editorId'].setData("");
	  var difficultyLevls=$('#difficultyLevls').val(-1);
	  $("#checkbox1").prop("checked",false);
	  $("#optionsRadio3").prop("checked",true);
	  $('#submitId').text("Submit").removeClass("btn-primary").addClass("btn-success");
	
});


$('#checkbox1').click(function(){
	var checked=$(this).prop("checked");
	if(checked){

		   $('#checkBoxValue').attr('value','FREE');
		  
		  }
	  else{
		  
		  $('#checkBoxValue').attr('value','PAID');
	  }
	
	
});
	
$('#submitId').click(function(){
	
	  var graphTitle=$('#graphtitleId').val();
	  var ckeditorValue= CKEDITOR.instances['editorId'].getData(); 
	  var diffLevel=$('#difficultyLevls').val();
	  var graphStatus=$(":radio:checked").val();
	  if(graphTitle==''){
		  toastr.error("Enter Graph Title");
		  $('#graphtitleId').focus();
	    	 return false;
	  }else if(ckeditorValue==''){
		  toastr.error("Enter CkEditor value");
		  CKEDITOR.instances['editorId'].getData().focus();
	    	 return false;
	  }else if(diffLevel=='-1'){
		  toastr.error("Please Select Difficulty Level");
		  $('#diffId').focus();
	    	 return false;
	  }else{
		 //$('#graphText').attr('value',CKEDITOR.instances['editorId'].getData());
		 $('#editorId').val(ckeditorValue);
		  var formData = $('#formId1').serialize();
		  $.ajax({
				type : 'POST',
				url : 'DatInterpretation-Add.action', 
				data : formData,
				success :  function deleteSuccess(data, status) {
					 $('#resetId').trigger("click");
					 $('#table_data').html(data);
					 $('#templateTitleId').val("");
		    		 $('#templateSubjectId').val("");
		    		 $('#optionsCheckbox1').val("");
		    		 
					},
				    error : function deleteFailed(errorIs) {
				
					toastr.error(' Failed ' + errorIs.responseText);
				}
					

			});
		  
	  }

});


});

function setDetails(graph_id,graph_title,graph,difficulty,access_type,graph_status){

	  $('#graphId').val(graph_id);
	  $('#graphtitleId').val(graph_title);
	  CKEDITOR.instances['editorId'].setData(graph);
	  $('#difficultyLevls').val(difficulty);
	   var graphStatus=graph_status;
	   if(access_type=='FREE'){
		   
		   $("#checkbox1").prop("checked","checked");
		  
		 }
	   else{
		   $("#checkbox1").prop("checked",false);
	   }
	     if(graphStatus=='ACTIVE'){
	    	 
	    	 $('input:radio[id=optionsRadio3]').prop("checked",true);
	    	 
	     }else {
			 
			 
			 $('input:radio[id=optionsRadio4]').prop("checked",true);
			 
		 }
	
	     $('#submitId').text("Update").removeClass("btn-success").addClass("btn-primary");
}


</script>

</head>
<body>
 <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"><span class="panel-title"> Add Data Interpretation Graph</span></div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form class="bs-example form-horizontal" method="post" action="Data-Interpretation" id="formId1">                                                   
                          <input type="hidden" id="graphId" name="dataInterpretionBO.graph_id" value="0"/>  
                         <input type="hidden" id="checkBoxValue" name="dataInterpretionBO.access_type"/>  
                           <input type="hidden" id="difficultyLevel" value="${request.difficultyLevels}"/> 
                             <s:hidden id="graphText" name="%{dataInterpretionBO.graph}"/>                   
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Graph title</label>
                                  <div class="col-lg-5">
                                    <input type="text" class="form-control" placeholder="Graph Title" name="dataInterpretionBO.graph_title" id="graphtitleId" maxlength="60" data-required="true" data-trigger="change">                            
                                  </div>
                             </div>                              
                             
                            <div class="form-group">
                          				<label class="col-lg-2 control-label">Graph</label>
                          				<div class="col-lg-10">
                            				<textarea id="editorId" name="dataInterpretionBO.graph" rows="10" cols="80"> </textarea>
											<script> CKEDITOR.replace('dataInterpretionBO.graph'); </script>
                          				</div>
                        	</div>
                                
                             <%-- <div class="form-group">
                                <label class="col-lg-2 col-lg-offset-4 control-label">Upload Graph Image</label>
                                <center>   
                                <div class="col-lg-4">                     	                			
                    			<div class="fileinput fileinput-new" data-provides="fileinput">
      							<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
 								<div>
        			
                    			<span class="btn btn-success btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                    			<input type="file" name="..."></span>
                    
        						<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                <a href="#" class="btn btn-primary fileinput-exists" data-dismiss="fileinput">Upload</a>
                                <p class="text-info"> sample text for URL</p>
                       			</div>
    							</div>
                                </div>
                              	</center>
                                
                        	</div>     --%>
                             
                             
                             <div class="form-group">

                                 <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level</label>
                                    <div class="col-lg-5">
                                   <%--  <s:select name="dataInterpretionBO.difficulty" cssClass="form-control m-b" list="difficultyLevellist" listValue="difficulty_name" listKey="difficulty_id" id="diffId" headerKey='-1' headerKeyValue="Select Difficulty Level"/> --%> 
                                    <select  class="form-control m-b" id="difficultyLevls" name="dataInterpretionBO.difficulty">
                                      
                                     </select>
                                    
                                    </div>
 
                             </div><!--Form group close-->                                                   	                                  
                       	
                       
                       		 <div class="form-group">
                       				
                                <label class="col-lg-2 col-lg-offset-4 control-label">Type</label></label>     
                               <div class="radioo i-checks col-lg-2">
                                  <label>
                                  <!--  <input type="radio"  id="optionsRadio1" value="FREE" checked name="dataInterpretionBO.access_type"> -->
                                   <input type="checkbox" id="checkbox1">
                                    <i></i>
                                   Free
                                  </label>
                                </div>
                          </div><!--form group-->                                                              	                                             
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-4 control-label">Status</label></label>
                        
                               <div class="radioo i-checks col-lg-2">
                                  <label>
                                       <input type="radio"  id="optionsRadio3" value="ACTIVE"  checked name="dataInterpretionBO.graph_status">
                                    <i></i>
                                   Active
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                      <input type="radio"  id="optionsRadio4" value="INACTIVE" name="dataInterpretionBO.graph_status">
                                    <i></i>
                                    In Active
                                  </label>
                                </div>
                        </div>
                                            
                        <div class="form-group">
                          <div class="col-lg-offset-6 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                          
                          <button type="button" class="btn  btn-success" id="submitId">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                          
                             <button type="button" class="btn  btn-danger" id="resetId">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
               <center> <span id="error"></span></center> 
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
          	<div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
              <div class="table-responsive" id="table_data">
          <%--     Iterator SIze is: <s:property value="%{#dataInterpretionList.size}"/> --%>
		    <form id="formId2">
			 <table class="table table-striped b-t b-light" id="table">
				<thead>
					<tr>
						<th><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
						<th>S.No</th>
						<th>Graph Title</th>
						<th>Graph</th>
						<th>Difficulty Level</th>
						<th>Access Type</th>
						<th>Graph Status</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
				
					<s:iterator value="dataInterpretionList" status="row">

					<tr>
						<th><label class="checkbox m-n i-checks"><input type="checkbox" id="select" name="dataId"  class="childBox"  value='<s:property value="graph_id"/>'><i></i></label></th>
						<td><s:property value="#row.index+1"/></td>
						<td><s:property value="graph_title"/></td>
						<td><s:text name="graph"/></td>
						 <td><s:property value="difficulty_name"/></td> 
						<td><s:property value="access_type"/></td>
						<td><s:property value="graph_status"/></td>
						
						<td><a class="btn btn-default" href="javascript:setDetails('<s:property value="graph_id"/>','<s:property value="graph_title"/>','<s:property value="graph"/>','<s:property value="difficulty"/>','<s:property value="access_type"/>','<s:property value="graph_status"/>')">Edit</a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table> 
		</form>
	</div>


                </section>
                <center><button type="button" class="btn btn-del" id="delete">Delete selected</button></center>
              </section><!--sec close for data table-->
              
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
            </section>
         </section>
         </section>
    <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
	<script src="assets/js/datatables/demo.js"></script>
        



</body>
</html>
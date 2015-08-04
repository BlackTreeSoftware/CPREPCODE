<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Web Application</title>  
   
 <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
<script src="assets/js/jquery.min.js"></script>
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
    
  <script type="text/javascript">
  $(document).ready(function(){
	  
	 
		
	  
	 /* var tableData=$('#tableData').val();
	 //alert(' json Data is; '+tableData);
	 var jsonTableData=JSON.parse(tableData);
	 //alert(' Is Data Avilable is: '+jsonTableData.isDataAvilable);
	 var data=jsonTableData.tableData; */
	 //alert($('#tableData').val());
	 var JSONObj=$.parseJSON($('#tableData').val());
	 /*   var arr1=JSONObj.tableData[0].question_no;
	   alert(arr1);  */  
	var data=JSONObj.tableData;
	   	 
	 if(data==null || data=='undefined'){
		 tableCss();
	 }
	 else{
		 
	 var table=$("#table");
	 table.find("tr:gt(0)").remove();
	 var i=0;
	 //alert(" Data is: "+data[i].question_id);
	  $.each(data, function() {
		 
			var rowNew="";
		 // alert(" Data is: "+data.isAllowToDelete);
		/* if(data.isAllowToDelete!='YES'){
		  rowNew = $('<tr id=tr'+data.question_id+'><td><label class="checkbox m-n i-checks"><input type="checkbox"   class="questionsCheckBox"  value="'+data.question_id+'"/><i></i></label></td><td>'+parseInt(index+1)+'</td><td>'+data.section_name+'</td><td>'+data.category_name+'</td><td>'+data.test_name+'</td><td>'+data.question_title+'</td><td>'+data.question_directions+'</td><td><input style="display: inline-block;padding: 6px 12px;margin-bottom: 0;font-size: 14px;font-weight: normal;line-height: 1.42857143;text-align: center;white-space: nowrap;vertical-align: middle;cursor: pointer;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;background-image: none;border: 1px solid transparent;border-radius: 0px;color: #333;background-color: #fff; border-color: #ccc;" type="button" value="EDIT"  class="btn" onClick=questionIdPass('+data.question_id+')></td></tr>');
		}
		else{
			rowNew = $('<tr id=tr'+data.question_id+'><td><label>Already In Use</label></td><td>'+parseInt(index+1)+'</td><td>'+data.section_name+'</td><td>'+data.category_name+'</td><td>'+data.test_name+'</td><td>'+data.question_title+'</td><td>'+data.question_directions+'</td><td><input style="display: inline-block;padding: 6px 12px;margin-bottom: 0;font-size: 14px;font-weight: normal;line-height: 1.42857143;text-align: center;white-space: nowrap;vertical-align: middle;cursor: pointer;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;background-image: none;border: 1px solid transparent;border-radius: 0px;color: #333;background-color: #fff; border-color: #ccc;" type="button" value="EDIT"  class="btn" onClick=questionIdPass('+data.question_id+')></td></tr>');
		} */
		
		rowNew = $('<tr id=tr'+data[i].question_id+'>'+
				 '<td><label class="checkbox m-n i-checks">'+
				 '<input  type="checkbox"   class="questionsCheckBox"  value="'+data[i].question_id+'"/><i></i></label></td>'+
				 '<td>'+parseInt(i+1)+'</td><td>'+data[i].section_name+'</td>'+
				 '<td>'+data[i].category_name+'</td><td>'+data[i].question_type_name+'</td>'+
				 '<td>'+data[i].question_title+'</td><td>'+data[i].question_directions+'</td>'+
				 '<td><input class="btn" style="display: inline-block;padding: 6px 12px;margin-bottom: 0;font-size: 14px;font-weight:'+
					 'normal;line-height: 1.42857143;text-align: center;white-space: nowrap;vertical-align: middle;cursor: pointer;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;background-image: none;border: 1px solid transparent;border-radius: 0px;color: #333;background-color: #fff; border-color: #ccc;" type="button" value="EDIT" onClick="questionIdPass('+data[i].question_id+')"></td>'+
				 '</tr>');
		
		  rowNew.appendTo(table); 
		  ++i;
		}); 
	  tableCss();
	 
	  
	 }
	  $('#maincheckbox').click(function(){
		//$('.questionsCheckBox').not(':disable').prop('checked',$(this).prop('checked'));
		  /* $('.questionsCheckBox').each(function(){
			 alert(' this .prope'+$(this).prop('disabled')); 
		  }); */
	  });
	  $('#maincheckbox').prop('checked',false);
	
	  
	  var messsage='<s:property value="#request.alertMessage"/>';
	  if(messsage!='')
		  {
		  toastr.success(messsage, "Operation Status");
		  }
	  
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
			};
  });
  function tableCss(){
		 var oTable = $('#table')
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
						},
						{
							"mData" : "columnname7"
						},
						{
							"mData" : "columnname8"
						}
						

						]
					});
		  
	 }
  </script>
  <script type="text/javascript">
  function questionIdPass(questionId)
  {
	  $('#questionId').attr('value',questionId);
	  $('#formSumission').trigger('click');
	  
  }
  
  </script>
  <script type="text/javascript">
  
  function questionsDeletion(){
	 
	  var matches = [];
	  $(".questionsCheckBox:checked").not(':disabled').each(function() {
	      matches.push(this.value);
	  });    
	 
	  if(matches!='')
		 {
		  
		  $.ajax({
				tye:'POST',
				url:'deleteSelectedQuestions.action',
				data:'deleteList='+matches,
				dataType:'json',
				success:deleteOperationStatus,
				errror:deleteOperaionFailed
				
			});
		// alert(' Slected ID is: '+matches);  
		 /* Message.deleteSelectedQuestions(matches,function(data) {
			 alert(' Data is: '+data);
				dwr.util.setValue("deleteStatus", data);
			  }); */
		 }
	 else{
		 toastr.warning("Please Select atleast one Question", " No questions are selected ");
	 }
 }
  function deleteOperationStatus(jsonData,status)
  {
	
	  if(jsonData.isExceptionRaised)
		  {
		  toastr.error(jsonData.exceptionIs, "Delete Operation Failed");
		  }
	  else{
		  if(jsonData.deleteStatus=='Deleted Successfully')
			  {
			  
			  /* $(".questionsCheckBox:checked").each(function(key,value) {
			      $('#tr'+$(value).val()).prop('checked',false).remove();
			  }); */
			  location.reload();
			  }
		  
		  toastr.success(jsonData.deleteStatus, "Delete Operation Status");
	  }
	  
  }
  function deleteOperaionFailed(errorIs){
	  toastr.error(errorIs.responseText, "Operation Failed");
  }
  </script>
  
  
</head>
<body class="">
   <s:hidden id="tableData" value="%{#request.tableData}"/>

        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12"  style="padding-top: 10px;">
             
            <%--  <div class="panel panel-default">
             <div class="panel-heading"> <s:hidden id="deleteStatus"/></span>  </div>
             <div class="panel-body"> --%>
              <s:if test="%{#request.operation=='Sentence Equivalence'}">
             <s:form action="SentenceEquivalence.action?sectionId=2" method="post" id="hiddenForm">
             <s:hidden value="" id="questionId" name="questionId"/>
             <input type="submit" style="display: none;" id="formSumission">
             </s:form>
             </s:if>
             <s:else>
             <s:form action="TextCompletion.action?sectionId=2" method="post" id="hiddenForm">
              <s:hidden value="" id="questionId" name="questionId"/>
                 <input type="submit" style="display: none;" id="formSumission">
             </s:form>
             </s:else>
           
             
            <!--  <div class="row"> -->
             
                <s:if test="%{#request.operation=='Sentence Equivalence'}">
             <a class="btn btn-default" href="SentenceEquivalence.action?sectionId=2" class="auto">
                                               
                                                <span>Add New</span>
                                              </a>
                                              </s:if>
                                              <s:else>
                                              <a class="btn btn-default" href="TextCompletion.action?sectionId=2" class="auto">
                                              
                                                <span>Add New</span>
                                              </a>
                                              </s:else>
                                                <br><br>
            <!--  <div class="col-lg-12" style="padding-top:20px;"> -->
             
             <section class="scrollable padder">
             
              <section class="panel panel-default"> 
              <header class="panel-heading">
                                  <s:property value="#request.operation"/> 
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>               
                 <div class="table-responsive" id="table_data">
                  <table class="table table-striped m-b-none"  id="table">
                     <thead>
                      <tr>
                       <th><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                        <th >S.No</th>
                        <th >Section</th>
                        <th >Category </th>
                        <th>Test Name</th>
                        <th >Question Title</th>
                        <th> Question Direction</th>
                       <th> Edit</th>
                         </tr>
                    </thead>		
			<tbody>
					</tbody>
                  </table>
                </div>
                </section>
             <center> <a href="javascript:questionsDeletion()" class="btn btn-del" >Delete selected</a></center>
              </section><!--sec close for data table-->
              <hr>
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
               <!--  </div>panel body close             
             </div>panel head close
             
             </div>div close
             </div>row start main End -->
        
            </section>
          </section>
          
        </section> 

  
  
  
 <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
	<script src="assets/js/datatables/demo.js"></script>
   
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>

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
		      { "mData": "columnname6" }
		    ]
		  } );
	  
	  
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=action_id]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=action_id]').each(function () {
	    			//alert("calling");
	    			//alert($(this).attr("id"));
	    			  $(this).prop('checked',false);
	    	});
	    	}
	        
	    });
	    

	 // alert("dsf");
	  $('#save').click(function(){
		  //alert("hai save");
		  var type=$('#type').val();
		 
		  //alert(name+"   "+type);
		  if(type==0){
			  toastr.error("Please Select Action");
			  return false;
		  }else if($('#quant').val()==''){
			  toastr.error("Enter Quant Values ");
			  return false;
		  }else if($('#verbal').val()==''){
			  toastr.error("Enter Verbal Values ");
			  return false;
		  }
		  else{
		  
		  var formData = $('#form1').serialize();
		  $.ajax({
			  type : 'POST',
			  url  : 'saveQuestionActions.action',			
			  data : formData,
				
				 success:function(data){ 
						//alert("Inserted successfully");
					 $('#table_data').html(data);
					 clean();
					 
		    		 $('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");
		    		 
					
		    		 
					},
					error:function(e){
						//alert("Error occured while process the request"+e);
					}
			 }); 
	  }
	 
  });
	  
	  $('#delete').click(function(){
		  if($('[name="action_id"]:checked').length == 0) {
				// alert($('input:checkbox:checked').length);
				 if($('input:checkbox:checked').length==1);
				 toastr.warning("No Action to Delete");
				 return false;
			 }else{ 
		  var var2 = $('input:checkbox:checked').length;
		 if(var2<=0){
			 toastr.warning("Please select the Action to Delete");
			 return false;
		 }else{
		  var formData = $('#form2').serialize();
		  
			 $.ajax({
				type : 'POST',
				url : 'deleteQuestionActions.action', 
				data : formData,
				success :  function deleteSuccess(data, status) {
					 $('#table_data').html(data);
					 clean();
					
		    		 $('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");
		    		
					},
				error : function deleteFailed(errorIs) {
					alert(' Failed ' + errorIs.responseText);
				}

			});
			 
			
		 }
			 }
		  
		 
		
	  });
	  
	  
	  $('#quant').keyup(
				function() {
					var actualScore = $("#quant")
					.val();
			var len = actualScore.length;
			 
					if (this.value.match(/[^0-9€]/g)) {
						this.value = this.value.replace(
								/[^0-9€]/g, '');
						toastr.warning("Enter Only Numbers");
						 $('#quant').focus();
					}
					
				});
	  $('#verbal').keyup(
				function() {
					var actualScore = $("#verbal")
					.val();
			var len = actualScore.length;
			 
					if (this.value.match(/[^0-9€]/g)) {
						this.value = this.value.replace(
								/[^0-9€]/g, '');
						toastr.warning("Enter Only Numbers");
						 $('#verbal').focus();
					}
					
				});
	  
	 
	  
	  
  });
  
  function editDetails(id,type, quant,verbal) {
	//alert(id+"  "+type+"  "+quant+"   "+verbal);
		
		$('#id').val(id);
		$('#type').val(type);
		$('#quant').val(quant);
		$('#verbal').val(verbal);
		
		$('#save').text("Update").removeClass("btn-success").addClass("btn-primary");
		

	}
  
  function clean(){
	  	
		 $('#id').attr('value',0);
		 $('#type').val(0);
		 $('#quant').val("");
		 $('#verbal').val("");
		 $('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");
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
             <div class="panel-heading"> <span class="panel-title"> Add Conditions</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-4 col-lg-offset-4" style="padding-top:40px;">
             
              <form method="post" class="bs-example form-horizontal" theme="simple" id="form1" enctype="multipart/form-data">
              <input type="hidden" id="id" name="bo.question_no" value="0"/>
                       
                        
                        
                        <div class="form-group">
                     	 <label class="col-sm-4 control-label">Action </label>
                      	<div class="col-sm-8">
                        <select name="bo.action_type" class="form-control m-b" id="type">
                          <option value="0">select</option>
                          <option value="1">Referring</option>
                          <option value="2">tour completion</option>
                          <option value="3">7/7 Option</option>
                          
                        </select>
                        </div>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Quant Questions </label>
                          <div class="col-lg-8">
                            <input type="text" name="bo.question_math" id="quant" class="form-control" placeholder="Enter Quant Questions" required="required" maxlength="50">                            
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Verbal Questions </label>
                          <div class="col-lg-8">
                            <input type="text" name="bo.question_verbal" id="verbal" class="form-control" placeholder="Enter Verbal Questions" required="required" maxlength="50">                            
                          </div>
                        </div>
                       
                        <div class="form-group">
                          <div class="col-lg-offset-4 col-lg-2">
                          <!-- <input type="button" id="save" class="btn btn-success" value="Submit"> -->
                             <button type="button" id="save" class="btn  btn-success">Submit</button> 
                          </div>
                        <div class="col-lg-offset-1 col-lg-2">
                            <button type="reset" class="btn btn-danger" onclick="javascript:clean()">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
        	  <center> <span id="error"></span></center>
              
              <hr>
                      
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="table_data">
                <form id="form2">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Action</th>
                        <th>Quant Questions</th>
                        <th>Verbal Questions</th>
                        <th class="text_center">Edit</th>
                        <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="conditionList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="action_type"/></td>
										<td><s:property value="question_math"/></td>
										<td><s:property value="question_verbal"/></td>
										<td align="center"><button class="btn btn-default" type="button" class="btn btn-sm btn-default"	onclick="editDetails('<s:property value="question_no"/>','<s:property value="action_type"/>','<s:property value="question_math"/>','<s:property value="question_verbal"/>')">Edit</button></td>
										<td class="chck" style="padding-bottom: 1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value="<s:property value="question_no"/>" name="action_id"/><i></i></label></td>
										
										
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
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
    <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	

</body>
</html>
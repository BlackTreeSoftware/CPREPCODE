<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Web Application</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
<link rel="stylesheet" href="assets/js/datatables/datatables.css"
	type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet"
	href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
<script src="assets/js/jquery.min.js"></script>

<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>	




  
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
											}

											]
										});
  
						
						

						/** Toaster messages ***/
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
						
						
						
						
						getReferralDetails();

						$('#update').hide();

						function getReferralDetails() {

							$('input:radio[id=active]').prop("checked",true);
							$('input:radio[id=inactive]').prop("checked",false);
							$.ajax({
								type : 'POST',
								url : 'list-referral-conditions.action',
								success : getSuccess,
								error : getFailed

							});

							function getSuccess(htmlScript, status) {

								$('#referral-condition-panel').html(htmlScript);

							}
							function getFailed(errorIs) {
								//alert(' Failed ' + errorIs.responseText);
							}

						}
						/*** Validations ****/
						
						 $('#condition_title').keyup(
												function() {
													var actualScore = $("#condition_title")
													.val();
											var len = actualScore.length;
													if (this.value.match(/[^a-zA-Z” ”]/g)) {
														this.value = this.value.replace(
																/[^a-zA-Z” ”]/g, '');
														
														toastr.error("Error Message", "Numeric characters are not accepted");
														/*  $("#error").fadeIn();
														 $('#error').html("Numeric characters are not accepted");
														 $("#error").css("color", "red");
														 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
														 $('#condition_title').focus();
													}
													else if (len > 50) {
														$("#condition_title").val('');
														//$('#contact').attr('value','');
														toastr.warning("Condition title Name should not exceed 50 characters");
														 $('#condition_title').focus();
													}
													
												});
						
						 $('#condition').keyup(
									function() {
										var actualScore = $("#condition")
										.val();
								var len = actualScore.length;
										if (this.value.match(/[^a-zA-Z” ”]/g)) {
											this.value = this.value.replace(
													/[^a-zA-Z” ”]/g, '');
											toastr.error("Error Message", "Numeric characters are not accepted");
											/*  $("#error").fadeIn();
											 $('#error').html("Numeric characters are not accepted");
											 $("#error").css("color", "red");
											 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
											 $('#condition').focus();
										}
										else if (len > 50) {
											$("#condition").val('');
											//$('#contact').attr('value','');
											toastr.warning("Condition should not exceed 50 characters");
											 $('#condition').focus();
										}
									});
						 
						 
						  $('#selectAll').click(function() {
							 
						    	if($('#selectAll').prop('checked')) {
						    			    		 
						    		
						    		$('input[id=select]').each(function () {
						    			////alert("calling");
						    			// //alert($(this).attr("id"));
						    			  $(this).prop('checked', true);
						    	});
						    	} else {
						    		// //alert("Un checked");
						    		$('input[id=select]').each(function () {
						    			////alert("calling");
						    			////alert($(this).attr("id"));
						    			  $(this).prop('checked',false);
						    	});
						    	}
						        
						    });
			
						
						  $('#reset').click(function(){
								$('#update').hide();
								$('#submit').show();
							});
						  
						$('#submit').click(function() {
						
							 var condition_title=$('#condition_title').val();
							 var condition=$('#condition').val();
							if(condition_title=='' || $.trim( $('#condition_title').val() ) == '' ){
								//	//alert('Please enter Condition Title');
								toastr.error("Error Message", "Please enter condition title");
								/*  $("#error").fadeIn();
								 $('#error').html("Please Enter Condition Title");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								 $('#condition_title').focus();
							}
							else if(condition=='' || $.trim( $('#condition').val() ) == '' ){
								toastr.error("Error Message", "Please enter condition");
								 /* $("#error").fadeIn();
								 $('#error').html("Please Enter Referral Condition");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								 $('#condition').focus();
							}
							else{ 
							var form = $('#referralConditionForm').serialize();
							
							var action = 'save';
							$.ajax({
								type : 'POST',
								url : 'manage-referral-conditions.action?action=' + action,
								data : form,
								datatype : 'json',
								success : actionSuccess,
								error : actionFailed

							});

							}
							function actionSuccess(data, status) {
								var json = JSON.parse(data);
							//	//alert(json.result);								
								if(json.result=='Referral Condition has been added successfully!!!')
								{
									toastr.success("Success Message", "Referral Condition has been added successfully!!!");
								/*  $("#error").fadeIn();
								 $('#error').html(json.result);
								 $("#error").css("color", "green"); */
								 $('#condition_title').val("");
								 $('#condition').val('');
								 $('#condition_id').val('');
								// setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500);
							}
								else{
									toastr.error("Error Message", json.result);
									/*  $("#error").fadeIn();
									 $('#error').html(json.result);
									 $("#error").css("color", "red");
									 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								}
								getReferralDetails();

							}
							function actionFailed(errorIs) {
								////alert(' Failed ' + errorIs.responseText);
								toastr.error("Error Message", "Oops!!.. Sorry.. failed to save the details..");
							}

						});	
						
						
						
						$('#update').click(function() {
							 var condition_title=$('#condition_title').val();
							 var condition=$('#condition').val();
							if(condition_title=='' || $.trim( $('#condition').val() ) == ''){
								////alert('Please enter Condition Title');
								
								toastr.error("Error Message", "Please enter condition title");
								/*  $("#error").fadeIn();
								 $('#error').html("Please enter Condition Title");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								 $('#condition_title').focus();
							}
							else if(condition=='' || $.trim( $('#condition').val() ) == ''){
								////alert('Please enter Condition');
								toastr.error("Error Message", "Please enter condition");
								/*  $("#error").fadeIn();
								 $('#error').html("Please enter Condition");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								 $('#condition').focus();
							}
							else{ 
							var form = $('#referralConditionForm').serialize();
							var action = 'update';
							$.ajax({
								type : 'POST',
								url : 'manage-referral-conditions.action?action=' + action,
								data : form,
								datatype : 'json',
								success : updateSuccess,
								error : updateFailed

							});
							}

							function updateSuccess(data, status) {

								var json = JSON.parse(data);
								////alert(json.result);	
								if(json.result=='Data has been updated successfully..')
								{
									 $('#condition_title').val("");
									 $('#condition').val('');
									 $('#condition_id').val('');									 
									 toastr.success("Success Message",json.result);
								/*  $("#error").fadeIn();
								 $('#error').html(json.result);
								 $("#error").css("color", "green");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
							}
								else{
									toastr.error("Error Message", "Please enter condition title");
									/*  $("#error").fadeIn();
									 $('#error').html(json.result);
									 $("#error").css("color", "red");
									 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								}
								 
								getReferralDetails();
								$('#update').hide();
								$('#submit').show();
								$('#condition_title').val('');
								$('#condition_id').val('');
								$('#condition').val('');

							}
							function updateFailed(errorIs) {
								////alert(' Failed ' + errorIs.responseText);
								toastr.error("Error Message", "Oops.. Sorry.. Could not update the data..");
							}

						});	
						
						
						
						$('#delete')
						.click(
								function() {
									var action = 'delete';
									var array = [];
									var checkboxvalues;
									$('input[id=select]:checked').each(
											function() {
												////alert($(this).val());

												array.push($(this)
														.val());
											});

									if (array.length > 0) {

										checkboxvalues = array
												.join(',')
												+ ",";												

										var deleteConditions = $('#deleteConditions').serialize();
												
										$.ajax({
													type : 'POST',
													url : 'manage-referral-conditions.action?action='
															+ action,
													data : deleteConditions,															
													datatype : 'json',
													success : deleteSuccess,
													error : deleteFailed

												});
									} else {
										toastr.error("Error Message", "Please Select Atleast One record");
									}
									function deleteSuccess(data, status) {
										var json = JSON.parse(data);
										//alert(json.result);
										
										if(json.result=='Successfully deleted all records'){
											toastr.success("Success Message", "Successfully deleted Selected records");
									
										}
										else{
											
											toastr.error("Error Message", json.result);
											/*  $("#error").fadeIn();
											 $('#error').html(json.result);
											 $("#error").css("color", "red");
											 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
										}
										
										getReferralDetails();
										/* if (json.result == 'SUCCESS') {
											//alert("Successfully deleted all records.. Thank You..");
											getDetails();

										} else {
											//alert(json.result
													+ " conditions have records so cannot be deleted");
											getReferralDetails();
										} */

										$('#update').hide();
										$('#submit').show();

									}
									function deleteFailed(errorIs) {
									//	//alert(' Failed '+ errorIs.responseText);
										toastr.error("Error Message", "Oops.. Sorry Could not delete the records...!!!");
									}

								});
				
						
  
					});
	
	
	function setDetails(name,condition ,status,id) {
		//alert(name+":::::"+id);
	
		$('#submit').hide();
		$('#update').show();		
		$('#condition_title').val(name);
		$('#condition_id').val(id);
		$('#condition').val(condition);
		var status=status;
		if(status=='ACTIVE')
			$('input:radio[id=active]').prop("checked",true);
		else
			$('input:radio[id=inactive]').prop("checked",true);
		

	}
	</script>
  
  
</head>
<body class="">
  
        
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Referral Condition Panel</span>  </div>
             <div class="panel-body">
             <div class="row">
         
             <div class="col-lg-4 col-lg-offset-4" style="padding-top:40px;">
                <!--  <small style="color:red;">All fields marked with an asterisk (*) are required.  </small><br><br>
                 <small id="error" style="color:red"></small> -->
              <form class="bs-example form-horizontal" id="referralConditionForm" name="referralConditionForm" method="post">
                    
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Condition Title <b style="color:red">*</b></label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" placeholder="Enter Condition Title" id="condition_title" name="referralConditionBO.condition_title">
                             <input type="hidden" class="form-control" placeholder="Enter Condition Title" id="condition_id" name="referralConditionBO.condition_id" data-required="true">                            
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Condition <b style="color:red">*</b></label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" placeholder="Enter Condition" id="condition" name="referralConditionBO.condition">                            
                          </div>
                        </div>
                                                
                        <div class="form-group">
                        <label class="col-lg-4  control-label">Status</label>
                         <div class="radioo i-checks col-lg-3">
                                  <label>
                                    <input type="radio" id="active" checked name="referralConditionBO.status" value="ACTIVE" checked>
                                    <i></i>
                                   Active
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-3">
                                  <label>
                                    <input type="radio" id="inactive" name="referralConditionBO.status" value="INACTIVE" >
                                    <i></i>
                                    In Active
                                  </label>
                                </div>           
                        </div>

                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn  btn-success" id="submit">Submit</button>
                             <button type="button" class="btn  btn-primary" id="update">Update</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="reset" class="btn  btn-danger" id="reset">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
              
              <hr>
                      
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="referral-condition-panel">
                  
                
                </div>
                
                </section>
                <center><a href="#" class="btn btn-del" id="delete">Delete selected</a></center>
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
	<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
	<script src="assets/js/datatables/demo.js"></script>

</body>
</html>
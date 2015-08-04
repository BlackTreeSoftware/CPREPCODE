<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html lang="en" class="app">
<head>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<title>Crunchprep | GRE Web Application</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />

<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>	




<script type="text/javascript">
	$(document)
			.ready(
					function() {

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
						
						
						

						getDetails();

						$('#update').hide();

						function getDetails() {

							$.ajax({
								type : 'POST',
								url : 'list-skill-levels.action',
								success : getSuccess,
								error : getFailed

							});

							function getSuccess(htmlScript, status) {

								$('#skill-levels').html(htmlScript);

							}
							function getFailed(errorIs) {
								toastr.error("Error Message","Failed"+errorIs.responseText);
								
							}

						}

						
						
						/*** Validations ****/
						
						 $('#skill_name').keyup(
												function() {
													var actualScore = $("#skill_name")
													.val();
											var len = actualScore.length;
											 
													if (this.value.match(/[^a-zA-Z” ”]/g)) {
														this.value = this.value.replace(
																/[^a-zA-Z” ”]/g, '');
														toastr.warning("Numeric Characters are not accepted");
														 $('#skill_name').focus();
													}
													else if (len > 50) {
														$("#skill_name").val('');
														//$('#contact').attr('value','');
														toastr.warning("Skill Name should not exceed 50 characters");
													}
												});
						
						
						
						
						
						$('#submit').click(function() {
							
							var skillname=$('#skill_name').val();
							if(skillname=='' || $.trim( $('#skill_name').val() ) == '' ){
								//alert('Please enter skill name');
								toastr.error("Error Message", "Please enter skill name");
								/* $("#error").fadeIn();
								 $('#error').html("Please enter skill name");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								 $('#skill_name').focus();
							}else if($('#section').val()==-1){
								toastr.error("Error Message", "Please Select Section");
							}
							else{ 
							var form = $('#skillForm').serialize();
							
							var action = 'save';
							$.ajax({
								type : 'POST',
								url : 'skill-levels.action?action=' + action,
								data : form,
								datatype : 'json',
								success : actionSuccess,
								error : actionFailed

							});

							}
							function actionSuccess(data, status) {
								var json = JSON.parse(data);
								
								if(json.result=='Skill has been saved.. Thank You!!'){
									toastr.success("Success Message", json.result);
									$('#skill_name').val("");
									$('#section').val(-1);
								/*  $("#error").fadeIn();
								 $('#error').html(json.result);
								 $("#error").css("color", "green");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
							}
							else{
								toastr.error("Error Message", json.result);
								/*  $("#error").fadeIn();
								 $('#error').html(json.result);
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
							}
								getDetails();

							}
							function actionFailed(errorIs) {
								
								toastr.error("Error Message", "OOps.. Sorry.. Failed to save.....");
							/* 	$("#error").fadeIn();
								 $('#error').html("Oops.. Sorry.. Could not process data..");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
							}

						});

						$('#update').click(function() {
							 var skillname=$('#skill_name').val();
							if(skillname=='' || $.trim( $('#skill_name').val() ) == '' ){
								
								toastr.error("Error Message", 'Please enter skill name');
							/* 	$("#error").fadeIn();
								 $('#error').html("Please enter skill name");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								 $('#skill_name').focus();
							}
							else{
							var form = $('#skillForm').serialize();
							
							var action = 'update';
							$.ajax({
								type : 'POST',
								url : 'skill-levels.action?action=' + action,
								data : form,
								datatype : 'json',
								success : updateSuccess,
								error : updateFailed

							});
							}

							function updateSuccess(data, status) {
								var json = JSON.parse(data);
							
								if(json.result=='Skill has been updated.. Thank You!!'){
									toastr.success("Success Message", json.result);
									$('#section').val(-1);
									/*  $("#error").fadeIn();
									 $('#error').html(json.result);
									 $("#error").css("color", "green");
									 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								}
								else{
									toastr.error("Error Message", json.result);
									/*  $("#error").fadeIn();
									 $('#error').html(json.result);
									 $("#error").css("color", "red");
									 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
								}						
								getDetails();
								$('#update').hide();
								$('#submit').show();
								$('#skill_name').val('');
								$('#skill_id').val('');

							}
							function updateFailed(errorIs) {
								
								toastr.error("Error Message", 'Oops!! Sorry failed to update...');
								/* $("#error").fadeIn();
								 $('#error').html("Oops.. Sorry.. Could not process data..");
								 $("#error").css("color", "red");
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
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
														//alert($(this).val());

														array.push($(this)
																.val());
													});

											if (array.length > 0) {

												checkboxvalues = array
														.join(',')
														+ ",";												

											
												$
														.ajax({
															type : 'POST',
															url : 'skill-levels.action?action='
																	+ action
																	+ '&checkboxvalues='
																	+ checkboxvalues,
															datatype : 'json',
															success : deleteSuccess,
															error : deleteFailed

														});
											} else {
												//alert('Please select records to delete');
												toastr.error("Error Message", 'Please Select records to delete');
												/*  $("#error").fadeIn();
												 $('#error').html('Please select records to delete');
												 $("#error").css("color", "green");
												 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
											}
											function deleteSuccess(data, status) {
												var json = JSON.parse(data);
												if (json.result == 'SUCCESS') {
													//alert("Successfully deleted all records.. Thank You..");
													toastr.success("Success Message", "Successfully deleted all records.. Thank You..");
													/*  $("#error").fadeIn();
													 $('#error').html("Successfully deleted all records.. Thank You..");
													 $("#error").css("color", "green");
													 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
													getDetails();

												} else {
													//alert(json.result+ " skills have records so cannot be deleted");
													toastr.error("Error Message", json.result+ " skills have records so cannot be deleted");
													/*  $("#error").fadeIn();
													 $('#error').html(json.result+ " skills have records so cannot be deleted");
													 $("#error").css("color", "green");
													 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
													
													
													getDetails();
												}

												$('#update').hide();
												$('#submit').show();

											}
											function deleteFailed(errorIs) {
											/* 	alert(' Failed '
														+ errorIs.responseText); */
												toastr.error("Error Message","Oops!! Sorry.. failed to delete records...");
											/* 	$("#error").fadeIn();
												 $('#error').html("Oops.. Sorry.. Could not process data..");
												 $("#error").css("color", "red");
												 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 3500); */
												
											}

										});
						
						
						
						  $('#selectAll').click(function() {
							 
						    	if($('#selectAll').prop('checked')) {
						    			    		 
						    		
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
						
						$('#reset').click(function(){
							$('#update').hide();
							$('#submit').show();
						});

					});

	function setDetails(name, id,sectionId) {
			
		$('#submit').hide();
		$('#update').show();		
		$('#skill_name').val(name);
		$('#skill_id').val(id);
		$('#section').val(sectionId);
		
		/* $('#skill_name').attr('value', name);
		$('#skill_id').attr('value', id); */
		

	}
</script>
</head>




<body class="">

	<section id="content">
		<section class="vbox">
			<section class="scrollable wrapper">

				<div class="row">
					<!--row start main-->
					<div class="col-lg-12">

						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="panel-title"> Add Skills </span>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-4 col-lg-offset-4"
										style="padding-top: 40px;">
<%-- <small style="color:red;">All fields marked with an asterisk (*) are required.  </small> --%>
              
										<form class="bs-example form-horizontal" name="skillForm"
											id="skillForm" method="post">
											<div class="form-group">
												<label class="col-lg-4 control-label">Skill Name <b style="color:red">*</b></label>
												<div class="col-lg-8">
													<input type="text" class="form-control"
														placeholder="Enter Skill Name" id="skill_name"
														name="skillBO.skill_name">
														
														<input type="hidden"
														class="form-control" placeholder="Enter Skill Name"
														id="skill_id" name="skillBO.skill_id" value="0">
												</div>
											</div>
											
						 <div class="form-group">
                     	 <label class="col-sm-4 control-label">Section </label>
                      	 <div class="col-sm-8">
                         <s:select  headerKey="-1" headerValue="Select" name="skillBO.sectionId" class="form-control m-b" id="section" list="sectionList" listValue="sectionName" listKey="sectionId" cssClass="form-control m-b">
                           <!-- <option value="-1">--Select--</option> -->
                          
                         </s:select>
                        </div>
                        </div>

											<div class="form-group">
												<div class="col-lg-offset-4 col-lg-2">
													<button type="button" id="submit" class="btn  btn-success">Submit</button>
													<button type="button" id="update" class="btn  btn-primary">Update</button>
												</div>
												<div class="col-lg-offset-1 col-lg-2">
													<button type="reset" id="reset" class="btn  btn-danger">Cancel</button>
												</div>
											</div>
											<!--form group close-->
										</form>

										<hr>

									</div>
									<!--col for skill level close-->

								</div>
								<!--Inner row1 close -->



								<div class="row">
									<div class="col-lg-8 col-lg-offset-2"
										style="padding-top: 40px;">

										<section class="scrollable padder">

											<section class="panel panel-default">

												<div class="table-responsive" id="skill-levels">
													<table id="table" class="table table-striped m-b-none"
														>
														<thead>
															<tr>
																<th>Select</th>
																<th>Sr.No</th>
																<th>Skill</th>
																<th><center>Edit</center></th>

															</tr>
														</thead>
														<tbody>
															<s:iterator value="skills" status="status">
																<tr>
																	<td><input type="checkbox" class="form-control"
																		id="select" value='<s:property value="skill_id"/>'></td>
																	<td><s:property value="#status.index+1" /></td>
																	<td><s:property value="skill_name" /></td>
																	<td align="center"><a class="btn btn-default" href="javascript:setDetails('<s:property value="skill_name"/>','<s:property value="skill_id"/>','<s:property value="SectionId"/>')">Edit</a>
																	</td>

																</tr>


															</s:iterator>
														</tbody>
													</table>

												</div>


											</section>
											<center>
												<a href="#" class="btn btn-del" id="delete">Delete
													selected</a>
											</center>
										</section>
										<!--sec close for data table-->

										<hr>
									</div>
									<!--col for DataTable close-->

								</div>

								<!--Inner row2 close -->

							</div>
							<!--panel body close-->
						</div>
						<!--panel head close-->

					</div>
					<!--div close-->
				</div>
				<!--row start main End-->



			</section>
		</section>
		<!--  <a href="assets#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a> -->
	</section>



	<script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	






</body>
</html>

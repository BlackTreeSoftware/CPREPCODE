<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- / footer -->
<script src="assets/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="assets/js/bootstrap.js"></script>
<!-- App -->
<script src="../../assets/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="assets/js/bootstrap.js"></script>
<!-- App -->
<link rel="stylesheet" href="assets/js/datepicker/datepicker.css"
	type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/css/jasny/jasny-bootstrap.min.css"
	type="text/css" />
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<script src="assets/js/select2/select2.min.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script type="text/javascript"> </script>
<script src="assets/js/datepicker/bootstrap-datepicker.js"></script>
<script src="assets/js/file-input/bootstrap-filestyle.min.js"></script>
<script src="assets/js/jasny/jasny-bootstrap.min.js"></script>
<script src="assets/js/parsley/parsley.min.js"></script>
<script src="assets/js/parsley/parsley.extend.js"></script>
	
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						 
						
						$('#first_name').keyup(function(){
							if($('#first_name').val().lenght>50)
								{
								 toastr.error("First Name Must be Less than 50 characters","Error");
								}
						});
						
						$('#lastname').keyup(function(){
							if($('#lastname').val().lenght>50)
								{
								 toastr.error("Last Name Must be Less than 50 characters","Error");
								}
						});

						//alert($('#test').val());

						if ($('#test').val() == "ACTIVE") {
							$('#testper').attr('checked', 'checked');
						}

						if ($('#perf').val() == "ACTIVE") {
							$('#perform').attr('checked', 'checked');
						}

						$("#regpass")
								.change(
										function() {
											var actualScore = $("#regpass")
													.val().length;

											if ((actualScore < 8)) {
												$("#regpass").attr("value", '');
												$("#regpassError")
														.html(
																"Password Should be Minimum 8");
											} else {
												$("#regpassError").html('');
											}
										});

						$("#conformPassword")
								.change(
										function() {
											var password = $("#regpass").val();
											var retype = $("#conformPassword")
													.val();

											//$("#regpass").html("Password Does'nt Match ");
											if (password != retype) {
												$("#conformPassword").attr(
														"value", '');
												$("#conformPassworderror")
														.html(
																"Password  Does'nt Match ");
												$('#submit').hide();
											} else {
												$("#conformPassworderror")
														.html('');
												$('#submit').show();
											}
										});

						

						 
 

						 

						 
 

					 
 
						//@author : satya bugs fixed startd 

						$('#updateinfo').click(
								
										function() {
											 if($('#form').parsley('validate')){
											$.ajax({
														type : 'POST',
														url : 'userProfiledupdationsdata.action',
														data : $('#form')
																.serialize(),
														datatype : 'JSON',
														success : function(data) {
															 toastr.success("You Profile  has been Updated successfully","Success");
														},
														error : function(error) {
															toastr.error("You Profile Not Updated!","Error");
														}
													});
											 }
										}
								 
										);

				
						$('#changepassword')
								.click(
										function() {
											 if($('#chgp').parsley('validate')){
											$.ajax({
														type : 'POST',
														url : 'ChangePass.action',
														data : $('#chgp')
																.serialize(),
														datatype : 'JSON',
														success : function(data) {
															toastr.success("Your Password changed successfully","Success");

														},
														error : function(error) {
															toastr.error("Password Not changed ","Error");

														}
													});
											 }
										});

						$('#usersettings')
								.click(
										function() {
											var testper;
											var prof;
											if ($('#perform').is(":checked")) {
												//alert("cehcked");
												prof = "ACTIVE";
											} else {
												//alert("not checked");
												prof = "INACTIVE";
											}
											if ($('#testper').is(":checked")) {
												//alert("checked");
												testper = "ACTIVE";
											} else {
												//alert("not checked");
												testper = "INACTIVE";
											}

											$
													.ajax({
														type : 'POST',
														url : 'usersettings.action',
														data : 'profile='
																+ prof
																+ '&testperformance='
																+ testper,
														datatype : 'JSON',
														success : function(data) {
															toastr.success("Settings updated succesfully");
														},
														error : function(error) {

														}
													});

										});

						 
						$('#form3')
								.on(
										'submit',
										function(e) {
											
										//alert("hai");
											var formURL = "imageupload.action";
											var formData = new FormData(this);
											$
													.ajax({
														url : formURL,
														type : "POST",
														data : formData,
														dataType:'json',
														mimeType : "multipart/form-data",
														contentType : false,
														cache : false,
														processData : false,
														success : function(data,textStatus,jqXHR) {
															//alert("success");
															toastr.success(data+"\n\t and messagie is : "+data.message);
															//refresh();
															
															if(data.message=='Image Uploaded Successfully'){
																
																location.reload();
																
																//alert("Image "+$("#img").html());
																}
															
														/* 	if($("#img").html().contains('img'))
																{
																
																alert('image Successfully updated');
																//toastr.success("Image Successfully Updated", "Success");
																
																} */
															
															else{
												           alert("Please Select Image");
																toastr.error("Please Select Image", "Error");
						
																location.reload();
															}
															
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															toastr.error("Image size should be < 300kb & image/png,image/jpeg Formats");
															//location.reload();
														}
													});
											e.preventDefault();

										});

						
					 
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
				
						 var nowTemp = new Date();
						 var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
						  
						 $('#testdate').datepicker({
						   onRender: function(date) {
						   return date.valueOf() < now.valueOf() ? 'disabled' : '';
						   }
						 });
						 
						 
						// toastr.success("Success Message", "Success");
						//toastr.error("Success Message", "Error");
					  }
					// changes End :  @author: satya
					
			);

 

	function Checkfiles() {
		//alert("............");
		var fup = document.getElementById('fileupload');
		var fileName = fup.value;
		var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		if (ext == "gif" || ext == "GIF" || ext == "JPEG" || ext == "jpeg"
				|| ext == "jpg" || ext == "JPG" || ext == "png" || ext == "PNG") {
			return true;
		} else {
			
			 
			document.getElementById("fileuploaderror").innerHTML = "Please select Jpg, jpeg ,gif,png";
			fup.focus();
			return false;
		}
	}
</script>
<script>
function dateFunction()
{
alert('in date function');	
var str = document.getElementById("test_date").value;
var day= str.substr(0,2);
var mnt= str.substr(3,2);
var yr= str.substr(6,4);
var sel = new Date(yr, mnt-1, day);
var tod = new Date();
if(tod>sel)
	{
	document.getElementById("dat").innerHTML ="Invalid Date, Dont Enter Past dates";
	document.getElementById("test_date").value ="";
	}
if(tod<sel)
{
document.getElementById("dat").innerHTML ="";
}
  
}
</script>

<script type="text/javascript">


function refresh() {
	//bootbox.alert("form Submited");
	document.getElementById("form3").action = "profilepage";
	document.getElementById("form3").submit();
	//resetParentDetails();
}
</script>




<style>
.padding-for-img-row{ margin-left:30px;}
@media (max-width:480px)
{
.padding-for-img-row{ margin-left:0px;}

}
</style>

</head>


<body class="" id="body">
  
	<section id="content">
		<section class="vbox">
		<br><br>
			<section class="scrollable wrapper">
	
				<div class="row">

					<div class="col-lg-12">
						<!--main col-->

						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">Profile Settings</div>
							</div>
							<div class="panel-body">

								<div class="row">

									<div class="col-lg-3 col-lg-offset-0 padding-for-img-row">

										<div class="row" style="margin-top: 40px;">

											<div class="col-lg-12 ">
												<center>
													<form theme="simple" id="form3">
													
														<div class="fileinput fileinput-new"
															data-provides="fileinput">
														<!-- 	check Image is:  -->
															<div class="fileinput-preview thumbnail" data-trigger="fileinput" id="img"	style="width: 200px; height: 150px;" alt="img">
															  <img src="<s:property value="%{#session.user.avatar_path}"/>"   class="img-responsive img-pro" />
															</div>
															<div>
																<span class="btn btn-success btn-file"> <span
																	class="fileinput-new">Select image</span> <span
																	class="fileinput-exists">Change</span> <input
																	type="file" accept="image/jpeg, image/png, image/gif"
																	name="chgpassbo.userimage" onchange="Checkfiles()"
																	id="fileupload">
																</span> <a href="#" class="btn btn-default fileinput-exists"
																	data-dismiss="fileinput">Remove</a> </br> </br>
																<button class="btn btn-success" id="imagesave"
																	type="submit" align="center">Upload Image</button>
															</div>
														</div>
													</form>
												</center>
											
												<hr>
												

											<div class="form-group">
												<label class="col-lg-12 col-xs-10 col-xs-offset-1 text-center text-success h3">Account
													info</label>

											</div>
											
											<br><br>
											<center>
											<div class="form-group">
												<label class="col-lg-5 col-xs-4 control-label">Start Date</label>
												<div class="col-lg-7 col-xs-6 ">
													<label class="control-label">${registrationBO.start_date}</label>

												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-5 col-xs-4 control-label">End Date</label>
												<div class="col-lg-7 col-xs-6">
													<label class="control-label">${registrationBO.end_date}</label>
												</div>
											</div>
											</center>
											
											
											</div>
											<!--col close-->
										</div>
										<!--row close-->

										<br>


									</div>
									<!--profile pic div close-->

									<div class="col-lg-3 col-lg-offset-1">


										<form id="form"
											class="bs-example form-horizontal form-right-margin">

											<h3 class="text-success text-center">Personal Info</h3>
											<br>

											<div class="form-group">
												<label class="col-lg-4 control-label">First Name</label>
												<div class="col-lg-8">
													<input type="text" class="form-control"
														placeholder="First name" name="registrationBO.first_name"
														id="first_name" value="${registrationBO.first_name}"
														data-type="alpha" data-trigger="change" data-required="true">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">Last Name</label>
												<div class="col-lg-8">
													<input type="text" class="form-control"
														placeholder="Last Name" name="registrationBO.last_name"
														id="lastname" value="${registrationBO.last_name}"
														data-type="alpha" data-required="true">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">Test Date</label>
												<div class="col-lg-8">
													<input type="text"
														class="datepicker-input border form-control"
														placeholder="Test Date" name="registrationBO.test_date" id="testdate"
														value="${registrationBO.test_date}" data-trigger="change" 
														data-type="dateIso" data-date-format="yyyy-mm-dd" data-required="true">
												</div>
												<span id='dat' style="color: red;"></span>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">Math Score</label>
												<div class="col-lg-8">
												
												
												<input type="number" class="form-control"
														data-trigger="change" data-required="true"
														data-type="number" placeholder="Math Target Score" value="${registrationBO.target_score_math}"
														min="140" max="170"
														name="registrationBO.target_score_math">
													</div>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">Verbal 
													Score</label>
												<div class="col-lg-8">
												
												<input type="number" class="form-control"
														data-trigger="change" data-required="true"
														data-type="number" placeholder="Verbal Target Score"
														min="140" max="170" value="${registrationBO.target_score_verbal}"
														name="registrationBO.target_score_verbal">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">Actual Score</label>
												<div class="col-lg-8">
												
												<input type="number" class="form-control"
														data-trigger="change" data-required="true" value="${registrationBO.actual_score}"
														data-type="number" placeholder="Actual Score" min="260"
														max="340" name="registrationBO.actual_score">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">Phone</label>
												<div class="col-lg-8">
													<input type="text" class="form-control"
														data-trigger="change" data-required="true"
														data-type="phone" placeholder="Phone No" value="${registrationBO.phone_number}" 
														name="registrationBO.phone_number" id="contact"
														data-minlength="10" data-maxlength="10">
														
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">City/Town</label>
												<div class="col-lg-8">
													<input type="text" class="form-control"
														placeholder="City/Town" name="registrationBO.city"
														id="state" value="${registrationBO.city}"
														data-type="alpha" data-required="true">
												</div>
											</div>
											
											<div class="form-group">
												<center>

													<button type="button" class="btn btn-success"
														id="updateinfo">Update Info</button>
												</center>
											</div>

											

												

										</form>


									</div>

									<div class="col-lg-3 col-lg-offset-1">
										<div class="row">
											<div class="col-lg-12">
												<form id="chgp" class="bs-example form-horizontal">

													<h3 class="text-success text-center">Change Password</h3>
													<br>

													<div class="form-group">
														<label class="col-lg-5 control-label"> Password </label>
														<div class="col-lg-7 profile">
															<input type="password" class="form-control"
																placeholder="Password" name="chgpassbo.password"
																id="regpass" data-required="true"> <span id="regpassError"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-lg-5 control-label"> Confirm
															Password </label>
														<div class="col-lg-7 profile">
															<input type="password" class="form-control"
																placeholder="Confirm Password"
																name="chgpassbo.confpassword" id="conformPassword" data-equalto="#regpass" data-required="true">
															 
														</div>
													</div>


													
													<div class="form-group">

														<center>
														<button type="button" class="btn btn-success"
															id="changepassword" onclick="formValidation()">Change
															Password</button>
														</center>

													</div>


												</form>
											</div>
											<!--internal col close-->
										</div>
										<!--internal row close-->
										<hr>

										<h3 class="text-success text-center">Additional Settings</h3>
										<br>

										<div class="row">
											<div class="col-lg-12 col-lg-offset-0">
												<form id="user" class="bs-example form-horizontal">
													<div class="form-group">

														<div class="col-sm-4">
															<p class="label-check text-right">Preferences</p>
														</div>

														<div class="col-sm-8">
															<%--<s:property value="%{registrationBO.testperformance}" /> --%>
															<div class="checkbox i-checks ">

																<s:hidden value="%{registrationBO.testperformance}" id="test"></s:hidden>
																<label> <input type="checkbox" id="testper"
																	name="registrationBO.testperformance"><i></i>Test Performance
																</label>
															</div>

															<div class="checkbox i-checks">
																<s:hidden value="%{registrationBO.profile}" id="perf"></s:hidden>
																<label> <input type="checkbox"
																	name="registrationBO.profile" id="perform"><i></i>Profile
																</label>
															</div>
															<br>
														</div>

													</div>

													<div>
														 <center>
														<input type="button" value="Update Settings"
															id="usersettings" class="btn btn-success" />
													</center> 
													</div>

												</form>
											</div>
										</div>
										<!--row close-->
										<br>

										<hr>

										 <div class="row">
               		
                    
                    <div class="col-lg-12 ">
                     
                     <div class="form-group">
                      <label class="col-sm-6 control-label label-check">Turn on Beta</label>
                      <div class="col-sm-6">
                        <label class="switch">
                          <input type="checkbox">
                          <span></span>
                        </label>
                      </div>
                      
                    </div>
                 
                 
                 </div>
                
                </div>

									</div>
									<!--row close-->
								</div>

							</div>
							<!--panel body close-->

						</div>
						<!--panel default close-->

					</div>
					<!--Inner row close-->
				</div>
				<!--main col-close -->



			</section>
		</section>
	</section>



</body>
</html>
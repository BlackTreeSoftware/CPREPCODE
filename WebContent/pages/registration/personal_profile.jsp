<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
<meta charset="utf-8" />
<title>Crunchprep | GRE Web Application</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="assets/css/animate.css" type="text/css" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css" />
<link rel="stylesheet" href="assets/css/icon.css" type="text/css" />
<link rel="stylesheet" href="assets/css/font.css" type="text/css" />
<link rel="stylesheet" href="assets/css/app.css" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" href="assets/js/datepicker/datepicker.css" type="text/css" />
<script type="text/javascript"
	src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>

<!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<script>
	$(document).ready(function() {
		//finishButton();  

		var nowTemp = new Date();
		 var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		  
		
		 var datePicker = $('#testdate').datepicker({
			   onRender: function(date) {
				   return date.valueOf() < now.valueOf() ? 'disabled' : '';
				   }
				 });
		 var t ;
		 $( document ).on(
		     'DOMMouseScroll mousewheel scroll',
		      
		     function(){       
		         window.clearTimeout( t );
		         t = window.setTimeout( function(){            
		             $('#testdate').datepicker('place')
		         }, 100 );        
		     }
		 );
		
	});
	function finishButton() {
		//size=$('#questions_size').val(); 
		//alert(parseInt( size ) + 1);
		var checkConditionSatisfied = true;
		var size = parseInt($('#questions_size').val()) + 1;
		for ( var i = 1; i < size; i++) {
			//alert($("input[name='questions_answers["+i+"].options']").attr('type'));
			if ($("input[name='questions_answers[" + i + "].options']").attr(
					'type') != 'text') {
				var innerCheck = false;
				var cnt = $("input[name='questions_answers[" + i
						+ "].options']:checked").length;

				if (cnt < 1) {
					if (checkConditionSatisfied) {
						checkConditionSatisfied = false;
					}
					innerCheck = true;
					alert(' Please Select Atleast One Value in \n\t'
							+ $('#question' + i).text());
					return false;

					//return false;
					// e.preventDefault();
				}

				/* else
					{
				 $("#wizardform").submit();
					}   */
			} else if ($("input[name='questions_answers[" + i + "].options']")
					.attr('type') == 'text') {
				var textField = $(
						"input[name='questions_answers[" + i + "].options']")
						.val();
				
				var regx = /^[A-Za-z0-9]+$/;
				if (textField == '') {
					alert("Fill the text field");
					return false;
				} else if (!regx.test(textField)) {
					 alert(' '+textField+' Not Allowed '); 	
					if (checkConditionSatisfied) {
						checkConditionSatisfied = false;
					}
				}

				//alert(' Text Field Value is : '+textField);
			}
		}
		if (checkConditionSatisfied) {
			// alert(' Readey to Form Submit ');
			$("#wizardform").submit();
		} else {
			//alert(' Form it not Ready to submit');
		}
	}
</script>
<body class="" style="overflow-y: auto">

	<div class="container-fluid">
		<div class="row" style="background-color: #2C2D31;">
			<div class="col-lg-1 col-lg-offset-2">
				<img src="assets/images/logo.png" style="padding: 5px;" />
			</div>
		</div>
	</div>
	<!--Header closed-->

	<section id="content" class="m-t-lg wrapper-lg animated fadeInDown">



		<div class="container aside-xxl">
			<section class="m-b-lg">
				<header class="wrapper text-center">
					<strong><h3>Enter Personal Details</h3></strong>
				</header>

				<!------->

				<div class="row">
					<div class="col-sm-12">
						<form id="wizardform" method="post"
							action="savePersonalDetails.action">

							<div class="panel panel-default">
								<div class="panel-heading">
									<ul class="nav nav-tabs nav-justified font-bold">
										<li><a href="#step1" data-toggle="tab">Step 1</a></li>
										<li><a href="#step2" data-toggle="tab">Step 2</a></li>
										<!--<li><a href="#step3" data-toggle="tab">Step 3</a></li>-->
									</ul>
								</div>
								<div class="panel-body">
									<p></p>
									<div class="line line-lg"></div>
									<h4>Validate Form</h4>
									<div class="progress progress-xs m-t-md">
										<div class="progress-bar bg-success"></div>
									</div>
									<br>
									<div class="tab-content">
										<div class="tab-pane" id="step1">


											<div class="list-group">

												<div class="list-group-item list-group-item-multi">
													<input type="text" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="alpha" placeholder="First Name"
														name="registrationBO.first_name" value="${registrationBO.first_name}" maxlength="50">

												</div>

												<div class="list-group-item list-group-item-multi">
													<input type="text" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="alpha" placeholder="Last Name"
														name="registrationBO.last_name" maxlength="50">

												</div>

												<div class="list-group-item list-group-item-multi">
													<input class="datepicker-input no-border form-control"
														size="16" type="text" value="Test Date"
														data-date-format="yyyy-mm-dd" data-trigger="change"
														data-required="true" data-type="dateIso" id="testdate"
														name="registrationBO.test_date">
												</div>

												<div class="list-group-item list-group-item-multi">
													<input type="number" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="number" placeholder="Math Target Score"
														min="140" max="170"
														name="registrationBO.target_score_math">

												</div>

												<div class="list-group-item list-group-item-multi">
													<input type="number" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="number" placeholder="Verbal Target Score"
														min="140" max="170"
														name="registrationBO.target_score_verbal">

												</div>

												<div class="list-group-item list-group-item-multi">
													<input type="number" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="number" placeholder="Actual Score" min="260"
														max="340" name="registrationBO.actual_score">

												</div>

												<div class="list-group-item list-group-item-multi">
													<input type="text" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="phone" placeholder="Phone No"
														name="registrationBO.phone_number"
														data-minlength="10" data-maxlength="10">

												</div>

												<div class="list-group-item list-group-item-multi">

													<input type="text" class="form-control no-border"
														data-trigger="change" data-required="true"
														data-type="alpha" placeholder="City/Town"
														name="registrationBO.city">

												</div>

											</div>
											<!--list group close-->


										</div>
										<!--step one close--->

										<div class="tab-pane" id="step2">

											<div class="list-group">

												<div class="list-group-item list-group-item-margin">
													<input type="hidden"
														value="<s:property value="%{orient_questions.size()}"/>"
														id="questions_size">
													<s:iterator value="orient_questions" id="ques" status="no">
														<s:if test="#ques.question_type == 'SINGLE'">
															<label id="question<s:property value="#no.index+1"/>"><%-- s:property
																	value="question" /> --%>
																	<s:text name="question" /></label>

															<input type="hidden"
																value="single<s:property value="#no.index+1"/>"
																name="hidden<s:property value="#no.index+1"/>" />
															<input type="hidden"
																name="questions_answers[<s:property value="#no.index+1"/>].question_id"
																value="<s:property value="#ques.question_id"/>" />
															<s:iterator value="#ques.options" status="status">
																<div class="radio i-checks">
																	<label>  
																		<input type="radio" value="<s:property/>"
																		id="<s:property value="#no.index+1"/><s:property value="#status.index+1"/>"
																		name="questions_answers[<s:property value="#no.index+1"/>].options" />
																		<i></i> <s:property />
																	</label>
																</div>
															</s:iterator>
															<s:if test="#ques.instructions != ''">
																<b>Instructions:<s:property value="instructions" /></b>
																<br>
															</s:if>
														</s:if>
														<s:if test="#ques.question_type == 'MULTIPLE'">
															<input type="hidden"
																value="double<s:property value="#no.index+1"/>"
																name="hidden<s:property value="#no.index+1"/>" />
															<input type="hidden"
																name="questions_answers[<s:property value="#no.index+1"/>].question_id"
																value="<s:property value="#ques.question_id"/>" />
															<label id="question<s:property value="#no.index+1"/>"><%-- <s:property
																	value="question" /> --%>
																	<s:text name="question" /></label>
															<s:iterator value="#ques.options" status="status">
																<div class="checkbox i-checks">
																	<label> 
																		<input type="checkbox" value="<s:property/>"
																		id="<s:property value="#no.index+1"/><s:property value="#status.index+1"/>"
																		name="questions_answers[<s:property value="#no.index+1"/>].options">
																		<i></i> <s:property />
																	</label>
																</div>
															</s:iterator>
															<s:if test="#ques.instructions != ''">
																<b>Instructions:<s:property value="instructions" /></b>
																<br>
															</s:if>
														</s:if>
														<s:if test="#ques.question_type== 'FILLIN'">
															<label id="question<s:property value="#no.index+1"/>"><%-- s:property
																	value="question" /> --%>
																	<s:text name="question" /></label>
															<div class="list-group-item list-group-item-multi">
																<input type="hidden"
																	name="questions_answers[<s:property value="#no.index+1"/>].question_id"
																	value="<s:property value="#ques.question_id"/>" /> 
																	<input
																	type="text" class="form-control no-border"
																	data-trigger="change" data-required="true"
																	data-type="alpha"
																	name="questions_answers[<s:property value="#no.index+1"/>].options"
																	id="questionArea">
															</div>
														</s:if>
													</s:iterator>

												</div>



											</div>
											<!--list group close-->

										</div>

										<!--step two close--->

										<div class="tab-pane" id="step3">This is step 3</div>
										<ul class="pager wizard m-b-sm">
											<li class="previous first" style="display: none;"><a
												href="#">First</a></li>
											<li class="previous"><a href="#">Previous</a></li>
											<li class="next last" style="display: none;"><a href="#">Last</a></li>
											<li class="next"><a href="#">Next</a></li>
										</ul>
									</div>
									<!--step three close--->

								</div>
							</div>
						</form>
					</div>
				</div>

				<!--row close-->


				<!---------->
			</section>
			<!-- section m-b-lg close -->
		</div>


	</section>
	<!-- footer -->
	<footer id="footer">
		<div class="text-center padder clearfix">
			<p>
				 <small>2014 &copy; CrunchPrep. A better way to study for the GRE.</small>
			</p>
		</div>
	</footer>

	<!-- / footer -->
	<script src="assets/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- App -->
	<script src="assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/js/app.plugin.js"></script>

	<script src="assets/js/parsley/parsley.min.js"></script>
	<script src="assets/js/wizard/jquery.bootstrap.wizard.js"></script>
	<script src="assets/js/wizard/demo.js"></script>


</body>
</html>
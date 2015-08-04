<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/js/chosen/chosen.css"
	type="text/css" />
<script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script src="assets/js/chosen/chosen.jquery.min.js"></script>

<link rel="stylesheet" href="assets/js/select2.1/select2.css"
	type="text/css" />

<link href="assets/plug-ins/toastr-master/toastr.min.css"
	rel="stylesheet" type="text/css" />
<script src="assets/plug-ins/toastr-master/toastr.min.js"
	type="text/javascript"></script>
</head>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						toastr.options = {
							"closeButton" : true,
							"debug" : false,
							"positionClass" : "toast-top-right",
							"onclick" : null,
							"showDuration" : "300",
							"hideDuration" : "1000",
							"timeOut" : "5000",
							"extendedTimeOut" : "1000",
							"showEasing" : "swing",
							"hideEasing" : "linear",
							"showMethod" : "fadeIn",
							"hideMethod" : "fadeOut"
						};

						$("#lesson").select2({
							maximumSelectionSize: 1
						});

						$("#difficultyLevelsCombo").select2({

						});
						$("#skillsCombo").select2({
							minimumSelectionSize : 3,
							maximumSelectionSize : 3,
							allowClear : true
						});
						$("#testNameCombo").select2({

						});
						
					//	//alert($('#difficultyLevel').val());
						$('#difficultyLevelsCombo').append(
								$('#difficultyLevel').val());
						$('#skillsCombo').append($('#skillLevels').val());
						$('#testNameCombo').append($('#testNames').val());
						$('#isFree').attr('value', 'PAID');
						$('#isRefferal').attr('value', 'NO');

						loadFormData();

						$('.typeClass').click(
								function() {
									var valueData = $(this).attr('name');
									var checked = $(this).prop("checked");

									if (checked) {
										if (valueData == 'uploadBO.feeType') {
											$('#isFree').attr('value', 'FREE');
										} else {
											$('#isRefferal').attr('value',
													'YES');
										}
									} else {
										if (valueData == 'uploadBO.feeType') {
											$('#isFree').attr('value', 'PAID');
										} else {
											$('#isRefferal')
													.attr('value', 'NO');
										}
									}
									

								});
						
						 $('#questionTitle').keyup(
									function() {
										var actualScore = $("#questionTitle")
										.val();
								var len = actualScore.length;
								 
										if (this.value.match(/[^a-zA-Z” ”]/g)) {
											this.value = this.value.replace(
													/[^a-zA-Z” ”]/g, '');
											toastr.warning("Numeric Characters are not accepted");
											 $('#questionTitle').focus();
										}
										else if (len > 200) {
											$("#questionTitle").val('');
											//$('#contact').attr('value','');
											toastr.warning("Question Title should not exceed 200 characters");
										}
									});
						

					});

	function clearAllCheckBoxes() {
		$(".choiceSelected").prop('checked', false);
		$('.answers').attr('value', '');

	}

	function choiceSelected(choiceBoxIndex, checkBox) {

		var textFieldValue = $('#choice' + choiceBoxIndex).val();
		////alert(textFieldValue);

		// //alert(' Text Field Value si: '+textFieldValue);

		//  //alert(' Text Filed Value is: '+textFieldValue);
		// $('#answer'+checkBoxSelectedLength).attr('value',textFieldValue);
		var inputData = textFieldValue;

		//  $('#answer1').attr('value',)

		$('.answers').each(function() {
			//	//alert(' input Data is: '+inputData);
			if (inputData != '') {
				////alert(' answer Value is: '+$(this).val());

				$(this).attr('value', inputData);
				inputData = '';

			}
		});

		////alert(choiceBoxIndex+' th Check Box Selected \n\t'+selectedCategory+"\n\t Present Selected COunt is :"+checkBoxSelectedLength);
	}
</script>


<script type="text/javascript">
	function formSubmission() {

		var typesSelection = $('.typeClass:checked').length;
		var testName = $('#testNameCombo').val();
		var lessonName = $('#lesson').val();
		var questionTitle = $('#questionTitle').val();
		var directions = $('textarea#directions').val();
		var editor1 = CKEDITOR.instances['editor1'].getData();
		var skillsCombo = $('#skillsCombo').val();
		var difficultyLevls = $('#difficultyLevelsCombo').val();
		var editor2 = CKEDITOR.instances['editor2'].getData();
		var editor3 = CKEDITOR.instances['editor3'].getData();
		var editor4 = CKEDITOR.instances['editor4'].getData();
		var solutionVideo = $('#solutionVideo').val();
		var selectedCategory = $('#selectedOption').attr('value');
		////alert('selected Category is: '+selectedCategory);
		var limitCount = 0;
		var textFieldCount = 0;
		limitCount = 5;
		var lessonsLength = $('#lessonslength').val();
		var loopNotEntered = true;
		
		//Choices********************************************************************** 
		var ch1 = $("#choice1").val();
		var ch2 = $("#choice2").val();
		var ch3 = $("#choice3").val();
		var ch4 = $("#choice4").val();
		var ch5 = $("#choice5").val();
		
		
		
		
		////alert(" Choice 1"+ch1);
		
		

		if (testName == -1) {
			toastr.warning(" Please Select Test Name ",
					" Test Name Not Selected ");
			$('#testNameCombo').focus();
			return false;
		} else if (lessonName == '' || lessonName == null) {
			toastr.warning("Please Select Lesson Name ",
					" Lesson name is not selected ");
			$('#lesson').focus();
			return false;
		} /* else if (lessonsLength > 0 && lessonName == null && lessonName.length<lessonsLength) {
			toastr.warning("You can select only " + lessonsLength + " lessons",
					" Please select " + lessonsLength + " lessons");
			$('#lesson').focus();
			return false;
		}
		else if (lessonsLength > 0 &&  lessonName.length<lessonsLength) {
			toastr.warning("You can select only " + lessonsLength + " lessons",
					" Please select " + lessonsLength + " lessons");
			$('#lesson').focus();
			return false;
		} */
		else if (questionTitle == '' || $.trim( questionTitle) == '') {
			toastr.warning("Please Enter Question Title ",
					" Question Title Not Be Empty ");
			$('#questionTitle').focus();
			return false;
		} else if (directions == '' || $.trim(directions) == '') {
			toastr.warning("Please Enter Question Direction",
					" Questions Direction Not Be Empty");
			$('#directions').focus();
			return false;

		} else if (editor1 == '' || $.trim(editor1) == '') {
			toastr.warning("Please Enter  Question Name",
					"Question Name must not Be Empty");
			$('#editor1').focus();
			return false;
		} else if (editor2 == '' || $.trim( editor2 ) == '') {
			toastr.warning("Please Enter Quant A Section",
					"Quant A Section must not be empty");
			////alert(' Please Enter Solution Text ');
			$('#editor2').focus();
			return false;
		} else if (editor3 == '' || $.trim(editor3 ) == '') {
			toastr.warning("Please Enter Quant B Section",
					"Quant B Section must not be empty");
			////alert(' Please Enter Solution Text ');
			$('#editor3').focus();
			return false;
		} 
		
		else if(ch1==ch2||ch1==ch3||ch1==ch4||ch1==ch5||ch2==ch3||ch2==ch4||ch2==ch5||ch3==ch4||ch3==ch5||ch4==ch5)
		{
			toastr.warning("Choice Values Must Be Differnt",	"No Two Choices have Same Values! ");
			return false;
		}
		else if(solutionVideo.length<5)
		{
			toastr.warning("Solution Video",	"Enter Valid Solution Video ");
			return false;
		}
		
		
		else {

			$('.choiceText').each(
					function() {

						textFieldCount = textFieldCount + 1;
						//	//alert(' textField Count is: '+textFieldCount+" Limit COunti si: "+limitCount);		
						if (textFieldCount <= limitCount) {
							if ($(this).val() == '' || $.trim( $(this).val() ) == '') {
								toastr.warning("Please Select Question Choice",
										" Question Choice Not Be Empty");
								$(this).focus();
								loopNotEntered = false;
								return false;
							}
						}

					});
			if (loopNotEntered) {

				if (loopNotEntered) {
					if ($(".choiceSelected:checked").length < 1) {
						toastr.warning("Please Select Question Answer",
								" Question Answer Not Be Empty");
						$(".choiceSelected:checked").focus();
						return false;
					} else if (skillsCombo != null && skillsCombo.length < 3) {

						toastr
								.warning("Please Select Atleast 3 Important Skills");
					} else if (skillsCombo == null) {
						toastr.warning("Please Select Question Skill Level",
								"Question Skill Level Not Selected !");
					}

					else if (difficultyLevls == -1
							|| difficultyLevls == 'Select  Difficulty' || difficultyLevls==null ||  difficultyLevls.length<1) {
						toastr.warning(
								"Please Select Question Difficulty Level",
								"Question Difficulty Level Not Selected !");
						$('#difficultyLevls').focus();
						return false;
					} else if (editor4 == '' || $.trim(editor4) == '') {
						toastr.warning("Please Enter Solution Text",
								"Solution Text must not be empty");
						////alert(' Please Enter Solution Text ');
						$('#editor4').focus();
						return false;
					} else {
						$('#questionText').attr('value',
								CKEDITOR.instances['editor1'].getData());
						$('#quantA').attr('value',
								CKEDITOR.instances['editor2'].getData());
						$('#quantB').attr('value',
								CKEDITOR.instances['editor3'].getData());
						$('#solutionText').attr('value',
								CKEDITOR.instances['editor4'].getData());
						var formSerialize = $('#dataForm').serialize();
						$.ajax({
							tye : 'POST',
							url : 'QuantComparisionUploading.action',
							data : formSerialize,
							dataType : 'json',
							success : formSuccessfllySubmited,
							errror : formSubmitionFailed

						});

					}
				}
			}
		}

		function formSuccessfllySubmited(jsonData, status) {

			if (jsonData.exceptionRaised) {
				toastr.error(jsonData.exceptionIs, "Operation Failed");
			} else {
				var questionId = jsonData.questionId;

				$('#questionId').attr('value', questionId);
				if (questionId == 0) {
					toastr.success("Data Saved",
							"Question Inserted Successfully");
					loadFormData();
					/* $('#operationName').attr('value',
							'Question Inserted Successfully');

					$('#questionForm').trigger('click');
 */
				} else {
					toastr.success("Data Updated",
							"Question Updated Successfully");
					/* $('#operationName').attr('value',
							'Question Updated Successfully');

					$('#questionForm').trigger('click'); */

				}

			}

		}
		function formSubmitionFailed(errorIs) {
			toastr.error(errorIs.responseText, "Operation Failed");

		}

	}
</script>


<script type="text/javascript">
	$(document).ready(function() {

		$('#resetButton').click(function() {		
			
			loadFormData();			
		});
	});
	function loadFormData() {
		var questionId = '<s:property value="#request.questionId"/>';		
		if (questionId > 0) {
			$('#operation').html("Update");
			var tableData = $('#tableData').val();
			var jsonTableData = JSON.parse(tableData);
			var testId = -1;
			var typeId = -1;
			var questionTitle = '';
			var Directions = '';
			var questionName = '';
			var lessons = [];
			var skillId1 = '';
			var skillId2 = '';
			var skillId3 = ''
			var difficultyId = '';
			var quantA = '';
			var quantB = '';
			var solutionText = '';
			var solutionVideo = '';
			var status = 'INACTIVE';
			var isFree = '';
			var isRefferal = '';
			var data = jsonTableData.tableData;
			var choices = [];
			var toolTips = [];
			var answers = [];
			$.each($.parseJSON(data), function(index, data) {
				testId = data.test_id;
				typeId = data.type_id;
				questionTitle = data.question_title;
				Directions = data.question_directions;
				questionName = data.question;
				skillId1 = data.skill_id1;
				skillId2 = data.skill_id2;
				skillId3 = data.skill_id3;
				difficultyId = data.diff_id;
				quantA = data.quantityA;
				quantB = data.quantityB;
				solutionText = data.solution_text;
				solutionVideo = data.solution_video;
				status = data.status;
				isFree = data.access_type;
				isRefferal = data.referral;
				if (choices.indexOf(data.choice) == -1)
					choices.push(data.choice);
				if (toolTips.indexOf(data.choice_tooltip) == -1)
					toolTips.push(data.choice_tooltip);
				if (answers.indexOf(data.answer) == -1)
					answers.push(data.answer);
				if (lessons.indexOf(data.sublesson_id) == -1)
					lessons.push(data.sublesson_id);
			});

			$('#lessonslength').attr('value', lessons.length);
			/* $("#lesson").select2({
				minimumSelectionSize : lessons.length,
				maximumSelectionSize : lessons.length
			}); */

			$.each(choices, function(index, value) {
				var index = parseInt(index + 1);
				$('#choice' + index).attr('value', value);
			});
			$.each(toolTips, function(index, value) {
				var index = parseInt(index + 1);
				$('#toolTip' + index).attr('value', value);
			});
			$.each(answers, function(index, value) {
				var index = parseInt(index + 1);
				$('#answer' + index).attr('value', value);
			});

			if (isRefferal == 'NO') {
				$('input[name="uploadBO.refferal"]').prop('checked', false);

			} else {
				$('input[name="uploadBO.refferal"]').prop('checked', true);
			}
			if (isFree == 'FREE') {
				$('input[name="uploadBO.feeType"]').prop('checked', true);

			} else {
				$('input[name="uploadBO.feeType"]').prop('checked', false);

			}
			if (status == 'ACTIVE') {
				$('#ACTIVE').trigger('click');
			} else {
				$('#INACTIVE').trigger('click');
			}

			//$('input:radio[name="uploadBO.status"]').prop('value',status);

			$('#questionTitle').val(questionTitle);
			$('#directions').val(Directions);
			$("#lesson").select2("val", lessons);
			$("#skillsCombo").select2("val", [ skillId1, skillId2, skillId3 ]);
			$("#testNameCombo").select2("val", [ testId ]);
			$("#difficultyLevelsCombo").select2("val", [ difficultyId ]);
			/*   $("#testNameCombo  option[value=" + testId + "]").attr("selected","selected");
			  $('#testNameCombo').val(testId); */

			/* $("#testNameCombo option").each(function (a, b) {
			 
			      if ($(this).val() == testId ){  //alert(testId); $(this).attr("selected", "selected");}
			  }); */

			CKEDITOR.instances['editor1'].setData(questionName);
			//$('#skillsCombo').val(skillId1);

			/*  $("#skillsCombo option").each(function (a, b) {
				 
			       if ($(this).val() == skillId1 ){  //alert(skillId1); $(this).val(skillId1);}
			   }); */

			CKEDITOR.instances['editor2'].setData(quantA);
			CKEDITOR.instances['editor3'].setData(quantB);
			CKEDITOR.instances['editor4'].setData(solutionText);
			$('#solutionVideo').val(solutionVideo);

			$('.answers').each(function() {
				var answer = $(this).val();
				$('.choiceText').each(function(index, value) {
					index = index + 1;
					var choiceText = $(this).val();
					if (answer == choiceText)
						$('#checkBox' + index).prop('checked', true);

				});
			});

		}
		
		else{
			$('#questionTitle').val('');
			$('#directions').val('');
			$('#solutionVideo').val('');
			$('#skillsCombo').select2('val','Select Skills');
			$('#testNameCombo').select2('val','');
			$('#difficultyLevelsCombo').select2('val','');
			$('#lesson').select2('val','Select Lessons');
			
			clearAllCheckBoxes();
			$(".typeClass").prop('checked', false);
			
			$(".choiceText").each(function() {
				$54(this).val('');			
			
			});
			$('#ACTIVE').trigger('click');
	    	CKEDITOR.instances.editor1.setData('');
	    	CKEDITOR.instances.editor2.setData('');
	    	CKEDITOR.instances.editor3.setData('');
	    	CKEDITOR.instances.editor4.setData('');
		}

	}
</script>

<body>


	<section id="content"> <s:hidden id="difficultyLevel"
		value="%{#request.difficultyLevels}" /> <s:hidden id="skillLevels"
		value="%{#request.skillLevels}" /> <s:hidden id="testNames"
		value="%{#request.testNames}" /> <s:hidden id="selectedOption"
		value="" /> <section class="vbox"> <section
		class="scrollable wrapper">

	<div class="row">
		<!--row start main-->
		<div class="col-lg-12">

			<div class="panel panel-default">
				<s:hidden id="lessonslength" value="0" />
				<s:hidden value="%{#request.requestedString}" id="requeststring"
					name="operation" />
				<s:if test="#request.questionId>0">
					<s:hidden id="tableData" value="%{#request.tableData}" />
				</s:if>
				<div class="panel-heading">
					<span class="panel-title">Quant Comparision</span>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-10 col-lg-offset-1" style="padding-top: 40px;">

							<form class="bs-example form-horizontal" id="dataForm">


								<s:hidden name="uploadBO.category_name"
									value="%{#request.operation}" />
								<s:hidden value="" name="uploadBO.feeType" id="isFree" />
								<s:hidden value="" name="uploadBO.refferal" id="isRefferal" />
								<s:hidden value="" name="uploadBO.question_type"
									id="question_type" />
								<s:hidden value="%{#request.questionId}"
									name="uploadBO.question_id" />



								<div class="form-group">
									<label class="col-lg-2  col-lg-offset-4  control-label">Type</label>

									<div class="checkbox i-checks col-lg-1">
										<label> <input type="checkbox" checked
											class="typeClass" name="uploadBO.feeType"> <i></i>
											Free
										</label>
									</div>
									<div class="checkbox i-checks col-lg-2">
										<label> <input type="checkbox" class="typeClass"
											name="uploadBO.refferal"> <i></i> Referral
										</label>
									</div>

								</div>
								<!--form group close-->


								<div class="form-group" id="id_100">
									<label class="col-lg-2 col-lg-offset-4 control-label">Test
										Name</label>
									<div class="col-lg-4">
										<select name="uploadBO.test_id" class="form-control m-b"
											id="testNameCombo">
											<!--  <option>Quantitative</option>
                                  <option>Verbal</option> -->
										</select>
									</div>
								</div>


								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Lesson
										Name</label>
									<div class="col-lg-5">
										<s:select id="lesson" class="form-control m-b"
											cssStyle="width: 80%;height: 34px;" list="lessons"
											multiple="true" headerKey="" headerValue=""
											name="uploadBO.lessons" data-required="true"
											data-placeholder="Select  Lessons">
										</s:select>
									</div>
								</div>


								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Question
										title</label>
									<div class="col-lg-4">
										<input type="text" class="form-control"
											name="uploadBO.question_title"
											placeholder="Enter Question Title" id="questionTitle" >
									</div>
								</div>


								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
									<div class="col-lg-3">
										<textarea cols="50" rows="4" style="max-width: 450px"
											id="directions" name="uploadBO.question_directions"></textarea>
									</div>
								</div>


								<div class="form-group">
									<label class="col-lg-2 control-label">Question</label>
									<div class="col-lg-10">
										<s:hidden name="uploadBO.question" id="questionText" />
										<textarea id="editor1" name="question" rows="10" cols="80"> </textarea>
										<script>
											CKEDITOR.replace('question')
										</script>
									</div>
								</div>


								<div class="form-group" id="single">
									<div class="row">

										<div class="col-lg-6 ">
											<s:hidden name="uploadBO.quantityA" id="quantA" />
											<div class="row">
												<label class="col-lg-2 control-label">Quant A</label><br>
												<br>
											</div>

											<div class="row">
												<div class="col-lg-12">
													<textarea id="editor2" name="quantitya" rows="10" cols="80"> </textarea>
													<script>
														CKEDITOR
																.replace('quantitya')
													</script>
												</div>
											</div>
										</div>
										<!--col close-->

										<div class="col-lg-6 ">
											<s:hidden name="uploadBO.quantityB" id="quantB" />
											<div class="row">
												<label class="col-lg-2 control-label">Quant B</label><br>
												<br>
											</div>

											<div class="row">
												<div class="col-lg-12">
													<textarea id="editor3" name="quantityb" rows="10" cols="80"> </textarea>
													<script>
														CKEDITOR
																.replace(
																		'quantityb')
													</script>
												</div>
											</div>

										</div>
										<!--col close-->

									</div>
								</div>



								<div class="form-group" id="single">

									<div class="row">

										<div class="col-lg-6 ">

											<div class="row">
												<!--row for internal choices -->
												<label class="col-lg-2 col-lg-offset-2  control-label">Choice
													1</label>
												<div class="col-lg-6">
													<input type="text" class="choiceText form-control" placeholder=""
														id="choice1" name="uploadBO.choices" >
												</div>

												<div class="radioo i-checks col-lg-2">
													<label> <input type="radio" name="b"
														value="option1" class="choiceSelected"
														onclick="choiceSelected(1,this)" id="checkBox1"> <i></i>
													</label>
												</div>

											</div>
											<!--row close for internal choices 1-->

											<br>

											<div class="row">
												<!--row for internal choices -->
												<label class="col-lg-2 col-lg-offset-2  control-label">Choice
													2</label>
												<div class="col-lg-6">
													<input type="text" class="choiceText form-control" placeholder=""
														id="choice2" name="uploadBO.choices">
												</div>

												<div class="radioo i-checks col-lg-2">
													<label> <input type="radio" name="b"
														value="option1" class="choiceSelected"
														onclick="choiceSelected(2,this)" id="checkBox2"> <i></i>
													</label>
												</div>

											</div>
											<!--row close for internal choices 2-->

											<br>

											<div class="row">
												<!--row for internal choices -->
												<label class="col-lg-2 col-lg-offset-2  control-label">Choice
													3</label>
												<div class="col-lg-6">
													<input type="text" class="choiceText form-control" placeholder=""
														id="choice3" name="uploadBO.choices">
												</div>

												<div class="radioo i-checks col-lg-2">
													<label> <input type="radio" name="b"
														value="option1" class="choiceSelected"
														onclick="choiceSelected(3,this)" id="checkBox3"> <i></i>
													</label>
												</div>

											</div>
											<!--row close for internal choices 3-->

											<br>

											<div class="row">
												<!--row for internal choices -->
												<label class="col-lg-2 col-lg-offset-2  control-label">Choice
													4</label>
												<div class="col-lg-6">
													<input type="text" class="choiceText form-control" placeholder=""
														id="choice4" name="uploadBO.choices">
												</div>

												<div class="radioo i-checks col-lg-2">
													<label> <input type="radio" name="b"
														value="option1" class="choiceSelected"
														onclick="choiceSelected(4,this)" id="checkBox4"> <i></i>
													</label>
												</div>
											</div>
											<!--row close for internal choices 4-->

											<br>

											<div class="row">
												<!--row for internal choices -->
												<label class="col-lg-2 col-lg-offset-2  control-label">Choice
													5</label>
												<div class="col-lg-6">
													<input type="text" class="choiceText form-control" placeholder=""
														id="choice5" name="uploadBO.choices">
												</div>

												<div class="radioo i-checks col-lg-2">
													<label> <input type="radio" name="b"
														value="option1" class="choiceSelected"
														onclick="choiceSelected(5,this)" id="checkBox5"> <i></i>
													</label>
												</div>
											</div>
											<!--row close for internal choices 5-->


										</div>
										<!--Col for choices close-->

										<div class="col-lg-6">
											<div class="form-group">
												<label class="col-lg-2 col-lg-offset-1 control-label">Answer
													1</label>
												<div class="col-lg-6">
													<input type="text" class="answers form-control" readonly
														placeholder="Answer 1" id="answer1"
														name="uploadBO.answers">
												</div>
											</div>

										</div>

									</div>

								</div>
								<!--Form group close for choices-->






								<br>

								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Skills</label>
									<div class="col-lg-3">
									<s:select id="skillsCombo" cssStyle="width: 100%;" list="#{}"
											multiple="true" headerKey="" headerValue=""
											name="uploadBO.skills" data-required="true"
											data-placeholder="Select Skills">
										</s:select>
										<%--  <select style="width:230px" multiple class="chosen-select" name="uploadBO.skillid1" class="form-control m-b" id="skillsCombo">
                                  
                                </select>  --%>


									</div>
								</div>


								<div class="form-group">

									<label class="col-lg-2 col-lg-offset-4 control-label">Difficulty
										level</label>
									<div class="col-lg-3">

										<s:select id="difficultyLevelsCombo"
											cssStyle="width: 100%;height: 34px;" list="#{}" headerKey=""
											headerValue="" name="uploadBO.difficulty_id"
											data-required="true" data-placeholder="Select  Difficulty">
										</s:select>
										<%--  <select class="form-control m-b" id="difficultyLevelsCombo" name="uploadBO.difficulty_id">
                                      
                                     </select> --%>
									</div>

								</div>


								<div class="form-group">
									<label class="col-lg-2 control-label">Solution Text</label>
									<s:hidden id="solutionText" name="uploadBO.solution_text" />
									<div class="col-lg-10">
										<textarea id="editor4" name="solutiontext" rows="10" cols="80"> </textarea>
										<script>
											CKEDITOR.replace('solutiontext')
										</script>
									</div>
								</div>


								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-2 control-label">Solution
										Video</label>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder=""
											id="solutionVideo" name="uploadBO.solution_video">
									</div>
								</div>


								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-3 control-label">Status</label></label>
									<div class="radioo i-checks col-lg-2">
										<label> <input type="radio" name="uploadBO.status"
											value="ACTIVE" checked id="ACTIVE"> <i></i> Active
										</label>
									</div>

									<div class="radioo i-checks col-lg-2">
										<label> <input type="radio" name="uploadBO.status"
											value="INACTIVE" id="INACTIVE"> <i></i> InActive
										</label>
									</div>
								</div>



								<div class="form-group">
									<div
										class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
										<button type="button" class="btn btn-success"
											onClick="formSubmission()">
											<label id="operation">Submit</label>
										</button>
									</div>
									<div class="col-lg-offset-1 col-lg-1">
										<button type="reset" id="resetButton" class="btn btn-danger">Reset</button>
									</div>
									<div class="col-lg-offset-1 col-lg-1">
									<a href="quant-comparision-questions.action" class="auto btn btn-default">View List</a>
<!-- 									<button type="button" class="btn btn-success" onclick="cleardata()">Clear</button> -->
									</div>
								</div>
								<!--form group close-->
							</form>


						</div>
						<!--col for skill level close-->

					</div>
					<!--Inner row1 close -->





				</div>
				<!--panel body close-->
			</div>
			<!--panel head close-->

		</div>
		<!--div close-->
	</div>
	<!--row start main End--> </section> </section> <a href="#"
		class="hide nav-off-screen-block" data-toggle="class:nav-off-screen"
		data-target="#nav"></a> 
		<%-- <s:form
		action="quant-comparision-questions.action" method="post">
		<s:hidden name="operationName" id="operationName" value="" />
		<s:submit id="questionForm" value="quant-comparision-questions"
			style="display:none" />
	</s:form> --%> </section>



	<script src="assets/js/select2.1/select2.min.js"></script>


	<script type="text/javascript">
		CKEDITOR.replace('editor1', {
			entities : false,
			basicEntities : false,
			entities_greek : false,
			entities_latin : false,
			htmlDecodeOutput : true,
			forceSimpleAmpersand : true,
		});
	</script>
	<script type="text/javascript">
		CKEDITOR.replace('editor2', {
			entities : false,
			basicEntities : false,
			entities_greek : false,
			entities_latin : false,
			htmlDecodeOutput : true,
			forceSimpleAmpersand : true,
		});
	</script>
	<script type="text/javascript">
		CKEDITOR.replace('editor3', {
			entities : false,
			basicEntities : false,
			entities_greek : false,
			entities_latin : false,
			htmlDecodeOutput : true,
			forceSimpleAmpersand : true,
		});
	</script>

	<script type="text/javascript">
		CKEDITOR.replace('editor4', {
			entities : false,
			basicEntities : false,
			entities_greek : false,
			entities_latin : false,
			htmlDecodeOutput : true,
			forceSimpleAmpersand : true,
		});

		$("#lesson").select2({
			
		});
	</script>
</body>
</html>
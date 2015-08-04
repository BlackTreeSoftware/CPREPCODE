<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/css/practice.css" type="text/css" />

<!---Styles for ul list-->
<link rel="stylesheet" href="assets/js/dropdown/practice-ul.css"
	type="text/css" />

<link rel="stylesheet" href="assets/js/chosen/chosen.css"
	type="text/css" />
<link rel="stylesheet" href="assets/css/practiceSession/customSetup.css"
	type="text/css" />
	 
<!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->


<script src="assets/js/chosen/chosen.jquery.min.js"></script>
<script src="assets/js/app.plugin.js"></script>
<script src="assets/js/jquery.min.js"></script>
<!-- App -->


<!--Nestable list js-->

<script>
	$(document).ready(function() {
	 
		
		$('#time').val("0");
		//$('#timeLimit').val("on");
		$('#questionslimits').val("0");
		
		var percentageCount = JSON.parse($('#percentageCount').val());
        var quantPercentCount;
   if(percentageCount.isDataAvailable){
		quantPercentCount = (percentageCount.percentageCount);
		$.each(JSON.parse(quantPercentCount), function(key, val) {
		
			//alert(val.quantComparison);
			
				if(val.math<50)
					$('#mathCount').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.verbal<50)
					$('#verbalCount').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.quantComparison<50)
					$('#quantComparison').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.problemSolving<50)
					$('#problemSolving').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.problemSolvingSingle<50)
					$('#problemSolvingSingle').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.problemSolvingDouble<50)
					$('#problemSolvingDouble').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.numericeEntrySingle<50)
					$('#numericeEntrySingle').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.numericEntryDouble<50)
					$('#numericEntryDouble').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.dataInterpretation<50)
					$('#dataInterpretation').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.sentenceEquivalence<50)
					$('#sentenceEquivalence').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.textCompletion<50)
					$('#textCompletion').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.textCompletionSingle<50)
					$('#textCompletionSingle').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.textCompletionDouble<50)
					$('#textCompletionDouble').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.textCompletiontriple<50)
					$('#textCompletiontriple').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.readingComprehension<50)
					$('#readingComprehension').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.readingCompLong<50)
					$('#readingCompLong').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.readingCompShort<50)
					$('#readingCompShort').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				if(val.criticalreasoning<50)
					$('#criticalReasoning').removeClass( "badge bg-success l-s-p-c" ).addClass( "badge bg-danger l-s-p-c" );
				
				
				$('#mathCount').html(val.math);			
				$('#verbalCount').html(val.verbal);			
				$('#quantComparison').html(val.quantComparison);		
				$('#problemSolving').html(val.problemSolving);			
				$('#problemSolvingSingle').html(val.problemSolvingSingle);		
				$('#problemSolvingDouble').html(val.problemSolvingDouble);			
				$('#numericeEntrySingle').html(val.numericeEntrySingle);			
				$('#numericEntryDouble').html(val.numericEntryDouble);			
				$('#dataInterpretation').html(val.dataInterpretation);			
				$('#sentenceEquivalence').html(val.sentenceEquivalence);			
				$('#textCompletion').html(val.textCompletion);			
				$('#textCompletionSingle').html(val.textCompletionSingle);		
				$('#textCompletionDouble').html(val.textCompletionDouble);			
				$('#textCompletiontriple').html(val.textCompletiontriple);			
				$('#readingComprehension').html(val.readingComprehension);			
				$('#readingCompShort').html(val.readingCompShort);			
				$('#readingCompLong').html(val.readingCompLong);
				$('#criticalReasoning').html(val.criticalreasoning);
				

		});
}else{
	
}
		
		var tableData = JSON.parse($('#tableData').val());

		var quantitative = tableData.Quantitative;

		var verbal = tableData.Verbal;
		$('#mathQuestions').attr('value', quantitative.QuantitativeHierarchy);
		$('#verbalQuestions').attr('value', verbal.VerbalHierarchy);
		
		$("#mathradio").click(function() {
			$("#quant").show();
			$("#verbal").hide();
			$('#hierarichyTable').html($('#mathQuestions').attr('value'));

		});

		$("#verbalradio").click(function() {
			$("#quant").hide();
			$("#verbal").show();
			$('#hierarichyTable').html($('#verbalQuestions').attr('value'));
		});
	
		//problem solving
		
		$("input:radio[name='a']").click(function() {
			finalOperation($(this));
			$('.parents').attr('checked', false);

		});
		
		$("#mathradio").trigger('click');
		 
		$('input[type=checkbox]').click(function() {
			finalOperation($(this));
		});
		
		 $('#start_test').click(function(){
			
			
			 var firstname=$('#practisesessioname').val(); 
			  if (firstname  == "") {
				 //toastr.error('Please enter Practice Session Name'); 
				 $('#span').html('Please enter Practice Session Name');
			    }
			 else if(firstname.indexOf(" ")==0  )
         	{ 
         	//toastr.error("No space please and don't leave Practice Session Name empty");
         	 $('#span').html("No space please and don't leave Practice Session Name empty");
         	}
         
         else if((firstname.length)>50)
         {
         	//toastr.error("Entered Lesson Name is Too Long ,please try to reduce the lesson name size");
         	 $('#span').html('Entered Practice Session Name is Too Long ,please try to reduce the lesson name size');
         }
			 else
				 {
				 $('#span').html('');
				 
			// alert("Starting Practice TEst"+$('#dificultyId').val());
			  var isInAdaptiveMode="";
			  if($('#adaptiveMode').is(':checked'))
				  {
				  isInAdaptiveMode="YES";
				  }
			  else
				  {
				  isInAdaptiveMode="NO";
				  }
			  var timeLimit = $('input:radio[name=d-s-r]:checked').val(); 
			 $('#timeLimit').val(timeLimit);
			//  alert("The data is"+$('#practisesessioname').val());
			$('#practisetestname').val($('#practisesessioname').val());
			$('#adaptiveModeVal').val(isInAdaptiveMode);
		    $('#questionsCountForm').attr('action','Get-Practice-Session-Questions.action');
		    $('#questionsCountForm').submit();
				 }
		 });
		 
		 $("#adaptiveMode").change(function() {
			    if(this.checked) {
			       
			        $('.alldificulty').prop('checked', false);
			        $('#veryEasyLevel').prop('checked', false);
			        $('#easyLevel').prop('checked', false);
			        $('#HardLevel').prop('checked', false);
			        $('#verdHardLevel').prop('checked', false);
			        $('#mediumLevel').prop('checked', false);
			        
			        $('.alldificulty').attr('disabled', true);
			        $('#veryEasyLevel').attr('disabled', true);
			        $('#easyLevel').attr('disabled', true);
			        $('#HardLevel').attr('disabled', true);
			        $('#verdHardLevel').attr('disabled', true);
			        $('#mediumLevel').attr('disabled', true);
			        
			    }
			    if(!this.checked) { 
			    	
			    	    $('.alldificulty').removeAttr('disabled');
				        $('#veryEasyLevel').removeAttr('disabled');
				        $('#easyLevel').removeAttr('disabled');
				        $('#HardLevel').removeAttr('disabled');
				        $('#verdHardLevel').removeAttr('disabled');
				        $('#mediumLevel').removeAttr('disabled');
			    }
			});
		 
		 
	});
</script>
<script type="text/javascript">


function timevalue(val1)
{
	//alert("The Time value data"+val1);
	$('#time').val(val1);
	$("#mathradio").trigger('click');
}
function questionslimitdata(val1)
{
//	alert("In Questions Limit Data----------------------"+val1);
	$('#questionslimits').val(val1);
	$("#mathradio").trigger('click');
}




	function finalOperation(checkBox) {
		// alert(' check box Selected ');
		var section = $("input:radio[name='a']:checked").val();
		var difficulties = [];
		var reuseMode = [];
		var mathType = [];
		var mathCategory = [];
		var verbalType = [];
		var verbalCategory = [];
		var parents = [];
		var childs = [];
		
		 var comprehenssion=[];
		 var ANSWERED=[];
		 var flag=[];
		 var GUESSED=[];
		
		 $('#sectionId').attr('value',section);
		 
		 $('input.ANSWERED:checked').each(function() {
			 ANSWERED.push($(this).val());
			// alert("The answered value"+$(this).val());
			});
		 $('input.FLAG:checked').each(function() {
			 flag.push($(this).val());
			});
		 $('input.GUESSED:checked').each(function() {
			 GUESSED.push($(this).val());
			});
		 
		 
		 $('input.compre:checked').each(function() {
			 comprehenssion.push($(this).val());
			});

		$('input.mcati:checked').each(function() {
			mathCategory.push($(this).val());
		});
		$('input.vcati:checked').each(function() {
			verbalCategory.push($(this).val());
		});
		$('input.mtype:checked').each(function() {
			mathType.push($(this).val());
		});
		$('input.mtype3:checked').each(function() {
			mathType.push(11);
			mathType.push(12);
			mathType.push(16);
			mathType.push(17);
		});
		$('input.mtype2:checked').each(function() {
			mathType.push(7); 
		});
		
		$('input.vtype:checked').each(function() {
			verbalType.push($(this).val());
		});
		
		$('input.vtype2:checked').each(function() {
			verbalType.push(6);
		});
		
		$('input.vtype3:checked').each(function() {
			verbalType.push(4);
			verbalType.push(5);
		});

		$('input.vtype4:checked').each(function() {
			verbalType.push(15);
		});
		
		$('input.dificulty:checked').each(function() {
			difficulties.push($(this).val());
		});
		$('input.questionType:checked').each(function() {
			reuseMode.push($(this).val());
		});
		$('input.parents:checked').each(function() {
			parents.push($(this).val());
		});
		$('input.childs:checked').each(function() {
			childs.push($(this).val());
		});

	/* 	alert(' \n section si: ' + section + "\n\t Difficulty LEvelts is: "
				+ difficulties + "\n Reuse Mode is: " + reuseMode);
		if (section == 1)
			alert(' \n Math Category is: ' + mathCategory + "\n Math Type is: "
					+ mathType);
		else
			alert("\n Verbal Type is: " + verbalType
					+ "\n Verbal Category is : " + verbalCategory);
		alert(" \n parents List is: " + parents + " \n CHild List is : "
				+ childs); */
				
			    var time=$('#time').val();
				//alert("the time value is"+time);
				$('#time').attr('value',time);
		$('#dificultyId').attr('value',difficulties);
		$('#comprehenssion').attr('value',comprehenssion);
		$('#reuseModeId').attr('value',reuseMode);
		$('#parent').attr('value',parents);
		$('#child').attr('value',childs);
		$('#FLAG').attr('value',flag);
		 $('#GUESSED').attr('value',GUESSED);
		 $('#ANSWERED').attr('value',ANSWERED);
		if(section==1){
		$('#catigorie').attr('value',mathCategory);
		$('#type').attr('value',mathType);
		}
		else{
			$('#catigorie').attr('value',verbalCategory);
			$('#type').attr('value',verbalType);
		}
		
		questionsCount();
	 
	}
	function questionsCount(){
	//	alert(' in questions Coutn ');
		 var formData=$('#questionsCountForm').serialize();
		 $.ajax({
				tye : 'POST',
				url : 'getQuestionsCount.action',
				data: formData,
				success : dataReady,
				errror : dataGettinFailed
			});
	 }
	 
	 function dataReady(jsonData)
	 {
		
	//	 alert(' in Json Data'+jsonData+" index is; "+jsonData.indexOf("yesData"));
		if(jsonData.indexOf("yesData") > -1){
			var data= jsonData.replace("yesData","");
			// alert(' Yes json Data Contains '+data); 
			$('#start_test').removeAttr('disabled'); 
			$('#questionsCount').html(data); 
			 if($('#questionCountNumber').html()==0)
				 {
				 $('#start_test').attr('disabled',true);
				 }
			
		}else{ $('#questionsCount').html(0);} 
		
		 
		/* if(jsonData.isDataAvilable)
		 $('#questionsCount').html(jsonData.rowCount);
		else $('#questionsCount').html(0); */
	 }
	 function dataGettinFailed(errorIs)
	 {
		  //alert(' Error Raised \n Error is; '+errorIs.responseText);
	 }
	 </script> 
</head>
<body>
	<s:hidden id="tableData" value="%{#request.tableData}" />
	<s:hidden id="percentageCount" value="%{#request.percentageCount}" />
	<s:hidden id="mathQuestions" value="" />
	<s:hidden id="verbalQuestions" value="" />

	<section id="content" id="frm"> 
	<section class="vbox">
	 <section class="scrollable wrapper">

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-white">
				<div class="panel-heading">
					<div class="panel-title">Practice Session</div>
				</div>
				<div class="panel-body">
					<div class="col-lg-12 text-center">
						<h4 class="text-center">Step-1: Choose Subject</h4>
						<hr />
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label"><h5>Subject</h5></label>
							<div class="col-sm-8 col-sm-offset-0">

								<div class="radio i-checks">
									<label> <input type="radio" id="mathradio" name="a"
										value="1" checked><i class="hide-for-label"></i>Math
									</label> <span class="badge bg-success l-s-p-c d-b-in" id="mathCount"></span>
								</div>

								<div class="radio i-checks ">
									<label> <input type="radio" id="verbalradio" name="a"
										value="2"><i class="hide-for-label"></i>Verbal
									</label> <span class="badge bg-success l-s-p-c d-b-in" id="verbalCount"></span>
								</div>

							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-lg-7 col-lg-offset-1">
						<div class="form-group" id="quant">
							<label class="col-sm-4 control-label"><h5>Question
									Type</h5></label>
							<div class="col-sm-8">
								<div class="checkbox i-checks">
									<label><input type="checkbox" class="mcati mtype2" value="4" /><i></i><b>
											Quantitative Comparison </b></label> <span
										class="badge bg-success l-s-p-c" id="quantComparison"></span>
								</div>


								<div class="checkbox i-checks">
									<label><input type="checkbox"
										onclick="$('.6type').prop('checked',$(this).prop('checked'))"
										class="mcati" id="psolving" value="6" /><i></i><b> Problem
											Solving </b></label> <span class="badge bg-success l-s-p-c"
										id="problemSolving"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="mtype 6type"
										id="psingle" value="8" /><i></i> Single Answer</label> <span
										class="badge bg-success l-s-p-c" id="problemSolvingSingle"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="mtype 6type"
										id="pmultiple" value="9" /><i></i> Multiple Answer</label> <span
										class="badge bg-success l-s-p-c" id="problemSolvingDouble"></span>
								</div>


								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="mtype 6type"
										id="nsingle" value="10" /><i></i> NumericEntry Single</label> <span
										class="badge bg-success l-s-p-c" id="numericeEntrySingle"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="mtype 6type"
										id="ndouble" value="13" /><i></i> NumericEntry Double</label> <span
										class="badge bg-success l-s-p-c" id="numericEntryDouble"></span>
								</div>

								<div class="checkbox i-checks">
									<label><input type="checkbox" class="mcati mtype3"
										id="ndouble" value="5" /><i></i><b> Data Interpretation </b></label>
									<span class="badge bg-success l-s-p-c" id="dataInterpretation"></span>
								</div>

							</div>


						</div>

						<div class="form-group" id="verbal">
							<label class="col-sm-4 control-label"><h5>Question
									Type</h5></label>
							<div class="col-sm-8">
								<div class="checkbox i-checks">
									<label><input type="checkbox" class="vcati vtype2" value="2" /><i></i><b>
											Sentence Equivalence </b></label> <span class="badge bg-success l-s-p-c"
										id="sentenceEquivalence"></span>
								</div>

								<div class="checkbox i-checks">
									<label><input type="checkbox"
										onclick="$('.1type').prop('checked',$(this).prop('checked'))"
										class="vcati" id="tcompletion" value="1" /><i></i><b>
											Text Completion </b></label> <span class="badge bg-success l-s-p-c"
										id="textCompletion"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="vtype 1type"
										id="tsingle" value="1" /><i></i> Single Blank</label> <span
										class="badge bg-success l-s-p-c" id="textCompletionSingle"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="vtype 1type"
										id="tdouble" value="2" /><i></i> Double Blank</label> <span
										class="badge bg-success l-s-p-c" id="textCompletionDouble"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="vtype 1type"
										id="ttriple" value="3" /><i></i> Triple Blank</label> <span
										class="badge bg-success l-s-p-c" id="textCompletiontriple"></span>
								</div>



								<div class="checkbox i-checks">
									<label><input type="checkbox"
										onclick="$('.3type').prop('checked',$(this).prop('checked'))"
										class="vcati vtype3" id="rcomprehension" value="3" /><i></i><b>
											Reading Comprehension </b></label> <span class="badge bg-success l-s-p-c"
										id="readingComprehension"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="vtype 3type "
										id="rsingle" value="'SHORT'" /><i></i> SHORT</label> <span
										class="badge bg-success l-s-p-c" id="readingCompShort"></span>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label><input type="checkbox" class="vtype 3type"
										id="rmultiple" value="'LONG'" /><i></i> LONG</label> <span
										class="badge bg-success l-s-p-c" id="readingCompLong"></span>
								</div>
                            <div class="checkbox i-checks">
									<label><input type="checkbox" class="vcati vtype4" value="9" /><i></i><b>
											Critical Reasoning </b></label> <span class="badge bg-success l-s-p-c"
										id="criticalReasoning"></span>
								</div>
								<!-- <div class="checkbox i-checks">
															<label><input type="checkbox" /><i></i> Select In</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div> -->
								<div class="checkbox i-checks">
									<label>
								</div>
							</div>
						</div>
					</div>
					<!--col-lg-close-->

					<div class="col-lg-12" style="margin-top: 20px;">
						<h4 class="text-center">Step-2: Select Lessons</h4>
						<hr />
						<div class="col-lg-3 col-sm-6 list-scroller " id="hierarichyTable">
							<div class="form-group">
								<div class="checkbox i-checks">
									<label> <input type="checkbox" /><i></i> <strong>Algebra</strong>
										<span class="badge bg-success l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label> <input type="checkbox" /><i></i> Algebraic
										Expressions <span class="badge bg-success l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label> <input type="checkbox" /><i></i> Factoring <span
										class="badge bg-success l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label> <input type="checkbox" /><i></i> Simultaneous
										Linear Equ... <span class="badge bg-success l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks m-l-20">
									<label> <input type="checkbox" /><i></i> Inequalities
										<span class="badge bg-success l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks">
									<label> <input type="checkbox" /><i></i> Geometry <span
										class="badge bg-primary l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks">
									<label> <input type="checkbox" /><i></i> Word Problems
										<span class="badge bg-danger l-s-p-c"></span>
									</label>
								</div>

								<div class="checkbox i-checks">
									<label> <input type="checkbox" /><i></i> Number
										Properties <span class="badge bg-success l-s-p-c"></span>
									</label>
								</div>
							</div>
						</div>


					</div>
					<!-- end col-md-12 -->

					<div class="col-lg-12" style="margin-top: 20px;">
						<h4 class="text-center">Step-3: Select Setting</h4>
						<hr />
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label"><h5>Difficulty</h5></label>
								<div class="col-sm-7">
									<div class="checkbox i-checks">
										<label> <input type="checkbox" id="adaptiveMode"/><i></i> Adaptive
										</label>
									</div>

									<hr />

									<div class="checkbox i-checks">
										<label> <input type="checkbox" class="alldificulty"
											onClick="$('.dificulty').prop('checked',$(this).prop('checked'))" /><i></i>
											All Difficulties
										</label>
									</div>

									<div class="checkbox i-checks">
										<label> <input type="checkbox" value=5
											class="dificulty" id="verdHardLevel"/><i></i> Very Hard
										</label>
									</div>

									<div class="checkbox i-checks">
										<label> <input type="checkbox" value=4
											class="dificulty" id="HardLevel"/><i></i> Hard
										</label>
									</div>

									<div class="checkbox i-checks">
										<label> <input type="checkbox" value=3
											class="dificulty" id="mediumLevel"/><i></i> Medium
										</label>
									</div>

									<div class="checkbox i-checks">
										<label> <input type="checkbox" value=2
											class="dificulty" id="easyLevel"/><i></i> Easy
										</label>
									</div>

									<div class="checkbox i-checks">
										<label> <input type="checkbox" value=1
											class="dificulty" id="veryEasyLevel"/><i></i> Very Easy
										</label>
									</div>
								</div>
							</div>
						</div>
						<!--col-lg-close-->

						<div class="col-lg-3 col-lg-offset-1">
							<div class="form-group">
								<label class="col-sm-5 control-label"><h5>Reuse
										Mode</h5></label>
								<div class="col-sm-7">
									<div class="checkbox i-checks">
										<label><input type="checkbox"
											onclick="$('.questionType').prop('checked',$(this).prop('checked'))" /><i></i>
											All Questions</label>
									</div>
									
									<div class="checkbox i-checks">
										<label> <input type="checkbox" class="questionType ANSWERED"
											value="'SKIPPED'" /><i></i> Unanswered
										</label>
									</div>

									<div class="checkbox i-checks">
										<label> <input type="checkbox" class="questionType ANSWERED"
											value="'CORRECT','INCORRECT'" onclick="$('.ANSWER').prop('checked',$(this).prop('checked'))"/><i></i> Answered
										</label>
									</div>

									

									<div class="checkbox i-checks m-l-20">
										<label> <input type="checkbox" class="questionType ANSWERED ANSWER"
											value="'CORRECT'" /><i></i> Correct
										</label>
									</div>

									<div class="checkbox i-checks m-l-20">
										<label> <input type="checkbox" class="questionType ANSWERED ANSWER"
											value="'INCORRECT'" /><i></i> Incorrect
										</label>
									</div>

									<div class="checkbox i-checks m-l-20">
										<label> <input type="checkbox" class="questionType FLAG ANSWER"
											value="'FLAG'" /><i></i> Flagged
										</label>
									</div>

									<div class="checkbox i-checks m-l-20">
										<label> <input type="checkbox" class="questionType GUESSED ANSWER"
											value="'GUESS'" /><i></i> Guessed
										</label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-4 col-lg-offset-1">
							<div class="form-group">

								<label class="col-sm-5 control-label"><h5>Time</h5></label>
								<div class="col-sm-7">
									<div class="btn-group m-r">
			 							<button data-toggle="dropdown"
											class="btn btn-sm btn-success dropdown-toggle">
											<span class="dropdown-label">Select time</span> <span
												class="caret"></span>
										</button>
										<ul class="dropdown-menu dropdown-select">
											<li onclick="timevalue('0');"><a href="#"><input type="radio" value="0" name="timeMode"  > All </a></li>
											<li onclick="timevalue('1');"><a href="#"><input  type="radio" value="1"  name="timeMode"     class="dee"> <1 Minute </a></li>
																	<li onclick="timevalue('2');"><a href="#"><input  type="radio" value="2" name="timeMode" class="dee"> 1 - 2 Minutes </a></li>
																	<li onclick="timevalue('3');"><a href="#"><input  type="radio" value="3"  name="timeMode" class="dee"> >3 Minutes </a></li>

										</ul>
									</div>
								</div>

								<div class="clearfix"></div>

								<label class="col-sm-5 control-label"><h5>Time Mode</h5></label>
								<div class="col-sm-7">
									<div class="btn-group m-r">
										<button data-toggle="dropdown"
											class="btn btn-sm btn-success dropdown-toggle">
											<span class="dropdown-label">Select time</span> <span
												class="caret"></span>
										</button>
										<ul class="dropdown-menu dropdown-select">
											<li><a href="#"><input type="radio" name="d-s-r"
													checked="" class="timeLimit" value="No Limit"> No Limit </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="5">
													5 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="10">
													10 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="15">
													15 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="20">
													20 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="25">
													25 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value=30">
													30 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="35">
													35 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="40">
													40 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="45">
													45 minutes </a></li>
											<li><a href="#"><input type="radio" name="d-s-r" value="60">
													60 minutes </a></li>
										</ul>
									</div>
								</div>

								<div class="clearfix"></div>

								<label class="col-sm-5 control-label"><h5>Total
										Questions</h5></label>
								<div class="col-sm-7">
									<div class="btn-group m-r">
										<button data-toggle="dropdown"
											class="btn btn-sm btn-success dropdown-toggle">
											<span class="dropdown-label">Select Questions</span> <span
												class="caret"></span>
										</button>
										<ul class="dropdown-menu dropdown-select">
											<li  onclick="questionslimitdata('0');"><a href="#"><input type="radio" name="d-s-r"
													> No Limit </a></li>
											<li  onclick="questionslimitdata('5');"><a href="#"><input type="radio" name="d-s-r">
													5 questions </a></li>
											<li  onclick="questionslimitdata('10');"><a href="#"><input type="radio" name="d-s-r">
													10 questions </a></li>
											<li  onclick="questionslimitdata('15');"><a href="#"><input type="radio" name="d-s-r">
													15 questions </a></li>
											<li  onclick="questionslimitdata('20');"><a href="#"><input type="radio" name="d-s-r">
													20 questions </a></li>
											<li  onclick="questionslimitdata('25');"><a href="#"><input type="radio" name="d-s-r">
													25 questions </a></li>
											<li  onclick="questionslimitdata('30');"><a href="#"><input type="radio" name="d-s-r">
													30 questions </a></li>
										</ul>
									</div>
								</div>



								<div class="clearfix"></div>

								<label class="col-sm-12 control-label m-t-10"><h5>Give
										this practice session a name</h5></label>
								<div class="col-sm-12">
									<input type="text" class="form-control"  id="practisesessioname"
										placeholder="Free Practice Session 1" name="practiceSessionName"  >
										<span id="span" style="color:red"></span>
								</div>
							</div>
						</div>
					</div>
					<!-- end col-md-12 -->
				</div>
				<!--panel body close-->

				<footer class="panel-footer bg-light lter">
				<!-- <a href="Get-Practice-Session-Questions.action" class="btn btn-success" id="start_test">Start Practice</a> -->
				<input type="button"  class="btn btn-success"  value="Start Practice" id="start_test">
				<div class="col-lg-6 col-lg-offset-0 pull-right text-right mb-hide" style="font-size: 14px; margin-top: 6px;">Your practice
					session will have <label id="questionsCount"></label> questions.</div>
				</footer>
			</div>
		</div>
		<!--col close-->
	</div>	
	<!-- row close --> </section> </section> <a href="#" class="hide nav-off-screen-block"
		data-toggle="class:nav-off-screen" data-target="#nav"></a> 
		
		<form id="questionsCountForm" name="from"  >
		
	<s:hidden name="section" id="sectionId"/>
	<s:hidden name="difficulty" id="dificultyId"/>
	<s:hidden name="reuseMode" id="reuseModeId"/>
	<s:hidden name="parents" id="parent"/>
	<s:hidden name="childs" id="child"/>
	<s:hidden name="catigories" id="catigorie"/>
	<s:hidden name="comprehenssion" id="comprehenssion"/>
	<s:hidden name="ANSWERED" id="ANSWERED"/>
	<s:hidden name="FLAG" id="FLAG"/>
<s:hidden name="TIMEMODE" id="time"/>
	<s:hidden name="GUESSED" id="GUESSED"/>
	<s:hidden name="types" id="type"/>
	<s:hidden name="testname" id="practisetestname"/>
	<s:hidden name="adaptiveMode" id="adaptiveModeVal"/>
	<s:hidden name="timeLimitFromCustomization" id="timeLimit"/>
	<s:hidden name="questionslimit" id="questionslimits"/>
	
</form>
		</section>

	<script src="assets/js/dropdown/practice-nested-list.js"></script>


</body>
</html>
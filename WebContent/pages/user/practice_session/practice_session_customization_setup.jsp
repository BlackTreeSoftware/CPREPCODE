<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

  <link rel="stylesheet" href="assets/css/practice.css" type="text/css" /> 
    
   <!---Styles for ul list-->
   <link rel="stylesheet" href="assets/js/dropdown/practice-ul.css" type="text/css" />
 
   <link rel="stylesheet" href="assets/js/chosen/chosen.css" type="text/css" />
   <link rel="stylesheet" href="assets/css/practiceSession/customSetup.css" type="text/css" />
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
 $(document).ready(function(){
	 
	 getCustomizationData();
	 $("#verbal").hide();
	 $("#mathradio").click(function(){
		 $("#quant").show();
	 	 $("#verbal").hide();
	 });
	 
	 $("#verbalradio").click(function(){
		 $("#quant").hide();
	 	 $("#verbal").show();
	 });
	 //problem solving
	 $("#psolving").click(function(){
		 if($(this).is(":checked")){
			
			 $("#psingle").prop("checked",true);
			 $("#pmultiple").prop("checked",true);
			 $("#psingle").prop("disabled",false);
			 $("#pmultiple").prop("disabled",false);
			 //numeric entry
			 $("#nsingle").prop("checked",true);
			 $("#ndouble").prop("checked",true);
			 $("#nsingle").prop("disabled",false);
			 $("#ndouble").prop("disabled",false);
		 }
		 else{
			
			 $("#psingle").prop("checked",false);
			 $("#pmultiple").prop("checked",false);
			 $("#psingle").prop("disabled",true);
			 $("#pmultiple").prop("disabled",true);
			 //numeric entry
			 $("#nsingle").prop("checked",false);
			 $("#ndouble").prop("checked",false);
			 $("#nsingle").prop("disabled",true);
			 $("#ndouble").prop("disabled",true);
		 }
	 });
	 
	
	 
	 //datainterpretation
	 $("#dinterpretation").click(function(){
		 if($(this).is(":checked")){
			
			 $("#dsingle").prop("checked",true);
			 $("#dmultiple").prop("checked",true);
			 $("#dentry1").prop("checked",true);
			 $("#dentry2").prop("checked",true);
			 $("#dsingle").prop("disabled",false);
			 $("#dmultiple").prop("disabled",false);
			 $("#dentry1").prop("disabled",false);
			 $("#dentry2").prop("disabled",false);
			 
		 }
		 else{
			
			 $("#dsingle").prop("checked",false);
			 $("#dmultiple").prop("checked",false);
			 $("#dentry1").prop("checked",false);
			 $("#dentry2").prop("checked",false);
			 $("#dsingle").prop("disabled",true);
			 $("#dmultiple").prop("disabled",true);
			 $("#dentry1").prop("disabled",true);
			 $("#dentry2").prop("disabled",true);
		 }
	 });
	 
	 //textcompletion
	 
	 $("#tcompletion").click(function(){
		 if($(this).is(":checked")){
			
			 $("#tsingle").prop("checked",true);
			 $("#tdouble").prop("checked",true);
			 $("#ttriple").prop("checked",true);
			 $("#tsingle").prop("disabled",false);
			 $("#tdouble").prop("disabled",false);
			 $("#ttriple").prop("disabled",false);
		 }
		 else{
			
			 $("#tsingle").prop("checked",false);
			 $("#tdouble").prop("checked",false);
			 $("#ttriple").prop("checked",false);
			 $("#tsingle").prop("disabled",true);
			 $("#tdouble").prop("disabled",true);
			 $("#ttriple").prop("disabled",true);
		 }
	 });
	 
	 // reading comprehension
	 $("#rcomprehension").click(function(){
		 if($(this).is(":checked")){
			
			 $("#rsingle").prop("checked",true);
			 $("#rmultiple").prop("checked",true);
			 $("#rsingle").prop("disabled",false);
			 $("#rmultiple").prop("disabled",false);
		 }
		 else{
			
			 $("#rsingle").prop("checked",false);
			 $("#rmultiple").prop("checked",false);
			 $("#rsingle").prop("disabled",true);
			 $("#rmultiple").prop("disabled",true);
		 }
	 });
	 $('input[type=checkbox]').click(function() {
		
		 
		 
		questionsCount(); 
	 });
	 $("#" ).click(function() {	       
	      var note =  $("#lession_note_phn").val();  
	                 $.ajax({
	                	 tye:'POST',
						url:'addNotes.action',
						data:'lesson_note_name='+note,
						success:function(data){
							toastr.success("Your Note is Saved!");
						},
						error:function(error){
							 
						}
						
					});
	      });
	 
 });
 </script> 
 <script type="text/javascript">
 function getCustomizationData(){
	 $.ajax({
			tye:'POST',
			url:'getCustomizationData.action',
			dataType:'json',
			success:dataReady,
			errror:dataGettinFailed
		});
 }
 function dataReady(jsonData){
	alert(' total number of Questions is: '+jsonData.rowCount); 
 }
 function dataGettinFailed(errorIs)
 {
	 
 }
 function questionSelected(allQuestions)
 {
	 $('.questions').prop( "checked",$(allQuestions).prop( "checked" ));
	// alert(' allQuestion Button Clicked '+$(allQuestions).prop( "checked" ));
 }
 function difficultySelected(difficulty){
	 $('.difficulty').prop( "checked",$(difficulty).prop( "checked" ));
 }
 
 function questionsCount(){
	// alert(' in questions count ');
	 var isAnswerd=$('#answerd').prop("checked");
	 
	 var isCorrect=$('#correct').prop("checked");
	 var isInCorrect=$('#inCorrect').prop("checked");
	 var isUnAnswered=$('#unAnswered').prop("checked");
	 
	 var difficultyCount= $('input.difficulty:checked').length;
	 var difficultyCount= $('input.difficulty:checked').length;
	 var dinterpretationCount=$('input.dinterpretation:checked').length;
	 
	 var tcompletionCount=$('input.tcompletion:checked').length;
	 var psolvingCount=$('input.psolving:checked').length;
	 var rcomprehensionCount=$('input.rcomprehension:checked').length;
	 var section=$("input:radio[name='a']:checked").val();
	 var sentanceEqualance=$('#sentenceEquivalence').prop("checked");//2
	 var criticalReasoning=$('#dinterpretation').prop("checked");//9
	 
	 var qunatitativeComparision=$('#quantitativeComparison').prop("checked");//4
	 
	// alert(' section is: '+section);
	 var difficultys=[];
	
	 var mathTypes=[];
	 var verbalTypes=[];
	 if(rcomprehensionCount==0) $('#rcomprehension').prop("checked",false);
	 else{
		 if(section==2)
		 {
		 $('input.rcomprehension:checked').each(function() {
			 verbalTypes.push($(this).val());
			});
		 }
	 }
	 if(tcompletionCount==0) $('#tcompletion').prop("checked",false);
	 else{
		 if(section==2)
		 {
		 $('input.tcompletion:checked').each(function() {
			 verbalTypes.push($(this).val());
			});
		 }
	 }
	 if(psolvingCount==0) $('#psolving').prop("checked",false);
	 else{
		 if(section==1)
		 {
		 $('input.psolving:checked').each(function() {
			 mathTypes.push($(this).val());
			});
		 }
	 }
	 if(dinterpretationCount==0) $('#dinterpretation').prop("checked",false);
	 else{
		 if(section==1)
			 {
			 $('input.dinterpretation:checked').each(function() {
				 mathTypes.push($(this).val());
				});
			 }
	 }
	 if(difficultyCount==0) $('#difficulty').prop("checked",false);
	 else{
		 $('input.difficulty:checked').each(function() {
			 difficultys.push($(this).val());
			});
	 }
	// alert(' difficulties is: '+difficultys);
	 if(section==1)
		 {
		// alert(' Match Block is; '+mathTypes);
		 }else{
		//	 alert(' verbal Types is: '+verbalTypes);
		}
	 
 }
 
 </script>
</head>
<body>
<section id="content">
					<section class="vbox">          
						<section class="scrollable wrapper">
            
							<div class="row">
								<div class="col-lg-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<div class="panel-title"> Practice Session</div>
										</div>
										<div class="panel-body">
											<div class="col-lg-3">
												<h4 class="text-center">Settings</h4>
												<hr />	
                                                
                                           	<div class="form-group">
                                            
                                          		<label class="col-sm-5 control-label"><h5>Time Mode</h5></label>
													<div class="col-sm-7">     
														<div class="btn-group m-r">
															<button data-toggle="dropdown" class="btn btn-sm btn-success dropdown-toggle">
																<span class="dropdown-label">Select time</span>
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu dropdown-select">                             
																<li><a href="#"><input type="radio" name="d-s-r" checked=""> No Limit </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 5 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 10 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 15 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 20 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 25 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 30 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 35 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 40 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 45 minutes </a></li>
																<li><a href="#"><input type="radio" name="d-s-r"> 60 minutes </a></li>
															</ul>
														</div>
													</div>

													<div class="clearfix"></div>
													
													<label class="col-sm-5 control-label">Total questions</label>
													<div class="col-sm-7">              
														<div class="btn-group m-r">
															<button data-toggle="dropdown" class="btn btn-sm btn-success dropdown-toggle">
																<span class="dropdown-label">Select Questions</span> 
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu dropdown-select">                             
																<li><a href="#"><input type="radio" name="d-s-r" checked="" /> No Limit </a></li>
																<li><a href="#"><input type="radio" name="d-s-r" /> 5 questions </a></li>
																<li><a href="#"><input type="radio" name="d-s-r" /> 10 questions </a></li>
																<li><a href="#"><input type="radio" name="d-s-r" /> 15 questions </a></li>
																<li><a href="#"><input type="radio" name="d-s-r" /> 20 questions </a></li>
																<li><a href="#"><input type="radio" name="d-s-r" /> 25 questions </a></li>
																<li><a href="#"><input type="radio" name="d-s-r" /> 30 questions </a></li>
															</ul>
														</div>
													</div>
												</div>
												
												<div class="clearfix"></div>
												   
												<hr />
												
												<div class="form-group">
													<label class="col-sm-5 control-label"><h5>Reuse Mode</h5></label>
													<div class="col-sm-7">
														<div class="checkbox i-checks">
															<label><input type="checkbox" onclick="questionSelected(this)" /><i></i> All Questions</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" class="questions" id="answerd" /><i></i> Answered
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" class="questions" id="unAnswered"/><i></i> Unanswered
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" class="questions" id="correct" /><i></i> Correct
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox"  class="questions" id="inCorrect"/><i></i> Incorrect
															</label>
														</div>
													</div>
												</div>
												
												<div class="clearfix"></div>
												
												<hr />
												
												<div class="form-group">
													<label class="col-sm-5 control-label"  ><h5>Difficulty</h5></label>
													<div class="col-sm-7">
														<div class="checkbox i-checks">
															<label><input type="checkbox" /><i></i> Adaptive</label>
														</div>
														
														<hr />

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" value="-1" onclick="difficultySelected(this)" id="difficulty"/><i></i> All Difficulties
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" value="5" class="difficulty" /><i></i> Very Hard
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" value="4" class="difficulty" /><i></i> Hard
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" value="3" class="difficulty" /><i></i> Medium
															</label>
														</div>
														
														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" value="2" class="difficulty" /><i></i> Easy
															</label>
														</div>
														
														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" value="1" class="difficulty" /><i></i> Very Easy
															</label>
														</div>
													</div>
												</div>
												
											</div><!--col-lg-close-->

											<div class="col-lg-3 col-lg-offset-1">
												<h4 class="text-center">Section</h4>
												<hr />
												<div class="form-group">
													<label class="col-sm-6 control-label"><h5>Subject</h5></label>
													<div class="col-sm-6 col-sm-offset-0">
													
													  <div class="radio i-checks">
															<label>
																<input id="mathradio" type="radio" name="a" value="1" checked><i class="hide-for-label"></i>Math
															</label>
															<span class="badge bg-success l-s-p-c d-b-in">48</span>
													  </div>
													
													  <div class="radio i-checks ">
															<label>
																<input id="verbalradio" type="radio" name="a" value="2"><i class="hide-for-label"></i>Verbal
															</label>
															<span class="badge bg-success l-s-p-c d-b-in">48</span>
													  </div>
														
													</div>
												</div>
																								<div class="clearfix"></div>
												
												<hr />
												
												<div class="form-group" id="quant">
													<label class="col-sm-12 control-label"><h5>Question Type</h5></label>
													<div class="col-sm-12">
														<div class="checkbox i-checks">
															<label><input type="checkbox" value="4" id="quantitativeComparison"/><i></i> Quantitative Comparison</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
													</div>
													<div class="col-sm-12">	
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="psolving" value="6"/><i></i> Problem Solving</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="psingle" disabled="disabled" value="8" class="psolving"/><i></i> Single Answer</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="pmultiple" disabled="disabled" value="9" class="psolving" /><i></i> Multiple Answer</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
													
													
												    <div class="checkbox i-checks">
															<label><input type="checkbox" id="nsingle" disabled="disabled" value="10" class="psolving" /><i></i> NumericEntry Single</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
													</div>
														
													<div class="checkbox i-checks">
															<label><input type="checkbox" id="ndouble" disabled="disabled" value="13" class="psolving"/><i></i> NumericEntry Double</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
													</div>
													
													</div>
													
													<div class="col-sm-12">
														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" id="dinterpretation" value="5" /><i></i> Data Interpretation
                                                                <span class="badge bg-primary l-s-p-c">48</span>
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" id="dsingle"  disabled="disabled" value="11" class="dinterpretation"/><i></i> Single Answer
                                                               <span class="badge bg-danger l-s-p-c">48</span>
															</label>
														</div>

														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" id="dmultiple"  disabled="disabled" value="12" class="dinterpretation"/><i></i> Multiple Answer
                                                                <span class="badge bg-success l-s-p-c">48</span>
															</label>
														</div>
														
														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" id="dentry1"  disabled="disabled" value="16" class="dinterpretation"/><i></i> Numeric Entry Single
                                                                <span class="badge bg-success l-s-p-c">48</span>
															</label>
														</div>
														
														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" id="dentry2"  disabled="disabled" value="17" class="dinterpretation"/><i></i> Numeric Entry Double
                                                                <span class="badge bg-success l-s-p-c">48</span>
															</label>
														</div>
														
														
													</div>
												</div>
												
												<div class="form-group" id="verbal">
													<label class="col-sm-12 control-label"><h5>Question Type</h5></label>
													<div class="col-sm-12">
														<div class="checkbox i-checks">
															<label><input type="checkbox" value="2" id="sentenceEquivalence"/><i></i> Sentence Equivalence</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
													</div>
													<div class="col-sm-12">	
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="tcompletion" value="1" /><i></i> Text Completion</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="tsingle" disabled="disabled" value="1" class="tcompletion"/><i></i> Single Blank</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="tdouble" disabled="disabled" value="2" class="tcompletion"/><i></i> Double Blank</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="ttriple" disabled="disabled" value="3" class="tcompletion" /><i></i> Triple Blank</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
													</div>
													
													<div class="col-sm-12">	
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="rcomprehension" value="3" /><i></i> Reading Comprehension</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="rsingle" disabled="disabled" value="4" class="rcomprehension"/><i></i> Single Answer</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<div class="checkbox i-checks">
															<label><input type="checkbox" id="rmultiple" disabled="disabled" value="5" class="rcomprehension" /><i></i> Multiple Answer</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div>
														
														<!-- <div class="checkbox i-checks">
															<label><input type="checkbox" /><i></i> Select In</label>
                                                            <span class="badge bg-success l-s-p-c">48</span>
														</div> -->
													</div>
													
													<div class="col-sm-12">
														<div class="checkbox i-checks">
															<label>
																<input type="checkbox" id="dinterpretation" value="9" /><i></i> Critical Reasoning
                                                                <span class="badge bg-primary l-s-p-c">48</span>
															</label>
														</div>
  												    </div>
												</div>
											</div><!--col-lg-close-->
											
											<div class="col-lg-4 col-lg-offset-1">
												<h4 class="text-center test-customisation-title-margin">Topics</h4>                          
												<hr />
												<div class="tree-view-practice">
                                                <div class="list-scroller">
											
											
                                                <ul>
                                                <li> <a href="#"> Math lesson test 1</a>
                                                <ul>
                                                <li><a href="#"> Magazine</a>
                                                <ul>
                                                <li class="tried">
                                               
                                                <section class="col-xs-10 check-class">
                                                
                                                <a href="#">Spiral Scratch</a> 
                                                
                                                 <span class="i-checks">                                                                      
                                                 <label>  <input type="checkbox" name="a" value="option1"><i></i> </label>
                                                 </span>
                                                
                                                <span class="paddy"> <span class="badge bg-success">75</span>
                                                </span>                   
                                                </section>
                                               
                                                </li>
                                                
                                                <li>
                                                <section class="col-xs-10"><a href="#">Real life</a>                                                 
                                                <span class="paddy"> <span class="badge bg-success">75</span>
                                                </span>                   
                                                </section>
                                                </li>
    
                                                <li>
                                              
                                                <section class="col-xs-10"><a href="#">Secondhand Daylight</a>                                                
                                                <span class="paddy"> <span class="badge bg-success">75</span>
                                                </span>                   
                                                </section>
                                                               
                                                </li>
                                                
                                                <li>
                                               
                                                <section class="col-xs-10"> <a href="#">The Correct Use</a>                                            
                                                <span class="paddy"> <span class="badge bg-info">65</span>
                                                </span>
                                                </section> 
                                                                                     
                                                </li>                                            
                                                </ul>
                                                </li>
                                                                                      
                                                <li > <a href="#">Buzzcocks</a>  
                                                <ul>
                                                
                                                <li>                                           
                                                <div class="col-xs-10"> <a href="#">Time's Up</a>                                           
                                                <span class="paddy"> <span class="badge bg-danger">20</span>
                                                </span>
                                               
                                                </div>
                                                </li>
                                                
                                                <li >
                                                <div class="col-xs-10"> <a href="#">Love Bites</a>                                           
                                                <span class="paddy"> <span class="badge bg-success">65</span>
                                                </span>
                                                </div>
                                                </li>
                                                
                                                <li >
                                                <div class="col-xs-10"><a href="#">A Different Kind Of Tension</a>                                           
                                                <span class="paddy"> <span class="badge bg-danger">32</span>
                                                </span>
                                                </div>
                                                </li>
                                                </ul>
                                                </li>
                                        
                                                <li><a href="#">Joy Division</a>
                                                <ul>
                                                
                                                <li >
                                                <div class="col-xs-10"> <a href="#">Unknown Pleasures</a>                                           
                                                <span class="paddy"> <span class="badge bg-info">48</span>
                                                </span>
                                                </div>
                                                </li>
                                                
                                                <li >
                                                <div class="col-xs-10"><a href="#">Closer</a>                                           
                                                <span class="paddy"> <span class="badge bg-info">36</span>
                                                </span>
                                                </div>
                                                </li>
                                                
                                                <li >
                                                <div class="col-xs-10"><a href="#">Still</a>                                          
                                                <span class="paddy"><span class="badge bg-success">68</span>
                                                </span>
                                                </div>
                                                </li>
                                                </ul>
                                                </li>
                                                </ul>
                                                </li>
                                                
                                                <li><a href="#">Liverpool</a>
                                                
                                                <ul>
                                                <li><a href="#">OMD</a>
                                                <ul>
                                                
                                                <li>
                                                <div class="col-xs-10"><a href="#">OMD</a>                                           
                                                <span class="paddy"> <span class="badge bg-info">65</span>
                                                </span>
                                                </div>
                                                </li>
                                                
                                                
                                                
                                                <li>
                                                <div class="col-xs-10" ><a href="#">Organisation</a>                                          
                                                <span class="paddy"> <span class="badge bg-info">65</span>
                                                </span>
                                                </div>
                                                </li>
                                                </ul>
                                                </li>
                                                <li><a href="#">Echo & the Bunnymen</a>
                                                
                                                <ul>
                                                
                                                <li>
                                                <div class="col-xs-10"><a href="#">Crocodiles</a>                                          
                                                <span class="paddy"> <span class="badge bg-info">65</span>
                                                </span>
                                                </div>
                                                </li>
                                                
                                                <li>
                                                <div class="col-xs-10"><a href="#">Heaven Up Here</a>                                          
                                                <span class="paddy"> <span class="badge bg-info">65</span>
                                                </span>
                                                </div>
                                               
                                                </li>
                                                
                                                <li>
                                                <div class="col-xs-10" ><a href="#">Porcupine</a>                                           
                                                <span class="paddy"> <span class="badge bg-info">65</span>
                                                </span>
                                                </div>
                                                </li>
                                                
                                                </ul>
                                                </li>
                                                </ul>
                                                </li>
                                         </ul>
											
											
											
                                            					
												</div>
												</div>
    
											</div><!--col closed-->
											<div class="clearfix"></div>
										</div><!--panel body close-->
                                         <!-- Get-Practice-Session-Questions.action -->
										<footer class="panel-footer bg-light lter">
										<!-- 	<button type="submit" class="btn btn-success" >Start Practice</button> -->
											<a href="Get-Practice-Session-Questions.action" class="btn btn-success" id="start_test">Start Practice</a>
											<div class="col-lg-6 col-lg-offset-0 pull-right text-right hidden-xs" style="font-size: 14px;margin-top: 5px;">
												Your practice session will have 20 questions.
											</div>
										</footer>
                                        
                                        
									</div>
								</div><!--col close--> 
							</div><!-- row close -->
						</section>
					</section>
					<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
				</section>
			
			<script src="assets/js/dropdown/practice-nested-list.js"></script>
			
			
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title> --> 
<link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" />
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/> 

<script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script src="assets/js/chosen/chosen.jquery.min.js"></script>
<script src="assets/js/select2.1/select2.min.js"></script>
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>   
<script type="text/javascript"> 
$(document).ready(function(){ 
	//alert('question:'+$('#question_id').val()); 
	//alert('Free:'+$('#isFree').val()+"\tReferral:"+$('#isReferral').val());  
	//alert('question:'+$('#question_text').val()+"\t Solution:"+$('#solution_text').val()); 
	$('#_add,#_addbreak,#_delete').hide();
	 $('#view,#submit').click(function(){
        		
        		$('#_add,#_addbreak,#_delete').show();
        	
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
			}   
	$('#multiple_ans,#grapharea').hide();
	$('#normalradio').prop('checked',true);
	$('#active_state').prop('checked',true);
	$('#single').click(function(){
		$('#multiple_ans').hide();
		$('#single_ans').show();
	});
	$('#multiple').click(function(){
		$('#multiple_ans').show();
		$('#single_ans').hide();
	});
	$('#normalradio').click(function(){
		$('#grapharea').hide();
	});
	$('#graphradio').click(function(){
		$('#grapharea').show();
	});
	$("#graph,#test,#difficulty").select2({ 
	});
	$("#skill").select2({ 
		maximumSelectionSize: 3 
	});
	
	$("#lesson").select2({ 
		maximumSelectionSize: 1 
	});
	
	$('#ans1_single').click(function(){
		$('#answer_single').val($('#ans1_text_single').val()); 
	});
	$('#ans2_single').click(function(){
		$('#answer_single').val($('#ans2_text_single').val()); 
	});
	$('#ans3_single').click(function(){
		$('#answer_single').val($('#ans3_text_single').val()); 
	});
	$('#ans4_single').click(function(){
		$('#answer_single').val($('#ans4_text_single').val()); 
	});
	$('#ans5_single').click(function(){
		$('#answer_single').val($('#ans5_text_single').val()); 
	});
	
	$('#ans1_multiple').click(function(){ 
		if($(this).is(':checked'))
			$('#multiple_ans1').val($('#ans1_text_multiple').val()); 
		else
		   $('#multiple_ans1').val(''); 
	});
	$('#ans2_multiple').click(function(){ 
		if($(this).is(':checked'))
			$('#multiple_ans2').val($('#ans2_text_multiple').val()); 
		else
		   $('#multiple_ans2').val('');  
	});
	$('#ans3_multiple').click(function(){ 
		if($(this).is(':checked'))
			$('#multiple_ans3').val($('#ans3_text_multiple').val()); 
		else
		   $('#multiple_ans3').val('');  
	});
	$('#ans4_multiple').click(function(){ 
		if($(this).is(':checked'))
			$('#multiple_ans4').val($('#ans4_text_multiple').val()); 
		else
		   $('#multiple_ans4').val('');  
	});
	$('#ans5_multiple').click(function(){ 
		if($(this).is(':checked'))
			$('#multiple_ans5').val($('#ans5_text_multiple').val()); 
		else
		   $('#multiple_ans5').val('');  
	});
	$('#ans6_multiple').click(function(){ 
		if($(this).is(':checked'))
			$('#multiple_ans6').val($('#ans6_text_multiple').val()); 
		else
		   $('#multiple_ans6').val('');  
	});
	
	if($('#question_id').val()!='')
	{
	if($('#type_id').val()==12||$('#type_id').val()==9)
		{
		   //$('#single_ans').hide();
		   //$('#multiple_ans').show();  
		  var choices=[];
		  var answers=[];
		  choices.push($('#ans1_text_multiple').val());
		  choices.push($('#ans2_text_multiple').val());
		  choices.push($('#ans3_text_multiple').val());
		  choices.push($('#ans4_text_multiple').val());
		  choices.push($('#ans5_text_multiple').val()); 
		  choices.push($('#ans6_text_multiple').val());
		  
		  answers.push($('#multiple_ans1').val());
		  answers.push($('#multiple_ans2').val());
		  answers.push($('#multiple_ans3').val());
		  answers.push($('#multiple_ans4').val());
		  answers.push($('#multiple_ans5').val());
		  answers.push($('#multiple_ans6').val()); 
		  
		  for(var i=0;i<choices.length;i++)
			  {  
			  for(var j=0;j<answers.length;j++)
				  {
				  if(choices[i]==answers[j])
				  {  
				  //alert('choices:'+choices[i]);
				  $('#ans'+(i+1)+'_multiple').prop('checked',true);
				  }
				  } 
			  } 
		}
	if($('#type_id').val()==11||$('#type_id').val()==8)
	{
		//alert($('#type_id').val());
		  var choices=[];
		  var answer=$('#answer_single').val();
		  choices.push($('#ans1_text_single').val());
		  choices.push($('#ans2_text_single').val());
		  choices.push($('#ans3_text_single').val());
		  choices.push($('#ans4_text_single').val());
		  choices.push($('#ans5_text_single').val()); 
		  for(var i=0;i<choices.length;i++)
			  {
			 //  alert(choices[i]);
			  if(choices[i]==answer)
				  { 
				  // alert('#ans'+(i+1)+'_single'); 
				  $('#ans'+(i+1)+'_single').prop('checked',true);
				  }
			  } 
	}
	 
	  $('#question_directions').val($('#directions').val());
	  $('#editor1').val($('#question_text').val());
	  $('#editor2').val($('#solution_text').val());
	  if($('#isFree').val()=='FREE')
		{ 
		  $('#fee_type').prop('checked',true);
		}
	if($('#isReferral').val()=='YES')
		{ 
		$('#referral_type').prop('checked',true); 
		}
	if($('#question_status').val()=='ACTIVE')
	{  
    //document.getElementById("active_state").checked=true;
	$('#active_state').prop('checked',true);
	$('#inactive_state').prop('checked',false);
	}
		if($('#question_status').val()=='INACTIVE')
		   {   
			//document.getElementById("inactive_state").checked=true;
			//document.getElementById("active_state").checked=false;
				document.getElementById("inactive_state").checked=true;
				document.getElementById("active_state").checked=false; 
			// $('#inactive_state').prop('checked',true); 
			// $('#active_state').prop('checked',false);
		   }  
		if($('#category_id').val()==5)
		{  
	     $('#normalradio').prop('checked',false);
	     $('#graphradio').prop('checked',true);
	     $('#grapharea').show();
		// document.getElementById("graphradio").checked=true;  
		}
		if($('#type_id').val()==12||$('#type_id').val()==9)
		{  
		 $('#multiple').prop('checked',true); 
		 $('#multiple_ans').show();
		 $('#single_ans').hide();  
		}
} 
 
	
	$("#reset").click(function(){ 
		$('#test').select2('val','');
		$('#lesson').select2('val','');
		$('#question_title').val('');
		$('#question_directions').val(''); 
		$('#skill').select2('val','');
		$('#difficulty').select2('val','');
		$('#graph').select2('val','');
		$('#ans1_text_single').val(''); 
		$('#ans2_text_single').val(''); 
		$('#ans3_text_single').val(''); 
		$('#ans4_text_single').val(''); 
		$('#ans5_text_single').val(''); 
		
		
		$('#ans1_single').prop('checked',false);
		$('#ans2_single').prop('checked',false);
		$('#ans3_single').prop('checked',false);
		$('#ans4_single').prop('checked',false);
		$('#ans5_single').prop('checked',false);
		
		$('#answer_single').val('');
		 
		
		$('#ans1_text_multiple').val('');
		$('#ans2_text_multiple').val('');
		$('#ans3_text_multiple').val('');
		$('#ans4_text_multiple').val('');
		$('#ans5_text_multiple').val('');
		$('#ans6_text_multiple').val('');
		
		$('#multiple_ans1').val('');
		$('#multiple_ans2').val('');
		$('#multiple_ans3').val('');
		$('#multiple_ans4').val('');
		$('#multiple_ans5').val('');
		$('#multiple_ans6').val('');
		
		
		$('#ans1_multiple').prop('checked',false);
		$('#ans2_multiple').prop('checked',false);
		$('#ans3_multiple').prop('checked',false);
		$('#ans4_multiple').prop('checked',false);
		$('#ans5_multiple').prop('checked',false);
		$('#ans6_multiple').prop('checked',false);
		
		CKEDITOR.instances.editor1.setData('');
		CKEDITOR.instances.editor2.setData('');
		
		$('#fee_type').prop('checked',false);
		$('#referral_type').prop('checked',false);
		 if($('#graphradio').is(':checked')) 
		  {
			 $('#graphradio').prop('checked',false);
		  } 
		 if($('#multiple').is(':checked')) 
		  {
			 $('#multiple').prop('checked',false);
		  } 
		$('#normalradio').prop('checked',true);
		$('#single').prop('checked',true);
		if($('#inactive_state').is(':checked')) 
		  {
			 $('#active_state').prop('checked',false);
		  }  
		$('#active_state').prop('checked',true); 
		$('#grapharea').hide(); 
		$('#videourl').val('');  
		$('#submit').text("Submit").removeClass("btn-primary").addClass("btn-success");
	});

	$('#view').click(function(){
		$.ajax({
   			url:"viewProblemSolving.action",
   			type: "POST",
   			success:function(data){ 
   			 $("#table-data").html(data);
   			 $("#operations_row").show(); 
   			},
   			error:function(e){
   				alert(e);
   			}
   		});
	});
	 
	$("#submit").click(function(){  
		var url = $('#videourl').val();
		var	reWhiteSpace = new RegExp(/^\s+$/);
		var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
		if($('#test').select2('val')=='')
		{
			toastr.error("Select Test Name", "Error Info");
			return false;
		}
		else if($('#lesson').select2('val')=='')
		{
			toastr.error("Select lesson", "Error Info");
			return false;
		}
		else if($('#question_title').val()==''||$('#question_title').val().length==0)
		{
			toastr.error("Enter Question Title", "Error Info");
			return false;
		}
		else if(reWhiteSpace.test($('#question_title').val()))
		{
			toastr.error("Question title should not contain blank spaces", "Error Info");
			return false;
		} 
		else if($('#question_directions').val()==''||$('#question_directions').val().length==0)
		{
			toastr.error("Enter Question Directions", "Error Info");
			return false;
		}
		else if(reWhiteSpace.test($('#question_directions').val()))
		{
			toastr.error("Question directions should not contain blank spaces", "Error Info");
			return false;
		} 
		else if(CKEDITOR.instances.editor1.getData()==''||CKEDITOR.instances.editor1.getData().length==0)
		{
			toastr.error("Enter Question", "Error Info");
			return false;
		} 
		else if(reWhiteSpace.test(CKEDITOR.instances.editor1.getData()))
		{
			toastr.error("Question should not contain blank spaces", "Error Info");
			return false;
		} 
		else if($('#skill').select2('val')=='')
		{
			toastr.error("Select skill", "Error Info");
			return false;
		}
		else if($('#difficulty').select2('val')=='')
		{
			toastr.error("Select Difficulty level", "Error Info");
			return false;
		}
		else if(CKEDITOR.instances.editor2.getData()==''||CKEDITOR.instances.editor2.getData().length==0)
		{
			toastr.error("Enter Solution Text", "Error Info");
			return false;
		}
		else if (!regexp.test(url)) { 
			toastr.error("Enter Valid URL", "Error Info");
			 return false;
		}  
		else
		{  
			var find=true;
			if($('#single').is(':checked'))
			{
			 
				 if($('#ans1_text_single').val()==''||$('#ans1_text_single').val().length==0||
							$('#ans2_text_single').val()==''||$('#ans2_text_single').val().length==0||
							$('#ans3_text_single').val()==''||$('#ans3_text_single').val().length==0||
							$('#ans4_text_single').val()==''||$('#ans4_text_single').val().length==0||
							$('#ans5_text_single').val()==''||$('#ans5_text_single').val().length==0)
					 {
					 toastr.error("Enter all the answers", "Error Info");
					 return false;
					 find=false;
					 }
				 else if($('[name="single_choice"]:checked').length == 0)
					{
					toastr.error("Select Answer", "Error Info");
					find=false;
					return false;
					}  
			else
				{
				find=true;
				}
			}
			if($('#multiple').is(':checked'))
			{  
				var count = $('#multiple_ans input:checked').length;
				if($('#ans1_text_multiple').val()==''||$('#ans1_text_multiple').val().length==0||
						$('#ans2_text_multiple').val()==''||$('#ans2_text_multiple').val().length==0||
						$('#ans3_text_multiple').val()==''||$('#ans3_text_multiple').val().length==0||
						$('#ans4_text_multiple').val()==''||$('#ans4_text_multiple').val().length==0||
						$('#ans5_text_multiple').val()==''||$('#ans5_text_multiple').val().length==0||
						$('#ans6_text_multiple').val()==''||$('#ans6_text_multiple').val().length==0)
				 {
				 toastr.error("Enter all the answers", "Error Info");
				 return false;
				 find=false;
				 }
				else if(count<1)
					{
					 toastr.error("Select the answer", "Error Info");
					 return false;
					 find=false;
					} 
				else
					{
					find=true;
					}
			}
			if($('#graphradio').is(':checked'))
				{
				 if($('#graph').select2('val')=='')
					 {
					 toastr.error("Select Graph!", "Error Info");
					 find=false;
					 return false;
					 }
				 else
					 {
					 find=true; 
					 }
				} 
			if(find)
				{
				//alert('hai');
				var question=CKEDITOR.instances.editor1.getData();
				var solution=CKEDITOR.instances.editor2.getData();
				 var question_type="";
				 var question_category="";
				 var fee_type="";var referral="";
				if($('#multiple').is(':checked'))
					{
					 question_type="multiple";
					}
				if($('#single').is(':checked'))
				{
				 question_type="single";
				}
				if($('#graphradio').is(':checked'))
				{
					question_category="datainter";
				}
				if($('#normalradio').is(':checked'))
				{
					question_category="normal";
				}
				if($('#referral_type').is(':checked'))
				{
				//$('#referral_type').val('YES');
					referral="YES";
				}
				if(!$('#referral_type').is(':checked'))
				{
				//$('#referral_type').val('NO');
					referral="NO";
				}
				if($('#fee_type').is(':checked'))
				{
				//$('#referral_type').val('FREE');
					fee_type="FREE";
				}
				if(!$('#fee_type').is(':checked'))
				{
				//$('#fee_type').val('PAID');
					fee_type="PAID";
				}
				//alert('fee type:'+$('#fee_type').val()+"\treferral:"+$('#referral_type').val());
				var formData=$('#problemsolving_form').serialize();  
				 $.ajax({
		    				url : 'saveProblemSolvingQuestion.action?question='+question+'&solution='+solution+'&question_type='+question_type+
		    						'&question_category='+question_category+'&fee_type='+fee_type+'&referral='+referral,
		    				data : formData,
		    				success : function(result) { 
		    			         // alert(result); 
		    					$("#operations_row").show();
		           				$("#table-data").html(result); 
		    				},
		    				error : function(e) {
		    					alert('Error' + e);
		    				}
		    			});
				return true; 
				}
			 
		}
	});
});

</script>
<!-- </head>
<body> -->
 <%-- <section id="content"> --%>
          <%-- <section class="vbox">          
            <section class="scrollable wrapper"> --%>
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default" style="margin-bottom:0px;">
            
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" >
             
              <form class="bs-example form-horizontal" id="problemsolving_form">                                                   
                       
                      <input type="hidden" value="${questionsUploadBO.question_id}" id="question_id" name="question_id"/>  
                      <input type="hidden" value="${questionsUploadBO.question_directions}" id="directions"/>
                      <input type="hidden" value="${questionsUploadBO.status}"  id="question_status"/>
                      <input type="hidden" value="${questionsUploadBO.access_type}" id="isFree"/>
                      <input type="hidden" value="${questionsUploadBO.refferal}" id="isReferral"/>
                      <input type="hidden" value="${questionsUploadBO.category_id}" id="category_id"/>
                      <input type="hidden" value="${questionsUploadBO.questiontype_id}" id="type_id"/>
                      <input type="hidden" value="${questionsUploadBO.question}" id="question_text"/>
                      <input type="hidden" value="${questionsUploadBO.solution_text}" id="solution_text"/>
                       		 <div class="form-group">
                                <label class="col-lg-2  col-lg-offset-4  control-label">Type</label>
                              	 <div class="checkbox i-checks col-lg-1">
                                  <label>
                                    <input type="checkbox"  name="questionsUploadBO.feeType" id="fee_type">
                                    <i></i>
                                    Free
                                  </label>
                                </div>
                                <div class="checkbox i-checks col-lg-2">
                                  <label>
                                    <input type="checkbox"  name="questionsUploadBO.refferal" id="referral_type">
                                    <i></i>
                                    Reffaral
                                  </label>
                                </div>
                                
                               </div><!--form group close-->     
                               
                            
                             <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label">Test Name</label>
                                <div class="col-lg-3">
                                <s:select id="test"  cssStyle="width:380px;" list="tests" headerKey="" headerValue=""  
                                data-required="true" name="questionsUploadBO.test_id" placeholder="Select a Test"
                                listKey="testId" listValue="testName">
                                 </s:select> 
                                </div>
                             </div>
                                
                                
                              <div class="form-group">
                              <label class="col-lg-2 col-lg-offset-4 control-label">Lesson Name</label>
                              <div class="col-lg-5">
                                <s:select id="lesson"  cssStyle="width:380px;" list="subLessons" headerKey="" headerValue=""  
                                data-required="true" name="questionsUploadBO.lessons" placeholder="Select Lessons"
                                listKey="sublesson_id" listValue="main_lesson_name" multiple="true">
                                 </s:select> 
                              </div>
                              </div>
                                
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Question title</label>
                                  <div class="col-lg-5">
                                    <input type="text" class="form-control" placeholder="Enter Question title" name="questionsUploadBO.question_title" 
                                    id="question_title" value="${questionsUploadBO.question_title}" >                            
                                  </div>
                             </div>    
                             
                             
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
                                  <div class="col-lg-3">
                                    <textarea cols="50" rows="4" style="max-width:380px" name="questionsUploadBO.question_directions"
                                    id="question_directions"></textarea>                          
                                  </div>
                             </div>    
                             
                             
                      	   <div class="form-group">
                       				 <label class="col-lg-2 col-lg-offset-4 control-label">Type</label>
                        			<div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="b" value="option1" checked="checked" id="normalradio">
                                    <i></i>
                                  Normal
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="b" value="option2" id="graphradio" >
                                    <i></i>
                                    Graph
                                  </label>
                                </div>
                            
                              
                            
                          </div><!--form group-->
                                  
                               <div class="form-group" id="grapharea">
                                 <label class="col-lg-2 col-lg-offset-4 control-label">Select Graph</label>
                                <div class="col-lg-3">
                              <s:select  cssStyle="width:380px;" list="dataInterpretiongrahps" headerKey=""
                                headerValue=""  id="graph" 
                                data-required="true" name="questionsUploadBO.graphId" placeholder="Select Graph"
                                listKey="graph_id" listValue="graph_title">
                                 </s:select> 
                                </div>
                             </div>
                       	
                          	<div class="form-group">
                          				<label class="col-lg-2 control-label">Question</label>
                          				<div class="col-lg-10">
                            				<textarea id="editor1" name="questionsUploadBO.question" rows="10" cols="80"></textarea> 
                          				</div>
                        	</div>
                       
                       		 <div class="form-group">
                       			
                                <label class="col-lg-2 col-lg-offset-3 control-label">Type</label></label>
                        			
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="p" value="SINGLE" checked id="single">
                                    <i></i>
                                   Single Answer
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="p" value="MULTILPE" id="multiple">
                                    <i></i>
                                    Multiple Answer
                                  </label>
                                </div>                                             
                            
                          </div><!--form group-->
                            
                            
                            <div class="form-group" id="single_ans">
								
                                <div class="row">
                                
                                 <div class="col-lg-6 ">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" id="ans1_text_single"
                                   name="choices_single[0]" value="${choices_single[0]}">                            
                                  </div> 
                                    <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio"  id="ans1_single" name="single_choice">
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 1-->

                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" 
                                     placeholder="" id="ans2_text_single" name="choices_single[1]"
                                     value="${choices_single[1]}">                            
                                  </div>
                                    <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" id="ans2_single" name="single_choice"> 
                                        <i></i>
                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 2-->
	
    								<br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" id="ans3_text_single"
                                    name="choices_single[2]"" value="${choices_single[2]}">                            
                                  </div>
                                
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" id="ans3_single"  name="single_choice">
                                        <i></i>
                                       
                                      </label>
                                    </div>
                                    
                                  </div><!--row close for internal choices 3-->
    								
                                    <br>
                              
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" id="ans4_text_single"
                                    name="choices_single[3]" value="${choices_single[3]}">                            
                                  </div>
                                    <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" id="ans4_single" name="single_choice">
                                        <i></i> 
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 4-->
                                  
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" id="ans5_text_single"
                                    name="choices_single[4]" value="${choices_single[4]}">                            
                                  </div>
                                   <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" id="ans5_single" name="single_choice">
                                        <i></i> 
                                      </label>
                                    </div>
                                    
                                  </div><!--row close for internal choices 5-->
                                  
                                  
                                  </div><!--Col for choices close-->
                                  
                                  <div class="col-lg-6">
                                    <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-1 control-label">Answer 1</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" readonly placeholder="" 
                                    id="answer_single" name="answer" value="${answer}">                            
                                  </div>
                             		</div> 
                             
                                  </div>
                                                                   
                                  </div>
                                  
                                 </div><!--Form group close for choices-->

							
             					<div class="form-group" id="multiple_ans">
								
                                <div class="row">
                                
                                 <div class="col-lg-6 ">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" 
                                    id="ans1_text_multiple" name="choices_multiple[0]" value="${choices_multiple[0]}">                            
                                  </div>
                                    <div class="checkbox i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox"  id="ans1_multiple">
                                        <i></i> 
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 1--> 
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" id="ans2_text_multiple"
                                    name="choices_multiple[1]" value="${choices_multiple[1]}">                            
                                  </div>
                                    <div class="checkbox i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" id="ans2_multiple">
                                        <i></i> 
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 2-->
	
    								<br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" id="ans3_text_multiple"
                                     name="choices_multiple[2]" value="${choices_multiple[2]}">                            
                                  </div>
                                    <div class="checkbox i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" id="ans3_multiple" >
                                        <i></i> 
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 3-->
    								
                                    <br>
                              
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" 
                                    id="ans4_text_multiple" name="choices_multiple[3]" value="${choices_multiple[3]}">                            
                                  </div>
                                   <div class="checkbox i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" id="ans4_multiple" >
                                        <i></i> 
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 4-->
                                  
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" 
                                    id="ans5_text_multiple" name="choices_multiple[4]" value="${choices_multiple[4]}">                            
                                  </div>
                                    <div class="checkbox i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" id="ans5_multiple" >
                                        <i></i>
                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 5-->

                                  <br>

                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 6</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" 
                                    id="ans6_text_multiple" name="choices_multiple[5]" value="${choices_multiple[5]}">                            
                                  </div>
                                  
                                  
                                    <div class="checkbox i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" id="ans6_multiple">
                                        <i></i> 
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 6-->                                  
                                  
                                  </div><!--Col for choices close-->
                                  
                                  <!------------------------>
                                  
                                  <div class="col-lg-6" >
                                  
                                    <div class="form-group">
                                    <div class="row">
                                      <label class="col-lg-2 col-lg-offset-1 control-label">Answer 1</label>
                                      <div class="col-lg-6">
                                        <input type="text" class="form-control" readonly placeholder="" 
                                        name="answers_multiple[0]" id="multiple_ans1" 
                                        value="${answers_multiple[0]}">                            
                                      </div><!--col close 1-->
                                      
                                      </div><!--row close for multiple answers-->
                                      
                                      <br>
									 
                                     <div class="row">
                                      <label class="col-lg-2 col-lg-offset-1 control-label">Answer 2</label>
                                      <div class="col-lg-6">
                                        <input type="text" class="form-control" readonly placeholder="" 
                                        name="answers_multiple[1]" id="multiple_ans2"
                                        value="${answers_multiple[1]}">                            
                                      </div><!--col close 2-->
                                      
                                      </div><!--row close for multiple answers-->

                                      <br>

                                      
                                      <div class="row">
                                      <label class="col-lg-2 col-lg-offset-1 control-label">Answer 3</label>
                                      <div class="col-lg-6">
                                        <input type="text" class="form-control" readonly placeholder="" 
                                        name="answers_multiple[2]" id="multiple_ans3"
                                        value="${answers_multiple[2]}">     
                                      </div><!--col close 3-->
                                      
                                      </div><!--row close for multiple answers-->
                                      
                                      <br>

                                      <div class="row">
                                      <label class="col-lg-2 col-lg-offset-1 control-label">Answer 4</label>
                                      <div class="col-lg-6">
                                        <input type="text" class="form-control" readonly placeholder="" 
                                        name="answers_multiple[3]" id="multiple_ans4"
                                         value="${answers_multiple[3]}">                            
                                      </div><!--col close 4-->                                      
                                      </div><!--row close for multiple answers-->
                                      
                                      <br>

                                      <div class="row">
                                      <label class="col-lg-2 col-lg-offset-1 control-label">Answer 5</label>
                                      <div class="col-lg-6">
                                        <input type="text" class="form-control" readonly placeholder="" 
                                        name="answers_multiple[4]" id="multiple_ans5"
                                        value="${answers_multiple[4]}">                            
                                      </div><!--col close 5-->                                      
                                      </div><!--row close for multiple answers-->
                                      
                                      <br>

                                      <div class="row">
                                      <label class="col-lg-2 col-lg-offset-1 control-label" >Answer 6</label>
                                      <div class="col-lg-6">
                                        <input type="text" class="form-control" readonly placeholder="" 
                                        name="answers_multiple[5]" id="multiple_ans6"
                                        value="${answers_multiple[5]}">                            
                                      </div><!--col close 6-->                                      
                                      </div><!--row close for multiple answers-->
                                      
                                      
                                      
                                     </div><!--form group close for multiple answers--> 
                                     
                                 
                                  </div><!--col-lg-6 for answers close-->
                                                                   
                                  </div>
                                  
                                 </div><!--Form group close for multiple choices-->


								                          
            						
                            <div class="form-group">
                              <label class="col-lg-2 col-lg-offset-4 control-label">Skill</label>
                              <div class="col-lg-3">
                                 <s:select id="skill"  cssStyle="width:380px;" list="skills" headerKey="" headerValue=""  
                                data-required="true" name="questionsUploadBO.skills" placeholder="Select a Test"
                                listKey="skill_id" listValue="skill_name" multiple="true">
                                 </s:select> 
                              </div>
                              </div>
                             
                             
                             <div class="form-group">

                                 <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level</label>
                                    <div class="col-lg-3">
                                    <s:select id="difficulty"  cssStyle="width:380px;" list="diff_levels" 
                                    listKey="difficulty_id" listValue="difficulty_name" 
                                    headerKey="" headerValue=""  data-required="true" name="questionsUploadBO.difficulty_id" placeholder="Select a Test">
                                 </s:select>
                                    </div>
 
                             </div><!--Form group close-->
                             
                             
                          
                            
                       		<div class="form-group">
                          				<label class="col-lg-2 control-label">Solution Text</label>
                          				<div class="col-lg-10">
                            				<textarea id="editor2" name="questionsUploadBO.solution_text" rows="10" cols="80"> </textarea> 
                          				</div>
                        	</div>
                       
                       		
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-2 control-label">Solution Video</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="Enter video URL" 
                                    id="videourl" name="questionsUploadBO.solution_video" value="${questionsUploadBO.solution_video}">                            
                                  </div>
                             </div> 
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label></label>
                        
                        <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" value="ACTIVE" name="questionsUploadBO.status" id="active_state">
                                    <i></i>
                                  Active
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" value="INACTIVE"  name="questionsUploadBO.status" id="inactive_state">
                                    <i></i>
                                    Inactive
                                  </label>
                                </div>           
                        
                        </div>
                                                             
                       
                                              
                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn btn-success" id="submit">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="button" class="btn btn-danger" id="reset">Reset</button>
                          </div>        
                          
                          <div class="col-lg-offset-1 col-lg-1">
                            <button type="button" class="btn btn-default" id="view">View Questions</button>
                          </div>                                          
                        </div><!--form group close-->
        	  </form>
              
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
          
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
           <%--  </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a> --%>
       <%--  </section> --%>
        
        
<script type="text/javascript">
	CKEDITOR.replace('editor1',{
        entities: false,
        basicEntities: false,
        entities_greek: false,
        entities_latin: false,
        htmlDecodeOutput: true,
        forceSimpleAmpersand : true,
    }); 
	
	</script>    
	<script type="text/javascript">
	CKEDITOR.replace('editor2',{
        entities: false,
        basicEntities: false,
        entities_greek: false,
        entities_latin: false,
        htmlDecodeOutput: true,
        forceSimpleAmpersand : true,
    }); 
	
	</script> 
        

 <!--        
</body>
</html> -->
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en" class="app">
<head>  
 <script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
   <script src="assets/js/jquery.min.js"></script>
   <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
   <link rel="stylesheet" href="assets/js/select2.1/select2.css"
	type="text/css" />
	
	
<script type="text/javascript">
CKEDITOR.replace('editor1');
CKEDITOR.replace('editor2');
</script> 
<script type="text/javascript">
	$(document).ready(function() {
		
		
		$("#skillsCombo").select2({
			minimumSelectionSize : 3,
			maximumSelectionSize : 3,
			allowClear : true
		});	
	$('#difficultyLevls').append($('#difficultyLevel').val());
	$('#skillsCombo').append($('#skillLevels').val());
	$('#testNameCombo').append($('#testNames').val());
	$('#senteequi').show();
	$('#textcomp').show();
	$('#isFree').attr('value','FREE');
	$('#isRefferal').attr('value','NO');  
	
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
	
	/* if($('#requeststring').val()=='SentenceEquivalence')
		{
		//alert("The requessted value is"+$('#requeststring').val());
		$('#senteequi').show();
		$('#textcomp').hide();
	//	$('#textcomp .double-ans').show();
		}
	if($('#requeststring').val()=='TextCompletion')
	{
		$('#senteequi').hide();
	$('#textcomp').show();
	} */
			
	$('.typeClass').click(function(){
		var valueData=$(this).attr('name');
		var checked=$(this).prop("checked");
	
		if(checked)
			{
			  if(valueData=='uploadBO.feeType')
				  {
				   $('#isFree').attr('value','FREE');
				  }
			  else{
				   $('#isRefferal').attr('value','YES');
			  }
			}
		else{
			 if(valueData=='uploadBO.feeType')
			  {
			   $('#isFree').attr('value','PAID');
			  }
		  else{
			   $('#isRefferal').attr('value','NO');
		  }
		}
	//alert('Final Data is:'+$('#isFree').attr('value')+"\n "+$('#isRefferal').attr('value'));
		
	});
	$('.choiceText').change(function(){
          var parent=$(this);
if(parent.val()!=''){
$('.choiceText').each(function(index,value){
	var child=$(this);
	//alert(' Parenet Id is s: '+child.prop('id'));
	if(child.val()!=''){
	if(parent.prop('id')!=child.prop('id'))
		{
		if(parent.val()==child.val()){ 
		//alert(' Parent Value is: '+parent.val()+" CHild Vlaue is: "+child.val());
		
		parent.val('');
		}
		 
		}
	}

});
}
	});
		$('#single').click(function() {
			
			singleButtonClicked();
			$('#selectedOption').attr('value',1);
			$('#question_type').attr('value','Single Blank');
			clearAllCheckBoxes();
			
		});
		$('#double').click(function() {
			
			doubleButtonClicked();
			$('#question_type').attr('value','Double Blank');
			$('#selectedOption').attr('value',2);
			clearAllCheckBoxes();
		});
		$('#triple').click(function() {
			
			tripleButtonClicked();
			$('#question_type').attr('value','Triple Blank');
			$('#selectedOption').attr('value',3);
			clearAllCheckBoxes();
			
		});
		 if($('#requeststring').val()=='SentenceEquivalence')
		 {
			
		 $('#double').trigger('click');
		 $('#blankSelectionDivision').hide();
		 
		 }
	 else{
		 $('#single').trigger('click');
		 singleButtonClicked();
	 }

	});
	
	function singleButtonClicked(){
		$('#textcomp').show();
		$('#textcomp .doubled').hide();
		$('.double-ans').hide();
		$('#textcomp .triple').hide();
		$('.triple-ans').hide();
		
	}
	function doubleButtonClicked(){

		$('.doubled').show();
		$('.double-ans').show();
		$('.triple').hide();
		$('.triple-ans').hide();
		
		
	}
	function tripleButtonClicked(){
		$('#textcomp .doubled').show();
		$('.double-ans').show();
		$('#textcomp .triple').show();
		$('.triple-ans').show();
	}
	function clearAllCheckBoxes(){
		$(".choiceSelected").prop('checked',false);
		$('.answers').attr('value','');
		
	}
	</script>
	<script type="text/javascript">
	function choiceSelected(choiceBoxIndex,checkBox)
	{ 
		var selectedCategory=$('#selectedOption').attr('value');
		var checkBoxSelectedLength=$(".choiceSelected:checked").length;
		var textFieldValue=$('#choice'+choiceBoxIndex).val();
		
		//alert(' This selected CHeck Box is: '+$(checkBox).prop('checked'));
		if($(checkBox).prop('checked')){		
		if(selectedCategory>=checkBoxSelectedLength)
			{
			 // alert(' Text Field Value si: '+textFieldValue);
			  if(textFieldValue=='')
				  {
				  $(checkBox).prop('checked',false);
				  }
			  else{
				//  alert(' Text Filed Value is: '+textFieldValue);
				 // $('#answer'+checkBoxSelectedLength).attr('value',textFieldValue);
				  var inputData=textFieldValue;
				  $('.answers').each(function(){
					//	alert(' input Data is: '+inputData);
						if(inputData!='')
							{
							//alert(' answer Value is: '+$(this).val());
							if($(this).val()=='')
								{
								 $(this).attr('value',inputData);
								 inputData='';
								}
							}
					});
			  }
			  
			}
		else{
			/* alert(' not Allowed to Select'); */
			 $(checkBox).prop('checked',false);
			
		}
		}else{
			$('.answers').each(function(){
				
				if($(this).val()!='')
					{
					//alert(' answer Value is: '+$(this).val());
					if($(this).val()==textFieldValue)
						{
						 $(this).attr('value','');
						}
					  
					}
			});
		}
		
		//alert(choiceBoxIndex+' th Check Box Selected \n\t'+selectedCategory+"\n\t Present Selected COunt is :"+checkBoxSelectedLength);
	}
	
	</script>
	<script type="text/javascript">
	
	function formSubmission(){
		
		var typesSelection=$('.typeClass:checked').length;
		var testName=$('#testNameCombo').val();
		var questionTitle=$('#questionTitle').val();
		var directions=$('textarea#directions').val();
		var editor1=CKEDITOR.instances['editor1'].getData();
		var skillsCombo=$('#skillsCombo').val();
		var difficultyLevls=$('#difficultyLevls').val();
		var editor2=CKEDITOR.instances['editor2'].getData();
		var solutionVideo=$('#solutionVideo').val();
		var selectedCategory=$('#selectedOption').attr('value');
		//alert('selected Category is: '+selectedCategory);
		var limitCount=0;
		var textFieldCount=0;
		if(selectedCategory==1)limitCount=5;
		else if(selectedCategory==2)limitCount=6;
		else if(selectedCategory==3)limitCount=9;
		
			if('<s:property value="%{#request.requestedString}" />'=='SentenceEquivalence') limitCount=5;
			
		var loopNotEntered=true;
		
	
		
		 if(testName==-1)
			{
			 toastr.warning(" Please Select Test Name ", " Test Name Not Selected ");  
			$('#testNameCombo').focus();
			  return false;
			}
		else if(questionTitle==''){
			 toastr.warning("Please Enter Question Tilte ", " Question Title Not Be Empty ");
			$('#questionTitle').focus();
         return false;			
		}else if(directions==''){
			 toastr.warning("Please Enter Question Direction", " Questions Direction Not Be Empty");
			$('#directions').focus();
         return false;
			
		}else if(editor1==''){
			 toastr.warning("Please Enter  Question Name", "Question Name Not Be Empty");
			$('#editor1').focus();
         return false;
		}else{
			
			 $('.choiceText').each(function(){
			
					textFieldCount=textFieldCount+1;
				//	alert(' textField Count is: '+textFieldCount+" Limit COunti si: "+limitCount);		
					if(textFieldCount<=limitCount){
					if($(this).val()=='')
						{
						 toastr.warning("Please Select Question Choice", " Question Choice Not Be Empty");
						 $(this).focus();
						  loopNotEntered=false;
						  return false;
						}
					}
					
				});
			 if(loopNotEntered){
				 
			 if('<s:property value="%{#request.requestedString}" />'=='SentenceEquivalence')
			  {
				 $('.form-control toolTip').each(function(){
						if($(this).val()=='')
							{
							  $(this).focus();
							  toastr.warning("Please Enter Question Tool Tip", " Question Tool Tip Not Be Empty");
							  loopNotEntered=false;
							  return false;
							}
						
						
					});
			  }
			 if(loopNotEntered){
			  if($(".choiceSelected:checked").length<selectedCategory){
				  toastr.warning("Please Select Question Answer", " Question Answer Not Be Empty");
				  $(".choiceSelected:checked").focus();
				  return false;
			  }
			  else if(skillsCombo==-1  || skillsCombo.length < 3){
				  toastr.warning("Please Select three Question Skill Levels", "Question Skill Level Not Selected !");
					$('#skillsCombo').focus();
		         return false;
				}else if(difficultyLevls==-1){
					toastr.warning("Please Select Question Difficulty Level", "Question Difficulty Level Not Selected !");
					$('#difficultyLevls').focus();
		         return false;
				}else if(editor2==''){
					toastr.warning("Please Enter Question Solution", "Question Solution Not Entered");
				//	alert(' Please Enter Solution Text ');
					$('#editor2').focus();
		         return false;
				}
				else{
					$('#questionText').attr('value',CKEDITOR.instances['editor1'].getData());
					$('#solutionText').attr('value',CKEDITOR.instances['editor2'].getData());
					var formSerialize=$('#dataForm').serialize();
					$.ajax({
						tye:'POST',
						url:'jsonQuestionUploading.action',
						data:formSerialize,
						dataType:'json',
						success:formSuccessfllySubmited,
						errror:formSubmitionFailed
						
					});
					//alert(' Now Form is Ready To Submit ');
					/*
else if(solutionVideo==''){
					
					alert(' Please Enter Solution Video Link ');
					$('#solutionVideo').focus();
		         return false;
				}*/
				}
			 }
			 }
		}
		
		
		 function formSuccessfllySubmited(jsonData,status){
			
			 
			  if(jsonData.exceptionRaised)
				 {
				  toastr.error(jsonData.exceptionIs, "Operation Failed");
				 }
			 else{
				 var questionId=jsonData.questionId;
			
				 $('#questionId').attr('value',questionId);
				 if(questionId==0)
					 {
					 toastr.success("Data Saved",
						"Question Inserted Successfully");
					
					/*  $('#operationName').attr('value','Question Inserted Successfully');
					
					 $('#questionForm').trigger('click'); */
					 
					 }
				 else{
					 toastr.success("Data Updated",
						"Question Updated Successfully");
					 $('#operation').text("Update").removeClass("btn-primary").addClass("btn-success");
					/*  $('#operationName').attr('value','Question Updated Successfully');
					
					 $('#questionForm').trigger('click'); */
					 
				 }
				 	 
			 }
			 
		 }
		 function formSubmitionFailed(errorIs)
		 {
			 toastr.error(errorIs.responseText, "Operation Failed");
			 
		 }
			
		
	}
	
	</script>  
	<script type="text/javascript">
	$(document).ready(function(){
		loadFormData();
		$('#resetButton').click(function(){
			//alert("hai");
			$('input[name="uploadBO.feeType"]').prop('checked',false);
			CKEDITOR.instances['editor1'].setData("");
			CKEDITOR.instances['editor2'].setData("");
			$('#solutionVideo').val("");
			$('.form-control toolTip').attr("value","");
			//alert($('.choiceSelected').val());
			$('.choiceSelected').val("");
			//alert($('#choice1').val());
			$('#choice1,#choice2,#choice3,#choice4,#choice5,#choice6,#choice7,#choice8,#choice9,#toolTip1,#toolTip2,#toolTip3,#toolTip4,#toolTip5,#toolTip6,#answer1,#answer2').attr("value","");
			
			//loadFormData();
		});
	});
	function loadFormData(){
		var questionId='<s:property value="#request.questionId"/>';
		alert('question Id is; '+questionId);
		if(questionId>0)
			{
			
			 $('#operation').text("Update").removeClass("btn-success").addClass("btn-primary");;
			
			var tableData=$('#tableData').val();
		//alert(' table Data is: '+tableData);
			var jsonTableData=JSON.parse(tableData);
			var testId=-1;
			var typeId=-1;
			var questionTitle='';
			var Directions='';
			var questionName='';
			var skillId1='';
			var skillId2='';
			var skillId3=''
			var difficultyId='';
			var solutionText='';
			var solutionVideo='';
			var status='INACTIVE';
			var isFree='';
			var isRefferal='';
			 var data=jsonTableData.tableData;
			// alert(' data is; '+data);
			 var choices = [];
			  var toolTips=[];
			  var answers=[];
			  $.each(data, function(index, data) {
				//  alert('index is; '+index+" data is : "+data)
				   testId=data.test_id;
				   typeId=data.type_id;
					 questionTitle=data.question_title;
					 Directions=data.question_directions;
					 questionName=data.question;
					 skillId1=data.skill_id1;
					 skillId2=data.skill_id2;
					 skillId3=data.skill_id3;
					 difficultyId=data.diff_id;
					 solutionText=data.solution_text;
					 solutionVideo=data.solution_video;
					 status=data.status;
					 isFree=data.access_type;
					 isRefferal=data.referral;
					 if(choices.indexOf(data.choice)==-1)
					 choices.push(data.choice);
					 if(toolTips.indexOf(data.choice_tooltip)==-1)
					 toolTips.push(data.choice_tooltip);
					 if(answers.indexOf(data.answer)==-1)
					 answers.push(data.answer);
			  });
			  
	 
			  $.each(choices, function( index, value ) {
				  var index=parseInt(index+1);
				  $('#choice'+index).attr('value',value);
				});
			  $.each(toolTips, function( index, value ) {
				  var index=parseInt(index+1);
				  $('#toolTip'+index).attr('value',value);
				});
			  $.each(answers, function( index, value ) {
				  var index=parseInt(index+1);
				  $('#answer'+index).attr('value',value);
				});
			 
			 if(isRefferal=='NO')
				 {
				 $('input[name="uploadBO.refferal"]').prop('checked',false);
				  
				 }
			 else{
				 $('input[name="uploadBO.refferal"]').prop('checked',true);
			 }
			 if(isFree=='FREE')
				 {
				 $('input[name="uploadBO.feeType"]').prop('checked',true);
				   
				 }
			 else{
				 $('input[name="uploadBO.feeType"]').prop('checked',false);
				
			 }
			 if(status=='ACTIVE')
				 {
				 $('#ACTIVE').trigger('click');
				 }else{
					 $('#INACTIVE').trigger('click');
				 }
			 
			 //$('input:radio[name="uploadBO.status"]').prop('value',status);
			 
			  $('#questionTitle').val(questionTitle);
			  $('#directions').val(Directions);
			  $('#testNameCombo').val(testId);
			  CKEDITOR.instances['editor1'].setData(questionName);
			 // $('#skillsCombo').val(skillId1);
			  $("#skillsCombo").select2("val", [ skillId1, skillId2, skillId3 ]);
			  $('#difficultyLevls').val(difficultyId);
			  CKEDITOR.instances['editor2'].setData(solutionText);
		  $('#solutionVideo').val(solutionVideo);
			  
			  $('#blankSelectionDivision').hide();
			  if($('#requeststring').val()!='SentenceEquivalence')
				 {

				  if(typeId==1)
					  {
					  $('#single').trigger('click');
					  }
				  else if(typeId==2)
					  {
					  $('#double').trigger('click');
					  }
				  else if(typeId==3)
				  {
				  $('#triple').trigger('click');
				  }	
				 }
			 $('.answers').each(function(){
				 var answer=$(this).val();
				 $('.choiceText').each(function(index,value){
					index=index+1;
					var choiceText=$(this).val();
					if(answer==choiceText) $('#checkBox'+index).prop('checked',true);
				 
				 });
			  });
			  
			}
		
	}
	
	</script>
 
</head>

<body class="">
  
<section id="content">

<s:hidden id="difficultyLevel" value="%{#request.difficultyLevels}"/>
<s:hidden id="skillLevels" value="%{#request.skillLevels}"/>
<s:hidden id="testNames" value="%{#request.testNames}"/>
<s:hidden id="selectedOption" value=""/>



          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <s:hidden value="%{#request.requestedString}" id="requeststring" name="operation"/>
            <s:if test="#request.questionId>0">
           <s:hidden id="tableData" value="%{#request.tableData}"/>
            </s:if>
            
            
            <div class="panel-heading"> <span class="panel-title"><s:property value="%{#request.operation}" /></span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
                          <form class="bs-example form-horizontal" id="dataForm">
                          <s:hidden name="uploadBO.category_name" value="%{#request.operation}"/>
                          <s:hidden value="" name="uploadBO.feeType" id="isFree"/>
                          <s:hidden value="" name="uploadBO.refferal" id="isRefferal"/>
                          <s:hidden value="" name="uploadBO.question_type" id="question_type"/>
                          <s:hidden value="%{#request.questionId}" name="uploadBO.question_id" id="questionId"/>
                               <div class="form-group">
                                <label class="col-lg-2 col-lg-offset-4  control-label">Type</label>
                               <div class="checkbox i-checks col-lg-1">
                                  <label>
                                    <input type="checkbox" checked  class="typeClass" name="uploadBO.feeType">
                                    <i></i>
                                    Free
                                  </label>
                                </div>
                                <div class="checkbox i-checks col-lg-2">
                                  <label>
                                    <input type="checkbox"  class="typeClass" name="uploadBO.refferal">
                                    <i></i>
                                    Refferal
                                  </label>
                                </div>
                                    </div><!--form group close-->   
                             <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label">Test Name</label>
                                <div class="col-lg-5">
                                <select name="uploadBO.test_id" class="form-control m-b" id="testNameCombo" >
                                 
                                 </select>
                                </div>
                             </div>
                                
                                
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Question title</label>
                                  <div class="col-lg-5">
                                    <input type="text" class="form-control" name="uploadBO.question_title"  placeholder="" id="questionTitle">                            
                                  </div>
                             </div>    
                             
                             
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
                                  <div class="col-lg-3">
                                    <textarea cols="50" rows="4" style="max-width:450px" id="directions" name="uploadBO.question_directions"></textarea>                          
                                  </div>
                             </div>    
                                  
                       	
                          	<div class="form-group">
                          				<label class="col-lg-2 control-label">Question</label>
                          				<s:hidden name="uploadBO.question" id="questionText"/>
                          				<div class="col-lg-10">
                            				<textarea class="ckeditor form-control" id="editor1" name="questiontitle" rows="10" cols="80" > </textarea>
										<%-- 	<script> CKEDITOR.replace('questiontitle') </script> --%>
                          				</div>
                        	</div>
                       
                       
                     
                       
                       		<div id="textcomp" class="form-group" >
                       		
                       		
                       		 <div class="form-group" id="blankSelectionDivision">
                       				 <label class="col-lg-2 col-lg-offset-3 control-label">Status</label>
                        			 <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="optionSelection" value="" id="single">
                                    <i></i>
                                   Single Blank
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="optionSelection" value="" id="double" >
                                    <i></i>
                                    Double Blank
                                  </label>
                                </div>   
                                
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="optionSelection" value="" id="triple">
                                    <i></i>
                                    Triple Blank
                                  </label>
                                </div>              
                            
                        </div>
                       		
                            <div class="form-group">
								
                                <div class="row">
                                
                                 <div class="col-lg-6 ">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 1</label>
                                  <div class="col-lg-6">
                                    <input type="text"  class="choiceText form-control" placeholder="" id="choice1" name="uploadBO.choices" >                            
                                  </div>
                                  
                                   <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(1,this)" id="checkBox1">
                                        <i></i>                                       
                                      </label>
<!--                                       <div class="dee">sdfjsfjsl</div> -->
                                    </div>
                                    
                                  </div><!--row close for internal choices 1-->

                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 2</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText form-control" placeholder="" id="choice2" name="uploadBO.choices">                            
                                  </div>
                                  
                                   <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(2,this)" id="checkBox2"> 
                                        <i></i>                                       
                                      </label>
                                    </div>
                                    
                                  </div><!--row close for internal choices 2-->
	
    								<br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 3</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice3" name="uploadBO.choices">                            
                                  </div>
                                  
                                   <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(3,this)" id="checkBox3"> 
                                        <i></i>                                       
                                      </label>
                                    </div>
                                    
                                  </div><!--row close for internal choices 3-->
    								
                                    <br>
                              
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 4</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice4" name="uploadBO.choices">                            
                                  </div>
                                 
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(4,this)" id="checkBox4">
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 4-->
                                  
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 5</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice5" name="uploadBO.choices">                            
                                  </div>
                                 
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(5,this)" id="checkBox5"> 
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 5-->
                                  
                                  
                                  <s:if test="%{#request.requestedString!='SentenceEquivalence'}">
                                 
                                    <div class="row doubled" style="padding-top:20px"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 6</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice6"  name="uploadBO.choices" >                            
                                  </div>
                                 
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(6,this)" id="checkBox6">
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 6-->
                                 
                                  
                                    <div class="triple" style="display:none;padding-top:20px">
                                    <div class="row" ><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 7</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice7" name="uploadBO.choices">                            
                                  </div>
                                 
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(7,this)" id="checkBox7">
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 6-->
                                
                                   
                                    <div class="row" style="padding-top:20px"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 8</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice8" name="uploadBO.choices">                            
                                  </div>
                                 
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox" class="choiceSelected" onclick="choiceSelected(8,this)" id="checkBox8">
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 6-->
                                  
                                
                                    <div class="row" style="padding-top:20px" ><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-1  control-label">Choice 9</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="choiceText  form-control" placeholder="" id="choice9" name="uploadBO.choices" id="checkBox9">                            
                                  </div>
                                 
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="checkbox"  class="choiceSelected" onclick="choiceSelected(9,this)">
                                        <i></i>                                       
                                      </label>
                                    </div>
                                  </div><!--row close for internal choices 6-->
                                  
                               </div>
                               </s:if>
                                  </div><!--Col for choices close-->
                                   <s:if test="%{#request.requestedString=='SentenceEquivalence'}">
                                   <div class="col-lg-6">
                                  
                                  <div class="form-group">
                                  <label class="col-lg-3 col-lg-offset-1 control-label">Choice 1 tool tip</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control toolTip" placeholder=""  name="uploadBO.tooltips" id='toolTip1'>                            
                                  </div>
                             	  </div> <!--form group close-->
                               
                                  <div class="form-group">
                                   <label class="col-lg-3 col-lg-offset-1 control-label">Choice 2 tool tip</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control toolTip" placeholder="" name="uploadBO.tooltips" id='toolTip2'>                            
                                  </div>
                             	  </div> <!--form group close-->
                                  

                                  
                                  <div class="form-group">
                                   <label class="col-lg-3 col-lg-offset-1 control-label">Choice 3 tool tip</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control toolTip" placeholder="" name="uploadBO.tooltips" id='toolTip3'>                            
                                  </div>
                             	  </div> <!--form group close-->
                                  
                                  
                                  <div class="form-group">
                                   <label class="col-lg-3 col-lg-offset-1 control-label">Choice 4 tool tip</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control toolTip" placeholder="" name="uploadBO.tooltips" id='toolTip4'>                            
                                  </div>
                             	  </div> <!--form group close-->
                                 

                                  
                                  <div class="form-group">
                                  <label class="col-lg-3 col-lg-offset-1 control-label">Choice 5 tool tip</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control toolTip" placeholder="" name="uploadBO.tooltips" id='toolTip5'>                            
                                  </div>
                             	  </div> <!--form group close-->
                             
                                  </div>
                                  </s:if>
                                                             
                                  </div>
                                  
                                  
                                 </div>
                                             
</div>                    		
                            <br>
            				
                            <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label">Skills</label>
                                <div class="col-lg-5">
                             <%--    <select name="uploadBO.skills"  multiple class="chosen-select" id="skillsCombo">
                                  
                                 </select> --%>
                                 	<s:select id="skillsCombo" cssStyle="width: 100%;" list="#{}"
											multiple="true" headerKey="-1" headerValue=""
											name="uploadBO.skills" data-required="true"
											data-placeholder="Select Skills">
										</s:select>
                                </div>
                             </div>
                             
                             
                             <div class="form-group">

                                 <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level</label>
                                    <div class="col-lg-5">
                                    <select  class="form-control m-b" id="difficultyLevls" name="uploadBO.difficulty_id">
                                      
                                     </select>
                                    </div>
 
                             </div>                               
                             
                              <div class="form-group">
                                 <!--  <div class="col-lg-5">     -->                                
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Answer 1</label>
                                  <div class="col-lg-5">
                                    <input type="text" class=" answers  form-control" readonly placeholder="Answer 1" id="answer1" name="uploadBO.answers">                            
                                  </div>
                             		<!-- </div>  -->
                             
                                  </div>
                                 <div class="double-ans" style="display:none;"> 
                                     <div class="form-group">
                                  <!-- <div class="col-lg-6"> -->
                                  
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Answer 2</label>
                                  <div class="col-lg-5">
                                    <input type="text" class="answers  form-control" readonly placeholder="Answer 2" id="answer2" name="uploadBO.answers">                            
                                  </div>
                             		<!-- </div>  -->
                             </div>
                             
                                  </div>
                                   <s:if test="%{#request.requestedString!='SentenceEquivalence'}">
                                 <div  class="triple-ans" style="display:none;">  
                                <!--   <div class="col-lg-6"> -->
                                    <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Answer 3</label>
                                  <div class="col-lg-5">
                                    <input type="text" class="answers" readonly placeholder="Answer 3" id="answer3" name="uploadBO.answers">                            
                                  </div>
                             		<!-- </div>  -->
                             
                                  </div>
                                 
                                      </div>   
                                  </s:if>   
                             
                            
                       		<div class="form-group">
                          				<label class="col-lg-2 control-label">Solution Text</label>
                          				<s:hidden id="solutionText" name="uploadBO.solution_text" />
                          				<div class="col-lg-10">
                            				<textarea class="ckeditor form-control" id="editor2" name="questiondesc" rows="10" cols="80" > </textarea>
											<%-- <script> CKEDITOR.replace('questiondesc' ) </script> --%>
                          				</div>
                        	</div>
                       
                       		
                              <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-2 control-label">Solution Video</label>
                                  <div class="col-lg-6">
                                    
                                    <input class="form-control" id="solutionVideo" type="text" placeholder="" name="uploadBO.solution_video"></input>                            
                                  </div>
                             </div> 
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label></label>
                        
                        <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="uploadBO.status" value="ACTIVE" checked id="ACTIVE">
                                    <i></i>
                                   Enable
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="uploadBO.status" value="INACTIVE" id="INACTIVE">
                                    <i></i>
                                    Disable
                                  </label>
                                </div>           
                        </div>
                                                             
                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button"  id="operation" class="btn btn-success" onClick="formSubmission()"><lable>Submit</lable></button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="reset" class="btn btn-danger" id="resetButton">Reset</button>
                          </div>
                           <div class="col-lg-offset-1 col-lg-1">
                           <s:if test="%{#request.requestedString=='SentenceEquivalence'}">
             <s:form action="SentanceEqualanceQuestions.action"  method="post">
             <s:hidden name="operationName" id="operationName" value=""/>
            <!--   <button type="submit" class="btn btn-default" id="questionForm" value="View Questions">View Questions</button> -->
            <a href="SentanceEqualanceQuestions.action" class="btn btn-default" > View Questions</a>
           <%--  <s:submit class="btn btn-default" id="questionForm" value="View Questions"/>  --%>
             </s:form>
             </s:if>
             <s:else>
             <s:form action="TextCompletionQuestions.action"  method="post">
             <s:hidden id="operationName" name="operationName" value=""/>
              <a href="TextCompletionQuestions.action" class="btn btn-default" > View Questions</a>
         <!--     <button type="submit" class="btn btn-default" id="questionForm" value="View Questions">View Questions</button> -->
            <%--  <s:submit class="btn btn-default" id="questionForm" value="View Questions"/> --%>
             </s:form>
             </s:else>   
             </div>                                             
                        </div><!--form group close-->
        	  </form>
              
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
          
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
            </section>
          </section>
  
 	<script src="assets/js/select2.1/select2.min.js"></script>
</body>

</html>
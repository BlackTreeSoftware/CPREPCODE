<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<head>
<script type="text/javascript">
$(document).ready(function(){ 
	//alert("hai");

	//alert('question:'+$('#question_id').val()); 
	//alert('Free:'+$('#isFree').val()+"\tReferral:"+$('#isReferral').val());  
	//alert('question:'+$('#question_text').val()+"\t Solution:"+$('#solution_text').val()); 
	//$('#_add,#_addbreak,#_delete').hide();
	 $('#view,#save').click(function(){
        		
        		$('#add,#delete').show();
        	
	}); 
	 });

</script>
</head> 
   
<div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default" style="margin-bottom:0px">
             
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" >
            
              <form id="form2" class="bs-example form-horizontal" data-validate="parsley" method="post">  
                               
                                <div class="form-group">
                                <div><s:hidden id="questionId" name="questionsBO.question_id" value="%{#request.questionId}" /></div> 
								<div><s:hidden id="sectionId" name="questionsBO.section_id" value="%{#request.sectionId}" /></div> 
								<div><s:hidden id="categoryId" name="questionsBO.category_id" value="%{#request.categoryId}" /></div> 
								<div><s:hidden id="typeId" name="questionsBO.questiontype_id" value="%{#request.typeId}" /></div> 
								<div><s:hidden id="avgtime" name="questionsBO.avg_time" value="%{#request.avgTime1}" /></div> 
								<div><s:hidden id="accessType1"  value="%{#request.accessType}" /></div> 
								<div><s:hidden id="referral1"  value="%{#request.referral}" /></div>
								<div><s:hidden id="status1"  value="%{#request.status}" /></div> 
								
                                </div>                                                 
                       			
                                <div class="form-group">
                                <label class="col-lg-2  col-lg-offset-4  control-label">Type</label>
                               
                               <div class="radioo i-checks col-lg-1">
                                  <label>
                                    <input id="typecheck" type="checkbox" name="questionsBO.access_type" value="PAID">
                                    <i></i>
                                   Free
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input id="referral" type="checkbox" name="questionsBO.refferal" value="NO" >
                                    <i></i>
                                    Referral
                                  </label>
                                </div>
                                
                               </div><!--form group close-->    
                            
                             <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label">Test Name</label>

                                <div class="col-lg-3">
                                <%-- <s:select id="test"  cssStyle="width:250px;" list="#{'1':'Practice Session','2':'Practice Test','3':'Practice Test'}" headerKey="-1" headerValue="select"  data-required="true" name="questionsBO.test_id" placeholder="Select a Test" value="%{#request.testId}"> --%>
                                <s:select id="test"  cssStyle="width:250px;" list="#{'1':'Practice Session'}" headerKey="-1" headerValue="select"  data-required="true" name="questionsBO.test_id" placeholder="Select a Test" value="%{#request.testId}">
 								<%--
                                <div class="col-lg-5">
                                <s:select id="test"  cssStyle="width:100%;" list="#{'1':'Practice Session','2':'Practice Test','3':'Practice Test'}" headerKey="" headerValue=""  data-required="true" name="questionsBO.test_id" placeholder="Select a Test" value="%{#request.testId}"> --%>

                                 </s:select>
                                </div>
                             </div>
                                
                                
                                <div class="form-group">
                              <label class="col-lg-2 col-lg-offset-4 control-label">Lesson Name</label>
                              <div class="col-lg-5">

                                <s:select id="lesson" cssStyle="width:350px;" list="lessons" multiple="true" headerKey="-1" headerValue="select" name="questionsBO.lessons" data-required="true" value="%{#request.lessons1}" >

                               <%--  <s:select id="lesson" cssStyle="width:100%;" list="lessons" multiple="true" headerKey="" headerValue="" name="questionsBO.lessons" data-required="true" value="%{#request.lessons1}" > --%>

                                </s:select>
                              </div>
                              </div>
                                
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Question title</label>
                                  <div class="col-lg-5">
                                    <input id="title" type="text" class="form-control" name="questionsBO.question_title"  data-required="true" placeholder="Enter Question Title Here" value="${requestScope.questionTitle}">                            
                                  </div>
                             </div>    
                             
                             
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
                                  <div class="col-lg-3">
                                    <textarea id="directions" cols="50" rows="4" name="questionsBO.question_directions"  data-required="true" style="max-width:450px">${requestScope.QuestionDirections}</textarea>                          
                                  </div>
                             </div>    
                             
                             
                      	   <div class="form-group">
                       				 <label class="col-lg-2 col-lg-offset-4 control-label">Type</label>
                        			
                               <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input id="normalradio" type="radio" name="questionsBO.actionType" value="Normal" checked>
                                    <i></i>
                                   Normal 
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input id="graphradio" type="radio" name="questionsBO.actionType" value="Graph" >
                                    <i></i>
                                   Graph
                                  </label>
                                </div>
                            
                                                         
                          </div><!--form group-->
                          
                           <div class="form-group" id="grapharea">
                           <label class="col-lg-2 col-lg-offset-4 control-label">Select Graph</label>
                                <div class="col-lg-3">
                                <s:select id="graph" cssStyle="width:350px;" list="interpretionBOs"  headerKey="" headerValue="" listKey="graph_id" listValue="graph_title" name="questionsBO.graphId" data-required="true" placeholder="Select a Graph" value="%{#request.graphId}">
                                 </s:select>
                                </div>
                           </div>
                                  
                       	
                          	<div class="form-group">
                          				<label class="col-lg-2 control-label">Question</label>
                          				<div class="col-lg-10">
                            				<textarea id="question" class="ckeditor form-control"  name="questionsBO.question" rows="10" cols="80">${requestScope.Question1}</textarea>
											
                          				</div>
                        	</div>
                       
                       		 <div class="form-group">
                       				 <label class="col-lg-2 col-lg-offset-4 control-label">Type</label>
                        			 <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input id="singleradio" type="radio" name="type" value="10" checked>
                                    <i></i>
                                   Single 
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input id="doubleradio" type="radio" name="type" value="13" >
                                    <i></i>
                                   Double
                                  </label>
                                </div>                                       
                            
                          </div><!--form group-->
                            
                            
                            <div class="form-group" id="dsingle">
								
                                <div class="row">
                                
                                 <div class="col-lg-6 col-lg-offset-3">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-4 col-lg-offset-2 control-label">Numeric Entry1</label>
                                  <div class="col-lg-6">
                                    <input id="entry11" type="text" name="questionsBO.answers[0]" class="form-control"  placeholder="Enter Entry Here" value="${requestScope.answers1[0]}" />                            
                                  </div>
                                 
                                  </div><!--row close for internal choices 1-->

                                  <br>
                                 
                                  </div><!--Col for choices close-->
                                                                   
                                  </div>
                            </div><!--Form group close for choices-->

                            
                            <div class="form-group" id="ddouble">
								
                                <div class="row">
                                
                                 <div class="col-lg-6 col-lg-offset-3">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-4 col-lg-offset-2 control-label">Numeric Entry2</label>
                                  <div class="col-lg-6">
                                    <input id="entry22" type="text" name="questionsBO.answers[1]" class="form-control"    placeholder="Enter Entry Here"  value="${requestScope.answers1[1]}" />                            
                                  </div>
                                 
                                  </div><!--row close for internal choices 1-->

                                  <br>
                                  
                                  <%-- <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-4 col-lg-offset-2 control-label">Numeric Entry2</label>
                                  <div class="col-lg-6">
                                    <input id="entry12" type="text" name="questionsBO.answers[2]" class="form-control"    placeholder="Enter Entry Here" value="${requestScope.answers1}" />                            
                                  </div>
                                 
                                  </div><!--row close for internal choices 1-->
 --%>
                                  <br>
                                 
                                  </div><!--Col for choices close-->
                                                                   
                                  </div>
                            </div><!--Form group close for choices-->
                            
                            
            						
                            <div class="form-group">
                              <label class="col-lg-2 col-lg-offset-4 control-label">Skill</label>
                              <div class="col-lg-3">
                                <s:select id="skill" cssStyle="width:250px;"  list="skills" multiple="true" headerKey="" headerValue="" name="questionsBO.skills" data-required="true" placeholder="Select Skills" value="%{#request.skills1}">
                                </s:select>   
                              </div>
                              </div>
                             
                             
                             <div class="form-group">

                                 <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level</label>
                                    <div class="col-lg-3">
                                    <s:select id="difficulty" cssStyle="width:250px;" list="difficulties" headerKey="" headerValue="" name="questionsBO.difficulty_id" data-required="true" placeholder="Select Difficulty" value="%{#request.difficulty1}" >
                                    </s:select>   
                                    </div>
 
                             </div><!--Form group close-->
                             
                             
                          
                            
                       		<div class="form-group">
                          				<label class="col-lg-2 control-label">Solution Text</label>
                          				<div class="col-lg-10">
                            				<textarea id="stext" class="ckeditor form-control"  name="questionsBO.solution_text" rows="10" cols="80">${requestScope.solutionText1}</textarea>
											
                          				</div>
                        	</div>
                       
                       		
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-2 control-label">Solution Video</label>
                                  <div class="col-lg-6">
                                     <input id="svideo" type="text" class="form-control" name="questionsBO.solution_video" data-type="url"  data-required="true" placeholder="Enter Solution Video URL Here" value="${requestScope.solutionVideo1}" />                        
                                  </div>
                             </div> 
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label></label>
                         <div class="radioo i-checks col-lg-2">
                                  
                                  <label>
                                    <input id="status1" type="radio" name="questionsBO.status" value="ACTIVE" checked>
                                    <i></i>
                                   Active
                                  </label>
                                 
                                 </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                <s:if test="%{#request.status=='INACTIVE'}">
                                  <label>
                                    <input id="status2" type="radio" name="questionsBO.status" value="INACTIVE" checked="checked">
                                    <i></i>
                                   In Active
                                  </label>
                                 </s:if>
                                 <s:else>
                                 <label>
                                    <input id="status2" type="radio" name="questionsBO.status" value="INACTIVE" >
                                    <i></i>
                                   In Active
                                  </label>
                                 </s:else>
                                </div>
                        </div>
                                                             
                       
                                              
                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="submit" id="save" class="btn  btn-success">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="button" class="btn  btn-danger" id="reset">Reset</button>
                          </div> 
                         
                         <div class="col-lg-offset-1 col-lg-1">
                            <button type="button" class="btn btn-default"   id="view">Questions List</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
              
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
          
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
             
             
<script type="text/javascript">
CKEDITOR.replace('question');
CKEDITOR.replace('stext');
</script> 

<script>
        $(document).ready(function() { 
        	
        	       	
        	var catId=$("#categoryId").val();
        	var typeId=$("#typeId").val();
        	//for categories
        	if(catId==6){
        		$("#normalradio").prop("checked","checked");
            	$("#graphradio").prop("disabled",true);
            	$("#grapharea").hide();
        	}
        	else if(catId==5){
        		$("#graphradio").prop("checked","checked");
        		$("#normalradio").prop("disabled",true);
        		$("#grapharea").show();
        	}
        	else{
        		$("#grapharea").hide();
        		
        	}
        	
        	//for types
        	if(typeId==10 || typeId==16){
        		//alert("single");
        		$("#singleradio").prop("checked","checked");
        		$("#doubleradio").prop("disabled",true);
        		$("#dsingle").show();
        		$("#ddouble").hide();
        	}
        	else if(typeId==13 || typeId==17){
        		//alert("double");
        		$("#doubleradio").prop("checked","checked");
        		$("#singleradio").prop("disabled",true);
        		$("#dsingle").show();
        		$("#ddouble").show();
        		//alert("2");
        	}
        	else{
        		$("#dsingle").show();
        		$("#ddouble").hide();
        	}
        	
        	//access type
        	if($("#accessType1").val()=='FREE'){
        		$("#typecheck").prop("checked","checked");
        		$("#typecheck").val("FREE");
        	}
        	if($("#referral1").val()=='YES'){
        		$("#referral").prop("checked","checked");
        		$("#referral").val("YES");
        	}
        	
        	
        	
        	$("#test").select2({
        		 
        	}); 
        	
        	$("#lesson").select2({
        		placeholder: "Select lessons",
        		maximumSelectionSize: 1
       	    });
        	
        	$("#graph").select2({
       		 
       		}); 
        	
        	$("#skill").select2({
        		maximumSelectionSize: 3
       		}); 
        	
        	$("#difficulty").select2({
       		 
       		}); 
        	
        	
        	$("#typecheck").click(function(){
         		//alert("Hi");
         		if($(this).is(":checked")){
         			//alert("True");
         			$(this).val("FREE");
         		}
         		else{
         			//alert("False");
         			$(this).val("PAID");
         		}
         	});
        	
        	$("#referral").click(function(){
         		//alert("Hi");
         		if($(this).is(":checked")){
         			//alert("True");
         			$(this).val("YES");
         		}
         		else{
         			//alert("False");
         			$(this).val("NO");
         		}
         	});
        	
        	$("#singleradio").click(function(){
        		
        		$("#dsingle").show();
        		$("#ddouble").hide();
        		//$("#entry1").attr("data-required","true");
        		$("#entry22").val("");
        		$("#entry11").val("");
        		
        	});
        	
        	$("#doubleradio").click(function(){
        		
        		$("#dsingle").show();
        		$("#ddouble").show();
        		$("#entry11").val("");
        		$("#entry22").val("");
        		
        		
        		
        		
        	});
        	
        	$("#normalradio").click(function(){
        		$("#grapharea").hide();
        	});
        	
        	$("#graphradio").click(function(){
        		$("#grapharea").show();
        		
        		
        	});
        	
        	//$("#normalradio").click();
        	//$("#singleradio").click();
        	
        	$('#form2').on('submit', function (e) {	

           	/// alert("addition");
           //	if($('#form2').parsley('validate')){
           		
         //  	 alert("true");
        	 var question1=CKEDITOR.instances['question'].getData();
       		 var solution1=CKEDITOR.instances['stext'].getData();
       		
       		 $("#question").val(question1);
       		 $("#stext").val(solution1);
       		 
       		 var lesson=$("#lesson").val();
       		var skill=$("#skill").val();
       		var difficulty=$("#difficulty").val();
       		 
       		if($("#test").val()==-1){
       			toastr.error("Select Test");
       			return false;
       		 }else if(lesson==''|| lesson==null){ 
       			toastr.error("Select Lesson");
       			return false;
      		 }else if($("#title").val()==""){ 
       			toastr.error("Enter title");
       			return false;
      		 }
       		
       		else if(question1==""){
       			toastr.error("Enter Question");
       			return false;
       		 }else if(skill==''|| skill==null){ 
        			toastr.error("Select Skill");
           			return false;
          		 }else if(difficulty==''|| difficulty==null){ 
            			toastr.error("Select Difficulty");
               			return false;
              		 }
       		
       		else if(solution1==""){
       			toastr.error("Enter solution Text");
       			return false;
       		 }
       		else if($("#svideo").val()==""){
       			toastr.error("Enter solution video");
       			return false;
       		 }
       		 
       		 
       		 else{
       		 
       		 
       		 
       		 var formData = new FormData(this);
       	//	 alert("1111");
       		$.ajax({
       			url:"AddNumericEntry.action",
       			type: "POST",
       			data:formData,
       			mimeType:"multipart/form-data",
       			contentType: false,
           	    cache: false,
       			processData:false,
       			success:function(data){
       		//		alert(data);
       				$("#table-data").html(data);
       				$("#table-info").show();
       				//toastr.success($("#success").val(), "Success");
       				
       			},
       			error:function(e){
       				//alert(e);
       				toastr.error($("#error").val(), "Error");
       			}
       		});
       		
       		 }
       		 
       		e.preventDefault();
        //   	}
         /*  	else{
           		alert("false");
           		e.preventDefault();
           		return false;
           	}*/
       	}); 
        	
        	$("#reset").click(function(){

        		CKEDITOR.instances['question'].setData("");
            	CKEDITOR.instances['stext'].setData("");

            	$("#typecheck").prop("checked",false);
            	$("#normalradio").prop("checked","checked");
            	$("#singleradio").prop("checked","checked");
            	$("#type").prop("checked","checked");
        		$("#questionId").val("");
        		$("#sectionId").val("");
        		$("#categoryId").val("");
        		$("#typeId").val("");

        		$("#title").val("");
        		$("#directions").val("");
        		$("#lesson").select2("val", "");
        		$("#test").select2("val", "");

        		

        		$("#graph").select2("val", "");
        	    $("#skill").select2("val", "");
        	    $("#difficulty").select2("val", "");
        	    
        	    
        	   
        	    $("#entry11").val("");
        	    $("#entry22").val("");


        		$("#svideo").val("");

            	
        	});
        	
        	$("#view").click(function(){
        		$.ajax({
           			url:"ViewNumericEntry.action",
           			type: "POST",
           			success:function(data){
           			
           			 $("#table-data").html(data);
           			 $("#table-info").show();
           			//toastr.info("NumericEntry Table Data", "Information");
           				
           			},
           			error:function(e){
           				alert(e);
           			}
           		});
        	});
        	
        });
</script>

<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Web Application</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />   
  
<link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" /> 
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" 	src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<%--  <script type="text/javascript" src="assets/plug-ins/calc/calc.js"></script>
<link rel="stylesheet" href="assets/plug-ins/calc/calc.css" rel="stylesheet" type="text/css"/>    --%> 
<script type="text/javascript"  src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>  
<script src="assets/js/select2.1/select2.min.js"></script>
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" /> 
<script src="assets/js/datatables/jquery.dataTables.min.js"></script> 
 
  
  <script type="text/javascript">
  $(document).ready(function(){  
	  
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
	  $('#section').on('change',function(){
			 $.getJSON('sectionBasedLessons.action',
					 {'sectionid':$(this).val()},
					 function(input){ 
						 $("#lessons option").remove();
						 $("#lessons").append(input.lessons);
						 $('#sub_lessons').select2({tags:[""],placeholder: "Select sub-lessons"});
					 });
		  });
	  $('#main_lesson_radio').attr('checked',true); 
	  $('#active_state').attr('checked',true);  
	    $('#main_lesson_radio').click(function(){
	    	 $('#sublessons_div').hide(); 
	    }); 
	    $('#save').click(function(){ 
	    	var parent_id=0;
	    	
	    	
	    	if($("#sub_lessons").select2("val")=='')
	    		{
	    		 parent_id=0;
	    		}
	    	else
	    		{
	    		parent_id=$("#sub_lessons").select2("val");
	    		}  
	     	var url = $('#videourl').val();
			var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/  
			var	reWhiteSpace = new RegExp(/^\s+$/);
	    	if($('#section').val()=='-1')
	    		{ 
	    		toastr.error("Select Section", "Error Info");
	    		return false;
	    		}
	    	else if($('#lessons').val()=='-1')
	    		{ 
	    		toastr.error("Select lesson", "Error Info");
	    		return false;
	    		}  
	    	else if($('#sub_lesson_name').val()==''||$('#sub_lesson_name').val().length==0)
	    		{ 
	    		 toastr.error("Enter Sublesson Name", "Error Info");
				 return false;
	    		}
	    	else if(reWhiteSpace.test($('#sub_lesson_name').val()))
	    		{
	    		 toastr.error("Sublesson Should not contain whitespaces", "Error Info");
				 return false;
	    		} 
	    	
	    	else
	    		{
	    		
	    		var isTrue=false;
	    		if($("#sub_lessons").select2("val")=='')
	    		{
	    		 parent_id=0; 
	    		 isTrue=true;
	    		}
	    		
	    		if($("#sub_lessons").select2("val")!='')
	    		{ 
		    		if(CKEDITOR.instances.editor1.getData() == ''|| CKEDITOR.instances.editor1.getData().length == 0)
		      		{ 
		      		toastr.error("Enter Text", "Error Info");
		      		isTrue=false;
		      		return false;
		      		}   
	  	    	  else if (!regexp.test(url)) { 
	  				toastr.error("Enter Valid URL", "Error Info");
	  				isTrue=false;
	  				return false;
	  			  } 
	  	    	 else if($("#skills_required").select2("val")=='')
		    		{ 
		    		 toastr.error("Select skills", "Error Info");
		    		 isTrue=false;
					 return false;
		    		}   
	  	    	 else
	  	    		 {
	  	    		  isTrue=true;
	  	    		 } 
	    		}  
	    		if(isTrue)
	    			{ 
	    			var ckeditorData = ""; 
		    		var dataToSend = CKEDITOR.instances.editor1.getData(); 
		    		if(dataToSend.length>1)
		    			{
		    			if (dataToSend.indexOf('&')>1) { 
			    			ckeditorData = CKEDITOR.instances.editor1.getData().replace(/\&/g,
			    					'**');
			    		} else {
			    			ckeditorData = CKEDITOR.instances.editor1.getData(); 
			    		}
			    		if (ckeditorData.indexOf("#")) {
			    			dataToSend = ckeditorData.replace(/\#/g, '@@');
			    		} else {
			    			dataToSend = ckeditorData;
			    		}   
		    			}
		    		else
		    			{
		    			dataToSend="";
		    			} 
		    		  var formData=$('#sublessons_form').serialize();  
		    		    $.ajax({
		    				url : 'saveSublessons.action?lessontext='+dataToSend+'&parent_id='+parent_id,
		    				data : formData,
		    				success : function(result) { 
		    					$('#sub_lessons_table').html(result);
		    					$('#section').val(-1);
		    			    	$('#lessons').val(-1); 
		    			    	$('#sub_lesson_name').val('');
		    			    	$('#skills_required').select2('val','');
		    			    	CKEDITOR.instances.editor1.setData('');
		    			    	$('#videourl').val(''); 
		    			    	if($('#save').hasClass('btn-primary'))
		    			    	{
		    			    		$('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");;
		    			    	} 	    			    	
		    			    	$('#free_type').attr('checked',false);
		    			    	$('#active_state').attr('checked',true);
		    			    	$('#sub_lessons').select2('val','');
		    			    	$('#sub_lesson_id').val('');
		    				},
		    				error : function(e) {
		    					alert('Error' + e);
		    				}
		    			});   
	    			}
	    		
	    		}
	    	
	    });
	    $('#reset').click(function(){
	    	$('#section').val(-1);
	    	$('#lessons').val(-1);  
	    	$('#sub_lesson_name').val('');
	    	$('#skills_required').select2('val','');
	    	CKEDITOR.instances.editor1.setData('');
	    	$('#videourl').val(''); 
	    	if($('#save').hasClass('btn-primary'))
	    	{
	    		$('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");;
	    	} 
	        document.getElementById("free_type").checked=false; 
	    	 document.getElementById("active_state").checked=true;
	    	$('#sub_lessons').select2('val','');
	    	$('#sub_lesson_id').val('');
	        $('#sub_lessons').select2({tags:[""],placeholder: "Select sub-lessons"});
	    });
	  });
  
 function editRecord(sectionid,lessonid,sublesson_name,lesson_text,video,accesstype,status,skill1,skill2,skill3,sublesson_id,parent_id,parent_name,lesson_type) 
  {     
	  //alert('Sectionid:'+sectionid+"\t lessonid:"+lessonid+"\t name:"+sublesson_name+"\t Text:"+lesson_text+"\t type:"+accesstype+"\t status:"+status); 
	 // alert('parent:'+parent_id+"\t lessonid:"+sublesson_id+"\ parent name:"+parent_name); 
	 $('#section').val(sectionid);
	 $.getJSON('sectionBasedLessons.action',
			 {'sectionid':sectionid},
			 function(input){
			     // alert(input.lessons);
				 $("#lessons option").remove();
				 $("#lessons").append(input.lessons);
				 $('#lessons').val(lessonid); 
			 }); 
	 $('#sub_lesson_name').val(sublesson_name);
	 if(parent_id==0)
		 {
		 $('#sub_lessons').val('');
		 }
	 else
		 {
		 $('#sub_lessons').val(parent_id);
		 } 
	 $("#skills_required").select2("val",[skill1,skill2,skill3]);
	 CKEDITOR.instances.editor1.setData(lesson_text); 
	 $('#videourl').val(video); 
	 if(accesstype=='FREE')
	 { 
		 document.getElementById("free_type").checked=true;
	 }
	 if(status=='ACTIVE')
	 { 
		 document.getElementById("active_state").checked=true;
	 }
	 if(status=='INACTIVE')
	 { 
		 document.getElementById("inactive_state").checked=true;
	 }  
	 $('#sub_lesson_id').val(sublesson_id); 
	 $('#save').text("Update").removeClass("btn-success").addClass("btn-primary");
	 $("#sub_lessons").select2({ 
       	 maximumSelectionSize: 1,
       	 placeholder: "Select Sublessons",
       	 allowClear: true,
       	 multiple:true,
       	    ajax: {
       	      url: "LessonHeirarchy.action?lessonid="+lessonid,   
       	      dataType: 'json',
       	      data: function (term, page) {
       	        return {
       	          q: term
       	        };
       	      },
       	      results: function (data, page) {
       	        return { results: data }; 
       	      }
       	    },
       	  initSelection : function (element, callback) {  
       		callback({id: parent_id, text: parent_name});
         }
       	});
  }  
  function deleteSubLessons()
  {
	  if ($('[name="subLessonBO.recordsToDelete"]:checked').length == 0) { 
			 toastr.error("Select atleast one Checkbox to delete", "Error Info");
			 return false;
		} 
	  else if($('[name="subLessonBO.recordsToDelete"]:checked').length > 1)
	  { 
		 toastr.error("Select only one Checkbox to delete", "Error Info");
			 return false;
	  }
	  else
		  {
		  var dt=$('#deleteForm').serialize();
		  $.ajax({
				url : "deleteSubLessons.action",
				data : dt,
				success : function(result) {   
					$('#sub_lessons_table').html(result);
					$('#section').val(-1);
			    	$('#lessons').val(-1); 
			    	$('#sub_lesson_name').val('');
			    	$('#skills_required').select2('val','');
			    	CKEDITOR.instances.editor1.setData('');
			    	$('#videourl').val(''); 
			    	$('#free_type').attr('checked',false);
			    	$('#active_state').attr('checked',true);
			    	$('#sub_lessons').select2('val','');
			    	$('#sub_lesson_id').val('');
				},
				error : function(e) {
					alert('Error' + e);
				}
			});
		  }
  }  
  </script>
  <style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
</head>
<body class="">
  <section class="vbox"> 
    <section>
      <section class="hbox stretch">
         
        <!-- /.aside -->
        
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Add Sub-Lessons</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form class="bs-example form-horizontal" id="sublessons_form">
                       
                       <input type="hidden" id="sub_lesson_id" name="sub_lesson_id"/>
                        <div class="form-group">
                     	 <label class="col-lg-2 col-lg-offset-4 control-label">Section ID</label>
                      	<div class="col-lg-5">
                        <select name="subLessonBO.sectionId"  id="section" class="form-control m-b">
                        <option value="-1">--Select--</option>
                           <s:iterator value="sections">
                           <option value="<s:property value="sectionId"/>"><s:property value="sectionName"/></option>
                           </s:iterator>
                         </select>
                        </div>
                        </div>
                        
                        
                        <div class="form-group">
                     	<label class="col-lg-2 col-lg-offset-4 control-label">Lesson</label>
                      	<div class="col-lg-5"> 
                        <select  class="form-control m-b" id="lessons" name="subLessonBO.lessonId"> 
                        </select> 
                        </div>
                        </div>                                                             
                       
                       
                        <!--  <div class="form-group" >
                        <label class="col-lg-2 col-lg-offset-4  control-label">Lesson Type</label>
                        
                         <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" name="a"  id="main_lesson_radio" value="Main Lesson">
                            <i></i>
                           From Parent
                          </label>
                        </div>
                        
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" name="a" value="Sub Lesson" id="sub_lesson_radio">
                            <i></i>
                           From Child
                          </label>
                        </div> 
                       
                        
                       </div>   -->
                       
                       <div class="form-group" id="sublessons_div">                      
						<label class="col-lg-2 col-lg-offset-4 control-label">Select Lesson</label>
						                      
                       <div class="col-lg-5">
                        <input type="hidden" id="sub_lessons"  style="width:380px" /> 
                       </div>                       
                       </div><!--Form group close-->
                       
                       <div class="form-group">
                          <label class="col-lg-2 col-lg-offset-4 control-label">Sub-Lesson Name</label>
                          <div class="col-lg-5">
                            <input type="text" class="form-control" placeholder="Enter Sub-lesson Name" name="subLessonBO.sub_lessonname" 
                            id="sub_lesson_name" data-trigger="change" maxlength="50">                            
                          </div>
                        </div> 
                       
                     
                       
                       
                       <div class="form-group">                      
						<label class="col-lg-2 col-lg-offset-4 control-label">Skills</label>                       
                       <div class="col-lg-5"> 
                          <select id="skills_required" style="width:300px"  multiple="multiple" name="subLessonBO.skills_required">
                          <s:iterator value="skills">
                          <option value="<s:property value="skill_id"/>"><s:property value="skill_name"/></option>
                          </s:iterator>
                          </select>
                       </div>                       
                       </div><!--Form group close-->
                        
                        
                       			<div class="form-group">
                          				<label class="col-lg-2 control-label">Lesson Text</label>
                          				<div class="col-lg-10">
                            				<textarea id="editor1" name="subLessonBO.lesson_text" rows="10" cols="80"> </textarea>
											<%-- <script> CKEDITOR.replace('subLessonBO.lesson_text') </script> --%>
                          				</div>
                        			</div>
                       
                       
                        <div class="form-group">
                          <label class="col-lg-3 col-lg-offset-2 control-label">Video URL</label>
                          <div class="col-lg-6">
                            <input type="text" class="form-control" placeholder="URL Link" name="subLessonBO.lesson_video" id="videourl">                            
                          </div>
                        </div>           
                        
                        
                         <div class="form-group">
                        <label class="col-lg-2  col-lg-offset-4  control-label">Type</label>
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="checkbox" name="subLessonBO.access_type" value="FREE" id="free_type">
                            <i></i>
                           Free
                          </label>
                        </div>
                        
                       
                        
                       </div><!--form group close-->     
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-4  control-label">Status</label>
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" name="subLessonBO.status" value="ACTIVE" id="active_state">
                            <i></i>
                            Active
                          </label>
                        </div> 
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" name="subLessonBO.status" value="INACTIVE" id="inactive_state">
                            <i></i>
                            In Active
                          </label>
                        </div>
                        
                       </div><!--form group close-->     
                                                      
                       
                        <div class="form-group">
                          <div class="col-lg-offset-6 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn  btn-success" id="save">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="button" class="btn  btn-danger" id="reset">Reset</button>
                          </div>     
                          
                          <!--  <div class="col-lg-offset-1 col-lg-1">
                            <button id="showCalc" type="button" class="btn  btn-warning" onclick="shw()">Calculator</button>
                          </div>   -->
                                                              
                        </div><!--form group close-->
        	  </form> 
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             <div class="row" id="sub_lessons_table">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             <form id="deleteForm">
             <section class="scrollable padder">
             
              <section class="panel panel-default">                
               
                 
                 <div class="table-responsive">
                  <table class="table table-striped m-b-none" id="sublessons_table">
                     <thead>
                     <tr>  
                        
                        <th>Lesson</th>
                        <th>Skill 1</th>
                        <th>Skill 2</th>
                        <th>Skill 3</th>
                        <th>Edit</th>  
                        <th>Delete</th>
                      </tr>
                    </thead>		

					<tbody>
						 
                    <s:iterator value="subLessons">
                    <tr> 
                    <td><s:property value="sub_lessonname"/></td>
                    <td><s:property value="skillname1"/></td>
                    <td><s:property value="skillname2"/></td>  
                    <td><s:property value="skillname3"/></td>
                      <td><button class="btn btn-default" type="button" onclick="editRecord('<s:property value="sectionId"/>','<s:property value="lessonId"/>','<s:property value="sub_lessonname"/>','<s:property value="lesson_text"/>','<s:property value="lesson_video"/>','<s:property value="access_type"/>','<s:property value="status"/>','<s:property value="skillid1"/>','<s:property value="skillid2"/>','<s:property value="skillid3"/>','<s:property value="sublesson_id"/>','<s:property value="parent_id"/>','<s:property value="parent_lesson_name"/>','<s:property value="lesson_type"/>')">Edit</button></td> 
                      <%-- <td><button class="btn btn-danger" type="button" onclick="editRecord('<s:property value="sectionId"/>','<s:property value="lessonId"/>','<s:property value="sub_lessonname"/>','<s:property value="lesson_video"/>','<s:property value="access_type"/>','<s:property value="status"/>','<s:property value="skillid1"/>','<s:property value="skillid2"/>','<s:property value="skillid3"/>','<s:property value="sublesson_id"/>','<s:property value="parent_id"/>','<s:property value="parent_lesson_name"/>','<s:property value="lesson_type"/>')">Edit</button></td> --%> 
                    <td class="chck" style="padding-top:3px"><label class="checkbox m-n i-checks"><input type="checkbox" value="<s:property value="sublesson_id"/>" name="subLessonBO.recordsToDelete"><i></i></label></td> 
                    </tr>
                    </s:iterator>   
					</tbody>
                  </table>
                </div>
                
                </section>
                 <center><a href="#" class="btn btn-del" onclick="deleteSubLessons()">Delete selected</a></center>
              </section><!--sec close for data table-->
              </form>
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div>
           
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
      </section>
    </section>
  </section>
  
 
 <!-- parsley -->
<%-- <script src="assets/js/parsley/parsley.min.js"></script>
<script src="assets/js/parsley/parsley.extend.js"></script>
<script src="assets/js/datatables/jquery.dataTables.min.js"></script>

<script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script> --%>
 <script type="text/javascript">
	CKEDITOR.replace('subLessonBO.lesson_text',{
        entities: false,
        basicEntities: false,
        entities_greek: false,
        entities_latin: false,
        htmlDecodeOutput: true,
        forceSimpleAmpersand : true,
    });  
	</script>    
 <script type="text/javascript">
	$(document).ready(function () { 
		
	    $("#sub_lessons").select2({tags:[""],placeholder: "Select Lessons"});
		$("#skills_required").select2({ maximumSelectionSize: 3 }); 
     	//$("#section").select2();
		//$("#sub_lessons").select2();
		
		  $('#lessons').on('change',function(){  
			$("#sub_lessons").select2({
				maximumSelectionSize: 1,
	       	    placeholder: "Select Sublessons",
	       	    allowClear: true,
	       	    multiple:true,
	       	    ajax: {
	       	      url: "LessonHeirarchy.action?lessonid="+$(this).val(),   
	       	      dataType: 'json',
	       	      data: function (term, page) {
	       	        return {
	       	          q: term
	       	        };
	       	      },
	       	      results: function (data, page) {  
	       	        return { results: data };
	       	        }
	       	     }
				});
		});
	 
		   
					
		var oTable = $('#sublessons_table').dataTable( {

		    "bProcessing": true, 

		    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
		    "sPaginationType": "full_numbers",
		    "aoColumns": [
		      { "mData": "columnname1" },
		      { "mData": "columnname2" },
		      { "mData": "columnname3" },
		      { "mData": "columnname4" },
		      { "mData": "columnname5" },
		      { "mData": "columnname6" }
		    ]
		  } ); 
		 /*   $.fn.calculator.hide = function(calc) {
				calc.fadeOut(500);
			 };
			 $('#calc').calculator({movable:true,resizable:true, width:160,defaultOpen:false});
			 $('#showCalc').click(function(){
					$('#calc').show();
				});  */
	 
});
 </script>
</body>
 
</html>
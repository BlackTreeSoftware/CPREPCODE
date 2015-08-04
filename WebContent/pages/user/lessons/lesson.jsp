<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



 
  <script type="text/javascript"  src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script> 
 

 
  
 
  <script src="assets/js/jquery.min.js"></script> 
  <script src="assets/js/bootstrap.js"></script>
 <%--  <link href="assets/js/select2-3.4.8/select2.css" rel="stylesheet" />
  <script src="assets/js/select2-3.4.8/select2.js"></script> --%>
  
<script>
$( document ).ready(function() {
	
	$('#body1').click(function(){
		
		$('#simplemodal-overlay,#simplemodal-container').remove();
		//$('.askdoubt').html('').html("<button class='btn btn-xs btn-default' style='padding-top: 5px; padding-bottom: 5px;' data-toggle='modal' href='#askdoubt' type='button' id='askdoubt'><i class='fa fa-question-circle text-success-dker' style='padding-right:3px; padding-top:0px; font-size:17px;'></i><span class='text askdoubt-hide'> Ask a doubt</span></button>");
		//$('.askdoubt').append('<button class="btn btn-xs btn-default" style="padding-top: 5px; padding-bottom: 5px;" data-toggle="modal" href="#askdoubt" type="button" id="askdoubt"><i class="fa fa-question-circle text-success-dker" style="padding-right:3px; padding-top:0px; font-size:17px;"></i><span class="text askdoubt-hide"> Ask a doubt</span></button>');
		
	});
	
	$('.btn-sider').click(function(e) {
		
		$('#confidencearea,#notesarea,#notes_section,.book,#ask').toggle();
		
		if($('#tog').hasClass('fa-chevron-circle-right')){
		$('#tog').removeClass('fa-chevron-circle-right').addClass('fa-chevron-circle-left');
		}
		else{
		$('#tog').removeClass('fa-chevron-circle-left').addClass('fa-chevron-circle-right');
		
		}
		
		
		$('.col-sm-95').toggleClass('col-sm-95-fuller');
		$('.col-sm-35').toggleClass('col-sm-35-fuller');
		
		
		
		
	        $('.sider').toggleClass('no-sider');
	});
	
	  $('#notes_user').hide();
	$('#note-icon1').click(function(){
		$('#notes_user').toggle();

		});
	 
	
	
	$('.note-xs').hide();
	
	
	
	
	$('#show_notes').hide();
	$(".buttonfornotes").click(function () {

			// Set the effect type
			var effect = 'slide';
		
			// Set the options for the effect type chosen
			var options = { direction:'up' };
		
			// Set the duration (default: 400 milliseconds)
			var duration = 400;
		
			$('#show_notes').toggle(effect, options, duration);
		});
		
		
		$("#notes_section").click(function () {

			// Set the effect type
			//var effect = 'slide';
		
			// Set the options for the effect type chosen
			var options = { direction:'left' };
		
			// Set the duration (default: 400 milliseconds)
			var duration = 400;
		
			$(this).toggle(effect, options, duration);
		});
		
		/* $("#send").click(function () {
		
		////alert("hai ");
		
		 
		  var formData = $('#doubt').serialize();
		  ////alert(formData);
			 $.ajax({
				type : 'POST',
				url : 'lessonaction.action',
				dataType:'json',
				data : formData,
				success :  function(data,textStatus,jqXHR) {
				////alert("Mail Sent Staus \n"+data.mailSentStatus);
				////alert(textStatus);
				////alert(data.data);
				if(data.message=="Mail Sent Successfully"){
					toastr.success("Mail Sent Successfully");
				}else if(data.message=="Doesnt Have Mentor"){
					
					toastr.error("Doesnt Have Mentor");
					}
				
					
				
					////alert("success");
					clean();
						
					
									

					},
				error : function actionFailed(data,textStatus,errorThrown) {
					toastr.error("Error");
				}

			});
		
		}); */
		
		function clean(){
			////alert("clean");
			
			$("#title").val("");
			$("#desc").val("");
		}
		
		
		

		
		
	
	
});


</script>
  
<style>

.amPmCheckbox input[type="checkbox"] {
											display: none;
										 }
										 
   									 
										
	.amPmCheckbox input[type="checkbox"] + label {    
												background: url(assets/images/icons/bookmarkicon-hover32.png) no-repeat;		
												height:32px;
												width:32px;
												padding:px;
												cursor:pointer;
										       }
										
	.amPmCheckbox input[type="checkbox"]:checked + label  {
															background: url(assets/images/icons/bookmarkicon32.png) no-repeat;		
															height:32px;
															width:32px;
															cursor:pointer;
														 }
</style>

</head>
<body>

<div class="row">
  <%-- <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">  --%>
                       
                       
                       <!---Notes Div for xs device--->
                             
                   
                 <!-------------------------Side bar for XS devices--------------------------------->
               <form  id="confidenceFrorm1"  method="post">
                <span class="col-sm-4 col-xs-4 text-center" style = "display:none">Lessons<b id="realtime" >00:00:00</b></span>
                     <input type="hidden" id="userId" name="lessonsmasterBO.user_id" value="${sessionScope.user.user_id}"/>
                     <!-- <input type="hidden" id="sublesson_id" name="lessonsmasterBO.sublesson_id" value=""/>  -->
              			<div class="row  head-bar-xs" >
                          <div class="col-xs-12">
                           <div class="askdoubt col-xs-3 ask-btn-xs" style="padding-top:20px; padding-bottom:20px; padding-left:0px">
                           		<button class="btn btn-xs btn-default " style="padding-top:5px; padding-bottom:5px;" data-toggle="modal" href="#askdoubt" type="button" id="askdoubt">
                                  <i class="fa fa-question-circle text-success-dker" style="padding-right:3px; padding-top:0px; font-size:17px;"></i>
                                  <span class="text askdoubt-hide"> Ask a doubt</span>                                 
                                </button>
                           </div>
                        
                          <s:if test="lessonsmasterBO.lesson_confidence=='LOW'">
                           <div class="col-xs-6" style="padding-top:20px; padding-bottom:20px;">
                              <div class="m-b-sm">                    		
                              <div class="btn-group btn-group-justified"> 
                             <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-success btn-test ">Low</a> 
                             <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info btn-test"> Medium</a> 
                             <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info  btn-test"> High</a>
                              </div>                                      
                           	  </div>
                           </div>
                           </s:if>
                           <s:elseif test="lessonsmasterBO.lesson_confidence=='MEDIUM'">
                            <div class="col-xs-6" style="padding-top:20px; padding-bottom:20px;">
                              <div class="m-b-sm">                    		
                              <div class="btn-group btn-group-justified"> 
                               <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-info btn-test ">Low</a> 
                               <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-success btn-test"> Medium</a> 
                               <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info  btn-test"> High</a>
                              </div>                                      
                           	  </div>
                           </div>
                           </s:elseif>
                            <s:elseif test="lessonsmasterBO.lesson_confidence=='HIGH'">
                            <div class="col-xs-6" style="padding-top:20px; padding-bottom:20px;">
                              <div class="m-b-sm">                    		
                              <div class="btn-group btn-group-justified"> 
                              <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-info btn-test ">Low</a> 
                              <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info btn-test"> Medium</a> 
                              <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-success  btn-test"> High</a>
                              </div>                                      
                           	  </div>
                           </div>
                           </s:elseif>
                            <s:else>
                            <div class="col-xs-6" style="padding-top:20px; padding-bottom:20px;">
                              <div class="m-b-sm">                    		
                              <div class="btn-group btn-group-justified"> 
                               <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-info btn-test ">Low</a> 
                               <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info btn-test"> Medium</a> 
                               <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info  btn-test"> High</a>
                              </div>                                      
                           	  </div>
                           </div>
                           </s:else>
                           
                          
                           
                           <!---Notes button for xs device--->
                           <div class=" col-xs-1" style="padding-top:15px;">
                           	
                             <center> <a href="#" class="text-primary-dker buttonfornotes" style="" id="note-icon1">
                             <img src="assets/images/icons/note-icon.png"  /></a></center>
                            
                              <!--<a href="#" class="text-primary-dker buttonfornotes visible-xs" style="padding-top:18px;" id="note-icon">
                              <i class="fa fa-2x fa-pencil-square-o">
                              </i></a>-->
                                                        
                           </div>
                           <!---close Notes button for xs device--->
                           
                           
                         
                         <s:if test="lessonsmasterBO.lesson_bookmark=='MARKED'">
                          <div class=" col-xs-1 col-xs-offset-1 ">
                                                                 
                         	    <div class="book-xs pull-right">
                                 <div class="amPmCheckbox">
                                    <input type="checkbox" name="data[Child][remember_me]" checked="checked" class="checkboxLabel main_street_input" id="am_2" value="1" onclick="saveBookmark('UNMARKED');" />
                                    <label for="am_2"> </label>
                                    </div>
                                </div>
                           </div> 
                         </s:if>   
                         
                         
                         <s:else>
                          <div class=" col-xs-1 col-xs-offset-1 ">
                                                                 
                         	    <div class="book-xs pull-right">
                                 <div class="amPmCheckbox">
                                    <input type="checkbox" name="data[Child][remember_me]" class="checkboxLabel main_street_input" id="am_2" value="1" onclick="saveBookmark('MARKED');" />
                                    <label for="am_2"> </label>
                                    </div>
                                </div>
                           </div> 
                         </s:else>                       
                         </div> 
                        </div>
                        </form>
                        <!---------------------->

					 <div class="row" id="notes_user">
                              <div class="col-xs-12 note-xs visible-xs" id="show_notes" style="width:100%">
                                    
                                    <section class="scrollable bg-light lter xs-paper"  style="width:100%">
                                     <textarea type="text" class="form-control form-controlss scrollable" placeholder="Type your note here" id="lession_note_phn" name="lessonsmasterBO.lesson_note_name" onchange="saveNotesPhone();"><s:text name="lessonsmasterBO.lesson_note_name" /></textarea>
                                    </section>
                              
                              </div>
                    </div>
                        
              <!---Close Notes Div for xs device--->
              
              <div class="row lesson-sidr">
              
              <div class="col-sm-95  headr-padding" id="sideTab">
             
              <!------------------------------------------------------->
              <div class="panel panel-white">
              
              <div class="panel-heading ">
              
              
              <div class="row"> 
              <span class="col-sm-4 col-xs-4  link-pading-xs-lft">
              <a href="javascript:navigationControl('PREVIOUS')" id="previous"><i class="fa fa-step-backward"></i>&nbsp;&nbsp; Previous Lesson </a></span>
              <span class="col-sm-4 col-xs-4 col-xs-offset-1 link-pading-xs-lft">Lessons</span>
            
              
              <span class="col-sm-3 col-xs-3 link-pading-xs-rht text-right"><a href="javascript:navigationControl('NEXT')" id="next"> Next Lesson &nbsp;&nbsp;<i class="fa fa-step-forward"></i></a> </span>
              </div>
             
              
              </div>
              <form id="myform" method="POST">
              <div class="panel-body">                            
              
              <div class="row lesson-content">
              <div class="col-sm-12 ">
              <table width="100%">
              <tr><td>
              <h1 class="text-primary"><s:property value="lessonsmasterBO.sublesson_name" />
              </td><td style="float:right">
           <!-- <div class="col-lg-6 ">
              <div class="col-sm-6 col-lg-6  " style="line-height:2.6">
              Lessons
              </div>
                 <div class="col-lg-4 col-sm-4" id="data_array">
												<input type="hidden" id="v10" class="padd"
													style="width: 130px" />
											</div>              
											
              </div> -->
              </td></tr>  
              </table>
              
              <%-- <s:property value="%{#session.user.user_id}"/> --%> </h1>
             
              <p>  <s:text name="lessonsmasterBO.lesson_text" /></p> 
              <%-- <input type="hidden" id="userId" name="lessonsmasterBO.user_id" value="${sessionScope.user.user_id}"/>
              <input type="hidden" id="sublesson_id" name="lessonsmasterBO.sublesson_id" value="5"/>  --%> 
              </div>
              
                  
                
              </div><!--Internal row close-->
               <hr>
              <div class="row"> 
              <span class="col-sm-4 col-xs-4  link-pading-xs-lft">
              <a href="javascript:navigationControl('PREVIOUS')" id="previous"><i class="fa fa-step-backward"></i>&nbsp;&nbsp; Previous Lesson </a></span>
              <span class="col-sm-4 col-xs-4 col-xs-offset-1 link-pading-xs-lft"><button type="button" class="btn btn-default" onclick="getTime();">Start Practice</button></span>
            
              
              <span class="col-sm-3 col-xs-3 link-pading-xs-rht text-right"><a href="javascript:navigationControl('NEXT')" id="next"> Next Lesson &nbsp;&nbsp;<i class="fa fa-step-forward"></i></a> </span>
              </div>
             
              
              
              
              </div><!--panel body close-->
              </form>
              </div><!--Panel default close-->
              </div>
              
              <div class="col-sm-35 sider ">
              
              <div class="row sider-header">
              
              
              <div class="col-sm-2" style="">
              <a href="#" class="btn-sider"> <i class="fa fa-chevron-circle-right btn-chevron" style="color:#1ccacc;" id="tog"></i></a>
              </div>
              
              <div class="col-sm-7" style="margin-top:10px; margin-bottom:10px;">
                              <!--<center> <a class="btn btn-primary"> Ask a doubt</a> </center>-->
                            <center>  <button class="btn btn-default " id="ask" data-toggle="modal" href="#askdoubt">
                                  <i class="fa fa-question-circle text-success-dker" style="padding-right:3px; font-size:17px;" ></i>
                                  <span class="text"> Ask a doubt</span>                                 
                                </button> </center>                                
              </div>
              
              <s:if test="lessonsmasterBO.lesson_bookmark=='MARKED'">
                  <div class="col-sm-1" style="margin-left:0px;">
                  <span>
                   <div class="book">
                      <div class="amPmCheckbox">
                                    <input type="checkbox" name="data[Child][remember_me]" checked="checked" class="checkboxLabel main_street_input" id="am_3" value="1"  onclick="saveBookmark('UNMARKED');"/>
                                    <label for="am_3"> </label>
                                    </div>
                        </div>
                   </span>                                                        
                   
              </div>
              </s:if>
              <s:else>
               <div class="col-sm-1" style="margin-left:0px;">
                  <span>
                   <div class="book">
                      <div class="amPmCheckbox">
                                    <input type="checkbox" name="data[Child][remember_me]"  class="checkboxLabel main_street_input" id="am_3" value="1"  onclick="saveBookmark('MARKED');"/>
                                    <label for="am_3"> </label>
                                    </div>
                        </div>
                   </span>                                                        
                   
              </div>
              </s:else>
              <%-- <s:elseif test="%{#lessonmasterBO.lesson_bookmark=='UNMARKED'}">

              </s:elseif>
 --%>              
             
                     
            <!--  <div class="col-sm-2" style="margin-top:-15px"> 
               <a data-toggle="modal" class="bug" href="#myModal"> <i class="fa fa-bug"></i> </a>
               </div>          -->
			   
            
              
              </div><!--row one close-->
              
              
              <hr class="bookmark">
              <form  id="confidenceFrorm"  method="post">
               <input type="hidden" id="userId" name="lessonsmasterBO.user_id" value="${sessionScope.user.user_id}"/> 
               <!-- <input type="hidden" id="sublesson_id" name="lessonsmasterBO.sublesson_id" value=""/>  --> 
              
               <s:if test="lessonsmasterBO.lesson_confidence=='LOW'">
                <div class="row" style="margin-top:20px; margin-bottom:10px;" id="confidencearea">
               
                    <div class="col-sm-12 col-sm-offset-0 col-lg-12 col-lg-offset-0">
                    <div class="m-b-sm">
                    		<label class="control-label text-center"> Confidence Level</label>
                          <div class="btn-group btn-group-justified"> 
                         <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-success btn-test ">Low</a> 
                           <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info btn-test"> Medium</a> 
                           <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info  btn-test"> High</a>

                          </div>
                          
                    </div>
                    </div>
                
              </div>
               </s:if>
               <s:elseif test="lessonsmasterBO.lesson_confidence=='MEDIUM'">
               <div class="row" style="margin-top:20px; margin-bottom:10px;" id="confidencearea">
              
                    <div class="col-sm-12 col-sm-offset-0 col-lg-12 col-lg-offset-0">
                    <div class="m-b-sm">
                    		<label class="control-label text-center"> Confidence Level</label>
                          <div class="btn-group btn-group-justified"> 
                         <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-info btn-test ">Low</a> 
                           <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-success btn-test "> Medium</a> 
                           <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info  btn-test"> High</a>
                          </div>
                          
                    </div>
                    </div>
                </div>
          
               </s:elseif>
               <s:elseif test="lessonsmasterBO.lesson_confidence=='HIGH'">
                <div class="row" style="margin-top:20px; margin-bottom:10px;" id="confidencearea">
              
                    <div class="col-sm-12 col-sm-offset-0 col-lg-12 col-lg-offset-0">
                    <div class="m-b-sm">
                    		<label class="control-label text-center"> Confidence Level</label>
                          <div class="btn-group btn-group-justified"> 
                         <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-info btn-test ">Low</a> 
                           <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info btn-test"> Medium</a> 
                           <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-success  btn-test"> High</a>
                          </div>
                          
                    </div>
                    </div>
                
            </div>
               </s:elseif>
               <s:else>
                <div class="row" style="margin-top:20px; margin-bottom:10px;" id="confidencearea">
              
                    <div class="col-sm-12 col-sm-offset-0 col-lg-12 col-lg-offset-0">
                    <div class="m-b-sm">
                    		<label class="control-label text-center"> Confidence Level</label>
                          <div class="btn-group btn-group-justified"> 
                         <a href="javascript:saveConfidence('LOW')"  id ="lowId" name="lessonsmasterBO.lesson_confidence" class="btn  btn-info btn-test ">Low</a> 
                           <a href="#" id="mediumId" onclick="saveConfidence('MEDIUM');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info btn-test"> Medium</a> 
                           <a href="#" id="highId" onclick="saveConfidence('HIGH');"  name="lessonsmasterBO.lesson_confidence"  class="btn  btn-info  btn-test"> High</a>
                          </div>
                          
                    </div>
                    </div>
                
             </div>
               </s:else>
             </form>
              
             <br>



              <div class="row" id="notesarea">
              <div class="col-sm-12 col-lg-12 col-lg-offset-0">
              							
                    <section class="scrollable bg-light lter paper"  id="notes_section">

                      <textarea type="text" class="form-control form-controlss scrollable" placeholder="Type your note here" id="lession_note" name="lessonsmasterBO.lesson_note_name" onchange="saveNotes();"><s:text name="lessonsmasterBO.lesson_note_name" /></textarea>
                    </section>
                                                          							                                                                                     
              
              </div>
              </div>
              
                                                                                         
              </div>
              
            </div>  
          <%--   </section>
          </section>
      
       
      </section>
       --%>
       <!--------------------Modal for Typos----------------------------->
  	
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      <h4 class="modal-title">Lesson Actions</h4>
                    </div>
                    <div class="modal-body">
                      
                      
                      <div class="row">
                      
                      <div class="col-lg-10 col-lg-offset-1">
                          <form class="form-horizontal" method="get">
                         
                         <div class="form-group">
                                  <label class="col-sm-2 control-label">Select</label>
                                  <div class="col-sm-10">
                                    <select name="account" class="form-control m-b">
                                      <option>I found bug</option>
                                      <option>I found typo</option>
                                      <option>I have doubt</option>
                                      <option>option 4</option>
                                    </select>                        
                                  </div>
                         </div>
                      
                          <div class="form-group">
                                  <label class="col-sm-3 control-label">Description</label>
                                  <div class="col-sm-7">
                                    <textarea cols="50" rows="5" style="max-width:300px;"></textarea>           
                                  </div>
                          </div>
            
             </form>
             
             
          </div>
          
          </div>
          
          
        </div>
        <div class="modal-footer">
         <form class="form-horizontal" method="get">
         <div class="form-group">
                          <div class="col-xs-offset-4 col-xs-2">
                            <button type="submit" class="btn  btn-success">Submit</button>
                          </div>
                        <div class="col-xs-offset-1 col-xs-2">
                           <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                          </div>                                                
                        </div><!--form group close-->
         
           </form>
             
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
 
  
  <!------------------------------------------------->

 <!--------------------Modal for ask a doubt----------------------------->
   <div class="modal fade" id="askdoubt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:10000">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      <h4 class="modal-title text-center">Ask a doubt</h4>
                    </div>
                    <div class="modal-body">
                      
                      
                      <div class="row" style="padding-top:10px;">
                      
                      <div class="col-sm-8 col-sm-offset-1">
                          <form class="form-horizontal" method="get" id="doubt">
                         <input type="hidden"  name="lessonBO.user_id" value="${sessionScope.user.user_id}"/>
                          <input type="hidden" id="type" name="lessonBO.action_type" value="DOUBT"/>
                         <div class="form-group">
                                  <label class="col-sm-2 control-label">Title</label>
                                  <div class="col-sm-8 col-sm-offset-1">
                                   <input type="text" class="form-control" name="lessonBO.action_title" id="title" style="width:100%"/>           
                                  </div>
                         </div>
                      
                          <div class="form-group">
                                  <label class="col-sm-3 control-label">Description</label>
                                  <div class="col-sm-7">
                                    <textarea cols="70" rows="5" class="form-control" style="width:150%" name="lessonBO.action_desc" id="desc"></textarea>           
                                  </div>
                          </div>
            
                       
             
          
        <div class="modal-footer">
        
         <div class="form-group">
                          <div class="col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn  btn-success" id="send" data-dismiss="modal" onclick="sendMail();">Submit</button>
                          </div>
                        <div class="col-xs-offset-1 col-xs-2">
                           <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                          </div>                                                
                        </div><!--form group close-->
         
           </div>
           </form>
           </div>
           </div>
             
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
  
 
 <script src="assets/js/timer/timernew.js" type="text/javascript"></script> 
    
  	
		
   
</body>
</html>
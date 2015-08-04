<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Web Application</title>  
   <style>
.chck {
	width: 40px;
}

.text_center {
	text-align: center
}
</style>
 <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
<script src="assets/js/jquery.min.js"></script>
    <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<script src="assets/js/common.js" type="text/javascript"></script>
  <script type="text/javascript">
  
   
  
  $(document).ready(function(){ 
	  $.fn.toasterMessages();	  
	  $.fn.selectTotalCheckboxes();
		    
		 
	 
	  var oTable = $('#table').dataTable( {

		    "bProcessing": true,		   

		    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
		    "sPaginationType": "full_numbers",
		    "aoColumns": [
		      { "mData": "columnname1" },
		      { "mData": "columnname2" },
		      { "mData": "columnname3" },
		      { "mData": "columnname4" },
		      { "mData": "columnname5" },
		      { "mData": "columnname6" },
		      { "mData": "columnname7" },
		      { "mData": "columnname8" },
		      { "mData": "columnname9" },
		      { "mData": "columnname10" }
		      
		    ]
		  } );

	   $('#form').submit(function(){
		  // action="obtain_EditCriticalReasoning"
		  $('#form').attr('action','obtain_EditCriticalReasoning.action?questionId='+$('#questionId').val());
		  $('#form').submit();
		   
	   });
	  
  });
  function deleteTabledata() {
	  var checked = $("input[name=deleteIds]:checked").length;
	 // alert(checked);
	  
	  if(checked==0)
		  {
		 
		  toastr.error("please select atleast single Question to Delete");
		  }
	  else{

		var questionIds_Array = new Array();
		$('input:checkbox[name=deleteIds]:checked').each(function() {
			var questionId = "#question" + $(this).attr("id");
			questionIds_Array.push($(questionId).html());
		});

		$.ajax({
			type : 'POST',
			url : 'deleteQuestions.action',
			data : 'deleteIds=' + questionIds_Array.toString(),
			success : function(result) {

				//alert("success");
				$('#table_data').html(result);
				//$("#error").fadeIn();
				//$('#error').html("Selected Lessons Deleted Successfully");
				//$("#error").css("color", "green");
				//setTimeout(function() {
					//$('#error').fadeOut(), $("#error").fadeOut("slow");
			//	}, 5000);
				
				toastr.success("Selected Lessons Deleted Successfully"); 
			},
			error : function(e) {
				//alert("outer error");
				//$("#error").fadeIn();
				//$('#error').html(
				//		"OOps! Selected lessons not Deleted please Try again");
				//$("#error").css("color", "red");
			//	setTimeout(function() {
			//		$('#error').fadeOut(), $("#error").fadeOut("slow");
				//}, 5000);
				toastr.error("OOps! Selected lessons not Deleted please Try again");
			}
		});
	  }
  }
		
	 function editQuestion(questionId)
	{
		//alert(questionId);
		 $( "#questionId").val( questionId );
	}  
  
  </script>
  
</head>
<body class="">
   
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12" style="padding-top:10px;">
             
               <a class="btn btn-default" href="obtain_CriticalReasoning.action" class="auto">
                                               
                                                <span>Add New</span>
                                              </a>
                                              <br><br>
           <%--   <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Critical Reasoning Questions</span>  </div>
             <div class="panel-body">
             
             
           <!--   <div class="row"> -->
             <div class="col-lg-12" > --%>
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">  
               <header class="panel-heading">
                                 Critical Reasoning Questions 
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header>      
            <input type="hidden" name="questionId" id="questionId" value="%{questionId}"/>             
           
                 <s:form id="form" name="form"  theme="simple">
                
               <div class="table-responsive" id="table_data">
                
									<table class="table table-striped m-b-none" id="table">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Section Name</th>
												<th>Category Name</th>
												<th>Question Type</th>
												<th>Question Title</th>
												<!-- 	<th>Directions</th> -->
												<th>Skills</th>
												<th>Difficulty Level</th>
												<th style="display: none">QuestionId</th>
												<!-- 	<th>Question</th>
												<th>Solution Text</th>
												<th>Solution Video</th>
												<th>Access Type</th>
												<th>Referral</th>
												<th>Answer</th>
												<th>Choice1</th>
												<th>Choice2</th>
												<th>Choice3</th>
												<th>Choice4</th>
												<th>Choice5</th> -->
												 <th class="text_center">Edit</th>
												<th class="chck text_center" style="padding-bottom: -2px">
													<label class="checkbox m-n i-checks"><input
														type="checkbox" id="maincheckbox"><i></i></label>
												</th>


											</tr>
										</thead>

										<tbody>
											<s:iterator value="list" status="row">
												<tr>

													<td><s:property value="#row.index+1" /></td>
													<td><s:property value="sectionName" /></td>
													<td><s:property value="categoryName" /></td>
													<td><s:property value="typeName" /></td>
													<td><s:property value="questionTititle" /></td>
													<%-- <td><s:property value="directions" /></td> --%>
													<td><s:property value="skillNames" /></td>
													<td><s:property value="difficultyName" /></td>
													<td style="display: none"
														id="question<s:property value="#row.index+1"/>"><s:property
															value="questionId" /></td>
													<%-- <td><s:property value="question" /></td>
													<td><s:property value="solutionText" /></td>
													<td><s:property value="solutionVideo" /></td>
													<td><s:property value="accessType" /></td>
													<td><s:property value="referralType" /></td>
													<td><s:property value="answer" /></td>
													<td><s:property value="choices[0]" /></td>
													<td><s:property value="choices[1]" /></td>
													<td><s:property value="choices[2]" /></td>
													<td><s:property value="choices[3]" /></td>
													<td><s:property value="choices[4]" /></td>
													
 --%>
                                                      <td align="center"><button type="submit" class="btn btn-sm btn-default" id="Edit" onclick="editQuestion('<s:property
															value="questionId" />')">EDIT</button>
                                                <%--     <s:url id="edit" action="obtain_CriticalReasoning" >
                                                    <s:param name="questionId" value="%{questionId}"/>
                                                    
                                                    </s:url> --%>
                                                      </td>
										
													<td class="chck" style="padding-top: 3px"><label
														class="checkbox m-n i-checks"><input
															type="checkbox" name="deleteIds"
															id="<s:property value="#row.index+1"/>" /><i></i></label></td>

												</tr>
											</s:iterator>


										</tbody>
									</table>
								</div>
								</s:form>
                
                </section>
                 <center><a href="javascript:deleteTabledata()" class="btn btn-del">Delete
										selected</a></center>
              </section><!--sec close for data table-->
              <span id="error"></span>
             <!--  <hr>
                <a href="obtain_CriticalReasoning.action" class="auto" style="color:blue">Add New Critical Resoning</a>    -->   
             </div><!--col for DataTable close-->
             
             <!-- </div>Inner row2 close -->
             
             
             
             
             
          <!--    </div>panel body close             
             </div>panel head close
             
             </div>div close
             </div>row start main End -->
        
            </section>
          </section>
          
        </section> 

  
  
  
 <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
	<script src="assets/js/datatables/demo.js"></script>
   
</body>
</html>
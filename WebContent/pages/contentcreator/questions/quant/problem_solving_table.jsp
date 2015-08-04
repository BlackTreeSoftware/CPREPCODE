<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
<!-- <link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" />  -->

 <%--<script type="text/javascript" 	src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script type="text/javascript"  src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script> --%>
<!-- <link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" /> -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>  
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" /> 
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/> 
<%-- <script src="assets/js/select2.1/select2.min.js"></script> --%>
 <!-- parsley --> 
<script src="assets/js/datatables/jquery.dataTables.min.js"></script> 
<script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript">
        $(document).ready(function() {  
        	
        	
        	$('#_add,#_addbreak,#_delete').show();
        	$('#_add,#_edit').click(function(){
        		
        		$('#_add,#_delete,#_addbreak').hide();
        	});
			
        	
         var oTable = $('#sample').dataTable( { 
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
 		      { "mData": "columnname10" }, 
 		      { "mData": "columnname11" },
 		    ]
 		  } ); 
         
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
        });
        
        function addRecord(){ 
        	$.ajax({
       			url:"Manage-ProblemSolving-Questions.action?sectionId=1",
       			type: "POST" ,
       			success:function(data){  
       			    $("#operations_row").hide();
       				$("#table-data").html(data); 
       			},
       			error:function(e){
       				alert(e);
       			}  
       		});
        }
        function editRecord(questionid)
        { 
        	$.ajax({
       			url:"Manage-ProblemSolving-Questions.action?questionid="+questionid+"&sectionId=1",
       			type: "POST" ,
       			success:function(data){  
       				 $("#table-data").html(data);  
       				 $('#submit').text("Update").removeClass("btn-success").addClass("btn-primary");
       				 $("#operations_row").hide();
       			},
       			error:function(e){
       				alert(e);
       			}  
       		});
        	//alert('Add Record:'+questionid);
        }
        
        function deleteRecord()
        {
        	
        	if ($('[name="recordsToDelete"]:checked').length == 0) {  
   			//alert("Select atleast one Checkbox to delete");
   			toastr.error('Select atleast one Checkbox to delete','Error Info');
   			return false;
   		   } 
        	else 
        		{
        		var formData=$('#problem_solving_table').serialize(); 
            	$.ajax({
           			url:"deleteProblemSolvingQuestion.action", 
           			data: formData, 
           			success:function(result){ 
           				//alert(result); 
           			    $("#table-data").html(result); 
           			},
           			error:function(e){
           				alert('error:'+e);
           			}  
           		}); 
        		}  
        }

</script>
</head>
<body>
<input type="hidden" id="success" name="" value="${successMsg}" />
<input type="hidden" id="error" name="" value="${errorMsg}" />    
<section id="content" >
          <section class="vbox" >
            <section class="scrollable wrapper">
               <a href="#" class="btn  btn-default" onclick="addRecord()" id="_add">Add New</a><span  id="_addbreak"><br><br></span>
                <section class="panel panel-default" style="margin-bottom:0px;">
                
                <header class="panel-heading">
                  <span class="panel-title"> Problem Solving Questions</span> 
                  <i class="fa fa-info-sign text-muted" data-toggle="	tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                </header>
              
                
               <div id="table-data">
               <div class="table-responsive" >
                <form id="problem_solving_table">
                  <table class="table table-striped b-t b-light" id="sample">
                    <thead>
                      <tr>
                        <th ><label class="checkbox m-n i-checks"><input type="checkbox" ><i></i></label></th>
                        <th >#</th>
                        <th >Test</th>
                        <th >Section</th>
                        <th >Category</th>
                        <th >Type</th>   
                        <th >Question Title</th>
                        <th >Access</th>
                        <th >Referral Status</th>
                        <th >Status</th>
                        <th ></th>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="questionsList" status="status" id="queslist">  
                    
                     <tr>
                    <td><label class="checkbox m-n i-checks"><input type="checkbox" name="recordsToDelete" value="<s:property value="question_id"/>"><i></i></label></td>
                    <td><s:property value="#status.index+1"/></td>
                    <td><s:property value="test_name"/></td>
                    <td><s:property value="section_name"/></td>
                    <td><s:property value="category_name"/></td> 
                    <td><s:property value="question_type"/></td>  
                    <td><s:property value="question_title"/></td>  
                    <s:if test="#queslist.refferal == 'YES'">
                      <td></td> 
                      <td>Referral</td> 
                    </s:if>
                    <s:else>
                      <td><s:property value="access_type"/></td>
                      <td></td>
                    </s:else>
                  
                    <td><s:property value="status"/></td> 
                    <td><a href="#" class="btn  btn-default" onclick="editRecord(<s:property value="question_id"/>)" id="_edit">Edit</a> </td>  
                    </tr>
                    </s:iterator> 
                    
                    </tbody>
                  </table>
                  
                  </form>
                </div>
               
          </div>
         
              </section>
               <br>
                   <center><a href="#" class="btn btn-del" onclick="deleteRecord()" id="_delete">Delete Selected Records</a></center>
            </section>
            
          </section>
         
        </section> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
 <link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" /> 
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" 	src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script type="text/javascript"  src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>


<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
 <link rel="stylesheet" href="assets/css/custom.css" type="text/css" /> 
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />




<script type="text/javascript">
        $(document).ready(function() { 
         

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
 		      { "mData": "columnname10" }
 		      
 		    ]
 		  } );
        	
        
        	$('#add,#delete').show();
        	
			$('#add,#edit').click(function(){
        		
        		$('#add,#delete').hide();
        	});
     	
         $("#delete").click(function(e){
     		var var2 = $('input:checkbox:checked').length;
     		//alert(var2);
     		if(var2>0){
     		var form=$("#form1");
     		
     		 $.ajax({
     		    
     		      type: 'POST',
     		      url:"DeleteNumericEntry.action",
     		      dataType: 'html',
     		      data: form.serialize(),
     		      success: function(page) {
     		       // alert(page);
     		       //toastr.success("Questions Deleted Successfully!", "Success");
     		       $("#table-data").html(page);
     		      
     		      
     		      
     		      },
	     		  error:function(e){
	     				//alert("Error occured while process the request"+e);
	     			 toastr.error("Questions Deletion Failed !", "Error");
	     				
	     			}
     		    
     		    
     		    });
     		  
     		return false;
     		}
     		else{
     			alert("Please select the record to delete!");
     			return false;
     		}
     		 e.preventDefault();
     	});
         
        $("#add").click(function(e){
        	//alert("insert");
        	insert(); //calling insert function
        	e.preventDefault();
        });
        
        
         
        });
        
        function insert(){
        	$.ajax({
       			url:"NumericEntry.action?sectionId=1",
       			type: "POST",
       			success:function(data){
       			
       			 $("#table-data").html(data);
       			 $("#table-info").hide();
       				
       			},
       			error:function(e){
       				alert(e);
       			}
       		});
        }
        
        function update(val){
        	//alert(val);
        	$('#add,#delete').hide();
        	$.ajax({
       			url:"UpdateNumericEntry.action",
       			data:"deleteRecords="+val,
       			type: "POST",
       			success:function(data){
       			
       			 $("#table-data").html(data);
       			 $("#table-info").hide();
       			$('#save').text("Update").removeClass("btn-success").addClass("btn-primary");
       				
       			},
       			error:function(e){
       				alert(e);
       			}
       		});
        }
        
        

</script>
</head>
<body id="body1">


<section id="content">
          <section class="vbox">
            <section class="scrollable wrapper">
                <a href="#" class="btn  btn-default"  id="add">Add New</a><span  id="_addbreak"><br><br></span>
                <section class="panel panel-default">
                <header class="panel-heading">
                  <span class="panel-title"> Numeric Entry Question</span> 
                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                </header>
               
               <div id="table-data"> 
              
               <div class="table-responsive">
               <form id="form1" method="post" action="" >
                  <table class="table table-striped b-t b-light" id="sample">
                    <thead>
                      <tr>
                        <th ><label class="checkbox m-n i-checks"><input type="checkbox"><i></i></label></th>
                        <th >#</th>
                        <th >Test</th>
                        <th >Section</th>
                        <th >Category</th>
                        <th >Type</th>
                        
                        <th >Question Title</th>
                        <th >Access</th>
                        <th >Status</th>
                        <th ></th>
                        
                      </tr>
                    </thead>
                    <tbody>
                    
                     <s:iterator value="#request.data" status="status">
                     
                    <tr>
                    <td><label class="checkbox m-n i-checks"><input type="checkbox" name="deleteRecords" value="<s:property value="question_id"/>"><i></i></label></td>
                    <td><s:property value="#status.index+1"/></td>
                    <td><s:property value="test_name"/></td>
                    <td><s:property value="section_name"/></td>
                    <td><s:property value="category_name"/></td>
                    <td><s:property value="question_type"/></td>
                    
                    <td><s:property value="question_title"/></td>
                    <td><s:property value="access_type"/></td>
                    <td><s:property value="status"/></td>
                    <td><a href="javascript:update('<s:property value="question_id"/>')" class="btn btn-default" id="edit">Edit</a> </td>
                   
                    </tr>
                               
                    </s:iterator> 
                    
                    </tbody>
                  </table>
                  </form>
                </div>
                </div>

              </section>
              <br>
                   <center><a href="#" class="btn btn-del"  id="delete">Delete Selected Records</a></center>
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav">Deleted</a>
        </section>

<script src="assets/js/select2.1/select2.min.js"></script>
 <!-- parsley -->
<script src="assets/js/parsley/parsley.min.js"></script>
<script src="assets/js/parsley/parsley.extend.js"></script>
<script src="assets/js/datatables/jquery.dataTables.min.js"></script>

<script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>


 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />

<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
<script src="assets/js/jquery.min.js"></script>
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
 
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
	  

	  var oTable = $('#table').dataTable( {

		    "bProcessing": true,

		   

		    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
		    "sPaginationType": "full_numbers",
		    "aoColumns": [
		      { "mData": "columnname1" },
		      { "mData": "columnname2" },
		      { "mData": "columnname3" },
		      { "mData": "columnname4" },
		      { "mData": "columnname5" }
		    ]
		  } );
	  
	  
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=diff_id]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=diff_id]').each(function () {
	    			//alert("calling");
	    			//alert($(this).attr("id"));
	    			  $(this).prop('checked',false);
	    	});
	    	}
	        
	    });
	    

	 // alert("dsf");
	  $('#save').click(function(){
		  //alert("hai save");
		  var name=$('#name').val();
		  var rating=$('#rating').val();
		  //alert(name+"   "+rating);
		  if(name==''){
			  toastr.error("Enter Difficulty");
			  return false;
		  }else if(rating==0){
			  toastr.error("Select Rating");
			  return false;
		  }
		  else{
		  
		  var formData = $('#form1').serialize();
		  $.ajax({
			  type : 'POST',
			  url  : 'saveDifficulty.action',			
			  data : formData,
				
				 success:function(data){ 
						//alert("Inserted successfully");
					 $('#table_data').html(data);
					 $('#name').val("");
		    		 $('#id').val(0);
		    		 $('#rating').val(0);
		    		 $('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");
		    		 
					 /* $("#error").fadeIn();
		    		 $('#error').html("Difficulty Added Successfully");
		    		 $("#error").css("color", "green");
		    		
		    		 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 5000); */
		    		 
					},
					error:function(e){
						//alert("Error occured while process the request"+e);
					}
			 }); 
	  }
	 
  });
	  
	  $('#delete').click(function(){
		  if($('[name="diff_id"]:checked').length == 0) {
				// alert($('input:checkbox:checked').length);
				 if($('input:checkbox:checked').length==1);
				 toastr.warning("No Difficulty to Delete");
				 return false;
			 }else{ 
		  var var2 = $('input:checkbox:checked').length;
		 if(var2<=0){
			 toastr.warning("Please select the Difficulty to Delete");
			 return false;
		 }else{
		  var formData = $('#form2').serialize();
		  
			 $.ajax({
				type : 'POST',
				url : 'deleteDifficulty.action', 
				data : formData,
				success :  function deleteSuccess(data, status) {
					 $('#table_data').html(data);
					 $('#name').val("");
		    		 $('#id').val(0);
		    		 $('#rating').val(0);
		    		 $('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");
		    		 
					/*  $("#error").fadeIn();
		    		 $('#error').html("Selected Difficulty Deleted Successfully");
		    		 $("#error").css("color", "green");
		    		 
		    		 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 5000);
					 alert('Deleted'); */

						

					},
				error : function deleteFailed(errorIs) {
					alert(' Failed ' + errorIs.responseText);
				}

			});
			 
			
		 }
			 }
		  
		 
		
	  });
	  
	  $('#name').keyup(function() {	
			
			valid = "true";
			var uname = document.getElementById("name").value;		
			var regExp = /^[a-zA-Z]/; // allow only letters
			var iChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?";
			if (!regExp.test(uname)) {
				//bootbox.alert("Enter Alphabets Only");
				//validation("Enter Alphabets Only");
				//alert("Error");
			document.getElementById("name").value="";
			document.getElementById("name").focus();
			return false;
			} else{
				for ( var i = 0; i < document.getElementById("name").value.length; i++) {
					if (iChars.indexOf(document.getElementById("name").value.charAt(i)) != -1) {
						//bootbox.alert("Enter Alphabets Only");
						document.getElementById("name").value="";
						document.getElementById("name").focus();
						return false;

					}
				}
			}
			
	});
	  
	  
  });
  
  function editDetails(id,name, rating) {
	//alert(id+"  "+name+"  "+rating);
		/* $('#submit').hide();
		$('#update').show(); */
		$('#id').attr('value', id);
		$('#name').val( name);
		$('#rating').val(rating);
		$('#save').text("Update").removeClass("btn-success").addClass("btn-primary");
		

	}
  
  function clean(){
	  	 $('#name').val("");
		 $('#id').val(0);
		 $('#rating').val(0);
		 $('#save').text("Submit").removeClass("btn-primary").addClass("btn-success");
  }
 
  
  </script>
</head>
<body>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Add Difficulty</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-4 col-lg-offset-4" style="padding-top:40px;">
             
              <form method="post" class="bs-example form-horizontal" theme="simple" id="form1" enctype="multipart/form-data">
              <input type="hidden" id="id" name="difficultyBO.difficulty_id" value="0"/>
                       
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Difficulty name  </label>
                          <div class="col-lg-8">
                            <input type="text" name="difficultyBO.difficulty_name" id="name" class="form-control" placeholder="Enter Difficulty Name" required="required" maxlength="50">                            
                          </div>
                        </div>
                        
                        <div class="form-group">
                     	 <label class="col-sm-4 control-label">Select Rating  </label>
                      	<div class="col-sm-8">
                        <select name="difficultyBO.difficulty_rating" class="form-control m-b" id="rating">
                          <option value="0">select</option>
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                        </select>
                        </div>
                        </div>
                       
                        <div class="form-group">
                          <div class="col-lg-offset-4 col-lg-2">
                          <!-- <input type="button" id="save" class="btn btn-success" value="Submit"> -->
                             <button type="button" id="save" class="btn  btn-success">Submit</button> 
                          </div>
                        <div class="col-lg-offset-1 col-lg-2">
                            <button type="reset" class="btn btn-danger" onclick="javascript:clean()">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
        	  <center> <span id="error"></span></center>
              
              <hr>
                      
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             
             <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="table_data">
                <form id="form2">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Difficulty</th>
                        <th>Rating</th>
                        <th class="text_center">Edit</th>
                        <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         
                        
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="difficultyList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="difficulty_name"/></td>
										<td><s:property value="difficulty_rating"/></td>
										<td align="center"><button class="btn btn-default" type="button" class="btn btn-sm btn-default"	onclick="editDetails('<s:property value="difficulty_id"/>','<s:property value="difficulty_name"/>','<s:property value="difficulty_rating"/>')">Edit</button></td>
										<td class="chck" style="padding-bottom: 1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value="<s:property value="difficulty_id"/>" name="diff_id"/><i></i></label></td>
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  </form>
                  
                
                </div>
                
                </section>
                <center><button type="button" class="btn btn-del" id="delete">Delete selected</button></center>
              </section><!--sec close for data table-->
              
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
             
             
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
    <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	

</body>
</html>
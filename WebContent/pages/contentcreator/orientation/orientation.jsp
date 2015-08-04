<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>

 <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	
<script type="text/javascript">
CKEDITOR.replace('question1');
</script>



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
	//toastr.success("Success Message", "Success");
	  
	
	  

	  var oTable = $('#table').dataTable( {

		    "bProcessing": true,

		   

		    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
		    "sPaginationType": "full_numbers",
		    "aoColumns": [
		      { "mData": "columnname1" },
		      { "mData": "columnname2" },
		      { "mData": "columnname3" },
		      { "mData": "columnname4" }
		    ]
		  } );
	  
	  $('#maincheckbox').click(function(event) { 
	    	if($('#maincheckbox').prop('checked')) {
	    		// alert("checked");		    		 
	    		
	    		$('input[name=orientation_id]').each(function () {
	    			//alert("calling");
	    			// alert($(this).attr("id"));
	    			  $(this).prop('checked', true);
	    	});
	    	} else {
	    		// alert("Un checked");
	    		$('input[name=orientation_id]').each(function () {
	    			//alert("calling");
	    			//alert($(this).attr("id"));
	    			  $(this).prop('checked',false);
	    	});
	    	}
	        
	    });
	  
	  
	  $('#save').click(function(){
		  //alert("hai save");
		  //alert($('#2').is(':checked'));
		 var question=CKEDITOR.instances['question1'].getData();
		 
		 
		 if(question==''){
			 toastr.error("Enter Question");
			 return false;
		 } 
		  
		 if(($('#1').is(':checked'))){
			 //alert("checked"+$('#choice1').val());
				if($('#choice1').val()==''){
					 toastr.error("Please Enter The Choice");
					return false;
				}
			
		 }
		 if(($('#2').is(':checked'))){
			 //alert("checked"+$('#choice1').val()+"  "+$('#choice1').val());
				if(($('#choice1').val()=='')||($('#choice2').val()=='')){
					 toastr.error("Please Enter Choices");
					return false;
				}
			
		 }
		 
		 if(question==''){
			 toastr.error("Enter Question");
			 return false;
		 } 
		 else{
		
		 $("#question1").val(question);
		  
		  var formData = $('#form1').serialize();
		  $.ajax({
			  type : 'POST',
			  url  : 'saveOrintationQuestions.action',			
			  data : formData,
				
				 success:function(data){ 
						//alert("Inserted successfully");
					 $('#table_data').html(data);
					 clean();
					 /* $("#error").fadeIn();
		    		 $('#error').html("Question Added Successfully");
		    		 $("#error").css("color", "green");
		    		
		    		 $('#id').val(0);
		    		 $('#rating').val(0);
		    		 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 5000); */
		    		 
					},
					error:function(e){
						alert("Error occured while process the request"+e);
					}
			 }); 
		  
		 }
	  
	 
  });
	  
	  
 $('#delete').click(function(){
	 
	  /* if($('#maincheckbox').prop('checked')) {
		 alert($('input:checkbox:checked').length);
		 if($('input:checkbox:checked').length==1);
		 toastr.warning("No Question to Delete");
		 return false;
	 }  */
	 if($('[name="orientation_id"]:checked').length == 0) {
		// alert($('input:checkbox:checked').length);
		 if($('input:checkbox:checked').length==1);
		 toastr.warning("No Question to Delete");
		 return false;
	 }else{
	 
		  
		  var var2 = $('input:checkbox:checked').length;
		 
		   if(var2<=0){
			 toastr.warning("Please select the Question to Delete");
			 return false;
		 }else{
		  var formData = $('#form2').serialize();
		  
			 $.ajax({
				type : 'POST',
				url : 'deleteorintationQuestions.action', 
				data : formData,
				success :  function deleteSuccess(data, status) {
					 $('#table_data').html(data);
					 clean();
					 /* $("#error").fadeIn();
		    		 $('#error').html("Selected Question Deleted Successfully");
		    		 $("#error").css("color", "green");
		    		 $('#name').val("");
		    		 $('#id').val(0);
		    		 $('#rating').val(0);
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
 
 
 
   $('input:radio[id=1]').click(function () {
	    //alert("checked 1");
	    $('#choices').show();
	    
	    });
	$('input:radio[id=2]').click(function () {
		// alert("checked 2");
		 $('#choices').show();    	
	    
	    });
	    
	$('input:radio[id=3]').click(function () {
		$('#choices').hide();
	
	    });
	
	
	
	$('#choice1').keyup(function() {	
		
		valid = "true";
		var uname = document.getElementById("choice1").value;		
		var regExp = /^[a-zA-Z]/; // allow only letters
		var iChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?";
		if (!regExp.test(uname)) {
			
		document.getElementById("choice1").value="";
		document.getElementById("choice1").focus();
		return false;
		} else{
			for ( var i = 0; i < document.getElementById("choice1").value.length; i++) {
				if (iChars.indexOf(document.getElementById("choice1").value.charAt(i)) != -1) {
					document.getElementById("choice1").value="";
					document.getElementById("choice1").focus();
					return false;

				}
			}
		}
		
});
	
$('#choice2').keyup(function() {	
		
		valid = "true";
		var uname = document.getElementById("choice2").value;		
		var regExp = /^[a-zA-Z]/; // allow only letters
		var iChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?";
		if (!regExp.test(uname)) {
			
		document.getElementById("choice2").value="";
		document.getElementById("choice2").focus();
		return false;
		} else{
			for ( var i = 0; i < document.getElementById("choice2").value.length; i++) {
				if (iChars.indexOf(document.getElementById("choice2").value.charAt(i)) != -1) {
					document.getElementById("choice2").value="";
					document.getElementById("choice2").focus();
					return false;

				}
			}
		}
		
});

$('.form-control').change(function(){
	
var parent=$(this);
if(parent.val()!=''){
$('.form-control').each(function(index,value){
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
	    
 
	  
	  
  });
  
 
	    
  
  function editDetails(id,type,question,instructions,choice1,choice2,choice3,choice4,choice5,choice6,choice7,choice8,choice9,choice10){
	  //alert("hai   "+id+"  "+type+"  "+question+"  "+instructions+"  "+choice1+"  "+choice2+"  "+choice3+"  "+choice4+"  "+choice5+"  "+choice6+"  "+choice7+"  "+choice8+"  "+choice9+"  "+choice10);
		
		//alert("hai   "+id+"  "+question+"  "+type+"  "+choice1+"  "+choice2+"  "+choice3+"  "+choice4+"  "+choice5+"  "+choice6);
		//choice1
		 $('#id').val(id);
		CKEDITOR.instances['question1'].setData(question);
		//document.getElementById(type).checked=true;
		$("#instructions").val(instructions);
		if(type=='FILLIN'){
			//alert("FILLIN");
			//document.getElementById("3").checked=true;
			$('#3').prop("checked",true);
			//$('#3').attr("checked",true);

			$('#choices').hide();
			
		}if(type=='SINGLE'){
			//alert("SINGLE");
			$('#1').prop("checked",true);
			//document.getElementById("1").checked=true;
			//$('#1').attr("checked",true);
			$("#choice1").val(choice1);
			$("#choice2").val(choice2);
			$("#choice3").val(choice3);
			$("#choice4").val(choice4);
			$("#choice5").val(choice5);	
			$("#choice6").val(choice6);
			$("#choice7").val(choice7);
			$("#choice8").val(choice8);
			$("#choice9").val(choice9);
			$("#choice10").val(choice10);
			$('#choices').show();
			
			
		}
		if(type=='MULTIPLE'){
			//alert("MULTIPLE");
			$('#2').prop("checked",true);
			//document.getElementById("2").checked=true;
			//$('#2').attr("checked",true);
			$("#choice1").val(choice1);
			$("#choice2").val(choice2);
			$("#choice3").val(choice3);
			$("#choice4").val(choice4);
			$("#choice5").val(choice5);	
			$("#choice6").val(choice6);
			$("#choice7").val(choice7);
			$("#choice8").val(choice8);
			$("#choice9").val(choice9);
			$("#choice10").val(choice10);
			$('#choices').show();
			
		}
		
		$('#save').text("Update").removeClass("btn-success").addClass("btn-primary");
		
			

		
	}
  function clean(){
	  
	 // alert("clean");
	  	$("#id").val("0");		
		var question1=CKEDITOR.instances['question1'].setData("");
		$("#question1").val(question1);
				
		/* $("#1").attr("checked",true);
		$("#2").attr("checked",false);
		$("#3").attr("checked",false); */
		
	    $("#choice1").val("");
		$("#choice2").val("");
		$("#choice3").val("");
		$("#choice4").val("");
		$("#choice5").val("");	
		$("#choice6").val("");
		$("#choice7").val("");
		$("#choice8").val("");
		$("#choice9").val("");
		$("#choice10").val("");
		$("#instructions").val("");
		$('#choices').show();
		document.getElementById("1").checked=true;
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
             <div class="panel-heading"> <span class="panel-title">Add Orientation Questions</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
             
             
              <form class="bs-example form-horizontal" method="post"  theme="simple" id="form1" enctype="multipart/form-data">
                             <input type="hidden" id="id" name="orientationsQuestionsBO.orientation_question_id" value="0"/>
                             
                              <div class="form-group">
                       				 <label class="col-lg-2 col-lg-offset-3 control-label">Status </label></label>
                        			 <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="orientationsQuestionsBO.question_type" id="1" value="SINGLE" checked required="required">
                                    <i></i>
                                   Single 
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="orientationsQuestionsBO.question_type" id="2" value="MULTIPLE" required="required">
                                    <i></i>
                                    Multiple
                                  </label>
                                </div>   
                                
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="orientationsQuestionsBO.question_type" id="3" value="FILLIN" required="required">
                                    <i></i>
                                    Fill In
                                  </label>
                                </div>              
                            
                        </div>
                        
                             
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-3 control-label">Question</label>
                                  <div class="col-lg-7">
                                   <!--  <textarea rows="10" cols="80"  name="orientationsQuestionsBO.question" id="question1"></textarea> --> 
                                    <textarea class="ckeditor form-control" name="orientationsQuestionsBO.question" id="question1" rows="10" cols="80" > </textarea>
                                    <%-- <script> CKEDITOR.replace('orientationsQuestionsBO.question' ) </script>  --%>     
                                                       
                                  </div>
                             </div> 
                             
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-3 control-label">Instructions</label>
                                  <div class="col-lg-3">
                                    <textarea cols="50" rows="4" style="max-width:450px" name="orientationsQuestionsBO.instructions" id="instructions"></textarea>                          
                                  </div>
                             </div>       
                                  
                       	
                          	
                       
                       
                       
                       		
                       
                       		
                            <div class="form-group" >
								
                                <div class="row" id="choices">
                                
                                 <div class="col-lg-8 col-lg-offset-2 ">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice1" id="choice1">                            
                                  </div>                                                                     
                                    
                                  </div><!--row close for internal choices 1-->

                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice2" id="choice2">                            
                                  </div>                                                                     
                                    
                                  </div><!--row close for internal choices 2-->
	
    								<br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice3" id="choice3">                            
                                  </div>                                                                     
                                    
                                  </div><!--row close for internal choices 3-->
    								
                                    <br>
                              
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice4" id="choice4">                            
                                  </div>
                                                                                                     
                                  </div><!--row close for internal choices 4-->
                                  
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice5" id="choice5">                            
                                  </div>                                                                   
                                  </div><!--row close for internal choices 5-->
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 6</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice6" id="choice6">                            
                                  </div>                                                                   
                                  </div><!--row close for internal choices 6-->
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 7</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice7" id="choice7">                            
                                  </div>                                                                   
                                  </div><!--row close for internal choices 7-->
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 8</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice8" id="choice8">                            
                                  </div>                                                                   
                                  </div><!--row close for internal choices 8-->
                                  <br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 9</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice9" id="choice9">                            
                                  </div>                                                                   
                                  </div><!--row close for internal choices 9-->
                                  <br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 10</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="" name="orientationsQuestionsBO.choice10" id="choice10">                            
                                  </div>                                                                   
                                  </div><!--row close for internal choices 10-->
                                  
                                  
                                  </div><!--Col for choices close-->
                                                                                                                                       
                                  </div>
                                  
                                 </div><!--Form group close for choices-->

						
                           
                       
                                              
                        <div class="form-group">
                          <div class="col-lg-offset-6 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                           <!-- <input type="button" id="save" class="btn btn-success" >Submit -->
                           <button type="button" class="btn  btn-success" id="save">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="reset" class="btn btn-danger" onclick="javascript:clean()">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
        	  <center> <span id="error"></span></center>
              
        
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
                        <th>Question</th>
                        <th class="text_center">Edit</th>
                        <th class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="orientationList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><%-- <s:property value="question"/> --%>
										<s:text name="question"/></td>
										
										<td align="center"><a class="btn btn-default" 	href="javascript:editDetails('<s:property value="orientation_question_id"/>',
										'<s:property value="question_type"/>','<s:property value="question"/>','<s:property value="instructions"/>',
										'<s:property value="choice1"/>','<s:property value="choice2"/>','<s:property value="choice3"/>',
										'<s:property value="choice4"/>','<s:property value="choice5"/>','<s:property value="choice6"/>',
										'<s:property value="choice7"/>','<s:property value="choice8"/>','<s:property value="choice9"/>','<s:property value="choice10"/>')">Edit</a>
										
										</td>
										<td class="chck" style="padding-bottom:1px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value="<s:property value="orientation_question_id"/>" name="orientation_id"/><i></i></label></td>
										 
										
										
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
         <script src="assets/plug-ins/ckeditor/ckeditor.js"></script> 
	
   

</body>
</html>

<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Web Application</title>  
   
   
  <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
 <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
<script src="assets/js/jquery.min.js"></script>
  <script src="assets/js/parsley/parsley.min.js"></script>
<script src="assets/js/parsley/parsley.extend.js"></script>
     <!--ck editor-->
  <script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
   
</head>


 <script type="text/javascript">

  $(document).ready(function(){ 
	  //alert('table');
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
		      { "mData": "columnname9" }
		      
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
			};
	//toastr.success("Success Message", "Success");
	//toastr.error("Success Message", "Error");
}
	  
  
	  
 ); 
  
  
  //************************************** save **************************************
  function saveMethod()
  {
	  
	  //alert(CKEDITOR.instances.passage.getData());
	  
	  //alert($("#passage").validate());
	  
	 
		 
if($('#rcform').parsley('validate')){		 
		 
   $.ajax({
		type : 'POST',
		url : 'saveReadingComprehension.action?passageData='+CKEDITOR.instances.editor1.getData(),
		data : $('#rcform').serialize(),
		
	    success :function(result){
	    	////alert('in result');
	    	 $('#table_data').html(result);
	    	 toastr.success($("#msglabel").html(), "Success");
	    	 $("#reset").click();
    	},
	error : function(e){	
	
		toastr.error("Insertion Failed", "Error");
	}
}); 
	
   var bdata = $("#submit").html();
	 if(bdata=='Update')
		 {
		 $("#submit").html('Submit');
		 }
}  
	  
 }
  
  //************************************** save  END*******************************************************
   
  //************************************** EDIT *******************************************************
  
  
   //EDit 
  function editRecord(passage_id,difficulty_name, passage_title,passage,passage_type,difficulty,access_type,status){
	 // //alert('Inside EDIT'+passage_id);
	  $("#submit").html("Update"); 
	  $('#passage_id').val(passage_id);
	  $('#passage_type').val(passage_type);
	  $('#difficulty').val(difficulty);
	  $('#access_type').val(access_type);
	  //$('#editor1').val(passage);	  
	  $('#difficulty_name').val(difficulty_name);
	  $('#passage_title').val(passage_title);
	  
	  CKEDITOR.instances.editor1.setData(passage);
	  
	 // alert(status+":"+access_type+":"+passage_type+":"+passage+":"+passage_id);
	 
	  if(status=='ACTIVE')
		  {
		  document.getElementById("active").checked=true;
		  document.getElementById("inactive").checked=false;
		  
	  
		  }
	  if(status=='INACTIVE')
	  {
		  document.getElementById("active").checked=false;
		  document.getElementById("inactive").checked=true;
		  
	  }
	  
	  if(access_type=='FREE')
	  {
	  document.getElementById("free").checked=true;
	  document.getElementById("paid").checked=false;
	  
  
	  }
  if(access_type=='PAID')
  {
	  document.getElementById("free").checked=false;
	  document.getElementById("paid").checked=true;
	  
  }
  
  }
  
  //************************************** EDIT  END*******************************************************
   
  //************************************** DELETE *******************************************************
  
  function deleteData()
	  {
		 
	  if($('input:checkbox:checked').length <= 0)
		 {
		
		 toastr.error("Please Select the Records To Delete","Error");
		  
		 
		 }
		
		 else
		 {
		 $.ajax({
				type : 'POST',
				url : 'deleteReadingComprehension.action',
				data : $('#deleteData').serialize(),
				
				 success :function(result){
			    	 $('#table_data').html(result);
			    	 
			    	 if($("#msglabel").html()=="success")
			    		 {
			    		 toastr.success("Data Deleted Successfully","Success");
			    		 $("#reset").click();
			    		 
			    		 }else{
			    			 toastr.warning("Selected Records are In Use!They are Dependent! Deletion Failed!!","Error");
			    			 
			    		 }
			    	 
		    		  
			    },
			    
			    error : function(e){	
					 
			    	 toastr.error("Data Deletion Failed","Error");
		    		 
				}
			}); 
	  }
	  }
	 
  //************************************** DELETE  END*******************************************************
  
  
  </script>






 <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title">Add Reading Comprehension</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form class="bs-example form-horizontal" id="rcform" method="get">
                       
                       			<div class="form-group">
                          				<label class="col-lg-3 control-label">Passage Title
                          				 <input type="text" value="0" name="readingComprehensionBO.passage_id" id="passage_id" style="display: none;">
                          				</label>
                          				<div class="col-lg-9">
                           				 	<input type="text"  data-required="true"  class="form-control" placeholder="Title" name= "readingComprehensionBO.passage_title" id= "passage_title">
                            			</div>
                        			</div>
                                    
                                    
                              <div class="form-group">
                          				<label class="col-lg-3 control-label">Passage</label>
                          				<div class="col-lg-9">
                            				<textarea id="editor1" name="readingComprehensionBO.passage"  rows="10" cols="80"> </textarea>
											<script> CKEDITOR.replace('readingComprehensionBO.passage');</script>
                          				</div>
                        	  </div>
                       


                            <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label"> Passage Type</label>
                                <div class="col-lg-5">
                                <select class="form-control m-b"   data-required="true" name= "readingComprehensionBO.passage_type" id= "passage_type">
                                  <option value="">Select</option>	
                                  <option value="SHORT">SHORT</option>
                                  <option value="MEDIUM">MEDIUM</option>
                                  <option value="LONG">LONG</option>
                                 </select>
                                </div>
                            </div>
                             
                             
                            <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label"> Difficulty</label>
                                <div class="col-lg-5">
                                <select  class="form-control m-b"   data-required="true" name= "readingComprehensionBO.difficulty" id= "difficulty">
                                <option value="">Select</option>	
                                   <s:iterator value="difficultyBOs">
                                    <option value="<s:property value="difficulty_id" />"><s:property value="difficulty_name" /> </option>
                                   </s:iterator>
                                </select>
                                </div>
                             </div>
                              
                             
                         
                       	
                    
                       	<div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Access Type</label>
                        <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio"  data-required="true"  name= "readingComprehensionBO.access_type" id= "free"  value="Free">
                                    <i></i>
                                   Free
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio"   data-required="true" name= "readingComprehensionBO.access_type" id= "paid" value="Paid" >
                                    <i></i>
                                    Paid
                                  </label>
                                </div>
                        </div> 
            		  
            			
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label> 
                         <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio"  data-required="true"  name= "readingComprehensionBO.status" id= "active" value="ACTIVE" >
                                    <i></i>
               						ACTIVE
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio"  data-required="true"  name= "readingComprehensionBO.status" id= "inactive" value="INACTIVE" >
                                    <i></i>
                                    INACTIVE
                                  </label>
                                </div>           
                        </div>
                                                             
                       
                                              
                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn btn-success" onclick="saveMethod();" id="submit">Submit</button>
                          </div>
                        <div class="col-lg-offset-=1 col-lg-1">
                            <button type="reset" id="reset" class="btn btn-danger">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
              
        
            
               <hr>
        
             </div><!--col for skill level close-->             
             </div><!--Inner row1 close -->
             
             
             
             
               <div class="row" id="table_data">
                         <div class="col-lg-10 col-lg-offset-1" style="padding-top:40px;">
                        
               <label id="msglabel" style="display: none;"><%
              
               if(request.getAttribute("message")==null)
               {
            	 out.write("");   
               }else{
            	out.write(request.getAttribute("message").toString());   
               }
               %></label> 
             
              <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" >                      
                <form id="deleteData">
                  <table class="table table-striped m-b-none" id="table">
                    <thead>
                      <tr>
                        <th>S.No</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Passage</th>
                        <th>Difficulty</th>
                        <th>Access Type</th>
                        <th>Status</th>
                        <th>Edit</th>
                        <th class="chck text_center" style="padding-bottom:0px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="readingComprehensionBOs" status="sno">
                    <tr> 
                                       
                    <td><s:property value="#sno.index+1"></s:property></td>
                    <td><s:property value="passage_title"></s:property></td>
                    <td><s:property value="passage_type"></s:property></td>
                    <td><s:property value="passage"></s:property></td>
                    <td><s:property value="difficulty_name"></s:property></td>
                    <td><s:property value="access_type"></s:property></td>
                    <td><s:property value="status"></s:property></td>
                    <td><button type="button" class="btn btn-sm btn-default"  value="EDIT" onclick="editRecord('<s:property value="passage_id"/>','<s:property value="difficulty_name"/>', '<s:property value="passage_title"/>','<s:property value="passage"/>','<s:property value="passage_type"/>','<s:property value="difficulty"/>','<s:property value="access_type"/>','<s:property value="status"/>')">EDIT</button></td>
                    <td class="chck" style="padding-top:3px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="passage_id"/>' name="deleterecords"  /><i></i></label></td>
                    </tr>
                    </s:iterator>
                    </tbody>
                  </table>
                      </form>
                </div>
                
                </section>
                <center><a href="#" class="btn btn-del" onclick="deleteData();">Delete selected</a></center>
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
	<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
	<script src="assets/js/datatables/demo.js"></script>
  
 
</body>
  
</html>



 
 
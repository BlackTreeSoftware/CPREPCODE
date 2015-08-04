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
  
  <script type="text/javascript">

  $(document).ready(function(){ 
	//alert('ok');
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
  
  
  //EDit 
  function editRecord(referral_master_id,condition_id, condition_title,referral_master_name,referral_master_quant,referral_master_verbal,referral_master_limit,status){
	 // //alert('Inside EDIT'+referral_master_id);
	  $("#submit").html("Update");
	  $('#referral_master_id').val(referral_master_id);
	  $('#referral_master_quant').val(referral_master_quant);
	  $('#referral_master_verbal').val(referral_master_verbal);
	  $('#referral_master_limit').val(referral_master_limit);
	  $('#referral_master_name').val(referral_master_name);	  
	  $('#condition_id').val(condition_id);
	  $('#condition_title').val(condition_title);
	 
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
	
  }
  
	 //save
	 function submitform() {
		// $("#submit").html("Update");
		
		
		 if($('#referralmasterform').parsley('validate')){
			 
			 $.ajax({
					type : 'POST',
					url : 'saveReferralMaster.action',
					data : $('#referralmasterform').serialize(),
					
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
			 
			 
		 } 
		 
		 var bdata = $("#submit").html();
		 if(bdata=='Update')
			 {
			 $("#submit").html('Submit');
			 }
	 }//method end
	  


  
	 function deleteData()
	  {
		 
		// alert(" Delete Record    : "+$('input:checkbox:checked').length);
		 //alert($('#maincheckbox').attr('checked'));
		 var len = $('input:checkbox:checked').length;
		 var check = $('input:checkbox[id=maincheckbox]').is(':checked');
		 
		 
		// alert(check+"  : "+len);
		 
		 if(check)
			 {
			 
			// alert("before : "+len);
			   len = len-1;
			  // alert(len);
			 
			 }
		 
		 if(len <= 0)
			 {
			 toastr.error("Please Select the Records To Delete","Error");
    		 }
		 
		 
		
		 else
		 {
		 $.ajax({
				type : 'POST',
				url : 'deleteReferralMaster.action',
				data : $('#deleteData').serialize(),
				
			    success :function(result){
			    	 $('#table_data').html(result);
			    	 
			    	 toastr.success($("#msglabel").html(),"Success");
			    	 $("#reset").click();
		    		  
			    },
			    
			    error : function(e){	
					 
			    	 toastr.error("Data Deletion Failed","Error");
		    		 
				}
			}); 
	  }
		 
		 $('input:checkbox[name=maincheckbox]').attr('checked',true);
	  }
	 
	 
  
  </script>

   
</head>
 
        
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Refferal Masters</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form class="bs-example form-horizontal" method="post" id="referralmasterform" data-validate="parsley">
               <div class="form-group">
                     	 <label class="col-lg-2 col-lg-offset-4 control-label">Referral Condition
                     	  <input type="text" value="0" name="referralMasterBO.referral_master_id" id="referral_master_id" style="display: none;">
                     	 </label>
                      	<div class="col-lg-5">
                        <select name="referralMasterBO.condition_id" class="form-control m-b" id="condition_id" data-required="true">
                        <option value="">Select</option>
                        <s:iterator value="conditionBOs"> 
                          <option value="<s:property value="condition_id"/>">  <s:property value="condition_title"/> </option> 
                         </s:iterator>
                         </select>                        
                        </div>
                        </div>
                        
                       
                        
                        
                         <div class="form-group">
                          <label class="col-lg-2 col-lg-offset-4 control-label">Quant Questions</label>
                          <div class="col-lg-5">
                            <input type="text" data-type="number"  data-required="true" class="form-control" placeholder="" name="referralMasterBO.referral_master_quant" id="referral_master_quant" maxlength="4">                            
                          </div>
                        </div>    
                        
                        <div class="form-group">
                          <label class="col-lg-2 col-lg-offset-4 control-label">Verbal Questions</label>
                          <div class="col-lg-5">
                            <input type="text" data-type="number"  data-required="true" class="form-control" placeholder="" name="referralMasterBO.referral_master_verbal" id="referral_master_verbal" maxlength="4">                            
                          </div>
                        </div>    
                        
                        <div class="form-group">
                          <label class="col-lg-2 col-lg-offset-4 control-label">Questions Limit</label>
                          <div class="col-lg-5">
                            <input type="text" data-type="number"  data-required="true" class="form-control" placeholder="" name="referralMasterBO.referral_master_limit" id="referral_master_limit" maxlength="4">                                                       
                          </div>
                        </div>     
                        
                          <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-4  control-label">Status</label>
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" data-required="true" id="active" checked name="referralMasterBO.status" value="ACTIVE"  >
                            <i></i>
                            ACTIVE
                          </label>
                        </div> 
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" data-required="true" id="inactive"  name="referralMasterBO.status" value="INACTIVE"  >
                            <i></i>
                           INACTIVE
                          </label>
                        </div>
                        
                       </div><!--form group close-->     
          
                       
                        <div class="form-group">
                          <div class="col-lg-offset-6 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn  btn-success" id="submit" onclick="submitform();">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="reset" class="btn  btn-danger" id="reset" > Reset</button>
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
                        <th>Condition</th>
                        <th>Title</th>
                        <th>Quant Questions</th>
                        <th>Verbal Questions</th>
                        <th>Questions Limit</th>
                        <th>Status</th>
                        <th>Edit</th>
                        <th class="chck text_center" style="padding-bottom:0px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox" name="maincheckbox"><i></i></label></th>
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="referralMasterBOs" status="sno">
                    <tr> 
                                       
                    <td><s:property value="#sno.index+1"></s:property></td>
                    <td><s:property value="condition_title"></s:property></td>
                    <td><s:property value="referral_master_name"></s:property></td>
                    <td><s:property value="referral_master_quant"></s:property></td>
                    <td><s:property value="referral_master_verbal"></s:property></td>
                    <td><s:property value="referral_master_limit"></s:property></td>
                    <td><s:property value="status"></s:property></td>
                    <td><button type="button" class="btn btn-sm btn-default"  value="EDIT" onclick="editRecord('<s:property value="referral_master_id"/>','<s:property value="condition_id"/>', '<s:property value="condition_title"/>','<s:property value="referral_master_name"/>','<s:property value="referral_master_quant"/>','<s:property value="referral_master_verbal"/>','<s:property value="referral_master_limit"/>','<s:property value="status"/>')">EDIT</button></td>                   
                    <td class="chck" style="padding-top:3px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="referral_master_id"/>' name="deleterecords"/><i></i></label></td>
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
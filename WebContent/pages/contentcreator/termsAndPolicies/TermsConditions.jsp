

<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html lang="en" class="app">
<head>  
 <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
	
    <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
    <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
   <script src="assets/js/select2/select2.min.js"></script>
  <script src="assets/plug-ins/ckeditor/ckeditor.js"></script>   
  <script src="assets/js/jquery.min.js"></script>
  <script src="assets/js/parsley/parsley.min.js"></script>
<script src="assets/js/parsley/parsley.extend.js"></script>
  <script type="text/javascript">
  
  
  </script>

  
  
</head>
<body class="">
   <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Terms & Conditions</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form class="bs-example form-horizontal" id="formt" action="getTermsConditions.action" method="post">
                                  	
                          	<div class="form-group">
                          				<label class="col-lg-2 control-label">Policy Terms</label>
                          				<div class="col-lg-10">
                            				<textarea id="policy_terms" class="ckeditor form-control"  rows="10" cols="80" required="required" name="policyBO.policy_terms"> </textarea>
										   
                          				</div>
                        	</div>
                             <div class="form-group">
                          				<label class="col-lg-2 control-label">Policy Conditions</label>
                          				<div class="col-lg-10">
                            				<textarea id="policy_conditions" class="ckeditor form-control" rows="10" cols="80" required="required" name="policyBO.policy_conditions"></textarea>
                                            
                          				</div>
                              </div>
                             
                              
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label></label>
                        
                        <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" id="active" value="ACTIVE" checked name="policyBO.status">
                            <i></i>
                           Active
                          </label>
                        </div>
                        
                       <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" id="inactive" value="INACTIVE" name="policyBO.status">
                            <i></i>
                           In Active
                          </label>
                        </div>
                        
                        </div>
                         <input type="hidden" class="form-control" id="status" name="policyBO.status">
                              
                              <div>
                              <input type="hidden" class="form-control" id="policy_id" name="policyBO.policy_id">
                              
                              </div>
                                           
                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="button" class="btn btn-success" id="confirm">Submit</button>
                            <button type="button" class="btn btn-primary" id="update">Update</button>
                          
                            
                          </div>
                          <div class="col-lg-offset-1 col-lg-1">
                            <button type="reset" class="btn btn-danger" id="reset">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
                        
                        <!-- <div id="saveTerms"></div> -->
        	  </form>
              
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
             <hr>             
                              
                     <div class="row">
             <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">                
               
                 
                 <div class="table-responsive" id="ajax-table">
                  <table class="table table-striped m-b-none"  id="policy">
                     <thead>
                      <tr>
                        <th>S.No</th>
                        <th>Select</th>
                        <th>Terms</th>
                        <th>Conditions</th>
                        <th>Status</th>
                        <th></th>
                        
                      
                    
                         
                         </tr>
                    </thead>		

					<tbody>
						 <s:iterator value="policyBOList" status="row"> 
                    <tr>

                     <td><s:property value="#row.index+1"/></td>
                     <td><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="policy_id"/>'><i></i></label></td>
                     <td><s:property value="policy_terms"/></td>
                     <td><s:property value="policy_conditions"/></td>
                     <td><s:property value="status" /></td>
                     <td><a href="javascript:setDetails('<s:property value="policy_terms"/>','<s:property value="policy_conditions"/>','<s:property value="policy_id"/>','<s:property value="status"/>')" >Edit</a></td>
                      </tr>

                    </s:iterator> 
					</tbody>
                  </table>
                </div>
                   
             
                </section>
                 <center><a href="#" class="btn btn-del" id="delete">Delete selected</a></center>
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
            
            
 <%-- <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
  <script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
  <script src="assets/js/datatables/demo.js"></script> --%>
 <script type="text/javascript">
	$(document).ready(function() {	
		refresh();
		
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
		 
 		var oTable = $('#policy').dataTable( {

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
		     			$('#update').hide();
						$('#confirm').click(function(){		
							var value1 = CKEDITOR.instances['policy_terms'].getData();
							var value2 = CKEDITOR.instances['policy_conditions'].getData();
							//alert(value1.trim());
							var templateRadioId=$('#status').val();
							var status=$(':radio:checked').val();
							if(value1==''){
								toastr.error("enter policies");
								return false;
								
							}else if(value2==''){
								toastr.error("enter conditions");
								return false;
								
							}else{
							$('#status').attr('value',status);		
									  $.ajax({
											type : 'POST',
											url : 'saveTermsConditions.action',
										    data :'policyBO.policy_terms='+value1+'&policyBO.policy_conditions='+value2+'&policyBO.status='+status,
											dataType : 'JSON',
										    success :function successInfo(result,status){
									            if(status=="success")
										            toastr.success("Terms and Policies Saved !!");
										            refresh();
										            return true;
									            },
										    error : function errorInfo(result){
										    	toastr.error("Error");
									            return false;
									   	   }
										}); 
							            
							  
							            
							}
					
						});  
						
						$('#update').click(function(){		
							var value1 = CKEDITOR.instances['policy_terms'].getData();
							var value2 = CKEDITOR.instances['policy_conditions'].getData();
							var id=$('#policy_id').val();
							var templateRadioId=$('#status').val();
							var status=$(':radio:checked').val();
							$('#status').attr('value',status);
									  $.ajax({
											type : 'POST',
											url : 'updateTermsConditions.action',
										    data :'policyBO.policy_terms='+value1+'&policyBO.policy_conditions='+value2+'&policyBO.policy_id='+id+'&policyBO.status='+status,
											dataType : 'JSON',
										    success :successInfo,
										    error : errorInfo
										}); 
							            function successInfo(result,status){
								            if(status=="success")
								            	toastr.success("Terms and Policies Updated !!");
								            refresh();
								            return true;
							            }
							  
							            function errorInfo(result){
								            alert("Error");
								            return false;
								   	   }							
						});    
						
						$('#delete').click(function(){		
							var id=$('#policy_id').val();
							var array=[];
							var checkboxvalues;
							$('input[id=select]:checked').each(function(){
								array.push($(this).val());
							});
							if (array.length > 0){
							checkboxvalues=array.join(',') + ",";
									  $.ajax({
											type : 'POST',
											url : 'deleteTermsConditions.action',
										    data :'checkboxvalues='+checkboxvalues,
											dataType : 'JSON',
										    success :successInfo,
										    error : errorInfo
										}); 
							            function successInfo(result,status){
								            if(status=="success")
								            toastr.success("Terms and Policies Deleted !!");
								            refresh();
								            return true;
							            }
							  
							            function errorInfo(result){
							                toastr.error("Record Already In Use.!");
								            refresh();
								            return false;
								   	   }	
							}
							else{
								toastr.warning("No record is selected to delete","Please Select atleast one record to delete");
							}
						}); 
						
						$('#reset').click(function(){
							CKEDITOR.instances['policy_terms'].setData('');
							CKEDITOR.instances['policy_conditions'].setData('');
							$('#confirm,#update').toggle();
							
						});
						
					
					});
	
	

	function setDetails(terms,conditions,id,status) {
		
		$('#confirm').hide();
		$('#update').show();
		CKEDITOR.instances['policy_terms'].setData(terms);
		CKEDITOR.instances['policy_conditions'].setData(conditions);
		$('#policy_id').attr('value', id);
		$('#status').attr('value', status);
		//var status1=status;
		if(status=='ACTIVE'){
		$('#active').prop("checked",true);
		}
		if(status=='INACTIVE'){
			
			$('#inactive').prop("checked",true);
		}
	} 
	function refresh(){
		$.ajax({
			type : 'POST',
			url  : 'RefreshTermsConditions.action',
		    success :successInfo,
		    error : errorInfo
		}); 
        function successInfo(result,status){
            if(status=="success")
            $('#ajax-table').html(result);
            $('#confirm').show();
    		$('#update').hide();
            CKEDITOR.instances['policy_terms'].setData('');
			CKEDITOR.instances['policy_conditions'].setData('');
            return true;
        }

        function errorInfo(result){
            alert("Error");
            return false;
   	   }	
		
	}

	 

		
	
	
</script>
<script type="text/javascript">
CKEDITOR.replace('policy_terms');
CKEDITOR.replace('policy_conditions');

</script> 

 
  <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	
</body>
 
</html>
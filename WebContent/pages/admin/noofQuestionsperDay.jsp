  <%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />

<style>
  .chck{  width:40px;}
  .text_center {text-align: center}
  </style>
<script src="/assets/js/jquery.min.js"></script>
<link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
 
  <script type="text/javascript">
  
  $(document).ready(function(){
	  
	
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
		      { "mData": "columnname10"},
		      { "mData": "columnname11"}
		    ]
		  } );
	  
	  
	 
  });
	
  </script>
 <%--  <script type="text/javascript">
  
  $(document).ready(function(){
	 alert(' in document Readyd ');
	  $.ajax({
			tye:'POST',
			url:'QuestionsData.action',
			dataType:'json',
			success:dataGettingSuccess,
			errror:operationFailed
			
		});
	  function dataGettingSuccess(jsonData){
		  
		  var tableData=jsonData.tableData;
		//  alert(' Json Data is; '+tableData);
		  $.each($.parseJSON(tableData), function(index, data) {
			  index=index+1;
			  alert(' index is; '+index+' data is : '+data.last_name);
			  rowNew = $('<tr id=tr'+data.question_id+'><td><label class="checkbox m-n i-checks"><input type="checkbox"   class="questionsCheckBox"  value="'+data.question_id+'"/><i></i></label></td><td>'+parseInt(index+1)+'</td><td>'+data.section_name+'</td><td>'+data.category_name+'</td><td>'+data.test_name+'</td><td>'+data.question_title+'</td><td>'+data.question_directions+'</td><td><input style="display: inline-block;padding: 6px 12px;margin-bottom: 0;font-size: 14px;font-weight: normal;line-height: 1.42857143;text-align: center;white-space: nowrap;vertical-align: middle;cursor: pointer;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;background-image: none;border: 1px solid transparent;border-radius: 0px;color: #333;background-color: #fff; border-color: #ccc;" type="button" value="EDIT"  class="btn" onClick=questionIdPass('+data.question_id+')></td></tr>');
		  }
		  );
	  }
	  function operationFailed(errorIs){
		  
	  }
  });
  
  </script> --%>
</head>
<body>
 <%--    <s:hidden id="tableData" value="%{#request.tableData}"/> --%>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title"> Student IP Addresses</span>  </div>
             <div class="panel-body">
     
             
             
             <!-- <div class="row">
             <div class="col-lg-12 col-lg-offset-2" style="padding-top:40px;"> -->
             
             <section class="scrollable padder">
             
              <section class="panel panel-default">
                
                <div class="table-responsive" id="table_data">
                <form id="form2">
                  <table id="table" class="table table-striped m-b-none">
                    <thead>
                      <tr>
                       
                        <th>S.No</th>
                        <th>Student Name</th>
                        <th>SectionName</th>
                        <th>CategoryName</th>
                        <th>QuestionType</th>
                        <th>TestDate</th>
                        <th>TestName</th>
                        <th>TotalQuestions</th>
                        <th>Correct</th>
                        <th>Incorrect</th>
                         <th>Skipped</th>
                       
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="questionsList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="name"/></td>
										<td><s:property value="section_name"/></td>
										<td><s:property value="category_name"/></td>
										<td><s:property value="type_name"/></td>
										<td><s:property value="test_date"/></td>
										<td><s:property value="test_name"/></td>
										<td><s:property value="total_questions"/></td>
										<td><s:property value="correct_questions"/></td>
										<td><s:property value="incorrect_questions"/></td>
										<td><s:property value="skipped_questions"/></td>
										
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  </form>
                  
                
                </div>
                
                </section>
               
              </section><!--sec close for data table-->
              
              
                      
            <!--  </div>col for DataTable close
             
             </div>Inner row2 close -->
             
             
             
             
             
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
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
		      { "mData": "columnname7" }
		     
		    ]
		  } );
	  
	  
	 
  });
	
  </script>
</head>
<body>

<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
              <div class="row">row start main
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title">Lessions Per Day</span>  </div>
             <div class="panel-body">
     
             
             
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
                        <th>Student Email</th>
                        <th>SectionName</th>
                        <th>Lesson Name</th>
                        <th>Sub Lesson Name</th>
                        <th>Lesson Taken Date</th>
                        <th>No Of Lessons</th>    
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="lessonsList" status="status">
									<tr >
										
										<td><s:property value="#status.index+1"/></td>
										<td><s:property value="email"/></td>
										<td><s:property value="section_name"/></td>
										<td><s:property value="mainlesson_name"/></td>
										<td><s:property value="sublesson_name"/></td>
										<td><s:property value="lesson_taken_date"/></td>
										<td><s:property value="no_lessons"/></td>
										
									</tr>
					</s:iterator>
                    </tbody>
                    
                  </table>
                  </form>
                  
                
                </div>
                
                </section>
               
              </section><!--sec close for data table-->
              
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
             
             
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--  div close -->
             </div><!--  row start main End--> 
            
            
            
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
    <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	
</body>
</html>
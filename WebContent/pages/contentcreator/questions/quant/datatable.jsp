<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 
 <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
<script src="assets/js/jquery.min.js"></script>

   <link rel="stylesheet" href="assets/css/custom.css" type="text/css"/>
   <script src="assets/js/jquery.min.js"></script>

</head>
<body>
<section id="content">
          <section class="vbox">
            <section class="scrollable padder">
              <div class="m-b-md">
                <h3 class="m-b-none">Datatable</h3>
              </div>
              <section class="panel panel-default">
                <header class="panel-heading">
                  DataTables 
                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                </header>
                <div class="table-responsive">
                  <table class="table table-striped m-b-none" data-ride="datatables" id="sample">
                    <thead>
                      <tr>
                        <!-- <th width="20"><label class="checkbox m-n i-checks"><input type="checkbox"><i></i></label></th> -->
                        <th width="20%">Rendering engine</th>
                        <th width="25%">Browser</th>
                        <th width="25%">Platform(s)</th>
                        <th width="15%">Engine version</th>
                        <th width="15%">CSS grade1</th>
                        <th width="15%">CSS grade2</th>
                        <th width="15%">CSS grade3</th>
                        <th width="15%">CSS grade4</th>
                        
                        
                         
                        
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>6</td>
                    <td>7</td>
                    <td>8</td>
                   
                    
                    
                   
                    </tr>
                    <tr>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>6</td>
                    <td>7</td>
                    <td>8</td>
                    
                    
                    
                   
                    </tr>
                   
                    
                    
                    </tbody>
                  </table>
                </div>
              </section>
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
 <!-- datatables -->
<!-- datatables -->

<script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	<script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
	<script src="assets/js/datatables/demo.js"></script>
 <script type="text/javascript">
 $(document).ready(function() {
	   
	   alert("Hi1");
// 	   $("#sample").dataTable( {

		      
// 		      "bProcessing": true,

// 		     // "sAjaxSource": "assets/js/datatables/datatable.json",

// 		      "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
// 		      "sPaginationType": "full_numbers",
// 		      "aoColumns": [
// 		        { "mData": "engine" },
// 		        { "mData": "browser" },
// 		        { "mData": "platform" },
// 		        { "mData": "version" },
// 		        { "mData": "grade1" },
// 		        { "mData": "grade2" },
// 		        { "mData": "grade3" },
// 		        { "mData": "grade4" }
		        
		        
// 		      ]  
// 		    } );
	   
	   
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
		      { "mData": "columnname6" }
		    ]
		  } );

	} );
</script>
 
</body>
</html>
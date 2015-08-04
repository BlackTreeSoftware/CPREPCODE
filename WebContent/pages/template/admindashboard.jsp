<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!--  <script src="http://code.highcharts.com/stock/highstock.js"></script>
<script src="http://code.highcharts.com/stock/modules/exporting.js"></script> -->
 <script type="text/javascript">
 
 $(document).ready(function(){
	   
$(function() {

	$.getJSON('usersGraphData.action', function(data) {
	  //  alert(' data is; '+data.data);
		$('#container').highcharts('StockChart', {
			
            
			rangeSelector : {
				selected : 1,
				inputEnabled: $('#container').width() > 480
			},

			title : {
				text : 'Student Registration Details'
			},
			
			series : [{
				name : 'StudentCount',
				 data :JSON.parse(data.data),
				
			}]
		});
	});

});  


   });
		</script>
		<script src="assets/js/highstock/highstock.js"></script>
</head>
<body>

<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title">Admin Dashboard</span>  </div>
             <div class="panel-body">
             
             
               


<div id="container" style="height: 400px; min-width: 310px"></div>
             
             </div>
             </div>
             </div>
             
             </div>
             </section>
             </section>
             </section>
	

  
          

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />

  <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css"/>
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>

  <script src="assets/js/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//alert("hello");
		var tableDa = $('#dynadata').val();

		var jsonTableData = JSON.parse(tableDa);
		// alert("hai"+jsonTableData);
		var data = jsonTableData.tableData;
		// alert("datat is"+data);
		if(data==null || data=='undefined'){
			tableCss();
		}
		else{
		
		var table = $("#table");
		table.find("tr:gt(0)").remove();
		$.each($.parseJSON(data),
						function(index, data) {
							var rowNew = "";
                            var topic=data.awa_question_topic;
							rowNew = $('<tr id='+data.awa_question_id+'><td><label class="checkbox m-n i-checks"><input type="checkbox" id="awacheck" class="questionsCheckBox" value="'+data.awa_question_id+'"/><i></i></label></td><td >'+data.category_name+'</td><td id="'+data.awa_question_id+'topic">'+ data.awa_question_topic+ '</td><td id="'+data.awa_question_id+'access">'+ data.access_type+ '</td><td id="'+data.awa_question_id+'stat">'+ data.status+ '</td><td style="display:none" id="'+data.awa_question_id+'direc">'+data.awa_question_directions+'</td><td style="display:none" id="'+data.awa_question_id+'testid">'+data.category_id+'</td><td><input style="display: inline-block;padding: 6px 12px;margin-bottom: 0;font-size: 14px;font-weight: normal;line-height: 1.42857143;text-align: center;white-space: nowrap;vertical-align: middle;cursor: pointer;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;background-image: none;border: 1px solid transparent;border-radius: 0px;color: #333;background-color: #fff; border-color: #ccc;" type="button" value="EDIT"  class="btn" onClick="questionsUpdation('+ data.awa_question_id+ ')"/></td> </tr>');
							//alert("in roww");
							rowNew.appendTo(table);
						});
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
			      { "mData": "columnname8" }
			      
			    ]
			  } );
		}
		$('#maincheckbox').click(function() 
				{
			$('.questionsCheckBox').prop('checked',
							$(this).prop('checked'));
				});
		$('#maincheckbox').prop('checked', false);
		
		
		
	
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
		

	});

function questionsUpdation(quesid) {
//alert("in questions updation"+quesid);
// 		alert(' Slected ID is: ' + quesid+""+$('#'+quesid+""+'direc').html()+""+$('#'+quesid+""+'testid').html()+""+$('#'+quesid+""+'topic').html()+""+$('#'+quesid+""+'access').html()+""+$('#'+quesid+""+'stat').html());
// 		alert("the values are"+c);
// 		$.ajax({
// 			tye : 'POST',
// 			url : 'updateawa.action',
// 			data : 'questionid=' + quesid,
// 			dataType : 'json',
// 			success : succesdata,
// 			errror : formerror

// 		});
$('#questionid').val(quesid);
$('#qtopic').val($('#'+quesid+""+'topic').html());
$('#qace').val($('#'+quesid+""+'access').html());
$('#qstat').val($('#'+quesid+""+'stat').html());
$('#qdirec').val($('#'+quesid+""+'direc').html());
$('#qtest').val($('#'+quesid+""+'testid').html());

//alert("the setted value is"+$('#questionid').val());
  $('#form').attr('action', "updateawa");
	  $('#form').submit();

	}


function awadetailsdeleting()
{
		var matches = [];
		$(".questionsCheckBox:checked").each(function() {
			matches.push(this.value);
		});
		if (matches.length>0) {
	      //	alert('Slected ID is: '+ matches);
			$('#deletelis').val(matches);
			$('#form').attr('action', "deleteawa");
			//toastr.success("Deleted Successfully");
			$('#form').submit();
			
		}
		else
			{
			toastr.warning("Select Atleast One Record");
			}
	
}




	</script>


</head>
<body>
<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
        <form id="form"  method="post">    
            <div class="row">
           
	<div class="col-lg-12 " style="padding-top: 10px;">
  <a class="btn btn-default" href="Manage-AWA-Questions.action" class="auto">Add New</a>
  <br><br>
		<section class="scrollable padder">
		 <section	class="panel panel-default">
		
		 <header class="panel-heading">
                                  AWA Questions
                                  <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> 
                                </header> <!-- <div id="dynamictable"/> -->
                    <s:hidden id="dynadata" value="%{#request.awatableData}" /> 
 			       <s:hidden  id="questionid" name="questionid" ></s:hidden>
                  <s:hidden id="qtopic" name="questiotopic"></s:hidden>
                 <s:hidden id="qdirec" name="questiodirec"></s:hidden>
                 <s:hidden id="qstat" name="status"></s:hidden>
                 <s:hidden id="qtest" name="testid"></s:hidden>
                   <s:hidden id="qace" name="accestype"></s:hidden>
                     <s:hidden id="deletelis" name="deltelist"></s:hidden>
		<div class="table-responsive" >
 			
			<table class="table table-striped m-b-none" id="table">
				<thead>
					<tr>
						<th width="20"><label class="checkbox m-n i-checks"><input	type="checkbox" class="form-control" id="maincheckbox"><i></i></label></th>
						<th>Test Name</th>
						<th class="th-sortable" data-toggle="class">Question Topic</th>
						<th>Access Type</th>
						<th>Status</th>
						<th width="15">Edit</th>
						<th style="display: none">Status</th>
						<th style="display: none">Edit</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		</section>
		<center>
			<a href="javascript:awadetailsdeleting()" class="btn btn-del">Delete
				selected</a>

		</center>
		
		</section>
		<!--sec close for data table-->
		<hr>
	</div>
	<!--col for DataTable close-->

</div>
    </form>              
            </section>
          </section>          
<!--           <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a> -->
        </section>       
 <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
</body>
</html>
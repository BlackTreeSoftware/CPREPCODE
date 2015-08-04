<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="assets/plug-ins/toastr-master/toastr.min.css" 	rel="stylesheet" type="text/css" />
<script src="assets/plug-ins/toastr-master/toastr.min.js" 	type="text/javascript"></script>
<link rel="stylesheet" href="assets/js/datatables/datatables.css" 	type="text/css" />
<script src="assets/plug-ins/ckeditor/ckeditor.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"positionClass" : "toast-top-right",
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};

		if ($('#qid').val() > 0) {
			
      $('#submit').text("Update");
			commondata();
		} else {
			$('#inactive').attr('checked', 'checked');
			$('#free').attr('checked', 'checked');
		}
	
	});

	function commondata()
	{

		if ($('#qstat').val() == 'ACTIVE')
			$('#active').attr('checked', 'checked');
		else
			$('#inactive').attr('checked', 'checked');
		
		   $('#cate').val($('#catid').val());
		
		   if ($('#qactype').val() == 'FREE')
			{
			
			$('#free').prop('checked', true);
			////alert("free");
			}
		   
		
	}
	
	
	function awaadding() {
		
		var	reWhiteSpace = new RegExp(/^\s+$/);
		
		if($('#cate').val()==-1){
			toastr.warning('Please Select Test Name');
			return false;
		}
		var isTrue=false;
		
		if(CKEDITOR.instances.editor1.getData() == ''|| CKEDITOR.instances.editor1.getData().length == 0)
  		{ 
  		toastr.error("Enter Question Type", "Error Info");
  		isTrue=false;
  		return false;
  		} else if(reWhiteSpace.test(CKEDITOR.instances.editor1.getData())){
  			toastr.error("Enter Question Type", "Error Info");
  			return false;
  		}  
		var qtype = CKEDITOR.instances['editor1'].getData();
	//	var qdirec = CKEDITOR.instances['editor2'].getData();
		if (qtype == '') {
			toastr.warning('Please Enter Question Type');
			$('#editor1').focus();
			return false;		} 
// 		else if (qdirec == '') {
// 			toastr.warning('Please Enter Question Directions');
// 			$('#editor2').focus();
// 		} 
		else {
			$('#questiontype').attr('value',CKEDITOR.instances['editor1'].getData());
// 			$('#questionDirec').attr('value',
// 					CKEDITOR.instances['editor2'].getData());
			if($('#free').is(':checked'))
				{
				$('#accessTypeId').val("FREE");
		
				$('#free').attr('checked', 'checked');	
			}
			else
				{
			
				$('#accessTypeId').val("PAID");
				}
					
			
			var formSerialize = $('#awaform').serialize();
			$.ajax({
				type : 'POST',
				url : 'AwaQuestionAdding.action',
				data : formSerialize,
				dataType : 'json',
				success : succesdata,
				errror : formerror

			});
		}
	}

	function succesdata(jsonData, status) {
		$('#submit').html('Submit');
		clean();
		if (jsonData.caughtexception) {
			toastr.error('Error' + jsonData.exception);
			
		}

		else {
			toastr.success(jsonData.message);
			//$('#awaform').attr('action', "AwaQuestionslist");
			//$('#awaform').submit();
			//dataclear();
		}

	}

	function formerror() {
		toastr.error(' FormSubmition Failed Error is: ' + errorIs.responseText);
	}
	
	function clean(){
		$('#cate').val('-1');		
		$('#free').attr('checked', 'checked');	
		$('#active').attr('checked', 'checked');
    	CKEDITOR.instances.editor1.setData('');
	}

	function dataclear() {

		
		if($('#qid').val() > 0)
			{
			////alert("in questions updation data");
			questionsUpdation($('#qid').val());
			//location.reload();
			}
		else{
			////alert("in Noraml reset data");
			$('#cate').val('-1');		
			$('#free').attr('checked', 'checked');	
			$('#active').attr('checked', 'checked');
	    	CKEDITOR.instances.editor1.setData('');
	    	//$('#cate').attr('value', -1);
	    	//$('#cate').val()==-1;
	    	//CKEDITOR.instances['editor1'].setData('');
	    //	CKEDITOR.instances.editor2.setData('');
	    	
		}
	
	}
	
	function cleardata()
	{
		////alert("jdsjhfsdjh");
		$('#awaform').attr('action', "Manage-AWA-Questions");
		$('#awaform').submit();
	}
	
	
	function questionsUpdation(quesid) {
		////alert("in questions updation"+quesid);
//		 		//alert(' Slected ID is: ' + quesid+""+$('#'+quesid+""+'direc').html()+""+$('#'+quesid+""+'testid').html()+""+$('#'+quesid+""+'topic').html()+""+$('#'+quesid+""+'access').html()+""+$('#'+quesid+""+'stat').html());
//		 		//alert("the values are"+c);
//		 		$.ajax({
//		 			tye : 'POST',
//		 			url : 'updateawa.action',
//		 			data : 'questionid=' + quesid,
//		 			dataType : 'json',
//		 			success : succesdata,
//		 			errror : formerror

//		 		});

		$('#questionId').val(quesid);
		$('#cate').val($('#catid').val());
		CKEDITOR.instances['editor1'].setData($('#qtype').val());
	//	CKEDITOR.instances['editor2'].setData($('#qdir').val());
		//alert("the accesstype data"+$('#qactype').val());
		if($('#qactype').val()=='FREE')
			{
			//alert("in qactype is free");
			$('#free').attr('checked', 'checked');	
			}
		else
			{
			$('#paid').attr('checked', 'checked');
			}
		if($('#qstat').val()=='ACTIVE')
			$('#active').attr('checked', 'checked');
		else
			$('#inactive').attr('checked', 'checked');
		
 	 


			}


	
</script>



</head>
<body>
	<section id="content"> <section class="vbox"> <section
		class="scrollable wrapper">

	<div class="row">
		<!--row start main-->
		<div class="col-lg-12">

			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="panel-title"> Add AWA</span>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-1" style="padding-top: 40px;">

							<form class="bs-example form-horizontal" id="awaform" method="post" >
							
							<input type="hidden" id="accessTypeId" name="uploadBO.access_type" > 
							<s:hidden name="uploadBO.question_id" id="questionId"></s:hidden>
								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Test
										Name</label>
									<div class="col-lg-3">
										<select name="uploadBO.category_id" id="cate"
											class="form-control m-b">
											<option value="-1">select</option>
											<option value="7">Issue</option>
											<option value="8">Argument</option>
										</select>


									</div>
								</div>

								<div class="form-group">
									<s:hidden name="uploadBO.question_type" id="questiontype" />
									<label class="col-lg-2 control-label">Question Type</label>
									<div class="col-lg-10">
										<textarea id="editor1" name="questiontypede" rows="10"
											cols="80">${uploadBO.question_type} </textarea>
										<%-- <script>
											CKEDITOR.replace('questiontypede');
										</script> --%>
									</div>
								</div>

								<%-- <div class="form-group">
									<s:hidden name="uploadBO.question_directions"
										id="questionDirec" />
									<label class="col-lg-2 control-label">Question
										Directions</label>
									<div class="col-lg-10">
										<textarea id="editor2" name="questiondirections" rows="10"
											cols="80">${uploadBO.question_directions}</textarea>
										<script>
											CKEDITOR
													.replace('questiondirections');
										</script>
									</div>
								</div> --%>
							
									<%-- <s:hidden id="sta" value="%{uploadBO.status}"></s:hidden> --%>
									<s:hidden id="qid" value="%{uploadBO.question_id}"></s:hidden>		
                                    <%--<s:property  value="%{uploadBO.access_type}"/>--%>
									<s:hidden id="qactype" value="%{uploadBO.access_type}"></s:hidden>
									<s:hidden id="catid" value="%{uploadBO.category_id}"></s:hidden>
									<s:hidden id="qstat" value="%{uploadBO.status}"></s:hidden>
<%-- 		                    	<s:hidden id="qdir" value="%{uploadBO.question_directions}"></s:hidden> --%>
									<s:hidden id="qtype" value="%{uploadBO.question_type}"></s:hidden>
									
								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Type</label></label>							

									<div class="radioo i-checks col-lg-2">
										<label> <input type="checkbox" id="free">
											 <i></i>
											Free
										</label>
									</div>
                                  <!-- <div class="radioo i-checks col-lg-2">
										<label > <input type="radio"
											name="uploadBO.access_type" id="paid" value="PAID">
											 <i></i>
											Paid
										</label>
									</div> -->
								</div>
								  	

								<div class="form-group">
									<label class="col-lg-2 col-lg-offset-4 control-label">Status</label></label>
									<div class="radioo i-checks col-lg-2">
										<label> <input type="radio"
											name="uploadBO.status" id="active" value="ACTIVE">
											 <i></i>
											Active
										</label>
									</div>

									<div class="radioo i-checks col-lg-2">
										<label> <input type="radio"
											name="uploadBO.status" id="inactive" value="INACTIVE">
											 <i></i>
											In active
										</label>
									</div>
								</div>


								<div class="form-group">
									<div
										class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
										<button type="button" class="btn btn-success" id="submit"
											onclick="awaadding()">Submit</button>
									</div>
									<div class="col-lg-offset-1 col-lg-1">
										<button type="button" class="btn btn-danger" onclick="dataclear();">Reset</button>
										
										
									</div>
									<!-- <div class="col-lg-offset-1 col-lg-1">
									<a href="Manage-AWA-Questions.action" class="auto btn btn-danger">Clear</a>
									
									</div> -->
									<div class="col-lg-offset-1 col-lg-1">
									<a href="AwaQuestionslist.action" class="auto btn btn-default">View List</a>
<!-- 									<button type="button" class="btn btn-success" onclick="cleardata()">Clear</button> -->
									</div>
								</div>
								<!--form group close-->

								
							</form>

						</div>
						<!--col for skill level close-->

					</div>
					<!--Inner row1 close -->

					<hr>
					<!--Inner row2 close -->

				</div>
				<!--panel body close-->
			</div>
			<!--panel head close-->

		</div>
		<!--div close-->
	</div>
	<!--row start main End--> </section> </section> <a href="#"
		class="hide nav-off-screen-block" data-toggle="class:nav-off-screen"
		data-target="#nav"></a> </section>


	<script src="assets/js/datatables/jquery.dataTables.min.js"></script>
	
<script type="text/javascript">
	CKEDITOR.replace('questiontypede',{
        entities: false,
        basicEntities: false,
        entities_greek: false,
        entities_latin: false,
        htmlDecodeOutput: true,
        forceSimpleAmpersand : true,
    });  
	</script>   
</body>
</html>
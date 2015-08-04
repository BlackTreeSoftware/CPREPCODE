function marking(){
	//alert("QUESTION ID"+$("#questionid").val());
	$('#_mark').toggleClass('active');
	if($("#_mark").hasClass('btn btn-default btn-test active')){
		
		$.ajax({
			type : 'POST',
			url : 'marking?flag='+'UNFLAG'+"&questionid="+$("#questionid").val(),
			dataType : 'json',
			
			success : function(data,textStatus,jqXHR) {
				
				if (data.message == "Operation Done Successfully") { 
					//toastr.success("Mail Sent Successfully");
				} else if (data.message == "Doesnt Have Mentor") {
					//toastr.error("Doesnt Have Mentor");
				}
			},
			error : function actionFailed(data,textStatus,errorThrown) {
				alert("error");
				//toastr.error("Error");
			}

		});

	}else if($("#_mark").hasClass('btn btn-default btn-test')){
		//alert("un checked");
		//alert("QUESTION ID"+$("#questionid").val());
		
		$.ajax({
			type : 'POST',
			url : 'marking?flag='+'FLAG'+"&questionid="+$("#questionid").val(),
			dataType : 'json',
			
			success : function(data,textStatus,jqXHR) {
				if (data.message == "Operation Done Successfully") {
					//alert("success");
					//toastr.success("Mail Sent Successfully");
				} else if (data.message == "Doesnt Have Mentor") {
					//toastr.error("Doesnt Have Mentor");
				}
			},
			error : function actionFailed(data,textStatus,errorThrown) {
				alert("error");
				//toastr.error("Error");
			}

		});
	}
}

function helping(){
	//alert("hai");
	$.ajax({
		  type : 'POST',
		  url  : 'helppage',			
		  		
			 success:function(data){ 
				// alert(data);
					//alert("Inserted successfully");
				 $('#overlayText').html(data);
				 $('#status_btn').text("Close");
				
				},
				error:function(e){
					alert("Error occured while process the request"+e);
				}
		 }); 
	
	
}

function quitTest(){
	//alert("quiting test");
	//alert("Time is\t"+$("#timeSpent").val());
	$.ajax({ 
  		type : "POST",
  		url :"quitTest?questionid="+$("#questionid").val(),
  		success : function(data){ 
			// alert(data);
			//alert("Inserted successfully");
		 $('#overlayText').html(data);
		 //$('#status_btn').text("Close");
		
		},
		error:function(e){
			alert("Error occured while process the request"+e);
		}
  	}); 
	
}

function showExitpage(){
	//alert("showing home page");
	//questionsForm
	$('#questionsForm').attr('action','homePage');
	$('#questionsForm').submit();
	
	//$('#row').html('<center><b>Start to take a new Test</b></center>');
}
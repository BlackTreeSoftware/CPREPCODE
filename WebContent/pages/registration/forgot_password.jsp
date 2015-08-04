<!DOCTYPE html>

<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>CrunchPrep GRE - Online GRE Prep with Practice Questions and Tests</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/icon.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/app.css" type="text/css" /> 
     <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
  <script src="assets/js/jquery.min.js"></script>
  <script type="text/javascript">
   $(document).ready(function(){
	   $("#succ").hide();
	   $("#error").hide();
	   $('#test').click(function(e){
			 
			 
			  var formData = $('#form1').serialize();
			  $.ajax({
				  type : 'POST',
				  url  : '/crunchprep/forgotpwdmail.action',
				  dataType:'json',
				  data : formData,
					
					 success:function(data){
						 $("#email").val("");
						//alert(data.message);
						 if(data.message=="Mail Sent Successfully"){
							 $("#succ").show();
							 setTimeout(function(){ $('#succ').fadeOut(),$("#error").fadeOut("slow"); }, 5000);
								
							}else {
								 $("#error").show();
								 setTimeout(function(){ $('#error').fadeOut(),$("#error").fadeOut("slow"); }, 5000);
								}
						
						},
						error:function(e){
							alert("error");
							
						}
				 }); 	 
			  return false;
		 
	  });
	   
	  
   });
   </script>
  
</head>
<body class="">

	
    <div class="container-fluid">
    <div class="row" style="background-color:#2C2D31; ">
    <div class="col-lg-1 col-lg-offset-2">
    <img src="assets/images/logo.png" style="padding:5px;" />
    </div>
    </div>
    </div>
	<!--Header closed--> 
    
  <section id="content" class="m-t-lg wrapper-md animated fadeInDown">
    <div class="container aside-xl">      
      <section class="m-b-lg">
        <header class="wrapper text-center" style="margin-top:100px;">
          <strong><h3>Forgot Password</h3></strong>
        </header>
        <div class="alert alert-danger" id="error">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <i class="fa fa-ok-sign"></i><strong>Enter Crunchprep Email</strong>
           </div>
            <div class="alert alert-success" id="succ">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <i class="fa fa-ok-sign"></i><strong>Email Has Sent Successfully</strong>
           </div> 
          <s:actionmessage cssStyle="list-style:none;color:red"/>
       
        <form   id="form1">
         
                   
        
          <div class="list-group">           
            <div class="list-group-item">
              <input type="email" placeholder="Email" id="email" name="registrationBO.email_id" class="form-control no-border" required="required">
            </div>            
          </div>
          <button type="submit" id="test" class="btn btn-lg btn-success btn-block">Submit</button>          
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>2014 &copy; CrunchPrep. A better way to study for the GRE.</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="assets/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  <!-- App -->
  <script src="assets/js/app.js"></script>  
  <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/js/app.plugin.js"></script>
</body>
</html>
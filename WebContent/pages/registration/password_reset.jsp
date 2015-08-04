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
	  //alert("hai");
	  $("#error").hide();
	  $("#mismatch").hide();
	  
  });
  
	  function passwordVerification(){
		  	//alert("hai");
		  	var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,16}/;
		   
		    var password=$('#pwd').val();
		    //alert('Password is: '+password);
		    if(!re.test(password)){
		    	 //$("#succ").html("Password Must have One Capital Letter, One Special Symbol and One Numeric with Atlease 8 characters in Length");
		    	  $("#error").show();
		    	document.getElementById("pwd").value="";		    	
		    	return false;
			  
		    }	else{
		    	 $("#error").hide();
		    }	  
	  }
	  
	  
 
  
  
  function cnfpwdValidation(){
	 //alert("hai");
	  var pwd = document.getElementById("pwd").value;
	  var cpwd = document.getElementById("cpwd").value;
	 // alert(pwd+"    "+cpwd)
	  if(pwd!=cpwd){
		  //$("#span").html("Password & Conform Password Doesn't Match");
		   $("#mismatch").show();
		 document.getElementById("pwd").value="";
		 document.getElementById("cpwd").value="";
		  return false;
		  
	  } else{
		  $("#mismatch").hide();
	  }
  }
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
          <strong><h3>Reset Password</h3></strong>
        </header>
        
        <div class="alert alert-success" id="error">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <i class="fa fa-ok-sign"></i><strong>Password Must have One Capital Letter, One Special Symbol and One Numeric with Atlease 8 characters in Length</strong>
         </div>
         <div class="alert alert-success" id="mismatch">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <i class="fa fa-ok-sign"></i><strong>Password & Conform Password Doesn't Match</strong>
         </div>
        
        <s:actionmessage/>
        
        <form action="resetpassword.action" method="post">
          <div class="list-group">           
            <div class="list-group-item list-group-item-margin" style="margin-bottom:0px">
           <s:hidden name="uid" value="%{#parameters['uid']}"/> 
             <%-- <input type="hidden" name="registrationBO.email_id" value="<%=request.getParameter("email_id") %>" id="" class="form-control no-border"/> --%>
              <input type="password" name="registrationBO.password" id="pwd" placeholder="New Password" class="form-control no-border" required="required" onchange="passwordVerification()">
            </div>
            <div class="list-group-item list-group-item-margin">
               <input type="password"  placeholder="Confirm Password" class="form-control no-border" id="cpwd" onchange="javascript:cnfpwdValidation()" required="required">
            </div>
            <span id="span"></span>
          </div>
          <button type="submit" class="btn btn-lg btn-success btn-block" >Reset</button>          
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
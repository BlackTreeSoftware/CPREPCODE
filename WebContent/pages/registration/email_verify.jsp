<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>CrunchPrep GRE - Online GRE Prep with Practice Questions and Tests</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <!---->
  <link rel="stylesheet" href="css/custom.css" type="text/css" />
  
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
  
  <style>
  a.social{color:#3b5999;}
  a.social:hover,social:active{color:#126da7;}
  
  a.gp{color:#dc4337;}
  a.gp:hover,gp:active{color:#ed5d52;}
  
  .icons ul li{ display:inline-block; margin-left:0px; padding-left:20px; padding-right:0px;}
  
 
   </style>
  
 <script>
 $(document).ready(function(){
	// $("#success").hide();
	 $("#link1").click(function(){
	//	 $("#success").show();
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

  <section id="content" class="m-t-lg wrapper-md animated fadeInUp" style="margin-top:150px"> 
 	 
     
    <div class="container aside-xxxl">
      <a class="navbar-brand block" href="index.html"></a>
      <section class="m-b-lg">
      
      <div class="panel panel-default">
      
      
      <div class="panel-body" style="padding:40px">
      
      <div class="row" >
      <%-- <div class="alert alert-success" id="success">
                    <button data-dismiss="alert" class="close" type="button">×</button>
                    <i class="fa fa-ok-sign"></i>
      <!-- A verification email has been sent to the email address . Please click on the link in the email to confirm your email address. -->
      <s:actionmessage/>
      </div> --%>
      
      <div class="col-lg-8 col-lg-offset-2">
      <p class="h1 text-success"> <i class="fa fa-envelope-o"></i> Verify your Mail</p><br>

      <p class="text"><strong class=" text-success-lt">Your email address is not confirmed yet. Please confirm your email address to continue using Crunchprep.</strong><br>

A Confirmation email has been sent to the email address <b><s:text name="#session.email_id"/>.</b> Please click on the link in the email to confirm your email address. If you do not receive the message within a few minutes, please check your bulk or spam email folders.<br><br>

In case the email did not arrive or you deleted it, <strong><a href="generate" id="link1" class="btn-link"><b>Click Here To Re-Send.</b></a></strong><br>
If you are having any other issues, please contact us at <br>
 +91 96404 44732.</p>
      </div>
      </div>      
      </div><!--row close-->
      
      </div>
      
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder">
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
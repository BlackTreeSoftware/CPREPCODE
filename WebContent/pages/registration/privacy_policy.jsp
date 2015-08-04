<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <!---->
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />   
  <link rel="stylesheet" href="assets/css/app.css" type="text/css" />
</head>
<body class="">


<section id="content" class="m-t-lg wrapper-md animated fadeInUp" style="margin-top:150px"> 
 	 
     
    <div class="container aside-xxxl">
      <a class="navbar-brand block" href="index.html"></a>
      <section class="m-b-lg">
           
      <div class="panel panel-default">
      
    
      <div class="panel-body" style="padding:40px">
      
      <div class="row" >
      <div class="col-lg-8 col-lg-offset-2" style="background-color: black">
    <img src="assets/images/logo.png" style="padding:5px;background-color: black" />
    </div> 
    <div class="col-lg-8"></div> <br>
      <%-- <div class="alert alert-success" id="success">
                    <button data-dismiss="alert" class="close" type="button">×</button>
                    <i class="fa fa-ok-sign"></i>
      <!-- A verification email has been sent to the email address . Please click on the link in the email to confirm your email address. -->
      <s:actionmessage/>
      </div> --%>
     
      <div class="col-lg-8 col-lg-offset-2">
      <p class="h1" style="padding-top:2%;font-family:Segoe UI"> Privacy Policies </p><br>

      <p class="text" style="font-family:Segoe UI"><s:property escape="false" value="privacyPolicyData"/> </p>
      </div>
      </div>      
      </div><!--row close-->
      
      </div>
      
      </section>
    </div>
  </section>


<%-- <div class="container-fluid">
    <div class="row" style="background-color:#2C2D31; ">
    <div class="col-lg-1 col-lg-offset-2">
    <img src="assets/images/logo.png" style="padding:5px;" />
    </div>
    </div>
    </div>
 
<s:property escape="false" value="privacyPolicyData"/> --%>
</body>
</html>
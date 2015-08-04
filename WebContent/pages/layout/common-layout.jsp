<!DOCTYPE html>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>Crunchprep | GRE Web Application</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <!--Custom css links-->

  <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
  
  <link rel="stylesheet" href="assets/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/icon.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/app.css" type="text/css" /> 
  
  
    <!--[if lt IE 9]>
    <script src="assets/js/ie/html5shiv.js"></script>
    <script src="assets/js/ie/respond.min.js"></script>
    <script src="assets/js/ie/excanvas.js"></script>
  <![endif]-->
   
</head>
<body class="">
  <script src="assets/js/jquery.min.js"></script>
  <section class="vbox">
   <header class="bg-white header header-md navbar navbar-fixed-top-xs box-shadow" id="tour_header">
   <tiles:insertAttribute name="header"></tiles:insertAttribute>
   </header>
    <section>
      <section class="hbox stretch">
       <tiles:insertAttribute name="menu"></tiles:insertAttribute>
       <tiles:insertAttribute name="body"></tiles:insertAttribute>
        
      </section>
    </section>
  </section>
  
  <script src="assets/js/bootstrap.js"></script>
 <script src="assets/js/app.js"></script>
  <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="assets/js/app.plugin.js"></script>
  <script type="text/javascript" src="assets/plug-ins/overlay/jquery.simplemodal.js"></script>
  <script type="text/javascript" src="assets/plug-ins/overlay/confirm.js"></script>

</body>
</html>
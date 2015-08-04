<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>Crunchprep | GRE Web Application</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/icon.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/app.css" type="text/css" />
 
  <link rel="stylesheet" href="assets/js/datepicker/datepicker.css" type="text/css" />
 
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
  
  <script type="text/javascript">
  	
  		function finishButton(){
  			
  			//alert("The Finish Alert");
  		}
  </script>
</head>
<body class="" style="overflow-y:auto">
	
    <div class="container-fluid">
    <div class="row" style="background-color:#2C2D31; ">
    <div class="col-lg-1 col-lg-offset-2">
    <img src="assets/images/logo.png" style="padding:5px;" />
    </div>
    </div>
    </div>
	<!--Header closed--> 
    
  <section id="content" class="m-t-lg wrapper-lg animated fadeInDown">
  	
    
    
    <div class="container aside-xxl">      
      <section class="m-b-lg">
        <header class="wrapper text-center">
          <strong><h3> Enter Personal Details </h3></strong>
        </header>
    
    		<!------->
    
    		  <div class="row">
                <div class="col-sm-12">
                  <form id="wizardform" method="get" action="">
                    <div class="panel panel-default">
                      <div class="panel-heading">
                        <ul class="nav nav-tabs nav-justified font-bold">
                          <li><a href="#step1" data-toggle="tab">Step 1</a></li>
                          <li><a href="#step2" data-toggle="tab">Step 2</a></li>
                          <!--<li><a href="#step3" data-toggle="tab">Step 3</a></li>-->
                        </ul>
                      </div>
                      <div class="panel-body">
                        <p>This twitter bootstrap plugin builds a wizard out of a formatter tabbable structure. It allows to build a wizard functionality using buttons to go through the different wizard steps and using events allows to hook into each step individually.</p>
                        <div class="line line-lg"></div>
                        <h4>Validate Form</h4>
                        <div class="progress progress-xs m-t-md">
                          <div class="progress-bar bg-success"></div>
                        </div>
                        <div class="tab-content">
                          <div class="tab-pane" id="step1">                            
                           
                           
                               <div class="list-group">
                               
                                <div class="list-group-item list-group-item-multi">
                                  <input type="text" class="form-control no-border" data-trigger="change" data-required="true" data-type="alpha" placeholder="First Name">
                                  
                                </div>

                                <div class="list-group-item list-group-item-multi">
                                  <input type="text" class="form-control no-border" data-trigger="change" data-required="true" data-type="alpha" placeholder="Last Name">
                                 
                                </div>                                  
            
                                <div class="list-group-item list-group-item-multi">
                                   <input class="datepicker-input no-border form-control" size="16" type="text" value="Test Date" data-date-format="yyyy-mm-dd" maxDate:"0D"
                                   data-trigger="change" data-required="true" data-type="dateIso">
                                </div>
            
                                <div class="list-group-item list-group-item-multi">
                                  <input type="number" class="form-control no-border" data-trigger="change" data-required="true" data-type="number" 
                                  placeholder="Math Target Score" min="140" max="170">
    
                                </div>
                                
                                 <div class="list-group-item list-group-item-multi">
                                  <input type="number" class="form-control no-border" data-trigger="change" data-required="true" data-type="number" 
                                  placeholder="Verbal Target Score" min="140" max="170">
    
                                </div>
            
                                <div class="list-group-item list-group-item-multi">
                                  <input type="number" class="form-control no-border" data-trigger="change" data-required="true" data-type="number" placeholder="Actual Score"
                                  min="240" max="360">

                                </div>
            
                                <div class="list-group-item list-group-item-multi">
                                	
                                   <input type="text" class="form-control no-border" data-trigger="change" data-required="true" data-type="number" placeholder="Phone No"> 

                                </div>
                                
                                 <div class="list-group-item list-group-item-multi">
                                	
                                    <input type="text" class="form-control no-border" data-trigger="change" data-required="true" data-type="alpha" placeholder="City/Town">

                                </div>
            
            
            					
                               
            					</div><!--list group close-->
                               
                           
                           
                           
                          </div>
                          <!--step one close--->
                          
                          <div class="tab-pane" id="step2">
                           	
                            <div class="list-group">
                           
                            <div class="list-group-item list-group-item-margin">
                              <label>Select Radio</label>   
                              <div class="radio i-checks">
                                  <label>
                                    <input type="radio" name="a" value="option1">
                                    <i></i>
                                    Option one
                                  </label>
                                </div>
                                <div class="radio i-checks">
                                  <label>
                                    <input type="radio" name="a" value="option2" checked>
                                    <i></i>
                                    Option two checked
                                  </label>
                                </div>
       	
            
           					 </div><!--Radio button list group close-->
                           
                           
                            <div class="list-group-item list-group-item-margin">
                            <div>Are you studying for any of these other tests?</div>
                            <div class="checkbox i-checks">
                                          <label>
                                            <input type="checkbox" value="">
                                            <i></i>
                                            Option one
                                          </label>
                                        </div>
                                        <div class="checkbox i-checks">
                                          <label>
                                            <input type="checkbox" checked value="">
                                            <i></i>
                                            Option two checked
                                          </label>
                                        </div>
                            </div><!--Check Box button list group close-->
                           
                           	</div><!--list group close-->
                           
                          </div>
                          
                          <!--step two close--->
                          
                          <div class="tab-pane" id="step3">This is step 3</div>
                          <ul class="pager wizard m-b-sm">
                            <li class="previous first" style="display:none;"><a href="#">First</a></li>
                            <li class="previous"><a href="#">Previous</a></li>
                            <li class="next last" style="display:none;"><a href="#">Last</a></li>
                            <li class="next"><a href="#">Next</a></li>
                          </ul>
                          </div>
                          <!--step three close--->
                        
                      </div>
                    </div>
                  </form>
                </div>
                </div>
                
                <!--row close-->
    
    
    		<!---------->
       </section><!-- section m-b-lg close -->
   </div>

    
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>Crunchprep &copy; 2014</small>
      </p>
    </div>
  </footer>

  <!-- / footer -->
  <script src="assets/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  <!-- App -->
  <script src="assets/js/datepicker/bootstrap-datepicker.js"></script>
  <script src="assets/js/app.js"></script>  
  <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="assets/js/app.plugin.js"></script>
  
  <script src="assets/js/parsley/parsley.min.js"></script>
  <script src="assets/js/wizard/jquery.bootstrap.wizard.js"></script>
  <script src="assets/js/wizard/demo.js"></script>

  
</body>
</html>
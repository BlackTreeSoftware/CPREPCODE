<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title> Crunchprep | GRE Web Application</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/icon.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/app.css" type="text/css" /> 
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css" /> 
  
  <link rel="stylesheet" href="assets/js/chosen/chosen.css" type="text/css" />
  
  <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css"/>
  
	
     <!--ck editor-->
  <script src="assets/plugins/ckeditor/ckeditor.js"></script>
  <script src="assets/ckfinder/ckfinder.js"></script> 

    <!--[if lt IE 9]>
    <script src="assets/js/ie/html5shiv.js"></script>
    <script src="assets/js/ie/respond.min.js"></script>
    <script src="assets/js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body class="">
  <section class="vbox">
    <header class="bg-white header header-md navbar navbar-fixed-top-xs box-shadow">
      <div class="navbar-header aside-md dk">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen" data-target="#nav">
          <i class="fa fa-bars"></i>
        </a>
        <a href="assets/index.html" class="navbar-brand">
          <img src="assets/images/logo-blck.png" class="m-r-sm" alt="scale">
         
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
          <i class="fa fa-cog"></i>
        </a>
      </div>
      <ul class="nav navbar-nav hidden-xs">
        <li class="dropdown">
          <a href="assets/#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="i i-grid"></i>
          </a>
          <section class="dropdown-menu aside-lg bg-white on animated fadeInLeft">
            <div class="row m-l-none m-r-none m-t m-b text-center">
              <div class="col-xs-4">
                <div class="padder-v">
                  <a href="assets/#">
                    <span class="m-b-xs block">
                      <i class="i i-mail i-2x text-primary-lt"></i>
                    </span>
                    <small class="text-muted">Mailbox</small>
                  </a>
                </div>
              </div>
              <div class="col-xs-4">
                <div class="padder-v">
                  <a href="assets/#">
                    <span class="m-b-xs block">
                      <i class="i i-calendar i-2x text-danger-lt"></i>
                    </span>
                    <small class="text-muted">Calendar</small>
                  </a>
                </div>
              </div>
              <div class="col-xs-4">
                <div class="padder-v">
                  <a href="assets/#">
                    <span class="m-b-xs block">
                      <i class="i i-map i-2x text-success-lt"></i>
                    </span>
                    <small class="text-muted">Map</small>
                  </a>
                </div>
              </div>
              <div class="col-xs-4">
                <div class="padder-v">
                  <a href="assets/#">
                    <span class="m-b-xs block">
                      <i class="i i-paperplane i-2x text-info-lt"></i>
                    </span>
                    <small class="text-muted">Trainning</small>
                  </a>
                </div>
              </div>
              <div class="col-xs-4">
                <div class="padder-v">
                  <a href="assets/#">
                    <span class="m-b-xs block">
                      <i class="i i-images i-2x text-muted"></i>
                    </span>
                    <small class="text-muted">Photos</small>
                  </a>
                </div>
              </div>
              <div class="col-xs-4">
                <div class="padder-v">
                  <a href="assets/#">
                    <span class="m-b-xs block">
                      <i class="i i-clock i-2x text-warning-lter"></i>
                    </span>
                    <small class="text-muted">Timeline</small>
                  </a>
                </div>
              </div>
            </div>
          </section>
        </li>
      </ul>
      <form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search">
        <div class="form-group">
          <div class="input-group">
            <span class="input-group-btn">
              <button type="submit" class="btn btn-sm bg-white b-white btn-icon"><i class="fa fa-search"></i></button>
            </span>
            <input type="text" class="form-control input-sm no-border" placeholder="Search apps, projects...">            
          </div>
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user user">
        <li class="hidden-xs">
          <a href="assets/#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="i i-chat3"></i>
            <span class="badge badge-sm up bg-danger count">2</span>
          </a>
          <section class="dropdown-menu aside-xl animated flipInY">
            <section class="panel bg-white">
              <div class="panel-heading b-light bg-light">
                <strong>You have <span class="count">2</span> notifications</strong>
              </div>
              <div class="list-group list-group-alt">
                <a href="assets/#" class="media list-group-item">
                  <span class="pull-left thumb-sm">
                    <img src="assets/images/a0.png" alt="..." class="img-circle">
                  </span>
                  <span class="media-body block m-b-none">
                    Use awesome animate.css<br>
                    <small class="text-muted">10 minutes ago</small>
                  </span>
                </a>
                <a href="assets/#" class="media list-group-item">
                  <span class="media-body block m-b-none">
                    1.0 initial released<br>
                    <small class="text-muted">1 hour ago</small>
                  </span>
                </a>
              </div>
              <div class="panel-footer text-sm">
                <a href="assets/#" class="pull-right"><i class="fa fa-cog"></i></a>
                <a href="assets/#notes" data-toggle="class:show animated fadeInRight">See all the notifications</a>
              </div>
            </section>
          </section>
        </li>
        <li class="dropdown">
          <a href="assets/#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="thumb-sm avatar pull-left">
              <img src="assets/images/a0.png" alt="...">
            </span>
            John.Smith <b class="caret"></b>
          </a>
          <ul class="dropdown-menu animated fadeInRight">            
            <li>
              <span class="arrow top"></span>
              <a href="assets/#">Settings</a>
            </li>
            <li>
              <a href="assets/profile.html">Profile</a>
            </li>
            <li>
              <a href="assets/#">
                <span class="badge bg-danger pull-right">3</span>
                Notifications
              </a>
            </li>
            <li>
              <a href="assets/docs.html">Help</a>
            </li>
            <li class="divider"></li>
            <li>
              <a href="assets/modal.lockme.html" data-toggle="ajaxModal" >Logout</a>
            </li>
          </ul>
        </li>
      </ul>      
    </header>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black nav-xs hidden-print" id="nav">          
          <section class="vbox">
            <section class="w-f scrollable">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railopacity="0.2">
               

                <!-- nav -->                 
                <nav class="nav-primary hidden-xs">
                  <div class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">Start</div>
                  <ul class="nav nav-main" data-ride="collapse">
                    <li >
                      <a href="assets/index.html" class="auto">
                       <i class="#"> <img src="assets/images/icons/home.png" /></i>
                        <span class="font-bold">Home</span>
                      </a>
                    </li>
                    <li >
                      <a href="assets/#" class="auto">
                                               
                        <i class=""><img src="assets/images/icons/lesson.png" /></i>
                       
                        <span class="font-bold">Lessons</span>
                      </a>
                     
                    </li>
                    <li >
                      <a href="assets/#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class=""><img src="assets/images/icons/practice.png" /></i>
                        </i>
                        <span class="font-bold">Practice</span>
                      </a>                     
                    </li>
                    <li >
                      <a href="assets/#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class=""><img src="assets/images/icons/flashcards.png" /></i>
                        </i>
                        <span class="font-bold">Flash Cards</span>
                      </a>                     
                    </li>
                    <li >
                      <a href="assets/#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class=""><img src="assets/images/icons/test.png" /></i>
                        </i>
                        <span class="font-bold">Take a test</span>
                      </a>                   
                    </li>
                    
                     <li >
                      <a href="assets/#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class=""><img src="assets/images/icons/note.png" /></i>
                        </i>
                        <span class="font-bold">Notes</span>
                      </a>                     
                    </li>    
                    
                     <li >
                      <a href="assets/#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class=""><img src="assets/images/icons/bookmark.png" /></i>
                        </i>
                        <span class="font-bold">Bookmark</span>
                      </a>                     
                    </li> 
                    
                     <li >
                      <a href="assets/#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class=""><img src="assets/images/icons/reports.png" /></i>
                        </i>
                        <span class="font-bold">Reports</span>
                      </a>                     
                    </li>                                             
                  </ul>                                   
                </nav>
                <!-- / nav -->
              </div>
            </section>
            
            <footer class="footer hidden-xs no-padder text-center-nav-xs">
             <!--<a href="assets/modal.lockme.html" data-toggle="ajaxModal" class="btn btn-icon icon-muted btn-inactive pull-right m-l-xs m-r-xs hidden-nav-xs">
                <i class="i i-logout"></i>
              </a>-->
             <!-- <a href="assets/#nav" data-toggle="class:nav-xs" class="btn btn-icon icon-muted btn-inactive m-l-xs m-r-xs">
                <i class="i i-circleleft text"></i>
                <i class="i i-circleright text-active"></i>
              </a>-->
            </footer>
          </section>
        </aside>
        <!-- /.aside -->
        
        <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
             <div class="row"><!--row start main-->
             <div class="col-lg-12">
             
             <div class="panel panel-default">
             <div class="panel-heading"> <span class="panel-title">Text Completion</span>  </div>
             <div class="panel-body">
             <div class="row">
             <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">
             
              <form class="bs-example form-horizontal">
                       
                         
                               <div class="form-group">
                                <label class="col-lg-2  col-lg-offset-4  control-label">Type</label>
                               
                               <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="b" value="option1" checked>
                                    <i></i>
                                   Free
                                  </label>
                                </div>
                                
                                <div class="radioo i-checks col-lg-2">
                                  <label>
                                    <input type="radio" name="b" value="option2" >
                                    <i></i>
                                    Referral
                                  </label>
                                </div>
                                
                               </div><!--form group close-->     
                       
                            
                             <div class="form-group">
                                 <label class="col-lg-2 col-lg-offset-4 control-label">Test Name</label>
                                <div class="col-lg-5">
                                <select name="account" class="form-control m-b">
                                  <option>Quantitative</option>
                                  <option>Verbal</option>
                                 </select>
                                </div>
                             </div>
                                
                                
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Question title</label>
                                  <div class="col-lg-5">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                             </div>    
                             
                             
                             <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
                                  <div class="col-lg-3">
                                    <textarea cols="50" rows="4" style="max-width:450px"></textarea>                          
                                  </div>
                             </div>    
                                  
                       	
                          	<div class="form-group">
                          				<label class="col-lg-2 control-label">Question</label>
                          				<div class="col-lg-10">
                            				<textarea id="editor1" name="questiontitle" rows="10" cols="80"> </textarea>
											<script> CKEDITOR.replace('questiontitle' ) </script>
                          				</div>
                        	</div>
                       
                       
                       
                       		<div class="form-group" id="single">
								
                                <div class="row">
                                
                                 <div class="col-lg-6 ">    
                                 
                                 <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                                  
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" name="b" value="option1" checked>
                                        <i></i>
                                       
                                      </label>
                                    </div>
                                    
                                  </div><!--row close for internal choices 1-->

                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                                   
                                   <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" name="b" value="option2" >
                                        <i></i>
                                       
                                      </label>
                                    </div>
                                  
                                  </div><!--row close for internal choices 2-->
	
    								<br>
                                  
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                                
                                 <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" name="b" value="option3" >
                                        <i></i>                                       
                                      </label>
                                    </div>
                                
                                  </div><!--row close for internal choices 3-->
    								
                                    <br>
                              
                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                                 
                                 <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" name="b" value="option4" >
                                        <i></i>
                                       
                                      </label>
                                 </div>
                                    
                                  </div><!--row close for internal choices 4-->
                                  
                                  <br>

                                  <div class="row"><!--row for internal choices -->                          
                                  <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                                  
                                  <div class="radi i-checks col-lg-1">
                                      <label>
                                        <input type="radio" name="b" value="option5" >
                                        <i></i>                                       
                                      </label>
                                  </div>
                                    
                                  </div><!--row close for internal choices 5-->
                                  
                                  
                                  </div><!--Col for choices close-->
                                  
                                  <div class="col-lg-6">
                                    <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-1 control-label">Answer 1</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" readonly placeholder="">                            
                                  </div>
                             		</div> 
                             
                                  </div>
                                                                   
                                  </div>
                                  
                                 </div><!--Form group close for choices-->

							
                       
                       		
                           
                       		
                            <br>
            				
                           <div class="form-group">
                              <label class="col-lg-2 col-lg-offset-4 control-label">Skills</label>
                              <div class="col-lg-3">
                                <select style="width:230px" multiple class="chosen-select">
                                  <option value="AK">Alaska</option>
                                  <option value="HI">Hawaii</option>
                                  <option value="CA">California</option>
                                  <option value="NV">Nevada</option>
                                  <option value="OR">Oregon</option>
                                  <option value="WA">Washington</option>
                                </select>
                              </div>
                              </div>
                             
                             
                             <div class="form-group">

                                 <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level</label>
                                    <div class="col-lg-3">
                                    <select name="account" class="form-control m-b">
                                      <option>Quantitative</option>
                                      <option>Verbal</option>
                                     </select>
                                    </div>
 
                             </div>
                             
                         
                            
                       		<div class="form-group">
                          				<label class="col-lg-2 control-label">Solution Text</label>
                          				<div class="col-lg-10">
                            				<textarea id="editor1" name="questiondesc" rows="10" cols="80"> </textarea>
											<script> CKEDITOR.replace('questiondesc' ) </script>
                          				</div>
                        	</div>
                       
                       		
                            <div class="form-group">
                                  <label class="col-lg-2 col-lg-offset-2 control-label">Solution Video</label>
                                  <div class="col-lg-6">
                                    <input type="text" class="form-control" placeholder="">                            
                                  </div>
                             </div> 
                       
                       
                        <div class="form-group">
                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label></label>
                        
                         <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" name="b" value="option1" checked>
                            <i></i>
                           Active
                          </label>
                        </div>
                        
                       <div class="radioo i-checks col-lg-2">
                          <label>
                            <input type="radio" name="b" value="option1" checked>
                            <i></i>
                           In Active
                          </label>
                        </div>
                        </div>
                                                             
                       
                                              
                        <div class="form-group">
                          <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                            <button type="submit" class="btn btn-success">Submit</button>
                          </div>
                        <div class="col-lg-offset-1 col-lg-1">
                            <button type="submit" class="btn btn-danger">Reset</button>
                          </div>                                                
                        </div><!--form group close-->
        	  </form>
              
        
             </div><!--col for skill level close-->
             
             </div><!--Inner row1 close -->
             
          
             
             
             
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
             </div><!--div close-->
             </div><!--row start main End-->
            
            
            
            </section>
          </section>
          <a href="assets/#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
      </section>
    </section>
  </section>
  <script src="assets/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  <!-- App -->
  <script src="assets/js/app.js"></script>  
  <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="assets/js/app.plugin.js"></script>
  
  <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
  <script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
  <script src="assets/js/datatables/demo.js"></script>
  <script src="assets/js/chosen/chosen.jquery.min.js"></script>
  
 
</body>
 <script type="text/javascript">
	$(document).ready(function () {    
	$('label.tree-toggler').click(function () {
		$(this).parent().children('ul.tree').toggle(300);
	});
});
 </script>
</html>
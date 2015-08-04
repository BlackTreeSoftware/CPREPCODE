<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>CrunchPrep GRE - Online GRE Prep with Practice Questions and Tests</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/icon.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/font.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/app.css" type="text/css" />  
    <!--[if lt IE 9]>
    <script src="assetsjs/ie/html5shiv.js"></script>
    <script src="assetsjs/ie/respond.min.js"></script>
    <script src="assetsjs/ie/excanvas.js"></script>
  <![endif]-->
  
  <style>
 
  a.social{color:#3b5999;}
  a.social:hover,social:active{color:#126da7;}
  
  a.gp{color:#dc4337;}
  a.gp:hover,gp:active{color:#ed5d52;}
  
  .icons ul li{ display:inline-block; margin-left:0px; padding-left:20px; padding-right:0px;}
  
 
   
 
   </style>
   <script src="//code.jquery.com/jquery-1.10.2.js"></script>
   
   <!-- GMAIL LOGIN *********************************** -->
   
   
<!--    Key Notes for the Editors from Devloper side :  satya
if the Application Domain is change/Page is Changed then do Folloing Steps
1.Go to App page in console.devloper.google -> Credincials -> 
    Redirect URIs: http://localhost:8080/crunchprep/signin(to changed URL)
	Javascript Origins: http://localhost:8080/(to changed URL)
     
2.In our Page chane 'REDIRECT: to changed url'
that It do not to change Entaire code  -->
    <script>
        var OAUTHURL    =   'https://accounts.google.com/o/oauth2/auth?';
        var VALIDURL    =   'https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=';
        var SCOPE       =   'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email';
        var CLIENTID    =   '946731390813-dn5gi0sica7rrtfovh4uh4t1tjjb1a9d.apps.googleusercontent.com';
        var REDIRECT    =   'http://<%=request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/signin';
        var LOGOUT      =   'http://accounts.google.com/Logout';
        var TYPE        =   'token';
        var _url        =   OAUTHURL + 'scope=' + SCOPE + '&client_id=' + CLIENTID + '&redirect_uri=' + REDIRECT + '&response_type=' + TYPE;
        var acToken;
        var tokenType;
        var expiresIn;
        var user;
        var loggedIn    =   false;

    
        function gmaillogin() {
        	
            var win         =   window.open(_url, "windowname1", 'width=800, height=600'); 
            var pollTimer   =   window.setInterval(function() { 
                try {
                    console.log(win.document.URL);
                    if (win.document.URL.indexOf(REDIRECT) != -1) {
                        window.clearInterval(pollTimer);
                        var url =   win.document.URL;
                        acToken =   gup(url, 'access_token');
                        tokenType = gup(url, 'token_type');
                        expiresIn = gup(url, 'expires_in');
                        win.close();
            
                        getGmailInfo(acToken);
                     
                    }
                } catch(e) {
                }
            }, 500);
            
        }
        function getGmailInfo(acToken) {
        	var link = 'https://www.googleapis.com/oauth2/v1/userinfo?access_token=' + acToken;
        	//alert('link : '+link)
            $.ajax({
                url: link,
                data: null,
                success: function(resp) {
                    user    =   resp;
                    console.log(user);
					 var mail = user.email;
					 var name =user.name;
					 var pic = user.picture;
					 var gender = user.gender;
					 
					 //alert(mail);
					 $.ajax({

						 url:"socialRegister.action?registrationBo.email_id="+mail+"&name="+name,
						success:function(data){
								$("#main-div").html(data);	
								$("#span").html("You are already Registred with Us! Sign in Here!");
							},
							error:function(error){
								 alert(error.status);
							}
						});
					  
					 
					/*  $('#email_id').val(mail);
			          $("#password").attr("placeholder", "Choose password");
			          $("#check").val('social');
			          $("#signinform").submit(); */
					// alert('Email : '+mail+'Name : '+mail+'PIC : '+pic+'Gender : '+gender);	 
                },
                dataType: "jsonp"
            });
        }

        //credits: http://www.netlobo.com/url_query_string_javascript.html
        function gup(url, name) {
            name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
            var regexS = "[\\#&]"+name+"=([^&#]*)";
            var regex = new RegExp( regexS );
            var results = regex.exec( url );
            if( results == null )
                return "";
            else
                return results[1];
        }
 
        
        
        
        
        
       //***************GMAIL LOGIN END *************************// 
       
       
       
       
        
//********************** FB Start***********************

/* Key Notes for the Editors from Devloper side :  satya
if the Application Domain is change/Page is Changed then do Folloing Steps
1.Go to App page in Facebook --> Settings --> 
     Appdomain			 :  Changed Domain Name
     Wensite Url&Site Url : Changed result page url
     
2.In our Page chane 'channelUrl: to changed url'
that It do not to change Entaire code */

 window.fbAsyncInit = function() {
	 // alert('fb init');
    FB.init({
      appId      : '778853572132659', // App ID
      channelUrl : 'http://<%=request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/signin', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });
    
    
 FB.Event.subscribe('auth.authResponseChange', function(response) 
 {
   if (response.status === 'connected') 
   {
    document.getElementById("message").innerHTML +=  "<br>Connected to Facebook";
    //SUCCESS
    
   }  
 else if (response.status === 'not_authorized') 
    {
     document.getElementById("message").innerHTML +=  "<br>Failed to Connect";

  //FAILED
    } else 
    {
     document.getElementById("message").innerHTML +=  "<br>Logged Out";

     //UNKNOWN ERROR
    }
 }); 
 
    };
    
    
    
    function fblogin()
 {
  FB.login(function(response) {
     if (response.authResponse) 
     {
       getUserInfo();
       
     } else 
     {
         console.log('User cancelled login or did not fully authorize.');
      }
   },{scope: 'email,user_photos,user_videos,read_friendlists'});
 
 
 }

  function getUserInfo() {   

   FB.api('/me', function(response) {
          var mail = response.email;
          var name = response.name;//picture     
          //alert(name);
          getPhoto();
         
         
			 $.ajax({
					
					url:"socialRegister.action?registrationBo.email_id="+mail+"&name="+name,
					success:function(data){
						//alert(data); 
						$("#main-div").html(data);
						$("#span").html("You are already Registred with Us! Sign in Here!");
						
						 
					},
					error:function(error){
						 alert(error.status);
					}
				});
          
    });
    }
  
 function getPhoto()
 {
   FB.api('/me/picture?type=normal', function(response) {

    var pic =response.data.url;
  
           
    });
 
 }
  // Load the SDK asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));

  //********************** FB END*********************** 
  
  
  



        

    </script>
    
    
    
    <script type="text/javascript">
    
    $(document).ready(function(){
    	$("#success-div").hide();
    	$("#error-div").hide();
    	
    	$( "#password" ).keyup(function( event ) {
    		
    		  if ( event.which == 13 ) {
    			  if($("#password").val()=='')
      			{
    			 // $("#span").html('Please Enter Password ');
    			   $("#span").fadeIn();
    			   $('#span').html("Please Enter Password");
    			    $("#span").css("color", "red");
    			    setTimeout(function(){ $('#span').fadeOut(),$("#span").fadeOut("slow"); }, 5000);
      			  return false;
      			}else{
    			  loginForm();
      			}
    		  }
    	});
    	 $("#succ").hide();
    	
    	if($("#message").val()=="Password Has Reset Successfully"){
    		//alert("hai show     "+$("#message").val());
    		 $("#succ").show();
    		  
    	}else{
    		//alert("hai");
    		 $("#succ").hide();
    	}
    	
    	
    	
    });
    function loginForm(){
    
       var emailId = $('#email').val();
       var password= $('#password').val();
       $('#emailId1').attr('value',emailId);
       $('#password1').attr('value',password);
       var formData = $('#formId').serialize();
       
       
       
       if(emailId.length<1)
		  {
    	  // $("#span").html('Please Enter your Email Id');
    	   $("#error-div").show();
		   $("#error").text("Please Enter your Email Id!");
		   // $("#span").css("color", "red");
		   //setTimeout(function(){ $('#error-div').fadeOut(),$("#error-div").fadeOut("slow"); }, 2000);
		 
		
		  }
	  else if(password.length<1)
		  {
		 // $("#span").html('Please Enter your password');
		  /* $("#span").fadeIn();
		   $('#span').html("Please Enter your password");
		    $("#span").css("color", "red");
		   setTimeout(function(){ $('#span').fadeOut(),$("#span").fadeOut("slow"); }, 5000); */
		   $("#error-div").show();
		   $("#error").text("Please Enter your password !");
		   // $("#span").css("color", "red");
		   //setTimeout(function(){ $('#error-div').fadeOut(),$("#error-div").fadeOut("slow"); }, 2000);
		 
			  return false;
		  }
	  else{
       
       $.ajax({
    	   
    	      type :'POST',
    	      
    	      url : 'loginuser.action',
    	      
    	      data : 'userBO.email_id='+emailId+'&userBO.password='+password,
    	      
    	      dataType : 'json',
    	      
    	      success : loginSuccess,
    	      
    	      error : errorInfo
    	   
       });
    	   
    	   
	  }   
    }
    
       
      function loginSuccess(result,status){
    	  
       if(status=='success')
    	   {
    	     if(result.validEmail==true)
    	    	 {
    	    	if(result.accountStatus=='Active')
    	    		{
    	    		 if(result.SubscriptionStatus=='Active'){
    	    			 $('#submitForm').trigger('click');
    	    	    	  return true;
    	    		 }else{
    	    			 $('#submitForm').trigger('click');
    	    		 } 	    		 
    	    	}
    	    	else{
    	    		 $('#submitForm').trigger('click');
    	    	}
    	    	 }
    	     else {
    	    	// $("#span").html("Invalid Email or Password");
    	    	 //$("#span").fadeIn();
    			 //  $('#span').html("Invalid Email or Password");
    			   $("#error-div").show();
    			   $("#error").text("Invalid Email or Password !");
    			   // $("#span").css("color", "red");
    			  //setTimeout(function(){ $('#error-div').fadeOut("slow"),$("#error-div").fadeOut("slow"); }, 3000);
    	     }
    	    
    	   }
      }
    
      function errorInfo(errorIs){
    	  $("#span").html(' Login verification Failed due to \n\t'+errorIs.responseText);
    	  return false;
    	  
      }
   
    </script>
  
</head>
<body>
<div id="main-div">
<div class="container-fluid">
    <div class="row" style="background-color:#2C2D31; ">
    <div class="col-lg-1 col-lg-offset-2">
    <img src="assets/images/logo.png" style="padding:5px;" />
    </div>
    </div>
    </div>
	<!--Header closed--> 
</div>
  <section id="content" class="m-t-lg wrapper-md animated fadeInUp"> 
  
 	 <!-- <input type="hidden" id="message" value="$(#request.message)"  class="form-control no-border"/> -->
 	 <input type="hidden" id="message" value="<%=request.getAttribute("message")%>"  class="form-control no-border"/>
     
    <div class="container aside-xl">
      <a class="navbar-brand block" href="index.html"></a>
      <section class="m-b-lg">
       
        <header class="wrapper text-center">
        
          <strong><h3>Log in to your account</h3></strong>
        </header>
        <div class="alert alert-success" id="succ">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <i class="fa fa-ok-sign"></i><strong>Password Has Reset Successfully.Please Login</strong>
         </div>
         
       
       
       <div id="error-div" class="alert alert-danger">
                   <!--  <button type="button" class="close" data-dismiss="alert">&times;</button> -->
                    <i class="fa fa-ban-circle"></i><label id="error"></label>
                  </div>
                  
       <div id="success-div" class="alert alert-success">
                  <!--   <button type="button" class="close" data-dismiss="alert">&times;</button> -->
                    <i class="fa fa-ok-sign"></i><label id="success"></label>
                  </div>
        
        <!-- <form action="javascript:loginaction();" id="signinform"> -->
 
        <!--  <form action="login.action" id="signinform"> -->

          <div class="list-group">
 
            <div class="list-group-item">
            

              <input type="email" placeholder="Enter Email" class="form-control no-border" id='email'  required="required" >

              

            </div>
            <div class="list-group-item">

               <input type="password" placeholder="Password" class="form-control no-border" id="password"  required="required">

               

            </div>
            <div></div>
             <span id="span"></span>
          </div>
      
        <form id="formId" action="loginNavigation.action" method="post">
         <!-- <input type="hidden" placeholder="Email" class="form-control no-border" id='email_id1' name="userBO.email_id" required="required" >
            <input type="hidden" placeholder="Password" class="form-control no-border" id="password1" name="userBO.password" required="required">
          -->
          <button type="button" class="btn btn-lg btn-success btn-block" onclick="loginForm()" id="signInId">Sign in</button>
          <input type="submit" style="display: none;" id="submitForm"/>
          
          <div class="text-center m-t m-b"><a href="forgotpassword" class="btn-link">Forgot your password?</a></div>
          <!-- <div class="text-center m-t m-b"><a href="personalProfile.action">Go to Profile</a></div> -->
          <div class="line line-dashed"></div>
                 
          <div class="icons">
          <div class="text-center">Or login with </div><br />


         <center><ul style="margin-left:-60px;">         
          <li><a href="#"  onclick="javascript:fblogin();" class="social"><i class="fa fa-3x fa-facebook-square"></i></a> </li>         
          <li><a href="#"  onclick="javascript:gmaillogin();" class="gp"><i class="fa fa-3x fa-google-plus-square"></i></a></li>
          </ul>
         </center>
          
          </div>
         
          <p class="text-muted text-center"><small>Do not have an account?</small></p>
          <a href="signup" class="btn btn-lg btn-primary btn-block">Sign Up</a>
          <input type="text" value="normal" id="check" name="userBO.check" style="display: none;"> 
        </form>
      </section>
    </div>
    
    
    
	<!-- Social Login Area -->      
    <iframe name='myIFrame' id="myIFrame" style='display:none'></iframe>
     
    
        
	<!-- Social Login Area -->      
    
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

  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  
</body>
</html>
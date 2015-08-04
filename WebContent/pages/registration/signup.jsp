<!DOCTYPE html>
<html lang="en" class="app">
<%@taglib prefix="s" uri="/struts-tags" %>
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
  <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
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
  <noscript>
 For full functionality of this site it is necessary to enable JavaScript.
 Here are the <a href="http://www.enable-javascript.com/" target="_blank">
 instructions how to enable JavaScript in your web browser</a>.
</noscript>
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
</head>
<body id="body1">
	
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
        <header class="wrapper text-center">
          <strong><h3>Create an Account</h3></strong>
        </header> 
		<div style="font-weight:BOLD;color:red">
        <s:actionmessage/> 
        </div>  
        <form id="form"   method="post">
        <% if(request.getParameter("referId")!=null) { %>
        <input type="hidden" id="referId" value="<%=request.getParameter("referId")%>" name="registrationBo.referralCode"  class="form-control no-border"/> 
        <%} %>
          <div class="list-group">            
            <div class="list-group-item list-group-item-margin">
              <input   placeholder="Email" class="form-control no-border" required="required" name="registrationBo.email_id" id="email">
            </div>
            <div class="list-group-item">
               <input type="password" placeholder="Password" class="form-control no-border" required="required" id="pwd" name="registrationBo.password"> 
            </div>
            <div class="list-group-item">
               <input type="password" placeholder="Confirm Password" class="form-control no-border" required="required" id="pwd1">
            </div>
            <span id="span" style="color:#ff0000;font:12px arial,sans-serif;"  ></span> 
          </div>
          <div class=" m-b">
          <s:url id="terms" action="termsofuse"></s:url><s:url id="privacy" action="privacypolicy"></s:url>
           <!--  <label class="checkbox m-n i-checks" style="padding-left:0px">
              <input type="checkbox" id="agreecheckbox"/><i></i></label> --> I agree to the <s:a href="%{terms}" cssClass="btn-link" target="_blank" cssStyle="color:#428bca;text-decoration:none">Terms of Use</s:a> and <s:a href="%{privacy}" cssClass="btn-link" target="_blank" cssStyle="color:#428bca;text-decoration:none">Privacy Policy</s:a>
              
          <label>  <span id="agreement" style="color:red"></span>
            </label>
          </div>
          <button type="button" class="btn btn-lg btn-primary btn-block" id="singup" >Sign up</button>
          <div class="line line-dashed"></div>
         
          <p class="text-muted text-center"><small>Already have an account?</small></p>
           <div class="icons">
          <div class="text-center">Or login with </div><br>


         <center><ul style="margin-left:-60px;">         
          <li><a href="#" onclick="javascript:fblogin();" class="social"><i class="fa fa-3x fa-facebook-square"></i></a> </li>         
          <li><a href="#" onclick="javascript:gmaillogin();" class="gp"><i class="fa fa-3x fa-google-plus-square"></i></a></li>
          </ul>
         </center>
          
          </div>
          <a href="signin.action" class="btn btn-lg btn-default btn-block">Sign in</a>
         
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
    <script type="text/javascript">
  $(document).ready(function(){ 
	  
	  $('#span').hide();
	 
	    $.fn.checkpassword = function() {
	    //	alert("Calling checkpassword");
	    	
	    	if ($("#pwd").val() === $("#pwd1").val()) 
	    	{ 
	    		$("#span").html("");
	    		if($("#pwd").val().length>16)
	    			{
	    			 $('#span').show();
	    			$("#span").html("Password Lenght must not be greater than 16");
	    			return false;
	    			}
	    		else{
	    			 $('#span').hide();
	    		return true;
	    		}
	    	} 
	    	else {
	    		$('#span').show();
	    		$("#span").html("Password's not matched");
	    		return false; 
	    	}
	   	  
	    };
	    $.fn.checkAgreement = function() {
	    //	alert("Calling check agreement");
	    	
	    	if($('#agreecheckbox').is(':checked')) {
	    		//alert("if");
	    		$("#agreement").html("");
	    		return true; 
	    	} else {
	    		//alert("else");
	    		$("#agreement").html("Please Agree the Terms of Use and Privacy Policy");
	    		return false;
	    	}
    		
	    };
	    $("#singup").click(function (e) {
	    //	alert("on submit");
	    	 
	    	if($.fn.checkEmailId($('#email').val()))
	    		{
	    	//alert("on email check");
	    	  if($.fn.checkpassword())
	    		  { 
	    		//  alert("on password comparision check");
	    		  if($.fn.checkPassword($('#pwd').val()))
	    		  {
	    			//  alert("on password value check");
	    			  if(true)//$.fn.checkAgreement()
		    			{
	    				// alert("on agreement check");  
	    				//	alert("Calling : Email : "+$('#email').val()+"\t password  "+$('#pwd').val());  
		    		  	$.ajax({
		    				type : 'POST',
		    				url : 'userRegister.action',
		    				data : $( "#form" ).serialize(),
		    			    success :function(data){
		    					// alert("successmessage");
		    			    	$("#body1").html(data);
		    			    	
		    				},
		    				error : function(e){
		    					   // alert("ErrorMessage:"+e);
		    				 
		    					 
		    				}
		    			}); 
		    			}
		    		else
		    			{
		    			 
		    			} 
	    			  $("#span").html(""); 
	    		  }
	    		  else
	    			  {
	    			 
	    			  $('#span').show();
	    			  $("#span").html("Password Must have Atleast One Capital letter, One Special Symbol and One Numeric with Minimum 8 characters in Length");
	    			  }
	    		   
	    		  }
	    	  else{
	        
	    
	    	  }
	    		}
	    	else
	    		{
	    		  
	    		 $('#span').show();
   			     $("#span").html("Please Enter Valid Email Id");
   			
	    		}
	    	
	        
	    });
	    $('#agreecheckbox').click(function() {
	    	 
	        if ($(this).is(':checked')) {
	        	 
	        	$("#agreement").html("");
	        
	        }
	        else
	        	{
	        	 
	        	}
	        
	    });
	    $( "#pwd1" ).change(function() {
	    	if ($("#pwd").val() != $("#pwd1").val()) 
	    	{ 
	    		$("#span").html("");	  
	    		 $('#span').hide();
	    	} 
	    	});
	    
	});
  $.fn.checkPassword = function(str) {	   
	  
	    // at least one number, one lowercase and one uppercase letter
	    // at least 8 characters
	   //  var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,16}/;	   
	    //return re.test(str);
	    var passReg = new RegExp(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,16}/);
	    var valid = passReg.test(str);

			if(!valid) {
		      return false;
		    } else {
		    	  return true;
		    } 
  };
  $.fn.checkEmailId = function(str1) {
	//  alert("Calling check email Id "+str1);  
	  // valid email id
	 // var re="/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i";
	   var emailReg = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
    		  var valid = emailReg.test(str1); 
    			if(!valid) {
    		      return false;
    		    } else {
    		    	  return true;
    		    } 
};
  $.fn.clear= function(str) {	   
		$("#pwd").val("");
		$("#pwd1").val("");
		$("#email").val("");		 
		$("#agreecheckbox").attr("checked", false);
	    
};
  </script>
</body>
</html>
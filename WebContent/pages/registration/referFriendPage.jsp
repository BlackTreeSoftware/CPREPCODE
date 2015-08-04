<%@ taglib prefix="s" uri="/struts-tags" %>
    <!DOCTYPE html>
    <html>

    <head>
        <!-- / footer -->
        <script src="assets/js/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="assets/js/bootstrap.js"></script>
        <!-- App -->
             <!-- Bootstrap -->
        <script src="assets/js/bootstrap.js"></script>
        <!-- App -->
        <link rel="stylesheet" href="assets/js/datepicker/datepicker.css" type="text/css" />
        <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
        <link rel="stylesheet" href="assets/css/jasny/jasny-bootstrap.min.css" type="text/css" />
        <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css" />
		
        <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
        <script src="assets/js/select2/select2.min.js"></script>
         
        
        <script src="assets/js/datepicker/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
        <script src="assets/js/file-input/bootstrap-filestyle.min.js"></script>
        <script src="assets/js/jasny/jasny-bootstrap.min.js"></script>
        <script src="assets/js/parsley/parsley.min.js"></script>
        <script src="assets/js/parsley/parsley.extend.js"></script>
        <script src="assets/js/gmail/client_ids.js"></script>
           <script src="http://connect.facebook.net/en_US/all.js"> </script>
           <script type="text/javascript" src="assets/js/ZeroClipboard.js"></script>
            
 
<script class="pre" src="assets/js/gmail/hello.js"></script>
	 
	<script class="pre" src="assets/js/gmail/facebook.js"></script>
<script class="pre" src="assets/js/gmail/google.js"></script>
 
           
  
  <style>
  .terms{ background-color:#FFF; color:#999; margin-left:2.5%; height:400px; width:95%; padding:20px; overflow-y:scroll;}
  .form-horizontal .form-control{ height:46px;}  
  .fnt{font-size:10px;} 
  .font-fourteen{ font-size:14px;}  
  .text-primary:hover{ text-decoration:underline}
  
  .share-links ul{ padding-left:0px; margin-left:0px;}
  .share-links ul li{ list-style:none; }
  .share-links li{ border-top:rgba(0,0,0,0.1) 1px solid; border-bottom:rgba(0,0,0,0.1) 1px solid; padding:6px; margin-left:0px !important; font-weight: 700; }
  </style>
  
  
  <!-- ************************  JAVA SCRIT START           -->
  
   <script>
   // var correctData=JSON.parse('['+correctCount+']');
   $(document).ready(function (){
	  //  alert('Refer');
	  // $("#referFriend").select2({tags:[""],placeholder: "Select Lessons"});
	  var glink="http://crunchprep.com/";
	  $("#gplus").attr("data-href", glink );
	
	  

	// ***********************************  GMAIL FRIENDS REQUEST START      *****************************
	 
	    var po = document.createElement('script'); 
	    po.type = 'text/javascript'; 
	    po.async = true;
	    po.src = 'https://apis.google.com/js/platform.js';
	    var s = document.getElementsByTagName('script')[0];
	    s.parentNode.insertBefore(po, s);
	    
	  
	  
	//***********************************  GMAIL FRIENDS REQUEST   END    *****************************
	    bar();
	    var refur = $("#refurl").html();			
		$("#copylink").val(refur);

   }); //document End
 
   </script>
  
   <script type="text/javascript">
//******************************************************   FACEBOOK FRIEND REQUESTS STARTED******************************    
FB.init({ 
	appId:'502112553253743', 
	cookie:true, 
	status:true,
	xfbml:true 
	});

 

function FacebookInviteFriends()
{

	 var flink="http://crunchprep.com/";
FB.ui({
	  method: 'send',
	  link: flink,	

	}	,
function(response) {
 if (response && !response.error_code) {
 	toastr.success("Success","Your Request is Successfully Sent!");
 } else {
 	toastr.error("Sorry","Internal Error with Facebook!"+response.error_code);
 }
}

);
}




//******************************************************   FACEBOOK FRIEND REQUESTS END****************************** 
</script>


 
<script class="pre">
hello.init( {
		 
		google : '946731390813-c4q0oaemj2lud6bfmborj2ki87o3js24.apps.googleusercontent.com',
		facebook : '502112553253743',
		 
	}
	, {
		redirect_uri:'http://localhost:8080/crunchprep/referFriend', 
		oauth_proxy : OAUTH_PROXY_URL, 
		scope:"friends"
	}
);
</script>

 
<script class="pre">
function getFriends(network, path){
	alert('Im Ok');

	var list = document.getElementById('list');
	list.innerHTML = '';
	var btn_more = document.getElementById('more');
	btn_more.style.display = 'none';

	// login
	hello.login( network, {scope:'friends'}, function(auth){
		if(!auth||auth.error){
			console.log("Signin aborted");
			return;
		}
		// Get the friends
		// using path, me/friends or me/contacts
		hello( network ).api( path, {limit:10} ,function(r, next){
		var allemails =''; 
		var jsemails =''; 
			for(var i=0;i<r.data.length;i++){
				var o = r.data[i];
				//allemails = allemails+o.email+',';
				var emailid =  o.email;
				
				if(emailid!=null)
					{
					
				
				
				allemails =	allemails+emailid+",";
				jsemails =	jsemails+'{\"id\":\"'+emailid+'\",\"text\":\"'+emailid+'\"},';
				
				
					}
				
			
			};
			jsemails = jsemails.substring(0, jsemails.length-1);
			//list.innerHTML = allemails;
		 
		 	var correctData=JSON.parse('['+jsemails+']');
			alert("Afrer Segnafy : "+correctData);
		
		$("#glabel").show();
		$("#referFriend").show();
		$("#invite").show();
		
		 $('#referFriend').select2({multiple:true,placeholder: "Click Here to Invite Your Friends",data: correctData});
		
		});
	});
	
	
}


 

function getEmails() {
	
	var allemails = $('#referFriend').select2('val');
	toastr.info('Wait a Moment...............','Sending Invitations.......');
	 alert("In Invite Gmail : "+allemails);
	$.ajax({		
		  url: 'gmailcontacts.action?emails='+allemails, 
		  success:function(data){
			  toastr.success("Success","Your Request is Successfully Sent!");
		  }	,	  
		error:function(e){			
			alert('in error'+e+" : "+e.message);
		} 
	}); 
	
}



function getinputEmails() {
	
	var allemails = $('#inputEmails').val();
	toastr.info('Wait a Moment...............','Sending Invitations.......');
	 alert("In InputInvite Gmail : "+allemails);
	$.ajax({		
		  url: 'gmailcontacts.action?emails='+allemails, 
		  success:function(data){
			  toastr.success("Success","Your Request is Successfully Sent!");
		  }	,	  
		error:function(e){			
			alert('in error'+e+" : "+e.message);
		} 
	}); 
	
}
//************************ FB LIKE START ***************************************** 

	FB.Event.subscribe('edge.create', function(response) {
	      alert('You liked the URL: ' + response);
	      $("#actiontype").html("fb_like");
	      vrplsave();
	  });
	  
	FB.Event.subscribe('edge.remove', function(response) {
	    alert('You Unliked: ' + response);
	    $("#actiontype").html("fb_unlike");
	    vrplsave();
	    
	});
	//************************ FB LIKE END *****************************************
	

	//************************ FB SHARE START *****************************************	
function fb_share() {
    FB.ui( {
        method: 'feed',
        name: "Facebook API: Tracking Shares using the JavaScript SDK",
        link: "http://crunchprep.com/",
        picture: "http://crunchprep.com/assets/themes/crunchprep/images/main-screen.png",
        caption: "Tracking Facebook Shares on your website or application is a useful way of seeing how popular your articles are with your readers. In order to tracking Shares, you must used the Facebook JavaScript SDK."
    }, function( response ) {
        if ( response !== null && typeof response.post_id !== 'undefined' ) {
            console.log( response );
            //alert(JSON.stringify(response));
            // ajax call to save response
            alert(response.post_id); 
            $("#actiontype").html("fb_share");
            vrplsave();
        }
    } );
  
}
//************************ FB SHARE END *****************************************



	//************************ FB SHARE START *****************************************	
function fb_shareEmail() {
    FB.ui( {
        method: 'feed',
        name: "Facebook API: Tracking Shares using the JavaScript SDK",
        link: "http://crunchprep.com/",
        picture: "http://crunchprep.com/assets/themes/crunchprep/images/main-screen.png",
        caption: "Tracking Facebook Shares on your website or application is a useful way of seeing how popular your articles are with your readers. In order to tracking Shares, you must used the Facebook JavaScript SDK."
    } , function( response ) {
        if ( response !== null && typeof response.post_id !== 'undefined' ) {
        	toastr.success("Success","Your Post is Successfully Shared!");
        }
    } );
  
}
//************************ FB SHARE END *****************************************



//************************ TWITTER START *****************************************
 
window.twttr = (function(d, s, id) {
    var t, js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//platform.twitter.com/widgets.js";
    fjs.parentNode.insertBefore(js, fjs);
    return window.twttr || (t = {
        _e: [],
        ready: function(f) {
            t._e.push(f)
        }
    });
}(document, "script", "twitter-wjs"));
        
// Wait for the asynchronous resources to load
twttr.ready(function(twttr) {
   
	
	twttr.events.bind('follow', function() {
        alert('Fallow Done');
        $("#actiontype").html("tw_follow");
        vrplsave();
       
    });
	
	twttr.events.bind('tweet', function() {
        alert('You Twitted');
        $("#actiontype").html("tw_tweet");
        vrplsave();
    });
	
	twttr.events.bind('unfollow', function() {
        alert('UNFollow Done');
        $("#actiontype").html("tw_unfollow");
        vrplsave();
    });
	
});
//************************ TWITTER END *****************************************

function twitterEmail()
{
alert('twitterEmail'); 
}
//************************ TWITTER END *****************************************

//************************ FACEBOOK START *****************************************
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=502112553253743&version=v2.0";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
//************************ FACEBOOK END *****************************************



//****************** G+ LIKE ***********************************
function onEnded(args) {
   var res = args.type;
    console.log(res);
    alert("G+ Ended :"+res);
    if (res == 'confirm') {
    	 $("#actiontype").html("g_like");
        vrplsave();
       

    }
    console.log(this);
}

(function () {
    var po = document.createElement('script');
    po.type = 'text/javascript';
    po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(po, s);
})();
//****************** G+ LIKE ***********************************




//****************** G SHARE ***********************************
 function callMeMaybe(jsonParams) {                     
                //document.getElementById('end').innerHTML = document.getElementById('end').innerHTML + jsonParams.type + ", ";
                $("#actiontype").html("g_share");
                vrplsave();
                $("#actiontype").html("g_follow");
                vrplsave();
            };
//****************** G SHARE ***********************************


 



function vrplsave(){
	var atype = $("#actiontype").html();
	
	var ur = '/crunchprep/vrpl?actiontype='+atype;
	alert('VRPL SAVE start  : '+atype+'URL : '+ur);
	
	  $.ajax({
		  
		  url  : ur, 
			 success:function(data){
				 //alert('success' +data);
				 $("#vrplid").html(data);				
				bar();
				
				},
				error:function(e){
					alert("error : "+JSON.stringify(e));
					
				}
		 }); 
	
	
	alert('VRPL SAVE End');
}


function showfirstdiv(){
	
	
  $("#firstdiv").show();
  $("#seconddiv").hide();
	
 
 
}


function showseconddiv(){
	
	 $("#seconddiv").show();
	  $("#firstdiv").hide();
	 
}

function bar() {
	var votes = $("#vrpl").html();
	var prog = $("#progress").html();
	//alert(votes+' : '+prog);
	 $("#bar").attr("data-original-title",votes+"/7");
	 $("#bar").css('width',prog+"%");
	 $("#ct").html(votes+"/7");
}



</script>



<!-- *************************  GAMIL COM ***************************** -->
  <!-- ************************  JAVA SCRIT END **************************           -->
  
</head>
<body class="">
 
 <section id="vrplid" style="display: none">
 <label id="refurl" style="display: none"><%=request.getAttribute("refurl") %></label> 
 <label id="vrpl"><%=request.getAttribute("votes") %></label> 
 <label id="progress"><%=request.getAttribute("progress") %></label> 
 <label id="actiontype"></label>
 </section>

<section class="vbox" id="maindiv">   
     
     
	<!--Header closed--> 
    
  <section id="content">
     <section class="vbox">          
      <section class="scrollable wrapper">
      <!--************************************************   1st DIV Start  **************************************** -->
     
       <div class="container aside-xxxl" style="background-color:#FFF;" id="firstdiv">      
   			   <section class="m-b-lg ">    	
				<div class="row">
                <div class="col-lg-12">
                <div class="m-t-15"><strong>Get Started</strong></div>
                <hr class=" hr-less">                  
                </div>                
                </div>	<!--get strted row close-->
                
                <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-sm-8 col-sm-offset-2 m-t-15">   
                
                	<div class="row alert bg-gray">
               
               		<div class="col-lg-8 col-sm-8">               
                           
                    <p class="font-bold h4 m-t-10"> Your 7 steps away from your bonus </p>
                    <p class="m-t-15">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.</p>
               
                  	
                    </div><!--inneer col8 close-->                    
                    
                    <div class="col-lg-4 col-sm-4">                                                                                
                    
                    <div class="m-t-10">
                    <p class="font-bold h5 m-t-10 text-center"> Achieved Bonus Step <b id="ct"></b></p> </p>
                    	<div class="progress progress-sm m-t-sm">
                          <div class="progress-bar bg-info" data-toggle="tooltip" data-original-title="1/7" style="width: 45%" id="bar"></div>
                        </div>                        
                     </div><!--progress bar div close-->             
                  	
                    </div><!--inneer col-4 close-->                                   
                    
                    </div><!--inner row close-->
                    
                </div>                
                </div>	<!--div row close for 2nd-->
				
                
				<div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-sm-8 col-sm-offset-2 share-links">
                <ul>
                <li><p class="h5 font-bold text-primary p-b-10">Step 1</p> Like us on Facebook : &nbsp;&nbsp;&nbsp;&nbsp;<div class="fb-like" data-href="https://www.facebook.com/gbcrunchprep" data-colorscheme="light" data-show-faces="false" data-header="false" data-stream="false" data-show-border="false" onclick="alert('Like Clicked');"></div> </li>
				<li><p class="h5 font-bold text-primary p-b-10">Step 2</p> Share on Facebook : &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="btn btn-sm btn-primary" onclick="fb_share();"><i class="fa fa-facebook-square text-white"></i> Share</a> </li>
				<li><p class="h5 font-bold text-primary p-b-10">Step 3</p> Google+ Us        : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="g-plusone" data-expandto="bottom" data-onendinteraction="onEnded" data-onstartinteraction="onStarted" data-recommendations="false" data-annotation="inline" data-height="25" data-autoclose="true" data-callback="onCallback" data-width="300"></div> </li>
				<li><p class="h5 font-bold text-primary p-b-10">Step 4</p>Follow on Google : &nbsp;&nbsp;&nbsp;&nbsp; <g:follow href="https://plus.google.com/u/0/111895101389966879705" rel="author" onstartinteraction="callMe" onendinteraction="callMeMaybe"></g:follow></li>
				<li><p class="h5 font-bold text-primary p-b-10">Step 5</p> Share on Google + : &nbsp;&nbsp;&nbsp;&nbsp;<g:plus action="share" annotation="bubble" height="24" href="http://crunchprep.com/" onstartinteraction="callMe" onendinteraction="callMeMaybe"></g:plus></li>				
				<li><p class="h5 font-bold text-primary p-b-10">Step 6</p> Tweet on Twitter : &nbsp;&nbsp;&nbsp;&nbsp;<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://crunchprep.com/" data-via="thecrunchprep">Tweet</a> </li>
				<li><p class="h5 font-bold text-primary p-b-10">Step 7</p> Simply follow us : &nbsp;&nbsp;&nbsp;&nbsp;<a href="https://twitter.com/crunchprepgre" class="twitter-follow-button" data-show-count="false" data-lang="en">Follow</a></li>
                </ul>
                <p> <a class="btn btn-default btn-lg" href="#" onclick="showseconddiv();">More Options to Invite Frinds!</a> </p>    
                </div>                
                </div>
				
			
      </section>
    </div>
  
                
                <!--************************************************   1st DIV END  **************************************** -->
                
                <!--************************************************   2nd Start  **************************************** -->
                
                   <div class="container aside-xxxl" style="background-color:#FFF;display: none" id="seconddiv">    
			
   			    <div class="row">
                    <div class="col-lg-12 ">
                    <br/><br/>
                    <p class="h2 text-primary text-center m-t-15 m-b-lg">Hello this is test text u can delte or alter me as per ur requirement. Thanks</p>
                    <p class="text-center"> There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour.</p>
                      
                    </div>                   
                </div>	
                
                <div class="row m-t-45">
                 
                    <div class="col-lg-4 col-md-4 col-lg-offset-1 col-md-offset-1">
                    <a href="#" class="btn btn-primary btn-lg btn-smal" onclick="getFriends('google', 'me/contacts');"> <img src="assets/images/gmail.png"> Invite your gmail contacts... </a>

                    <p class="text-dark-lter text-center fnt" >we won't store your password and your contacts are secure</p>
                    
                     
                    </div>
                	
                    <div class="col-lg-1 col-md-1" >
                	<p class="m-t-15 text-center"><strong>OR</strong></p>
                    </div>
                
                
                	<div class="col-lg-4 col-md-4">
                    	 
                         <form class="form-horizontal">
                         <div class="input-group m-b">
                          <input type="text" class="form-control" id="inputEmails">
                          <span class="input-group-addon"><a href="#" onclick="getinputEmails();"><i class="fa fa-mail-forward"></i> Send</a></span>
                         </div>
                         </form>
                         
                    </div>     
				
                    </div><!--btn row closed-->
                    
                    <div class="col-sm-10">                    
                    	<input type="text" style="width:80%;display: none" id="referFriend"  />
                    </div>
                    
                    <button class="btn btn-lg btn-default btn-rounded  col-sm-2 control-label pull-right" id="invite" onclick="getEmails();" style="display: none"> 
                     Invite
                    </button>
					
					<hr>	
				
				
				
				    <div class="row m-t-45">
                    <div class="col-lg-12 ">
                    <p class="h5 text-darker text-center font-bold">More ways to invite your friends</p>
					<br>									                   
                    </div>                   
                	</div>
					
					
				<div class="row ">
                 
                    <div class="col-lg-4 col-md-4 col-lg-offset-2 col-md-offset-2">
							<form class="pull right">
							   <div class="input-group m-b">
									  <a class="input-group-addon" href="#"><i class="fa fa-link"></i> Copy link</a>
									  <input type="text" class="form-control" placeholder="Some code here to copy" id="copylink">
							   </div>
						    </form>
                    </div>
                	                  
					<div class="col-lg-2 col-md-2 ">
						               	
                        <center> <a href="#" class="btn btn-primary" onclick="fb_shareEmail();"><i class="fa fa-facebook text-white"></i>&nbsp;&nbsp; Share on Facebook</a></center>
					
                    </div>       

					<div class="col-lg-2 col-md-2 marginer">
						  <center><a href="https://twitter.com/share" class="twitter-share-button" data-url="http://crunchprep.com/" data-via="thecrunchprep" data-size="large"> <i class="fa fa-twitter text-primary"></i>&nbsp;&nbsp; Tweet in twitter</a></center>                	
                        
						
						
                    </div>       		
					
                </div><!--btn row closed-->
                
                <div class="row m-t-15">
                <div class="col-lg-12 links-prime">
                <div class="text-center font-fourteen">Once you've invited friends, you can <a href="referFriend" class="text-primary" >view the status of your referrals</a> or visit our <a href="#" class="text-primary">	
                Help Center</a> if you have any questions.</div>
                </div>            
                </div>
               
				<ul id="list"></ul>
			<button id="more" style="display:none;"></button>
	
 
				<hr>
				<br/><br/>
			
			</div>
				
			
			
  
 
      <div id="fb-root"></div>
 
  <hr/>
   
 

 
       
        
        
        
        
        
		
 		
      
   
  </section>
 
  </section>
  </section>
  </section><!--section vbox close-->
  
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>Crunchprep &copy; 2014</small>
      </p>
    </div>
  </footer>  
  <!-- / footer -->      
  
  
   
</body>
<link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" />  
  <script src="bootbox.min.js"></script>
    <script>
        $(document).on("click", ".alert", function(e) {
            bootbox.alert("Hello world!", function() {
                console.log("Alert Callback");
            });
        });
    </script>

</html>
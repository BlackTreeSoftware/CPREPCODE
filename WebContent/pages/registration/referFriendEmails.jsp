 
			<%@ taglib prefix="s" uri="/struts-tags" %>	
			<script type="text/javascript">
			
			$(document).ready(
			function(){
			var refur = $("#refurl").html();	
			alert('im form Ajax Doc Ready'+refur);	
			$("#copylink").val(refur);
			
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
			   
			 
				twttr.events.bind('tweet', function() {
					toastr.success("Success","Your Post is Successfully Twitted!");
			    });
				
				 
			});
				
				
			}
			);
			
			
			</script>
			
        <section id="content" class="m-t-lg wrapper-md animated fadeInDown" >
          <section class="vbox">          
            <section class="scrollable wrapper">
				
				
				
				 <div class="container aside-xxxl" style="background-color:#FFF;">      
				
              <section class="m-b-lg m-t-45 m-b-45">
			
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
						                 	
                         <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://crunchprep.com/" data-via="thecrunchprep">Tweet</a> Tweet on Twitter
						<a href="https://twitter.com/crunchprepgre" class="twitter-follow-button" data-show-count="false" data-lang="en">Follow</a> Simply follow us
				
						
						
						
                    </div>       		
					
                </div><!--btn row closed-->
                
                <div class="row m-t-15">
                <div class="col-lg-12 links-prime">
                <div class="text-center font-fourteen">Once you've invited friends, you can <a href="referFriend.action" class="text-primary" >view the status of your referrals</a> or visit our <a href="#" class="text-primary">	
                Help Center</a> if you have any questions.</div>
                </div>            
                </div>
               
				<ul id="list"></ul>
			<button id="more" style="display:none;"></button>
	
 
				<hr>
				<br/><br/>
			
			</section>
			</div>
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
     
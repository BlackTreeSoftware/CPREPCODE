<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script> 
 <!-- Sparkline Chart -->
  <script src="assets/js/charts/sparkline/jquery.sparkline.min.js"></script>  
    <script src="assets/js/charts/flot/jquery.flot.min.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.tooltip.min.js"></script>
 <script src="assets/js/charts/flot/jquery.flot.spline.js"></script>
   <script src="assets/js/charts/flot/jquery.flot.pie.min.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.resize.js"></script>
  <script src="assets/js/charts/flot/jquery.flot.grow.js"></script> 
  <script src="assets/js/charts/flot/demo.js"></script>  
 <link rel="stylesheet" href="assets/js/intro/introjs.css" type="text/css" /> 
  <script src="assets/js/intro/intro.min.js"></script>
  <script src="assets/js/intro/demo.js"></script>
  
  
  <style>
  	
	/*This is for anchor tag with green color*/
	a.green{color:#179877;} 
	a.green:hover,.green:active{color:#47a447;text-decoration:underline} 
	
	a.link-lesson{color:#006666; line-height:0}
	a.link-lesson:hover,link-lesson:active{color:#060; text-decoration:underline}
	
	.table .lheight{line-height:8;}
	.table .nlheight{line-height:0;}
	
	.table .nl{line-height:-1;}

  </style>
</head>
<body>
    
           <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
            
   <!--row for prfile-->         
   
            
              <h3>How am i doing ?</h3>
                
              <div class="row" id="tour9">
              <div class="col-lg-6">
              <div class="panel panel-default">
              <div class="panel-heading">Math Overall</div>
              <div class="panel-body">
              
              <div class="col-lg-4">
						<!--<div class="ques-top">75%</div>
                        <div class="ques-bot">Questions correct</div> <br>-->
                        <div class="panel b-a">
                        <div class="panel-heading no-border bg-primary lt text-center">
                          <span class="m-t m-b text-white text-box"> 75%</span>
                        </div>
                        <div class="padder-v text-center clearfix">                            
                          
                          <div class="col-xs-12">
                            <div class="h6" style="padding-top:12px; padding-bottom:12px;">Questions Correct</div>                            
                          </div>
                        </div>
                      </div>
              </div><!--Inner col close-->
              
              <div class="col-lg-4">
						<!--<div class="ques-comp">450</div>
                        <div class="ques-bot">Questions practiced</div> <br>-->
                        
                        <div class="panel b-a">
                        <div class="panel-heading no-border bg-info lt text-center">
                          <span class="m-t m-b text-white text-box"> 450</span>
                        </div>
                        <div class="padder-v text-center clearfix">                            
                          
                          <div class="col-xs-12">
                            <div class="h6" style="padding-top:12px; padding-bottom:12px;">Questions practiced</div>                            
                          </div>
                        </div>
                      </div>
              </div><!--Inner col close-->
              
              <div class="col-lg-4">
               		   <!--<div class="ques-time">1:52 </div>
                       <div class="ques-bot">average time per question</div>-->
                       
                       <div class="panel b-a">
                        <div class="panel-heading no-border bg-success lt text-center">
                          <span class="m-t m-b text-white text-box"> 1:52</span>
                        </div>
                        <div class="padder-v text-center clearfix">                            
                          
                          <div class="col-xs-12">
                            <div class="h6" style="padding-top:12px; padding-bottom:12px;">average time per question</div>                            
                          </div>
                        </div>
                      </div>
                       
              </div><!--Inner col close-->
              
              </div><!--Panel body closing-->
              </div><!--Panel head close-->
              </div>
			  <!--Math overall col for panel close-->              
      		 
              <div class="col-lg-6">
              <div class="panel panel-default">
              <div class="panel-heading">Verbal Overall</div>
              <div class="panel-body">
              
              <div class="col-lg-4">
						<!--<div class="ques-top">75%</div>
                        <div class="ques-bot">Questions correct</div> <br>-->
                        <div class="panel b-a">
                        <div class="panel-heading no-border bg-primary lt text-center">
                          <span class="m-t m-b text-white text-box"> 75%</span>
                        </div>
                        <div class="padder-v text-center clearfix">                            
                          
                          <div class="col-xs-12">
                            <div class="h6" style="padding-top:12px; padding-bottom:12px;">Questions Correct</div>                            
                          </div>
                        </div>
                      </div>
              </div><!--Inner col close-->
              
               <div class="col-lg-4">
						<!--<div class="ques-comp">450</div>
                        <div class="ques-bot">Questions practiced</div> <br>-->
                        
                        <div class="panel b-a">
                        <div class="panel-heading no-border bg-info lt text-center">
                          <span class="m-t m-b text-white text-box"> 450</span>
                        </div>
                        <div class="padder-v text-center clearfix">                            
                          
                          <div class="col-xs-12">
                            <div class="h6" style="padding-top:12px; padding-bottom:12px;">Questions practiced</div>                            
                          </div>
                        </div>
                      </div>
              </div><!--Inner col close-->
              
               <div class="col-lg-4">
               		   <!--<div class="ques-time">1:52 </div>
                       <div class="ques-bot">average time per question</div>-->
                       
                       <div class="panel b-a">
                        <div class="panel-heading no-border bg-success lt text-center">
                          <span class="m-t m-b text-white text-box"> 1:52</span>
                        </div>
                        <div class="padder-v text-center clearfix">                            
                          
                          <div class="col-xs-12">
                            <div class="h6" style="padding-top:12px; padding-bottom:12px;">average time per question</div>                            
                          </div>
                        </div>
                      </div>
                       
              </div><!--Inner col close-->
              
			  </div><!--Panel body closing-->
              </div><!--Panel head close-->
              </div>
              
			  <!--Verbal overall col for panel close-->              
			  </div><!--Row close for Verbal panel close-->
              
              <div class="row" id="tour10">
              <div class="col-lg-6">
              <div class="panel panel-default">
              <div class="panel-heading">Top 3 Strengths</div>
              <div class="panel-body">
              
               		<table class="table table-responsive">
                    <tr><td class="lheight"><span class="btn btn-success">Skills</span></td><td class="lheight"><span class="btn btn-success">Correct</span></td>
                    <td class="lheight"><span class="btn btn-success hidden-xs">Skill Percent</span></td></tr>
                    <tr> <td class="nlheight">Sentence equvilance</td><td class="nlheight">90%</td> <td class="nlheight"> <div class="progress progress-sm progress-striped active hidden-xs">
  				   	<div class="progress-bar progress-bar-success"  role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%">
    			   	<span class="sr-only">90% Complete</span></div></div></td> </tr>
                    <tr> <td class="nlheight">Text Completion</td><td class="nlheight">40%</td> <td class="nlheight"> <div class="progress progress-sm progress-striped active hidden-xs">
  				   	<div class="progress-bar progress-bar-danger"  role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
    			   	<span class="sr-only">45% Complete</span></div></div></td> </tr>
                    <tr> <td class="nlheight">Reading Comprehension</td><td class="nlheight">65%</td> <td class="nlheight"> <div class="progress progress-sm progress-striped active hidden-xs">
  				   	<div class="progress-bar progress-bar-warning"  role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 65%">
    			   	<span class="sr-only">65% Complete</span></div></div></td> </tr> 
                    <tr class="hidden-xs hidden-sm"> <td></td> <td></td> <td class="pull-right nlheight"> <a href="#" class="btn green">More Oppurtunities</a></td></tr>                   
             		</table>
             
              
                
			  </div><!--Panel body closing-->
              </div><!--Panel head close-->
              </div>
			  <!--Top 3 Oppurtunities col for panel close--> 
              
              
              <div class="col-lg-6">
              <div class="panel panel-default">
              <div class="panel-heading">Top 3 Oppurtunities</div>
              <div class="panel-body">
					
                    <table class="table table-responsive">
                    <tr><td class="lheight"><span class="btn btn-success">Skills</span></td><td class="lheight"><span class="btn btn-success">Correct</span></td>
                    <td class="lheight"><span class="btn btn-success hidden-xs">Skill Percent</span></td></tr>
                    <tr> <td class="nlheight">Sentence equvilance</td><td class="nlheight">90%</td> <td class="nlheight"> <div class="progress progress-sm progress-striped active hidden-xs">
  				   	<div class="progress-bar progress-bar-success"  role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%">
    			   	<span class="sr-only">90% Complete</span></div></div></td> </tr>
                    <tr> <td class="nlheight">Text Completion</td><td class="nlheight">40%</td> <td class="nlheight"> <div class="progress progress-sm progress-striped active hidden-xs">
  				   	<div class="progress-bar progress-bar-danger"  role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
    			   	<span class="sr-only">45% Complete</span></div></div></td> </tr>
                    <tr> <td class="nlheight">Reading Comprehension</td><td class="nlheight">65%</td> <td class="nlheight"> <div class="progress progress-sm progress-striped active hidden-xs">
  				   	<div class="progress-bar progress-bar-warning"  role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 65%">
    			   	<span class="sr-only">65% Complete</span></div></div></td> </tr> 
                    <tr class="hidden-xs hidden-sm"> <td></td> <td></td> <td class="pull-right nlheight"> <a href="#" class="btn green">More Oppurtunities</a></td></tr>                   
             		</table>
             
              
			  </div><!--Panel body closing-->
              </div><!--Panel head close-->
              </div>
			  <!--Verbal overall col for panel close--> 
			  
			  
			  
              
                           
			  </div><!--Row close for Verbal panel close-->
              
              
              <div class="row" id="tour11">
              <div class="col-lg-4">
              <div class="panel panel-default" style="min-height:300px;">
              <div class="panel-heading">
              
              <div class="panel-title"> What should i do now ?  <p class="pull-right hidden-xs" style="padding-top:0px;"><a href="#" class="btn btn-success btn-xs "> Go to my study plan</a></p></div>
                
              </div>
              <div class="panel-body">
                <div class="row">
                <div class="col-lg-12">
                <h4>Based on your practice score we recommend you</h4>
                <table class="table">
				<tr><td>Take the <a href="#" class="btn link-lesson">oppurtunity Lessson #5</a> </td></tr>
                <tr><td>Take the <a href="#" class="btn link-lesson">oppurtunity Lessson #2</a> </td></tr>
                <tr><td>Take the <a href="#" class="btn link-lesson">oppurtunity Lessson #5</a> </td></tr>
                <tr><td>Take the <a href="#" class="btn link-lesson">oppurtunity Lessson #5</a> </td></tr>
                <tr><td>Take the <a href="#" class="btn link-lesson">oppurtunity Lessson #5</a> </td></tr>             
                <tr><td>Take the <a href="#" class="btn link-lesson">oppurtunity Lessson #5</a> </td></tr>
                </table>
                <a href="#" class="btn green"> See More Recomendations</a> 
                
				</div>
                </div>
              
             </div>
             </div>
             </div>
            
              
            <div class="col-lg-8">
            	
                <section class="panel panel-default">
                     <header class="panel-heading panel-title"><div id="graphName">Quantitative Graph</div></header>
              
               <div class="bg-light dk wrapper">
                      <span class="pull-right h4"><div id="highestScore">Heighest Score : 165</div></span>
                      <span class="h4"><div id="leastScore">Lowest Score : 155</div></span>
                      <div class="text-center m-b-n m-t-sm">
                         
                         
                          
                          <div class="sparkline" data-type="line" data-height="205" data-width="100%" data-line-width="2" data-line-color="#dddddd" data-spot-color="#bbbbbb" 
                          data-fill-color="" data-highlight-line-color="#fff" data-spot-radius="3" data-resize="true" values="147,149,155,158,155,158,164" 
                          id="verbal" style="display: block;"><canvas width="951" height="195" style="display: inline-block; width: 951px; height: 195px; vertical-align: top;"></canvas></div>
                          
                          <div class="sparkline" data-type="line" data-height="205" data-width="100%" data-line-width="2" data-line-color="#dddddd" data-spot-color="#bbbbbb" 
                          data-fill-color="" data-highlight-line-color="#fff" data-spot-radius="3" data-resize="true" values="155,148,160,162,158,161,158" 
                          id="quant" style="display: block;"><canvas width="951" height="195" style="display: inline-block; width: 951px; height: 195px; vertical-align: top;"></canvas></div>
                          
                         
                         <!-- <div class="sparkline inline" data-type="bar" data-height="130" data-bar-width="6" data-bar-spacing="6" data-bar-color="#1ccc88">10,9,11,10,11,10,12,10,9,10,11,9,8</div>-->
                      </div>
					  
                    </div>
                                        
             <center>
                <footer class="panel-footer bg-white">
                  <div class="row  no-gutter">
                   
                     <div class="form-group">
                      
                      <div class="col-sm-3">
                        <div class="radio i-checks">
 						 <label>
                            <input type="radio" value="" name="quant_verbal" id="radio1">
                            <i></i>
                            Quantitative Analysis
                          </label>                         
                        
                        </div>
                        </div>                
                        
                        <div class="col-sm-3">
                        <div class="radio i-checks">
 						 <label>
                            <input type="radio" value="" name="quant_verbal" id="radio2">
                            <i></i>
                            Verbal
                          </label>
                       
                        </div>
                        </div> 
                    </div> <!--formgroup close-->
                                      
                  </div>
                </footer>  
              </center>      
                  </section>

        	</div>
       
              </div><!-- row close -->
              
            </section>
          </section>
        </section>
   
</body>
 <script type="text/javascript">
  
  $(document).ready(function(){
  // alert("dsafdsa");
		$('#verbal').fadeOut();
		$('input:radio[id=radio2]').prop('checked',false);
		$('input:radio[id=radio1]').prop('checked', true);
		
	
		
		$("#radio1").click(function(){
				
			//alert("Entered In Quant Click");
				$('#verbal').fadeOut(0);
				$('#quant').fadeIn();
				$('#graphName').html("Quantitative Graph");

		});
		$("#radio2").click(function(){
				
		//	alert("Entered In Verbal Click");
				$('#quant').fadeOut(0);
				$('#verbal').fadeIn();
				$('#graphName').html("Verbal Graph");
		});
		
  });
  
  </script>
</html>
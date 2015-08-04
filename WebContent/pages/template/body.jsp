
<%@taglib prefix="s" uri="/struts-tags"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 






 --%>

 </head>
 <body>

	<section id="content"> <section class="vbox"> <section
		class="scrollable wrapper"> <!--row for prfile-->


	<h3>How am i doing ?</h3>

	<div class="row"  id="tour9">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">Math Overall</div>
				<div class="panel-body">

					<div class="col-lg-4">
						<!--<div class="ques-top">75%</div>
                        <div class="ques-bot">Questions correct</div> <br>-->
						<div class="panel b-a ">
							<div class="panel-heading no-border bg-primary lt text-center ud">
								<span class="m-t m-b text-white text-box text"> <s:property
										value="userDashBoardBO.math_questions_correct_percent" default="0"  />%
								</span>
							</div>
							<div class="padder-v text-center clearfix">

								<div class="col-xs-12 ud">
									<div class="p"
										style="padding-top: 12px; padding-bottom: 12px;">Questions
										Correct</div>
								</div>
							</div>
						</div>
					</div>
					<!--Inner col close-->

					<div class="col-lg-4">
						<!--<div class="ques-comp">450</div>
                        <div class="ques-bot">Questions practiced</div> <br>-->

						<div class="panel b-a">
							<div class="panel-heading no-border bg-info lt text-center ud">
								<span class="m-t m-b text-white text-box text"> <s:property
										value="userDashBoardBO.math_questions_practiced" default="0" /></span>
							</div>
							<div class="padder-v text-center clearfix">

								<div class="col-xs-12 ud">
									<div class="p"
										style="padding-top: 12px; padding-bottom: 12px;">Questions
										practiced</div>
								</div>
							</div>
						</div>
					</div>
					<!--Inner col close-->

					<div class="col-lg-4">
						<!--<div class="ques-time">1:52 </div>
                       <div class="ques-bot">average time per question</div>-->

						<div class="panel b-a">
							<div class="panel-heading no-border bg-success lt text-center ud">
								<span class="m-t m-b text-white text-box text"> <s:property
										value="userDashBoardBO.math_avg_time" default="00:00:00" /></span>
							</div>
							<div class="padder-v text-center clearfix">

								<div class="col-xs-12 ud">
									<div class="p"
										style="padding-top: 12px; padding-bottom: 12px;">Avg
										time per question</div>
								</div>
							</div>
						</div>

					</div>
					<!--Inner col close-->

				</div>
				<!--Panel body closing-->
			</div>
			<!--Panel head close-->
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
							<div class="panel-heading no-border bg-primary lt text-center ud">
								<span class="m-t m-b text-white text-box text"> <s:property
										value="userDashBoardBO.verbal_questions_correct_percent" default="0" />%
								</span>
							</div>
							<div class="padder-v text-center clearfix">

								<div class="col-xs-12 ud">
									<div class="p"
										style="padding-top: 12px; padding-bottom: 12px;">Questions
										Correct</div>
								</div>
							</div>
						</div>
					</div>
					<!--Inner col close-->

					<div class="col-lg-4">
						<!--<div class="ques-comp">450</div>
                        <div class="ques-bot">Questions practiced</div> <br>-->

						<div class="panel b-a">
							<div class="panel-heading no-border bg-info lt text-center ud">
								<span class="m-t m-b text-white text-box text"> <s:property
										value="userDashBoardBO.verbal_questions_practiced" default="0"/></span>
							</div>
							<div class="padder-v text-center clearfix">

								<div class="col-xs-12 ud">
									<div class="p"
										style="padding-top: 12px; padding-bottom: 12px;">Questions
										practiced</div>
								</div>
							</div>
						</div>
					</div>
					<!--Inner col close-->

					<div class="col-lg-4">
						<!--<div class="ques-time">1:52 </div>
                       <div class="ques-bot">average time per question</div>-->

						<div class="panel b-a">
							<div class="panel-heading no-border bg-success lt text-center ud">
								<span class="m-t m-b text-white text-box text"> <s:property
										value="userDashBoardBO.verbal_avg_time" default="00:00:00"/></span>
							</div>
							<div class="padder-v text-center clearfix">

								<div class="col-xs-12 ud">
									<div class="p"
										style="padding-top: 12px; padding-bottom: 12px;">Avg
										time per question</div>
								</div>
							</div>
						</div>

					</div>
					<!--Inner col close-->

				</div>
				<!--Panel body closing-->
			</div>
			<!--Panel head close-->
		</div>

		<!--Verbal overall col for panel close-->
	</div>
	<!--Row close for Verbal panel close-->

	<div class="row" id="tour10">
		<div class="col-lg-6">
			<div class="panel panel-default ">
				<div class="panel-heading">Top 3 Strengths</div>
				<div class="panel-body" id="blur1">

					<table class="table table-responsive">
						<tr>
 
							<td class=""><span class="btn btn-success">Skills</span></td>
							<td class=""><span class="btn btn-success">Correct</span></td>
							<td class=""><span class="btn btn-success hidden-xs">Skill Percent</span></td>
						</tr>
						<tr>
							<td class="" id="top1Cat"> </td>
							<td class="" id="top1Per"> </td>
							<td class="">
								<div
									class="progress progress-sm progress-striped active hidden-xs">
									<div id="top1progress" class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="10" aria-valuemin="0"
										aria-valuemax="100" style="width: 0%">
										<span class="sr-only" >10% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="" id="top2Cat"> </td>
							<td class="" id="top2Per"> </td>
							<td class="">
								<div
									class="progress progress-sm progress-striped active hidden-xs">
									<div id="top2progress" class="progress-bar progress-bar-danger"
										role="progressbar" aria-valuenow="90" aria-valuemin="0"
										aria-valuemax="100" style="width:  0%">
										<span class="sr-only" >45% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="" id="top3Cat"> </td>
							<td class="" id="top3Per"> </td>
							<td class="">
								<div
									class="progress progress-sm progress-striped active hidden-xs">
									<div  id="top3progress" class="progress-bar progress-bar-warning"
										role="progressbar" aria-valuenow="90" aria-valuemin="0"
										aria-valuemax="100" style="width:0%">
										<span class="sr-only">65% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						<tr class="hidden-xs hidden-sm">
							<td></td>
							<td></td>
							<td class="pull-right nlheight"><a href="skillData.action"
								class="btn green">More Strengths</a></td>
						</tr>
					</table>



				</div>
				<!--Panel body closing-->
			</div>
			<!--Panel head close-->
		</div>
		<!--Top 3 Oppurtunities col for panel close-->


		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">Top 3 Oppurtunities</div>
				<div class="panel-body" id="blur2">

					<table class="table table-responsive">
						<tr>
							<td class=""><span class="btn btn-success">Skills</span></td>
							<td class=""><span class="btn btn-success">Correct</span></td>
							<td class=""><span class="btn btn-success hidden-xs">Skill
									Percent</span></td>
						</tr>
						<tr>
							<td class="" id="least4Cat"> </td>
							<td class="" id="least4Per"> </td>
							<td class="">
								<div
									class="progress progress-sm progress-striped active hidden-xs">
									<div id="least4progress" class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="90" aria-valuemin="0"
										aria-valuemax="100" style="width:  0%">
										<span class="sr-only" >90% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="" id="least5Cat"> </td>
							<td class="" id="least5Per"> </td>
							<td class="">
								<div
									class="progress progress-sm progress-striped active hidden-xs">
									<div id="least5progress" class="progress-bar progress-bar-danger"
										role="progressbar" aria-valuenow="90" aria-valuemin="0"
										aria-valuemax="100" style="width: 0%">
										<span class="sr-only" >45% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="" id="least6Cat"> </td>
							<td class="" id="least6Per"> </td>
							<td class="">
								<div
									class="progress progress-sm progress-striped active hidden-xs">
									<div id="least6progress" class="progress-bar progress-bar-warning"
										role="progressbar" aria-valuenow="90" aria-valuemin="0"
										aria-valuemax="100" style="width: 0%">
										<span class="sr-only" >65% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						<tr class="hidden-xs hidden-sm">
							<td></td>
							<td></td>
							<td class="pull-right nlheight"><a href="skillData.action"
								class="btn green">More Oppurtunities</a></td>
						</tr>
					</table>


				</div>
				<!--Panel body closing-->
			</div>
			<!--Panel head close-->
		</div>
		<!--Verbal overall col for panel close-->





	</div>
	<!--Row close for Verbal panel close-->


	<div class="row"  id="tour11">
		<div class="col-lg-4">
			<div class="panel panel-default" style="min-height: 300px;">
				<div class="panel-heading">

					<div class="panel-title">
						What should i do now ?
						<p class="pull-right hidden-xs" style="padding-top: 0px;">
							<a href="#" class="btn btn-success btn-xs "> Go to my study
								plan</a>
						</p>
					</div>

				</div>
				<div class="panel-body" id="blur3">
					<div class="row">
						<div class="col-lg-12">
							<h4>Based on your practice score we recommend you<s:iterator value="skillbasedlessons" >hai</s:iterator></h4>
						 <div id="skillBasedLessons"> 
						 </div>
			<!-- 				<table class="table">
								<tr>
									<td>Take the <a href="#" class="btn link-lesson">oppurtunity
											Lessson #5</a>
									</td>
								</tr>
								<tr>
									<td>Take the <a href="#" class="btn link-lesson">oppurtunity
											Lessson #2</a>
									</td>
								</tr>
								<tr>
									<td>Take the <a href="#" class="btn link-lesson">oppurtunity
											Lessson #5</a>
									</td>
								</tr>
								<tr>
									<td>Take the <a href="#" class="btn link-lesson">oppurtunity
											Lessson #5</a>
									</td>
								</tr>
								<tr>
									<td>Take the <a href="#" class="btn link-lesson">oppurtunity
											Lessson #5</a>
									</td>
								</tr>
								<tr>
									<td>Take the <a href="#" class="btn link-lesson">oppurtunity
											Lessson #5</a>
									</td>
								</tr>
							</table> -->
							<a href="#" class="btn green"> See More Recomendations</a>

						</div>
					</div>

				</div>
			</div>
		</div>


		<div class="col-lg-8">

			<section class="panel panel-default"> <header
				class="panel-heading panel-title">
			<div id="graphName">Quantitative Graph</div>
			</header>

			<div class="bg-light dk wrapper" id="blur4">
				<span class="pull-right h4"><div id="highestScore">Heighest
						Score : 165</div></span> <span class="h4"><div id="leastScore">Lowest
						Score : 155</div></span>
				<div class="text-center m-b-n m-t-sm">



					<div class="sparkline" data-type="line" data-height="205"
						data-width="100%" data-line-width="2" data-line-color="#dddddd"
						data-spot-color="#bbbbbb" data-fill-color=""
						data-highlight-line-color="#fff" data-spot-radius="3"
						data-resize="true" values="147,149,155,158,155,158,164"
						id="verbal" style="display: block;">
						<canvas width="951" height="195"
							style="display: inline-block; width: 951px; height: 195px; vertical-align: top;"></canvas>
					</div>

					<div class="sparkline" data-type="line" data-height="205"
						data-width="100%" data-line-width="2" data-line-color="#dddddd"
						data-spot-color="#bbbbbb" data-fill-color=""
						data-highlight-line-color="#fff" data-spot-radius="3"
						data-resize="true" values="155,148,160,162,158,161,158" id="quant"
						style="display: block;">
						<canvas width="951" height="195"
							style="display: inline-block; width: 951px; height: 195px; vertical-align: top;"></canvas>
					</div>


					<!-- <div class="sparkline inline" data-type="bar" data-height="130" data-bar-width="6" data-bar-spacing="6" data-bar-color="#1ccc88">10,9,11,10,11,10,12,10,9,10,11,9,8</div>-->
				</div>

			</div>

			<center>
				<footer class="panel-footer bg-white">
				<div class="row  no-gutter">

					<div class="form-group">

						<div class="col-sm-3">
							<div class="radio i-checks">
								<label> <input type="radio" value="" name="quant_verbal"
									id="radio1"> <i></i> Quantitative Analysis
								</label>

							</div>
						</div>

						<div class="col-sm-3">
							<div class="radio i-checks">
								<label> <input type="radio" value="" name="quant_verbal"
									id="radio2"> <i></i> Verbal
								</label>

							</div>
						</div>
					</div>
					<!--formgroup close-->

				</div>
				</footer>
			</center>
			</section>

		</div>

	</div>
	<!-- row close --> 
	<div class="row">
	<div class="col-lg-12">        
                
        <div class="panel panel-profile">
              <div class="panel-heading">
                <h4 class="panel-title"> Hi Baji </h4>
              </div>
              <div class="panel-body">              
               <!--this row is for profile box-->
               
                                                            
               
               <div class="row">        		
                
                <!--this is for profile image box-->
        		<div class="col-lg-2" style="padding-left:40px;">
          		              		
                     <center><img src="assets/images/a0.png" class="img img-responsive img-thumbnail"><br>
                     <a href="#" class="btnpro btn-info"> Change Profile Pic </a><br>                     
                     </center>    			 
        		</div>
                
                <div class="col-lg-4" style="padding-left:40px;">
                <table class="table">
                <tbody>
                
  				<tr><td>Email </td>      <td class="names"> User@gmail.com</td></tr>
                <tr><td class="sidename">Test Date </td>  <td class="names"> 16/9/2014</td></tr><tr><td>Phone </td>      <td class="names"> XXX-9898-8999</td> </tr><tr><td class="sidename">Target Score </td>  <td class="names"> 320</td></tr>
                
                <tr><td class="sidename">Actual Score </td>  <td class="names"> 311</td></tr></tbody></table>                         		              		                                           			 
        		</div>
                                
                
                <!--inner col-4 close-->
                
                
        
        		</div>
                <!-----inner row close----->
            
                
                
              </div>
              </div>
              </div>
	</div>
	</section> </section> </section>

 

<script type="text/javascript">
	$(document).ready(function() {
		
		
		//$.cookie("test",0);
		//alert("be ready");
		
		 
		 $('.introjs-skipbutton').click(function(){
			// alert("json cllaing");
			/* var myVar = $("#introjs-tooltipbuttons").find('.introjs-button introjs-skipbutton').text();
			alert(myVar); */
			
			    //alert($(this).text());
			
			if($(this).text()=='Skip'){
				
				  $.ajax({
					  type : 'POST',
					  url  : 'updatelogincount.action',	
					  dataType : 'json',
					  success : success,
					  error : error
						
						 
					 }); 
				  
					}else if($(this).text()=='Done'){
						
						 $.ajax({
							  type : 'POST',
							  url  : 'tourcompletion.action',	
							  dataType : 'json',
							  success : success,
							  error : error
								
								 
							 });
			}
			 
		 });
		 
		$('#verbal').fadeOut();
		$('input:radio[id=radio2]').prop('checked', false);
		$('input:radio[id=radio1]').prop('checked', true);

		$("#radio1").click(function() {

			//alert("Entered In Quant Click");
			$('#verbal').fadeOut(0);
			$('#quant').fadeIn();
			$('#graphName').html("Quantitative Graph");

		});
		$("#radio2").click(function() {

			//	alert("Entered In Verbal Click");
			$('#quant').fadeOut(0);
			$('#verbal').fadeIn();
			$('#graphName').html("Verbal Graph");
		});
		
		$.ajax({
			tye : 'POST',
			url : 'getTop3Skills.action',
			dataType : 'json',
			success : skillsready,
			errror : Error
		});

		function skillsready(jsonData) {
			// alert(jsonData.skillBasedLessons);
			var lessonData=jsonData.skillBasedLessons;
			var skillBasedLessonsDivData="<table class=table>";
			 
			 $(lessonData).each(function(index) {
				 var array=lessonData[index]; 
				 //skillBasedLessonsDivData+="<tr><td>Take the lesson<a href=gotoLesson.action?lessonId="+array[0]+" class=btn link-lesson>"+array[2]+"</a></td></tr>";
				/*  skillBasedLessonsDivData+="<tr><td>Take the lesson<a href=javascript:setDisplayLessons("+array[0]+","+array[3]+") class=btn link-lesson>"+array[2]+"</a></td></tr>"; */
				 //skillBasedLessonsDivData+="<tr><td>Take the lesson<a href=gotoRecommendedLesson.action?lessonsmasterBO.sublesson_id="+array[0]+" class=btn link-lesson>"+array[2]+"</a></td></tr>";
				 skillBasedLessonsDivData+="<tr><td>Take the lesson<a href=Get-Lessons-List.action?lessonsmasterBO.sublesson_id="+array[0]+"&pagesIs=page1 class='btn link-lesson'>"+array[2]+"</a> in "+array[4]+"</td></tr>";
			 });
			 skillBasedLessonsDivData+="</table>";
			  $('#skillBasedLessons').html(skillBasedLessonsDivData);
			var data=jsonData.data1;
			 $( data ).each(function(index) {
				  var array=data[index]; 
				  if(index<3)
					  {
					 // alert("if");
				  var catName="#top"+(index+1)+"Cat";
				  var per="#top"+(index+1)+"Per";
				  var progress="#top"+(index+1)+"progress";
				//  alert(catName+"\t"+per+"\t"+progress);
				  if(catName!='null')
						{
				  $(catName).html(array[0]);
				  if(array[1]==null) array[1]=0;
				  $(per).html(array[1]+"%");
			  	//  $(progress).html(array[4]);
			  	$(progress).css('width',Math.round(array[1])+"%");
						}
				else
					{
					  $(catName).html('');
					  $(per).html(''); 
					  $(progress).css('width','');
					}
				
				
				
				
				
				//alert(array[4]);
				$(progress).removeClass();
				if(array[1]>=80)
					{
					$(progress).addClass('progress-bar progress-bar-success');
					}
				else if(array[1]>=50 && array[1]<80)
					{
					$(progress).addClass('progress-bar progress-bar-warning');
					}
				else
					{
					$(progress).addClass('progress-bar progress-bar-danger');
					}
					  }
				  else
					  {
					 // alert("else");
					  var catName="#least"+(index+1)+"Cat";
					  var per="#least"+(index+1)+"Per";
					  var progress="#least"+(index+1)+"progress";
					  if(catName!='null')
						{
					  $(catName).html(array[0]);
					  if(array[1]==null) array[1]=0;
					  $(per).html(array[1]+"%");
					 // $(progress).html(array[4]);
					  $(progress).css('width',Math.round(array[1])+"%");
					  $(progress).removeClass();
						}
					  else
						{
						  $(catName).html('');
						  $(per).html(''); 
						  $(progress).css('width','');
						}
					  
					  
					  if(array[1]>=80)
						{
						$(progress).addClass('progress-bar progress-bar-success');
						}
					else if(array[1]>=50 && array[1]<80)
						{
						$(progress).addClass('progress-bar progress-bar-warning');
						}
					else
						{
						$(progress).addClass('progress-bar progress-bar-danger');
						}
					  }
				 
				  });
			
		 

		}
		function Error(errorIs) {
			alert(' Error');
		}
		
		function success(){
			
		}
		function error(){
			
		}
	
	});
</script>

<%-- <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script> --%>
<link rel="stylesheet" href="assets/css/userdashboard.css" type="text/css" />
<!-- Sparkline Chart -->
<script src="assets/js/charts/sparkline/jquery.sparkline.min.js"></script>
<script src="assets/js/charts/flot/jquery.flot.min.js"></script>
<script src="assets/js/charts/flot/jquery.flot.tooltip.min.js"></script>
<script src="assets/js/charts/flot/jquery.flot.spline.js"></script>
<script src="assets/js/charts/flot/jquery.flot.pie.min.js"></script>
<script src="assets/js/charts/flot/jquery.flot.resize.js"></script>
<script src="assets/js/charts/flot/jquery.flot.grow.js"></script>
<script src="assets/js/charts/flot/demo.js"></script>



<script src="assets/js/foggy/jquery.foggy.js"></script>
<!-- Tour Demo Js & CSS -->

<s:if test="%{#session.logincount==0}">
<link rel="stylesheet" href="assets/js/intro/introjs.css" type="text/css" /> 
	  <script src="assets/js/intro/intro.min.js"></script>
	  <script src="assets/js/intro/demo.js"></script>
	  <script type='text/javascript'>
      $(document).ready( function(){
    	  $('#blur1,#blur2,#blur3,#blur4').addClass('diss');
        $('.diss').foggy();
        $('.foggy-demo-manual').foggy({
          cssFilterSupport: false
        });
       
      });
    </script>  
</s:if>

<!-- Tour Demo Js & CSS -->
</body>
</html>
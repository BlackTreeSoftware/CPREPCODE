<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
   <link rel="stylesheet" href="assets/css/review.css" type="text/css" />  
    <!--[if lt IE 9]>
  <link rel="stylesheet" href="/assets/css/mentor/studentsectionprogress.css" type="text/css" />
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
  
 
  
</head>
<body>

<section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">
               	
              <div class="row" style="padding-left:0px;">              
              <div class="col-sm-12 ">
              <s:hidden id="questionTypes_quant" value="%{#request.questionTypes_quant}"/>
              <s:hidden id="correctCount_quant" value="%{#request.correctCount_quant}"/>
              <s:hidden id="wrongCount_quant" value="%{#request.wrongCount_quant}"/>
              
              <s:hidden id="questionTypes_verbal" value="%{#request.questionTypes_verbal}"/>
              <s:hidden id="correctCount_verbal" value="%{#request.correctCount_verbal}"/>
              <s:hidden id="wrongCount_verbal" value="%{#request.wrongCount_verbal}"/>
              
              	 <section class="panel panel-default">
                    <header class="panel-heading bg-light" >
                    <ul class="nav nav-tabs nav-justified">
                        <li class="active"><a href="#math" data-toggle="tab">Math</a></li>
                        <li class=""><a href="#verbal" data-toggle="tab">Verbal</a></li>                      
                      </ul>
                    </header>
                    <div class="panels-body">
                      <div class="tab-content">
                        <div class="tab-pane active" id="math">
                        
                        		<div class="panels-body">
                                           
                                            <div class="row main-box">
                
                                              <div class="col-md-4 col-sm-4">
                                                  <div class="panel b-a">  
                                                   <div class="panel-heading no-border bg-success lt text-center">
                                                    <div class="h5 padder-v text-white">Total Lessons</div>  
                                                      <span class="main_text text-white">
                                                      ${userProgressBO.totalQuantLessons}
                                                      </span>                           
                                                    </div>                                                
                                                  </div>
                                                </div><!--End Your percent correct-->
                                               
                                                <div class="col-md-4 col-sm-4">
                                                  <div class="panel b-a">  
                                                    <div class="panel-heading no-border bg-danger lt text-center">
                                                    <div class="h5 padder-v text-white">Lessons Finished</div>  
                                                      <span class="main_text text-white">
                                                      ${userProgressBO.totalQuantLessonsCompleted}
                                                      </span>                         
                                                    </div>                                                
                                                  </div>
                                                </div><!--End Your AVG PACE-->
                                               
                                                <div class="col-md-4 col-sm-4">
                                                  <div class="panel b-a">  
                                                    <div class="panel-heading no-border bg-dark lt text-center">
                                                    <div class="h5 padder-v text-white">Total Time Spent</div>  
                                                      <span class="main_text text-white">
                                                      ${userProgressBO.totalSpentForQuantLessons}
                                                      </span>
                                                       </div>
                                                    </div>
                                                </div><!--End Others' Average Pace -->
                                            
                                            </div><!--Row closed-->
                                            <hr class="">
                                            
                                            <div class="row">
                								
                                                 <div class="col-lg-12">
                                                  <section class="panel panel-default">                                                   
                                                    
                                                    <div class="panel-body text-center">
                                                                <div id="containermath" style=" height: 400px; "></div>
                                                    </div>  
                                                      
                                                 </section>
                                                </div>
                                                                                              
                                            </div>
                                            
                                                                          
                   			 </div><!--panel body close-->
                        </div>
                        <!-------MATH TAB END----->
                        <div class="tab-pane" id="verbal">
                        
                        	<div class="panels-body">
                                           
                                            <div class="row main-box">
                
                                              <div class="col-md-4 col-sm-4">
                                                  <div class="panel b-a">  
                                                   <div class="panel-heading no-border bg-success lt text-center">
                                                    <div class="h5 padder-v text-white">Total Lessons</div>  
                                                      <span class="main_text text-white">
                                                       ${userProgressBO.totalVerbalLessons}
                                                      </span>                           
                                                    </div>                                                
                                                  </div>
                                                </div><!--End Your percent correct-->
                                               
                                                <div class="col-md-4 col-sm-4">
                                                  <div class="panel b-a">  
                                                    <div class="panel-heading no-border bg-danger lt text-center">
                                                    <div class="h5 padder-v text-white">Lessons Finished</div>  
                                                      <span class="main_text text-white">
                                                       ${userProgressBO.totalVerbalLessonsCompleted}
                                                      </span>                         
                                                    </div>                                                
                                                  </div>
                                                </div><!--End Your AVG PACE-->
                                               
                                                <div class="col-md-4 col-sm-4">
                                                  <div class="panel b-a">  
                                                    <div class="panel-heading no-border bg-dark lt text-center">
                                                    <div class="h5 padder-v text-white">Total Time Spent</div>  
                                                      <span class="main_text text-white">
                                                      ${userProgressBO.totalSpentForVerbalLessons}
                                                      </span>
                                                       </div>
                                                    </div>
                                                </div><!--End Others' Average Pace -->
                                            
                                            </div><!--Row closed-->
                                            <hr class="">
                                            
                                            <div class="row">
                								
                                                 <div class="col-lg-12">
                                                  <section class="panel panel-default">                                                   
                                                    
                                                    <div class="panel-body text-center">
                                                                <div id="container" style=" height: 400px; "></div>
                                                    </div>  
                                                      
                                                 </section>
                                                </div>
                                                                                              
                                            </div>
                                            
                                                                          
                   			 </div><!--panel body close-->
                        
                        
                        </div>                        
                      </div>
                    </div>
                  </section>
                  </div>
                  </div>
    			
              
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        
        
   
<script src="assets/js/highcharts.js"></script>  



  <script type="text/javascript">
  
  $(document).ready(function(){
	 //alert('Question types:'+$('#questionTypes').val()+"\tcorrect:"+$('#correctCount').val()+"\tWrong count:"+$('#wrongCount').val());  
	// alert('Question types:'+$('#questionTypes_verbal').val()+"\tcorrect:"+$('#correctCount_verbal').val()+"\tWrong count:"+$('#wrongCount_verbal').val());
	  $('#containermath').highcharts({
          chart: {
              type: 'column'
          },
			colors: ['#e33244','#1aae88'],
          title: {
              text: 'Stacked column chart'
          },
          xAxis: { 
              categories: JSON.parse($('#questionTypes_quant').val())
          },
          yAxis: {
              min: 0,
              title: {
                  text: '% of Practice Session'
              }
          },
          tooltip: {
              pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
              shared: true
          },
          plotOptions: {
              column: {
                  stacking: 'percent',
					dataLabels: {
                      enabled: true,
                      color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                      style: {
                          /*textShadow: '0 0 3px black, 0 0 3px black'*/
                      }
                  }
              }
          },
              series: [{
              name: 'Wrong', 
              data: JSON.parse($('#wrongCount_quant').val())
          }, {
              name: 'Correct', 
              data: JSON.parse($('#correctCount_quant').val())
          }]
      });
  });

  
		
		
		
							
		</script>
		
		<script>
		$('#container').highcharts({
            chart: {
                type: 'column'
            },
			colors: ['#e33244','#1aae88'],
            title: {
                text: 'Stacked column chart'
            },
            xAxis: {
            	 categories: JSON.parse($('#questionTypes_verbal').val())
            },
            yAxis: {
                min: 0,
                title: {
                    text: '% of Practice Session'
                }
            },
            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                shared: true
            },
            plotOptions: {
                column: {
                    stacking: 'percent',
					dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            /*textShadow: '0 0 3px black, 0 0 3px black'*/
                        }
                    }
                }
            },
                series: [{
                name: 'Wrong',
                data: JSON.parse($('#wrongCount_verbal').val())
            }, {
                name: 'Correct',
                data: JSON.parse($('#correctCount_verbal').val())
            }]
        });
		
		</script>


</body>
</html>
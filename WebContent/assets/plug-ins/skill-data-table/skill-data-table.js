/* Formatting function for row details - modify as you need */
function format ( d ) {
   
		/*	return '<div class="row" style="padding:12px;>'+
		    '<div class="col-xs-12">'+
			'<div class="col-xs-12"><span class="text-dkr"><h4 class="m-t-0">Quantitative / GRE Numeric Entry / Difficulty : Medium</h4></span> </div>'+
			'<div class="col-xs-12"><span class="font-15 text-dkr">You have answered this question</span> </div>'+
            '<div class="col-lg-8 col-sm-8 col-xs-12">'+
			'<hr><div class="row p-b-10"> <div class="col-xs-6"><i class="fa fa-2x fa-check-circle text-success"></i> <span class="h3 text-success p-l-10">CORRECT</span> <p class="font-15 m-t-5">You are among <span class="font-bold text-success">14%</span> people who got this question correct</p></div>  <div class="col-xs-6"><i class="fa fa-2x fa-clock-o text-primary"></i> <span class="h3 text-primary p-l-10">01 <span class="h5">min</span> 52 <span class="h5">sec</span></span> <p class="font-15 m-t-5">Your pace is better than others pace: <span class="font-bold text-primary">01 min 52 sec</span></p></div> </div> '+
			'<hr class="hr-less">'+
			' <div class="row p-t-10"> <div class="col-xs-12"> <span class="col-xs-4 font-15"> <span class="font-bold text-success p-r-10">41%</span> Guessed</span>'+ '<span class="col-xs-4 font-15 "> <span class="font-bold text-success p-l-10 p-r-10">59%</span> Guessed Incorrectly</span>'+'<span class="col-xs-4 font-15"> <span class="font-bold text-success p-l-10 p-r-10">13%</span> Flagged</span></div></div>'+
			'</div>'+
			
			'<div class="col-lg-4 col-sm-4 col-xs-12 text-primary">'+ 
			'<hr> <p class="h4 m-t-15">Question attempt history</p><hr class="hr-less"><span class="font-15"><i class="fa fa-check-circle text-success"></i><span class="p-l-10 p-r-10">Correct</span><i class="fa fa-arrow-right"></i> <i class="fa fa-clock-circle"></i> <span class="p-l-10 p-r-10">01 min 52 sec (Jul 23<sup>rd</sup>, 23:11:20)</span></span>'+
			'<br><span class="font-15"><i class="fa fa-times-circle text-danger"></i><span class="p-l-10 p-r-10 text-danger"> Wrong</span><i class="fa fa-arrow-right"></i> <i class="fa fa-clock-circle"></i> <span class="p-l-10 p-r-10">01 min 52 sec (Jul 23<sup>rd</sup>, 23:11:20)</span></span>'           					                 	
			'</div>'
			;*/
		}
 
$(document).ready(function() { 
    var table = $('#expandable-table').DataTable( {
        "data": [
    {
      "Skill": "Sentence Equvilance",
      "Questions Answered": "200/350",
      "Avg Time": '<i class="fa fa-clock-o text-success"></i> 2 : 65 ',     
	  "Accuracy": '<div class="progress"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuetransitiongoal="90"></div></div><script type="text/javascript">$(document).ready(function(){ $(".progress-bar").progressbar({display_text: "fill"});});</script>'
    },
	
	{
      "Skill": "Sentence Equvilance",
      "Questions Answered": "200/350",
      "Avg Time": '<i class="fa fa-clock-o text-success"></i> 2 : 65 ',     
	  "Accuracy": '<div class="progress"><div class="progress-bar progress-bar-info" role="progressbar" aria-valuetransitiongoal="75"></div></div><script type="text/javascript">$(document).ready(function(){ $(".progress-bar").progressbar({display_text: "fill"});});</script>'
    },
	{
      "Skill": "Sentence Equvilance",
      "Questions Answered": "200/350",
      "Avg Time": '<i class="fa fa-clock-o text-success"></i> 2 : 65 ',     
	  "Accuracy": '<div class="progress"><div class="progress-bar progress-bar-warning" role="progressbar" aria-valuetransitiongoal="60"></div></div><script type="text/javascript">$(document).ready(function(){ $(".progress-bar").progressbar({display_text: "fill"});});</script>'
    },
	{
      'Skill': 'Sentence Equvilance',
      "Questions Answered": "200/350",
      "Avg Time": '<i class="fa fa-clock-o text-success"></i> 2 : 65 ',     
	  "Accuracy": '<div class="progress"><div class="progress-bar progress-bar-danger" role="progressbar" aria-valuetransitiongoal="35"></div></div><script type="text/javascript">$(document).ready(function(){ $(".progress-bar").progressbar({display_text: "fill"});});</script>'
    
	},
	
	{
      "Skill": "Sentence Equvilance",
      "Questions Answered": "200/350",
      "Avg Time": '<i class="fa fa-clock-o text-success"></i> 2 : 65',     
	  "Accuracy": '<div class="progress"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuetransitiongoal="92"></div></div><script type="text/javascript">$(document).ready(function(){ $(".progress-bar").progressbar({display_text: "fill"});});</script>'
    }
	
	],
        "columns": [ 
			
            { "data": "Skill" },
            { "data": "Questions Answered" },
            { "data": "Avg Time" },            
			{ "data": "Accuracy" }
        ],
        "order": [[1, 'asc']]
    } );
	
	$('#expandable-table_length,#expandable-table_filter').hide();
     
    // Add event listener for opening and closing details
 
} );
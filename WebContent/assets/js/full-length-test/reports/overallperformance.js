$(document).ready(function(){
	
	drawTable();
	getOverallPerformanceData();
	
});

function drawTable(){
	
	var oTable = $('#table').dataTable( {

	    "bProcessing": true,
	    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
	    "sPaginationType": "full_numbers",
	    "aoColumns": [
	      { "mData": "columnname1" },
	      { "mData": "columnname2" },
	      { "mData": "columnname3" }
	     
	    ]
	  } );
}

function getOverallPerformanceData(){
	
	$.ajax({ 
  		type : "POST",
  		url :"Get-Overall-Performance-Data.action",
  		dataType:"json",
  		success : setPerformanceData,
  		error: errorOccuredAtPerformance
  	});
}

function setPerformanceData(performance){
	
	$('#correct').text(performance.correct);
	$('#incorrect').text(performance.incorrect);
	$('#totaltime').text((performance.totaltime=='null')?"00:00:00":performance.totaltime);
	$('#guessed').text((performance.guessed=='null')?"0":performance.guessed);
	$('#flagged').text((performance.flagged=='null')?"0":performance.flagged);
	$('#quantCorrectTime').text((performance.quantCorrectTime=='null')?"00:00:00":performance.quantCorrectTime);
	$('#verbalCorrectTime').text((performance.verbalCorrectTime=='null')?"00:00:00":performance.verbalCorrectTime);
	$('#quantIncorrectTime').text((performance.quantIncorrectTime=='null')?"00:00:00":performance.quantIncorrectTime);
	$('#verbalIncorrectTime').text((performance.verbalInorrectTime=='null')?"00:00:00":performance.verbalInorrectTime);
	chart(performance.correct,performance.incorrect);
	
}


function errorOccuredAtPerformance(){
	
	alert("error occured");
}

function chart(correct,incorrect){
	
	 $('#chart2').highcharts({
         chart: {
             plotBackgroundColor: null,
             plotBorderWidth: null,
             plotShadow: false
         },
         title: {
             text: ''
         },
         tooltip: {
     	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
         },
         plotOptions: {
             pie: {
                 allowPointSelect: true,
                 cursor: 'pointer',
                 dataLabels: {
                     enabled: false
                 },
                 showInLegend: false,
					colors: "#27d4a8,#e0e6f0".split(",")
             }
         },
         series: [{
             type: 'pie',
             name: 'Browser share',
             data: [
                 ['Correct',   parseInt(correct)],
                 ['Incorrect', parseInt(incorrect)]
                 
             ]
         }]
     });
}
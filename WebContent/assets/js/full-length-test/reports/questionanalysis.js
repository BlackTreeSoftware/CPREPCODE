
$(document).ready(function(){
	
	setAnalysisData($('#qaData').val(),$('#fastSlowData').val());
	
	
});

function drawTable(){
	
	var oTable = $('#table').dataTable( {

	    "bProcessing": true,
	    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
	    "sPaginationType": "full_numbers",
	    "aoColumns": [
	      { "mData": "columnname1" },
	      { "mData": "columnname2" },
	      { "mData": "columnname3" },
	      { "mData": "columnname4" },
	      { "mData": "columnname5" },
	      { "mData": "columnname6" },
	      { "mData": "columnname7" }
	     
	    ]
	  } );
	
}


function setAnalysisData(analysis,fastslowdata){
	
	
	var JSONObj = JSON.parse(analysis);
	var fsJson = JSON.parse(fastslowdata);
	
	$('#_totalQuestions').text(JSONObj.totalpoolquestions);
	$('#_solvedQuestions').text(JSONObj.solvedcount);
	$('#_leftQuestions').text(JSONObj.Leftquestions);
	$('#_totalAnswered').text(JSONObj.solvedcount);
	$('#_correctQuestions').text(JSONObj.correctcount);
	$('#_incorrectQuestions').text(JSONObj.Incorrectcount);
	drawCharts(parseInt(JSONObj.solvedcount),parseInt(JSONObj.Leftquestions),parseInt(JSONObj.correctcount),parseInt(JSONObj.Incorrectcount));
	drawTable();
    
    $('#qcf').text(((fastslowdata.indexOf("QMinCorrect")<=-1)?"00:00:00 ":fsJson.QMinCorrect.UserTime)+" secs");
    $('#qcs').text(((fastslowdata.indexOf("QMaxCorrect")<=-1)?"00:00:00 ":fsJson.QMaxCorrect.UserTime)+" secs");
	$('#vcf').text(((fastslowdata.indexOf("VMinCorrect")<=-1)?"00:00:00 ":fsJson.VMinCorrect.UserTime)+" secs");
	$('#vcs').text(((fastslowdata.indexOf("VMaxCorrect")<=-1)?"00:00:00 ":fsJson.VMaxCorrect.UserTime)+" secs");
	
	$('#qwf').text(((fastslowdata.indexOf("QMinINCorrect")<=-1)?"00:00:00 ":fsJson.QMinINCorrect.UserTime)+" secs");
	$('#qws').text(((fastslowdata.indexOf("QMaxINCorrect")<=-1)?"00:00:00 ":fsJson.QMaxINCorrect.UserTime)+" secs");
	$('#vwf').text(((fastslowdata.indexOf("VMinINCorrect")<=-1)?"00:00:00 ":fsJson.VMinINCorrect.UserTime)+" secs");
	$('#vws').text(((fastslowdata.indexOf("VMaxINCorrect")<=-1)?"00:00:00 ":fsJson.VMaxINCorrect.UserTime)+" secs");
	
	$('#qcfTitle').html(((fastslowdata.indexOf("QMinCorrect")<=-1)?"No Question Found":fsJson.QMinCorrect.QuestionTitle));
    $('#qcsTitle').html(((fastslowdata.indexOf("QMaxCorrect")<=-1)?"No Question Found":fsJson.QMaxCorrect.QuestionTitle));
	$('#vcfTitle').html(((fastslowdata.indexOf("VMinCorrect")<=-1)?"No Question Found":fsJson.VMinCorrect.QuestionTitle));
	$('#vcsTitle').html(((fastslowdata.indexOf("VMaxCorrect")<=-1)?"No Question Found":fsJson.VMaxCorrect.QuestionTitle));
	
	$('#qwfTitle').html(((fastslowdata.indexOf("QMinINCorrect")<=-1)?"No Question Found":fsJson.QMinINCorrect.QuestionTitle));
    $('#qwsTitle').html(((fastslowdata.indexOf("QMaxINCorrect")<=-1)?"No Question Found":fsJson.QMaxINCorrect.QuestionTitle));
	$('#vwfTitle').html(((fastslowdata.indexOf("VMinINCorrect")<=-1)?"No Question Found":fsJson.VMinINCorrect.QuestionTitle));
	$('#vwsTitle').html(((fastslowdata.indexOf("VMaxINCorrect")<=-1)?"No Question Found":fsJson.VMaxINCorrect.QuestionTitle));
	
	
	
	//alert(JSONObj.solvedcount+"  "+JSONObj.Leftquestions+"  "+JSONObj.correctcount+" "+JSONObj.Incorrectcount);
	
}

function errorOccuredAtAnalysis(){
	
}

function drawCharts(solved,left,correct,incorrect){
	
	$('#chart3').highcharts({
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
                colors: "#2980b9,#7f8c8d".split(",")
            }
        },
        series: [{
            type: 'pie',
            name: 'Percentage',
            data: [
                ['Solved : <b>'+solved+'</b>',   solved],
                ['Left : <b>'+left+'</b>',       left]
                
            ]
        }]
    });
	
	
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
				colors: "#27d4a8,#e74c3c".split(",")
            }
        },
        series: [{
            type: 'pie',
            name: 'Percentage',
            data: [
                ['Correct : <b>'+correct+'</b>',   correct],
                ['Incorrect : <b>'+incorrect+'</b>',       incorrect]
                
            ]
        }]
    });


}




function format ( d ) {
	//var his = d.hisdiv.replace(/@/g, '\"');
	var result = "<i class='fa fa-2x fa-check-circle text-success'></i> <span class='h3 text-success p-l-10'>"+d.Result+"</span>";
	if(d.Result=="WRONG") result = "<i class='fa fa-2x fa-times-circle text-danger'></i> <span class='h3 text-danger p-l-10'>"+d.Result+"</span>";
	if(d.result=="SKIPPED") result = "<i class='fa fa-2x fa-times-circle text-danger'></i> <span class='h3 text-danger p-l-10'>"+d.result+"</span>";
		return '<div class="row" style="padding:12px;>'+
	    '<div class="col-xs-12">'+
		'<div class="col-xs-12"><span class="text-dkr"><h4 class="m-t-0"> '+d.QuestionTitle+' / '+d.CategoryName+' / Difficulty :'+d.Difficulty+' </h4></span> </div>'+
		'<div class="col-xs-12"><span class="font-15 text-dkr">You have answered this question</span> </div>'+
        '<div class="col-lg-8 col-sm-8 col-xs-12">'+
		'<hr><div class="row p-b-10"> <div class="col-xs-6">'+result+''+
		'<p class="font-15 m-t-5">You are among <span class="font-bold text-success">'+d.CorrectPercentage+'%</span> people who got this question correct</p></div> '+
		' <div class="col-xs-6"><i class="fa fa-2x fa-clock-o text-primary"></i> <span class="h3 text-primary p-l-10">'+d.User_time+' </span> <p class="font-15 m-t-5">Your pace is better than others pace: <span class="font-bold text-primary">'+d.AvgTime+'</span></p></div> </div> '+
		'<hr class="hr-less">'+
		' <div class="row p-t-10"> <div class="col-xs-12"> <span class="col-xs-4 font-15"> <span class="font-bold text-success p-r-10">'+d.GuessPercentage+'%</span> Guessed</span>'+ '<span class="col-xs-4 font-15 "> <span class="font-bold text-success p-l-10 p-r-10">'+d.GuessInCorrectPercentage+'%</span> Guessed Incorrectly</span>'+'<span class="col-xs-4 font-15"> <span class="font-bold text-success p-l-10 p-r-10">'+d.FlaggedPercentage+'%</span> Flagged</span></div></div>'+
		'</div>'+			
		'<div class="col-lg-4 col-sm-4 col-xs-12 text-primary">'+ 
		'<hr> <p class="h4 m-t-15">Question attempt history</p>'+
		'<hr class="hr-less">'+d.history+'</div>'
		;
	}


$(document).ready(function() {
	
	
	var table = $('#example1').DataTable( {
		"data": JSON.parse($('#qaDataTable').val()),
		"columns": [
			{
				"class":          'details-control',
				"orderable":      false,
				"data":           null,
				"defaultContent": ''
			},
			{ "data": "Result" },
			{ "data": "CategoryName" },
			{ "data": "Guess" },
			{ "data": "Flag" },
			{ "data": "TestDate" },
			{ "data": "Reason" },
			{ "data": "RetryButton" }
		],
		"order": [[1, 'asc']]
	} );
	
	
	
	// Add event listener for opening and closing details
	$('#example1 tbody').on('click', 'td.details-control', function () {
	
	
		var tr = $(this).parents('tr');
		var row = table.row( tr );

		if ( row.child.isShown() ) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
		}
		else {
			// Open this row
			row.child( format(row.data()) ).show();
			tr.addClass('shown');
		}
	} );
	
	 $('#example1').dataTable().columnFilter({
		 
	        aoColumns: [ null,{ type: "select", values: [ 'Correct', 'Wrong','Skipped'], selected: ''  },{ type: "select", values: [ 'Problem Solving', 'Data Interpretation','Quantitative Comparision','Reading Comprehension','Sentence Equivalence','Text Completion','Analyze an Issue','Analyze an argument','Critical Reasoning'], selected: ''  },{ type: "select", values: [ 'Guessed', 'Confident'], selected: ''  },{ type: "select", values: [ 'FLAGGED', 'UNFLAG',], selected: ''  },null,{ type: "select", values: JSON.parse($('#filterReasons').val()), selected: ''  },null,
	 
			   ]
		 });
	
	
	



} );





    


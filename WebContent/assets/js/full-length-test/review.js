
/*
 *  This Js all about Review Questions page at full length test.
 *  It uses the gotoQuestion hidden variable for goto question functionality at full.jsp
 */
function getReviewPage(){
	
	$.ajax({ 
  		type : "POST",
  		url :"Review-Page.action",
  		success : replaceDivWithReview,
  		error: errorAtGettingReviewPage
  	}); 
}

function replaceDivWithReview(reviewPageHtmlScript){
	
	$('#question_div').html(reviewPageHtmlScript);
	displayReviewButtons();
	displayQuestionsList();
	 
}

function errorAtGettingReviewPage(){
	
	
}

function returnFromReview(){
	
	displayQuestion('return');
	hideReviewButtons();
}

function goToQuestion(){
	
	displayQuestion('GOTO-'+$('#gotoQuestion').val());
	hideReviewButtons();
}

function hideReviewButtons(){
	
	$('#_quit,#_exit,#showCalc,#_review,#_mark,#_help,#_back,#_next,#_pause,.question_index').show(); 
	$('#_returnFromReview,#_goToQuestion,#_Continue,#_BackSection').hide();
	$('#_buttonsArea').removeClass('col-lg-2 col-lg-offset-10').addClass('col-lg-6 col-lg-offset-6');
	$('.timer_index').removeClass('col-xs-12 col-sm-12').addClass('col-xs-6 col-sm-6');
}

function displayReviewButtons(){
	
	$('#_quit,#_exit,#showCalc,#_review,#_mark,#_help,#_back,#_next,#_pause,.question_index').hide(); 
	$('#_returnFromReview,#_goToQuestion').show();
	$('#_buttonsArea').removeClass('col-lg-6 col-lg-offset-6').addClass('col-lg-2 col-lg-offset-10');
	$('.timer_index').removeClass('col-xs-6 col-sm-6').addClass('col-xs-12 col-sm-12');
	$('#_goToQuestion').attr("disabled","disabled");
}

function displayQuestionsList(){
	
	var questionsList1="<table  class='table table-bordered table-responsive'><tr id=table1><th>Question No</th><th>Status</th><th>Marked</th></tr>";
	var questionsList2="<table  class='table table-bordered table-responsive'><tr id=table2><th>Question No</th><th>Status</th><th>Marked</th></tr>";
	var question=JSON.parse($('#updatedAnswers').val());
	var ans,mark;
	for(var j=0;j<question.length;j++)
	  {
		 ans = (question[j].IsAnswered=="ANSWERED")?"Answered":"Unanswered";
		 mark = (question[j].IsFlagged=="FLAG")?"Yes":"No";
		 if(j+1 <= 10) questionsList1 += "<tr onclick=selectedQuestion('"+(j+1)+"')><td>"+(j+1)+"</td><td>"+ans+"</td><td>"+mark+"</td></tr>";
		 else questionsList2 += "<tr onclick=selectedQuestion('"+(j+1)+"')><td>"+(j+1)+"</td><td>"+ans+"</td><td>"+mark+"</td></tr>";
	  }
	questionsList1 +="</table>";
	questionsList2 +="</table>";
	$('#questionList1').append(questionsList1);
	$('#questionList2').append(questionsList2);
	
}


$(function() {
    
    /* Get all rows from your 'table' but not the first one 
     * that includes headers. */
	//alert("hai");
    var rows = $('tr').not('#table1 , #table2');	
    
    /* Create 'click' event handler for rows */
    rows.on('click', function(e) {
        
    	
        /* Get current row */
        var row = $(this);
        
        /* Check if 'Ctrl', 'cmd' or 'Shift' keyboard key was pressed
         * 'Ctrl' => is represented by 'e.ctrlKey' or 'e.metaKey'
         * 'Shift' => is represented by 'e.shiftKey' */
        if ((e.ctrlKey || e.metaKey) || e.shiftKey) {
            /* If pressed highlight the other row that was clicked */
        	
            row.addClass('highlight');
        } else {
            /* Otherwise just highlight one row and clean others */
        	
            rows.removeClass('highlight');
            row.addClass('highlight');
        }
       
        
    });
    
    /* This 'event' is used just to avoid that the table text 
     * gets selected (just for styling). 
     * For example, when pressing 'Shift' keyboard key and clicking 
     * (without this 'event') the text of the 'table' will be selected.
     * You can remove it if you want, I just tested this in 
     * Chrome v30.0.1599.69*/
    $(document).bind('selectstart dragstart', function(e) { 
        e.preventDefault(); return false; 
    });
    
});




function selectedQuestion(questionIndex){
	
	var rows = $('tr').not('#table1 , #table2');
	rows.on('click', function(e) {
        
    	
        
        var row = $(this);
        
       
        if ((e.ctrlKey || e.metaKey) || e.shiftKey) {
            /* If pressed highlight the other row that was clicked */
        	
            row.addClass('highlight');
        } else {
            /* Otherwise just highlight one row and clean others */
        	
            rows.removeClass('highlight');
            row.addClass('highlight');
        }
       
        if(rows.hasClass('highlight')) $('#_goToQuestion').removeAttr("disabled");
        
    });
	
	$('#gotoQuestion').val(questionIndex);
	
}

	
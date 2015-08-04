<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
        <!DOCTYPE html>

        <html lang="en" class="app">

        <head>
            <meta charset="utf-8" />
            <title>Crunchprep | GRE Web Application</title>
            <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
                <%--All CSS files --%>
                <link rel="stylesheet" href="assets/js/chosen/chosen.css" type="text/css" />
                <link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" />
                <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css" />
              
                <!--ck editor-->
                <script type="text/javascript" src="assets/plug-ins/ckeditor/ckeditor.js"></script>
                <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
                <script src="assets/ckfinder/ckfinder.js"></script>
                <%--Own JS files --%>
                    <script src="assets/js/readingcomprehension/functions.js"></script>
                    <script src="assets/js/common.js" type="text/javascript"></script>
                <script type="text/javascript">
                    $(document).ready(function () {

                        <%--For Toaster MEssages--%>
                            $.fn.toasterMessages();

                        <%--It isfor Initial Document Loading--%>
                        if ($('#questionId1').val() == 0)
                            $.fn.initialDocument();

                        <%--Multiple Radio Button Clicked event--%>
                            $('#multipleansradioId').click(function () {
                                $.fn.mulitipleansradioClick(this);
                            });

                        <%-- Single Radio Button Clicked event--%>
                            $('#singleansradioId').click(function () {
                                $.fn.singleansradioClick(this);
                            });
                            
                       <%-- SelectIn Radio Button Clicked event--%>    
                        $('#selectInansradioId').click(function () {
                            $.fn.selectInansradioClick(this);
                        });

                        <%-- Form Submit event--%>
                        $("#form").submit(function (event) {
                            event.preventDefault();
                            $.fn.validateforInsertion();

                        });
                        
                        <%-- Single Answer Radio Button Click event--%>
                        var prevIndex = "";
                        $('input[name=sinlgechoiceRadiobtn]:radio').click(function () {
                            $.fn.singleRadiobuttonClick(this, prevIndex);
                            prevIndex = this;
                        });

                        <%-- Multiple Answer Radio Button Click event--%>
                        $('input[name=multipleradioButton]:checkbox').click(function () {

                            $.fn.mulitpleRadiobuttonClick(this);

                        });
                        
                        <%-- Reset Function--%>
                        $('#reset').click(function () {
                            $.fn.resetFunction();
                        });
                        
                        <%-- Passage Type Change event--%>
                        $('#passagetypeId').change(function () {

                            $.fn.passageTypeOnChange($('#passagetypeId').val());
                        });
                        
                        /* For form Editing  */
                        if ($('#questionId1').val() != 0) {

                            $.fn.editRc();
                            var skillslist = $('#skills1').val();
                            var arr = skillslist.split(',');
                            $('#skillId').val([arr[0], arr[1], arr[2]]);
                            $('#submit').text("Update");

                        };

                    });
                </script>


        </head>

        <body class="">


            <section id="content">
                <section class="vbox">
                    <section class="scrollable wrapper">

                        <div class="row">
                            <!--row start main-->
                            <div class="col-lg-12">

                                <div class="panel panel-default">
                                    <div class="panel-heading"> <span class="panel-title"> Add Reading Comprehension Questions</span> 
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-lg-8 col-lg-offset-1" style="padding-top:40px;">

                                                <form class="bs-example form-horizontal" data-validate="parsley" id="form">
                                                    <!-- hidden Variables -->

                                                    <input type="hidden" name="solutionText" id="solutionTextId1" value="${readingcmpQuestionBo.solutionText}">
                                                    <input type="hidden" name="question" id="questionId1ans" value="${readingcmpQuestionBo.question}">
                                                    <input type="hidden" id="directions1" value="${readingcmpQuestionBo.directions}">
                                                    <input type="hidden" name="questionId" id="questionId1" value="${readingcmpQuestionBo.questionId}">
                                                    <input type="hidden" id="accessType1" value="${readingcmpQuestionBo.accessType}">
                                                    <input type="hidden" id="referral1" value="${readingcmpQuestionBo.referral}">
                                                    <input type="hidden" id="status1" value="${readingcmpQuestionBo.status}">
                                                    <input type="hidden" id="skills1" value="${readingcmpQuestionBo.skills}">
                                                    <input type="hidden" id="questionType1" value="${readingcmpQuestionBo.questionTypeName}">

                                                    <!-- hidden Variables -->
                                                    <div class="form-group">
                                                        <label class="col-lg-2  col-lg-offset-4  control-label">Type</label>

                                                        <div class="radioo i-checks col-lg-1">
                                                            <label>
                                                                <input type="checkbox" name="accessType" value="FREE" id="accessTypeId">
                                                                <i></i>
                                                                Free
                                                            </label>
                                                        </div>

                                                        <div class="radioo i-checks col-lg-2">
                                                            <label>
                                                                <input type="checkbox" name="referral" value="YES" id="referralId">
                                                                <i></i>
                                                                Referral
                                                            </label>
                                                        </div>

                                                    </div>
                                                    <!--form group close-->
                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Test Name</label>
                                                        <div class="col-lg-5">
                                                            <s:select name="testId" cssClass="form-control m-b" list="testsList" headerKey="-1" headerValue="Select" data-required="true" value="#{readingcmpQuestionBo.testId}" listValue="testName" listKey="testId" id="testId">
                                                            </s:select>
                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Question title</label>
                                                        <div class="col-lg-5">
                                                            <input type="text" name="questionTitle" id="questionTitle" class="form-control" placeholder="Question Title" value="${readingcmpQuestionBo.questionTitle}">
                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
                                                        <div class="col-lg-3">
                                                            <textarea cols="50" rows="4" style="max-width:450px" name="directions" id="directions"></textarea>
                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Passage Type</label>
                                                        <div class="col-lg-5">

                                                            <s:select name="passageType" cssClass="form-control m-b" list="#{'SHORT':'SHORT','MEDIUM':'MEDIUM','LONG':'LONG'}" headerKey="-1" headerValue="Select" data-required="true" id="passagetypeId" value="#{readingcmpQuestionBo.passageType}" key="key" theme="simple">
                                                            </s:select>

                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Passage</label>
                                                        <div class="col-lg-5">
                                                            <s:select name="passageId" cssClass="form-control m-b" list="passagesList" headerKey="-1" headerValue="Select" data-required="true" listValue="passage_title" listKey="passage_id" id="passageId" value="#{readingcmpQuestionBo.passageId}">
                                                            </s:select>


                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 control-label">Question</label>
                                                        <div class="col-lg-10">
                                                            <textarea id="question" name="question1" rows="10" cols="80"></textarea>
                                                            <script>
                                                                CKEDITOR.replace('question1');
                                                            </script>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-3 control-label">Type</label>
                                                        </label>

                                                        <div class="radioo i-checks col-lg-2">
                                                            <label>
                                                                <input type="radio" name="questionType" value="Single Answer" id="singleansradioId">
                                                                <i></i>
                                                                Single Answer
                                                            </label>
                                                        </div>

                                                        <div class="radioo i-checks col-lg-2">
                                                            <label>
                                                                <input type="radio" name="questionType" value="Multiple Answer" id="multipleansradioId">
                                                                <i></i>
                                                                Multiple Answer
                                                            </label>
                                                        </div>

                                                        <div class="radioo i-checks col-lg-2">
                                                            <label>
                                                                <input type="radio" name="questionType" value="Select In" id="selectInansradioId">
                                                                <i></i>
                                                                Select in
                                                            </label>
                                                        </div>

                                                    </div>
                                                    <!--form group-->


                                                    <div class="form-group" id="single">

                                                        <div class="row">

                                                            <div class="col-lg-6 " id="singleAnsChoiceDivId">

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="option1" value="${readingcmpQuestionBo.choices[0]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="radio" name="sinlgechoiceRadiobtn" value="option1" id="singleRadio1">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 1-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="option2" value="${readingcmpQuestionBo.choices[1]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="radio" name="sinlgechoiceRadiobtn" value="option2" id="singleRadio2">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 2-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="option3" value="${readingcmpQuestionBo.choices[2]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="radio" name="sinlgechoiceRadiobtn" value="option3" id="singleRadio3">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 3-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" id="option4" name="choices" value="${readingcmpQuestionBo.choices[3]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="radio" name="sinlgechoiceRadiobtn" value="option4" id="singleRadio4">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 4-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="option5" value="${readingcmpQuestionBo.choices[4]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="radio" name="sinlgechoiceRadiobtn" value="option5" id="singleRadio5">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 5-->


                                                            </div>
                                                            <!--Col for choices close-->

                                                            <div class="col-lg-6" id="sinlgeAnsAnswerDivId">
                                                                <div class="form-group">
                                                                    <label class="col-lg-2 col-lg-offset-1 control-label" id="answerId">Answer</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" readonly placeholder="" name="answers" id="singleAnswer" value="${readingcmpQuestionBo.answers[0]}">
                                                                    </div>
                                                                </div>

                                                            </div>

                                                        </div>

                                                    </div>
                                                    <!--Form group close for choices-->


                                                    <div class="form-group" id="multipleAnsDivId">

                                                        <div class="row">

                                                            <div class="col-lg-6 ">

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="multipleChoiceId1" value="${readingcmpQuestionBo.choices[0]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="checkbox" name="multipleradioButton" value="multipleChoiceId1" id="multicheckId1">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 1-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="multipleChoiceId2" value="${readingcmpQuestionBo.choices[1]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="checkbox" name="multipleradioButton" value="multipleChoiceId2" id="multicheckId2">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 2-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="multipleChoiceId3" value="${readingcmpQuestionBo.choices[2]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="checkbox" name="multipleradioButton" value="multipleChoiceId3" id="multicheckId3">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 3-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="multipleChoiceId4" value="${readingcmpQuestionBo.choices[3]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="checkbox" name="multipleradioButton" value="multipleChoiceId4" id="multicheckId4">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 4-->

                                                                <br>

                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="multipleChoiceId5" value="${readingcmpQuestionBo.choices[4]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="checkbox" name="multipleradioButton" value="multipleChoiceId5" id="multicheckId5">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 5-->

                                                                <br>


                                                                <div class="row">
                                                                    <!--row for internal choices -->
                                                                    <label class="col-lg-2 col-lg-offset-3  control-label">Choice 6</label>
                                                                    <div class="col-lg-6">
                                                                        <input type="text" class="form-control" placeholder="" name="choices" id="multipleChoiceId6" value="${readingcmpQuestionBo.choices[5]}">
                                                                    </div>

                                                                    <div class="radi i-checks col-lg-1">
                                                                        <label>
                                                                            <input type="checkbox" name="multipleradioButton" value="multipleChoiceId6" id="multicheckId6">
                                                                            <i></i> 
                                                                        </label>
                                                                    </div>

                                                                </div>
                                                                <!--row close for internal choices 6-->

                                                            </div>
                                                            <!--Col for choices close-->

                                                            <!------------------------>

                                                            <div class="col-lg-6">

                                                                <div class="form-group">
                                                                    <div class="row">
                                                                        <label class="col-lg-2 col-lg-offset-1 control-label">Answer 1</label>
                                                                        <div class="col-lg-6">
                                                                            <input type="text" class="form-control" readonly placeholder="" name="answers" id="answer1" value="${readingcmpQuestionBo.answers[0].trim()}">
                                                                        </div>
                                                                        <!--col close 1-->

                                                                    </div>
                                                                    <!--row close for multiple answers-->

                                                                    <br>

                                                                    <div class="row">
                                                                        <label class="col-lg-2 col-lg-offset-1 control-label">Answer 2</label>
                                                                        <div class="col-lg-6">
                                                                            <input type="text" class="form-control" readonly placeholder="" name="answers" id="answer2" value="${readingcmpQuestionBo.answers[1].trim()}">
                                                                        </div>
                                                                        <!--col close 2-->

                                                                    </div>
                                                                    <!--row close for multiple answers-->

                                                                    <br>


                                                                    <div class="row">
                                                                        <label class="col-lg-2 col-lg-offset-1 control-label">Answer 3</label>
                                                                        <div class="col-lg-6">
                                                                            <input type="text" class="form-control" readonly placeholder="" name="answers" id="answer3" value="${readingcmpQuestionBo.answers[2].trim()}">
                                                                        </div>
                                                                        <!--col close 3-->

                                                                    </div>
                                                                    <!--row close for multiple answers-->

                                                                    <br>

                                                                    <div class="row">
                                                                        <label class="col-lg-2 col-lg-offset-1 control-label">Answer 4</label>
                                                                        <div class="col-lg-6">
                                                                            <input type="text" class="form-control" readonly placeholder="" name="answers" id="answer4" value="${readingcmpQuestionBo.answers[3].trim()}">
                                                                        </div>
                                                                        <!--col close 4-->
                                                                    </div>
                                                                    <!--row close for multiple answers-->

                                                                    <br>

                                                                    <div class="row">
                                                                        <label class="col-lg-2 col-lg-offset-1 control-label">Answer 5</label>
                                                                        <div class="col-lg-6">
                                                                            <input type="text" class="form-control" readonly placeholder="" name="answers" id="answer5" value="${readingcmpQuestionBo.answers[4].trim()}">
                                                                        </div>
                                                                        <!--col close 5-->
                                                                    </div>
                                                                    <!--row close for multiple answers-->

                                                                    <br>

                                                                    <div class="row">
                                                                        <label class="col-lg-2 col-lg-offset-1 control-label">Answer 6</label>
                                                                        <div class="col-lg-6">
                                                                            <input type="text" class="form-control" readonly placeholder="" name="answers" id="answer6" value="${readingcmpQuestionBo.answers[5].trim()}">
                                                                        </div>
                                                                        <!--col close 6-->
                                                                    </div>
                                                                    <!--row close for multiple answers-->
                                                                </div>
                                                                <!--form group close for multiple answers-->
                                                            </div>
                                                            <!--col-lg-6 for answers close-->

                                                        </div>

                                                    </div>
                                                    <!--Form group close for multiple choices-->




                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Skill</label>
                                                        <div class="col-lg-3">


                                                            <s:select name="skills" cssStyle="width: 230px" multiple="true" list="skillsList" listValue="skill_name" listKey="skill_id" id="skillId">
                                                            </s:select>


                                                        </div>
                                                    </div>




                                                    <div class="form-group" style="display:none">

                                                        <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level</label>
                                                        <div class="col-lg-3">

                                                            <s:select name="difficultyId" data-required="true" headerKey="-1" headerValue="Select" cssClass="form-control m-b" list="difficultiesList" listValue="difficulty_name" listKey="difficulty_id" id="difficultyId" value="#{readingcmpQuestionBo.difficultyId}">
                                                            </s:select>
                                                        </div>

                                                    </div>
                                                    <!--Form group close-->


                                                    <div class="form-group">
                                                        <label class="col-lg-2 control-label">Solution Text</label>
                                                        <div class="col-lg-10">
                                                            <textarea id="solutionText" name="solutionTextdata" rows="10" cols="80"></textarea>
                                                            <script>
                                                                CKEDITOR.replace('solutionTextdata');
                                                            </script>
                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-2 control-label">Solution Video</label>
                                                        <div class="col-lg-6">
                                                            <input type="text" name="solutionVideo" class="form-control" placeholder="" value="${readingcmpQuestionBo.solutionVideo}" id="solutionVideo">
                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label class="col-lg-2 col-lg-offset-3 control-label">Status</label>
                                                        </label>

                                                        <div class="radioo i-checks col-lg-2">
                                                            <label>
                                                                <input type="radio" name="status" value="ACTIVE" id="activeId">
                                                                <i></i>
                                                                Active
                                                            </label>
                                                        </div>

                                                        <div class="radioo i-checks col-lg-2">
                                                            <label>
                                                                <input type="radio" name="status" value="INACTIVE" id="inActiveId">
                                                                <i></i>
                                                                In Active
                                                            </label>
                                                        </div>

                                                    </div>



                                                    <div class="form-group">
                                                        <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                                                            <button type="submit" class="btn btn-success" id="submit">Submit</button>
                                                        </div>
                                                        <div class="col-lg-offset-1 col-lg-1">
                                                            <button type="button" class="btn btn-danger" id="reset">Reset</button>
                                                        </div>
                                                        <div class="col-lg-offset-1 col-lg-1">
                                                            <a class="btn btn-default" href="reading-comprehension-questionsTable.action?sectionId=2">Questions List</a>
                                                        </div>
                                                    </div>
                                                    <!--form group close-->
                                                </form>
                                              

                                            </div>
                                            <!--col for skill level close-->

                                        </div>
                                        <!--Inner row1 close -->





                                    </div>
                                    <!--panel body close-->
                                </div>
                                <!--panel head close-->

                            </div>
                            <!--div close-->
                        </div>
                        <!--row start main End-->



                    </section>
                </section>
                <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
            </section>
                <%--All JS files --%> 
                <script src="assets/js/select2.1/select2.min.js"></script>
                <!-- parsley -->
                <script src="assets/js/parsley/parsley.min.js"></script>
                <script src="assets/js/parsley/parsley.extend.js"></script>
               
          
        </body>
        <script type="text/javascript">
            $(document).ready(function () {
                
                $("#skillId").select2({
                    maximumSelectionSize: 3,
                    placeholder: "Please Select skills"
                });
            });
        </script>

        </html>
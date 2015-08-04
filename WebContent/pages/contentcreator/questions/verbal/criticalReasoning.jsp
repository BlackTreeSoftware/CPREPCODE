<!DOCTYPE html>
<html lang="en" class="app">
<%@taglib prefix="s" uri="/struts-tags" %>

    <head>
        <meta charset="utf-8" />
        <title>Crunchprep | GRE Web Application</title>
        <%--All CSS files --%>
            <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
            <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
            <link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />

            <%--Select 2 Js files --%>
                <link rel="stylesheet" href="assets/js/select2.1/select2.css" type="text/css" />

                <%--CKEditor --%>
                    <script src="assets/plug-ins/ckeditor/ckeditor.js"></script>

                    <%--Toaster Messages --%>
                        <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css" />
                        <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>



                        <script type="text/javascript">
                            $(document).ready(function () {
                                var prevIndex = '',
                                    indexcount = 0;

                                <%--Toaster Messages --%>
                                    toastr.options = {
                                        "closeButton": true,
                                        "debug": false,
                                        "positionClass": "toast-top-right",
                                        "onclick": null,
                                        "showDuration": "300",
                                        "hideDuration": "1000",
                                        "timeOut": "5000",
                                        "extendedTimeOut": "1000",
                                        "showEasing": "swing",
                                        "hideEasing": "linear",
                                        "showMethod": "fadeIn",
                                        "hideMethod": "fadeOut"
                                };

                                <%--Editing Question --%>
                                if ($('#questionId').val() != 0) {
                                    /*   alert("question Id ; "+$('#questionId').val()+
				    "\t solutionTextId ="+$('#bsolutionTextId').val()+
				   "\t Access Type : "+$('#accessType').val()+
				  "\t referralType :"+$('#breferralType').val()
				    +"\t bdirections :"+$('#bdirections').val()+
				   "\t bquestion :"+$('#bquestion').val()+
				    "\t bstatus  ="+$('#bstatus').val()+"\t testId ="+$('#testId').val()+"\t skills : "+$('#bskills').val());  */
				    $('#submit').text('Update');

                                    //alert("Calling"+$('#bquestion').val());
                                    if ($('#accessType').val() == 'FREE') {

                                        $("input[name='accessType']").prop('checked', true);
                                    } else {
                                        $("input[name='accessType']").prop('checked', false);
                                    }

                                    $('#directions').val($('#bdirections').val());

                                    if ($('#breferralType').val() == 'YES') {

                                        $("input[name='referralType']").prop('checked', true);
                                    } else {
                                        $("input[name='referralType']").prop('checked', false);
                                    }

                                    if ($('#bstatus').val() == 'ACTIVE') {
                                        $('#active').prop("checked", true);
                                    } else {
                                        $('#inactive').prop("checked", true);
                                    }

                                    //alert("question : "+$('#bquestion').val());
                                    CKEDITOR.instances['question'].setData($('#bquestion').val());
                                    CKEDITOR.instances['solutionText'].setData($('#bsolutionTextId').val());
                                    CKEDITOR.instances['passage'].setData($('#bpassage').val());


                                    var i = 0,
                                        k = 0;
                                    $('#form input[name="choices"]').each(function () {
                                        i++;

                                        // alert("current tesxt : "+$(this).val()+"\t answer : "+$('#answer').val());
                                        if ($(this).val() == $('#answer').val()) {
                                            //alert("matched");
                                            k = i;
                                        }

                                        var skillslist = $('#bskills').val(); //alert(skillslist);
                                        var arr = skillslist.split(',');
                                        $('#skillId').val([arr[0], arr[1], arr[2]]);

                                    });
                                    var l = 0;
                                    $('#form input[name="choiceRadiobtn"]').each(function () {
                                        l++;
                                        var option = '#option' + l;
                                        if (l == k) {
                                            $(this).prop("checked", true);
                                            $(option).prop("readonly", true);
                                            prevIndex = option;
                                            indexcount = indexcount + 1;

                                        }
                                    });
                                } else {
                                    $('#freeTypeId').prop("checked", true);
                                    $('#active').prop("checked", true);
                                }



                                <%-- Choice Radio Button Click Event --%>

                                $('input[name=choiceRadiobtn]:radio').click(function () {
                                    $.fn.singleRadiobuttonClick(this, prevIndex);
                                    prevIndex = this;

                                });
                                $.fn.singleRadiobuttonClick = function (curr, prev) {

                                    var id = '#' + curr.value; //alert(prev);
                                    if ($(id).val().length != 0) {
                                        $('#answer').val($(id).val());
                                        $(id).attr("readonly", true);

                                        if (prev != '') {
                                            var preId = '#' + prev.value;
                                            $(preId).attr("readonly", false);
                                        }
                                    } else {
                                        $('input[name=choiceRadiobtn]:radio').prop('checked', false);
                                    }
                                    if (indexcount == 1) {
                                        $(prev).attr("readonly", false);
                                    }
                                    if (curr.value == prev.value) {
                                        $(id).prop('checked', true);
                                        $(id).attr("readonly", true);
                                    }
                                }; <%-- Select@ initialization --%>
                                    $("#skillId").select2({
                                        placeholder: "Select Skills",
                                        maximumSelectionSize: 3,
                                        allowClear: true
                                    });

                                <%-- Reset Functionality --%>
                                    $.fn.clear = function (str) {
                                        $("#questionId").val("0");
                                        $('#freeTypeId').prop("checked", true);
                                        $('#active').prop("checked", true);
                                        $("#testId").val($("#testId option:first").val());
                                        $("#difficultyId").val($("#difficultyId option:first").val());
                                        $('input[type=text]').val('');
                                        $("#directions").val("");
                                        $("input[name=choiceRadiobtn]").attr("checked", false);
                                        $("input[name=choices]").attr("readonly", false);
                                        $("#skillId").select2('val', '');
                                        CKEDITOR.instances['question'].setData('');
                                        CKEDITOR.instances['solutionText'].setData('');
                                        CKEDITOR.instances['passage'].setData('');


                                };
                                $("#reset").click(function () {
                                    //alert("Reset Calling");
                                    $.fn.clear();
                                });


                                <%-- Form Submit Event --%>
                                    $("#submit").click(function () {

                                        var question = CKEDITOR.instances['question'].getData();
                                        var solutionText = CKEDITOR.instances['solutionText'].getData();
                                        var passage = CKEDITOR.instances['passage'].getData();
                                        $('#bquestion').val(question);
                                        $('#bpassage').val(passage);
                                        $('#bsolutionTextId').val(solutionText);
                                        //  alert(question+"\t"+solutionText);
                                        if ($('#testId').val() == -1) {
                                            toastr.error("Please Select Test Type");
                                        } else {

                                            if ($('#questionTitle').val() == " " || $('#questionTitle').val().length == 0||$('#questionTitle').val().indexOf(" ")==0) {

                                                toastr.error("Please Enter Question Title");
                                            } else {

                                                if ($('#directions').val() == " " || $('#directions').val().length == 0||$('#directions').val().indexOf(" ")==0 ) {

                                                    toastr.error("Please Enter Directions");
                                                } else {

                                                    if (passage == " " || passage.length == 0 ||passage.indexOf(" ")==0) {
                                                        toastr.error("Please Enter Passage");
                                                    } else {
                                                        if (question == " " || question.length == 0||question.indexOf(" ")==0) {

                                                            toastr.error("Please Enter Question");
                                                        } else {

                                                            if ($('#option1').val() == " " || $('#option1').val().length == 0 &&
                                                                $('#option2').val() == " " || $('#option2').val().length == 0 &&
                                                                $('#option3').val() == " " || $('#option3').val().length == 0 &&
                                                                $('#option4').val() == " " || $('#option4').val().length == 0 &&
                                                                $('#option5').val() == " " || $('#option5').val().length == 0 ||
                                                                $('#option1').val().indexOf(" ")==0||$('#option2').val().indexOf(" ")==0||$('#option3').val().indexOf(" ")==0||$('#option4').val().indexOf(" ")==0||$('#option5').val().indexOf(" ")==0) {

                                                                toastr.error("Please Enter All the Choices");
                                                            } else {

                                                                if ($('#answer').val() == " " || $('#answer').val().length == 0||$('#answer').val().indexOf(" ")==0) {

                                                                    toastr.error("Please Enter Answer");
                                                                } else {

                                                                    var skills = $("#skillId").val();
                                                                    //  alert("skills length : "+skills.length<=3);
                                                                    if (skills != null && skills.length < 1) {

                                                                        toastr.error("Please Select Atleast 1 Important Skills");
                                                                    } else if (skills == null) {
                                                                        toastr.error("Please Select Atleast 1 Important Skills");
                                                                    } else {

                                                                        if ($('#difficultyId').val() == -1) {
                                                                            toastr.error("Please Select Difficulty Level");
                                                                        } else {

                                                                            if (solutionText == " " || solutionText.length == 0 ||solutionText.indexOf(" ")==0) {

                                                                                toastr.error("Please Enter Solution Text");
                                                                            } else {

                                                                                if ($('#solutionvideo').val() == " " || $('#solutionvideo').val().length == 0||$('#solutionvideo').val().indexOf(" ")==0) {

                                                                                    toastr.error("Please Enter Solution Video");
                                                                                }
                                                                                
                                                                                if(/^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/|www\.)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/.test($('#solutionvideo').val())){
                                                                                	  $.ajax({
                                                                                          type: 'POST',
                                                                                          url: 'newCriticalReasoning.action',
                                                                                          data: $("#form").serialize(),
                                                                                          dataType: 'JSON',
                                                                                          success: function (result) {
                                                                                              //alert(result.result);
                                                                                              if (result.result == "success") {

                                                                                                  toastr.success("New Critical Reasoning Question Uploaded successfully!");
                                                                                                  $.fn.clear();
                                                                                              } else if (result.result == "update") {
                                                                                                  toastr.success("Critical Reasoning Question Updated successfully!");
                                                                                                  $('#submit').text('Submit');
                                                                                                 
                                                                                                  $.fn.clear();

                                                                                              } else {

                                                                                                  toastr.error("Question not saved please try again later");
                                                                                              }
                                                                                          },
                                                                                          error: function (e) {
                                                                                              toastr.error("Error");
                                                                                          }
                                                                                      });
                                 												} else {
                                 													 toastr.error("Please Enter Valid URL for Solution Video ");
                                 												}
                                                                                
                                                                            }
                                                                        }
                                                                    }



                                                                }

                                                            }

                                                        }
                                                    }




                                                }
                                            }


                                        }


                                    });
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
                                <div class="panel-heading">
                                    <span class="panel-title">New Critical Reasoning</span>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-8 col-lg-offset-1" style="padding-top: 40px;">

                                            <form class="bs-example form-horizontal" id="form">

                                                <input type="hidden" id="questionId" name="questionId" value="${criticalReasoningBo.questionId}">
                                                <input type="hidden" id="bsolutionTextId" name="solutionText" value="${criticalReasoningBo.solutionText}">
                                                <input type="hidden" id="bquestion" name="question" value="${criticalReasoningBo.question}">
                                                <input type="hidden" id="bpassage" name="passage" value="${criticalReasoningBo.passage}">
                                                <input type="hidden" id="accessType" value="${criticalReasoningBo.accessType}">
                                                <input type="hidden" id="breferralType" value="${criticalReasoningBo.referralType}">
                                                <input type="hidden" id="bdirections" value="${criticalReasoningBo.directions}">
                                                <input type="hidden" id="bstatus" value="${criticalReasoningBo.status}">
                                                <input type="hidden" id="bskills" name="skills1" value="${criticalReasoningBo.skills}">
                                                <div class="form-group">
                                                    <label class="col-lg-2  col-lg-offset-4  control-label">Type</label>

                                                    <div class="radioo i-checks col-lg-2">
                                                        <label class="checkbox m-n i-checks">
                                                            <input type="checkbox" name="accessType" id="freeTypeId" value="Free" required="required"/><i></i>Free</label>
                                                    </div>

                                                    <div class="radioo i-checks col-lg-2">
                                                        <label class="checkbox m-n i-checks">
                                                            <input type="checkbox" name="referralType" id="freeTypeId" value="Referal" required="required"/><i></i>Referral</label>
                                                    </div>

                                                </div>
                                                <!--form group close-->


                                                <div class="form-group">
                                                    <label class="col-lg-2 col-lg-offset-4 control-label">Test Name
                                                    </label>
                                                    <div class="col-lg-5">
                                                        <s:select name="testId" headerKey="-1" headerValue="Select" cssClass="form-control m-b" list="tests_list" value="#{criticalReasoningBo.testId}" listValue="testName" listKey="testId" id="testId" required="required">
                                                        </s:select>
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label class="col-lg-2 col-lg-offset-4 control-label">Question title
                                                    </label>
                                                    <div class="col-lg-5">
                                                        <input type="text" class="form-control" placeholder="Question Title" name="questionTititle" required="required" id="questionTitle" value="${criticalReasoningBo.questionTititle}">
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label class="col-lg-2 col-lg-offset-4 control-label">Directions</label>
                                                    <div class="col-lg-3">
                                                        <textarea cols="50" rows="4" style="max-width:450px" name="directions"   placeholder="Question Directions" id="directions"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label">Passage</label>
                                                    <div class="col-lg-10">
                                                        <textarea class="ckeditor form-control" id="passage" name="passage1" rows="10" cols="80" required="required"></textarea>
                                                        <script>
                                                            CKEDITOR.replace('passage1');
                                                        </script>
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label">Question</label>
                                                    <div class="col-lg-10">
                                                        <textarea class="ckeditor form-control" id="question" name="question1" rows="10" cols="80" required="required"></textarea>
                                                        <script>
                                                            CKEDITOR.replace('question1');
                                                        </script>
                                                    </div>
                                                </div>



                                                <div class="form-group" id="single">

                                                    <div class="row">

                                                        <div class="col-lg-6 ">

                                                            <div class="row">
                                                                <!--row for internal choices -->
                                                                <label class="col-lg-2 col-lg-offset-3  control-label">Choice 1
                                                                </label>
                                                                <div class="col-lg-6">
                                                                    <input type="text" class="form-control" placeholder="Question Choice" name="choices" id="option1" required="required" value="${criticalReasoningBo.choices[0]}">
                                                                </div>

                                                                <div class="radi i-checks col-lg-1">
                                                                    <label>
                                                                        <input type="radio" name="choiceRadiobtn" value="option1" data-required="true"> <i></i>

                                                                    </label>
                                                                </div>

                                                            </div>
                                                            <!--row close for internal choices 1-->

                                                            <br>

                                                            <div class="row">
                                                                <!--row for internal choices -->
                                                                <label class="col-lg-2 col-lg-offset-3  control-label">Choice 2
                                                                </label>
                                                                <div class="col-lg-6">
                                                                    <input type="text" class="form-control" placeholder="Question Choice" name="choices" id="option2" required="required" value="${criticalReasoningBo.choices[1]}">
                                                                </div>

                                                                <div class="radi i-checks col-lg-1">
                                                                    <label>
                                                                        <input type="radio" name="choiceRadiobtn" value="option2" data-required="true"> <i></i>

                                                                    </label>
                                                                </div>

                                                            </div>
                                                            <!--row close for internal choices 2-->

                                                            <br>

                                                            <div class="row">
                                                                <!--row for internal choices -->
                                                                <label class="col-lg-2 col-lg-offset-3  control-label">Choice 3
                                                                </label>
                                                                <div class="col-lg-6">
                                                                    <input type="text" class="form-control" placeholder="Question Choice" name="choices" id="option3" required="required" value="${criticalReasoningBo.choices[2]}">
                                                                </div>

                                                                <div class="radi i-checks col-lg-1">
                                                                    <label>
                                                                        <input type="radio" name="choiceRadiobtn" value="option3" data-required="true"> <i></i>
                                                                    </label>
                                                                </div>

                                                            </div>
                                                            <!--row close for internal choices 3-->

                                                            <br>

                                                            <div class="row">
                                                                <!--row for internal choices -->
                                                                <label class="col-lg-2 col-lg-offset-3  control-label">Choice 4
                                                                </label>
                                                                <div class="col-lg-6">
                                                                    <input type="text" class="form-control" placeholder="Question Choice" name="choices" id="option4" required="required" value="${criticalReasoningBo.choices[3]}">
                                                                </div>

                                                                <div class="radi i-checks col-lg-1">
                                                                    <label>
                                                                        <input type="radio" name="choiceRadiobtn" value="option4" data-required="true"> <i></i>

                                                                    </label>
                                                                </div>

                                                            </div>
                                                            <!--row close for internal choices 4-->

                                                            <br>

                                                            <div class="row">
                                                                <!--row for internal choices -->
                                                                <label class="col-lg-2 col-lg-offset-3  control-label">Choice 5
                                                                </label>
                                                                <div class="col-lg-6">
                                                                    <input type="text" class="form-control" placeholder="Question Choice" name="choices" id="option5" required="required" value="${criticalReasoningBo.choices[4]}">
                                                                </div>

                                                                <div class="radi i-checks col-lg-1">
                                                                    <label>
                                                                        <input type="radio" name="choiceRadiobtn" value="option5" data-required="true"> <i></i>
                                                                    </label>
                                                                </div>

                                                            </div>
                                                            <!--row close for internal choices 5-->


                                                        </div>
                                                        <!--Col for choices close-->

                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label class="col-lg-2 col-lg-offset-1 control-label">Answer
                                                                </label>
                                                                <div class="col-lg-6">
                                                                    <input type="text" class="form-control" readonly name="answer" id="answer" placeholder="Answer" required="required" value="${criticalReasoningBo.answer}">
                                                                </div>
                                                            </div>

                                                        </div>

                                                    </div>

                                                </div>
                                                <!--Form group close for choices-->





                                                <br>

                                                <div class="form-group">
                                                    <label class="col-lg-2 col-lg-offset-4 control-label">Skills</label>
                                                    <div class="col-lg-3">

                                                        <%-- <s:select name="skills" cssStyle="width: 230px" data-required="true" multiple="true" cssClass="select2-offscreen parsley-validated" list="skills_list" listValue="skill_name" listKey="skill_id" id="skillId">
                                                            </s:select>--%>

                                                            <s:select name="skills" cssStyle="width: 230px" multiple="true" list="skills_list" listValue="skill_name" listKey="skill_id" id="skillId">
                                                            </s:select>

                                                    </div>
                                                </div>


                                                <div class="form-group">

                                                    <label class="col-lg-2 col-lg-offset-4 control-label">Difficulty level
                                                    </label>
                                                    <div class="col-lg-3">
                                                        <s:select name="difficultyId" headerKey="-1" headerValue="Select" value="#{criticalReasoningBo.difficultyId}" cssClass="form-control m-b" list="difficulties_list" listValue="difficulty_name" listKey="difficulty_id" id="difficultyId">
                                                        </s:select>
                                                    </div>

                                                </div>



                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label">Solution Text</label>
                                                    <div class="col-lg-10">
                                                        <textarea class="ckeditor form-control" id="solutionText" name="solutionText1" rows="10" cols="80" placeholder="Solution Text" required="required"></textarea>
                                                        <script>
                                                            CKEDITOR.replace('solutionText1');
                                                        </script>
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label class="col-lg-2 col-lg-offset-2 control-label">Solution Video
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" placeholder="Video URL for the Question" name="solutionVideo" id="solutionvideo" value="${criticalReasoningBo.solutionVideo}">
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label class="col-lg-2 col-lg-offset-3 control-label">Status</label>
                                                    </label>

                                                    <div class="radioo i-checks col-lg-2">
                                                        <label>
                                                            <input type="radio" name="status" id="active" value="ACTIVE" checked> <i></i> Active
                                                        </label>
                                                    </div>

                                                    <div class="radioo i-checks col-lg-2">
                                                        <label>
                                                            <input type="radio" name="status" id="inactive" value="INACTIVE" checked> <i></i> In Active
                                                        </label>
                                                    </div>
                                                </div>

                                                <span id="error"></span>

                                                <div class="form-group">
                                                    <div class="col-lg-offset-5 col-lg-1 col-sm-offset-4 col-sm-2 col-xs-offset-4 col-xs-2">
                                                        <button type="button" class="btn btn-success" id="submit">Submit</button>
                                                    </div>
                                                    <div class="col-lg-offset-1 col-lg-1">
                                                        <button type="button" class="btn btn-danger" id="reset">Reset</button>
                                                    </div>
                                                    <div class="col-lg-offset-1 col-lg-1">
                                                        <a class="btn btn-default" href="totalQuestionsList.action" class="auto">View Questions</a>
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
            <a href="assets/#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>

        <%--All JS files --%>
            <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
            <script src="assets/js/datatables/demo.js"></script>
            <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>
            <%--Select 2 JS file --%>
                <script src="assets/js/select2.1/select2.min.js"></script>
                <script type="text/javascript">
                    $(document).ready(function () {

                        $("#skillId").select2({
                            maximumSelectionSize: 3,
                            placeholder: "Please Select skills"
                        });
                    });
                </script>
    </body>

</html>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
    <html lang="en" class="app">

    <head>
        <meta charset="utf-8" />
        <title>Crunchprep | GRE Web Application</title>
        <style>
            .chck {
                width: 40px;
            }
            .text_center {
                text-align: center
            }
        </style>
        <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
        <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
        <link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
        <script src="assets/js/jquery.min.js"></script>
        <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css" />
        <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
        <script src="assets/js/common.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $.fn.selectTotalCheckboxes();
                $.fn.datatableColumns();
                $('#form').submit(function () {
                    $('#form').attr('action', 'obtain_EditRCquestion.action?questionId=' + $('#questionId').val());
                    $('#form').submit();

                });
                $.fn.toasterMessages();
            });
            $.fn.datatableColumns = function () {
                var oTable = $('#table').dataTable({

                    "bProcessing": true,

                    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
                    "sPaginationType": "full_numbers",
                    "aoColumns": [{
                            "mData": "columnname1"
                        }, {
                            "mData": "columnname2"
                        }, {
                            "mData": "columnname3"
                        }, {
                            "mData": "columnname4"
                        }, {
                            "mData": "columnname5"
                        }, {
                            "mData": "columnname6"
                        }, {
                            "mData": "columnname7"
                        }, {
                            "mData": "columnname8"
                        }, {
                            "mData": "columnname9"
                        }

                    ]
                });
            };

            function deleteTabledata() {
                var checked = $("input[name=deleteIds]:checked").length;
                if (checked == 0) {

                    toastr.error("please select atleast single Question to Delete");
                } else {

                    var questionIds_Array = new Array();
                    $('input:checkbox[name=deleteIds]:checked').each(function () {
                        var questionId = "#question" + $(this).attr("id");
                        questionIds_Array.push($(questionId).html());
                    });

                    $.ajax({
                        type: 'POST',
                        url: 'deleteRcQuestions.action',
                        data: 'deleteIds=' + questionIds_Array.toString(),
                        success: function (result) {
                            $('#table_data').html(result);
                            toastr.success("Selected Question Deleted Successfully");
                        },
                        error: function (e) {
                            toastr.error("OOps! Selected Question not Deleted please Try again");
                        }
                    });
                }
            }

            function editQuestion(questionId) {

                $("#questionId").val(questionId);
            }
        </script>

    </head>

    <body class="">

        <section id="content">
            <section class="vbox">
                <section class="scrollable wrapper">
					<a href="reading-comprehension-questions.action?sectionId=2" class="auto btn btn-default" style="color:blue">Add New </a>
                    <div class="row" style="padding-top:10px">
                        <!--row start main-->
                        <div class="col-lg-12">

                            <div class="panel panel-default">
                                <div class="panel-heading"> <span class="panel-title"> Reading Comprehension Questions List</span> 
                                </div>
                                <div class="panel-body">


                                    <div class="row">
                                        <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">

                                            <section class="scrollable padder">

                                                <section class="panel panel-default">
                                                    <input type="hidden" name="questionId" id="questionId" value="%{questionId}" />

                                                    <s:form id="form" name="form" theme="simple">

                                                        <div class="table-responsive" id="table_data">

                                                            <table class="table table-striped m-b-none" id="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th>S.No</th>
                                                                        <th>Question Type</th>
                                                                        <th>Passage</th>
                                                                        <th>Question Title</th>
                                                                        <th>Skills</th>
                                                                        <th>Difficulty Level</th>
                                                                        <th style="display: none">QuestionId</th>
                                                                        <th class="text_center">Edit</th>
                                                                        <th class="chck text_center" style="padding-bottom: -2px">
                                                                            <label class="checkbox m-n i-checks">
                                                                                <input type="checkbox" id="maincheckbox"><i></i>
                                                                            </label>
                                                                        </th>


                                                                    </tr>
                                                                </thead>

                                                                <tbody>
                                                                    <s:iterator value="rcQuestionsList" status="row">
                                                                        <tr>

                                                                            <td><s:property value="#row.index+1" />
                                                                            </td>
                                                                            <td><s:property value="questionTypeName" />
                                                                            </td>
                                                                            <td><s:property value="passageName" />
                                                                            </td>
                                                                            <td><s:property value="questionTitle" />
                                                                            </td>
                                                                            <td><s:property value="skillsName" />
                                                                            </td>
                                                                            <td><s:property value="difficultyName" />
                                                                            </td>
                                                                            <td style="display: none" id="question<s:property value=" #row.index+1 "/>"><s:property value="questionId" />
                                                                            </td>
                                                                            <td align="center">
                                                                                <button type="submit" class="btn btn-sm btn-default" id="Edit" onclick="editQuestion('<s:property
															value=" questionId " />')">Edit</button>
                                                                            </td>
                                                                            <td class="chck" style="padding-top: 3px">
                                                                                <label class="checkbox m-n i-checks">
                                                                                    <input type="checkbox" name="deleteIds" id="<s:property value=" #row.index+1 "/>" /><i></i>
                                                                                </label>
                                                                            </td>

                                                                        </tr>
                                                                    </s:iterator>


                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </s:form>

                                                </section>
                                                <center><a href="javascript:deleteTabledata()" class="btn btn-del">Delete selected</a>
                                                </center>
                                            </section>
                                            <!--sec close for data table-->
                                            <span id="error"></span>
                                            
                                             
                                        </div>
                                        <!--col for DataTable close-->

                                    </div>
                                    <!--Inner row2 close -->
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

        </section>
        
         <!--All JS files-->
        <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
        <script src="assets/js/datatables/demo.js"></script>

    </body>

    </html>
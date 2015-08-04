<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
    <html lang="en" class="app">

    <head>
        <meta charset="utf-8" />
        <title>Crunchprep | GRE Web Application</title>
        <%--All CSS files --%>
        <link rel="stylesheet" href="assets/js/datatables/datatables.css" type="text/css" />
        <link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
        <link rel="stylesheet" href="assets/js/datatables/jquery.dataTables.min.js" type="text/css" />
        <%--For Toster Messages --%>
        <link href="assets/plug-ins/toastr-master/toastr.min.css" rel="stylesheet" type="text/css" />
        <script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        <%--Table Editing Event--%>
            function editTable(sectionId, lessonName, lessonId) {
                //alert("Calling"+sectionId+"\t"+lessonName+"\t"+lessonId);
                $('#lesson_name').val(lessonName);
                $('#sectionId').val(sectionId);
                $('#lessonId').val(lessonId); 
                $('#submit').text("Update");
            }
         <%--Table Delete Button Event --%>
            function deleteTabledata() {
                var checked = $("input[name=delete]:checked").length;
                // alert(checked);

                if (checked == 0) {

                    toastr.error("please select atleast single Lesson to Delete");
                } else {

                    var lessonIds_Array = new Array();
                    $('input:checkbox[name=delete]:checked').each(function () {
                        var lessonId = "#lesson" + $(this).attr("id");
                        lessonIds_Array.push($(lessonId).html());
                    });

                    $.ajax({
                        type: 'POST',
                        url: 'delete_lesson.action',
                        data: 'lessonIds=' + lessonIds_Array.toString(),
                        success: function (result) { 
                            
                            $('#table_data').html(result);
                            
                            $('#lesson_name').val("");
                            
                           // toastr.success($('#successMsg').val());
                           // toastr.success($('#errorMsg').val());
                        },
                        error: function (e) {
                            
                            toastr.error("OOps! Selected lessons not Deleted please Try again");

                        }
                    });
                }

            }

            $(document).ready(function () {
            	
            	<%--For Toster Messages --%>
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
               
                <%--All checkboxes Selection Listener --%>
                $('#maincheckbox').click(function (event) {
                    if ($('#maincheckbox').prop('checked')) {
                        //alert("checked \t"+$('#maincheckbox').val());		    		 

                        $('input[name=delete]').each(function () {
                            //alert("calling");
                            // alert($(this).attr("id"));
                            $(this).prop('checked', true);
                        });
                    } else {
                        // alert("Un checked \t"+$('#maincheckbox').val());
                        $('input[name=delete]').each(function () {
                            //alert("calling");
                            //alert($(this).attr("id"));
                            $(this).prop('checked', false);
                        });
                    }

                });


                <%--Table Columns Listener --%>
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
                        }

                    ]
                });

                <%--Form Submit Event --%>
                $("#submit").click(function () {
                	
                	
             	if($('#sectionId').val()==-1)
                	{
                	toastr.error("Please Select a Section");
                	}
                	else
                		{
                    if ($('#lesson_name').val == "" || $('#lesson_name').val().length == 0) {
                       
                        toastr.error("Please Enter Lesson Name"); 
                    }
                    else if($('#lesson_name').val().indexOf(" ")==0  )
                    	{ 
                    	toastr.error("No space please and don't leave Lesson Name empty");
                    	}
                    
                    else if(($('#lesson_name').val().length)>50)
                    {
                    	toastr.error("Entered Lesson Name is Too Long ,please try to reduce the lesson name size");
                    }
                      
                     else {
                        // alert( $('#lessonId').val());
                        $.ajax({
                            type: 'POST',
                            url: 'new_lesson_master.action',
                            data: $('#form').serialize(),
                            success: function (result) { 
                                $('#table_data').html(result); 
                              //  toastr.success($('#successMsg').val()); 
                              //  toastr.error($('#errorMsg').val()); 
                                $.fn.clear();
                                $('#lessonId').val(0);
                                $('#submit').text("Submit");
                            },
                            error: function (e) {
                               
                                toastr.error("Sorry! lesson name not saved please Try again");
                            }
                        });
                    }  
                		}  
                });

                <%--Reset Functionality --%>
                $("#reset").click(function () {
                    $.fn.clear();
                });
                $.fn.clear = function (str) {
                    $('#lesson_name').val("");
                    $("#sectionId").val($("#sectionId option:first").val());

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
                                <div class="panel-heading"> <span class="panel-title"> Add Lessons</span> 
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-4 col-lg-offset-4" style="padding-top:40px;">
                                     

                                            <form class="bs-example form-horizontal" id="form" name="form">

                                                <input type="hidden" name="lessonsbo.lessonId" id="lessonId" />
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Section ID</label>
                                                    <div class="col-sm-8">
                                                        <s:select name="lessonsbo.sectionId" cssClass="form-control m-b" list="sections_list" listValue="sectionName" listKey="sectionId" id="sectionId" headerKey="-1" headerValue="Select">
                                                        </s:select>
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">Lesson name</label>
                                                    <div class="col-lg-8">
                                                        <input type="text" class="form-control" placeholder="Lesson Name" name="lessonsbo.lessonName" id="lesson_name">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-lg-offset-4 col-lg-2">
                                                        <button type="button" class="btn  btn-success" id="submit">Submit</button>
                                                    </div>
                                                    <div class="col-lg-offset-1 col-lg-2">
                                                        <button type="button" class="btn  btn-danger" id="reset">Reset</button>
                                                    </div>
                                                </div>
                                                <!--form group close-->
                                            </form>
                                            <center> <span id="error"></span>
                                            </center>

                                            <hr>

                                        </div>
                                        <!--col for skill level close-->

                                    </div>
                                    <!--Inner row1 close -->


                                    <div class="row">
                                        <div class="col-lg-8 col-lg-offset-2" style="padding-top:40px;">

                                            <section class="scrollable padder">

                                                <section class="panel panel-default">


                                                    <div class="table-responsive" id="table_data">
                                                        <table class="table table-striped m-b-none" id="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>S.No</th>
                                                                    <th>Section</th>
                                                                    <th>Lesson</th>
                                                                    <th style="display : none">LessonId</th>
                                                                    <th style="display : none">sectionId</th>
                                                                    <th>Edit</th>
                                                                    <th>
                                                                        <label class="checkbox m-n i-checks">
                                                                            <input type="checkbox" id="maincheckbox"><i></i>
                                                                        </label>
                                                                    </th>


                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                                <s:iterator value="lessons_list" status="row">
                                                                    <tr>
                                                                        <td><s:property value="#row.index+1" />
                                                                        </td>
                                                                        <td><s:property value="sectionName" />
                                                                        </td>
                                                                        <td><s:property value="lessonName" />
                                                                        </td>
                                                                        <td style="display : none" id="lesson<s:property value=" #row.index+1 "/>"><s:property value="lessonId" />
                                                                        </td>
                                                                        <td style="display : none" id="section<s:property value=" #row.index+1 "/>"><s:property value="sectionId" />
                                                                        </td>
                                                                        <td>
                                                                            <button type="button" class="btn btn-sm btn-default" onclick="editTable('<s:property value=" sectionId "/>','<s:property value="lessonName " />','<s:property value="lessonId " />')">EDIT</button>
                                                                        </td>
                                                                        <td>
                                                                            <label class="checkbox m-n i-checks">
                                                                                <input type="checkbox" name="delete" id="<s:property value=" #row.index+1 "/>"/><i></i>
                                                                            </label>
                                                                        </td>

                                                                    </tr>
                                                                </s:iterator>


                                                            </tbody>
                                                        </table>
                                                           <!-- Input HiddenFields -->
                                                    </div>

                                                </section>
                                                <center><a href="javascript:deleteTabledata()" class="btn btn-del">Delete selected</a>
                                                </center>
                                            </section>
                                            <!--sec close for data table-->

                                            <hr>

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



        <%--All JS files --%>
        <script src="assets/js/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/js/datatables/jquery.csv-0.71.min.js"></script>
        <script src="assets/js/datatables/demo.js"></script>

    </body>

    </html>
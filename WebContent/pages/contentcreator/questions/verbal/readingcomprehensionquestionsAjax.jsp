<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
 $(document).ready(function(){ 
	 
	 <%--For Toaster MEssages --%>
	  $.fn.toasterMessages();
	  
	  <%--Total Checkboxes Selection Listener --%>
	  $.fn.selectTotalCheckboxes();
	  
	  <%--Data table columns--%>
	  $.fn.datatableColumns(); 
	  
  });
  $.fn.datatableColumns= function() {
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
		      { "mData": "columnname7" },
		      { "mData": "columnname8" },
		      { "mData": "columnname9" } 
		      
		    ]
		  } ); 
  };
  </script>
</head>
<body>
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
													<label class="checkbox m-n i-checks"><input
														type="checkbox" id="maincheckbox"><i></i></label>
												</th>


											</tr>
										</thead>

										<tbody>
											<s:iterator value="rcQuestionsList" status="row">
												<tr>

													<td><s:property value="#row.index+1" /></td>
													<td><s:property value="questionTypeName" /></td>
													<td><s:property value="passageName" /></td>
													<td><s:property value="questionTitle" /></td>
													<td><s:property value="skillsName" /></td>											 
													<td><s:property value="difficultyName" /></td>
													<td style="display: none" id="question<s:property value="#row.index+1"/>"><s:property value="questionId" /></td>													 
                                                    <td align="center"><button type="submit" class="btn btn-sm btn-default" id="Edit" onclick="editQuestion('<s:property
															value="questionId" />')">Edit</button>
                                                     </td>										
													<td class="chck" style="padding-top: 3px"><label
														class="checkbox m-n i-checks"><input
															type="checkbox" name="deleteIds"
															id="<s:property value="#row.index+1"/>" /><i></i></label></td>

												</tr>
											</s:iterator>


										</tbody>
									</table>
								</div>
</body>
</html>
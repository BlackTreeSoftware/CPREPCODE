<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.chck {
	width: 40px;
}

.text_center {
	text-align: center
}
</style>
<script type="text/javascript">
$(document).ready(function(){ 
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
		      { "mData": "columnname9" },
		      { "mData": "columnname10" }
		      
		    ]
		  } );
						$('#maincheckbox').click(function(event) {
							if ($('#maincheckbox').prop('checked')) {
								// alert("checked");		    		 

								$('input[name=delete]').each(function() {
									//alert("calling");
									// alert($(this).attr("id"));
									$(this).prop('checked', true);
								});
							} else {
								// alert("Un checked");
								$('input[name=delete]').each(function() {
									//alert("calling");
									//alert($(this).attr("id"));
									$(this).prop('checked', false);
								});
							}

						});

					});
					 
					</script>
</head>
<body>
<div class="table-responsive" id="table_data">
									<table class="table table-striped m-b-none" id="table">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Section Name</th>
												<th>Category Name</th>
												<th>Question Type</th>
												<th>Question Title</th>
												<!-- 	<th>Directions</th> -->
												<th>Skills</th>
												<th>Difficulty Level</th>
												<th style="display: none">QuestionId</th>
												<!-- 	<th>Question</th>
												<th>Solution Text</th>
												<th>Solution Video</th>
												<th>Access Type</th>
												<th>Referral</th>
												<th>Answer</th>
												<th>Choice1</th>
												<th>Choice2</th>
												<th>Choice3</th>
												<th>Choice4</th>
												<th>Choice5</th> -->
												 <th class="text_center">Edit</th>
												<th class="chck text_center" style="padding-bottom: -2px">
													<label class="checkbox m-n i-checks"><input
														type="checkbox" id="maincheckbox"><i></i></label>
												</th>


											</tr>
										</thead>

										<tbody>
											<s:iterator value="list" status="row">
												<tr>

													<td><s:property value="#row.index+1" /></td>
													<td><s:property value="sectionName" /></td>
													<td><s:property value="categoryName" /></td>
													<td><s:property value="typeName" /></td>
													<td><s:property value="questionTititle" /></td>
													<%-- <td><s:property value="directions" /></td> --%>
													<td><s:property value="skillNames" /></td>
													<td><s:property value="difficultyName" /></td>
													<td style="display: none"
														id="question<s:property value="#row.index+1"/>"><s:property
															value="questionId" /></td>
													<%-- <td><s:property value="question" /></td>
													<td><s:property value="solutionText" /></td>
													<td><s:property value="solutionVideo" /></td>
													<td><s:property value="accessType" /></td>
													<td><s:property value="referralType" /></td>
													<td><s:property value="answer" /></td>
													<td><s:property value="choices[0]" /></td>
													<td><s:property value="choices[1]" /></td>
													<td><s:property value="choices[2]" /></td>
													<td><s:property value="choices[3]" /></td>
													<td><s:property value="choices[4]" /></td>
													
 --%>
                                                      <td align="center"><button type="button" class="btn btn-sm btn-default">EDIT</button></td>
										
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
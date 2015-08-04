<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">

$(document).ready(function(){

var oTable = $('#admin1').dataTable( {

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



});


</script>



</head>
<body>

                  <div class="table-responsive" id="ajax-admin">
                  <table class="table table-striped m-b-none" id="admin1">
                    <thead>
                        <tr>
                        <th>Sr.No</th>
                        <th>Select</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Section</th>
                        <th>Contact</th>
                        <th>Status</th> 
                         <th>Edit</th> 
                        </tr>
                    </thead>
                    <tbody>
                     <s:iterator value="adminRegistrationList" status="row"> 
                     <tr>
                     <td><s:property value="#row.index+1"/></td>
                     <td><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="admin_id"/>'><i></i></label></td>
                     <td><s:property value="admin_name"/></td>
                     <td><s:property value="email"/></td>
                     <td><s:property value="role" /></td>
                     <td><s:property value="section_name" /></td>
                     <td><s:property value="contact" /></td>
                     <td><s:property value="status" /></td>
                     <td><a class="btn btn-default" href="javascript:setDetails('<s:property value="admin_name"/>','<s:property value="email"/>','<s:property value="contact" />','<s:property value="status"/>','<s:property value="section_name" />','<s:property value="role" />')"  >Edit</a></td>
                     </tr>
                    </s:iterator>
                    </tbody>
                    </table>
                
                </div>

</body>
</html>
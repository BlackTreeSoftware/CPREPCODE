<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		      { "mData": "columnname9" }
		      
		    ]
		  } );
	  
}
	  
); 



</script>
</head>
<body>
               <div class="row" id="table_data">
                <div class="col-lg-10 col-lg-offset-1" style="padding-top:40px;">
                        
               <label id="msglabel" style="display: none;"><%
              
               if(request.getAttribute("message")==null)
               {
            	 out.write("");   
               }else{
            	out.write(request.getAttribute("message").toString());   
               }
               %></label> 
             
              <section class="scrollable padder">             
              <section class="panel panel-default">
                
                <div class="table-responsive" >                      
                <form id="deleteData">
                  <table class="table table-striped m-b-none" id="table">
                    <thead>
                      <tr>
                        <th>S.No</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Passage</th>
                        <th>Difficulty</th>
                        <th>Access Type</th>
                        <th>Status</th>
                        <th>Edit</th>
                        <th class="chck text_center" style="padding-bottom:0px"><label class="checkbox m-n i-checks"><input type="checkbox" id="maincheckbox"><i></i></label></th>
                         </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="readingComprehensionBOs" status="sno">
                    <tr> 
                                       
                    <td><s:property value="#sno.index+1"></s:property></td>
                    <td><s:property value="passage_title"></s:property></td>
                    <td><s:property value="passage_type"></s:property></td>
                    <td><s:property value="passage"></s:property></td>
                     <td><s:property value="difficulty_name"></s:property></td>
                    <td><s:property value="access_type"></s:property></td>
                    <td><s:property value="status"></s:property></td>
                    <td><button type="button" class="btn btn-sm btn-default"  value="EDIT" onclick="editRecord('<s:property value="passage_id"/>','<s:property value="difficulty_name"/>', '<s:property value="passage_title"/>','<s:property value="passage"/>','<s:property value="passage_type"/>','<s:property value="difficulty"/>','<s:property value="access_type"/>','<s:property value="status"/>')">EDIT</button></td>
                    <td class="chck" style="padding-top:3px"><label class="checkbox m-n i-checks"><input type="checkbox" id="select" value='<s:property value="passage_id"/>' name="deleterecords"  /><i></i></label></td>
                    </tr>
                    </s:iterator>
                    </tbody>
                  </table>
                      </form>
                </div>
                
                </section>
                <center><a href="#" class="btn btn-del" onclick="deleteData();">Delete selected</a></center>
              </section><!--sec close for data table-->
              
              <hr>
                      
             </div><!--col for DataTable close-->
             
             </div><!--Inner row2 close -->
</body>
</html>

 
 
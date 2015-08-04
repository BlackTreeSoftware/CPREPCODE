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
	if('<s:property value="#request.resume"/>'=='normal')
		{
		//alert(' resume is : Noraml');
		$('#resume').hide(); 
		
		}
	else{
		//alert(' resume is : resume');
		$('#take').hide(); 
	}
	
});
</script>
<script type="text/javascript">
function resumeTestNavigation(){
	alert(' in resume Test ');
	$('#nextAction').submit();
}

</script>
<script type='text/javascript' src="assets/js/full-length-test/retrieval_navigations.js"></script>
</head>
<body>
 <section id="content">
          <section class="vbox">          
            <section class="scrollable wrapper">

          
             
             <div class="panel panel-default">
            
             <div class="panel-body">
            
             
             <div class="row" >
             <div class="col-sm-8 col-sm-offset-2 ">
             
            <label id="check" style="display: none;"><s:property value="#request.resume"/></label>
            
             <p  style="font-size: 36px; margin-top:30px;" class="font-bold text-center">GRE FULL LENGTH TEST PAGE</p>
             <s:if test="#request.resume=='normal'">
             
             </s:if>
             <s:else>
           <%--   table Data is :<s:property value="#request.tableData"/> --%>
             
             </s:else>
             
             <p class="h4 text-center"><strong>General Instructions </strong></p><br>
             <ul>
             <li><p align="left"> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is <br>
             	that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing <br>             	             	             	
              </p>
              </li>
              
              <li>
               <p align="left"> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is <br>
             	that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing <br>
             	             	
              </p>
              </li>
              </ul>
            </div>
             
             </div><!--Inner row2 close -->
             <hr/>
            <div class="row" style=" margin-top:30px; margin-bottom:30px;">
            <center>
            <a id="take" class="btn btn-success btn-test" href="Start-Full-Test">Take a Test</a>
            <a  id="resume" class="btn btn-primary btn-test" onClick="resumeTestNavigation()">Resume Test</a>
            </center>
            <div class="col-sm-1 col-sm-offset-1"></div>
            <!-- <div class="col-sm-1 col-sm-offset-1"><a class="btn btn-default col-lg-offset-1 btn-test" href="#">Review Answers</a></div> -->            	            
            </div>
             </div><!--panel body close-->             
             </div><!--panel head close-->
             
           
      <form action="Start-Resume-Test" id="nextAction" method="post">
       <s:if test="#request.resume=='normal'">
      <s:hidden value="#request.tableData" name="tableData" id="tableData"/>
      </s:if>
      </form>       
            
            
            </section>
          </section>
         
      </section>
     
</body>
</html>
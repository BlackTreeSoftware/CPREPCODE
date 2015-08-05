<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>it' Home Page</title>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript">

function pageLoaded(){
	$('#loginForm').submit();	
}
</script>
</head>
<body onload="pageLoaded()">
<h2 id="matter"> Hello Every one</h2> 

<form action="defaultAction.action" id="loginForm"/>
</body>
</html>

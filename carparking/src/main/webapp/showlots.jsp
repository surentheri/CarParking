<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "carparkingPOJO.ScrCarParkingPOJO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check out</title>
<link rel="stylesheet" href="styles.css">
</head>
<%
ScrCarParkingPOJO scrParkingPojoArr[] = (ScrCarParkingPOJO[]) request.getAttribute("obj");
boolean Submitted=false;
ScrCarParkingPOJO scrobj=null;
%>
<style>
table,th,tr,td{
border : 1px solid black;
width:50%;
text-align : center;
margin-left: auto;
margin-right: auto;
border-spacing: 0 0em;
}
</style>
<%
String result="";
String Parking="";
%>
<body class="body">

<form name="myform" action="checkout"  method="post">
	<button  class="submit" style="float: left;" onclick="home()" > home </button>

<div  class="center" >
<img class="logo" alt="logo" src= "Logo.png" width="50" height="50"/>
<span class="span" style="font-size:50px ">Parking Lot</span>
 </div>
 
<h3>SHOW LOTS</h3>
<h4>Total no of floors : <%=scrParkingPojoArr.length-1%></h4>


<table>

<tr><th>Floor</th> <th> Capacity </th> <th> No of Vechicle </th> <th> Availability </th> <tr>
<% for (int i=0;i<scrParkingPojoArr.length;i++) {
	scrobj = scrParkingPojoArr[i];
%>
<tr>
<td><%=scrobj.getFloor()%> </td>
<td><%=scrobj.getCapacity()%> </td>
<td><%=(scrobj.getCapacity()- scrobj.getAvailable())%> </td>
<td><%=scrobj.getAvailable()%> </td>
</tr>

<% }%>
</table>
</form>
</body>
<script>
	function home() {
		document.myform.action = "options.jsp";
		document.myform.action.submit();
	}
</script>
</html>
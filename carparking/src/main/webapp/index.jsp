<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Parking</title>
<link rel="stylesheet" href="styles.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<%
System.out.println(request);

%>
<style>
table, th, tr, td {
	border: 2px solid black;
	width: 50%;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	border-spacing: 0 0em;
}
</style>

<body class="body" onload=onloadfun()>

	<form name="myform" action="options.jsp">
		<div class="center">
			<img class="logo" alt="logo" src="Logo.png" width="50" height="50" />
			<span class="span" style="font-size: 50px">Parking Lot</span>
		</div>

		<h3>Welcome to the Parking Lot</h3>
		<h4>
			<span id="data" name="data"> </span>
		</h4>
		<table class="table">
			<tr>
				<th>Type of Vechicle</th>
				<th>Cost/Hour</th>
			</tr>
			<tr>
				<td><img alt="car" src="car.png" /></td>
				<td>50 / hour</td>
			</tr>
			<tr>
				<td><img alt="bike" src="bike.png" /></td>
				<td>10 / hour</td>
			</tr>
		</table>
		<br> <br> <input style="background-color: solid black"
			class="submit" type="submit" value="Proceed"> <input
			type="hidden" name="handleid" id="handleid" value="onload">
	</form>
</body>
<script>
function onloadfun(){
	var name = 'onload';
	$.ajax({
	url     : '/carparking/Processing',
	method     : 'POST',
	data    : {
		"action" : "onload" 
	},
	success    : function(resultText){
	$('#data').html(resultText);
	}
	});
}
</script>
</html>
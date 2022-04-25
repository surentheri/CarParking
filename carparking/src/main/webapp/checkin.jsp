<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check In</title>
<link rel="stylesheet" href="styles.css">
</head>

<style>
table, th, tr, td {
	border-spacing: 0 2em;
}
</style>
<%
String result = request.getAttribute("result") == null ? "" : (String) request.getAttribute("result");
%>
<body class="body">

	<form name="myform" action="/carparking/Processing"  method="post">
		<button class="submit" style="float: left;" onclick="home()">home</button>

		<div class="center">
			<img class="logo" alt="logo" src="Logo.png" width="50" height="50" />
			<span class="span" style="font-size: 50px">Parking Lot</span>
		</div>

		<h3>CHECK IN</h3>

		<table>
			<tr>
				<td><label>Vehicle Number </label></td>
				<td><input type="text" name="VechicleNumber"
					id="VechicleNumber"></td>
			</tr>
			<tr>
				<td><label>Vehicle Type</label></td>
				<td><input type="radio" name="type" value="car"> <label><img
						alt="car.png" src="car.png" width=30 height=30 /></label> <input
					type="radio" name="type" value="bike"> <label><img
						alt="bike.png" src="bike.png" width=30 height=30 /></label></td>
			</tr>

			<tr>
				<td><label>Enter Lot</label></td>
				<td><input type="text" name="lot" id="lot"></td>
			</tr>

			<tr>
				<td><label>Check in Time</label></td>
				<td><input type="time" id="time" name="time" /></td>
			</tr>

		</table>
		<input type="hidden" name="action" value="checkIn"> <input
			type="submit" value="CheckIn" class="submit">
	</form>
	<p><%=result%></p>
</body>

<script type="text/javascript">
	function home() {
		document.myform.action = "options.jsp";
		document.myform.action.submit();
	}
</script>
</html>
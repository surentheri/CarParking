<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check out</title>
<link rel="stylesheet" href="styles.css">
</head>

<style>
table, th, tr, td {
	border-spacing: 0 2em;
}
</style>
<%
String result = request.getAttribute("result")==null ? "" : (String) request.getAttribute("result");
String cost =  request.getAttribute("cost") == null ? "" : (String)request.getAttribute("cost") ;
%>
<body class="body">

	<form name="myform" action="/carparking/Processing" method="post">
		<button class="submit" style="float: left;" onclick="home()">home</button>

		<div class="center">
			<img class="logo" alt="logo" src="Logo.png" width="50" height="50" />
			<span class="span" style="font-size: 50px">Parking Lot</span>
		</div>

		<h3>CHECK OUT</h3>

		<table>
			<tr>
				<td><label>Vehicle Number </label></td>
				<td><input type="text" name="VechicleNumber"
					id="VechicleNumber"></td>
			</tr>

			<tr>
				<td><label>Check out Time</label></td>
				<td><input type="time" name="checkouttime" id="checkouttime" />
				</td>
			</tr>

		</table>
		<input type="hidden" value="checkout" name="action"> <input
			type="submit" value="Check out" class="submit">
	</form>
	<p><%=result%></p>
	<a><%=cost%></a>
</body>
<script type="text/javascript">
	function home() {
		document.myform.action = "options.jsp";
		document.myform.action.submit();
	}
</script>
</html>
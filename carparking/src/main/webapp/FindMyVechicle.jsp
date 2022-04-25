<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="carparkingPOJO.ScrVechiclePOJO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check out</title>
<link rel="stylesheet" href="styles.css">
</head>
<%
boolean Submitted = request.getAttribute("submitted") != null ? true : false;
String result = (String) request.getAttribute("result");
String history = (String) request.getAttribute("history");
%>
<style>
table, th, tr, td {
	border-spacing: 0 2em;
}
</style>
<%

%>
<body class="body">

	<form name="myform" action="Processing" method="post">
		<button class="submit" style="float: left;" onclick="home()">
			home</button>

		<div class="center">
			<img class="logo" alt="logo" src="Logo.png" width="50" height="50" />
			<span class="span" style="font-size: 50px">Parking Lot</span>
		</div>

		<h3>Find Vechicle</h3>

		<table>
			<tr>
				<td><label>Vehicle Number </label></td>
				<td><input type="text" name="VechicleNumber"
					id="VechicleNumber"></td>
			</tr>
			<input type="hidden" name="action" value="findvech">
		</table>
		<input type="submit" value="Find" class="submit">
	</form>
	<%
	if (Submitted) {
	%>
	<table>
		<tr>
			<th>Parking History</th>

			<th>InParking</th>
		</tr>

		<tr>
			<td>
				<%
				if (history != null && history.contains("-")) {
				%>
				<table>
					<tr>
						<th>Floor</th>
						<th>Date</th>
						<th>Check In Time</th>
						<th>Check out Time</th>
						<tr>
						<%
						for (String row : history.split("-")) {
							String[] col = row.split(",");
						%>
					
					
					
					<tr>
						<td><%=col[0]%></td>
						<td><%=col[1]%></td>
						<td><%=col[2]%></td>
						<td><%=col[3]%></td>
					</tr>
					<%
					}
					%>
				</table> <%
 } else {
 %> <%=history%> <%
 }
 %>
			</td>
			<td><%=result %>
	</table>
	<%
	}
	%>
</body>
<script type="text/javascript">
	function home() {
		document.myform.action = "options.jsp";
		document.myform.action.submit();
	}
</script>
</html>
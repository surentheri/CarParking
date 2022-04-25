<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Parking</title>
<link rel="stylesheet" href="styles.css">
</head>
<%
int noOfLot = 1;
int noOfCapacity = 1;
%>


<body class="body">

	<form name="myform">
		<div class="center">
			<img class="logo" alt="logo" src="Logo.png" width="50" height="50" />
			<span class="span" style="font-size: 50px">Parking Lot</span>
		</div>
		<table>

			<tr>
				<td><button class="submit" onclick="CheckIn()">Check
						In</button></td>
			</tr>
			<tr>
				<td>
					<button class="submit" onclick="CheckOut()">Check Out</button>
				</td>
			</tr>

			<tr>
				<td>
					<button class="submit" onclick="findVechicle()">Find
						Vechicle</button>
				</td>
			</tr>
		</table>
		<button class="showlot" onclick="showLot()">Show Lot</button>
		<input type="hidden" id="action" name="action">
	</form>

	<script>
		function CheckIn() {
			document.myform.action = "checkin.jsp";
			document.myform.action.submit();
		}

		function CheckOut() {
			document.myform.action = "checkout.jsp";
			document.myform.action.submit();
		}

		function findVechicle() {
			document.myform.action = "FindMyVechicle.jsp";
			document.myform.action.submit();
		}

		function showLot() {
			document.myform.action = "/carparking/Processing";
			document.myform.action.value = "showlot"
			document.myform.action.submit();
		}
	</script>

</body>
</html>
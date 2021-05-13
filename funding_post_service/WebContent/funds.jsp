<%@page import="com.Funding_post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/funds.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Funds Management V10.1</h1>
<form id="formFund" name="formFund">
 Fund name:
 <input id="fundMan_Name" name="fundMan_Name" type="text"
 class="form-control form-control-sm">
 <br> Fund email:
 <input id="Email" name="Email" type="text"
 class="form-control form-control-sm">
 <br> Fund contact:
 <input id="Contact" name="Contact" type="text"
 class="form-control form-control-sm">
 <br> Fundman ID:
 <input id="fundman_ID" name="fundman_ID" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidFundIDSave"
 name="hidFundIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divFundsGrid">
 <%
 Funding_post fundObj = new Funding_post();
     out.print(fundObj.readFunds());
 %>
</div>
</div> </div> </div>
</body>
</html>
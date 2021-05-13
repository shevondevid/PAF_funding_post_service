$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();
 	
 	var status = validateFundForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "FundAPI",
 type : type,
 data : $("#formFund").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onFundSaveComplete(response.responseText, status);
 }
 });
});



function onFundSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divFundGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 }
$("#hidFundIDSave").val("");
$("#formFund")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidFundIDSave").val($(this).data("fundid"));
		 $("#fundMan_Name").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#Email").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#Contact").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#fundman_ID").val($(this).closest("tr").find('td:eq(3)').text());
		});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "FundAPI",
		 type : "DELETE",
		 data : "fund_ID=" + $(this).data("fundid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onFundDeleteComplete(response.responseText, status);
		 }
		 });
		});



function onFundDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divFundGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}




//CLIENT-MODEL================================================================
function validateFundForm()
{
// Name
if ($("#fundMan_Name").val().trim() == "")
 {
 return "Insert Fund Name.";
 }
// Email
if ($("#Email").val().trim() == "")
 {
 return "Insert Fund Email.";
 }
// Contact-------------------------------
if ($("#Contact").val().trim() == "")
 {
 return "Insert Fund Contact.";
 }

// Fundman ID------------------------
if ($("#fundman_ID").val().trim() == "")
 {
 return "Insert Fund Fundman ID.";
 }
return true;
}
 	
package com;
import java.sql.*;
public class Funding_post
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
		 "jdbc:mysql://127.0.0.1:3306/gadjet_badget?serverTimezone=UTC", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readFunds()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Funda_Name</th><th> Email </th><th>Contact</th><th>Fundman_ID</th><th>Update</th><th>Remove</th></tr>";
 String query = "select * from Funds";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
	 String fund_ID= Integer.toString(rs.getInt("fund_ID"));
		String fundMan_Name= rs.getString("fundMan_Name");
		String Email=rs.getString("Email");
		String Contact=rs.getString("Contact");
		String fundman_ID = rs.getString("fundman_ID");
		//String funded_date = rs.getString("funded_date");// How to Get Date as A String - Doubt
		//String funded_time = rs.getString("funded_time");// How to Get Date as A String - Doubt
 // Add into the html table
 output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" + fund_ID
 + "'>" + fundMan_Name + "</td>";
 output += "<td>" + Email + "</td>";
 output += "<td>" + Contact + "</td>";
 output += "<td>" + fundman_ID + "</td>";
 //output +="<td>"+funded_date+"</td>";
 //output +="<td>"+funded_time+"</td>";
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundid='"
 + fund_ID + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Funds.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertFund(String fundManName, String email,String contact,String fundmanID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 // create a prepared statement
 String query = " insert into Funds(`fund_ID`,`fundMan_Name`,`Email`,`Contact`,`fundman_ID`)"
 
+ " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, fundManName);
		 preparedStmt.setString(3, email);
		 preparedStmt.setString(4, contact);
		 preparedStmt.setString(5, fundmanID);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newFunds = readFunds();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newFunds + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the funds.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updateFund(String fundid,String fundManName, String email,String contact,String fundmanID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for updating.";
		 }
		 // create a prepared statement
		 String query = "UPDATE Funds SET fundMan_Name=?,Email=?,Contact=?,fundman_ID=? WHERE fund_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, fundManName);
		 preparedStmt.setString(2, email);
		 preparedStmt.setString(3, contact);
		 preparedStmt.setString(4, fundmanID);
		 preparedStmt.setInt(5, Integer.parseInt(fundid)); 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newFunds = readFunds();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newFunds + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the funds.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteFund(String fund_ID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 }
		 // create a prepared statement
		 String query = "delete from Funds where fund_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fund_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newFunds = readFunds();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newFunds + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the funds.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		}
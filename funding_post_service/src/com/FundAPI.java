package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FundAPI") 
public class FundAPI  extends HttpServlet {
	
	Funding_post fundObj = new Funding_post();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 String output = fundObj.insertFund(request.getParameter("fundMan_Name"),
			request.getParameter("Email"),
			request.getParameter("Contact"),
			request.getParameter("fundman_ID"));
			response.getWriter().write(output);
			}
	
	
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = fundObj.updateFund(paras.get("hidFundIDSave").toString(),
			paras.get("fundMan_Name").toString(),
			paras.get("Email").toString(),
			paras.get("Contact").toString(),
			paras.get("fundman_ID").toString());
			response.getWriter().write(output);
			} 
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = fundObj.deleteFund(paras.get("fund_ID").toString());
			response.getWriter().write(output);
			}
}

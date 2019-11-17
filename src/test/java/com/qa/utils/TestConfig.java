package com.qa.utils;
public class TestConfig{


	
	public static String server="smtp.gmail.com";
	public static String from = "sammca87@gmail.com";
	public static String password = "yuvi@666";
	public static String[] to ={"sammca87@gmail.com"};
	public static String subject = "Test Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath=System.getProperty("user.dir") + "\\target\\extentreports\\extent.html";
	public static String attachmentName="extent.html";
	
	
	
	//SQL DATABASE DETAILS	
	public static String driver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	//MYSQL DATABASE DETAILS
	public static String mysqldriver="com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/18thaug2018";
	
	
	
	
	
	
	
	
	
}

package edu.luc.webservices.helpers;
import java.sql.*;

public class TestHelper {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://us-cdbr-azure-west-c.cloudapp.net:3306/projectwebservices";
	
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;	//  Database credentials
	static final String USER = "bcfe38148e2425";
	static final String PASS = "887cac00";
	
	public static ResultSet queryDatabase(String tableName){
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			if (tableName.equals("orders")){
				sql = "SELECT * FROM " + tableName +" ORDER BY Order_ID DESC LIMIT 1;";
			}else{
				sql = "SELECT * FROM " + tableName +" ORDER BY " + tableName + "_ID DESC LIMIT 1;";
			}
			rs = stmt.executeQuery(sql);
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		System.out.println("Finished Query!");
		return rs;
	}
	
	public static ResultSet makeQuery(String query){
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		System.out.println("Finished Query!");
		return rs;
	}
}

package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	public int Id; 
	public String Username; 
	
	public User() {}
	
	public User(int Id, String Username)
	{
		this.Id = Id;
		this.Username = Username;
	}
	
	public static Boolean DoesUserExist(String username)
	{
		Statement stmt = null;
		ResultSet rs = null;

		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT Username FROM [dbo].[User] WHERE [Username] = '" + username + "'");		 
		    if (rs.next())
		    {
		    	return true;  	 
		    }
		    
		    return false;
		}
		catch (SQLException ex)
		{
		    System.out.println(ex.getMessage());
		    System.out.print('\n');
		    return true;
		}
		finally 
		{
			Database.SqlFinally(stmt, rs);
		}
	}
	
	public static User GetUserByUserName(String username) throws Exception
	{
		Statement stmt = null;
		ResultSet rs = null;

		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT Id, Username FROM [dbo].[User] WHERE [Username] = '" + username + "'");		 
		    if (rs.next())
		    {
		    	User user = new User();
		    	user.Id = rs.getInt("Id");
		    	user.Username = rs.getString("Username");
		    	return user;
		    }		    
		}
		catch (SQLException ex)
		{
		    System.out.println(ex.getMessage());
		    System.out.print('\n');
		}
		finally 
		{
			Database.SqlFinally(stmt, rs);
		}
		
		throw new Exception ("User Not Found");
	}
	
	public static Boolean InsertNewUser(String username)
	{
		Statement stmt = null;
		ResultSet rs = null;

		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    stmt.executeUpdate("INSERT INTO [dbo].[User] VALUES ('" + username + "');");		 
		    return true;
		}
		catch (SQLException ex)
		{
		    System.out.println(ex.getMessage());
		    System.out.print('\n');
		    return false;
		}
		finally 
		{
			Database.SqlFinally(stmt, rs);
		}
	}
	
}

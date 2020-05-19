package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public static Connection GetCloudSqlConnection()
	{
		try 
		{
			return DriverManager.getConnection("jdbc:sqlserver://tcss-545.database.windows.net:1433;database=MovieCollection;user=appuser@tcss-545;password=Password1234!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void SqlFinally(Statement stmt, ResultSet rs)
	{
		if (rs != null) 
	    {
	        try 
	        {
	            rs.close();
	        } 
	        catch (SQLException sqlEx) { }

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { }

	        stmt = null;
	    }
	}
}

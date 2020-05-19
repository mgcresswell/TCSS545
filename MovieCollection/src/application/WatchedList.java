package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WatchedList {
	public int UserId;
	public int MovieId; 
	
	public WatchedList() {}
	
	public WatchedList(int UserId, int MovieId)
	{
		this.UserId = UserId;
		this.MovieId = MovieId;
	}
	
	public static Boolean AddMovie()
	{
		Statement stmt = null;
		ResultSet rs = null;

		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    stmt.executeUpdate("INSERT INTO [dbo].[WatchedList] VALUES ( '" + UserLibrary.currentUser.Id + "','" + UserLibrary.currentMovieId + "');");		 
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
	
	public static Boolean RemoveMovie()
	{
		Statement stmt = null;
		ResultSet rs = null;

		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    stmt.executeUpdate("DELETE FROM [dbo].[WatchedList] WHERE UserId = " + UserLibrary.currentUser.Id + " AND MovieId = " + UserLibrary.currentMovieId + ";");		 
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
	
	public static Boolean Contains(int movieId)
	{
		Statement stmt = null;
		ResultSet rs = null;

		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT UserId, MovieId FROM [dbo].[WatchedList] WHERE [MovieId] = '" + movieId + "'");		 
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
	
	public static ObservableList<MovieGrid> GetWatchedList()
	{
		Statement stmt = null;
		ResultSet rs = null;
		ObservableList<MovieGrid> movies = FXCollections.observableArrayList();
		
		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT [MovieId], [Title], [ReleaseDate] FROM [dbo].[WatchedList] wl INNER JOIN [dbo].[Movie] m on wl.MovieId = m.Id Where UserId =" + UserLibrary.currentUser.Id + ";");		 
		    while (rs.next())
		    {
		    	MovieGrid newMovie = new MovieGrid();
		    	newMovie.Id.setValue(rs.getInt("MovieId"));
		    	newMovie.Title.setValue(rs.getString("Title"));
		    	newMovie.ReleaseDate.setValue(rs.getDate("ReleaseDate").toString());
		    	movies.add(newMovie);
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
		return movies;
	}
}
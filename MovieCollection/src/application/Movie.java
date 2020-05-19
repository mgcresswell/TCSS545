package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Movie {
		
		public int Id; 
		public String Title; 
		public Date ReleaseDate;
		public Integer RunTime; 
		public Double Budget; 
		public String Overview; 
		public String Tagline; 
		public Double Revenue; 
		public List<MovieGenre> GenreList;
		public List<MovieProductionCompany> ProductionComapnyList;
		
		Movie () {}
		
		Movie (int Id, String Title, Date ReleaseDate, Integer RunTime, Double Budget, String Overview, String Tagline, Double Revenue)
		{
			this.Id = Id;
			this.Title = Title;
			this.ReleaseDate = ReleaseDate;
			this.RunTime = RunTime; 
			this.Budget = Budget;
			this.Overview = Overview; 
			this.Tagline = Tagline; 
			this.Revenue = Revenue; 
			this.GenreList = new ArrayList<MovieGenre>();
			this.ProductionComapnyList = new ArrayList<MovieProductionCompany>();
		}
		
		public static Movie GetMovieById(int movieId)
		{
			Statement stmt = null;
			ResultSet rs = null;
			Movie movie = new Movie();
			
			try 
			{
				Connection conn = Database.GetCloudSqlConnection();
			    stmt = conn.createStatement();
			    rs = stmt.executeQuery("SELECT [Id],[Title],[ReleaseDate],[RunTime],[Budget],[Overview],[Tagline],[Revenue] FROM [dbo].[Movie] WHERE [Id] = '" + UserLibrary.currentMovieId + "'");		 
			    if (rs.next())
			    {
			    	movie.Id = rs.getInt("Id");
			    	movie.Title = rs.getString("Title");
			    	movie.ReleaseDate = rs.getDate("ReleaseDate");
			    	movie.RunTime = rs.getInt("RunTime");
			    	movie.Budget = rs.getDouble("Budget");
			    	movie.Overview = rs.getString("Overview");
			    	movie.Tagline = rs.getString("Tagline");
			    	movie.Revenue = rs.getDouble("Revenue");
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
			return movie;
		}
}

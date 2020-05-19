package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieGrid {
    public SimpleIntegerProperty Id = new SimpleIntegerProperty();
    public SimpleStringProperty Title = new SimpleStringProperty();
    public SimpleStringProperty ReleaseDate = new SimpleStringProperty();
     
    public int getId() {
        return Id.get();
    }

    public String getTitle() {
        return Title.get();
    }

    public String getReleaseDate() {
        return ReleaseDate.get();
    }
    
    public static ObservableList<MovieGrid> SearchMovies(String search)
	{
		Statement stmt = null;
		ResultSet rs = null;
		ObservableList<MovieGrid> movies = FXCollections.observableArrayList();
		
		try 
		{
			Connection conn = Database.GetCloudSqlConnection();
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT [Id], [Title], [ReleaseDate] FROM [dbo].[Movie] WHERE [Title] like '%" + search + "%'");		 
		    while (rs.next())
		    {
		    	MovieGrid newMovie = new MovieGrid();
		    	newMovie.Id.setValue(rs.getInt("Id"));
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
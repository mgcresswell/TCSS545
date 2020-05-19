package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MovieViewController implements Initializable {
	
	@FXML AnchorPane AnchorPane;
	@FXML Label movieTitle = new Label();
	@FXML Label movieTagline = new Label();
	@FXML Label movieReleaseDate = new Label();
	@FXML Label movieRunTime = new Label();
	@FXML Label movieBudget = new Label();
	@FXML Label movieRevenue = new Label();
	@FXML TextArea movieOverview = new TextArea();
	@FXML Button addToCollectionBtn = new Button();
	@FXML Button addToWatchListBtn = new Button();
	@FXML Button addToWatchedListBtn = new Button();

	
	@FXML
    private void addToCollection(ActionEvent ev) 
    { 
		Movie currentMovie = Movie.GetMovieById(UserLibrary.currentMovieId);
		if (MovieCollection.Contains(currentMovie.Id))
		{
			if (MovieCollection.RemoveMovie())
			{
				addToCollectionBtn.setText("Add To Collection");
			}			
		}
		else
		{
			if (MovieCollection.AddMovie())
			{
				addToCollectionBtn.setText("Remove From Collection");
			}
		}	
    }
	
	@FXML
    private void addToWatchList(ActionEvent ev) 
    { 
		Movie currentMovie = Movie.GetMovieById(UserLibrary.currentMovieId);
		if (WatchList.Contains(currentMovie.Id))
		{
			if (WatchList.RemoveMovie())
			{
				addToWatchListBtn.setText("Add To Watch List");
			}	
		}
		else
		{
			if (WatchList.AddMovie())
			{
				addToWatchListBtn.setText("Remove From Watch Listn");
			}
		}
    }
	
	@FXML
    private void addToWatchedList(ActionEvent ev) 
    { 
		Movie currentMovie = Movie.GetMovieById(UserLibrary.currentMovieId);
		if (WatchedList.Contains(currentMovie.Id))
		{
			if (WatchedList.RemoveMovie())
			{
				addToWatchedListBtn.setText("Add To Watched List");
			}	
		}
		else
		{
			if (WatchedList.AddMovie())
			{
				addToWatchedListBtn.setText("Remove From Watched List");
			}
		}
    }
	
	@FXML
    private void homeBtnAction(ActionEvent ev) 
    { 
		 try 
		 {
			 Parent window3 = FXMLLoader.load(getClass().getResource("/application/UserView.fxml"));
			 Scene newScene = new Scene(window3);
			 Stage mainWindow = (Stage)  ((Node)ev.getSource()).getScene().getWindow();
			 mainWindow.setScene(newScene);	
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Movie currentMovie = Movie.GetMovieById(UserLibrary.currentMovieId);
		movieTitle.setText(currentMovie.Title);
		movieTagline.setText(currentMovie.Tagline);
		movieReleaseDate.setText(currentMovie.ReleaseDate.toString());
		movieRunTime.setText(currentMovie.RunTime.toString());
		movieBudget.setText(currentMovie.Budget.toString());
		movieRevenue.setText(currentMovie.Revenue.toString());
		movieOverview.setText(currentMovie.Overview);
		
		if (!WatchedList.Contains(currentMovie.Id))
		{
			addToWatchedListBtn.setText("Add To Watched List");				
		}
		else
		{
			addToWatchedListBtn.setText("Remove From Watched List");		
		}
		
		if (!WatchList.Contains(currentMovie.Id))
		{
			addToWatchListBtn.setText("Add To Watch List");			
		}
		else
		{
			addToWatchListBtn.setText("Remove From Watch Listn");			
		}
		
		if (!MovieCollection.Contains(currentMovie.Id))
		{
			addToCollectionBtn.setText("Add To Collection");					
		}
		else
		{
			addToCollectionBtn.setText("Remove From Collection");		
		}

	}
}

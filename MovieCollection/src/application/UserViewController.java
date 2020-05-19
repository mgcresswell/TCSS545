package application;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserViewController implements Initializable {
	
	@FXML AnchorPane AnchorPane;	
	@FXML Label userLbl = new Label();
	@FXML TableView<MovieGrid> itemTbl;
    @FXML TableColumn<MovieGrid, Integer> movieIdCol;  
    @FXML TableColumn<MovieGrid, String> movieTitleCol;
    @FXML TableColumn<MovieGrid, String> movieReleaeDateCol;

    ObservableList<MovieGrid> data;
	
	@FXML
    private void viewWatchListAction(ActionEvent ev) 
    { 
		ObservableList<MovieGrid> movieResult = WatchList.GetWatchList();
	    itemTbl.setItems(movieResult);
    }
	
	@FXML
    private void viewWatchedListAction(ActionEvent ev) 
    { 
		ObservableList<MovieGrid> movieResult = WatchedList.GetWatchedList();
	    itemTbl.setItems(movieResult);
    }
	
	@FXML
    private void viewCollectionListAction(ActionEvent ev) 
    { 
		ObservableList<MovieGrid> movieResult = MovieCollection.GetCollection();
	    itemTbl.setItems(movieResult);
    }
	
	@FXML
    private void movieSearchAction(ActionEvent ev) 
    { 
		try 
		 {
			 Parent window3 = FXMLLoader.load(getClass().getResource("/application/MovieSearch.fxml"));
			 Scene newScene = new Scene(window3);
			 Stage mainWindow = (Stage)  ((Node)ev.getSource()).getScene().getWindow();
			 mainWindow.setScene(newScene);	
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Set up the table data
		movieIdCol.setCellValueFactory(new PropertyValueFactory<MovieGrid,Integer>("Id"));
		movieIdCol.setVisible(false);
		movieTitleCol.setCellValueFactory(new PropertyValueFactory<MovieGrid,String>("Title"));
		movieReleaeDateCol.setCellValueFactory(new PropertyValueFactory<MovieGrid,String>("ReleaseDate"));
		userLbl.setText("User: " + UserLibrary.currentUser.Username);
		
		this.itemTbl.setRowFactory(tv -> {
			TableRow<MovieGrid> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    MovieGrid rowData = row.getItem();
                    try {
						navigateToMovie(event, rowData.getId());
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
			});
			return row;
		});
	}
	
	
	private void navigateToMovie(MouseEvent event, int movieId) throws IOException  
	{	 
		 UserLibrary.currentMovieId = movieId;
		 Parent window3 = FXMLLoader.load(getClass().getResource("/application/MovieView.fxml"));
		 Scene newScene = new Scene(window3);
		 Stage mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
		 mainWindow.setScene(newScene);	
    } 
}

package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MovieSearchController implements Initializable {
	
	@FXML AnchorPane AnchorPane;	
	@FXML TextField searchCriteria = new TextField();	
	@FXML TableView<MovieGrid> itemTbl;
    @FXML TableColumn<MovieGrid, Integer> movieIdCol;
    @FXML TableColumn<MovieGrid, String> movieTitleCol;
    @FXML TableColumn<MovieGrid, String> movieReleaeDateCol;

    ObservableList<MovieGrid> data;
	
	@FXML
    private void handleButtonAction(ActionEvent ev) 
    { 
		ObservableList<MovieGrid> movieResult = MovieGrid.SearchMovies(searchCriteria.getText());
	    itemTbl.setItems(movieResult);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Set up the table data
		movieIdCol.setCellValueFactory(new PropertyValueFactory<MovieGrid,Integer>("Id"));
		movieIdCol.setVisible(false);
		movieTitleCol.setCellValueFactory(new PropertyValueFactory<MovieGrid,String>("Title"));
		movieReleaeDateCol.setCellValueFactory(new PropertyValueFactory<MovieGrid,String>("ReleaseDate"));
		
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
	
	private void navigateToMovie(MouseEvent event, int movieId) throws IOException  
	{	 
		 UserLibrary.currentMovieId = movieId;
		 Parent window3 = FXMLLoader.load(getClass().getResource("/application/MovieView.fxml"));
		 Scene newScene = new Scene(window3);
		 Stage mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
		 mainWindow.setScene(newScene);	
    } 
}

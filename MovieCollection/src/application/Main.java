package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import static javafx.application.Application.launch;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static Stage parentWindow;
	public static User currentUser;
	
	@FXML
	TextField username = new TextField();
	
	@Override
    public void start(Stage stage) throws Exception 
	{
		parentWindow = stage;
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
	@FXML
    private void handleButtonAction(ActionEvent ev) throws IOException  
	{	 
		 try 
		 {
			 UserLibrary.currentUser = User.GetUserByUserName(username.getText());
			 Parent window3 = FXMLLoader.load(getClass().getResource("/application/UserView.fxml"));
			 Scene newScene = new Scene(window3);
			 Stage mainWindow = (Stage)  ((Node)ev.getSource()).getScene().getWindow();
			 mainWindow.setScene(newScene);	
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
	
	@FXML
    private void newUserBtnAction(ActionEvent ev) throws IOException  
	{	 
		 Parent window3 = FXMLLoader.load(getClass().getResource("/application/CreateUser.fxml"));
		 Scene newScene = new Scene(window3);
		 Stage mainWindow = (Stage)  ((Node)ev.getSource()).getScene().getWindow();
		 mainWindow.setScene(newScene);	
    }  
}

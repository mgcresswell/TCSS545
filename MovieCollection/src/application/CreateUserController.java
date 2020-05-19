package application;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.Random; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreateUserController implements Initializable {
	
	@FXML
	TextField username = new TextField();
	
	public static Stage parentWindow;
	
	@FXML
    private void handleButtonAction(ActionEvent ev) throws Exception 
    { 
		Stage mainWindow = (Stage)  ((Node)ev.getSource()).getScene().getWindow();
		
		if (!User.DoesUserExist(this.username.getText().toString()))
		{
			if (User.InsertNewUser(this.username.getText().toString()))
			{
				SuccessfulUserCreation(ev);
			}
		}
    }
	
	public void SuccessfulUserCreation(ActionEvent ev) throws Exception
	{
		 Parent window3 = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		 Scene newScene = new Scene(window3);
		 Stage mainWindow = (Stage)  ((Node)ev.getSource()).getScene().getWindow();
		 mainWindow.setScene(newScene);	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

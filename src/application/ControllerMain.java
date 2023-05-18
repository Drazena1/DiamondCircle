package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.MissingFormatArgumentException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mapa.Mapa;
public class ControllerMain implements Initializable {
	
	@FXML
	Button start;
	
	@FXML
	TextField dim;
	
	@FXML
	TextField brojIgraca;
	@FXML
	Label labela1;
	@FXML
	Label labela2;
	

	
	public static String d;
	public static String num;
	public static Logger logger = Logger.getLogger(ControllerMain.class.getName());

	{
		try{
			Main.handler=  new FileHandler(Main.loggerFolder + File.separator + "controllermain.log");
			logger.addHandler(Main.handler);
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			start.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent t) {
					boolean start= true;
					try {
						d=dim.getText();
						
						num=brojIgraca.getText();
						if((Integer.parseInt(d)<7 || Integer.parseInt(d)>10) || Integer.parseInt(num)<2 || Integer.parseInt(num)>4) {
							start=false;
							throw new MissingFormatArgumentException("UNESITE ISPRAVNE VRIJEDNOSTI");
						}
						
					}catch(MissingFormatArgumentException e) {
						 logger.log(Level.SEVERE, " Greska!!");
					}
					
					
					if (start) {
					Main.map= new Mapa();
					try {
						Parent root = FXMLLoader.load(getClass().getResource("/gui/first.fxml"));
					
						Stage stage = new Stage();
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("Podaci");
						stage.setResizable(true);
						stage.show();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					}
				}
			});
		
	}

}

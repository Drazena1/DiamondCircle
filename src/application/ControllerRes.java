package application;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;




public class ControllerRes implements Initializable {
	
		@FXML
	    private VBox vbox;
		@FXML
	    private BorderPane border;
		@FXML
		private ScrollPane scroll;
		public static Logger logger = Logger.getLogger(ControllerRes.class.getName());

		{
			try{
				Main.handler=  new FileHandler(Main.loggerFolder + File.separator + "controllerres.log");
				logger.addHandler(Main.handler);
			}catch(IOException e){
				e.printStackTrace();
				
			}
		}

	    public void initialize(URL arg0, ResourceBundle arg1){
	       File[] files = Main.resultsFolder.listFiles();
	    //	File[] files = new File("C:\\Users\\Korisnik\\Desktop\\java2022\\Java2022_Drazena_Vasiljevic\\results\\").listFiles();
	    	
	    	if(files != null){
	            for(File f : files){
	                try(
	                FileReader fajl = new FileReader(f);
	                  BufferedReader br = new BufferedReader(fajl);
	            		
	            			
	                   )
	                {
	                	
	                   String text = br.lines()
	                                        .collect(Collectors.joining(System.lineSeparator()));
	                    TitledPane pane = new TitledPane();
	                    pane.setExpanded(false);
	                    pane.setText(f.getName());
	                 TextArea textArea = new TextArea(text);
	                    textArea.setEditable(false);
	                    textArea.setWrapText(true);
	                    textArea.setPrefWidth(800);
	                    pane.setContent(textArea);
	                    pane.setPrefWidth(800);
	                    pane.setMinHeight(400);
	                    vbox.getChildren().add(pane);
	                }catch (IOException e){
	                	e.printStackTrace();
	                	
	                   logger.log(Level.SEVERE, " Greska  prilikom citanja fajla!");
	                }
	            }
	            
	        }
	    }

}

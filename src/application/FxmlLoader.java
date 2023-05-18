package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class FxmlLoader {
	public static Stage load(@SuppressWarnings("rawtypes")Class c, String fxml, String naziv) {
		Stage stage = new Stage();
		try {
			stage.setTitle(naziv);
			stage.setResizable(true);
			BorderPane pane = (BorderPane) FXMLLoader.load(c.getResource(fxml));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent> (){
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
		}catch(IOException ex) {
			Logger.getLogger(c.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stage;
	}

}

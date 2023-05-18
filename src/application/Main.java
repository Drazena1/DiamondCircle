package application;

import mapa.Mapa;
import java.io.File;
import java.util.logging.Handler;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static Mapa map;
	public static final File resultsFolder = new File("results");
	public static final File loggerFolder = new File("loggerFolder");
	public static Handler handler;
	@Override
	public void start(Stage primaryStage) {
		FxmlLoader.load(this.getClass(),"/gui/Main.fxml", "DiamondCircle");
	}
	
	public static void main(String[] args) {
		launch(args);
	
		
	}
	
	
}


package application;

import java.net.URL;



import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import mapa.Field;


public class ControllerPath implements Initializable {

	
	@FXML
	Label labela;
	   
		private static final int SIZE = ControllerFirst.SIZE;
		public static int idFigure;
		static Pane mapa[][] = new Pane[SIZE][SIZE];
		  @FXML
		    private GridPane grid;
	
		Stage stage;
		Pane pane = new Pane();
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			for(int i=0;i<SIZE;i++)
				for(int j=0;j<SIZE;j++) {
					mapa[i][j] = new Pane();
					mapa[i][j].setStyle("-fx-border-color: black;-fx-border-width:0.3");
				}

			setGridLines();
			setFiguresMap();
		
		}
		
		public void setGridLines()
		{
			grid.setGridLinesVisible(true);
			final int columnCount = SIZE-1;
			final int rowCount = SIZE-1;
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			cc.setPercentWidth(85d / columnCount);

			for (int i = 0; i < columnCount; i++) {
				grid.getColumnConstraints().add(cc);
			}
			
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			rc.setPercentHeight(85d / rowCount);

			for (int j = 0; j < rowCount; j++) {
				grid.getRowConstraints().add(rc);
			}
			

		}
		
		private void setFiguresMap()
		{
			
			int br=1;
			Label label;
			for(int i=0; i < SIZE; i++) {
				for(int j=0; j < SIZE; j++) {
					label = new Label(String.format("%d", br));
					label.setStyle("-fx-font-size: 1em; -fx-text-fill: green;-fx-font-weight: bold");
					mapa[i][j].getChildren().add(label);
					grid.add(mapa[i][j], j, i);
					br++;
				}
			}
		
		for(Field field:ControllerFirst.fig.get(idFigure).getPathSec())
		{
			
			mapa[field.getX()][field.getY()].setStyle("-fx-background-color:lightblue;-fx-border-color: green;-fx-border-width:0.30");
		}

		
			
			
		}
		
}		
		



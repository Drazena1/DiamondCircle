package application;

import java.io.File;
import java.io.IOException;


import game.Game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mapa.Field;
import mapa.*;
import object.Player;
import cards.*;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import cards.Images;
import figure.Color;
import figure.Fast;
import figure.Figure;
import figure.Flying;
import figure.Ghost;
import figure.Regular;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public  class ControllerFirst implements Initializable
{
 	public static int br;
	private static final int BROJFIGURA=4; 
	public static final int CONST=2; 
	public  static ArrayList<Figure> fig = new ArrayList<>();
	public static final int SIZE = Integer.parseInt(ControllerMain.d);
	static Pane mapa[][] = new Pane[SIZE][SIZE];
	static Pane karta[][] = new Pane[1][1];
	public long currentTime;
	
	
	@FXML
	private BorderPane border;
	@FXML
	public TextField brojIgara;
	@FXML
	public ImageView imageSet;
	@FXML
	public TextField vrijeme;

	
	@FXML
	public Label prvi;
	@FXML
	public Label drugi;
	@FXML
	public Label treci;
	@FXML
	public Label cetvrti;
	
	@FXML
	GridPane grid;
	
	@FXML
	Button start;
	
	@FXML
	Button stop;
	
	@FXML
	Button rezultati;
	
	@FXML
	public TextArea opisKarte;
	
	@FXML
	public Label labelaFirst;
	
	@FXML
	public Label labelaSec;
	
	@FXML
	public Label labelaThree;
	
	@FXML
	public ListView<String> fLista;
	
	public static String[] names;
	TextField textField;
	Label labela;
	
	Stage stage;
	Pane pane = new Pane();
	
	
	 private boolean var = false;
	 Game game;
	 Ghost ghost;
	 
		public static Logger logger = Logger.getLogger(ControllerFirst.class.getName());

		{
			try{
				Main.handler=  new FileHandler(Main.loggerFolder + File.separator + "controllerfirst.log");
				logger.addHandler(Main.handler);
			}catch(IOException e){
				e.printStackTrace();
				
			}
		}

	
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int i=0;i<SIZE;i++)
			for(int j=0;j<SIZE;j++) {
				mapa[i][j] = new Pane();
				mapa[i][j].setStyle("-fx-border-color: green;-fx-border-width:0.2");
			}
	
		
	    setGridLines();		
		prikaziMapu();
		
		
		 final ControllerFirst f = this;
		 game = new Game(f);
		// ghost= new Ghost();
		// ghost= new Ghost(f);
				 start.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent t) {
		        	if(var== false) {
		        		new Thread(game).start();
		        		//new Thread(ghost).start();
		        		var= true;
		        	}
		        	
		        	if(game.pause) {
		        		game.pause=false;
		        		//ghost.pause=false;
		        		synchronized(game) {
                			game.notify();
		        		}
		        	/*	synchronized(ghost) {
                			ghost.notify();
		        		}
		        		*/
		        		
		        	}
		        }
		    });
		 
		 stop.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent t) {
	            	game.pause=true;
	            //	ghost.pause=true;;
	            }
	        });
		
		fLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				
				br=fLista.getSelectionModel().getSelectedIndex();
				ControllerPath.idFigure=br;
							
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/gui/path.fxml"));
				
					Stage stage = new Stage();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Putanja");
					stage.setResizable(true);
					stage.show();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		
	 rezultati.setOnAction(new EventHandler<ActionEvent>() {
			 
			 @Override
			 public void handle(ActionEvent e){
		 
		       try{
		            FXMLLoader fxmlLoader = new FXMLLoader();
		            fxmlLoader.setLocation(getClass().getResource("/gui/res.fxml"));
		            Scene scene = new Scene(fxmlLoader.load(), 500, 700);
		            Stage stage = new Stage();
		            stage.setScene(scene);
		            stage.setTitle("Game results");
		            stage.setResizable(true);
		            stage.show();
		        }catch (IOException ex){
		            logger.log(Level.SEVERE, "Problem loading fxml file", e);
		        }
		    }
		 });
		
		
	
		

	}
	private void setGridLines(){
		
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
	
	
	
	public static void holeColor( int i, int j, Field f)
	{
		if (f.getHole()!= null)
		{
		
			mapa[i][j].setStyle("-fx-background-color:black;-fx-border-color: black;-fx-border-width:0.30");
		}
		else {
		if(i!=0 || j!=0)
			mapa[i][j].setStyle("-fx-background-color: null;-fx-border-color: black;-fx-border-width:0.30");
		}
	}
	
	public static void diamondsColor( int i, int j, Field f)
	{
		if (f.getDiamonds()!= null )
		{
		
			mapa[i][j].setStyle("-fx-background-color:white;-fx-border-color: black;-fx-border-width:0.60");
		}
		else {
		if(i!=0 || j!=0)
			mapa[i][j].setStyle("-fx-background-color: null;-fx-border-color: black;-fx-border-width:0.30");
		}
	}
	

	public void addSpecialCard()
	{
		imageSet.setImage(Images.specialCard);
	}
	
	public void addCard(Card card, int steps) {

		final ControllerFirst f = this;
		
			switch (steps) {
			    case 1: new RegularCard(1,f); break;
	            case 2: new RegularCard(2,f); break;
	            case 3: new RegularCard(3,f); break;
	            case 4: new RegularCard(4,f); break;
			}

	
	}
	
	
	public static void figuresColor(String color, int i, int j, Field f) {
		if(f!=null) {
			Element e = f.getElement();
			if(e!=null) {
				if(e instanceof Regular) {
					Regular figure = (Regular)e;
					color = figure.getColor().toString();
				}
				else if(e instanceof Flying) {
					Flying figure = (Flying)e;
					color = figure.getColor().toString();
				}
				else if(e instanceof Fast) {
					Fast figure = (Fast)e;
					color = figure.getColor().toString();
				}	
			
			}
		}
		if(i!=0 || j!=0)
			mapa[i][j].setStyle("-fx-background-color:" + color + ";-fx-border-color: black;-fx-border-width:0.30");
	}
	public static void setColor() {
	//	map.getPath();
		/*int [] numi= {3,2,3,4,1,2,3,4,5,0,1,2,3,4,5,6,1,2,3,4,5,2,3,4,3};
		int [] numj = {0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6};
	for(int n=0; n<=24;n++)
		if(numi[n]==i)
			if(numj[n]==j)
			mapa[i][j].setStyle("-fx-background-color: gray; -fx-border-color: black; -fx-border-width:0.30");
		*/
		for (Field field : Mapa.getPath())
		{
			mapa[field.getX()][field.getY()].setStyle("-fx-background-color:gray;-fx-border-color: green;-fx-border-width:0.30");
		}
	}
	
	private void prikaziMapu() {
		int br=1;
		
		Label l;
		for(int i=0; i < SIZE; i++) {
			for(int j=0; j < SIZE; j++) {
				l = new Label(String.format("%d", br));
				l.setStyle("-fx-font-size: 1em;-fx-text-fill: green;-fx-font-weight: bold");
				
				mapa[i][j].getChildren().add(l);
				grid.add(mapa[i][j], j, i);
				setColor();
				
				br++;
			}

		}
	
		

     }
	  
	 

	
	public void createList(ArrayList<Player> playerList) {
	
		names = new String[playerList.size()*BROJFIGURA];
		int j=0;
		for(int i=0; i<playerList.size(); i++) {			
			for(int k=0; k<4; k++) {
				fig.add(playerList.get(i).getFigure().get(k));				
				names[j++] = playerList.get(i).getFigure().get(k).getName();
			}
		}
		fLista.getItems().addAll(names);
	}
	 
	

	
	 public void displayN(Player player , Color color, int n) {
		 if (n==0)
		 {
			 
			 prvi.setText(player.getPlayerName());
			 prvi.setStyle("-fx-font-size: 1.2em;-fx-text-fill:" + color +";-fx-font-weight: bold");
		 }
		 if (n==1)
		 {
			 drugi.setText(player.getPlayerName());
			 drugi.setStyle("-fx-font-size: 1.2em;-fx-text-fill:" + color +";-fx-font-weight: bold");
		 }
		 if (n==2)
		 {
			 treci.setText(player.getPlayerName());
			 treci.setStyle("-fx-font-size: 1.2em;-fx-text-fill:" + color +";-fx-font-weight: bold");
		 }
		 if (n==3)
		 {
			cetvrti.setText(player.getPlayerName());
			cetvrti.setStyle("-fx-font-size: 1.2em;-fx-text-fill:" + color +";-fx-font-weight: bold");
		 }
		 
		 
	 }
	  

	  
	}

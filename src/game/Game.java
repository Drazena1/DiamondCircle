package game;


import java.io.File;



import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import object.Player;
import figure.Color;
import figure.Fast;
import figure.Figure;
import figure.Flying;
import figure.Ghost;
import figure.Regular;
import mapa.*;
import cards.Card;
import cards.RegularCard;
import cards.SpecialCard;
import application.ControllerFirst;
import application.ControllerMain;
import application.Main;


public class Game extends Thread

{
	private ControllerFirst controller;
	private static int playernum;
    public boolean pause;
	public static ArrayList<Player> playerList = new ArrayList<Player>();

	public static int brojac=0;
	private static ArrayList<Card> cards = new ArrayList<>();
	private long start;
	private long figureTime;

	private long ukupnoVrijeme;

	long currentTime;

	private static int gameCount;
	private Ghost ghost;

	private  static ArrayList<Figure> figures = new ArrayList<>();

	public static Logger logger = Logger.getLogger(Game.class.getName());
	//public static Handler fileHandler;
	{
		try{
			Main.handler=  new FileHandler(Main.loggerFolder + File.separator + "game.log");
			logger.addHandler(Main.handler);
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	public Game()
	{}
	

	public Game(ControllerFirst controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		Integer numofplayers = Integer.parseInt(ControllerMain.num);
		
		try {
			
			
			File[] files = Main.resultsFolder.listFiles();
	        if(files != null)
	            gameCount = files.length;
	            controller.brojIgara.setText(String.format("%d", gameCount));
			
				if(numofplayers > playernum)
					create(numofplayers);
				Player pl= new Player();
				Color colorfig;
				
				controller.createList(playerList);
				
				for (int m=0; m< playerList.size() ; m++)
				{
					pl= playerList.get(m);
					colorfig=pl.getColor();
					controller.displayN(pl,colorfig,m);
			       
				}
				  ghost = new Ghost(this);
			      ghost.start();
			 
			
			    int j=0;
			  
			    
			   start = System.currentTimeMillis();
			   
		
			   while(brojac<numofplayers) {
		    
				Player player = playerList.get(j);
				
								
				if(!player.isFinish()) {
					Figure figure = player.getFigure().get(player.ind);
					 
					checkPause();
				
				
				
					
				while(figure.isStart()) {
					figureTime= System.currentTimeMillis();
					Field first = Mapa.path.get(0);	
					setXY(first, figure);
					
					isHole(figure.getX(), figure.getY(), figure);
					//provjerimo da li je figura u rupi
					//ako je u rupi figura je zavrsila pa imamo sl if
					if(!figure.isEnd()) {
						if(Main.map.getField(figure.getX(), figure.getY()).getElement() == null) {
							Field f = Main.map.getField(figure.getX(), figure.getY());
							synchronized(f.object) {
								Main.map.getField(figure.getX(), figure.getY()).setElement(figure);
								figure.getPathSec().add(f); //f.getNumber()
								//figura samo jednom ima start polje
								figure.setStart(false);
								figure.getPath().add(f.getNumber());
								try {
									sleep(500);
								}catch(InterruptedException e) {
									
									logger.log(Level.SEVERE, e.fillInStackTrace().toString());
								}
							}
						}
					}
				}
				

				
					
				
				//	newCard(figure);
		          
			Card k = cards.remove(0);
				
				if(k instanceof RegularCard) {
					if (figure instanceof Fast)
					{
					figure.setSteps(((RegularCard)k).getHop()*ControllerFirst.CONST + figure.getDiamonds());
					figure.setDiamonds(0);//konstanta dva						
					}
					else
					{
					figure.setSteps(((RegularCard)k).getHop() + figure.getDiamonds() );
					figure.setDiamonds(0);
					}
					controller.addCard(k,((RegularCard)k).getHop());
					
					
				}
				else if(k instanceof SpecialCard) {
					controller.opisKarte.setText("SPECIJALNA KARTA POSTAVLJA RUPE" + figure.getName());
					((SpecialCard)k).addHoles();
					controller.addSpecialCard();
					
				}
				cards.add(k);
					
				
				//cards.add(k);
				 currentTime = System.currentTimeMillis()-start;
				 controller.vrijeme.setText(String.format("%d", currentTime/1000));
			//na osnovu broja koraka postavljamo figuru na sl polje
				int temp[] = nextStep(player, figure);
                     
			    if(!figure.isEnd()) setText(player,figure,temp);
	
				isHole(temp[0], temp[1], figure); //provjeravamo da li je rupa ispod figure
					 if (figure.isEnd()) controller.opisKarte.setText("" + figure.getName() + " igraca " + player.getPlayerName()+" je zavrsila");
					 
						if(!figure.isEnd()) {	
							
							
								Field f= Main.map.getField(temp[0], temp[1]);
								synchronized(f.object) {
									f.setElement(figure);
									figure.getPathSec().add(f);
									figure.getPath().add(f.getNumber());
									if((f.getDiamonds())!=null) {
										int numDim=0;
										numDim= figure.getDiamonds() +1;
										figure.setDiamonds(numDim);
										figure.setAllDiamonds(figure.getAllDiamonds()+1);
										figure.setSteps(0);
										//int nextSt=0;
										//nextSt=figure.getSteps() + figure.getDiamonds();
										//figure.setSteps(nextSt);
										
										f.setDiamonds(null);//polje
									}
									//noveKord = temp;
								}
					
								
									synchronized(Main.map.getField(figure.getX(), figure.getY()).object) {
										Main.map.getField(figure.getX(), figure.getY()).setElement(null);
										figure.setX(temp[0]);
										figure.setY(temp[1]);
									}
								
							
								
						}
							
					
								 Field endField = Mapa.path.get(Mapa.path.size()-1);
									if((figure.getX()==endField.getX() && figure.getY()==endField.getY())) {
										figure.setEnd(true);
									}
						
					figure.setDuration((System.currentTimeMillis() - figureTime));
     
						
						try {
							sleep(1000);
						}catch(InterruptedException ex) {
							ex.printStackTrace();
						}
     
						removeHoles();
						
						
						
						if(figure.isEnd()) {
							synchronized(Main.map.getField(figure.getX(), figure.getY()).object) {
								Main.map.getField(figure.getX(), figure.getY()).setElement(null);
								if(player.ind<4-1) {
									player.ind+=1;
									player.steps=0;
									
								}
							}
						}
					
				}
					
					j++;
					
					int br=0;
					for(int l=0; l<4; l++) {
						if(player.getFigure().get(l).isEnd())
							br++;
					}
					
					if((br==4) && (player.isFinish()== false)) {
					//if(isFinish(player) && (player.isFinish()==false)) {
						brojac++;
						player.setFinish(true);
					}
					//igrac ima jos figura ili ako je on zavrsio ali ima jos igraca
					if( (br!=4) || (br==4 && brojac<playerList.size())) {
						if(j==playerList.size())
							j=0;
					}
					if(brojac==playerList.size()) {
						ukupnoVrijeme = System.currentTimeMillis()-start;
					
					}
				
			
				} 
			   writeResults();
			   controller.opisKarte.setText("KRAJ IGRE!");
				
				
			
				
	}		
				
			
	catch(MissingFormatArgumentException e) {
			
		logger.log(Level.SEVERE, e.fillInStackTrace().toString());
		}
	}
				
	public void setText(Player player, Figure figure, int [] temp)
	{
		String s = "Na potezu je " + player.getPlayerName() + "boja" + figure.getColor() + ", figura " + figure.getName() + (player.ind+1) + ", prelazi " + figure.getSteps() + " polja, " +
		 	"pomjera se sa pozicije " + Main.map.getField(figure.getX(), figure.getY()).getNumber()  + " na poziciju " + 
			Main.map.getField(temp[0], temp[1]).getNumber() + " Ukupan broj dijamanata:" + figure.getAllDiamonds();
		controller.opisKarte.setText(s);
	}
	
	private void setXY(Field field, Figure figure)
	 {
	  figure.setX(field.getX());
	  figure.setY(field.getY());
	}

	
	private void writeResults()
	{
		String pom="";
		String s="";
		String durationF="";
		LocalDateTime time = LocalDateTime.now();
		//PrintWriter pw = new PrintWriter(new FileWriter(new File("Igre" + File.separator + "IGRA_" + date.getTime()+ ".txt")));
		File result = new File(Main.resultsFolder + File.separator + "IGRA" + time.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_kk_mm_ss")) + ".txt");
		   try(FileWriter writer = new FileWriter(result, true)){
	            for(int i=0; i<playernum; i++)
	            {
	            	Player player =playerList.get(i);
	            	s=player.getPlayerName() + "\n";
	            	writer.write(s+ System.lineSeparator());
	            	ArrayList<Figure> figure = player.getFigure();
	            	for(int j=0; j<4; j++) 
	            	{
	            		Figure f= figure.get(j);
	            		durationF="Vrijeme kretanja ove figure :" + (f.getDuration()/1000);
	            		s= f.getName() +  " (" + f.getColor() + ") " + (j+1) + f.getName() + " - predjeni put: "  + f.getPath().toString() + " stigla do cilja " + (f.isId()? "ne" : "da") + System.lineSeparator() + "Broj dijamanata ove figure: " + f.getAllDiamonds() +System.lineSeparator()+ durationF;
	            		writer.write(s+ System.lineSeparator());}
	            }
	            pom+= "Ukupno vrijeme trajanja igre: " + (ukupnoVrijeme/1000) ;
	            writer.write(" " + pom);
	        }catch (IOException e){
	        
	        	logger.log(Level.SEVERE, e.fillInStackTrace().toString());
	        }
	            
	}
		   

	            
	
public void newCard(Figure figure)
{ 
            
	Card k = cards.remove(0);
	
	if(k instanceof RegularCard) {
		if (figure instanceof Fast)
		{
		figure.setSteps(((RegularCard)k).getHop()*ControllerFirst.CONST + figure.getDiamonds());
		figure.setDiamonds(0);//konstanta dva						
		}
		else
		{
		figure.setSteps(((RegularCard)k).getHop() + figure.getDiamonds() );
		figure.setDiamonds(0);
		}
		controller.addCard(k,((RegularCard)k).getHop());
		
		
	}
	else if(k instanceof SpecialCard) {
		controller.opisKarte.setText("SPECIJALNA KARTA POSTAVLJA RUPE" + figure.getName());
		((SpecialCard)k).addHoles();
		controller.addSpecialCard();
		
	}
	cards.add(k);
					
					
}


public synchronized void checkPause(){
					        if(pause){
					        	synchronized(this) {
					        		try{
					        	
					               wait();
					            }catch (InterruptedException e){
					                
					            	logger.log(Level.INFO, "InterruptedException");
					            }
					        }
					    }
}
	
			
			public void create(Integer broj) {
				
				Color[] colors = colorRand(broj);
			
				for(int i = 0; i < broj; i++){
		           
					Player player= new Player();
		            player.setPlayerName("Igraè" + (i + 1));
		            player.setColor(colors[i]);
		            figures=generateFigure(colors[i]);
		            player.setFigure(figures);
		            playerList.add(player);
		            playernum++;
		            
				}
				generateCards();
			}
		        
		            
			
				
			
				  private ArrayList<Figure> generateFigure(Color color){
				        Random rng = new Random();
				       // Figure[] temp = new Figure[4];
				        ArrayList<Figure> temp = new ArrayList<Figure>();

				        for(int i = 0; i < 4; i++){
				            switch(rng.nextInt(3)){
				                case 0:
				                    temp.add( new Regular("Obicna figura ", color, 0));
				                    break;
				                case 1:
				                    temp.add(new Flying("Lebdeca figura ", color, 0));
				                    break;
				                case 2:
				                    temp.add( new Fast("Super brza figura ", color, 0));
				                    break;
				            }
				        }

				        return temp;
				    }
				  
			   private Color[] colorRand(int playersNumber){
			        Color[] colors = new Color[playersNumber];
			        List<Integer> randomNums =
			        ThreadLocalRandom.current()
			                .ints(0, 4)
			                .distinct()
			                .limit(playersNumber)
			                .boxed()
			                .collect(Collectors.toList());

			        for(int i = 0; i < playersNumber; i++){
			            switch (randomNums.get(i)){
			                case 0: colors[i] = Color.RED; break;
			                case 1: colors[i] = Color.BLUE; break;
			                case 2: colors[i] = Color.YELLOW; break;
			                case 3: colors[i] = Color.GREEN; break;
			            }
			        }

			        return colors;
			        
			        
			     
			    }
			   
			  
			   
			   
	 private void  generateCards(){
	     
	        for(int i = 0; i < 10; i++){
	            cards.add(new RegularCard(1,controller));
	            cards.add(new RegularCard(2, controller));
	            cards.add(new RegularCard(3,controller));
	            cards.add(new RegularCard(4,controller));
	        }

	        for(int i = 0; i < 12; i++)
	            cards.add(new SpecialCard(controller));

	        Collections.shuffle(cards);

	    }
	 
	  

	    
	    private synchronized int [] nextStep (Player player, Figure figure)
		 {
	    	
			 Field field= new Field();
			 int [] koordinate = new int[2];
			 player.steps+=figure.getSteps();
			 if(player.steps<Mapa.path.size())
				 field=Mapa.path.get(player.steps);
			 else
			 {
	
				 figure.setEnd(true);

			 }
			 if(figure.getSteps() >0) {
			 
			 if(field.getElement() != null)  //!= null 
			 {
				 while(field.getElement() != null)
				 {
					 if(player.steps+1 <Mapa.path.size())
					  field=Mapa.path.get(++player.steps);
					 
					 else
					 {
						
						 figure.setEnd(true);
									
					 }
				 }
					 
			}
			 }
			 koordinate[0]=field.getX();
			 koordinate[1]=field.getY();
			 return koordinate;
				 
	    	
		 }
	 
	 private void removeHole(Field f)
	 {
		// Field f= new Field();
		 for(int i=0; i<Mapa.path.size(); i++) {
		 f=Mapa.path.get(i);
		 if (f.getHole()!= null)
		 {
			 f.setHole(null);
			 ControllerFirst.holeColor(f.getX(),f.getY(),f);
		 }
		 }
	 }
	 
	 private synchronized void removeHoles()
	 {
	
		 for(Field f : Mapa.path)
	            removeHole(f);
		 
	 }
	 
	 
	 private void isHole(int x, int y, Figure figure) {
		
				Field field = Main.map.getField(x, y);
				if(field.getHole()!=null && !(figure instanceof Flying)) {
					synchronized(field.object) {
						field.setElement(figure);
					}
					
					figure.getPath().add(field.getNumber());
					try {
						sleep(500);
					}catch(InterruptedException ex) {
					
					}
					synchronized(field.object) {
						field.setElement(null);
					}
					figure.setEnd(true);
					figure.setId(true);
				}
		
	 
	 
		
	 } 	 
	 
	 
	 
}

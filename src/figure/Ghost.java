package figure;

import java.util.Random;

import mapa.*;
import object.Diamond;
import application.ControllerFirst;
import application.Main;
import game.Game;

public class Ghost extends Thread {

	protected int x;
	protected int y;
	protected boolean end = false;
	private int diamonds;
	public boolean pause;
	Game game= new Game();
	
	public Ghost() {
		super();
	}
	public Ghost(Game game)
	{
		super(game);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}
	
	@Override
	public void run()
	{
		game.checkPause();
		
		
	Random rng = new Random();
	diamonds = rng.nextInt(ControllerFirst.SIZE - 1) + 2;
	Field f= new Field();
	while(true) {
     
			for(int i = 0; i < diamonds; i++){
                int index = rng.nextInt(Mapa.path.size());
            	f = Mapa.path.get(index);
        		x = f.getX();
        		y = f.getY();
        		Field n = Main.map.getField(x, y);
        		if((n.getDiamonds()==null) && n.getElement()== null)
        			//provjeri radi li
        		{
    				n.setDiamonds(new Diamond());
    				ControllerFirst.diamondsColor( n.getX(), n.getY(), n);
    				
        		}
        		
        		
        		
			}
        		try
        		{
        			sleep(5000);
        			
        		}
        		
	
			
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
        		if(Thread.interrupted())
                    return;
        		
        		if(Game.brojac == 4)
    				break;
        			
	}	
	
	}
	

	
	  public synchronized void setPause(boolean pause) {
	        this.pause = pause;
	        notify();
	    }

	}


package mapa;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import application.ControllerFirst;
import javafx.application.Platform;
import object.Diamond;
import object.Hole;

public class Field {
	

	protected int x, y;
	transient public Object object = new Object();
	private ArrayList<Element> elements = new ArrayList<>();
	protected Element element;
	
	protected Diamond diamonds;
	protected Hole hole;
	protected int num;
	
	public Field() {
		
	}
	
	public Field(int x, int y) {
		this.x = x;
		this.y = y;
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
	
	public synchronized void setElementi(Element e) {
		elements.add(e);
	}
	
	public synchronized Element getElement() {
		return element;
	}
	
	public boolean isEmpty() {
		return element==null;
	}
	
	public void setDiamonds(Diamond diamonds) {
		this.diamonds = diamonds;
	}
	
	public Diamond getDiamonds() {
		return diamonds;
	}
	
	public void setHole(Hole hole) {
		this.hole = hole;
	}
	
	public Hole getHole() {
		return hole;
	}
	
	 public synchronized boolean isHole(){ return hole != null; }
	
	public synchronized void setElement(Element e) {
		this.element = e;
		if(element == null && !elements.isEmpty()) {
			synchronized(elements.get(0)) {
				elements.get(0).notify();
				elements.remove(0);
			}
		}
		final Field f = this;
		new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                   ControllerFirst.figuresColor(null, x, y,f); //importovati gui controller
                    }
                });
            }
        }, 0);
	}

	public int getNumber() {
		return num;
	}

	public void setNumber(int num) {
		this.num = num;
	}

}

package figure;
import java.util.ArrayList;

import game.Game;
import mapa.Element;
import mapa.Field;
//import java.util.ArrayList;



public abstract class Figure extends Element{
	
	protected ArrayList<Field> pathSec = new ArrayList<>();
	
	protected int x, y;
	protected boolean end=false;
	protected boolean start = true;
	protected String name;
	protected long duration;
	protected boolean id = false;
	protected int diamonds=0;
	protected int allDim=0;
	 protected Game game;
	protected Color color;
	protected int steps;
	
	protected ArrayList<Integer> path = new ArrayList<>();

	
	public boolean isFigure() {return true;}
	
	public boolean isRegular() {return false;}
	public boolean isFlying() {return false;}
	public boolean isFast() {return false;}
	
	public Figure() {
		
	}
	
	public Figure(String name, Color color, int steps) {
		super();
		this.name = name;
		this.color = color;
		this.steps = steps;
		
	}
	public Figure (Game game)
	{
		this.game = game;
	}
	
	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}
	
	public int getAllDiamonds() {
		return allDim;
	}

	public void setAllDiamonds(int allDim) {
		this.allDim=allDim;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration= duration;
	}
	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
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
	
	public boolean isEnd() {
		return end;
	}
	
	public ArrayList<Field> getPathSec() {
		return pathSec;
	}

	public void setPathSec(ArrayList<Field> pathSec) {
		this.pathSec = pathSec;
	}
	
	public void setEnd(boolean end) {
		this.end = end;
	}
	
	public boolean isStart() {
		return start;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> getPath() {
		return path;
	}

	public void setPath(ArrayList<Integer> path) {
		this.path = path;
	}
	

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}




}

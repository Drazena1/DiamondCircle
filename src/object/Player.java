package object;


import java.util.ArrayList;


import figure.*;


public class Player {

	

	protected String playerName;
//	private Figure[] figure = new Figure[4];
	private ArrayList<Figure> figure = new ArrayList<>();
	protected boolean finish= false;
	protected Color color;
	public int steps=0;
	
	public int ind=0;
	
	public Player() {
		
	}
	
	public Player(String playerName, ArrayList<Figure> figure) {
		super();
		this.playerName = playerName;
		this.figure = figure;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/*
	public void setFigure(Figure [] figure) {
		this.figure = figure;
	}
	
	public Figure [] getFigure() {
		return figure;
	}*/
	
	public ArrayList<Figure> getFigure() {
		return figure;
	}
	
	public void setFigure(ArrayList<Figure> figure) {
		this.figure = figure;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isFinish() {
		return finish;
	}
	
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	
}

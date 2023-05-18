package object;

public class Diamond {
private int value;
	
	private int x;
	private int y;
	public Diamond()
	{};
	public Diamond(int x, int y)
	{
		this.setX(x);
		this.setY(y);
		value=1;
	}
	
	   public int getValue() {
	        return value;
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

}

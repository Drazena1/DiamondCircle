package mapa;

public class Element implements XY {

	protected int x, y;
	
}

interface XY{
	default public void setX(int x) {
		
	}
	
	default public int getX() {
		return -1;
	}
	
	default public int getY() {
		return -1;
	}
	
	default public void setY(int y) {
		
	}

}

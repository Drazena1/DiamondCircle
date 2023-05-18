package figure;



public class Flying extends Figure {
	public Flying(String name, Color color, int steps) {
		super(name, color, steps);
	}
	
	@Override
	public String toString() {
		return " (" + color + ", " + "Lebdeca figura " + ")";
	}
	
	public boolean isFlying(){
        return true;
    }

}

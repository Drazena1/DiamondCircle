package figure;

public class Fast extends Figure {
	public Fast(String name, Color color, int steps) {
		super(name, color, steps);
	}
	
	@Override
	public String toString() {
		return " (" + color + ", " + "Super brza figura " + ")";
	}
	
	public boolean isFast(){
        return true;
    }


}

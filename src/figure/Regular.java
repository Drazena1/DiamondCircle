package figure;

public class Regular extends Figure {

	public Regular(String name, Color color, int steps) {
		super(name, color, steps);
	}
	
	@Override
	public String toString() {
		return " (" + color + ", " + "Obicna figura " + ")";
	}
	
	public boolean isRegular(){
        return true;
    }

}

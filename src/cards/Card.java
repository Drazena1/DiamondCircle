package cards;

import javafx.scene.image.ImageView;


public class Card extends ImageView {
	    public Card(){
	        setPreserveRatio(true);
	        setCache(true);
	        setSmooth(true);
	        setFitWidth(290);
	    }
	    
	  
	    public boolean isSpecialCard(){
	        return false;
	    }

	}

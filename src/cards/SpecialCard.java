package cards;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import application.ControllerFirst;
import application.Main;
import figure.Fast;
import figure.*;
import figure.Regular;
import mapa.Field;
import mapa.Mapa;
import object.Hole;
public class SpecialCard extends Card {
	
	ControllerFirst gui;
    public SpecialCard(ControllerFirst gui){
    	this.gui=gui;
        gui.imageSet.setImage(Images.specialCard);
    }

    public void addHoles (){
    	Random rnd = new Random();
		int num = rnd.nextInt(4)+1;
		
		List <Integer> indexes = ThreadLocalRandom.current()
                .ints(0, Mapa.path.size())
                .distinct()
                .limit(num)
                .boxed()
                .collect(Collectors.toList());
		
		for(Integer i : indexes)
		{
			Field f= Mapa.path.get(i);
			if(f.getHole() == null && (i!=0)) {
				f.setHole(new Hole());
				ControllerFirst.holeColor( f.getX(), f.getY(), f);
				
				
				Figure figura;
				if(Main.map.getField(f.getX(), f.getY()).getElement()!=null) {
					Field temp = Main.map.getField(f.getX(), f.getY());
					if(temp.getElement() instanceof Regular )
						figura = (Regular)temp.getElement();
					else if(temp.getElement() instanceof Fast)
						figura = (Fast)temp.getElement();
					else
						figura= (Flying)temp.getElement();
					
					if((figura instanceof Flying)) {
						ControllerFirst.figuresColor(null, f.getX(), f.getY(), f);
						//f.setHole(null);
						}
					
					if(!(figura instanceof Flying)) {
					synchronized(temp.object) {
						temp.setElement(null);
						figura.setEnd(true);
						figura.setId(true);
						try {
							Thread.sleep(500);
						}catch(InterruptedException ex) {
							ex.printStackTrace();
						}
						f.setHole(null);
						ControllerFirst.figuresColor(null, f.getX(), f.getY(), f);			
				
				
			             }
		
          
    }
				}
			}
			
		}
    }
		

    public boolean isSpecialCard(){
        return true;
    }

}

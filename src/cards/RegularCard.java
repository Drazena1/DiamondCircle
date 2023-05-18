package cards;

import application.ControllerFirst;


public class RegularCard extends Card {
	
	private int hops;
	ControllerFirst gui;

    public RegularCard(int hops, ControllerFirst gui){
        this.hops = hops;
        this.gui=gui;
        switch (hops){
            case 1: gui.imageSet.setImage(Images.one); break;
            case 2: gui.imageSet.setImage(Images.two); break;
            case 3: gui.imageSet.setImage(Images.three); break;
            case 4: gui.imageSet.setImage(Images.four); break;
        }
    }

 
    
    public int getHop() {
		return hops;
	}

	public void setHop(int hops) {
		this.hops = hops;
	}

}

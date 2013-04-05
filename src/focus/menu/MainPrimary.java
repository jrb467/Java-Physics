package focus.menu;

import java.awt.Component;

import focus.LightFocusHandler;

import menu.Button;
import menu.Label;
import menu.MenuSet;

public class MainPrimary extends MenuSet {

	public MainPrimary(LightFocusHandler handler, Component source) {
		super(7, 0, handler, source);
		foci[0] = new Label(-50, -15, 500, 100, this, source, "Bloxx");
		foci[1] = new Button(-50, -30, 500, 100, 0, this, source, "Local");
		foci[2] = new Button(-50, -45, 500, 100, 1, this, source, "Online");
		foci[3] = new Button(-35, -60, 200, 100, 2, this, source, "Profile");
		foci[4] = new Button(-65, -60, 200, 100, 3, this, source, "Options");
		foci[5] = new Button(-35, -75, 200, 100, 4, this, source, "About");
		foci[6] = new Button(-65, -75, 200, 100, 5, this, source, "Quit");
	}
	
	public void handleInfo(int i, int id){
		handler.handleInfo(id, this.id);	
	}
}

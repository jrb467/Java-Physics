package focus.pausemenu;

import java.awt.Component;

import focus.LightFocusHandler;
import menu.Button;
import menu.MenuSet;

public class PausePrimary extends MenuSet {

	public PausePrimary(LightFocusHandler handler, Component source) {
		super(3, 0, handler, source);
		foci[0] = new Button(-50, -25, 500, 100, 0, this, source, "Resume");
		foci[1] = new Button(-50, -50, 500, 100, 1, this, source, "Settings");
		foci[2] = new Button(-50, -75, 500, 100, 2, this, source, "Quit");
	}
	
	public void handleInfo(int i, int id){
		handler.handleInfo(id, this.id);
	}
}

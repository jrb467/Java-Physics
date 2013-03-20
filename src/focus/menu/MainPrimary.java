package focus.menu;

import menu.Button;
import menu.Label;
import menu.MenuSet;

public class MainPrimary extends MenuSet {

	public MainPrimary() {
		super(6);
		foci[0] = new Label(-50, -15, 500, 100, "Bloxx");
		foci[1] = new Button(-50, -30, 500, 100, "Local");
		foci[2] = new Button(-50, -45, 500, 100, "Online");
		foci[3] = new Button(-50, -60, 500, 100, "Profile");
		foci[4] = new Button(-35, -75, 200, 100, "Options");
		foci[5] = new Button(-65, -75, 200, 100, "About");
	}

}

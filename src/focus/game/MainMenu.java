package focus.game;

import menu.MenuSet;
import focus.SubFocus;


public class MainMenu extends SubFocus {

	public MainMenu(){
		super(true, true);
		foci = new MenuSet[5];
		priority = new int[5];
		for(int i = 0; i < 5; i++){
			priority[i] = i;
		}
	}

}

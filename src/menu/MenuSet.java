package menu;

import focus.SubFocus;

public class MenuSet extends SubFocus {
	
	public MenuSet(int length){
		super(true, true);
		foci = new MenuItem[length];
		priority= new int[length];
		for(int i = 0; i < length; i++){
			priority[i] = i;
		}
	}
	
	@Override
	public void closing() {
		// TODO Auto-generated method stub

	}

}

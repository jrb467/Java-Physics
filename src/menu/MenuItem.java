package menu;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import focus.Focus;

public abstract class MenuItem extends Focus {
	public Rectangle r = new Rectangle(0,0,0,0);
	public boolean hover = false;
	
	public MenuItem() {
		super(false, false);
	}
	
	public void update(Point mouse){
		if(r.contains(mouse)){
			onHover(mouse);
		}else{
			offHover(mouse);
		}
	}
	
	public void onHover(Point mouse){
		if(!hover){
			mouseEntered(new MouseEvent(null, 0, 0, 0, mouse.x, mouse.y, 0, false));
			hover = true;
		}
	}
	
	public void offHover(Point mouse){
		if(hover){
			mouseExited(new MouseEvent(null, 0, 0, 0, mouse.x, mouse.y, 0, false));
			hover = false;
		}
	}
}

package menu;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import focus.Focus;
import focus.LightFocusHandler;

public abstract class MenuItem extends Focus {
	public Rectangle r = new Rectangle(0,0,0,0);
	public boolean hover = false;
	public float horizAlign = -1;
	public float vertAlign = -1;
	
	public MenuItem(int id, LightFocusHandler handler, Component source) {
		super(false, false, id, handler, source);
	}
	
	public MenuItem(int x, int y, int width, int height, int id, LightFocusHandler handler, Component source) {
		super(false, false, id, handler, source);
		r = new Rectangle(x,y,width,height);
		if(x < 0){
			horizAlign = (float)-x/100;
		}
		if(y < 0){
			vertAlign = (float)-y/100;
		}
	}
	
	public Rectangle getRect(int width, int height){
		if(r.x < 0){
			if(r.y < 0){
				return new Rectangle((int)(width*horizAlign-r.width/2), (int)(height*vertAlign-r.height/2), r.width, r.height);
			}else{
				return new Rectangle((int)(width*horizAlign-r.width/2), r.y, r.width, r.height);
			}
		}else{
			if(r.y < 0){
				return new Rectangle(r.x, (int)(height*vertAlign-r.height/2), r.width, r.height);
			}else{
				return new Rectangle(r.x, r.y, r.width, r.height);
			}
		}
	}
	
	public void update(Point mouse){
		if(mouse == null){
			offHover(mouse);
			return;
		}
		if(getRect(source.getWidth(), source.getHeight()).contains(mouse)){
			onHover(mouse);
		}else{
			offHover(mouse);
		}
	}
	
	public void onHover(Point mouse){
		if(!hover){
			mouseEntered(new MouseEvent(source, 0, 0, 0, mouse.x, mouse.y, 0, false));
			hover = true;
		}
	}
	
	public void offHover(Point mouse){
		if(mouse == null){
			mouseExited(new MouseEvent(source, 0, 0, 0, -1, -1, 0, false));
			return;
		}
		if(hover){
			mouseExited(new MouseEvent(source, 0, 0, 0, mouse.x, mouse.y, 0, false));
			hover = false;
		}
	}
}

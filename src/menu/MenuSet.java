package menu;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import focus.LightFocusHandler;
import focus.SubFocus;

public abstract class MenuSet extends SubFocus {
	
	public MenuSet(int length, int id, LightFocusHandler handler, Component source){
		super(true, true, id, handler, source);
		foci = new MenuItem[length];
		priority= new int[length];
		for(int i = 0; i < length; i++){
			priority[i] = i;
		}
	}
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			setFocus(0, (priority[0]+1)%priority.length);
		}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			setFocus(0, (priority[0]-1+priority.length)%priority.length);
		}else{
			foci[priority[0]].keyPressed(arg0);
		}
	}
	public void keyReleased(KeyEvent arg0) {
		foci[priority[0]].keyReleased(arg0);
	}
	public void keyTyped(KeyEvent arg0) {
		foci[priority[0]].keyTyped(arg0);
	}
	public void mouseClicked(MouseEvent arg0) {
		for(MenuItem i: (MenuItem[])foci){
			if(i.getRect(source.getWidth(), source.getHeight()).contains(arg0.getPoint())){
				i.mouseClicked(arg0);
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {
		for(MenuItem i: (MenuItem[])foci){
			if(i.getRect(source.getWidth(), source.getHeight()).contains(arg0.getPoint())){
				i.mousePressed(arg0);
			}
		}
	}
	public void mouseReleased(MouseEvent arg0) {
		for(MenuItem i: (MenuItem[])foci){
			if(i.getRect(source.getWidth(), source.getHeight()).contains(arg0.getPoint())){
				i.mouseReleased(arg0);
			}
		}
	}
}

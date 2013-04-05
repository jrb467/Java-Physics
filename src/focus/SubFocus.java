package focus;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract class SubFocus extends Focus implements LightFocusHandler{
	public Focus[] foci;
	public int[] priority;

	public SubFocus(boolean haltUpdates, boolean haltRendering, int id, LightFocusHandler handler, Component source) {
		super(haltUpdates, haltRendering, id, handler, source);
	}
	
	public void update(Point mouse){
		for(int i = 0; i < priority.length; i++){
			foci[priority[i]].update(mouse);
			if(foci[priority[i]].HALT_BACKGROUND_UPDATES){
				break;
			}
		}
	}
	
	public void setFocus(int index, int value){
		int temp = priority[index];
		int temp2;
		priority[index] = value;
		for(int i = index+1; i < priority.length; i++){
			if(priority[i] == value){
				temp2 = priority[i];
				priority[i] = temp;
				temp = temp2;
				break;
			}else{
				temp2 = priority[i];
				priority[i] = temp;
				temp = temp2;
			}
		}
		if(index == 0){
			foci[priority[0]].gainedFocus();
			foci[priority[1]].lostFocus();
		}
	}
	
	public void render(Graphics2D g){
		int depth = -1;
		for(int i = 0; i < priority.length; i++){
			depth++;
			if(foci[priority[i]].HALT_BACKGROUND_RENDER){
				break;
			}
		}
		for(int i = depth; i >= 0; i--){
			foci[priority[i]].render(g);
		}
	}
	
	public void handleInfo(int i, int id){}
	
	public void closing(){
		for(Focus f: foci){
			f.closing();
		}
	}
	
	public void keyPressed(KeyEvent arg0) {
		foci[priority[0]].keyPressed(arg0);
	}
	public void keyReleased(KeyEvent arg0) {
		foci[priority[0]].keyReleased(arg0);
		
	}
	public void keyTyped(KeyEvent arg0) {
		foci[priority[0]].keyTyped(arg0);
	}
	public void mouseClicked(MouseEvent arg0) {
		foci[priority[0]].mouseClicked(arg0);
	}
	public void mouseEntered(MouseEvent arg0) {
		foci[priority[0]].mouseEntered(arg0);
	}
	public void mouseExited(MouseEvent arg0) {
		foci[priority[0]].mouseExited(arg0);
	}
	public void mousePressed(MouseEvent arg0) {
		foci[priority[0]].mousePressed(arg0);
	}
	public void mouseReleased(MouseEvent arg0) {
		foci[priority[0]].mouseReleased(arg0);
	}

}

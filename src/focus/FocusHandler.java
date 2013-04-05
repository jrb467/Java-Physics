package focus;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract class FocusHandler implements LightFocusHandler{
	public Focus[] foci;
	public int[] priority;
	
	public void update(Point mouse){
		for(int i = 0; i < priority.length; i++){
			foci[priority[i]].update(mouse);
			if(foci[priority[i]].HALT_BACKGROUND_UPDATES == true){
				break;
			}
		}
	}
	
	public void render(Graphics2D g){
		int depth = 1;
		for(int i = 0; i < priority.length; i++){
			if(foci[priority[i]].HALT_BACKGROUND_RENDER == true){
				break;
			}
			depth++;
		}
		for(int i = depth-1; i >= 0; i--){
			foci[priority[i]].render(g);
		}
	}
	
	public void setFocus(int value, int index){
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
	
	public void handleInfo(int i, int id){}
	
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

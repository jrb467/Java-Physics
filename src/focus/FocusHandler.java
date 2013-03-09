package focus;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public abstract class FocusHandler implements MouseListener, KeyListener{
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
	
	public void render(Graphics2D g, int width, int height){
		int depth = 1;
		for(int i = 0; i < priority.length; i++){
			if(foci[priority[i]].HALT_BACKGROUND_RENDER == true){
				break;
			}
			depth++;
		}
		for(int i = depth-1; i >= 0; i--){
			foci[priority[i]].render(g, width, height);
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

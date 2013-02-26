package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import focus.*;


public class GameManager implements WindowListener, MouseListener, KeyListener{
	private GameFrame parent;
	private Focus[] foci;
	private int[] priority;
	
	public GameManager(GameFrame frame){
		parent = frame;
		frame.addWindowListener(this);
		foci = new Focus[3];
		priority = new int[3];
		foci[0] = new MainMenu();
		foci[1] = new Game();
		foci[2] = new PauseMenu();
		priority[0] = 1;
		priority[1] = 0;
		priority[2] = 2;
	}
	
	public void manageGameLogic(){
		for(int i = 0; i < priority.length; i++){
			foci[priority[i]].update();
			if(foci[priority[i]].HALT_BACKGROUND_UPDATES == true){
				break;
			}
		}
	}
	
	public void renderGame(Graphics2D g, int width, int height){
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
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		for(Focus i: foci){
			i.closing();
		}
		parent.destroy();
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

	
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			parent.destroy();
			return;
		}
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

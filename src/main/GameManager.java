package main;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import focus.*;
import focus.game.*;


public class GameManager extends FocusHandler implements WindowListener{
	private GameFrame parent;
	
	public GameManager(GameFrame frame){
		parent = frame;
		frame.addWindowListener(this);
		foci = new Focus[3];
		priority = new int[3];
		foci[0] = new MainMenu(this, parent);
		foci[1] = new Game(this, parent);
		foci[2] = new PauseMenu(this, parent);
		priority[0] = 0;
		priority[1] = 1;
		priority[2] = 2;
	}
	
	public void handleInfo(int i, int id){
		switch(id){
		case 0: //menu
			switch(i){
			case 0: //local game
				setFocus(1, 0);
				break;
			case 1: //quit
				for(Focus c: foci){
					c.closing();
				}
				parent.destroy();
				break;
			}
			break;
		case 1: //game
			//TODO add switch(i) if needed
			setFocus(2, 0);
			break;
		case 2: //pausemenu
			setFocus(i, 0); //i corresponds to either the game or main menu
			break;
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
			for(Focus i: foci){
				i.closing();
			}
			parent.destroy();
			return;
		}
		super.keyPressed(arg0);
	}
}

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import focus.*;
import focus.game.*;


public class GameManager extends FocusHandler implements WindowListener, MouseListener, KeyListener{
	private GameFrame parent;
	
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
		super.keyPressed(arg0);
	}
}

package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Focus implements MouseListener, KeyListener{
	public final boolean HALT_BACKGROUND_UPDATES;
	public final boolean HALT_BACKGROUND_RENDER;
	protected boolean hasFocus = false;
	
	public Focus(boolean haltUpdates, boolean haltRendering){
		HALT_BACKGROUND_UPDATES = haltUpdates;
		HALT_BACKGROUND_RENDER = haltRendering;
	}
	
	public void gainedFocus(){
		hasFocus = true;
	}
	
	public void lostFocus(){
		hasFocus = false;
	}
	
	public abstract void closing();
	
	public abstract void update();
	
	public abstract void render(Graphics2D g, int width, int height);
	
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}

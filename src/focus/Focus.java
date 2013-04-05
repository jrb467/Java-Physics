package focus;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Focus implements LightFocus{
	public final boolean HALT_BACKGROUND_UPDATES;
	public final boolean HALT_BACKGROUND_RENDER;
	public LightFocusHandler handler;
	public Component source;
	protected boolean hasFocus = false;
	public int action;
	public int id;
	
	public Focus(boolean haltUpdates, boolean haltRendering, int id, LightFocusHandler handler, Component source){
		HALT_BACKGROUND_UPDATES = haltUpdates;
		HALT_BACKGROUND_RENDER = haltRendering;
		this.id = id;
		this.handler = handler;
		this.source = source;
	}
	
	public void handleCommand(int c){}
	
	public void gainedFocus(){
		hasFocus = true;
	}
	
	public void lostFocus(){
		hasFocus = false;
	}
	
	public abstract void closing();
	
	public abstract void update(Point mouse);
	
	public abstract void render(Graphics2D g);
	
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}

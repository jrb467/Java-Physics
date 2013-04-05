package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import focus.LightFocusHandler;
import focus.game.MainMenu;


public class Button extends MenuItem {
	private static final Color lblue = new Color(150,150,200);
	public String label;
	private boolean hover = false;
	private boolean down = false;
	//Color, style, etc?
	
	public Button(int x, int y, int width, int height, int id, LightFocusHandler handler, Component source, String label){
		super(x,y,width,height,id, handler, source);
		this.label = label;
	}
	
	public void closing() {}

	@Override
	public void render(Graphics2D g) {
		Rectangle r2 = getRect(source.getWidth(), source.getHeight());
		if(hover){
			if(down){
				g.setColor(Color.darkGray);
			}else{
				g.setColor(Color.gray);
			}
		}else{
			if(hasFocus){
				g.setColor(lblue);
			}else{
				g.setColor(Color.lightGray);
			}
		}
		g.fillRect(r2.x,r2.y,r.width,r.height);
		if(hasFocus){
			g.setColor(Color.blue);
			g.drawRect(r2.x, r2.y, r.width, r.height);
			g.drawRect(r2.x-1, r2.y-1, r.width+2, r.height+2);
		}else{
			g.setColor(Color.black);
			g.drawRect(r2.x, r2.y, r.width, r.height);
		}
		g.setColor(Color.black);
		g.setFont(MainMenu.homespun);
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D b = metrics.getStringBounds(label, g);
		g.drawString(label, (int)(r2.width-b.getWidth())/2+r2.x, (int)((r2.height-b.getHeight())/2+r2.y+b.getHeight()));
	}
	
	public void mousePressed(MouseEvent e){
		down = true;
	}
	
	public void mouseReleased(MouseEvent e){
		if(down == true){
			handler.handleInfo(0, id);
		}
		down = false;
	}
	
	public void mouseExited(MouseEvent e){
		hover = false;
		down = false;
	}
	
	public void mouseEntered(MouseEvent e){
		hover = true;
	}
	
	public void keyTyped(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			handler.handleInfo(0, id);
		}
	}
}

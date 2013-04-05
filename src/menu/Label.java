package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import focus.LightFocusHandler;
import focus.game.MainMenu;

public class Label extends MenuItem {
	public String label;
	
	public Label(int x, int y, int width, int height, LightFocusHandler handler, Component source, String label){
		super(x,y,width,height,-1,handler, source);
		this.label = label;
	}

	public void closing(){}
	
	@Override
	public void render(Graphics2D g) {
		Rectangle r2 = getRect(source.getWidth(), source.getHeight());
		g.setColor(Color.black);
		g.setFont(MainMenu.homespun);
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D b = metrics.getStringBounds(label, g);
		g.drawString(label, (int)(r.width-b.getWidth())/2+r2.x, (int)(r.height-b.getHeight())/2+r2.y);
	}

}

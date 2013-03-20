package menu;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Label extends MenuItem {
	public String label;
	
	public Label(int x, int y, int width, int height, String label){
		super(x,y,width,height);
		this.label = label;
	}

	public void closing(){}
	
	@Override
	public void render(Graphics2D g, int width, int height) {
		Rectangle r2 = getRect(width, height);
		g.setColor(Color.black);
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D b = metrics.getStringBounds(label, g);
		g.drawString(label, (int)(r.width-b.getWidth())/2+r2.x, (int)(r.height-b.getHeight())/2+r2.y);
	}

}

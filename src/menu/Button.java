package menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class Button extends MenuItem {
	public String label;
	private boolean hover = false;
	private boolean down = false;
	//Color, style, etc?
	
	public Button(int x, int y, int width, int height, String label){
		super(x,y,width,height);
		this.label = label;
	}
	
	public void closing() {}

	public void update(Point mouse) {}

	@Override
	public void render(Graphics2D g, int width, int height) {
		if(hover){
			if(down){
				g.setColor(Color.darkGray);
			}else{
				g.setColor(Color.gray);
			}
		}else{
			g.setColor(Color.lightGray);
		}
		g.fillRect(r.x, r.y, r.width, r.height);
		g.setColor(Color.black);
		g.drawRect(r.x, r.y, r.width, r.height);
	}
	
	public void mousePressed(MouseEvent e){
		down = true;
	}
	
	public void mouseReleased(MouseEvent e){
		down = false;
	}
	
	public void mouseExited(MouseEvent e){
		hover = false;
		down = false;
	}
	
	public void mouseEntered(MouseEvent e){
		hover = true;
	}

}

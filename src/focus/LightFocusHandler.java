package focus;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface LightFocusHandler extends MouseListener, KeyListener{
	
	public void update(Point mouse);
	public void render(Graphics2D g);
	public void handleInfo(int i, int id);
}

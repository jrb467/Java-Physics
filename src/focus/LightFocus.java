package focus;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface LightFocus extends MouseListener, KeyListener{

	public void gainedFocus();
	public void lostFocus();
	public void closing();
	public void update(Point mouse);
	public void render(Graphics2D g);
	public void handleCommand(int c);
}

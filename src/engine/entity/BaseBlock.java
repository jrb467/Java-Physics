package engine.entity;

import java.awt.Point;
import java.awt.geom.Point2D.Float;

import engine.event.Collision;

public class BaseBlock extends Entity {
	private static final long serialVersionUID = 1L;
	private float health = 50000;
	private float resist = 20;
	
	public BaseBlock(float friction, int id){
		super(friction, new Float[4], true, id);
		verticies[0] = new Float(0,0);
		verticies[1] = new Float(0, 80);
		verticies[2] = new Float(80,80);
		verticies[3] = new Float(80, 0);
		calculatePointSets(new Point[0]);
		calculateBounds();
	}
	
	@Override
	public void handleCollision(Collision c){
		if(c.speed > resist){
			health -= c.speed-resist;
		}
	}
	
	@Override
	public Object[] getRenderInfo(){
		Object[] temp = new Object[3];
		temp[0] = absolutePoints();
		temp[1] = id;
		temp[2] = health;
		return temp;
	}
	
	public void tick(){
		super.tick();
		if(health <= 0){
			destroy = true;
		}
	}
}

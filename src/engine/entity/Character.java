package engine.entity;

import engine.event.Collision;

import java.awt.Point;
import java.awt.geom.Point2D.Float;

public class Character extends Entity {
	//dE6gx7XP4vK2
	
	private static final long serialVersionUID = 1L;
	public boolean wantsJump;

	public Character(){
		super(1, new Float[12], false, 7);
		//12                                                                                            
		createVerticies(15,0, 30,0, 30,20, 70,20, 70,40, 30,40, 30,80, 15,80, 15,40, 0,40, 0,20, 15,20);
		Point[] divides = createDivides(2,11,5,8);
		calculatePointSets(divides);
		calculateBounds();
	}
	
	public void tick(){
		super.tick();
		wantsJump = false;
	}
	
	@Override
	public void handleCollision(Collision c) {
		if(wantsJump){
			velocity.y = -40;
			wantsJump = false;
		}
	}

	@Override
	public Object[] getRenderInfo() {
		Object[] temp = new Object[2];
		temp[0] = absolutePoints();
		temp[1] = id;
		return temp;
	}

}

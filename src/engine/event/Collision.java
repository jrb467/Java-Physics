package engine.event;

import engine.entity.Entity;

public class Collision {
	public final Entity one;
	public final Entity two;
	public final float speed;
	
	public Collision(Entity one, Entity two, float speed){
		this.one = one;
		this.two = two;
		this.speed = speed;
	}
	//TODO temp class for setting up Game. It'll pass through the entities colliding and their "id"s
}

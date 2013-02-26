package engine;

import java.util.ArrayList;

import engine.math.CollisionTest;

import engine.event.Collision;
import engine.event.CollisionHandler;
import engine.entity.Entity;

public class Engine {
	private CollisionHandler handler;
	
	//TODO 
	//  1) add in subentities (for convex)
	//  2) create a complex character
	
	public Engine(){
		//TODO have it either generate a world or load it
	}
	
	public void setCollisionHandler(CollisionHandler handler){
		this.handler = handler;
	}
	
	public void update(ArrayList<Entity> entities){
		synchronized(entities){
			for(int e = 0; e < entities.size(); e++){
				if(!entities.get(e).staticEntity){
					entities.get(e).velocity.y += 3;
				}
			}
			//iterates through the entities testing collisions
			for(int i = 0; i < entities.size()-1; i++){
				for(int j = i+1; j < entities.size(); j++){
					Entity one = entities.get(i);
					Entity two = entities.get(j);
					if(!(one.staticEntity && two.staticEntity)){
						if(CollisionTest.testCollision(one, two)){
							//TODO once fancier velocity is added in the translation probably will be needed to be moved as well
							//float xsq = (float)Math.pow(one.velocity.x-two.velocity.x, 2);
							//float ysq = (float)Math.pow(one.velocity.y-two.velocity.y, 2);;
							float speed = 100;
							Collision c = new Collision(one, two, speed);
							handler.handleCollision(c);
							one.handleCollision(c);
							two.handleCollision(c);
						}
					}
				}
			}
			for(int i = 0; i < entities.size(); i++){
				entities.get(i).tick();
			}
			for(int i = 0; i < entities.size(); i++){
				if(entities.get(i).destroy){
					entities.remove(i);
				}
			}
		}
	}
}

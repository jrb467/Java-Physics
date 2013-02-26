package focus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Float;
import java.io.IOException;
import java.util.ArrayList;

import engine.Engine;
import engine.entity.BaseBlock;
import engine.entity.Entity;
import engine.event.Collision;
import engine.event.CollisionHandler;
import engine.serial.WorldLoad;
import engine.serial.WorldSave;
import engine.entity.Character;

import main.Focus;

public class Game extends Focus implements CollisionHandler{
	private Engine engine;
	private ArrayList<Entity> entities;
	private Character you;
	
	boolean right = false;
	boolean left = false;
	boolean brake = false;
	
	//TODO add in the character and do the key events for it
	
	public Game(){
		super(false, true); //TODO the first one (halt updates) may be true
		/*
		try {
			entities = WorldLoad.loadWorld("boners.gamesave");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		entities = new ArrayList<Entity>();
		you = new Character();
		you.location.x = 200;
		you.location.y = 100;
		entities.add(you);
		//entities.clear();
		/**/
		addBlock(-40, 100);
		addBlock(-40, 180);
		addBlock(-40, 260);
		addBlock(-40, 340);
		addBlock(-40, 420);
		addBlock(-40, 500);
		addBlock(40, 500);
		addBlock(120, 500);
		addBlock(200, 500);
		addBlock(280, 500);
		addBlock(360, 500);
		addBlock(440, 500);
		addBlock(520, 500);
		addBlock(600, 500);
		addBlock(680, 500);
		addBlock(760, 500);
		addBlock(760, 420);
		//addBlock(760, 340);
		//addBlock(760, 260);
		//addBlock(760, 180);
		//addBlock(760, 100);
		
		
		/**/
		/**/
		/*
		entities = new ArrayList<Entity>();
		Entity e = new BaseBlock(1, 0);
		e.location.x = 20;
		e.location.y = 380;
		entities.add(e);
		e = new BaseBlock(1, 0);
		e.location.x = 110;
		e.location.y = 380;
		entities.add(e);
		e = new BaseBlock(1, 0);
		e.location.x = 200;
		e.location.y = 380;
		entities.add(e);
		e = new BaseBlock(1, 0);
		e.location.x = 270;
		e.location.y = 380;
		entities.add(e);
		 */
		/*
		Float[] verts = {new Float(0,0), new Float(0,80), new Float(80,80), new Float(80,0)};
		e = new Entity(1, verts, false, 0){
			private static final long serialVersionUID = 1L;
			public void handleCollision(Collision c){}
			public Object[] getRenderInfo(){
				Object[] temp = new Object[2];
				temp[0] = absolutePoints();
				temp[1] = id;
				return temp;
			}
		};
		e.location.x = 110;
		e.location.y = 0;
		entities.add(e);
		*/
		engine = new Engine(); //TODO it'll prolly load from a file or a generation
		engine.setCollisionHandler(this);
	}
	
	
	//LOLOLOLOL HELPERPEHRLEPKSJDFD
	private void rPressed(){
		
		entities = new ArrayList<Entity>();
		you = new Character();
		you.location.x = 200;
		you.location.y = 100;
		entities.add(you);
		//entities.clear();
		/**/
		addBlock(-40, 100);
		addBlock(-40, 180);
		addBlock(-40, 260);
		addBlock(-40, 340);
		addBlock(-40, 420);
		addBlock(-40, 500);
		addBlock(40, 500);
		addBlock(120, 500);
		addBlock(200, 500);
		addBlock(280, 500);
		addBlock(360, 500);
		addBlock(440, 500);
		addBlock(520, 500);
		addBlock(600, 500);
		addBlock(680, 500);
		addBlock(760, 500);
		addBlock(760, 420);
	}
	
	//HELPER METHOD
	private void addBlock(int x, int y){
		Entity n = new BaseBlock(1, 0);
		n.location.x = x;
		n.location.y = y;
		entities.add(n);
	}

	public void update() {
		engine.update(entities);
		updateCharacter();
		// TODO Do physics. Handle collisions. Finish game logic

	}

	public void render(Graphics2D g, int width, int height){
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		Object[] current;
		Float[] points;
		int id;
		synchronized(entities){
			for(Entity e: entities){
				current = e.getRenderInfo();
				points = (Float[])current[0];
				id = (int)current[1];
				int[] x, y;
				x = new int[points.length];
				y = new int[points.length];
				for(int i = 0; i < points.length; i++){
					x[i] = (int)points[i].x;
					y[i] = (int)points[i].y;
				}
				//TODO change to use the id instead of instanceof
				if(e instanceof BaseBlock){
					float health = (float)current[2];
					if(health > 0)
						g.setColor(new Color((int)(health/200), (int)(health/200), (int)(health/200)));
					else
						g.setColor(Color.black);
				}else if(id == 7){
					g.setColor(Color.red);
				}else{
					g.setColor(Color.lightGray);
				}
				g.fillPolygon(x, y, points.length);
				g.setColor(Color.black);
				for(int i = 0; i < points.length-1; i++){
					g.drawLine(x[i], y[i], x[i+1], y[i+1]);
				}
				g.drawLine(x[0], y[0], x[x.length-1], y[y.length-1]); 
				//lol f[0] shout out to captain falcon
			}
		}
	}
	
	public void closing(){
		try {
			WorldSave.save(entities, "boners.gamesave");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateCharacter(){
		if(right){
			synchronized(entities){
				if(you.velocity.x <= 8){
					you.velocity.x += 2;
				}else if(you.velocity.x < 10){
					you.velocity.x = 10;
				}
			}
		}
		if(left){
			synchronized(entities){
				if(you.velocity.x >= -8){
					you.velocity.x -= 2;
				}else if(you.velocity.x > -10){
					you.velocity.x = -10;
				}
			}
		}
		if(brake){
			synchronized(entities){
				if(you.velocity.x < -.5){
					you.velocity.x += .5;
				}else if(you.velocity.x > .5){
					you.velocity.x -= .5;
				}else{
					you.velocity.x = 0;
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D){
			right = true;
		}else if(key == KeyEvent.VK_A){
			left = true;
		}else if(key == KeyEvent.VK_W){
			you.wantsJump = true;
		}else if(key == KeyEvent.VK_R){
			rPressed();
		}else if(key == KeyEvent.VK_S){
			brake = true;
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A){
			left = false;
		}else if(key == KeyEvent.VK_D){
			right = false;
		}else if(key == KeyEvent.VK_S){
			brake = false;
		}
	}
	
	//TODO account for insets
	public void mousePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		BaseBlock b = new BaseBlock(1,0);
		b.location = new Float(x, y);
		synchronized(entities){
			entities.add(b);
		}
	}

	public void handleCollision(Collision c) {
		//TODO add platform specific stuff here based on their "id"s
		
	}
}

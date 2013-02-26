package engine.entity;

import java.awt.Point;
import java.awt.geom.Point2D.Float;
import java.io.Serializable;

import engine.event.Collision;
import engine.math.geometry.Vector;
import engine.math.util.TwoDMath;

public abstract class Entity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public final float friction;
	public final Float[] verticies; //in location to the object origin (0, 0);
	public PointSet[] sets;
	public Float location;
	public Vector velocity = new Vector(0, 0);
	public float minX;
	public float maxX;
	public float minY;
	public float maxY;
	public final boolean staticEntity;
	public boolean destroy = false;
	public final int id;
	public final static byte DEFAULT_ID = 0;
	
	public Entity(float friction, Float[] verticies, boolean isStatic, int id){
		this.friction = friction;
		this.verticies = verticies;
		this.staticEntity = isStatic;
		this.id = id;
		location = new Float();
		calculateBounds();
	}
	
	public void calculateBounds(){
		maxX = java.lang.Float.NEGATIVE_INFINITY;
		minX = java.lang.Float.MAX_VALUE;
		maxY = java.lang.Float.NEGATIVE_INFINITY;
		minY = java.lang.Float.MAX_VALUE;
		for(Float f: verticies){
			if(f == null) break;
			if(f.x > maxX){
				maxX = f.x;
			}else if(f.x < minX){
				minX = f.x;
			}
			if(f.y > maxY){
				maxY = f.y;
			}else if(f.y < minY){
				minY = f.y;
			}
		}
	}
	
	public Float[] absolutePoints(){
		Float[] temp = new Float[verticies.length];
		for(int i = 0; i < verticies.length; i++){
			temp[i] = TwoDMath.sum(verticies[i], location);
		}
		return temp;
	}
	
	public Point[] createDivides(int... vals){
		Point[] p = new Point[vals.length/2];
		for(int i = 0; i < vals.length; i+=2){
			p[i/2] = new Point(vals[i], vals[i+1]);
		}
		return p;
	}
	
	public void createVerticies(int... verts){
		for(int i = 0; i < verts.length; i+=2){
			verticies[i/2] = new Float(verts[i], verts[i+1]);
		}
	}
	
	//Calculates point sets from divisions
	public void calculatePointSets(Point[] d){
		if(d.length == 0){
			sets = new PointSet[1];
			int[] points = new int[verticies.length];
			int[] ignore = {};
			for(int i = 0; i < verticies.length; i++){
				points[i] = i;
			}
			sets[0] = new PointSet(points,ignore);
			return;
		}
		sets = new PointSet[d.length+1];
		int min = 0;
		int max = verticies.length;
		int n, index;
		int[] temp, ignores;
		temp = new int[verticies.length-d[0].y+d[0].x+1];
		ignores = new int[1];
		ignores[0] = d[0].x;
		index = 0;
		for(n = 0; n <= d[0].x; n++){
			temp[index] = n;
			index++;
		}
		for(n = d[0].y; n < max; n++){
			temp[index] = n;
			index++;
		}
		sets[0] = new PointSet(temp, ignores);
		min = d[0].x;
		max = d[0].y;
		for(int i = 1; i < d.length; i++){
			index = 0;
			temp = new int[max+d[i].x-min-d[i].y+2];
			ignores = new int[2];
			ignores[0] = d[i].x;
			if(max == verticies.length){
				ignores[1] = 0;
			}else{
				ignores[1] = max;
			}
			for(n = min; n <= d[i].x; n++){
				temp[index] = n;
				index++;
			}
			for(n = d[i].y; n <= max; n++){
				if(n == verticies.length) break;
				temp[index] = n;
				index++;
			}
			sets[i] = new PointSet(temp, ignores);
			min = d[i].x;
			max = d[i].y;
		}
		temp = new int[d[d.length-1].y-d[d.length-1].x+1];
		ignores = new int[1];
		ignores[0] = max;
		for(n = min; n <= max; n++){
			temp[n-min] = n;
		}
		sets[sets.length-1] = new PointSet(temp, ignores);
	}
	
	public abstract void handleCollision(Collision c);
	
	public abstract Object[] getRenderInfo();
	
	public void tick(){
		if(!this.staticEntity){
			location.setLocation(location.x+velocity.x, location.y+velocity.y);
			velocity = new Vector(velocity.x*.95f, velocity.y*.95f);
		}
	}
}

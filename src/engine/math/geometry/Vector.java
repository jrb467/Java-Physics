package engine.math.geometry;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Vector implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final Vector MAX_VALUE = new Vector(Float.MAX_VALUE, Float.MAX_VALUE);
	public static final Vector DEFAULT = new Vector(0,0);
	public float x;
	public float y;
	
	public Vector(Point2D.Float components){
		x = components.x;
		y = components.y;
	}
	
	public Vector(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector vectorFromSet(Point2D.Float baseVector, Point2D.Float newOrigin){
		return new Vector(new Point2D.Float(baseVector.x-newOrigin.x, baseVector.y-newOrigin.y));
	}
	
	public static Vector vectorFromAxis(Axis a, float length){
		float x = (float)(length/(Math.sqrt(a.slope*a.slope+1)));
		float y = a.slope*x;
		return new Vector(x,y);
	}
	
	public static Vector vectorFromLine(Line l, float length){
		float x = (float)(length/(Math.sqrt(l.slope*l.slope+1)));
		float y = l.slope*x;
		return new Vector(x,y);
	}
	
	public static Vector vectorFromSegment(LineSegment l){
		return new Vector(l.p2.x-l.p1.x, l.p2.y-l.p1.y);
	}
	
	public static float dotProduct(Vector a, Vector b){
		return a.x*b.x + a.y*b.y;
	}
	
	public static Vector multiplyByScalar(Vector v, float s){
		return new Vector(v.x*s, v.y*s);
	}
	
	public static Vector sum(Vector one, Vector two){
		return new Vector(one.x+two.x, one.y+two.y);
	}
	
	public static Vector sub(Vector one, Vector two){
		return new Vector(two.x-one.x, two.y-one.y);
	}
	
	public float getLength(){
		return (float)Math.sqrt(x*x+y*y);
	}
	
	public float getLengthSquared(){
		return x*x+y*y;
	}
	
	public String toString(){
		return x+", "+y;
	}
}

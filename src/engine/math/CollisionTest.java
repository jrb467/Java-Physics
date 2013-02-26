package engine.math;

import java.awt.geom.Point2D.Float;

import engine.math.geometry.Axis;
import engine.math.geometry.Vector;
import engine.math.util.TwoDMath;

import engine.entity.Entity;
import engine.entity.PointSet;

public class CollisionTest {
	/*
	 * Tests if the two polygons are going to collide, are colliding, and what distance needs to be
	 * moved in order to account for collision
	 * 
	 * STEPS:
	 * 
	 * 		1)  Pick an edge on either polygon
	 * 		2)  Find the axis/Vector perpindicular to that axis
	 * 		3) 	Project each polygon onto that axis, using these steps:
	 * 			1) Set the intitial min/max values as the first point coordinates' dot product
	 * 			2) Loop through the rest of the verticies, testing their dot product vs min & max
	 * 			3) Return the min & max values
	 * 			NOTE: dot product and projection is calculated with:
	 * 				proj(a onto b) = (a*b)/mag(b)
	 * 		4)Test if projections overlap, if not BREAK(intersecting=FALSE)
	 * 		5)If they overlap, test to see if the overlap is less than the minimum (to test which axis to move along)
	 * 		6)Repeat for all sides on each polygon
	 * 		7)Deal with the translation vector
	 */
	
	public static boolean testCollision(Entity one, Entity two){
		/* Sets up the variables:
		 * 		puts both entities verticies into two sets (for shorter names)
		 * 		initialize the translationAxis for the final translation
		 * 		NOTE: Float corresponds to Point2D.Float
		 * 		Set up a point for the minimum interval
		 * 		Find the relative velocity of the two objects, store as a vector
		 * 		set up variables for the current projection axis and current translation distance
		 * 		Set up two points (format: (minVal, maxVal)), for the current projections
		 */
		boolean result = false;
		if(one.maxX < two.minX || one.minX > two.maxX || one.minY > two.maxY || one.maxY < two.minY) return false;
		Float[] set1 = one.verticies;
		Float[] set2 = two.verticies;
		PointSet curSet1;
		PointSet curSet2;
		Axis translationAxis = new Axis(1);
		//TODO what are these for?
		Float edgePoint1 = new Float(0,0);
		Float edgePoint2 = new Float(0,0);
		//NOTE: this is a Point2D.Float because the second float is only an indicator which side
		//each entity was on during the collision
		Float minimumInterval = new Float(-java.lang.Float.MAX_VALUE, 0);
		Vector relativeVelocity = Vector.sub(two.velocity, one.velocity);
		Axis currentAxis = null;
		Float currentInterval;
		Float proj1;
		Float proj2;
		//Begin to loop through every side of the polygons
		//for(int m = 0; m < set1.length+set2.length; m++){
		for(int n = 0; n < one.sets.length; n++){
			curSet1 = one.sets[n];
			for(int x = 0; x < two.sets.length; x++){
				curSet2 = two.sets[x];
				for(int i = 0; i < curSet1.points.length+curSet2.points.length; i++){
					//Test if the current axis should be ignored
					boolean cont = false;
					if(i < curSet1.points.length){
						for(int h = 0; h < curSet1.ignore.length; h++){
							if(curSet1.points[i] == curSet1.ignore[h]){
								cont = true;
								break;
							}
						}
					}else{
						for(int h = 0; h < curSet2.ignore.length; h++){
							if(curSet2.points[i-curSet1.points.length] == curSet2.ignore[h]){
								cont = true;
								break;
							}
						}
					}
					//---
					//---
					if(cont) continue;
					currentAxis = createCurrentAxis(i, set1, set2, curSet1, curSet2);
					proj1 = projectSet(currentAxis, one, curSet1, relativeVelocity);
					proj2 = projectSet(currentAxis, two, curSet2, new Vector(0,0));
					currentInterval = intervalDistance(proj1, proj2);
					//if they don't overlap on this axis, they aren't colliding, so return false and break
					if(currentInterval.x > 0){
						break;
					}
					//If its less than the minimum translation, store as minimum translation and take note of the translation axis
					if(currentInterval.x > minimumInterval.x){
						minimumInterval = currentInterval;
						translationAxis = currentAxis;
					}
					if(i == curSet1.points.length+curSet2.points.length-1){
						handleVerifiedCollision(one, two, translationAxis, minimumInterval);
						result = true;
					}
				}
				minimumInterval = new Float(-java.lang.Float.MAX_VALUE, 0);
				translationAxis = new Axis(1);
			}
		}
		return result;
	}
	
	private static void handleVerifiedCollision(Entity one, Entity two, Axis translationAxis, Float minimumInterval){
		if(minimumInterval.y != 0){
			minimumInterval.x = -minimumInterval.x;
		}
		if(one.staticEntity){
			Vector tran2 = Vector.vectorFromAxis(translationAxis, -minimumInterval.x);
			two.velocity = new Vector(tran2.x+two.velocity.x, tran2.y+two.velocity.y);
		}else if(two.staticEntity){
			Vector tran1 = Vector.vectorFromAxis(translationAxis, minimumInterval.x);
			one.velocity = new Vector(tran1.x+one.velocity.x, tran1.y+one.velocity.y);
		}else{
			//calculates how much each would move based on relative mass
			float t1 = minimumInterval.x/2;
			float t2 = -minimumInterval.x/2;
			Vector tran1 = Vector.vectorFromAxis(translationAxis, t1);
			Vector tran2 = Vector.vectorFromAxis(translationAxis, t2);
			one.velocity = new Vector(tran1.x+one.velocity.x, tran1.y+one.velocity.y);
			two.velocity = new Vector(tran2.x+two.velocity.x, tran2.y+two.velocity.y);
		}
		
	}
	
	private static Float intervalDistance(Float a, Float b){
		if(a.x < b.x){
			return new Float(b.x-a.y, 0);
		}
		return new Float(a.x-b.y, 1);
	}
	
	private static Axis createCurrentAxis(int index, Float[] set, Float[] set2, PointSet pSet, PointSet pSet2){
		Axis current;
		if(index < pSet.points.length){
			if(index < pSet.points.length-1){
				current = Axis.axisFromPoints(set[pSet.points[index]], set[pSet.points[index+1]]);
			}else{
				current = Axis.axisFromPoints(set[pSet.points[index]], set[pSet.points[0]]);
			}
		}else{
			index -= pSet.points.length;
			if(index < pSet2.points.length-1){
				current = Axis.axisFromPoints(set2[pSet2.points[index]], set2[pSet2.points[index+1]]);
			}else{
				current = Axis.axisFromPoints(set2[pSet2.points[index]], set2[pSet2.points[0]]);
			}
		}
		return current.perpindict();
	}
	
	public static Float projectSet(Axis axis, Entity entity, PointSet set, Vector relativeVelocity){
		/*	Initialize Variables:
		 * 		put array verticies into a convenient set
		 * 		create a unit vector for projection along the axis
		 * 		create a vector for the first point of the entity (factoring in location)
		 * 		create a float for the dot product of the current point
		 * 		create the dot product for the velocity (handled later)
		 * 		set up the min and max values for projection
		 */
		Float[] pset = entity.verticies;
		Vector vect = axis.unitVector();
		Vector entityVect = new Vector(TwoDMath.sum(pset[set.points[0]],entity.location));
		float dotProduct = Vector.dotProduct(vect, entityVect);
		float veloDotProduct = Vector.dotProduct(vect, relativeVelocity);
		float min = dotProduct;
		float max = dotProduct;
		//start looping through all the other points
		for(int i = 1; i < set.points.length; i++){
			//recalculate the entity vector for the next point
			entityVect = new Vector(TwoDMath.sum(pset[set.points[i]],entity.location));
			//recalculate the dot product
			dotProduct = Vector.dotProduct(vect, entityVect);
			//test against min and max, and alter accordingly
			if(dotProduct < min){
				min = dotProduct;
			}else if(dotProduct > max){
				max = dotProduct;
			}
		}
		//factor in the relative velocity vector
		if(veloDotProduct < 0){
			min += veloDotProduct;
		}else{
			max += veloDotProduct;
		}
		//return a new Point2D.Float in the form (min,max)
		return new Float(min,max);
	}
	
	/*
	 * Gives the projection of an entity (+relative velocity) over the vector defining one of it's sides.
	 * Returns it as a Point2D.Float in the form (min,max)
	 */
	public static Float projectEntity(Axis axis, Entity entity, Vector relativeVelocity){
		/*	Initialize Variables:
		 * 		put array verticies into a convenient set
		 * 		create a unit vector for projection along the axis
		 * 		create a vector for the first point of the entity (factoring in location)
		 * 		create a float for the dot product of the current point
		 * 		create the dot product for the velocity (handled later)
		 * 		set up the min and max values for projection
		 */
		Float[] set = entity.verticies;
		Vector vect = axis.unitVector();
		Vector entityVect = new Vector(TwoDMath.sum(set[0],entity.location));
		float dotProduct = Vector.dotProduct(vect, entityVect);
		float veloDotProduct = Vector.dotProduct(vect, relativeVelocity);
		float min = dotProduct;
		float max = dotProduct;
		//start looping through all the other points
		for(int i = 1; i < set.length; i++){
			//recalculate the entity vector for the next point
			entityVect = new Vector(TwoDMath.sum(set[i],entity.location));
			//recalculate the dot product
			dotProduct = Vector.dotProduct(vect, entityVect);
			//test against min and max, and alter accordingly
			if(dotProduct < min){
				min = dotProduct;
			}else if(dotProduct > max){
				max = dotProduct;
			}
		}
		//factor in the relative velocity vector
		if(veloDotProduct < 0){
			min += veloDotProduct;
		}else{
			max += veloDotProduct;
		}
		//return a new Point2D.Float in the form (min,max)
		return new Float(min,max);
	}
}

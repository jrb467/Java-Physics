package engine.entity;

import java.io.Serializable;

public class PointSet implements Serializable{
	private static final long serialVersionUID = 1L;
	public final int[] ignore;
	public final int[] points;
	
	public PointSet(int[] points, int[] ignore){
		this.ignore = ignore;
		this.points = points;
	}
}

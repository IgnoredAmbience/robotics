package navigation;

import lejos.geom.Point;
import lejos.robotics.navigation.Pose;

import java.lang.Math;

public class Wall {

	private static final float ERROR = 0.001f;
	private Point a;
	private Point b;
	
	/**
	 * Represents a wall between points p1 and p2.
	 * 
	 * @param p1 the start of end point of the wall.
	 * @param p2 
	 */
	public Wall(Point p1, Point p2) {
		this.a = p1;
		this.b = p2;
	}
	
	/**
	 * 
	 * @see distanceToWall(float x, float y, double angle)
	 * 
	 * @param p the pose of the object
	 * @return the distance to the wall.
	 */
	public float distanceToWall(Pose p) {
		return distanceToWall(p.getX(), p.getY(), p.getHeading());
	}
	
	/**
	 * Calculates the size of the line going from (x, y) to the wall with a specific angle.
	 * 
	 * Uses equation in notes:
	 * m = ((by - ay)(ax - x) - (bx - ax)(ay - y)) / ((by - ay) (cos angle) - (bx - ax) (sin angle))
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param degrees angle in degrees of the input line, given from the x axis.
	 * @return the distance to the wall. Returns max double if the wall and line do not intersect.
	 */
	public float distanceToWall(float x, float y, float degrees) {
		float angle = (float) Math.toRadians(degrees);
		
		float yDiff = (float) (b.getY() - a.getY());
		float xDiff = (float) (b.getX() - a.getX());
		
		float div = (float) ((yDiff * Math.cos(angle)) - xDiff * Math.sin(angle));
		
		// Prevent /0 errors?
		if (div < ERROR && div > -ERROR) {
			return Float.MAX_VALUE;
		}
		
		float top = yDiff * ((float)(a.getX()) - x) - xDiff * ((float)(a.getY()) - y);
		
		return top / div;
		
	}
	
	public boolean willCollide(Pose p) {
		return willCollide(p.getX(), p.getY(), p.getHeading());
	}
	
	
	public boolean willCollide(float x, float y, float angle) {
		double dist = distanceToWall(x, y, angle);
		Point intersect = movePoint(x, y, angle, dist);
		/*
		System.out.print("willCollide: (" + String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(angle));
		System.out.print(a);
		System.out.println(b);
		System.out.print(dist);
		System.out.print(" point: ");
		System.out.print(intersect);
		System.out.println(pointInLineBounds(intersect));
		*/
		
		return pointInLineBounds(intersect);
	}
	
	private Point movePoint (double x, double y, double degrees, double distance) {
		double angle = Math.toRadians(degrees);
		float newX = (float) (x + distance * Math.cos(angle));
		float newY = (float) (y + distance * Math.sin(angle));
		
		return new Point(newX, newY);
	}
	
	private boolean pointInLineBounds(Point p) {
		long x = Math.round(p.getX());
		long y = Math.round(p.getY());
		
		// checks whether the x coordinate is between and then checks if the y coordinate is between.
		return ((x <= a.getX() && x >= b.getX()) ||
				(x >= a.getX() && x <= b.getX())) &&
			   ((y <= a.getY() && y >= b.getY()) ||
			     y >= a.getY() && y <= b.getY());
		
	}
}

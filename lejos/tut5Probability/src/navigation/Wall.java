package navigation;

import navigation.Pose;

import lejos.geom.Point;

import java.lang.Math;

public class Wall {

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
	public double distanceToWall(Pose p) {
		return distanceToWall(p.getX(), p.getY(), p.getAngle());
	}
	
	/**
	 * Calculates the size of the line going from (x, y) to the wall with a specific angle.
	 * 
	 * Uses equation in notes:
	 * m = ((by - ay)(ax - x) - (bx - ax)(ay - y)) / ((by - ay) (cos angle) - (bx - ax) (sin angle))
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param angle angle of the input line, given from the x axis.
	 * @return the distance to the wall. Returns max double if the wall and line do not intersect.
	 */
	public double distanceToWall(float x, float y, double angle) {
		
		double yDiff = b.getY() - a.getY();
		double xDiff = b.getX() - a.getX();
		
		double div = (yDiff * Math.cos(angle)) - xDiff * Math.sin(angle);
		
		if (div == 0) {
			return Double.MAX_VALUE;
		}
		
		double top = yDiff * (a.getX() - x) - xDiff * (a.getY() - y);
		
		return top / div;
		
	}
	
	public boolean willCollide(Pose p) {
		return willCollide(p.getX(), p.getY(), p.getAngle());
	}
	
	
	public boolean willCollide(float x, float y, double angle) {
		double dist = distanceToWall(x, y, angle);
		Point intersect = movePoint(x, y, angle, dist);
		
		return pointInLineBounds(intersect);
	}
	
	private Point movePoint (float x, float y, double angle, double distance) {
		float newX = (float) (x + distance * Math.cos(angle));
		float newY = (float) (y + distance * Math.sin(angle));
		
		return new Point(newX, newY);
	}
	
	private boolean pointInLineBounds(Point p) {
		
		// checks whether the x coordinate is between and then checks if the y coordinate is between.
		return ((p.getX() < a.getX() && p.getX() > b.getX()) ||
				(p.getX() > a.getX() && p.getX() < b.getX())) &&
			   ((p.getY() < a.getY() && p.getY() > b.getY()) ||
			     p.getY() > a.getY() && p.getY() < b.getY());
		
	}
}

package navigation;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import lejos.robotics.navigation.Pose;

public class WallSet implements Iterable<Wall> {
	
	private Collection<Wall> walls;
	
	public WallSet() {
		this.walls = new ArrayList<Wall> ();
	}
	
	public WallSet(Collection<Wall> walls) {
		this.walls = walls;
	}
	
	public Wall facingWall(Pose p) {
		
		Wall nearestWall = walls.iterator().next();
		float nearestDist = Float.MAX_VALUE;
		
		for (Wall w : walls) {
			float dist = w.distanceToWall(p);
			if (dist < nearestDist && dist > 0 && w.willCollide(p)) {
				nearestDist = dist;
				nearestWall = w;
			}
		}
		
		return nearestWall;
	}
	
	
	public void addWall(Wall w) {
		walls.add(w);
	}

	@Override
	public Iterator<Wall> iterator() {
		return walls.iterator();
	}
}

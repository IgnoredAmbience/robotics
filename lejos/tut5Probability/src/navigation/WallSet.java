package navigation;

import java.util.Collection;
import java.util.ArrayList;

public class WallSet {
	
	private Collection<Wall> walls;
	
	public WallSet() {
		this.walls = new ArrayList<Wall> ();
	}
	
	public WallSet(Collection<Wall> walls) {
		this.walls = walls;
	}
	
	public Wall facingWall(Pose p) {
		
		Wall nearestWall = null;
		double nearestDist = Double.MAX_VALUE;
		
		for (Wall w : walls) {
			double dist = w.distanceToWall(p);
			if (w.willCollide(p) && dist < nearestDist) {
				nearestDist = dist;
				nearestWall = w;
			}
		}
		
		return nearestWall;
	}
	
}

import lejos.geom.Point;
import navigation.Wall;
import navigation.WallSet;


public class CreateWalls {
	
	public static WallSet createWalls() {
		WallSet w = new WallSet();
		w.addWall(new Wall(new Point(0, 0), new Point(0, 1680)));
		w.addWall(new Wall(new Point(0, 1680), new Point(840, 1680)));
		w.addWall(new Wall(new Point(840, 1680), new Point(840, 1260)));
		w.addWall(new Wall(new Point(840, 1260), new Point(840, 2100)));
		w.addWall(new Wall(new Point(840, 2100), new Point(1680, 840)));
		w.addWall(new Wall(new Point(1680, 840), new Point(2100, 840)));
		w.addWall(new Wall(new Point(2100, 840), new Point(2100, 0)));
		w.addWall(new Wall(new Point(2100, 0), new Point(0, 0)));
		return w;
	}
	
}

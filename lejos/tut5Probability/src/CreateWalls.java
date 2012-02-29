import lejos.geom.Point;
import navigation.Wall;
import navigation.WallSet;


public class CreateWalls {
	
	public static WallSet createWalls() {
		WallSet w = new WallSet();
		w.addWall(new Wall(new Point(0, 0), new Point(0, 168)));
		w.addWall(new Wall(new Point(0, 168), new Point(84, 168)));
		w.addWall(new Wall(new Point(84, 168), new Point(84, 126)));
		w.addWall(new Wall(new Point(84, 126), new Point(84, 210)));
		w.addWall(new Wall(new Point(84, 210), new Point(168, 84)));
		w.addWall(new Wall(new Point(168, 84), new Point(210, 84)));
		w.addWall(new Wall(new Point(210, 84), new Point(210, 0)));
		w.addWall(new Wall(new Point(210, 0), new Point(0, 0)));
		return w;
	}
	
}

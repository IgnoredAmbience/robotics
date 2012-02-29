import lejos.geom.Point;
import navigation.Wall;
import navigation.WallSet;


public class CreateWalls {
	
	public static WallSet createWalls() {
		Point po = new Point(0,0);
		Point pa = new Point(0,1680);
		Point pb = new Point(840,1680);
		Point pc = new Point(840,1260);
		Point pd = new Point(840,2100);
		Point pe = new Point(1680,2100);
		Point pf = new Point(1680,840);
		Point pg = new Point(2100,840);
		Point ph = new Point(2100,0);
		
		WallSet w = new WallSet();
		w.addWall(new Wall(po, pa));
		w.addWall(new Wall(pa, pb));
		w.addWall(new Wall(pc, pd));
		w.addWall(new Wall(pd, pe));
		w.addWall(new Wall(pe, pf));
		w.addWall(new Wall(pf, pg));
		w.addWall(new Wall(pg, ph));
		w.addWall(new Wall(ph, po));
		return w;
	}
	
}

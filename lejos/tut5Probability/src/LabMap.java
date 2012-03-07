import lejos.geom.Point;
import navigation.Wall;
import navigation.WallSet;


public class LabMap extends WallSet {
	public LabMap() {
		super();
		Point po = new Point(0,0);
		Point pa = new Point(0,1680);
		Point pb = new Point(840,1680);
		Point pc = new Point(840,1260);
		Point pd = new Point(840,2100);
		Point pe = new Point(1680,2100);
		Point pf = new Point(1680,840);
		Point pg = new Point(2100,840);
		Point ph = new Point(2100,0);
		
		addWall(new Wall(po, pa));
		addWall(new Wall(pa, pb));
		addWall(new Wall(pc, pd));
		addWall(new Wall(pd, pe));
		addWall(new Wall(pe, pf));
		addWall(new Wall(pf, pg));
		addWall(new Wall(pg, ph));
		addWall(new Wall(ph, po));
	}
}

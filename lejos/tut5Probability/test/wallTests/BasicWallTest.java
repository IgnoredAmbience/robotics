package wallTests;

import static org.junit.Assert.*;
import junit.framework.Assert;
import lejos.geom.Point;

import navigation.Wall;
import navigation.WallSet;

import org.junit.Before;
import org.junit.Test;

import utils.Pose;

public class BasicWallTest {

	WallSet w;
	
	@Before
	public void setUp() throws Exception {
		w = new WallSet();
		w.addWall(new Wall(new Point(0, 0), new Point (0, 5)));
		w.addWall(new Wall(new Point(0, 5), new Point (5, 5)));
		w.addWall(new Wall(new Point(5, 5), new Point (5, 0)));
		w.addWall(new Wall(new Point(5, 0), new Point(0, 0)));
	}

	@Test
	public void test() {
		Assert.assertNotNull(w.facingWall(new Pose(2, 2, 0)));
	}

}

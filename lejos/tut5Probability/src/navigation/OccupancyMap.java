package navigation;

import lejos.geom.Point;
import lejos.nxt.LCD;
import lejos.robotics.navigation.Pose;

/**
 * The occupancy map take input in real-world coordinates
 * @author thomas
 *
 */
public class OccupancyMap {
	private int[][] map;
	private int mx, my, g;
	
	public boolean display;
	private float lx, ly;
	private final int DISPLAY_THRESH = 1;
	
	public OccupancyMap() {
		this(4000, 2560, 40, true);
	}
	
	/**
	 * @param maxx (exclusive)
	 * @param maxy (exclusive)
	 * @param granularity
	 */
	public OccupancyMap(int maxx, int maxy, int granularity, boolean displayLCD) {
		g = granularity;
		mx = maxx;
		my = maxy;
		map = new int[mx / g][my / g];
		display = displayLCD;
		lx = LCD.SCREEN_WIDTH / (mx / g);
		ly = LCD.SCREEN_HEIGHT / (my / g);
	}
	
	int getValueAt(Point p) throws IndexOutOfBoundsException {
		if(p.x < 0 || p.x > mx) {
			throw new IndexOutOfBoundsException("Point x out of bounds: " + p.x + " <> " + mx);
		} else if(p.y < 0 || p.y > my) {
			throw new IndexOutOfBoundsException("Point y out of bounds: " + p.y + " <> " + my);
		} else {
			return map[((int) p.x)/g][((int) p.y)/g];
		}
	}
	
	/**
	 * Swallows errors
	 */
	private void addValueAtCell(int x, int y, int v) {
		if(x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
			return;
		} else {
			map[x][y] += v;
			if(display) {
				LCD.setPixel((int) (x*lx), (int) (y*ly),
						map[x][y] >= DISPLAY_THRESH ? 1 : 0);
			}
		}
	}
	
	private void addValueAtCoord(float x, float y, int v) {
		addValueAtCell((int) (x/g), (int) (y/g), v);
	}
	
	/**
	 * Given a pose, offset angle from 'forward', and a measured distance, update
	 * the occupancy map to take this into account
	 * 
	 * This assumes that the sonar beam acts as a ray (as it has shown to be in testing)
	 * 
	 * @param robotPose
	 * @param angle	Offset angle from the robot's forward direction that the
	 * 		distance measure was taken with
	 * @param distance Distance to obstruction
	 */
	public void updateValue(Pose robotPose, int angle, int distance) {
		int halfBeamWidth = 25;
		
		Point rayEnd = robotPose.pointAt(distance, robotPose.getHeading() + angle);
		
		int x0 = (int) (robotPose.getX());
		int y0 = (int) (robotPose.getY());
		int x1 = (int) (rayEnd.getX());
		int y1 = (int) (rayEnd.getY());
		
		// Mark cells around beam end as filled
		for(int cx = -1; cx <= 1; cx++) {
			for(int cy = -1; cy <= 1; cy++) {
				addValueAtCell(cx + x1/g, cy + y1/g, -1);
			}
		}
		
		// Mark beam as clear
		
		if(x0 > x1) {
			int t = x0;
			x0 = x1;
			x1 = t;
		}
		if(y0 > y1) {
			int t = y0;
			y0 = y1;
			y1 = t;
		}

		float m = (y1 - y0)/(x1 - x0);
		
		// We approximate a wide beam with ends parallel to the axes
		// Mark all cells up to beam end as clear
		for(float x = x0, y = y0; x <= x1; x+=g) {
			y += m*g;
			
			if(m > 1) {
				addValueAtCoord(x-halfBeamWidth, y, 1);
				addValueAtCoord(x+halfBeamWidth, y, 1);
			} else {
				addValueAtCoord(x, y-halfBeamWidth, 1);
				addValueAtCoord(x, y+halfBeamWidth, 1);
			}
		}
	}
}

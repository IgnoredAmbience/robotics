
import lejos.geom.Point;
import lejos.robotics.localization.PoseProvider;
import tasks.WaypointNavigator;


public class CreateWayPoints {
	public static WaypointNavigator getWayPoints(PoseProvider p) {
		WaypointNavigator w = new WaypointNavigator(p); 
		// Initial location - w.addElement(new Point(840, 300));
		w.addElement(new Point(1800, 300));
		w.addElement(new Point(1800, 540));
		w.addElement(new Point(1260, 540));
		w.addElement(new Point(1260, 1680));
		w.addElement(new Point(1260, 1260));
		w.addElement(new Point(300, 540));
		w.addElement(new Point(840, 540));
		w.addElement(new Point(840, 300));
		return w;
	}
}


import lejos.geom.Point;
import lejos.robotics.localization.PoseProvider;
import tasks.WaypointNavigator;


public class CreateWayPoints {
	public static WaypointNavigator getWayPoints(PoseProvider p) {
		WaypointNavigator w = new WaypointNavigator(p); 
		w.addElement(new Point(84, 30));
		w.addElement(new Point(180, 30));
		w.addElement(new Point(180, 54));
		w.addElement(new Point(126, 54));
		w.addElement(new Point(126, 168));
		w.addElement(new Point(126, 126));
		w.addElement(new Point(30, 54));
		w.addElement(new Point(84, 54));
		w.addElement(new Point(84, 30));
		return w;
	}
}

package utils;

import lejos.robotics.navigation.Move;

public class Pose extends lejos.robotics.navigation.Pose {
	public Pose() {
		super();
	}
	
	public Pose(float x, float y, float heading) {
		super(x,y,heading);
	}

	public void updatePose(Move m) {
		switch(m.getMoveType()) {
		case TRAVEL:
			moveUpdate(m.getDistanceTraveled());
			break;
		case ROTATE:
			rotateUpdate(m.getAngleTurned());
			break;
		}
	}
}

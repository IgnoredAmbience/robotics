package utils;

import interfaces.Drawable;
import lejos.nxt.LCD;
import lejos.robotics.navigation.Move;

public class Pose extends lejos.robotics.navigation.Pose implements Drawable {
	private static final float unitsPerPixel = 30;
	private static final int xoffset = LCD.SCREEN_WIDTH / 2;
	private static final int yoffset = LCD.SCREEN_HEIGHT / 2;
	
	public Pose() {
		super();
	}
	
	public Pose(float x, float y, float heading) {
		super(x,y,heading);
	}
	
	

	public Pose(float x, float y) {
		super(x, y, 0);
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

	@Override
	public void drawToLCD() {
		LCD.setPixel((int) (getX()/unitsPerPixel) + xoffset,
								 (int) (getY()/unitsPerPixel) + yoffset, 1);
	}
}

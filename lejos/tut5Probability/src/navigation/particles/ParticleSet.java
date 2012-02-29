package navigation.particles;

import interfaces.Drawable;
import java.util.ArrayList;

import utils.Pose;

import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;


public class ParticleSet extends ArrayList<Particle> implements Drawable, MoveListener, PoseProvider {
	private final static int DEFAULT_MAX_SIZE = 100;
	private final static float unitsPerPixel = 30;

	public ParticleSet() {
                this(DEFAULT_MAX_SIZE);
	}
	
	public ParticleSet(int maxSize) {
		super(maxSize);
		for(int i = 0; i < maxSize; i++) {
			add(new Particle());
		}
	}
	
	public float getWeight() {
		return 1/this.size();
	}
	
	public void moveUpdate(float distance) {
		for(Particle p : this) {
			p.moveUpdate(distance);
		}
	}
	
	public void rotateUpdate(float angle) {
		for(Particle p : this) {
			p.rotateUpdate(angle);
		}
	}

	@Override
	public void drawToLCD() {
		for(Particle p : this) {
			p.drawToLCD();
		}
	}

	@Override
	public void moveStarted(Move event, MoveProvider mp) {}

	@Override
	public void moveStopped(Move event, MoveProvider mp) {
		for(Pose p : this) {
			p.updatePose(event);
		}
		drawToLCD();
	}

	@Override
	public Pose getPose() {
		double x = 0, y = 0, angle = 0;
		for(Particle p : this) {
			x += p.getX();
			y += p.getY();
			angle += p.getHeading();
		}
		x /= size();
		y /= size();
                // FIXME: Angles cannot be handled so simply!
		angle /= size();
		
		return new Pose((float) x, (float) y, (float) angle);
	}

	@Override
	public void setPose(lejos.robotics.navigation.Pose aPose) {
		// Intentionally empty
	}
}

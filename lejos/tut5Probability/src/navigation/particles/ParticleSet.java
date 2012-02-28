package navigation.particles;

import interfaces.Drawable;
import java.util.ArrayList;

import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;


public class ParticleSet extends ArrayList<Particle> implements Drawable, MoveListener {
	private final static int MAX_SIZE = 100;
	private final static float unitsPerPixel = 30;

	
	public ParticleSet() {
		super(MAX_SIZE);
		for(int i = 0; i < MAX_SIZE; i++) {
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
		for(Particle p : this) {
			p.updatePose(event);
		}
		drawToLCD();
	}
}

package navigation.particles;

import interfaces.Drawable;
import java.util.ArrayList;

import MCLocalisation.Pair;

import utils.Pose;
import utils.RandomGenerator;

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
	}

	public ParticleSet(Pose initialPose) {
    this(DEFAULT_MAX_SIZE, initialPose);
	}
	
	public ParticleSet(int maxSize, Pose initialPose) {
		this(maxSize);
		for(int i = 0; i < maxSize; i++) {
			Particle p = new Particle(initialPose.getX(), initialPose.getY(), initialPose.getHeading());
			add(p);
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
		//resample();
		
		double x = 0, y = 0, angle = 0, weight = 0;
		for(Particle p : this) {
			//System.out.println(p.toString());
			x += p.getWeight() * p.getX();
			y += p.getWeight() * p.getY();
			angle += p.getWeight() * p.getHeading();
			weight += p.getWeight();
		}
		//System.out.println(weight);
		x /= weight;
		y /= weight;
    // FIXME: Angles cannot be handled so simply!
		angle /= weight;
		
		drawToLCD();
		
		return new Pose((float) x, (float) y, (float) angle);
	}

	@Override
	public void setPose(lejos.robotics.navigation.Pose aPose) {
		// Intentionally empty
	}
	
	
	public void resample() {
		
		ParticleSet newSet = new ParticleSet(this.size());
		ArrayList<Pair> accumulatorArray = new ArrayList<Pair> ();
		
		float accumulator = 0.0f;
		for (Particle p : this) {
			accumulator += p.getWeight();
			accumulatorArray.add(new Pair(accumulator, p));
		}
		
		
		double counter = 0.0;
		while (newSet.size() < this.size()) {
			counter = RandomGenerator.sampleUniform(accumulator);
			
			Particle valueToAdd = null;
			for (Pair p : accumulatorArray) {
				if (counter <= p.value) {
					valueToAdd = p.attached;
					break;
				}
			}
			
			if (valueToAdd == null) {
				continue;
			} else {
				newSet.add(valueToAdd);
			}
		}
		
		this.clear();
		
		for (Particle p : newSet) {
			this.add(p);
		}
		
	}
}

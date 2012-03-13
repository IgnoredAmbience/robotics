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
	private Pose currentPose;
	private boolean needsUpdate = false;
	
	private ParticleSet(int maxSize) {
		super(maxSize);
	}

	public ParticleSet(Pose initialPose) {
    this(DEFAULT_MAX_SIZE, initialPose);
	}
	
	public ParticleSet(int maxSize, Pose initialPose) {
		this(maxSize);
		currentPose = initialPose;
		setPose(initialPose);
	}
	
	public float getWeight() {
		return 1/this.size();
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
		needsUpdate = true;
	}

	@Override
	public Pose getPose() {
		if(needsUpdate) {
			resample();
		}
		return new Pose(currentPose.getX(), currentPose.getY(), currentPose.getHeading());
	}

	@Override
	public void setPose(lejos.robotics.navigation.Pose aPose) {
		for(int i = 0; i < size(); i++) {
			clear();
			Particle p = new Particle(aPose.getX(), aPose.getY(), aPose.getHeading());
			add(p);
		}
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
			
			Particle v = null;
			for (Pair p : accumulatorArray) {
				if (counter <= p.value) {
					v = p.attached;
					break;
				}
			}
			
			if (v == null) {
				continue;
			} else {
				newSet.add(new Particle(v.getX(), v.getY(), v.getHeading()));
			}
		}
		
		this.clear();
		

		// Calculate current pose
		double x = 0, y = 0, angle1 = 0, angle2 = 0, weight = 0;
		
		for (Particle p : newSet) {
			this.add(p);
			
			x += p.getWeight() * p.getX();
			y += p.getWeight() * p.getY();
			angle1 += p.getWeight() * Math.sin(Math.toRadians(p.getHeading()));
			angle2 += p.getWeight() * Math.cos(Math.toRadians(p.getHeading()));
			weight += p.getWeight();
		}

		x /= weight;
		y /= weight;
		
		currentPose = new Pose((float) x, (float) y, 0);
		currentPose.rotateUpdate((float) Math.toDegrees(Math.atan2(angle1, angle2)));
		needsUpdate = false;
	}
}

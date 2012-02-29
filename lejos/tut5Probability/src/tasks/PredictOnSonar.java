package tasks;

import navigation.Wall;
import navigation.particles.Particle;
import navigation.particles.ParticleSet;
import lejos.robotics.navigation.Pose;
import robot.Robot;

public class PredictOnSonar implements Task {

	private static final double ERROR = 2;
	private ParticleSet p;
	
	
	public PredictOnSonar() {
		p = new ParticleSet();
	}
	
	public PredictOnSonar(ParticleSet possiblePoses) {
		this.p = possiblePoses;
	}
	
	@Override
	public void run(Robot r) {
		
		int dist = r.getSensorDist();
		
		Pose p = r.getCurrentPose();
		
		Wall w = r.wallSet.facingWall(p);
		
		if (!(w.distanceToWall(p) - dist < ERROR || dist - w.distanceToWall(p) < ERROR)) {
			p = workOutBestPose(w, p);
			r.setPose(p);
		}
		
	}

	private Pose workOutBestPose(Wall facingWall, Pose cur) {
		
		double dist = facingWall.distanceToWall(cur);
		Pose best = cur;
		
		for (Particle p : this.p) {
			double curDist = facingWall.distanceToWall(p);
			if (dist > facingWall.distanceToWall(p)) {
				best = p;
				dist = curDist;
			}
		}
		
		return best;
	}

}

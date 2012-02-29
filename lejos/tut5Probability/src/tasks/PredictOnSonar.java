package tasks;

import navigation.Wall;
import navigation.WallSet;
import navigation.particles.Particle;
import robot.Robot;

public class PredictOnSonar implements Task {
	
	private double sonarDeviation = 0.0;
	
	@Override
	public void run(Robot r) {
		
		float dist = r.getSensorDist();
		WallSet w = r.wallSet;
		sonarDeviation = r.sonarDeviation;
		
		
		for (Particle p : r.particleSet) {
			
			float newWeight = calculateLikelihood(p, dist, w);
			
			p.setWeight(newWeight);
			
		}
		
	}
	
	public float calculateLikelihood(Particle p, float actualDist, WallSet w) {
		
		Wall facing = w.facingWall(p);
		float pDist = facing.distanceToWall(p);
		
		return gausianFunc(pDist, actualDist);
	}

	private float gausianFunc(float m, float z) {
		double accum = -(Math.pow((z - m), 2));
		accum = (accum / (2 * Math.pow(sonarDeviation, 2)));
		accum = Math.exp(accum);
		
		return (float) accum;
	}



}

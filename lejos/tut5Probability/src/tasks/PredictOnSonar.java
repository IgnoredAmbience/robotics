package tasks;

import navigation.Wall;
import navigation.WallSet;
import navigation.particles.Particle;
import robot.Robot;

public class PredictOnSonar implements Task {
	
	private double sonarDeviation = 0.0;
	private float gaussianConst = 0.01f;
	
	@Override
	public void run(Robot r) {
		
		float dist = r.getSensorDist();
		WallSet w = r.wallSet;
		sonarDeviation = r.sonarDeviation;
		
		
		for (Particle p : r.particleSet) {
			
			float newWeight = calculateLikelihood(p, dist, w);
			//System.out.println(newWeight);
			
			p.setWeight(newWeight);
			
		}
		
	}
	
	public float calculateLikelihood(Particle p, float actualDist, WallSet w) {
		
		Wall facing = w.facingWall(p);
		if (facing == null) {
			throw new NullPointerException("There is no wall that you are facing");
		}
		float pDist = facing.distanceToWall(p);
		
		//System.out.println("Pdist: " + String.valueOf(pDist) + " actualDist: " + String.valueOf(actualDist));
		
		return gausianFunc(pDist, actualDist) + gaussianConst;
	}

	private float gausianFunc(float m, float z) {
		double accum = -(Math.pow((z - m), 2));
		accum = (accum / (2 * Math.pow(sonarDeviation, 2)));
		accum = Math.exp(accum);
		
		return (float) accum;
	}



}

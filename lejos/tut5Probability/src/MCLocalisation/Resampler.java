package MCLocalisation;

import navigation.particles.ParticleSet;

import java.util.Map;
import java.util.HashMap;

public class Resampler {
	
	public static ParticleSet resample(ParticleSet s) {
		
		ParticleSet newSet = new ParticleSet ();
		
		double accumulator = 0.0;
		for (Particle p : s) {
			accumulator += p.getWeight();
			
		}
		
	}
	
	
	
	private class Pair {
		
		public double value;
		public Particle attached;
		
		public Pair (double value, Particle attached) {
			this.attached = attached;
			this.value = value;
		}
		
	}
	
}

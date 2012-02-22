package MCLocalisation;

import navigation.particles.Particle;
import navigation.particles.ParticleSet;

import java.util.ArrayList;

public class Resampler {
	
	public static ParticleSet resample(ParticleSet s) {
		
		ParticleSet newSet = new ParticleSet ();
		ArrayList<Pair> accumulatorArray = new ArrayList<Pair> ();
		
		double accumulator = 0.0;
		for (Particle p : s) {
			accumulator += p.getWeight();
			
		}
		
		// TODO: finish this;
		
		return newSet;
		
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

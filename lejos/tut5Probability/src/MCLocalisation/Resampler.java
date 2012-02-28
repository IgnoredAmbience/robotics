package MCLocalisation;

import navigation.particles.Particle;
import navigation.particles.ParticleSet;

import java.util.ArrayList;

import utils.RandomGenerator;

public class Resampler {
	
	public static ParticleSet resample(ParticleSet s, int resampleSize) {
		
		ParticleSet newSet = new ParticleSet ();
		ArrayList<Pair> accumulatorArray = new ArrayList<Pair> ();
		
		float accumulator = 0.0f;
		for (Particle p : s) {
			accumulator += p.getWeight();
			accumulatorArray.add(new Pair(accumulator, p));
		}
		
		
		double counter = 0.0;
		while (newSet.size() < resampleSize) {
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
		
		return newSet;
		
	}
	
}

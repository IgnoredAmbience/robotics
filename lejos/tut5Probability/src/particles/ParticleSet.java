package particles;

import java.util.ArrayList;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.NormalDistribution;
import org.apache.commons.math.distribution.NormalDistributionImpl;

public class ParticleSet {
	private ArrayList<Particle> particles;
	private final int MAX_SIZE = 100;
	
	public ParticleSet () {
		this.particles = new ArrayList<Particle> (MAX_SIZE);
	}
	
	
	public void addParticle (Particle p) {
		particles.add(p);
	}
	
	
	public float getWeight() {
		return 1/particles.size();
	}
	
	public double random() {
		NormalDistribution n = new NormalDistributionImpl ();
		
		try {
			return n.cumulativeProbability(0);
		} catch (MathException e) {
			return Double.MAX_VALUE;
		}
	}
	
}

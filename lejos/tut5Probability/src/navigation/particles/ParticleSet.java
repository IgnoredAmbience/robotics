package particles;

import java.util.ArrayList;

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
}

package navigation.particles;

import java.util.ArrayList;

public class ParticleSet extends ArrayList<Particle> {
	private final static int MAX_SIZE = 100;
	
	public ParticleSet () {
		super(MAX_SIZE);
	}	
	
	public float getWeight() {
		return 1/this.size();

	}
}

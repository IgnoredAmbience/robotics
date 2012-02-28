package MCLocalisation;

import navigation.particles.Particle;

/**
 * Class used in building an accumulator array.
 * 
 * @author Chris
 *
 */
public class Pair {
	public double value;
	public Particle attached;

	public Pair (double value, Particle attached) {
		this.attached = attached;
		this.value = value;
	}
}
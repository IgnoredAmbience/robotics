package navigation.particles;

import utils.Pose;
import utils.RandomGenerator;

/**
 * A class that shows a particle with a position,  facing and positive weight.
 * 
 * @author Chris Bates
 *
 */
public class Particle extends Pose {
	
	
	
	private float weight;
	static float MOVE_DEVIATION = 7f;
	static float ROTATE_DEVIATION = 5f;

	public Particle() {
		super();
		this.setWeight(0);
	}

	public Particle(float x, float y, float angle) {
		super(x, y, angle);
		this.setWeight(0);
	}

	public Particle(float x, float y) {
		super(x, y);
		this.setWeight(0);
	}
	
	public Particle(float x, float y, float angle, float weight) {
		super(x, y, angle);
		this.setWeight(weight);
	}

	/**
	 * @param weight a positive double expressing the weight associated with this particle
	 */
	public void setWeight(float weight) {
		
		if (weight < 0) {
			throw new NumberFormatException("The weight given (" + weight + ") was negative. Weights cannot be negative.");
		}
		this.weight = weight;
		
	}

	/**
	 * 
	 * @return a positive double expressing the weight of the particle
	 */
	public float getWeight() {
		return this.weight;
	}
	
	@Override
	public void moveUpdate(float distance) {
		super.moveUpdate(RandomGenerator.sampleGaussian(distance, MOVE_DEVIATION));
	}
	
	@Override
	public void rotateUpdate(float angle) {
		super.rotateUpdate(RandomGenerator.sampleGaussian(angle, ROTATE_DEVIATION));
	}
}

package navigation.particles;

import utils.Pose;
import utils.RandomGenerator;

/**
 * A class that shows a particle with a position and facing.
 * 
 * @author Chris Bates
 *
 */
public class Particle extends Pose {
	static float MOVE_DEVIATION = 7f;
	static float ROTATE_DEVIATION = 5f;

	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
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

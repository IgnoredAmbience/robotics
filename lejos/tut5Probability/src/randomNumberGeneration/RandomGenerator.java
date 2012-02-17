package randomNumberGeneration;

import java.util.Random;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.NormalDistributionImpl;;

public class RandomGenerator {
	
	/**
	 * Tries to return a sample from the Gaussian distribution with mean and standardDeviation.
	 * 
	 * @param mean mean of the distribution that is sampled.
	 * @param standardDeviation standard deviation of the distribution that is sampled.
	 * @return a sample from a gaussian distribution, unless it is not possible with the given inputs, in which case it returns Double max.
	 */
	public static double sampleGaussian(float mean, float standardDeviation) {
		try {
			return new NormalDistributionImpl (mean, standardDeviation).sample();
		} catch (MathException e) {
			return Double.MAX_VALUE;
		}
	}
	
	/**
	 * Generates a random number between 0 and max
	 * 
	 * @param max the maximum value of afforementioned random number
	 * @return the number between 0 and max.
	 */
	public static double sampleUniform(float max) {
		Random r = new Random();
		
		return r.nextFloat() * max;
	}
	
}

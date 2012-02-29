package utils;

import java.util.Random;

public class RandomGenerator {
	static private Random r = new Random();
	
	/**
	 * Tries to return a sample from the Gaussian distribution with mean and standardDeviation.
	 * 
	 * @param mean mean of the distribution that is sampled.
	 * @param standardDeviation standard deviation of the distribution that is sampled.
	 * @return a sample from a gaussian distribution, unless it is not possible with the given inputs, in which case it returns Double max.
	 */
	public static float sampleGaussian(float mean, float standardDeviation) {
	  float u     = sampleUniform(1.0f);
	  float theta = sampleUniform((float) (2 * Math.PI));

	  // Fix to avoid infinity problem
	  if (u == 0) {
	    u = 0.0001f;
	  }

	  float r = (float) Math.sqrt(-2 * (float) Math.log(u));

	  float x = r * (float) Math.cos(theta);

	  return mean + standardDeviation * x;
	}
	
	/**
	 * Generates a random number between 0 and max
	 * 
	 * @param max the maximum value of afforementioned random number
	 * @return the number between 0 and max.
	 */
	public static float sampleUniform(float max) {
		return r.nextFloat() * max;
	}
}

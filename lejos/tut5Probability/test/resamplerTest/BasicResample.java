package resamplerTest;

import junit.framework.Assert;
import navigation.particles.Particle;
import navigation.particles.ParticleSet;

import org.junit.Before;
import org.junit.Test;

import MCLocalisation.Resampler;

public class BasicResample {

	private ParticleSet p;
	
	@Before
	public void setUp() throws Exception {
		p = new ParticleSet(4);
		p.set(0, new Particle(2f, 0f, 0f, 0.2f));
		p.set(1, new Particle(1f, 0f, 0f, 0.1f));
		p.set(2, new Particle(3f, 0f, 0f, 0.3f));
		p.set(3, new Particle(4f, 0f, 0f, 0.4f));
	}
	
	@Test
	public void test() {
		ParticleSet newSet = Resampler.resample(p, 4);
		Assert.assertTrue(newSet.size() == 4);
	}

}

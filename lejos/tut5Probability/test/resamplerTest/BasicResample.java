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
		p = new ParticleSet();
		p.add(new Particle(2d, 0d, 0d, 0.2f));
		p.add(new Particle(1d, 0d, 0d, 0.1f));
		p.add(new Particle(3d, 0d, 0d, 0.3f));
		p.add(new Particle(4d, 0d, 0d, 0.4f));
	}
	
	@Test
	public void test() {
		ParticleSet newSet = Resampler.resample(p, 4);
		Assert.assertTrue(newSet.size() == 4);
	}

}

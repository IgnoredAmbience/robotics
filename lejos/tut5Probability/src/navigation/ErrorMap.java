package navigation;

import lejos.nxt.LCD;
import interfacesThatBatesWants.Drawable;

/**
 * Specific type of ConcreteMovementMap which uses the Float as the type of PointData.
 * 
 * This ErrorMap describes a probability map which takes takes 0 to be "no chance the robot
 * is at this location" and 1 to be "The robot can only be at this location".
 * 
 * This ErrorMap uses 0 as the default probability.
 * 
 * The probabil
 * @author Chris Bates
 *
 */
public class ErrorMap extends ConcreteMovementMap<Double> implements Drawable {
	
	private final double EPSILON = 0.00001;
	
	public ErrorMap(int xSize, int ySize) {
		super(xSize, ySize, new Double(0));
		
	}

	@Override
	public void addPoint(int x, int y, Double p) {
		if (isProbability(p)) {
			super.addPoint(x, y, p);
		}
	}
	
	
	public boolean isProbability(Double p) {
		return p >= 0.0 && p <= 1.0;
	}
	
	/**
	 * Pass in a square to check for normalisation specified by (xMin, yMin) (xMax, yMax).
	 * 
	 * Normalisation check is performed which involves summing all the probabilities in the
	 * square and seeing if it adds up to 1.
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return 
	 */
	public boolean isNormalised(int xMin, int xMax, int yMin, int yMax) {
		
		if (!checkPoint(xMin, yMin) || !checkPoint(xMax, yMax)) {
			throw new IndexOutOfBoundsException("The square specified by (" + xMin + ", " + yMin + ") " +
					" and (" + xMax + ", " + yMax + ")  are out of the bounds of the map.");
		}
		double sum = 0;
		for (int x = xMin; x < xMax; x++) {
			for (int y = yMin; y < yMax; y++) {
				sum += this.getPoint(x, y);
			}
		}
		
		return (1 - sum) < this.EPSILON || (sum - 1) < this.EPSILON;
	}

	@Override
	public void drawToLCD() {
		
		LCD.clearDisplay();
		
		int imageWidth = this.getXSize();
		int imageHeight = this.getYSize();
		
		byte [] image = new byte [imageWidth*imageHeight];
		
		for (int i = 0; i < this.getXSize(); i++) {
			for (int j = 0; j < this.getYSize(); j++) {
				Double d = this.getPoint(i, j);
				if (d > 0) {
					image [i*this.getYSize() + j]= (byte) 1;
				}
			}
		}
		
		
		LCD.bitBlt(image, imageWidth, imageHeight, 0, 0, 0, 0, LCD.SCREEN_WIDTH, LCD.SCREEN_HEIGHT, LCD.ROP_COPY);
		
	}
	

}

package navigation;

/**
 * Describes a 2D map of PointType. The map size is fixed at creation time.
 * @author Chris Bates
 *
 * @param <PointType>
 */
public interface MovementMap<PointType> {
	/**
	 * Adds a point p onto the map at (x, y). Also used to set the point (x, y) to value p.
	 * @param x x coordinate to add
	 * @param y y coordinate to add
	 * @param p data to put in the map
	 * @throws IndexOutOfBoundsException thrown if x or y are not in the scope of the map
	 */
	public void addPoint(int x, int y, PointType p) throws IndexOutOfBoundsException;
	
	/** 
	 * @param x x coordinate to get
	 * @param y y coordinate to get
	 * @return the data at (x, y)
	 * @throws IndexOutOFBoundsException thrown if x or y are not in the scope of the map
	 */
	public PointType getPoint(int x, int y) throws IndexOutOfBoundsException;
	
	/**
	 * Resets the point at (x, y) to some default value;
	 * @param x x coordinate to reset
	 * @param y y coordinate to reset
	 * @throws IndexOutOfBoundsException thrown if x or y are not in the scope of the map
	 */
	public void resetPoint(int x, int y) throws IndexOutOfBoundsException;
	
	/**
	 * 
	 * @param x x coordinate to check
	 * @param y y coordinate to check
	 * @return true if a point is found at (x, y) else false, if x and y are not in the bounds, it returns false
	 */
	public boolean isPoint(int x, int y);
	
	public int getYSize();
	
	public int getXSize();
}

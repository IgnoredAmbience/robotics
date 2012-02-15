package map;

import java.util.ArrayList;

/**
 * Direct concrete implementation of MovementMap
 * 
 * @author Chris Bates
 *
 * @param <PointData>
 */
public class ConcreteMovementMap<PointData> implements MovementMap<PointData> {
	
	private ArrayList<ArrayList<PointData>> map;
	
	private int xSize;
	private int ySize;
	private PointData initialisingType;

	/**
	 * Creates a new concrete map, which represents a map of size (xSize, ySize)
	 * 
	 * @param xSize
	 * @param ySize
	 */
	public ConcreteMovementMap (int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.initialisingType = null;
		
		this.initiliseMap();
	}
	
	public ConcreteMovementMap (int xSize, int ySize, PointData initData) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.initialisingType = initData;
		
		this.initiliseMap();
		
	}
	
	
	protected void initiliseMap () {
		map = new ArrayList<ArrayList <PointData>> (this.ySize);
		
		for (int i = 0; i < this.ySize; i++) {
			ArrayList<PointData> col = new ArrayList<PointData> (this.xSize);
			for (int  j = 0; j < this.xSize; j++) {
				col.add(this.initialisingType);
			}
			map.add(col);
		}
	}
	
	@Override
	public void addPoint(int x, int y, PointData p) {
		if (!checkPoint(x, y)) throw new IndexOutOfBoundsException();
		
		map.get(y).set(x, p);
	}




	@Override
	public PointData getPoint(int x, int y) {
		if (!checkPoint(x, y)) throw new IndexOutOfBoundsException();
		
		return unsafeGetPoint(x, y);
	}
	

	
	
	
	@Override
	public void resetPoint(int x, int y) throws IndexOutOfBoundsException {
		if (!checkPoint(x, y)) throw new IndexOutOfBoundsException();
		
		addPoint(x, y, this.initialisingType);
	}

	@Override
	public boolean isPoint(int x, int y) {
		return checkPoint(x, y) && unsafeGetPoint(x, y) != null;
	}
	
	@Override
	public int getYSize() {
		return this.ySize;
	}

	@Override
	public int getXSize() {
		return this.xSize;
	}


	/**
	 * Checks the coordinates to see if they are between 0 and their corresponding size
	 * @param x
	 * @param y
	 */
	protected boolean checkPoint(int x, int y) {
		return (x > 0 && x < this.xSize && y > 0 && y < this.ySize);
	}
	
	/**
	 * Performs the get operation on the map but without checking for out of bounds exceptions
	 * @param x x coord
	 * @param y y coord
	 * @return the result of map.get(y).get(x)
	 */
	protected PointData unsafeGetPoint(int x, int y) {
		return map.get(y).get(x);
	}

	
	
}

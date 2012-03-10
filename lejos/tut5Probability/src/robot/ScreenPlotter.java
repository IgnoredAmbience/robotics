package robot;

import lejos.nxt.LCD;

public class ScreenPlotter {
	private final float unitsPerPixel;
	
	public ScreenPlotter(float s) {
		unitsPerPixel = s;
		LCD.clear();
	}
	
	public void plot(float x, float y) {
		LCD.setPixel(px(x), px(y), 1);
	}
	
	public void line(float x, float y, float x2, float y2) {
		int px = px(x);
		int py = px(y);
		int px2 = px(x2);
		int py2 = px(y2);
		
		for(int i = Math.min(px, px2); i <= Math.max(px, px2); i++) {
			for(int j = Math.min(py, py2); j <= Math.max(py, py2); j++) {
				LCD.setPixel(i, j, 1);
			}
		}
	}
	
	private int px(float x) {
		return (int) (x/unitsPerPixel);
	}
}

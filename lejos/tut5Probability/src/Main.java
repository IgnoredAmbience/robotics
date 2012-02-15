import map.ErrorMap;


public class Main {
	public static void main (String [] args) {
		 ErrorMap m = new ErrorMap (10, 10);
		 
		 m.addPoint(1, 5, 0.1);
		 
		 m.addPoint(9, 4, 0.5);
		 
		 m.drawToLCD();
	}
}

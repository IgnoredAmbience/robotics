import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import robot.Robot;
import robot.RotatingSonar;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;

public class PlaceRecognition implements CommandListener {
	static final String EXT = ".hist";
	String fn;
	List menu;
	RotatingSonar s = new RotatingSonar(MotorPort.A, SensorPort.S1);
	Display display;
	
	public void run() throws IOException {
		menu = new List("Test Components", Choice.IMPLICIT);
		menu.append("1", null);
		menu.append("2", null);
	  menu.append("3", null);
	  menu.append("4", null);
	  menu.append("5", null);
	  menu.append("Detect", null);
	  menu.setCommandListener(this);
		display = Display.getDisplay();
    display.setCurrent(menu);
    display.show(true);

		Button.waitForAnyPress();
	}
	public static void main (String [] args) throws IOException {
		new PlaceRecognition().run();
	}

	public void commandAction(Command c, Displayable d) {
		if(menu.getSelectedIndex() == 5) {
			display.quit();
			LCD.setAutoRefresh(true);
			LCD.clear();
			detect();
			Button.waitForAnyPress();
		} else {
  		fn = menu.getString(menu.getSelectedIndex());
  		
  		byte[] b = s.scanFrequencies(0, 360, 5);
  		s.rotateTo(0);
  		
  		File f = new File(fn + EXT);
  		try {
  			f.createNewFile();
    		FileOutputStream fo = new FileOutputStream(f);
    		fo.write(b);
    		fo.close();
  		} catch (Exception e) {}
		}
	}
	
	public void detect() {
		Sound.beepSequence();
		byte[] current = s.scanFrequencies(0, 360, 5);
		s.rotateTo(0);
		
		for(File f : File.listFiles()) {
			if(f == null) break;
			if(f.getName().indexOf(EXT) == -1) continue;
			
			try {
				FileInputStream fi = new FileInputStream(f);
  			byte[] cmp = new byte[256];
  			fi.read(cmp);
  			fi.close();
  			System.out.println(f.getName() + " " + ssq(current, cmp));
  			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private int ssq(byte[] current, byte[] cmp) {
		int[] c = castByteArray(current);
		int[] f = castByteArray(cmp);
		int accum = 0;
		for(int i = 0; i < current.length; i++) {
			accum += Math.pow(c[i] - f[i], 2);
		}
		return accum;
	}
	
	int[] castByteArray(byte[] arr) {
		int[] intArr = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			intArr[i] = arr[i] < 0 ? arr[i] + 256 : arr[i];
		}
		
		return intArr;
	}
}

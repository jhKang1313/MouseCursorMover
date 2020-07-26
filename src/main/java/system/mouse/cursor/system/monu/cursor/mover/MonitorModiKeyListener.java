package system.mouse.cursor.system.monu.cursor.mover;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

public class MonitorModiKeyListener implements HotKeyListener{
	private GraphicsDevice monitor;
	private Dimension monitorCenterLocation = new Dimension();
	private Rectangle monitorBound;
	private Robot mouseCursor;
	public MonitorModiKeyListener(GraphicsDevice monitor, Robot mouseCursor){
		try{
			this.monitor = monitor;
			this.monitorBound = this.monitor.getDefaultConfiguration().getBounds();
			monitorCenterLocation.setSize(monitorBound.x + (this.monitor.getDisplayMode().getWidth() / 2), monitorBound.y + (this.monitor.getDisplayMode().getHeight() / 2));
			this.mouseCursor = new Robot(monitor);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void onHotKey(HotKey hotKey) {
		mouseCursor.mouseMove(monitorCenterLocation.width, monitorCenterLocation.height);
		mouseCursor.mouseMove(monitorCenterLocation.width, monitorCenterLocation.height);
		//mouseCursor.mouseMove(2560, 360);
		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double y = MouseInfo.getPointerInfo().getLocation().getY();
		System.out.println("given loaction value : " + monitorCenterLocation.width + ", " + monitorCenterLocation.height);
		System.out.println("set location value : " + x + ", " + y);
		System.out.println("----------------------------------------");
	}
}

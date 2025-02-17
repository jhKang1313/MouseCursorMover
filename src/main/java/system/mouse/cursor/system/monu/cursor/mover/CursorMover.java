package system.mouse.cursor.system.monu.cursor.mover;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

import com.tulskiy.keymaster.common.Provider;

public class CursorMover{
	final Provider provider = Provider.getCurrentProvider(false);
	private Robot mouseCursor;
	private Map<Integer, String> appMap = null;
	public CursorMover(){
		try{
			mouseCursor = new Robot();
			GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
			for(int i = 0 ; i < gd.length; i++){
				for(int j = 0 ; j < gd.length - (i + 1) ; j++){
					if(gd[j].getDefaultConfiguration().getBounds().x > gd[j + 1].getDefaultConfiguration().getBounds().x){
						GraphicsDevice temp = gd[j+1];
						gd[j+1] = gd[j];
						gd[j] = temp;
					}
				}
			}
			for(int i = 0 ; i < gd.length ; i++){
				provider.register(KeyStroke.getKeyStroke("alt " + (i + 1)), new MonitorModiKeyListener(gd[i], mouseCursor));
			}

			appMap = new HashMap<Integer, String>();
			appMap.put(1, "Safari");
			appMap.put(2, "KakaoTalk");
			appMap.put(3, "Calendars");
			appMap.put(4, "IntelliJ IDEA");
			appMap.put(5, "Reminders");
			appMap.put(6, "GitKraken");

			for(Integer key : appMap.keySet()){
				provider.register(KeyStroke.getKeyStroke("ctrl meta " + key), new AppLauncherKeyListener(key, appMap.get(key)));
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		try{
			CursorMover mover = new CursorMover();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

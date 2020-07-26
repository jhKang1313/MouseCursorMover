package system.mouse.cursor.system.monu.cursor.mover;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;

import javax.swing.KeyStroke;

import com.tulskiy.keymaster.common.Provider;

public class CursorMover{
	final Provider provider = Provider.getCurrentProvider(false);
	private Robot mouseCursor;
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

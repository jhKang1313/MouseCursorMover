package system.mouse.cursor.system.monu.cursor.mover;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

import java.io.IOException;

public class AppLauncherKeyListener implements HotKeyListener {
  private Integer key = null;
  private String appName = null;
  private String APP_PATH = "/Applications/";
  public AppLauncherKeyListener(Integer key, String appName) {
    this.key = key;
    this.appName = appName;
  }

  public void onHotKey(HotKey hotKey) {
    try {
      String appFullPath = APP_PATH + appName + ".app";
      new ProcessBuilder("open", "-a", appFullPath).start();
      new ProcessBuilder("open", "-a", appName).start();
      System.out.println("ctrl cmd "+ this.key + " on press");
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}

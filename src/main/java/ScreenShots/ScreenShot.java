package ScreenShots;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenShot {
    public static File getHomeDir() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getHomeDirectory();
    }

    public static BufferedImage grabScreen(int x, int y, int width, int hight) {
        try {
            return new Robot().createScreenCapture(new Rectangle(x, y, width, hight));
        } catch (SecurityException e) {
        } catch (AWTException e) {
        }
        return null;
    }

}

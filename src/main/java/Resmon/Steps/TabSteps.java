package Resmon.Steps;

import Resmon.Graphic;
import Resmon.TabPanel;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.IOException;

public class TabSteps {
    public static void makeSceenshotByTab(WiniumDriver driver, String nameTab, String path) throws InterruptedException, IOException {
        TabPanel tabPanel = new TabPanel(driver);
        tabPanel.clickTab(nameTab);// клик по табу
        Thread.sleep(3000);
        //делаем скриншот
        Graphic.makeSceenshotsByTab(path,Graphic.getGraphicList(driver));
    }
}

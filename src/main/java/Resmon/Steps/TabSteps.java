package Resmon.Steps;

import Resmon.Objects.ScrollBarInMonitorWindow;
import Resmon.Objects.TabPanel;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.IOException;

public class TabSteps {
    public static void makeSceenshotByTab(WiniumDriver driver, String nameTab, String path) throws InterruptedException, IOException {
        TabPanel tabPanel = new TabPanel(driver);
        tabPanel.clickTab(nameTab);// клик по табу
        Thread.sleep(3000);
        //делаем скриншот
        GraphicSteps.makeSceenshotsByTab(path, GraphicSteps.getGraphicList(driver), new ScrollBarInMonitorWindow(driver).getButtonDown());
    }
}

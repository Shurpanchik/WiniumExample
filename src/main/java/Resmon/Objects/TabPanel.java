package Resmon.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.IOException;

public class TabPanel {

    private WebElement tabPanel;
    private WiniumDriver driver;

    public TabPanel(WiniumDriver driver) {
        this.driver = driver;
         tabPanel = driver.findElementByClassName("NativeHWNDHost")
                        .findElement(By.className("Window"))
                        .findElement(By.className("WdcTab"));
    }

    public void clickTab(String nameTab){
        tabPanel.findElement(By.name(nameTab)).click();
    }

    public WebElement getTabPanel() {
        return tabPanel;
    }

}


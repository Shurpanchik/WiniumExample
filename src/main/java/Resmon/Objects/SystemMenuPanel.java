package Resmon.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

public class SystemMenuPanel {

    private WebElement menuPanel;
    private WebElement monitoringTab;
    private WiniumDriver driver;

    public SystemMenuPanel(WiniumDriver driver, String automationId) {
        this.driver = driver;
        menuPanel = driver.findElement(By.xpath("//*[@AutomationId='MenuBar']"));
        monitoringTab = menuPanel.findElement(By.xpath("//*[@AutomationId='Item 2']"));
    }

    /*
        метод остановки графиков
     */
    public void stopMonitoring(){
        monitoringTab.click();
        monitoringTab.findElement(By.name("Остановить мониторинг")).click();
    }
}

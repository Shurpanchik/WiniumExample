package Resmon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

public class Graphic {

    private WebElement graphic;
    private WiniumDriver driver;

    public Graphic(WiniumDriver driver, String automationId) {
        this.driver = driver;
        String xpathAutomationId = "//*[@AutomationId='"+automationId+"']";
        graphic = driver.findElementByClassName("NativeHWNDHost")
                .findElement(By.className("Window"))
                .findElement(By.xpath(xpathAutomationId));
    }
    public Graphic(WiniumDriver driver, String automationId, String name) {
        this.driver = driver;
        String xpathGraph= "//*[@AutomationId='"+automationId+"' and @ClassName='"+name+"']";
        graphic = driver.findElementByClassName("NativeHWNDHost")
                .findElement(By.className("Window"))
                .findElement(By.xpath(xpathGraph));
              //  .findElement(By.xpath(xpathAutomationId));
    }

    public WebElement getGraphic() {
        return graphic;
    }


}

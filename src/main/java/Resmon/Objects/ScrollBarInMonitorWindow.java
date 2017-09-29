package Resmon.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

public class ScrollBarInMonitorWindow {

    private WebElement scroll;
    private WebElement buttonUp;
    private WebElement buttonDown;


    public ScrollBarInMonitorWindow(WiniumDriver driver){
        scroll = driver.findElementByClassName("NativeHWNDHost")
                                .findElement(By.className("Window"))
                                .findElements(By.className("CCVScrollBar")).get(1);
        buttonUp = scroll.findElement(By.name("Строка вверх"));
        buttonDown = scroll.findElement(By.name("Строка вниз"));
    }

    public WebElement getScroll() {
        return scroll;
    }

    public WebElement getButtonUp() {
        return buttonUp;
    }

    public WebElement getButtonDown() {
        return buttonDown;
    }
}

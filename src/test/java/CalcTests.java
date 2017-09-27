import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import java.io.File;
import java.io.IOException;

public class CalcTests {
    private WiniumDriverService service;
    private WiniumDriver driver;

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.winium.desktop.driver","src\\main\\resources\\Winium.Desktop.Driver.exe");
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\calc.exe");

        service = new WiniumDriverService.Builder()
                .usingDriverExecutable(new File("src\\main\\resources\\Winium.Desktop.Driver.exe"))
                .usingPort(9999)
                .withVerbose(true)
                .withSilent(false)
                .buildDesktopService();


        if (!service.isRunning()){
            service.start();
            System.out.println("Сервер запущен");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver = new WiniumDriver(service, options);

    }
    @After
    public void finish(){
        service.stop();
        driver.close();
    }
    @Test
    public void firstTest(){

       // WebElement window = driver.findElement(By.className("CalcFrame"));

        driver.findElement(By.id("132")).click();//2
        driver.findElement(By.id("93")).click();//+
        driver.findElement(By.id("134")).click();//4
        driver.findElement(By.id("92")).click();//*
        driver.findElement(By.id("134")).click();//4
        driver.findElement(By.id("121")).click();//=

        driver.close();
    }
    @Test

    public void twoTest(){
      //  driver.findElement(By.xpath("//*[@AutomationId='num2Button']")).clickTab();//2
        /*
        driver.findElementById("num4Button").
        driver.findElementByName("Два").clickTab();//2
        driver.findElementByName("Плюс").clickTab();//+
        driver.findElementByName("Четыре").clickTab();//4
        driver.findElementByName("Умножить на").clickTab();//*
        driver.findElementByName("Четыре").clickTab();//4
        driver.findElementByName("Равно").clickTab();//=

        driver.close();
        */
    }


}

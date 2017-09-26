import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import java.io.File;
import java.io.IOException;

public class ResmonTests {

    private WiniumDriverService service;
    private WiniumDriver driver;

    @Before
    public void setUp()throws IOException{
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\resmon.exe");
        System.setProperty("file.encoding", "UTF-8");
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

        driver = new WiniumDriver(service, options);
    }

    @Test
    public void resmonTest() throws InterruptedException {
        System.out.println("Hi");
       // driver.findElementByLinkText("Обзор").click();
        driver.findElement(By.partialLinkText("Обзор")).click();
      //  driver.findElementByClassName("NativeHWNDHost");;
      //  driver.findElement(By.xpath("//*[@AutomationId='tabctrl']")).click();
       // driver.findElementByName("Обзор").click();
        System.out.println("Buy");
    }

    @After
    public void tearDown(){
        service.stop();
    }
}

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class NotepadTest {

    private WiniumDriverService service;
    private  WiniumDriver driver;

    @Before
    public void setUp() throws IOException, InterruptedException {
        System.setProperty("webdriver.winium.desktop.driver","src\\main\\resources\\Winium.Desktop.Driver.exe");
        System.setProperty("file.encoding", "UTF-8");
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\notepad.exe");
        service = new WiniumDriverService.Builder()
                .usingDriverExecutable(new File("src\\main\\resources\\Winium.Desktop.Driver.exe"))
                .usingPort(9999)
                .withVerbose(true)
                .withSilent(false)
                .buildDesktopService();

        driver = new WiniumDriver(service, options);

        if (!service.isRunning()){
            service.start();
            System.out.println("Сервер запущен");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
     @After
     public void finish(){
            service.stop();
            driver.close();
     }
    @Test
    public void firstTest() throws IOException {
        if (!service.isRunning()){
            service.start();
            System.out.println("Сервер запущен");
        }
       // driver.findElementByClassName("Edit").sendKeys("This is sample test");
        driver.findElementByName("Формат").click();
        driver.findElementByName("Вид").click();
        driver.findElementByName("Справка").click();

        driver.findElement(By.xpath("//*[@ClassName='Edit']")).sendKeys("This is sample test");
        driver.findElement(By.xpath("//*[@ControlType='ControlType.MenuBar'"));
        driver.findElement(By.xpath("//*[@AutomationId='MenuBar']"));
               // .findElement(By.xpath("//*[@AutomationId='Item 1']"))
               // .clickTab();
        driver.findElement(By.xpath("//*[@AutomationId='Item 1']")).click();
        driver.findElement(By.xpath("//*[@AutomationId='Item 2']")).click();
        driver.findElement(By.xpath("//*[@AutomationId='Item 3']")).click();
        driver.findElement(By.xpath("//*[@AutomationId='Item 4']")).click();
        driver.findElement(By.xpath("//*[@AutomationId='Item 5']")).click();

        service.stop();
    }

    @Test
    public void test() throws IOException {
        DesktopOptions options= new DesktopOptions();
        options.setApplicationPath("C:\\WINDOWS\\system32\\notepad.exe");
        try{
            WiniumDriver driver=new WiniumDriver(new URL("http://localhost:4444"),options);
            driver.findElementByClassName("Edit").sendKeys("This is sample test");
            //   driver.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            Assert.assertEquals(true,false);
        }
    }

    @Test
    public void testNotepad3() throws InterruptedException, IOException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.name("Файл"))).build().perform();

        driver.findElement(By.name("Файл")).click();
        driver.findElement(By.name("Сохранить как...")).click();
        Thread.sleep(2000);
        driver.findElement(new By.ByClassName("#32770")).click();
        //    window2.findElement(new By.ByClassName("Edit")).sendKeys("kdlfklsd");
        //   window.findElement(By.name("Don't Save")).clickTab();
    }

}

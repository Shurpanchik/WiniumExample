import Resmon.Graphic;
import Resmon.Steps.TabSteps;
import Resmon.TabPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;

import static ScreenShots.ScreenShot.grabScreen;

public class ResmonTests {

    private WiniumDriverService service;
    private WiniumDriver driver;
    private DesktopOptions options;
    private String basePath;
    private String nameReportDirectory;


    @Before
    public void setUp() throws IOException, InterruptedException {
        options = new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\resmon.exe");
        System.setProperty("file.encoding", "UTF-8");
        service = new WiniumDriverService.Builder()
                .usingDriverExecutable(new File("src\\main\\resources\\Winium.Desktop.Driver.exe"))
                .usingPort(9999)
                .withVerbose(true)
                .withSilent(false)
                .buildDesktopService();

        if (!service.isRunning()) {
            service.start();
            System.out.println("Сервер запущен");
        }

        driver = new WiniumDriver(service, options);
        Thread.sleep(5000);
        driver.findElementByXPath("//*[@AutomationId='Maximize']").click();
        basePath = "D:\\Users\\user\\Desktop\\Projects\\LeanFT\\Winium1\\target\\reports\\";
        nameReportDirectory = "report" + Calendar.getInstance().getTimeInMillis();

    }


    /*
     *   Тест нахождения всех графиков по всем табам
    */
    @Test
    public void AllTabGraphicTest() throws IOException, InterruptedException {

        TabSteps.makeSceenshotByTab(driver,"Обзор",basePath + nameReportDirectory + "\\browser");// обзор
        TabSteps.makeSceenshotByTab(driver,"ЦП",basePath + nameReportDirectory + "\\cp");// цп
        TabSteps.makeSceenshotByTab(driver,"Память",basePath + nameReportDirectory + "\\memory");//память
        TabSteps.makeSceenshotByTab(driver,"Сеть",basePath + nameReportDirectory + "\\network" );//сеть
        TabSteps.makeSceenshotByTab(driver, "Диск", basePath + nameReportDirectory + "\\disk");// диск

    }

    @After
    public void tearDown() {
        service.stop();
      //  driver.quit();
    }
}

import Resmon.Graphic;
import Resmon.TabPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static ScreenShots.ScreenShot.grabScreen;

public class ResmonTests {

    private WiniumDriverService service;
    private WiniumDriver driver;
    private DesktopOptions options;
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

        Thread.sleep(5000);
        if (!service.isRunning()){
             service.start();
            System.out.println("Сервер запущен");
        }

        driver = new WiniumDriver(service, options);
        Thread.sleep(5000);
        driver.findElementByXPath("//*[@AutomationId='Maximize']").click();
    }

    /*
    * Тест клика по табу ЦП и создание скриншотов графиков в разделе ЦП
     */

    @Test
    public void CpGraphicTest() throws InterruptedException, IOException {
        System.out.println("Hi");

        // кликаем по табу ЦП
        TabPanel tabPanel = new TabPanel(driver);
        tabPanel.clickTab("ЦП");// клик по табу ЦП
        Thread.sleep(5000);
        System.out.println(tabPanel.getTabPanel().getSize());

        // поиск всех графиков
        Graphic graphicCPAll = new Graphic(driver, "cpuGraph_cputab");
        Graphic graphicCPService = new Graphic(driver,"serviceGraph");
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width-graphicCPAll.getGraphic().getSize().getWidth());
        int х = Toolkit.getDefaultToolkit().getScreenSize().width-graphicCPAll.getGraphic().getSize().getWidth();

        ImageIO.write(
                    grabScreen( Toolkit.getDefaultToolkit().getScreenSize().width-graphicCPAll.getGraphic().getSize().getWidth()+50,
                            100,
                            graphicCPAll.getGraphic().getSize().getWidth(), graphicCPAll.getGraphic().getSize().getHeight()),
                "png", new File("D:\\Users\\user\\Desktop\\Projects\\LeanFT\\Winium1\\target\\report\\", "cp.png"));

        ImageIO.write(
                grabScreen( Toolkit.getDefaultToolkit().getScreenSize().width-graphicCPService.getGraphic().getSize().getWidth()+50,
                        100+graphicCPService.getGraphic().getSize().height,
                        graphicCPAll.getGraphic().getSize().getWidth(), graphicCPService.getGraphic().getSize().getHeight()),
                "png", new File("D:\\Users\\user\\Desktop\\Projects\\LeanFT\\Winium1\\target\\report\\", "graphicCPService.png"));

        System.out.println("Buy");
    }

    /*
    * Тест клика по табу ЦП и создание скриншотов графиков в разделе ЦП
     */

    @Test
    public void BrowserTabGraphicTest() throws InterruptedException, IOException{
        // кликаем по табу Обзор
        TabPanel tabPanel = new TabPanel(driver);
        tabPanel.clickTab("Обзор");// клик по табу ЦП
    }
    @After
    public void tearDown(){
        service.stop();
      //  driver.quit();
    }
}

package Resmon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static ScreenShots.ScreenShot.grabScreen;

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

    /*
    *  Получение сдвига по y для создания скриншота
    * */
    public static int getShiftGraphicScreenshotByY(int cntgraphicInWindowMonitoring) {
        if (cntgraphicInWindowMonitoring < 5) {
            return cntgraphicInWindowMonitoring;
        } else {
            return 5;
        }
    }

    /*
         получение названия графика
     */
    public static String getScreenNameGraph(WebElement graph) {
        String nameGraph="defaultName";
        if (graph.getAttribute("Name") == null || graph.getAttribute("Name") == "") {
            return Calendar.getInstance().getTimeInMillis() + ".png";
        } else {
            try {
              nameGraph =  String.format("%s.png", graph.getAttribute("Name").replaceAll("![А-яA-z0-9]]", ""));
              //TODO заглушка на слеш
              if(nameGraph.contains("/")){nameGraph = nameGraph.replaceAll("/","");}
              //TODO заглушка на звездочку
                if(nameGraph.contains("*")){nameGraph = nameGraph.replaceAll("\\*","");}
            }
            catch (Exception ex){
               nameGraph = Calendar.getInstance().getTimeInMillis() + ".png";
            }
            finally {
                return nameGraph;
            }
        }
    }
    /*
        создание скриншотов графиков в заданном репозитории
     */
    public static void makeSceenshotsByTab(String pathDirectory, List <WebElement> graphicList) throws IOException {
        // создаем директорию, в которую положим скриншоты из таба network
        File path = new File(pathDirectory);
        path.mkdirs();

        int cntgraphicInWindowMonitoring = 1;

        // определяем прямоугольник на экране и делаем скриншот
        for (WebElement graph : graphicList){
            int shiftY = Graphic.getShiftGraphicScreenshotByY(cntgraphicInWindowMonitoring);
            System.out.println(shiftY);
            int x = Toolkit.getDefaultToolkit().getScreenSize().getSize().width - graph.getSize().getWidth()-50;
            int y = (graph.getSize().getHeight()+20)*shiftY;
            int w = graph.getSize().getWidth()+150;
            int h = graph.getSize().getHeight()+50;
            String screen = Graphic.getScreenNameGraph(graph);
            ImageIO.write(
                    grabScreen(x,y,w,h),
                    "png", new File(pathDirectory, screen));
            cntgraphicInWindowMonitoring++;
        }
    }

    /*
        Получение всех графиков в текущей вкладке в окне мониторинга ресурсов
     */
    public static List<WebElement> getGraphicList(WiniumDriver driver) throws InterruptedException {
        List<WebElement> graphicList = driver.findElementByClassName("NativeHWNDHost")
                .findElement(By.className("Window"))
                .findElements(By.className("graph"));
        return  graphicList;
    }
}

package Resmon.Steps;

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

public class GraphicSteps {


    /*
    *  Получение сдвига по y для создания скриншота
    */

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
            nameGraph = String.format("%s.png", graph.getAttribute("Name").replaceAll("[/*.():&?]", ""));
            return  nameGraph;
        }
    }
    /*
        создание скриншотов графиков в заданном репозитории
     */
    public static void makeSceenshotsByTab(String pathDirectory, List <WebElement> graphicList, WebElement scrollBar) throws IOException {
        // создаем директорию, в которую положим скриншоты из таба network
        File path = new File(pathDirectory);
        path.mkdirs();

        int cntgraphicInWindowMonitoring = 1;

        // определяем прямоугольник на экране и делаем скриншот
        for (WebElement graph : graphicList){
            int shiftY = GraphicSteps.getShiftGraphicScreenshotByY(cntgraphicInWindowMonitoring);
            System.out.println(shiftY);
            int x = Toolkit.getDefaultToolkit().getScreenSize().getSize().width - graph.getSize().getWidth()-50;
            int y = (graph.getSize().getHeight()+20)*shiftY;
            int w = graph.getSize().getWidth()+150;
            int h = graph.getSize().getHeight()+50;
            String screen = GraphicSteps.getScreenNameGraph(graph);
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

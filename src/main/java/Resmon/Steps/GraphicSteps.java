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

    public static int getShiftGraphicScreenshotByY(int cntgraphicInWindowMonitoring, WebElement graphic) {
        int h = graphic.getSize().getHeight();
        switch (cntgraphicInWindowMonitoring){
            case 1: return h+10;
            case 2: return 2*h+30;
            case 3: return 3*h+40;
            case 4: return 4*h+50;
            default:return 5*h+100;
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
    public static void makeSceenshotsByTab(String pathDirectory, List <WebElement> graphicList, WebElement scrollButtonDown) throws IOException, InterruptedException {
        // создаем директорию, в которую положим скриншоты из таба network
        File path = new File(pathDirectory);
        path.mkdirs();

        // определяем прямоугольник на экране и делаем скриншот
        for (int i=0;i<graphicList.size();i++){
            WebElement graph = graphicList.get(i);

            if(i==graphicList.size()-1){
                makeGraphicVisieble(null,scrollButtonDown);
            }else {
                makeGraphicVisieble(graphicList.get(i+1),scrollButtonDown);
            }

            int x = Toolkit.getDefaultToolkit().getScreenSize().getSize().width - graph.getSize().getWidth()-50;
            int y = getShiftGraphicScreenshotByY(i+1,graph);
            int w = graph.getSize().getWidth()+150;
            int h = graph.getSize().getHeight()+150;
            String screen = GraphicSteps.getScreenNameGraph(graph);
            ImageIO.write(
                    grabScreen(x,y,w,h),
                    "png", new File(pathDirectory, screen));
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

    /*
        Прокрутить график до зоны видимости
     */
    private static void makeGraphicVisieble(WebElement nextGraph, WebElement scrollButtonDown) throws InterruptedException {
        if(nextGraph==null){
            for(int i=0;i<14;i++){
                scrollButtonDown.click();
              //  Thread.sleep(1000);
            }
        }
        else {
            while (!nextGraph.isDisplayed()) {
                scrollButtonDown.click();
                Thread.sleep(1000);
            }
        }
    }

}

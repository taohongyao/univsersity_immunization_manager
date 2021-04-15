package daycare;

import daycare.console.DayCare;
import daycare.gui.StartWindows;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    public static void dataInputTest(){
        DayCare dayCare=new DayCare();
        dayCare.demo2();
    }

    @Test
    public static void dataOutputTest(){
        DayCare dayCare=new DayCare();
        dayCare.demo();
    }

    public static void GUITest(){
        DayCare dayCare = new DayCare();
        dayCare.readFromFile();
        new StartWindows(dayCare);
    }

}

package daycare;


import daycare.console.DayCare;
import daycare.gui.StartWindows;

public class Application {

    public static void main(String [] args){
        DayCare dayCare = new DayCare();
        dayCare.readFromFile();
        new StartWindows(dayCare);
    }
}

package restaurant.kitchen;

import java.util.Observable;
import java.util.Observer;

public class Cook implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Start cooking - " + arg);
    }

    @Override
    public String toString() {
        return name;
    }
}
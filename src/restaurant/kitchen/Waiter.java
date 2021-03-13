package restaurant.kitchen;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg + " was cooked by " + o);
    }
}

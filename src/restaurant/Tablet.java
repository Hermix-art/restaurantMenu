package restaurant;

import restaurant.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;// let us know where the order came from

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){// creates order for chosen dishes
        Order order = null;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());

            if(!order.isEmpty()){
                setChanged();
                notifyObservers(order);
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE,"Console is unavailable.");
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}

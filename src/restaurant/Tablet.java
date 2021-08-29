package restaurant;

import restaurant.ad.AdvertisementManager;
import restaurant.ad.NoVideoAvailableException;
import restaurant.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Herman Kulik
 */
public class Tablet extends Observable {
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;// let us know where the order came from

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {// creates order for chosen dishes
        Order order = null;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());

            if (!order.isEmpty()) {
                setChanged();
                notifyObservers(order);

                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
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

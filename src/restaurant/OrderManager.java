package restaurant;

import restaurant.kitchen.Order;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Manages the orders, and allows cooks to take the orders from queue
 *
 * @author Herman Kulik
 */
public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        orderQueue.add(order);
    }
}

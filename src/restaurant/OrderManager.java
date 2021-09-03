package restaurant;

import restaurant.kitchen.Cook;
import restaurant.kitchen.Order;
import restaurant.statistic.StatisticManager;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Manages the orders, and allows cooks to take the orders from queue
 *
 * @author Herman Kulik
 */
public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();


    public OrderManager() {
        Set<Cook> cooks = StatisticManager.getInstance().getCooks();
        Thread daemonThread = new Thread(() -> {
            Set<Cook> cooksSet = StatisticManager.getInstance().getCooks();
            try {
                while (true) {
                    Thread.sleep(10);
                    if (!orderQueue.isEmpty()) {
                        for (Cook cook : cooksSet) {
                            if (!cook.isBusy()) {
                                cook.startCookingOrder((orderQueue.poll())); // cook cooks not in a separate thread
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
            }


        });

        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        orderQueue.add(order);
    }
}

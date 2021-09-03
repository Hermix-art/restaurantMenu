package restaurant.kitchen;

import restaurant.ConsoleHelper;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Herman Kulik
 */
public class Cook extends Observable {
    private boolean busy;
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    /**
     * Processes the order (by printing it to console), registers the order as an EventData (CookedOrderEventDataRow)
     * in StatisticManager. Let's the waiter know about the order (order is prepared)
     *
     * @param order the order taken from the queue of orders
     */
    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StatisticManager // statistic registration
                .getInstance()
                .register(new CookedOrderEventDataRow(order.getTabletName(), name, order.getTotalCookingTime() * 60, order.getDishes()));

        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }
}
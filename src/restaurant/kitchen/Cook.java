package restaurant.kitchen;

import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Herman Kulik
 */
public class Cook extends Observable {
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
        System.out.println("Start cooking - " + order);
        StatisticManager // statistic registration
                .getInstance()
                .register(new CookedOrderEventDataRow(order.getTabletName(), name, order.getTotalCookingTime() * 60, order.getDishes()));

        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString() {
        return name;
    }
}
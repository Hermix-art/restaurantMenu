package restaurant.kitchen;

import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Herman Kulik
 */
public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Start cooking - " + arg);

        Order order = (Order) arg;
        StatisticManager // statistic registration
                .getInstance()
                .register(new CookedOrderEventDataRow(order.getTabletName(), name, order.getTotalCookingTime() * 60, order.getDishes()));

        setChanged();
        notifyObservers(arg);
    }

    @Override
    public String toString() {
        return name;
    }
}
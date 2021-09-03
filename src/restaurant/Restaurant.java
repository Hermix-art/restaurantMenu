package restaurant;

import restaurant.kitchen.Cook;
import restaurant.kitchen.Waiter;

/**
 * @author Herman Kulik
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        Tablet tablet = new Tablet(1);
        Cook cook = new Cook("Johns the cook");
        Waiter waiter = new Waiter();

        tablet.addObserver(cook);
        cook.addObserver(waiter);

        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}

package restaurant;

import restaurant.kitchen.Cook;
import restaurant.kitchen.Order;
import restaurant.kitchen.Waiter;
import restaurant.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Herman Kulik
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        Cook cookJohn = new Cook("Johns the cook");
        Cook cookRyan = new Cook("Ryan the cook");

        Waiter waiter = new Waiter();

        cookJohn.addObserver(waiter);
        cookRyan.addObserver(waiter);

        StatisticManager.getInstance().register(cookJohn);
        StatisticManager.getInstance().register(cookRyan);

        List<Tablet> tablets = new ArrayList<>();
        OrderManager orderManager = new OrderManager();

        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i + 1);
            tablet.addObserver(orderManager);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tablets, 500);
        Thread thread = new Thread(generatorTask);
        thread.start();
        try {
            Thread.sleep(3000);
            thread.interrupt();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}

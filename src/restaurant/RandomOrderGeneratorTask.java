package restaurant;

import java.util.List;

/**
 * Responsible for generating the order with a delay, via random tablet, provided in a constructor
 *
 * @author Herman Kulik
 */
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int delay;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.delay = interval;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Tablet tablet = tablets.get((int) (Math.random() * tablets.size()));

            tablet.createOrder();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignore) {
                return;
            }
        }

    }
}

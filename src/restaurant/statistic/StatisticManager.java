package restaurant.statistic;

import restaurant.statistic.event.EventDataRow;

/**
 * Gathers restaurant statistics and hold them
 *
 * @author Herman Kulik
 */
public class StatisticManager {
    private static StatisticManager statisticManager;

    private StatisticManager() {

    }

    public static StatisticManager getInstance() {
        if (statisticManager == null) {
            statisticManager = new StatisticManager();
        }
        return statisticManager;
    }

    /**
     * Registers statistic events in the repository
     *
     * @param data new event to be registered
     */
    public void register(EventDataRow data) {

    }
}

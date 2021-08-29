package restaurant.statistic;

import restaurant.statistic.event.EventDataRow;
import restaurant.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gathers restaurant statistics and hold them
 *
 * @author Herman Kulik
 */
public class StatisticManager {
    private StatisticStorage statisticStorage = new StatisticStorage();
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

    /**
     * Represents a storage of statistic data. Uses hashmap to keep events (EventType enum - key, List<EventDataRow> - value)
     */
    private class StatisticStorage {
        private final Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            this.storage = new HashMap<>();
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<>());
            }
        }
    }
}

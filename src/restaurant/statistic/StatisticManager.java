package restaurant.statistic;

import restaurant.kitchen.Cook;
import restaurant.statistic.event.CookedOrderEventDataRow;
import restaurant.statistic.event.EventDataRow;
import restaurant.statistic.event.EventType;
import restaurant.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Gathers restaurant statistics and hold them
 *
 * @author Herman Kulik
 */
public class StatisticManager {
    private Set<Cook> cooks = new HashSet<>();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private static StatisticManager statisticManager;

    private StatisticManager() {
    }

    /**
     * Helps to implement singleton pattern for StatisticManager
     *
     * @return StatisticManager object
     */
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
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        if (cook != null) {
            cooks.add(cook);
        }
    }

    /**
     * Calculates summary profit from advertisement in repository {@link #statisticStorage}
     *
     * @return Map<Date, Double> with sum of profit for each separate day
     */
    public Map<String, Double> calculateProfitForAdvertisement() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Double> profits = new HashMap<>();

        List<EventDataRow> videos = statisticStorage.get().get(EventType.SELECTED_VIDEOS);
        for (EventDataRow event : videos) {
            double sum = ((VideoSelectedEventDataRow) event).getAmount();
            String date = dateFormat.format(event.getDate());

            profits.putIfAbsent(date, 0.0);
            profits.put(date, profits.get(date) + (sum/100.0));
        }

        return profits;
    }

    /**
     * Calculates workload of cooks in the kithen.
     * Places the data into a new Map, which contains the date, the name of a cook and the number of minutes,
     * he worked on a specific date;
     *
     * @return a HashMap, filled with data
     */
    public Map<String, TreeMap<String, Integer>> calculateWorkLoadForCook() {
        List<EventDataRow> cookings = statisticStorage.get().get(EventType.COOKED_ORDER);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, TreeMap<String, Integer>> sortedData = new HashMap<>();

        for (EventDataRow event : cookings) {
            String date = dateFormat.format(event.getDate());
            sortedData.putIfAbsent(date, new TreeMap<>());
        }

        for (EventDataRow event : cookings) {
            String date = dateFormat.format(event.getDate());
            String name = ((CookedOrderEventDataRow) event).getCookName();
            int minutes = (int)(Math.round(((CookedOrderEventDataRow) event).getCookingTimeSeconds() / 60.0));

            TreeMap<String, Integer> insideMap = sortedData.get(date);
            insideMap.putIfAbsent(name, 0);
            insideMap.put(name, insideMap.get(name) + minutes);
        }
        return sortedData;
    }

    /**
     * Represents a storage of statistic data. Uses hashmap to keep events (EventType enum - key, List<EventDataRow> - value)
     */
    private class StatisticStorage {
        private final Map<EventType, List<EventDataRow>> storage;

        /**
         * Initializes {@link #storage} with keys - (EventType) and Values - empty Arraylist<EventDataRow>
         */
        public StatisticStorage() {
            this.storage = new HashMap<>();
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<>());
            }
        }

        /**
         * Puts EventDataRow into appropriate list, basing on the EventType
         *
         * @param data the object to put into the repository
         */
        private void put(EventDataRow data) {
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
        }

        Map<EventType, List<EventDataRow>> get() {
            return Collections.unmodifiableMap(storage);
        }
    }
}

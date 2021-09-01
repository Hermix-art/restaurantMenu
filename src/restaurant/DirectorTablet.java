package restaurant;

import restaurant.ad.StatisticAdvertisementManager;
import restaurant.statistic.StatisticManager;

import java.util.*;

/**
 * Tablet for monitoring restaurant statistics
 *
 * @author Herman Kulik
 */
public class DirectorTablet {

    /**
     * Prints profits from each particular video per each day, prints total amount of profit to console.
     */
    void printAdvertisementProfit() {
        double sum = 0;
        String pattern = "%s - %s";
        Map<String, Double> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return new Date(o2).compareTo(new Date(o1));
            }
        });
        map.putAll(StatisticManager.getInstance().calculateProfitForAdvertisement());
        for (Map.Entry<String, Double> set : map.entrySet()) {
            System.out.printf(pattern + "%n", set.getKey(), set.getValue());
            sum += set.getValue();
        }
        System.out.print("Total - " + sum + "\n");

    }

    /**
     * Prints workloading of each cook per day (in minutes) to console.
     */
    void printCookWorkloading() {
        Map<String, TreeMap<String, Integer>> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return new Date(o2).compareTo(new Date(o1));
            }
        });
        treeMap.putAll(StatisticManager.getInstance().calculateWorkLoadForCook());
        for (String s : treeMap.keySet()) {
            System.out.println(s);
            TreeMap<String, Integer> innerMap = treeMap.get(s);
            for (Map.Entry<String, Integer> enty : innerMap.entrySet()) {
                System.out.println(enty.getKey() + " - " + enty.getValue() + " min");
            }
            System.out.println();
        }

    }

    /**
     * Prints advertisement videos with > 0 number of hits to console
     */
    void printActiveVideoSet() {
        Map<String, Integer> notArchivedVideosNames = StatisticAdvertisementManager.getInstance().getActiveVideos();
        for (Map.Entry<String, Integer> entry : notArchivedVideosNames.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
    }

    /**
     * Prints advertisement videos with <= 0 number of hits to console (archived videos)
     */
    void printArchivedVideoSet() {
        Set<String> archivedVideos = StatisticAdvertisementManager.getInstance().getArchivedVideos();
        for (String s : archivedVideos) {
            System.out.println(s);
        }
        System.out.println();
    }
}

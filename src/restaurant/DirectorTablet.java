package restaurant;

import restaurant.statistic.StatisticManager;

import java.util.*;

/**
 * Tablet for monitoring restaurant statistics
 *
 * @author Herman Kulik
 */
public class DirectorTablet {
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

    void printActiveVideoSet() {

    }

    void printArchivedVideoSet() {

    }
}

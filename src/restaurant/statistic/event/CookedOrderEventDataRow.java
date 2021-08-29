package restaurant.statistic.event;

import restaurant.kitchen.Dish;

import java.util.Date;
import java.util.List;

/**
 * Represents event, related to a cooked order statistics
 *
 * @author Herman Kulik
 */
public class CookedOrderEventDataRow implements EventDataRow {
    private final Date currentDate;
    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishes;

    /**
     * @param tabletName         name of the tablet, the order was created with
     * @param cookName           cook, which cooked the dish
     * @param cookingTimeSeconds time for cooking a dish
     * @param cookingDishes      the list of dishes in the order
     */
    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishes) {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishes = cookingDishes;
        this.currentDate = new Date();

    }
}

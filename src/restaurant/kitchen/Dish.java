package restaurant.kitchen;

/**
 * @author Herman Kulik
 */
public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(13),
    JUICE(5),
    WATER(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Dish dish : values()) {
            stringBuilder.append(dish.toString()).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}

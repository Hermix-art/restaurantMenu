package restaurant.kitchen;

import restaurant.ConsoleHelper;
import restaurant.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * @author Herman Kulik
 */
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;//chosen dishes

    public int getTotalCookingTime() {
        int sum = 0;
        for (Dish dish : dishes) {
            sum += dish.getDuration();
        }
        return sum;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        if (isEmpty()) return " ";
        return "Your order: " + dishes.toString() + " of " + tablet.toString() +
                ", cooking time " + getTotalCookingTime() + "min";
    }

    public String getTabletName() {
        return tablet.toString();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}

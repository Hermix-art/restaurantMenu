package restaurant.kitchen;

import restaurant.ConsoleHelper;
import restaurant.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;//chosen dishes

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if(dishes.isEmpty()) return " ";
        return "Your order: " + dishes.toString() + " of " + tablet.toString();
    }
}

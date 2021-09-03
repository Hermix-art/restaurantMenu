package restaurant.kitchen;

import restaurant.Tablet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Herman Kulik
 */
public class TestOrder extends Order{

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        dishes.add(Dish.JUICE);
        dishes.add(Dish.FISH);
        dishes.add(Dish.WATER);
        dishes.add(Dish.STEAK);
        dishes.add(Dish.SOUP);
    }
}

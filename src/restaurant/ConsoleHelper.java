package restaurant;

import restaurant.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Herman Kulik
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> chosenDishes = new ArrayList<>();
        List<String> dishesNames = Stream.of(Dish.values())
                .map(Dish::name)
                .collect(Collectors.toList());

        writeMessage(Dish.allDishesToString());
        writeMessage("Please select the dishes, and type \'exit\': ");

        String dish;
        while (!(dish = readString()).equals("exit")) {

            if (!dishesNames.contains(dish.toUpperCase())) writeMessage(String.format(
                    "We have no %s, please select the dish from list", dish));
            else chosenDishes.add(Dish.valueOf(dish.toUpperCase()));
        }
        return chosenDishes;
    }
}

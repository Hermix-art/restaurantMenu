package restaurant.kitchen;

public enum Dish {
    FISH,
    STEAK,
    SOUP,
    JUICE,
    WATER;
    public static String allDishesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Dish dish: Dish.values()){
            stringBuilder.append(dish).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}

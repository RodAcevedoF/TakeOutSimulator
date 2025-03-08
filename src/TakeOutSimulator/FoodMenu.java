package TakeOutSimulator;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    private List<Food> menu;

    public FoodMenu() {
        this.menu = new ArrayList<>();
        Food apple = new Food("apple", "sweet and fresh", 1);
        Food bread = new Food("bread", "soft and crispy", 2);
        Food egg = new Food("egg", "high in protein", 3);
        this.menu.add(apple);
        this.menu.add(bread);
        this.menu.add(egg);
    }

    public Food getFood(int index) {
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        }
        throw new IllegalArgumentException("Invalid menu selection: " + index);
    }


    public int getFoodItems() {
        return this.menu.size();
    }

    public Food getLowestCostFood(){
        if (menu.isEmpty()) {
            return null;
        }
        Food lowest = menu.get(0);
        for (Food f : menu)
            if(f.getPrice() < lowest.getPrice()) {
                lowest = f;
            }
        return lowest;
    }
    @Override
    public String toString() {
        int i = 1;
        String result = "";
        for(Food f : menu) {
            result += i + ". Enjoy " + f.getName() + ": " + f.getDescription() + ". Cost: $" + f.getPrice() + "\n";
            i++;
        }
        return result;
    }
}

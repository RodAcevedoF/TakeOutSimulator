package TakeOutSimulator;
import java.util.Scanner;

public class TakeOutSimulator {
    Customer customer;
    FoodMenu menu;
    Scanner input;

    public TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input) {
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private <T> T getResponse(String userInputPrompt, UserInputRetriever<T> userInputRetriever) {
        while (true) {
            System.out.println(userInputPrompt);
            if (input.hasNextInt()) {
                int selection = input.nextInt();
                input.nextLine();
                try {
                    return userInputRetriever.produceOutput(selection);
                } catch (IllegalArgumentException e) {
                    System.out.println(selection + " is not a valid input. Try Again!");
                }
            } else {
                System.out.println("Input needs to be an int type.");
                input.next(); // Consume invalid input
            }
        }
    }

    public Boolean shouldSimulate() {
        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program: ";

        UserInputRetriever<Boolean> retriever = selection -> {
            if (selection == 1) {
                Food cheapestFood = menu.getLowestCostFood();
                if (cheapestFood != null && customer.getMoney() >= cheapestFood.getPrice()) {
                    return true;
                } else {
                    System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
                    return false;
                }
            } else if (selection == 0) {
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        };

        return getResponse(userPrompt, retriever);
    }

    public Food getMenuSelection() {
        StringBuilder userPromptBuilder = new StringBuilder("Today's Menu Options!\n\n");
        for (int i = 0; i < menu.getFoodItems(); i++) {
            Food food = menu.getFood(i);
            userPromptBuilder.append(String.format(
                    "%d. %s: %s    Cost: $%d\n",
                    i + 1,
                    food.getName(),
                    food.getDescription(),
                    food.getPrice()
            ));
        }
        userPromptBuilder.append("Choose a menu item!: ");
        String userPrompt = userPromptBuilder.toString();

        UserInputRetriever<Food> userInputRetriever = selection -> {
            Food selectedFood = menu.getFood(selection - 1);
            if (selectedFood != null) {
                return selectedFood;
            } else {
                throw new IllegalArgumentException("Selection out of range");
            }
        };

        return getResponse(userPrompt, userInputRetriever);
    }

    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";

        UserInputRetriever<Boolean> userInputRetriever = selection -> {
            if (selection == 1) {
                return true;
            } else if (selection == 0) {
                return false;
            } else {
                throw new IllegalArgumentException("Selection must be 0 or 1");
            }
        };
        return getResponse(userPrompt, userInputRetriever);
    }

    public void checkOutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Payment is processing...");
        int balance = customer.getMoney() - shoppingBag.getTotalPrice();
        customer.setMoney(balance);
        System.out.printf("Your remaining money: $%d\n", customer.getMoney());
        System.out.println("Thanks and enjoy!!");
    }

    public void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<Food>();
        int customerMoneyLeft = customer.getMoney();

        while (true) {
            System.out.printf("You have $%d left to spend\n", customerMoneyLeft);

            Food selectedFood = getMenuSelection();
            if (customerMoneyLeft >= selectedFood.getPrice()) {
                customerMoneyLeft -= selectedFood.getPrice();
                shoppingBag.addItem(selectedFood);
            } else {
                System.out.println("Oops! Looks like you don't have enough for that. Choose another item or checkout.");
            }

            if (!isStillOrderingFood()) {
                checkOutCustomer(shoppingBag);
                break;
            }
        }
    }

    public void startTakeOutSimulator() {
        System.out.println("Hello, welcome to my restaurant!");
        System.out.printf("Welcome %s!\n", customer);

        while (shouldSimulate()) {
            takeOutPrompt();
        }

        System.out.println("Thank you for visiting! Goodbye!");
    }
}


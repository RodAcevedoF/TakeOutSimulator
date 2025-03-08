import TakeOutSimulator.Customer;
import TakeOutSimulator.FoodMenu;
import TakeOutSimulator.TakeOutSimulator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your name:");
        String customerName = input.nextLine();

        System.out.println("Enter the amount of money you have:");
        while(!input.hasNextInt()) {
            System.out.println("Enter a valid number");
            input.next();
        }
        int money = input.nextInt();

        Customer customer = new Customer(customerName, money);
        FoodMenu menu = new FoodMenu();
        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, menu, input);
        takeOutSimulator.startTakeOutSimulator();
    }
}

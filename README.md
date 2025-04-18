# TakeOutSimulator

This is a simple Java console application that simulates a take-out ordering system. Users can select food items from a menu, manage their shopping bag, and checkout while keeping track of their remaining balance.

## Features

- Prompt user for name and available money.
- Display a menu of food items with prices and descriptions.
- Allow users to continue ordering as long as they have enough money.
- Handle user input validation for menu selection and continuation prompts.
- Show remaining balance upon checkout and end the simulation.

## Project Structure

- `.idea/`: IDE configuration files.
- `.gitignore`: Specifies files to ignore in Git.
- `src/TakeOutSimulator/`:
  - `Customer.java`: Represents the customer with name and money.
  - `Food.java`: Defines food items implementing `PricedItem`.
  - `PricedItem.java`: Interface for items with a price.
  - `FoodMenu.java`: Manages the menu and food items.
  - `ShoppingBag.java`: Stores selected items and calculates total price.
  - `UserInputRetriever.java`: Functional interface for input handling.
  - `TakeOutSimulator.java`: Main simulation logic.
  - `Main.java`: Entry point that starts the application.

## How to Run

1. **Clone or download** the project.
2. **Compile** the Java sources:
   ```bash
   javac -d out src/TakeOutSimulator/*.java
   ```
3. **Run** the application:
   ```bash
   java -cp out TakeOutSimulator.Main
   ```

## Example

```
Enter your name:
John
Enter the amount of money you have:
10
Hello, welcome to my restaurant!
Welcome Customer{name='John', money=10}!
Enter 1 to CONTINUE simulation or 0 to EXIT program:
1
You have $10 left to spend
Today's Menu Options!

1. apple: sweet and fresh    Cost: $1
2. bread: soft and crispy    Cost: $2
3. egg: high in protein    Cost: $3
Choose a menu item!: 2
Enter 1 to CONTINUE shopping or 0 to CHECKOUT:
1
...
```

## Requirements

- Java 11 or higher

---

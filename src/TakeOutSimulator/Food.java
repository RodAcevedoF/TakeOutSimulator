package TakeOutSimulator;

public class Food implements PricedItem<Integer> {
    final private String name;
    final private String description;
    private Integer price;

    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Food{name='%s', description='%s', price=%d}", name, description, price);
    }
}

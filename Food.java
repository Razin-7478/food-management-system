package FoodMS;

/**
 * Represents a food item in the inventory.
 * Extends Item - demonstrates Inheritance and Polymorphism.
 */
public class Food extends Item {

    private double price;
    private int quantity;

    public Food(String id, String name, double price, int quantity) {
        super(id, name);
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double totalValue() {
        return price * quantity;
    }
}

package FoodMS;

/**
 * Abstract base class for all inventory items.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class Item {

    private String id;
    private String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates the total monetary value of this item.
     * Must be implemented by concrete subclasses.
     */
    public abstract double totalValue();
}

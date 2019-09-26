/**
* @author Tyler Jones
* @version 0.1.0
*
* This is the public enum that describes each item and its price that can be
* stocked into the VendingMachine.
*
* This file works in conjunction with VendingMachine.java and VendingWorld.java
* to simulate a vending machine.
*
*/
public enum VendingItem {
    /**
    * Every Item and its corresponding price
    */
    LAYS(1.50),
    DORITOS(1.50),
    COKE(2.50),
    RAMBLIN_RECK_TOY(180.75),
    RUBIKS_CUBE(30.00),
    RAT_CAP(15.00),
    FASET_LANYARD(10.00),
    GRAPHING_CALCULATOR(120.00),
    UGA_DIPLOMA(0.10),
    PIE(3.14),
    CLICKER(55.55),
    CHEETOS(1.25),
    SPRITE(2.50),
    RED_BULL(4.75),
    RAMEN(3.15),
    COLD_PIZZA(0.99);

    private final double price;

    VendingItem(double price) {
        /**
        * VendingItem Constructor
        */
        this.price = price;
    }

    /**
    * @return a string the represents the item and its price as:
    * "[item]: $price"
    */
    public String toString() {
        //System.out.println(this.name());
        String str = "";
        str = str.format("%s: $%3.2f", this.name(), price);
        return str;
    }

    /**
    * @return getter method for the price of the item
    */
    public double getPrice() {
        return price;
    }

}

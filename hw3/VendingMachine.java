import java.util.Random;
/**
*@author tjones329
*@version 0.1.0
*
* the vending machine class creates the actual vending machine and can be
* stocked with different items from VendingItem.java.
*
*/
public class VendingMachine {
    private static double totalSales;
    private int luckyChance = 0;
    private Random rand = new Random();
    private VendingItem[][][] shelf = new VendingItem[6][3][5];
    private double itemCost;

    /**
    * this is the constructor for the vending machine class. it will also
    * make sure that the vending machine is restocked whenever the vending
    * machine class is called.
    *
    */
    public VendingMachine() {
        this.totalSales = totalSales;
        this.luckyChance = luckyChance;
        this.rand = rand;
        this.shelf = shelf;
        restock();

    }

    /**
    * @param code the user inputs a code that determines the position of the
    of the item that should be returned.
    * @return the item object that has been selected by the user
    *
    * Notes: for each slot in the position bring the item in that position
    * 1 space closer to the front.
    */
    public VendingItem vend(String code) {
        int rowPosition = code.charAt(0) - 65;
        int colPosition = code.charAt(1) - 49;
        VendingItem itemSelected = shelf[rowPosition][colPosition][0];
        for (int i = 0; i < 4; i++) {
            shelf[rowPosition][colPosition][i]
                = shelf[rowPosition][colPosition][i + 1];
            shelf[rowPosition][colPosition][i + 1] = null;
        }
        // if (itemSelected != null) {
        //     itemCost -= itemSelected.getPrice();
        // }
        if (free()) {
            System.out.println("You received your item for free!");
            return itemSelected;
        }
        if (itemSelected != null) {
            totalSales += itemSelected.getPrice();
        }
        return itemSelected;
    }

    /**
    * @return returns a boolean that says if they get a free item or not
    *
    * if the method returns true, it resets the value of luckyChance and if
    * it returns false it increments the value of luckyChance by 1 to
    * increase the chances the next time vend() is called.
    */
    private boolean free() {
        int roll = rand.nextInt(100);
        if (roll <= luckyChance) {
            luckyChance = 0;
            return true;
        } else {
            luckyChance += 1;
            return false;
        }
    }
    /**
    * there is no return but it loops through the shelf 3d item array and
    * randomly selects an item from VendingItem enum and fills that slot
    * with that item.
    *
    */
    public void restock() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 5; k++) {
                    int itemValue = rand.nextInt(16);
                    VendingItem item = VendingItem.values()[itemValue];
                    shelf[i][j][k] = item;
                    // if (item != null) {
                    //     itemCost += item.getPrice();
                    // }
                }
            }
        }
    }

    /**
    * @return totalSales
    *
    * just a simple getter method that will return the value of totalSales
    */
    public static double getTotalSales() {
        return totalSales;
    }

    /**
    * @return the number of items in the vending machine. not a getter
    * method. it loops through
    *
    */
    public int getNumberOfItems() {
        int itemCounter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 5; k++) {
                    if (shelf[i][j][k] != null) {
                        itemCounter++;
                    }
                }
            }
        }
        return itemCounter;
    }
    /**
    * @return double
    */
    public double getTotalValue() {
        double itemCost = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 5; k++) {
                    if (shelf[i][j][k] != null) {
                        itemCost += shelf[i][j][k].getPrice();
                    }
                }
            }
        }
        return itemCost;
    }
    /**
    * @return int representing chance to get free item
    */
    public int getLuckyChance() {
        return luckyChance;
    }
    /**
    * @return String representation of the vending machine
    */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append("                            VendaTron 9000                "
            + "            \n");
        for (int i = 0; i < shelf.length; i++) {
            s.append("------------------------------------------------------"
                + "----------------\n");
            for (int j = 0; j < shelf[0].length; j++) {
                VendingItem item = shelf[i][j][0];
                String str = String.format("| %-20s ",
                    (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append(String.format("There are %d items with a total "
            + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
            + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }
}

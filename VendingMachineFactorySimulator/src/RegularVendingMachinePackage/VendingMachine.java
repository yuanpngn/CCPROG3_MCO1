package RegularVendingMachinePackage;

import java.io.IOException;
import java.util.*;
// Base class for VendingMachine, this is where the logic happens

/*
 * the importance of this class is to ensure that RegularVendingMachine and
 * RamenVendingMachine would have common functionality and attributes
 */

class VendingMachine {

  // declaration of variables
  private static Scanner scanner = new Scanner(System.in);
  private double balance; // balance
  private int numberOfSlots; // slots
  private int capacityPerSlot; // capacity
  private static String choice;
  private Map<Integer, Integer> inventory; // stock number = quantity
  private Map<Integer, String> items; // stock number = name
  private Map<Integer, Double> prices; // stock number = price
  private double totalSales; // Track the total sales
  private Map<Integer, Integer> startingInventory; // Track the starting inventory
  private Map<Integer, Integer> endingInventory; // Track the ending inventory

  // method to add balance
  public void addBalance(double amount) {
    balance += amount;
  }

  public static void clearScreen() throws IOException, InterruptedException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    return;
  }

  // method to get the value of balance
  public double getBalance() {
    return balance;
  }

  // method to edit the price
  public void editItemPrice(int slotNumber, double newPrice) {
    try {
      clearScreen();
    } catch (IOException | InterruptedException e) {
      System.out.println("Error cleaning: " + e.getMessage());
    }
    if (prices.containsKey(slotNumber)) {
      prices.put(slotNumber, newPrice);
      System.out.println("Item price updated. New price for slot " + slotNumber + ": P" + newPrice);
    } else {
      System.out.println("Invalid slot number.");
    }
  }

  // method to restock item
  public void restockItem(int slotNumber, int quantity) {
    if (inventory.containsKey(slotNumber)) {
      int currentStock = inventory.get(slotNumber);
      int newStock = currentStock + quantity;
      inventory.put(slotNumber, newStock);
      endingInventory.put(slotNumber, newStock); // Update the endingInventory map
      System.out.println("Restocked " + items.get(slotNumber) + " - New quantity: " + newStock);
    } else {
      System.out.println("Invalid slot number.");
    }

    try {
      clearScreen();
    } catch (IOException | InterruptedException e) {
      System.out.println("Error cleaning: " + e.getMessage());
    }
  }

  // method to set values for vending machine
  public VendingMachine(int numberOfSlots, int capacityPerSlot, Map<Integer, Integer> inventory,
      Map<Integer, String> items, Map<Integer, Double> prices) {
    this.numberOfSlots = numberOfSlots; // Set the number of slots for the vending machine
    this.capacityPerSlot = capacityPerSlot; // Set the capacity per slot for the vending machine
    this.inventory = inventory; // Set the inventory map for the vending machine
    this.items = items; // Set the items map for the vending machine
    this.prices = prices; // Set the prices map for the vending machine
    this.totalSales = 0.0; // Initialize the total sales to 0.0
    this.startingInventory = new HashMap<>(inventory); // Create a copy of the initial inventory map
    this.endingInventory = new HashMap<>(inventory); // Create a copy of the initial inventory map to track changes
  }

  // method to display items including the balance
  public void displayItems() {
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|                     Vending Machine                     |");
    System.out.println("+---------------------------------------------------------+");
    System.out.println("| Balance: P" + getBalance() + "                                           " + "|");
    System.out.println("| Available Items:" + "                                        " + "|");
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|   Slot   |     Item     |   Price   |     Calories      |");
    System.out.println("+---------------------------------------------------------+");
    for (int i = 1; i <= numberOfSlots; i++) {
      if (inventory.get(i) > 0) {
        String item = items.get(i);
        String price = "P" + prices.get(i);
        String calories = "Calories: " + getCalories(item);

        String formattedString = String.format("|%5d     |%-12s |%10s |%18s  |\n", i, item, price, calories);
        System.out.print(formattedString);
      }
    }
    System.out.println("+---------------------------------------------------------+");
  }

  // setting calorie values to items
  private int getCalories(String item) {
    // Update this method to return the calories for each item
    if (item.equals("Noodles")) {
      return 300;
    } else if (item.equals("Egg")) {
      return 80;
    } else if (item.equals("Chashu Pork")) {
      return 200;
    } else if (item.equals("Fried Tofu")) {
      return 120;
    } else if (item.equals("Negi")) {
      return 20;
    } else if (item.equals("Tonkotsu Broth")) {
      return 150;
    } else if (item.equals("Ukokkei Broth")) {
      return 180;
    } else if (item.equals("Miso Broth")) {
      return 100;
    } else {
      return 0; // Default value for items without specified calories
    }
  }

  // method to process the customer purchase
  public void processPurchase(int slotNumber, double payment) {
    if (inventory.containsKey(slotNumber)) {
      double itemPrice = prices.get(slotNumber);

      // Check if the payment amount is valid
      if (payment == 5 || payment == 10 || payment == 50 || payment == 100 || payment == 200 || payment == 500) {
        // Check if the payment amount is sufficient
        if (payment >= itemPrice) {
          int quantity = inventory.get(slotNumber);

          // Check if the item is in stock
          int currentStock = inventory.get(slotNumber);
          if (currentStock > 0) {
            // Deduct the item quantity and update the balance and total sales
            endingInventory.put(slotNumber, quantity - 1);
            balance += itemPrice;
            totalSales += itemPrice;

            System.out.println("Purchase successful!");
            System.out.println("Item: " + items.get(slotNumber));
            System.out.println("Price: P" + itemPrice);
            System.out.println("Change: P" + (payment - itemPrice));
          } else {
            try {
              clearScreen();
            } catch (IOException | InterruptedException e) {
              System.out.println("Error cleaning: " + e.getMessage());
            }
            System.out.println("Item out of stock. Please select another item.");
          }
        } else {
          try {
            clearScreen();
          } catch (IOException | InterruptedException e) {
            System.out.println("Error cleaning: " + e.getMessage());
          }
          System.out.println("Insufficient payment. Please insert more coins or bills.");
        }
      } else {
        try {
          clearScreen();
        } catch (IOException | InterruptedException e) {
          System.out.println("Error cleaning: " + e.getMessage());
        }
        System.out.println("Invalid denomination. Only 5, 10, 50, 100, 200, and 500 denominations are accepted.");
      }
    } else {
      try {
        clearScreen();
      } catch (IOException | InterruptedException e) {
        System.out.println("Error cleaning: " + e.getMessage());
      }
      System.out.println("Invalid slot number.");
    }
  }

  // method to display transaction summary
  public void printTransactionSummary() {
    try {
      clearScreen();
    } catch (IOException | InterruptedException e) {
      System.out.println("Error cleaning: " + e.getMessage());
    }

    System.out.println("Transaction Summary:");
    System.out.println("Starting Inventory:");
    displayInventory(startingInventory);
    System.out.println("Ending Inventory:");
    displayInventory(endingInventory);
    System.out.println("Total Sales: P" + totalSales);
    System.out.println("Press Any Number to Continue");
    choice = scanner.next();

    try {
      clearScreen();
    } catch (IOException | InterruptedException e) {
      System.out.println("Error cleaning: " + e.getMessage());
    }
  }

  // // method to get know the truth values for parameter item
  // private boolean isCustomizableRamen(String item) {
  // return item.equals("Noodles") || item.equals("Egg") || item.equals("Chashu
  // Pork")
  // || item.equals("Fried Tofu") || item.equals("Negi") || item.equals("Tonkotsu
  // Broth")
  // || item.equals("Ukokkei Broth") || item.equals("Miso Broth");
  // }

  // method to display the inventory
  private void displayInventory(Map<Integer, Integer> inventory) {
    for (int i = 1; i <= numberOfSlots; i++) {
      System.out.println(i + ". " + items.get(i) + " - Quantity: " + inventory.get(i));
    }
  }

  public static RegularVendingMachine createRegularVendingMachine() {
    // Initialize the regular vending machine with default attributes
    int numberOfSlots = 8;

    // maximum number that can be stored in a vending machine
    int capacityPerSlot = 10;

    // use maps to store values
    Map<Integer, Integer> inventory = new HashMap<>(); // Key: slot number, Value: number of items in the slot

    Map<Integer, String> items = new HashMap<>(); // Key: slot number, Value: item name

    Map<Integer, Double> prices = new HashMap<>(); // Key: slot number, Value: item price

    // loop to set a default price of 1.0 for all items
    for (int i = 1; i <= numberOfSlots; i++) {
      inventory.put(i, capacityPerSlot);
      items.put(i, "Item " + i);
      prices.put(i, 1.0);
    }
    // return the values to be displayed on Regular Vending Machine
    return new RegularVendingMachine(numberOfSlots, capacityPerSlot, inventory, items, prices);
  }

  public static void testVendingMachine(VendingMachine vendingMachine) throws IOException, InterruptedException {
    Scanner scanner = new Scanner(System.in);

    // run testing vending machine
    System.out.println("Testing Vending Machine...");
    try {
      clearScreen();
    } catch (IOException | InterruptedException e) {

      System.out.println("Error cleaning: " + e.getMessage());
    }
    while (true) {
      vendingMachine.displayItems();
      System.out.println("+-----------------------------------------+");
      System.out.println("|                Menu                     |");
      System.out.println("| 5 & 10 COINS, 100, 200 & 500 BILLS ONLY |");
      System.out.println("| 1. Purchase an Item                     |");
      System.out.println("| 2. Print Transaction Summary            |");
      System.out.println("| 3. Return to Main Menu                  |");
      System.out.println("| 4. Maintenance Feature                  |");
      System.out.println("+-----------------------------------------+");
      System.out.print("Enter your choice: ");
      choice = scanner.next();

      switch (choice) {
        case "1":
          if (vendingMachine.getBalance() == 0) {
            try {
              clearScreen();
            } catch (IOException | InterruptedException e) {
              System.out.println("Error cleaning: " + e.getMessage());
            }
            System.out.println("Vending Machine out of order");
            return;
          }

          System.out.print("Enter the slot number of the item you want to purchase: ");
          int slotNumber = scanner.nextInt();
          System.out.print("Enter the payment amount: P");
          double payment = scanner.nextDouble();
          if (payment != 5 || payment != 10 || payment != 50 || payment != 100 || payment != 200 || payment != 500) {
            vendingMachine.processPurchase(slotNumber, payment);
          }
          // try {
          // clearScreen();
          // } catch (IOException | InterruptedException e) {
          // System.out.println("Error cleaning: " + e.getMessage());
          // }
          // }

          vendingMachine.processPurchase(slotNumber, payment);
          break;

        // if (vendingMachine instanceof RamenVendingMachine) {
        // try {
        // clearScreen();
        // } catch (IOException | InterruptedException e) {
        // System.out.println("Error cleaning: " + e.getMessage());
        // }

        // System.out.println("Blanching noodles....");
        // Thread.sleep(1000);
        // System.out.println("Heating broth...");
        // Thread.sleep(1000);
        // System.out.println("Placing noodles in cup...");
        // Thread.sleep(1000);
        // System.out.println("Ramen Done!");
        // Thread.sleep(1500);
        // System.out.println("Press any Key");
        // choice = scanner.next();

        case "2":
          vendingMachine.printTransactionSummary();
          break;

        case "3":
          try {
            clearScreen();
          } catch (IOException | InterruptedException e) {
            System.out.println("Error cleaning: " + e.getMessage());
          }

          System.out.println("Returning to Main Menu...");
          return;

        case "4":
          try {
            clearScreen();
          } catch (IOException | InterruptedException e) {
            System.out.println("Error cleaning: " + e.getMessage());
          }

          System.out.println("+------------------------------------+");
          System.out.println("|        Maintenance Feature         |");
          System.out.println("|                                    |");
          System.out.println("| 1. Restock Item                    |");
          System.out.println("| 2. Add Balance                     |");
          System.out.println("| 3. Edit Item Price                 |");
          System.out.println("| 4. Return                          |");
          System.out.println("+------------------------------------+");
          System.out.print("Enter your choice: ");
          choice = scanner.next();

          switch (choice) {
            case "1":
              System.out.print("Enter the slot number of the item to restock: ");
              int restockSlotNumber = scanner.nextInt();
              System.out.print("Enter the quantity to restock: ");
              int restockQuantity = scanner.nextInt();
              vendingMachine.restockItem(restockSlotNumber, restockQuantity);
              break;

            case "2":
              System.out.print("Enter the amount to add to the balance: P");
              double amount = scanner.nextDouble();
              vendingMachine.addBalance(amount);

              try {
                clearScreen();
              } catch (IOException | InterruptedException e) {
                System.out.println("Error cleaning: " + e.getMessage());
              }

              System.out.println("Balance added successfully. New balance: P" + vendingMachine.getBalance());
              break;

            case "3":
              System.out.print("Enter the slot number of the item to edit the price: ");
              int itemSlotNumber = scanner.nextInt();
              System.out.print("Enter the new price for the item: P");
              double newPrice = scanner.nextDouble();
              vendingMachine.editItemPrice(itemSlotNumber, newPrice);
              break;

            case "4":
              return;

            default:
              System.out.println("Invalid choice. Please try again.");
              break;
          }
          break;

        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }

  }
}

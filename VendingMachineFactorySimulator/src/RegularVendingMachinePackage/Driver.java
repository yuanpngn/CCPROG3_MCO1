
package RegularVendingMachinePackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver {

  // universal variables
  // universal scanner to use for this VendingMachineFactorySimulator class
  public static Scanner scanner = new Scanner(System.in);

  // the input choice is a string so there will be no error when inputting
  public static String choice;

  // method to clear screen
  public static void clearScreen() throws IOException, InterruptedException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    return;
  }

  public static void updateProgressBar(int progress) {
    String progressBar = "Progress: [";
    int completedBlocks = progress / 5;
    int remainingBlocks = 20 - completedBlocks;

    for (int i = 0; i < completedBlocks; i++) {
      progressBar += "=";
    }

    progressBar += ">";

    for (int i = 0; i < remainingBlocks; i++) {
      progressBar += " ";
    }

    progressBar += "] " + progress + "%";

    System.out.print("\r" + progressBar);
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    int progress = 0;

    Scanner scanner = new Scanner(System.in);

    while (progress != 100) {
      Thread.sleep(5);
      progress += 1;
      updateProgressBar(progress);
    }

    Thread.sleep(500);
    System.out.print(".");
    Thread.sleep(500);
    System.out.print(".");
    Thread.sleep(500);
    System.out.print(".");
    System.out.println("\rLoading Complete ");
    Thread.sleep(500);

    // create an object currentMachine
    // the initial value is set to null that will set the currentMachine variable to
    // hold the attributes of the Vending Machine
    VendingMachine currentMachine = null;

    try {
      clearScreen();
    } catch (IOException | InterruptedException e) {

      System.out.println("Error cleaning: " + e.getMessage());
    }

    // loop through menu, only Exit will make it close
    while (true) {
      System.out.println("+------------------------------------+");
      System.out.println("|           Menu                     |");
      System.out.println("|                                    |");
      System.out.println("| 1. Create a Vending Machine        |");
      System.out.println("| 2. Test a Vending Machine          |");
      System.out.println("| 3. Exit                            |");
      System.out.println("+------------------------------------+");
      System.out.print("Enter your choice: ");
      choice = scanner.next();

      try {
        clearScreen();
      } catch (IOException | InterruptedException e) {

        System.out.println("Error cleaning: " + e.getMessage());
      }

      switch (choice) {
        case "1":
          System.out.println("+------------------------------------+");
          System.out.println("|        Create a Vending Machine    |");
          System.out.println("|                                    |");
          System.out.println("| 1. Regular Vending Machine         |");
          System.out.println("| 2. Ramen Vending Machine           |");
          System.out.println("+------------------------------------+");
          System.out.print("Enter the type of vending machine to create: ");
          choice = scanner.next();

          try {
            clearScreen();
          } catch (IOException | InterruptedException e) {
            System.out.println("Error cleaning: " + e.getMessage());
          }

          if (choice.equals("1")) {
            currentMachine = VendingMachine.createRegularVendingMachine();
            System.out.println("Regular Vending Machine created.");
          } else if (choice.equals("2")) {
            // currentMachine = createRamenVendingMachine();
            System.out.println("Ramen Vending Machine Feature Soon to be released.");
          } else {
            System.out.println("Invalid choice. Please try again.");
          }
          break;

        case "2":
          if (currentMachine != null) {
            VendingMachine.testVendingMachine(currentMachine);
          } else {
            System.out.println("No Vending Machine created. Please create a Vending Machine first.");
          }
          break;

        case "3":
          System.out.println("+------------------------------------+");
          System.out.println("|    Exiting the program...          |");
          System.out.println("+------------------------------------+");
          System.exit(0);

        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}

// private static RamenVendingMachine createRamenVendingMachine() {
// // Initialize the ramen vending machine with customizable ramen items and
// prices
// int numberOfSlots = 8;
// int capacityPerSlot = 10;

// // use maps to store values
// Map<Integer, Integer> inventory = new HashMap<>(); // Key: slot number,
// Value: number of items in the slot

// Map<Integer, String> items = new HashMap<>(); // Key: slot number, Value:
// item name

// Map<Integer, Double> prices = new HashMap<>(); // Key: slot number, Value:
// item price

// // Customize the ramen vending machine items and prices
// items.put(1, "Noodles");
// items.put(2, "Egg");
// items.put(3, "Chashu Pork");
// items.put(4, "Fried Tofu");
// items.put(5, "Negi");
// items.put(6, "Tonkotsu Broth");
// items.put(7, "Ukokkei Broth");
// items.put(8, "Miso Broth");

// // prices
// prices.put(1, 5.0);
// prices.put(2, 10.0);
// prices.put(3, 2.0);
// prices.put(4, 1.0);
// prices.put(5, 6.0);
// prices.put(6, 4.0);
// prices.put(7, 11.0);
// prices.put(8, 36.0);

// for (int i = 1; i <= numberOfSlots; i++) {
// inventory.put(i, capacityPerSlot);
// }
// // return the values to be displayed on Regular Vending Machine
// return new RamenVendingMachine(numberOfSlots, capacityPerSlot, inventory,
// items, prices);
// }

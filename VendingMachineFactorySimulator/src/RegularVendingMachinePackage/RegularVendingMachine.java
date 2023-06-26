package RegularVendingMachinePackage;

import java.util.*;

/*
 * IMPORTANT NOTES
 * There are 2 classes in this Java Class file which are the VendingMachine
 * class and VendingMachineSimulation Class.
 * VendingMachine class is like the blueprint of this vending machine, whereas
 * VendingMachineSimulation class is the program that
 * uses the blueprint to simulate the operation of one or more vending machines.
 * I used Parameters, Encapsulation, and Maps for this purposes:
 * Map is used to store key values, for example, item, price
 * Parameter is used to pass down values and return to pass back those values,
 * for example, methodname(parameter)
 * Encapsulation is used to hide the implementation details from the external
 * code and provide an interface to interact with the vending machine
 * for example, the printTransactionSummary() from Vending Machine class
 * provides the information of the inventory for the Vending Machine
 */

/*
 * this parts will be for invoking those types of vending machine that
 * ensures that the superclass constructor is properly executed before
 * the subclass constructor, establishing the correct initialization order and
 * maintaining the inheritance hierarchy.
 * For example, subclass extends parent class or super class constructor
 * RegularVendingMachine/SubCLass = VendingMachine/Parent Class
 */

// RegularVendingMachine class extends VendingMachine
class RegularVendingMachine extends VendingMachine {
  public RegularVendingMachine(int numberOfSlots, int capacityPerSlot,
      Map<Integer, Integer> inventory, Map<Integer, String> items, Map<Integer, Double> prices) {
    super(numberOfSlots, capacityPerSlot, inventory, items, prices);
  }

}

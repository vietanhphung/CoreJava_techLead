import java.util.*;

public class B8Test {
    public static void main(String[] args) {
        B8.Inventory inventory = new B8().new Inventory();

        // Test Case 1: Add items to inventory
        System.out.println("Test Case 1: Adding items");
        inventory.add(101, "Laptop");
        inventory.add(102, "Phone");
        inventory.add(103, "Tablet");
        inventory.display();
        System.out.println();

        // Test Case 2: Retrieve item info
        System.out.println("Test Case 2: Get info for existing ID");
        System.out.println(inventory.getInfo(102)); // Expected: "Phone"
        System.out.println();

        // Test Case 3: Retrieve info for a non-existing ID
        System.out.println("Test Case 3: Get info for non-existing ID");
        System.out.println(inventory.getInfo(999)); // Expected: "ID not found"
        System.out.println();

        // Test Case 4: Remove an existing item
        System.out.println("Test Case 4: Removing an existing item");
        inventory.remove(103);
        inventory.display(); // Tablet should be removed
        System.out.println();

        // Test Case 5: Attempt to remove a non-existing ID
        System.out.println("Test Case 5: Removing a non-existing ID");
        inventory.remove(999); // Expected: "ID not found"
        System.out.println();

        // Test Case 6: Update an existing item
        System.out.println("Test Case 6: Updating an existing item");
        inventory.update(101, "Gaming Laptop");
        System.out.println(inventory.getInfo(101)); // Expected: "Gaming Laptop"
        System.out.println();

        // Test Case 7: Attempt to update a non-existing item
        System.out.println("Test Case 7: Updating a non-existing ID");
        inventory.update(999, "Smartwatch"); // Expected: "ID not found"
        System.out.println();

        // Test Case 8: Display after updates
        System.out.println("Test Case 8: Final Inventory State");
        inventory.display();
    }
}

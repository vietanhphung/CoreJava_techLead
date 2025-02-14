import java.util.HashMap;
import java.util.Map;

public class B3Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        // Test add()
        System.out.println("Testing add() method:");
        B3.add(map, "Apple", 10);
        B3.add(map, "Banana", 5);
        B3.add(map, "Orange", 8);
        B3.display(map);
        System.out.println();

        // Test exist()
        System.out.println("Testing exist() method:");
        System.out.println("Apple exists? " + B3.exist(map, "Apple"));  // Expected: true
        System.out.println("Grapes exists? " + B3.exist(map, "Grapes")); // Expected: false
        System.out.println();

        // Test search()
        System.out.println("Testing search() method:");
        B3.search(map, "Banana");  // Expected: Key found
        B3.search(map, "Mango");   // Expected: Key not found
        System.out.println();

        // Test remove()
        System.out.println("Testing remove() method:");
        B3.remove(map, "Orange");
        B3.display(map); // Expected: "Apple: 10, Banana: 5" (No Orange)
        System.out.println();

        // Edge Case: Removing non-existent key
        System.out.println("Testing remove() on non-existent key:");
        B3.remove(map, "Pineapple"); // Should not crash or change anything
        B3.display(map);
    }
}

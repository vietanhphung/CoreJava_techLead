import java.util.*;

public class B2Test {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>(Arrays.asList(
            "United States", "Canada", "Mexico", "Brazil",
            "United Kingdom", "Germany", "France", "Italy",
            "Spain", "Australia"
        ));

        // Test add method
        B2.add(set, "Japan");
        assert set.contains("Japan") : "Add method failed";

        B2.add(set, "Canada"); // Duplicate add should not change set
        int expectedSize = 11;
        assert B2.count(set) == expectedSize : "Add method failed (duplicate check)";

        // Test display method (manual check)
        System.out.println("Set contents:");
        B2.display(set);

        // Test exist method
        assert B2.exist(set, "France") : "exist method failed for existing value";
        assert !B2.exist(set, "China") : "exist method failed for absent value";

        // Test remove method
        B2.remove(set, "Mexico");
        assert !set.contains("Mexico") : "remove method failed";

        B2.remove(set, "Russia"); // Removing non-existent value should do nothing
        assert B2.count(set) == expectedSize - 1 : "remove method failed for non-existent value";

        // Test count method
        assert B2.count(set) == 10 : "count method failed";

        System.out.println("All test cases passed!");
    }
}

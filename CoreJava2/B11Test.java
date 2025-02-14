import java.util.HashMap;
import java.util.Map;

public class B11Test {
    public static void main(String[] args) {
        System.out.println("Test Case 1: Adding and Retrieving Values");

        // Create a dictionary
        Map<String, String> dictionaryMap = new HashMap<>();
        B11.Dictionary dictionary = new B11().new Dictionary(dictionaryMap);

        // Add words to the dictionary
        dictionary.add("hello", "A greeting");
        dictionary.add("world", "The Earth, or everyone");
        dictionary.add("java", "A programming language");

        // Retrieve and print values
        System.out.println("hello: " + dictionary.get("hello")); // Expected: A greeting
        System.out.println("world: " + dictionary.get("world")); // Expected: The Earth, or everyone
        System.out.println("java: " + dictionary.get("java"));   // Expected: A programming language

        // Edge Case: Key that doesn’t exist
        System.out.println("\nTest Case 2: Retrieving a Non-Existing Key");
        System.out.println("python: " + dictionary.get("python")); // Expected: null

        // Edge Case: Adding duplicate key (should override existing value)
        System.out.println("\nTest Case 3: Overwriting an Existing Key");
        dictionary.add("java", "A hot beverage");
        System.out.println("java: " + dictionary.get("java")); // Expected: A hot beverage

        // Edge Case: Empty dictionary
        System.out.println("\nTest Case 4: Empty Dictionary");
        Map<String, String> emptyMap = new HashMap<>();
        B11.Dictionary emptyDict = new B11().new Dictionary(emptyMap);
        System.out.println("Unknown key: " + emptyDict.get("unknown")); // Expected: null
    }
}

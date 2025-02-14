import java.util.HashMap;
import java.util.Map;

public class B10Test {
    public static void main(String[] args) {
        System.out.println("Test Case 1: Grade Distribution with Sample Data");
        Map<String, Double> grades = new HashMap<>();
        grades.put("Alice", 9.0);
        grades.put("Bob", 7.5);
        grades.put("Charlie", 5.0);
        grades.put("David", 3.5);
        grades.put("Eve", 8.5);
        grades.put("Frank", 6.5);
        grades.put("Grace", 4.0);
        grades.put("Henry", 5.5);

        Map<String, Integer> result = B10.gradeDistribution(grades);

        // Expected output:
        // pass with high score: 2 (Alice, Eve)
        // pass: 3 (Bob, Frank, Henry)
        // faill: 3 (David, Grace, Charlie)

        System.out.println("pass with high score: " + result.get("pass with high score"));
        System.out.println("pass: " + result.get("pass"));
        System.out.println("faill: " + result.get("faill"));

        // Edge Case: All high scores
        System.out.println("\nTest Case 2: All High Scores");
        Map<String, Double> allHigh = new HashMap<>();
        allHigh.put("Alice", 9.0);
        allHigh.put("Bob", 9.5);
        allHigh.put("Charlie", 8.2);
        
        Map<String, Integer> result2 = B10.gradeDistribution(allHigh);
        System.out.println("pass with high score: " + result2.get("pass with high score")); // Should be 3
        System.out.println("pass: " + result2.get("pass")); // Should be 0
        System.out.println("faill: " + result2.get("faill")); // Should be 0

        // Edge Case: All failing
        System.out.println("\nTest Case 3: All Fail");
        Map<String, Double> allFail = new HashMap<>();
        allFail.put("David", 3.0);
        allFail.put("Eve", 2.5);
        allFail.put("Frank", 1.0);
        
        Map<String, Integer> result3 = B10.gradeDistribution(allFail);
        System.out.println("pass with high score: " + result3.get("pass with high score")); // Should be 0
        System.out.println("pass: " + result3.get("pass")); // Should be 0
        System.out.println("faill: " + result3.get("faill")); // Should be 3

        // Edge Case: Empty input
        System.out.println("\nTest Case 4: Empty Input");
        Map<String, Double> empty = new HashMap<>();
        
        Map<String, Integer> result4 = B10.gradeDistribution(empty);
        System.out.println("pass with high score: " + result4.get("pass with high score")); // Should be 0
        System.out.println("pass: " + result4.get("pass")); // Should be 0
        System.out.println("faill: " + result4.get("faill")); // Should be 0
    }
}

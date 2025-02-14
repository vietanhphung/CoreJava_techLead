import java.util.HashMap;
import java.util.Map;

public class B7Test {
    public static void main(String[] args) {
        // Test case 1: Normal case with multiple students
        System.out.println("Test Case 1: Normal case");
        Map<String, Integer[]> grades1 = new HashMap<>();
        grades1.put("Alice", new Integer[]{90, 85, 80});
        grades1.put("Bob", new Integer[]{75, 70, 65});
        grades1.put("Charlie", new Integer[]{100, 95, 90});
        B7.averageGrade(grades1);
        System.out.println();

        // Test case 2: Single student
        System.out.println("Test Case 2: Single student");
        Map<String, Integer[]> grades2 = new HashMap<>();
        grades2.put("David", new Integer[]{50, 60, 70, 80});
        B7.averageGrade(grades2);
        System.out.println();

        // Test case 3: Student with only one grade
        System.out.println("Test Case 3: Student with one grade");
        Map<String, Integer[]> grades3 = new HashMap<>();
        grades3.put("Eve", new Integer[]{95});
        B7.averageGrade(grades3);
        System.out.println();

        // Test case 4: Student with no grades
        System.out.println("Test Case 4: Student with no grades");
        Map<String, Integer[]> grades4 = new HashMap<>();
        grades4.put("Frank", new Integer[]{});  // Should handle division by zero
        B7.averageGrade(grades4);
        System.out.println();

        // Test case 5: Empty student list
        System.out.println("Test Case 5: Empty student list");
        Map<String, Integer[]> grades5 = new HashMap<>();
        B7.averageGrade(grades5);
    }
}

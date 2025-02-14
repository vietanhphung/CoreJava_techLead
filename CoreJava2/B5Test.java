import java.util.ArrayList;
import java.util.Arrays;

public class B5Test {
    public static void main(String[] args) {
        B5 b5 = new B5();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 7, 11, 15, 1, 8, 3));

        // Test Case 1: Normal case with a valid pair
        System.out.println("Test Case 1: Target = 9");
        ArrayList<Integer> result1 = b5.sumOf2(list, 9);
        System.out.println("Output: " + result1); // Expected: [2, 7]
        System.out.println();

        // Test Case 2: Another valid pair
        System.out.println("Test Case 2: Target = 10");
        ArrayList<Integer> result2 = b5.sumOf2(list, 10);
        System.out.println("Output: " + result2); // Expected: [2, 8]
        System.out.println();

        // Test Case 3: No valid pair
        System.out.println("Test Case 3: Target = 20");
        ArrayList<Integer> result3 = b5.sumOf2(list, 20);
        System.out.println("Output: " + result3); // Expected: []
        System.out.println();

        // Test Case 4: Edge case with only one element
        System.out.println("Test Case 4: Single element list");
        ArrayList<Integer> singleElementList = new ArrayList<>(Arrays.asList(5));
        ArrayList<Integer> result4 = b5.sumOf2(singleElementList, 10);
        System.out.println("Output: " + result4); // Expected: []
        System.out.println();

        // Test Case 5: Edge case with empty list
        System.out.println("Test Case 5: Empty list");
        ArrayList<Integer> emptyList = new ArrayList<>();
        ArrayList<Integer> result5 = b5.sumOf2(emptyList, 5);
        System.out.println("Output: " + result5); // Expected: []
    }
}

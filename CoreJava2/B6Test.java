import java.util.ArrayList;
import java.util.Arrays;

public class B6Test {
    public static void main(String[] args) {
        // Test case 1: Normal case with increasing and decreasing prices
        System.out.println("Test Case 1: Normal case");
        ArrayList<Integer> prices1 = new ArrayList<>(Arrays.asList(7, 1, 5, 3, 6, 4));
        System.out.println("Max Profit: " + B6.maxProfit(prices1)); // Expected: 5 (Buy at 1, Sell at 6)
        System.out.println();

        // Test case 2: Always decreasing prices (no profit possible)
        System.out.println("Test Case 2: Always decreasing");
        ArrayList<Integer> prices2 = new ArrayList<>(Arrays.asList(7, 6, 4, 3, 1));
        System.out.println("Max Profit: " + B6.maxProfit(prices2)); // Expected: 0
        System.out.println();

        // Test case 3: Single day (no transactions possible)
        System.out.println("Test Case 3: Single day");
        ArrayList<Integer> prices3 = new ArrayList<>(Arrays.asList(5));
        System.out.println("Max Profit: " + B6.maxProfit(prices3)); // Expected: 0
        System.out.println();

        // Test case 4: Empty list
        System.out.println("Test Case 4: Empty list");
        ArrayList<Integer> prices4 = new ArrayList<>();
        System.out.println("Max Profit: " + B6.maxProfit(prices4)); // Expected: 0
        System.out.println();

        // Test case 5: Prices with multiple peaks
        System.out.println("Test Case 5: Multiple peaks");
        ArrayList<Integer> prices5 = new ArrayList<>(Arrays.asList(2, 4, 1, 7, 5, 3, 6, 8));
        System.out.println("Max Profit: " + B6.maxProfit(prices5)); // Expected: 7 (Buy at 1, Sell at 8)
    }
}

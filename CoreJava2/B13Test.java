import java.util.List;
import java.util.Arrays;

public class B13Test {
    public static void main(String[] args) {
        // Sample input list with duplicates
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 2, 3, 6, 7, 3, 8);

        // Expected output: [2, 3, 3] (Order of duplicates depends on iteration)
        System.out.print("Duplicate elements: ");
        B13.duplicate(numbers);
    }
}

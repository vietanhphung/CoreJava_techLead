import java.util.*;

public class B1Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5, 6, 7, 7534, 3, 32, 54, 6, 7, 8, 9, 0, 0, 0));

        // Test add method
        B1.add(list, 99);
        assert list.contains(99) : "Add method failed";

        // Test display method (Manually check output)
        System.out.println("List contents:");
        B1.display(list);

        // Test sumList method
        int expectedSum = list.stream().mapToInt(Integer::intValue).sum();
        assert B1.sumList(list) == expectedSum : "sumList method failed";

        // Test minMax method
        ArrayList<Integer> minMaxValues = B1.minMax(list);
        assert minMaxValues.get(0) == Collections.min(list) : "minMax method (min) failed";
        assert minMaxValues.get(1) == Collections.max(list) : "minMax method (max) failed";

        // Test remove method (removes all occurrences)
        B1.remove(list, 0);
        assert !list.contains(0) : "remove method failed";

        // Test delete method (removes all occurrences, same as remove)
        B1.delete(list, 5);
        assert !list.contains(5) : "delete method failed";

        // Test exist method
        assert B1.exist(list, 99) : "exist method failed for present value";
        assert !B1.exist(list, 100) : "exist method failed for absent value";

        System.out.println("All test cases passed!");
    }
}

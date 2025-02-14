import java.util.ArrayList;
import java.util.Arrays;

public class JavaCore1TestSuite {
    public static void main(String[] args) {
        // 5 - Shortest String
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("apple", "banana", "kiwi", "pear"));
        System.out.println("Test 5: " + (JavaCore1.shortestString(stringList).equals("kiwi") ? "PASS" : "FAIL"));

        // 6 - Sort List
        ArrayList<Number> numberList = new ArrayList<>(Arrays.asList(5, 3, 8, 1));
        System.out.println("Test 6: " + (JavaCore1.sortList(numberList).equals(Arrays.asList(1, 3, 5, 8)) ? "PASS" : "FAIL"));
        
        // 7 - Sort String List
        System.out.println("Test 7: " + (JavaCore1.sortString(stringList).equals(Arrays.asList("apple", "banana", "kiwi", "pear")) ? "PASS" : "FAIL"));

        // 8 - Median
        ArrayList<Number> medianList = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 5));
        System.out.println("Test 8: " + (JavaCore1.median(medianList).equals(3) ? "PASS" : "FAIL"));

        // 9 - Word Count
        System.out.println("Test 9: " + (JavaCore1.wordCount("Hello world, this is a test") == 5 ? "PASS" : "FAIL"));

        // 10 - A Count
        System.out.println("Test 10: " + (JavaCore1.aCount(stringList) == 3 ? "PASS" : "FAIL"));

        // Level 2 - 1 Second Largest
        ArrayList<Number> secondLargestList = new ArrayList<>(Arrays.asList(10, 5, 8, 20, 15));
        System.out.println("Test L2-1: " + (JavaCore1.secondLargest(secondLargestList) == 15 ? "PASS" : "FAIL"));

        // Level 2 - 2 Longest String
        System.out.println("Test L2-2: " + (JavaCore1.longestString(stringList).equals("banana") ? "PASS" : "FAIL"));

        // Level 2 - 4 Sum of numbers divisible by 5 and 3
        ArrayList<Number> divList = new ArrayList<>(Arrays.asList(15, 30, 10, 20, 25));
        System.out.println("Test L2-4: " + (JavaCore1.sumOfDivisible5(divList) == 45 ? "PASS" : "FAIL"));

        // Level 2 - 5 Max Contiguous Subarray Sum
        ArrayList<Integer> contList = new ArrayList<>(Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4));
        System.out.println("Test L2-5: " + (JavaCore1.maxContArraySum(contList) == 6 ? "PASS" : "FAIL"));

        // Level 3 - 1 Second Smallest
        ArrayList<Number> secondSmallestList = new ArrayList<>(Arrays.asList(10, 5, 8, 20, 15));
        System.out.println("Test L3-1: " + (JavaCore1.secondSmallest(secondSmallestList) == 8 ? "PASS" : "FAIL"));

        // Level 3 - 2 Max Difference
        System.out.println("Test L3-2: " + (JavaCore1.maxDifference(new ArrayList<>(Arrays.asList(1, 10, 5, 8))) == 9 ? "PASS" : "FAIL"));

        // Level 3 - 6 Median of Two Lists
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 3, 5));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(2, 4, 6));
        System.out.println("Test L3-6: " + (JavaCore1.medianOf2(list1, list2) == 3 ? "PASS" : "FAIL"));

        // Level 4 - 1 Min Steps Bubble Sort
        ArrayList<Integer> bubbleList = new ArrayList<>(Arrays.asList(5, 1, 4, 2, 8));
        System.out.println("Test L4-1: " + (JavaCore1.minStepsBBSort(bubbleList) == 5 ? "PASS" : "FAIL"));
    }
}

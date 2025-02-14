
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class B4 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 5, 3, 7, 2, 8, 4, 6));
        System.out.println("Original list: " + list);
        System.out.println("Ascending order: " + ascendingOrder(list));
        System.out.println("Descending order: " + descendingOrder(list));
    }

    public static ArrayList<Integer> ascendingOrder(ArrayList<Integer> list) {
        Collections.sort(list);
        return list;
    }
    public static ArrayList<Integer> descendingOrder(ArrayList<Integer> list) {
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

}
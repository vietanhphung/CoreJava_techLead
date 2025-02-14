import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;


public class B13 {
    public static void main(String[] args) {
        // Your code here
    }

    public static void duplicate( List<Integer> list){
        List<Integer> l = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();

        for(int i : list){
            if (set.contains(i)){
                l.add(i);
            }
            else{
                set.add(i);
            }
        }
        System.out.println(l);
    }
}
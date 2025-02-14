import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

public class B15 {
    public static void main(String[] args) {
    }


    public static void overLap(Set<Integer> a, Set<Integer> b){
        Set<Integer> set = new HashSet<>();
        for( int i : a){
            set.add(i);
        }
        for(int i : b){
            set.add(i);
        }
        System.out.println(set);
    }
}
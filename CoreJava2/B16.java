import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class B14 {
    public static void main(String[] args) {
    }


    public static void list(Set<Integer> a){
        List<Integer> list = new ArrayList<>();
        for( int i : a){
            list.add(i);
        }
        list.sort(null);
        System.out.println(list.get(0) + " and " + list.get(list.size() -1));
    }
}
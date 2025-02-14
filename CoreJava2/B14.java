import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class B14 {
    public static void main(String[] args) {
    }


    public static void overLap(Set<Integer> a, Set<Integer> b){
        List<Integer> overLap = new ArrayList<>();
        for( int i : a){
            if(b.contains(i)){
                overLap.add(i);
            }
        }
        System.out.println(overLap);
    }
}
import java.util.HashSet;
import java.util.Set;   

public class B2 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("United States");
        set.add("Canada");
        set.add("Mexico");
        set.add("Brazil");
        set.add("United Kingdom");
        set.add("Germany");
        set.add("France");
        set.add("Italy");
        set.add("Spain");
        set.add("Australia");
    }

    public static void add(Set<String> set, String value) {
        if(!exist(set, value)) {
            set.add(value);
        }
    }

    public static void display(Set<String> set) {
        for (String s : set) {
            System.out.println(s);
        }
    }

    public static boolean exist(Set<String> set, String value) {
        for (String s : set) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static void remove(Set<String> set, String value) {
        if (exist(set, value)){
            set.remove(value);
        }
    }

    public static int count(Set<String> set) {
        return(set.size());
    }

}
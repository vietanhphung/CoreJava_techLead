import java.util.Map;

public class B3 {
    public static void main(String[] args) {
    }

    public static void add(Map<String, Integer> map, String key, Integer value) {
        map.put(key, value);
    }

    public static void display(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void search(Map<String, Integer> map, String key){
        if(map.containsKey(key)){
            System.out.println("Key: " + key + " is present in the map");
        }else{
            System.out.println("Key: " + key + " is not present in the map");
        }
    }

    public static void remove(Map<String, Integer> map, String key){
        if(map.containsKey(key)){
            map.remove(key);
        }
    }

    public static boolean exist(Map<String, Integer> map, String key){
        return map.containsKey(key);
    }

   
}
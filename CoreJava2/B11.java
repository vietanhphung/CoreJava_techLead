import java.util.Map;

public class B11 {
    public static void main(String[] args) {
       
    }

    public class Dictionary{
        Map<String, String> dict;;
        public Dictionary(Map<String, String> dict){
            this.dict = dict;
        }

        public void add(String key, String value){
            dict.put(key, value);
        }

        public String get(String key){
            return dict.get(key);
        }

    }

}
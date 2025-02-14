import java.util.Map;
import java.util.HashMap;

public class B8 {
    public static void main(String[] args) {
    }

    public class Inventory{
        Map<Integer, String> inventory;
    

        public Inventory(){
            this.inventory = new HashMap<Integer, String>();
        }

        public void add(int id, String name){
            this.inventory.put(id, name);
        }

        public void display(){
            for (Map.Entry<Integer, String> entry : this.inventory.entrySet()) {
                System.out.println("ID: " + entry.getKey() + " Name: " + entry.getValue());
            }
        }

        public String getInfo(int id){
            if (!this.inventory.containsKey(id)){
                return "ID not found";
            }
            return this.inventory.get(id);
        }

        public void remove(int id){
            if (!this.inventory.containsKey(id)){
                System.out.println("ID not found");
            }
            this.inventory.remove(id);
        }

        public void update(int id, String info){
            if (!this.inventory.containsKey(id)){
                System.out.println("ID not found");
            }
            this.inventory.put(id, info);
        }
    }

}
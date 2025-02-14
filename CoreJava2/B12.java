import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;



public class B12{
    
        public void main(String[] args) {
        }
    
        public class Inventory {
            private  List< Map<String, Object> >  inventory;
        
    
            public Inventory( List< Map<String, Object> > inventory){
                this.inventory = inventory;
            }
            



            public List<Map<String, Object>> sortByName() {
                return inventory.stream()
                .sorted(Comparator.comparing(a -> (String) a.get("name")))
                .collect(Collectors.toList());
            }

            public List<Map<String, Object>> sortByPrice(){
                return inventory.stream()
                .sorted(Comparator.comparing(a -> (Double) a.get("price")))
                .collect(Collectors.toList());
            }

            public List<Map<String, Object>> sortByManufactureDate(){
                return inventory.stream()
                .sorted(Comparator.comparing (a -> (Date) a.get("manufactureDate")))
                .collect(Collectors.toList());
            }

            public List<Map<String, Object>> sortByPriceAndDate(){
                return inventory.stream()
                .sorted(Comparator
                    .comparing ((Map<String, Object> a) -> (Double) a.get("price"))
                .thenComparing( a -> (Date) a.get("manufactureDate")))
                .collect(Collectors.toList());
            }



        }

}
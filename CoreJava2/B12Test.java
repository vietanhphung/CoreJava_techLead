import java.util.*;

public class B12Test {
    public static void main(String[] args) {
        List<Map<String, Object>> inventoryList = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Laptop");
        item1.put("price", 1200.00);
        item1.put("manufactureDate", new Date(1690000000000L)); // Example date

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "Phone");
        item2.put("price", 800.00);
        item2.put("manufactureDate", new Date(1670000000000L));

        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "Tablet");
        item3.put("price", 600.00);
        item3.put("manufactureDate", new Date(1680000000000L));

        inventoryList.add(item1);
        inventoryList.add(item2);
        inventoryList.add(item3);

        B12.Inventory inventory = new B12().new Inventory(inventoryList);

        System.out.println("Sorted by Name:");
        printList(inventory.sortByName());

        System.out.println("\nSorted by Price:");
        printList(inventory.sortByPrice());

        System.out.println("\nSorted by Manufacture Date:");
        printList(inventory.sortByManufactureDate());

        System.out.println("\nSorted by Price and Date:");
        printList(inventory.sortByPriceAndDate());
    }

    private static void printList(List<Map<String, Object>> list) {
        for (Map<String, Object> item : list) {
            System.out.println(item);
        }
    }
}

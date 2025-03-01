package com.java_sql.main.controller.controller_java;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spring2/b12")
public class Spring2ControllerB12 {

    private List<Map<String, Object>> inventory = new ArrayList<>();

    public Spring2ControllerB12() {
        // Sample Data
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "ItemA");
        item1.put("price", 20.5);
        item1.put("manufactureDate", new Date(1672531200000L)); // Example Date

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "ItemB");
        item2.put("price", 15.0);
        item2.put("manufactureDate", new Date(1672617600000L));

        inventory.add(item1);
        inventory.add(item2);
    }

    @GetMapping("/sortByName")
    public List<Map<String, Object>> sortByName() {
        return inventory.stream()
                .sorted(Comparator.comparing(a -> (String) a.get("name")))
                .collect(Collectors.toList());
    }

    @GetMapping("/sortByPrice")
    public List<Map<String, Object>> sortByPrice() {
        return inventory.stream()
                .sorted(Comparator.comparing(a -> (Double) a.get("price")))
                .collect(Collectors.toList());
    }

    @GetMapping("/sortByManufactureDate")
    public List<Map<String, Object>> sortByManufactureDate() {
        return inventory.stream()
                .sorted(Comparator.comparing(a -> (Date) a.get("manufactureDate")))
                .collect(Collectors.toList());
    }

    @GetMapping("/sortByPriceAndDate")
    public List<Map<String, Object>> sortByPriceAndDate() {
        return inventory.stream()
                .sorted(Comparator.comparing((Map<String, Object> a) -> (Double) a.get("price"))
                        .thenComparing(a -> (Date) a.get("manufactureDate")))
                .collect(Collectors.toList());
    }
}

package com.bangchamcong.demo;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;
import java.util.Iterator;


public class TablePrinter {





    public static void printIncomeMap(Map<String, Object[]> incomeMap) {
        if (!incomeMap.isEmpty()) {
            Iterator<String> iterator = incomeMap.keySet().iterator();
            iterator.next(); // Remove the first row
            iterator.remove();
        }
    
        incomeMap.forEach((key, value) -> System.out.println(key + " => " + Arrays.toString(value)));
    }
    


    public static void printTable(Map<String, Map<Integer, Double>> data) {
        // Get all unique date columns in sorted order, but exclude Day 0
        TreeSet<Integer> dateColumns = new TreeSet<>();
        for (Map<Integer, Double> wages : data.values()) {
            dateColumns.addAll(wages.keySet());
        }
        dateColumns.remove(0); // Remove "Day 0"
    
        // Print header
        System.out.printf("%-15s", "Employee");
        for (Integer date : dateColumns) {
            System.out.printf("%-12d", date); // Print date as an integer
        }
        System.out.println("\n" + "-".repeat(15 + dateColumns.size() * 12));
    
        // Print rows
        for (Map.Entry<String, Map<Integer, Double>> employee : data.entrySet()) {
            String name = employee.getKey();
            Map<Integer, Double> wages = employee.getValue();
    
            System.out.printf("%-15s", name);
            for (Integer date : dateColumns) {
                Double value = wages.get(date);
                if (value != null && value != 0.0) {
                    System.out.printf("%-12.0f", value); // Print with 2 decimal places
                } else {
                    System.out.printf("%-12s", ""); // Skip null or empty cells
                }
            }
            System.out.println();
        }
    }
    

    public static void main(String[] args) {
        // Test the function with your data
    }
}

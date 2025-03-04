package com.example;

import java.util.*;
import java.util.Map;
import com.example.Object.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class EmployeeIncomeComparator {

    public static void compareTotalIncomes(List<Employee> employees, Map<String, Double> totalIncomeMap) {
        for (Employee employee : employees) {
            String empCode = employee.getEmployeeNumber();
            int computedTotalIncome = (int) Math.round(computeTotalIncome(employee));

            if (totalIncomeMap.containsKey(empCode)) {
                int extractedTotalIncome = (int) Math.round(totalIncomeMap.get(empCode));

                System.out.printf("Employee: %s (%s)%n", employee.getName(), empCode);
                System.out.printf("   Computed Total Income: %d%n", computedTotalIncome);
                System.out.printf("   Extracted Total Income: %d%n", extractedTotalIncome);
                System.out.printf("   %s%n", (computedTotalIncome == extractedTotalIncome) ? "✅ Match" : "❌ Mismatch");
                System.out.println("--------------------------------------------------");
            } else {
                System.out.printf("❌ Employee %s (%s) not found in extracted data%n", employee.getName(), empCode);
            }
        }
    }

    public static String compareTotalIncomesAsJson(List<Employee> employees, Map<String, Double> totalIncomeMap) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode resultsArray = mapper.createArrayNode();
        
        for (Employee employee : employees) {
            ObjectNode employeeNode = mapper.createObjectNode();
            String empCode = employee.getEmployeeNumber();
            int computedTotalIncome = (int) Math.round(computeTotalIncome(employee));
            employeeNode.put("employeeName", employee.getName());
            employeeNode.put("employeeNumber", empCode);
            employeeNode.put("computedTotalIncome", computedTotalIncome);
            
            if (totalIncomeMap.containsKey(empCode)) {
                int extractedTotalIncome = (int) Math.round(totalIncomeMap.get(empCode));
                employeeNode.put("extractedTotalIncome", extractedTotalIncome);
                employeeNode.put("match", computedTotalIncome == extractedTotalIncome);
            } else {
                employeeNode.put("error", "Employee not found in extracted data");
            }
            resultsArray.add(employeeNode);
        }
        
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultsArray);
        } catch (Exception e) {
            return "{}"; // Return empty JSON in case of error
        }
    }

    private static double computeTotalIncome(Employee employee) {
        double total = 0.0;
        Map<String, String> shiftTypeAliases = new HashMap<>();
        shiftTypeAliases.put("WK-D", "WK");
        shiftTypeAliases.put("WK-N", "WK");

        for (WorkDay workDay : employee.getWorkDay()) {
            for (Map.Entry<String, Double> entry : workDay.getHourWageType().entrySet()) {
                String wageType = entry.getKey();
                Double hoursWorked = entry.getValue();
                String normalizedWageType = shiftTypeAliases.getOrDefault(wageType, wageType);
                Double hourlyRate = employee.getTypeWage().getOrDefault(normalizedWageType, 0.0);
                total += hoursWorked * hourlyRate;
            }
        }
        return total;
    }
}

package com.example.Display;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.Object.Employee;
import com.example.Object.WorkDay;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {

    public static String getEmployeeDetailsAsJson(Employee employee) {
        try {
            Map<String, Object> employeeDetails = new HashMap<>();
            employeeDetails.put("name", employee.getName());
            employeeDetails.put("employeeNumber", employee.getEmployeeNumber());

            double totalEarnings = 0;
            List<WorkDay> workDays = employee.getWorkDay();
            Map<String, Object> workDayDetails = new HashMap<>();

            // Define shift type mappings
            Map<String, String> shiftTypeAliases = Map.of(
                "WK-D", "WK",
                "WK-N", "WK"
            );

            for (WorkDay workDay : workDays) {
                Map<String, Object> dayDetails = new HashMap<>();
                double dailyEarnings = 0;

                for (Map.Entry<String, Double> entry : workDay.getHourWageType().entrySet()) {
                    String shiftType = entry.getKey();
                    Double hoursWorked = entry.getValue();
                    String normalizedShiftType = shiftTypeAliases.getOrDefault(shiftType, shiftType);
                    Double wageRate = employee.getTypeWage().getOrDefault(normalizedShiftType, 0.0);

                    double earnings = hoursWorked * wageRate;
                    dailyEarnings += earnings;

                    Map<String, Object> shiftDetails = new HashMap<>();
                    shiftDetails.put("shiftType", shiftType);
                    shiftDetails.put("normalizedShiftType", normalizedShiftType);
                    shiftDetails.put("hoursWorked", hoursWorked);
                    shiftDetails.put("hourlyRate", wageRate);
                    shiftDetails.put("earnings", earnings);

                    dayDetails.put(shiftType, shiftDetails);
                }

                totalEarnings += dailyEarnings;
                dayDetails.put("totalDailyEarnings", dailyEarnings);
                workDayDetails.put(workDay.getDate(), dayDetails);
            }

            employeeDetails.put("workDays", workDayDetails);
            employeeDetails.put("totalEarnings", totalEarnings);

            // Convert Map to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeDetails);

        } catch (Exception e) {
            return "{\"error\": \"Failed to generate JSON\"}";
        }
    }
}

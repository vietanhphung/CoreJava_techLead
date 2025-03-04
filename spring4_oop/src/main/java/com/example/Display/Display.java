package com.example.Display;
import java.util.Map;
import com.example.Object.Employee;
import com.example.Object.WorkDay;

public class Display {

    public static void displayEmployeeDetails(Employee employee) {
        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Employee Number: " + employee.getEmployeeNumber());
        System.out.println("------------------------------------------------");

        double totalEarnings = 0;

        // Define shift type mappings
        Map<String, String> shiftTypeAliases = Map.of(
            "WK-D", "WK",
            "WK-N", "WK"
        );

        for (WorkDay workDay : employee.getWorkDay()) {
            System.out.println("Date: " + workDay.getDate());

            double dailyEarnings = 0;
            for (Map.Entry<String, Double> entry : workDay.getHourWageType().entrySet()) {
                String shiftType = entry.getKey();
                Double hoursWorked = entry.getValue();

                // Normalize shift type using alias map
                String normalizedShiftType = shiftTypeAliases.getOrDefault(shiftType, shiftType);

                Double wageRate = employee.getTypeWage().getOrDefault(normalizedShiftType, 0.0); // Use normalized shift type

                double earnings = hoursWorked * wageRate;
                dailyEarnings += earnings;

                System.out.printf("   Shift Type: %s (%s), Hours Worked: %.2f, Hourly Rate: %.2f, Earnings: %.2f%n",
                        shiftType, normalizedShiftType, hoursWorked, wageRate, earnings);
            }

            totalEarnings += dailyEarnings;
            System.out.printf("   Total Earnings for %s: %.2f%n", workDay.getDate(), dailyEarnings);
            System.out.println("------------------------------------------------");
        }

        System.out.printf("Total Earnings for Employee %s: %.2f%n", employee.getName(), totalEarnings);
        System.out.println("========================================================\n\n");
    }
}

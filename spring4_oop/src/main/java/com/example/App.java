package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.DataExtract.ExtractEmployeeData;
import com.example.DataExtract.ExtractTotalIncome;
import com.example.DataExtract.ExtractWorkDayData;
import com.example.Display.Display;
import com.example.Display.JSON;
import com.example.Object.Employee;
import com.example.Object.WorkDay;

import org.apache.poi.ss.usermodel.Sheet;

public class App {
    public static void main(String[] args) {
        String filePath = "BangCong.xlsx";  

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);  

            ExtractEmployeeData employees = new ExtractEmployeeData();
            employees.createEmployees(sheet);
            
            ExtractWorkDayData workDayData = new ExtractWorkDayData(sheet);
            Map<String, ArrayList<WorkDay>> workLog = workDayData.extractWorkDaysData();

            for(Employee employee : employees.getEmployees()){
                employee.setWorkDays(workLog.get(employee.getEmployeeNumber()));
                Display.displayEmployeeDetails(employee);
                //System.out.println(JSON.getEmployeeDetailsAsJson(employee));
            }
            
            

            //----------------
            int sumColumn = 16;
            ExtractTotalIncome extractedTotalIncome = new ExtractTotalIncome(sheet);
            Map<String, Double> totalIncomes = extractedTotalIncome.getTotalIncome(sumColumn);
            EmployeeIncomeComparator.compareTotalIncomes(employees.getEmployees(), totalIncomes);
            //System.out.println(EmployeeIncomeComparator.compareTotalIncomesAsJson(employees.getEmployees(), totalIncomes));
            

            

        } catch (IOException e) {  
            e.printStackTrace();
        }
    }
}

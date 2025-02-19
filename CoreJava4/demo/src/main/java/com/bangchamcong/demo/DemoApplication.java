package com.bangchamcong.demo;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        
        // Example file path - Change this based on your actual file location
        String filePath = "/Users/connor/Desktop/files/project/CoreJava_techLead/CoreJava4/BangCong.xlsx";


        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean result = wageTypeFullyDeclared(sheet);
            System.out.println("Wage Type Fully Declared: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to check if wage types are fully declared
    public static Boolean wageTypeFullyDeclared(Sheet sheet) {
        Row row = sheet.getRow(5);
        if (row == null) {
            System.out.println("Row 6 is missing in the Excel file.");
            return false;
        }

        Iterator<Cell> cellIterator = row.cellIterator();
        Set<String> set = new HashSet<>();
        Set<String> tempSet = new HashSet<>();
        boolean collectedAllWageTypes = false;
        int dailyWageCol = -1;

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getColumnIndex() > 3 && !cell.getStringCellValue().equals("$") && !collectedAllWageTypes) {
                set.add(cell.getStringCellValue());
            }
            if (cell.getStringCellValue().trim().equalsIgnoreCase("tổng lương")) {
                collectedAllWageTypes = true;
                dailyWageCol = cell.getColumnIndex() + 1;
            }
            if (collectedAllWageTypes && cell.getColumnIndex() >= dailyWageCol) {
                if (tempSet.size() != set.size()) {
                    tempSet.add(cell.getStringCellValue());
                } else {
                    if (!tempSet.equals(set)) {
                        System.out.println("Wage Type Column not fully defined");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

package com.spring.main.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.ui.Model;


@SpringBootApplication
public class Spring4Service_Main {


    // Check if a row is empty
    private static boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK && cell.getCellType() != CellType._NONE) {
                return false;
            }
        }
        return true;
    }

    // Check if wage types are fully declared
    public static boolean wageTypeFullyDeclared(Sheet sheet) {
        Row row = sheet.getRow(5);
        if (row == null) {
            System.out.println("Row 6 is missing in the Excel file.");
            return false;
        }

        Set<String> set = new HashSet<>();
        Set<String> tempSet = new HashSet<>();
        boolean collectedAllWageTypes = false;
        int dailyWageCol = row.getLastCellNum() - 1;

        for (Cell cell : row) {
            String cellValue = cell.getStringCellValue().trim().toLowerCase();
            if (cell.getColumnIndex() > 2 && cellValue.equals("wk-d") && !collectedAllWageTypes) {
                collectedAllWageTypes = true;
                dailyWageCol = cell.getColumnIndex() + 3;
            }

            if (!set.contains(cellValue) && !collectedAllWageTypes && !cellValue.equals("$")) {
                set.add(cellValue);
            }

            if (collectedAllWageTypes && cell.getColumnIndex() >= dailyWageCol && 
                !cellValue.equals("wk-d") && !cellValue.equals("wk-n")) {
                tempSet.add(cellValue);
                if (tempSet.size() == set.size()) {
                    if (!tempSet.equals(set)) {
                        System.out.println("Wage Type Column not fully defined");
                        return false;
                    }
                    tempSet.clear();
                }
            }
        }
        return true;
    }

    // Retrieve merged regions for a given row
    public static Map<Integer, int[]> getMergedRegionsForRow(Sheet sheet, int targetRow) {
        Map<Integer, int[]> mergedRegionsMap = new HashMap<>();
    
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            if (mergedRegion.getFirstRow() <= targetRow && mergedRegion.getLastRow() >= targetRow) {
                Row row = sheet.getRow(targetRow);
                if (row != null) {
                    Cell firstCell = row.getCell(mergedRegion.getFirstColumn());
                    if (firstCell != null && firstCell.getCellType() == CellType.NUMERIC) {
                        int key = (int) firstCell.getNumericCellValue();
                        mergedRegionsMap.put(key, new int[]{mergedRegion.getFirstColumn(), mergedRegion.getLastColumn()});
                    }
                }
            }
        }
        return mergedRegionsMap;
    }
    

    // get sum of hours for each employee
    public static Map<String,Integer> hourSum(Map<String, Map<Integer,Integer>> map){
        String name = "";
        int sum = 0;
        Map<String, Integer> output = new HashMap<>();
        for (Map.Entry<String, Map<Integer, Integer>> entry : map.entrySet()) {
            name = entry.getKey();
            for (int hours : entry.getValue().values()) { 
                sum = sum + hours;
            }
            output.put(name, sum);
            sum = 0;
        }
        return output;
        
    }





    // Display employee hour table
    public static Map<String, Map<Integer,Integer>> displayEmployeeHourTable(Sheet sheet) {
        int pointer = -1;
        Row typeRow = sheet.getRow(5);
        Iterator<Cell> rowIterator = typeRow.iterator();

        while (rowIterator.hasNext()) {
            Cell cell = rowIterator.next();
            if (cell.getStringCellValue().equals("$")) {
                pointer = cell.getColumnIndex() + 2;
            }
        }
        if (pointer == -1) {
            throw new IllegalStateException("Pointer was not initialized");
        }

        Map<Integer, int[]> dateSpan = getMergedRegionsForRow(sheet, 3);
        Map<String, Map<Integer, Integer>> dateHourSum = new HashMap<>();

        for (Row row : sheet) {
            if (row.getRowNum() > 5 && !isRowEmpty(row)) {
                String name = "";
                int sum = 0;
                int date = 0;
                Map<Integer, Integer> dailySum = new HashMap<>();

                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 2) {
                        name = cell.getStringCellValue();
                    }
                    if (cell.getCellType() == CellType.NUMERIC && cell.getColumnIndex() > pointer) {
                        for (Map.Entry<Integer, int[]> entry : dateSpan.entrySet()) {
                            int[] range = entry.getValue();
                            if (cell.getColumnIndex() >= range[0] && cell.getColumnIndex() <= range[1]) {
                                sum += (int) cell.getNumericCellValue();
                                date = entry.getKey();
                            }
                        }
                        if (date != 0) {
                            dailySum.put(date, dailySum.getOrDefault(date, 0) + sum);
                            sum = 0;
                            date = 0;
                        }
                    }
                }
                if (!name.isEmpty()) {
                    dateHourSum.put(name, dailySum);
                }
            }
        }
        /* 
        Map<String, Integer> sumHours = hourSum(dateHourSum);
        System.out.println("Employee Hour Table:");
        for (Map.Entry<String, Map<Integer, Integer>> entry : dateHourSum.entrySet()) {
            System.out.println("Name: " + entry.getKey());
            for (Map.Entry<Integer, Integer> dateEntry : entry.getValue().entrySet()) {
                System.out.println("  Date: " + dateEntry.getKey() + " | Hours: " + dateEntry.getValue());
            }
            System.out.println("    Sum of Hours: " + sumHours.get(entry.getKey()));
        }
            */
        return dateHourSum;
    }
    
}

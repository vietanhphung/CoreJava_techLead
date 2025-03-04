package com.example.DataExtract;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import com.example.Object.WorkDay;

public class ExtractWorkDayData {
    private Sheet sheet;
    private Map<String, int[]> indexMap;

    public ExtractWorkDayData(Sheet sheet) {
        this.indexMap = getMergedRegionsForRow(sheet, 3);
        this.sheet = sheet;
    }

    // Read work log and create a Map of WorkDays for each employee
    public Map<String, ArrayList<WorkDay>> extractWorkDaysData() {
        int startColumnIndex = 17;
        int startRowIndex = 6;
        Map<String, ArrayList<WorkDay>> output = new HashMap<>();
        String currentEmployee = "";
        ArrayList<WorkDay> workDays = new ArrayList<>();
        Map<String, Double> typeHour = new HashMap<>();
        String currentDay = "";

        for (Row row : sheet) {
            if (row.getRowNum() < startRowIndex) continue;

            String employeeCode = "";
            Cell empCell = row.getCell(1);
            if (empCell != null && empCell.getCellType() == CellType.STRING) {
                employeeCode = empCell.getStringCellValue();
            }

            // If a new employee is encountered, store previous employee's data
            if (!employeeCode.isEmpty() && !employeeCode.equals(currentEmployee)) {
                if (!currentEmployee.isEmpty()) {
                    // Store last workday before switching employees
                    if (!currentDay.isEmpty() && !typeHour.isEmpty()) {
                        workDays.add(new WorkDay(currentDay, new HashMap<>(typeHour)));
                    }
                    output.put(currentEmployee, new ArrayList<>(workDays));
                }

                // Reset variables for new employee
                currentEmployee = employeeCode;
                workDays.clear();
                typeHour.clear();
                currentDay = "";
            }

            for (Cell cell : row) {
                if (cell.getColumnIndex() > startColumnIndex && cell.getCellType() == CellType.NUMERIC) {
                    String date = getDateByColumn(indexMap, cell.getColumnIndex());

                    if (!date.equals(currentDay)) {
                        // Store previous day's data
                        if (!currentDay.isEmpty() && !typeHour.isEmpty()) {
                            workDays.add(new WorkDay(currentDay, new HashMap<>(typeHour)));
                            typeHour.clear();
                        }
                        currentDay = date;
                    }

                    String workType = getWorkType(cell.getColumnIndex(), sheet);
                    double cellValue = cell.getNumericCellValue();

                    // Ensure only positive values are considered
                    if (cellValue > 0) {
                        typeHour.put(workType, typeHour.getOrDefault(workType, 0.0) + cellValue);
                    }
                }
            }
        }

        // Store last employee's data
        if (!currentEmployee.isEmpty() && !workDays.isEmpty()) {
            workDays.add(new WorkDay(currentDay, new HashMap<>(typeHour)));
            output.put(currentEmployee, workDays);
        }

        return output;
    }

    // --- Helper Methods ---
    
    public static Map<String, int[]> getMergedRegionsForRow(Sheet sheet, int targetRow) {
        Map<String, int[]> mergedRegionsMap = new HashMap<>();

        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            if (mergedRegion.getFirstRow() <= targetRow && mergedRegion.getLastRow() >= targetRow) {
                Row row = sheet.getRow(targetRow);
                if (row != null) {
                    Cell firstCell = row.getCell(mergedRegion.getFirstColumn());
                    if (firstCell != null) {
                        String key;
                        if (firstCell.getCellType() == CellType.NUMERIC) {
                            key = String.valueOf((int) firstCell.getNumericCellValue());
                        } else {
                            key = firstCell.getStringCellValue();
                        }

                        // Ensure unique key by appending "ofNextMonth" if needed
                        while (mergedRegionsMap.containsKey(key)) {
                            key += " ofNextMonth";
                        }

                        mergedRegionsMap.put(key, new int[]{mergedRegion.getFirstColumn(), mergedRegion.getLastColumn()});
                    }
                }
            }
        }
        return mergedRegionsMap;
    }

    public static String getWorkType(int columnIndex, Sheet sheet) {
        int searchRow = 5;
        Row row = sheet.getRow(searchRow);
        if (row == null) return "Unknown";
        Cell cell = row.getCell(columnIndex);
        return (cell != null) ? cell.getStringCellValue() : "Unknown";
    }

    public static String getDateByColumn(Map<String, int[]> map, int index) {
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            int[] values = entry.getValue();
            if (values.length == 2 && values[0] <= index && values[1] >= index) {
                return entry.getKey();
            }
        }
        return "Unknown Date";  // Avoid returning null
    }
}

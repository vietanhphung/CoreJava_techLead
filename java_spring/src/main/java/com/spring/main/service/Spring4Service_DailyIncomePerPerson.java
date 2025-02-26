package com.spring.main.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Spring4Service_DailyIncomePerPerson {

    Sheet sheet;

    public Spring4Service_DailyIncomePerPerson(Sheet sheet){
        this.sheet = sheet;
    }


    //helper methods------------------------------


    public static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;  // Found a non-empty cell
            }
        }
        return true;
    }
    


    public Map<String, ArrayList<Integer>> getWageTypeIndex(int rowIndex, int pointer) {
        Map<String, ArrayList<Integer>> colIndexType = new HashMap<>();
        Row typeRow = sheet.getRow(rowIndex); 
    
        if (typeRow == null) {
            return colIndexType; 
        }
    
        for (Cell cell : typeRow) {
            if (cell.getColumnIndex() >= pointer && cell.getCellType() == CellType.STRING) {
                colIndexType.computeIfAbsent(cell.getStringCellValue(), k -> new ArrayList<>()).add(cell.getColumnIndex());
            }
        }
        return colIndexType;
    }
    


    public Map<String,Integer> getWageOfShiftType(){
        Map<String, Integer> wageIndex = new HashMap<>();
        Row row = sheet.getRow(5);
        for (Cell cell : row){
            if (cell.getColumnIndex() >2 && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().equals("$")){
                
                if ( wageIndex.containsKey(cell.getStringCellValue() )){
                    break;
                }
                wageIndex.put(cell.getStringCellValue(), cell.getColumnIndex()+1);
               
            }
        }
        wageIndex.put("WK-D",wageIndex.get("WK-N"));
        return wageIndex;

    }


    public Map<String, Map<String, Double>> getEmployeePerHourType(int startPointer) {
        Map<String, Map<Integer, Double>> out = new HashMap<>();
        Map<String, Integer> index = getWageOfShiftType();
        Map<String, Map<String, Double>> employeePerHour = new HashMap<>();
        
    

        Map<Integer, Double> temp2 = new HashMap<>();
        String name ="";
        for (Row row : sheet) {
            if (row == null || row.getPhysicalNumberOfCells() == 0) continue; // Skip null or empty rows
            Map<String, Double> temp0 =new HashMap<>();
            for (Cell cell : row) {   
                if (cell == null || cell.getCellType() == CellType.BLANK) {continue;}
                //add name to map
                if (cell.getColumnIndex() == 2 && cell.getCellType() == CellType.STRING && cell.getRowIndex() >5) {
                    name = cell.getStringCellValue();   
                    
                }
                
                
                // create <typeOfShift, dollar/Hour> hash map 
                if (cell.getColumnIndex() > 2 && cell.getColumnIndex() < startPointer && cell.getRowIndex() > 5) {
                    
                    for (Map.Entry<String, Integer> entry : index.entrySet()) {
                        
                        if (entry.getValue() == cell.getColumnIndex()  ) { 
                            temp0.put(entry.getKey(), cell.getNumericCellValue());
                            employeePerHour.put(name, temp0);
                          
                        }
                    }
                }                 
            }
        }

        return employeePerHour;
    }
    // getEmployeePerHourType {Nguyen van A : {TC1 : 382948302 , GC : 3214798}}


    //-----------------------Main methods-------------------------------------------

    public Map<String, Map<Integer,Double>> getSumDailyPerEmployee(Sheet sheet, int startPointer){
        Map<String, Map<String, Double>> employeePerHour = getEmployeePerHourType(18); 
        Map<String, ArrayList<Integer>> wageTypeIndex = getWageTypeIndex(5, 3);
        Map<Integer, int[]> mergedRegions = Spring4Service_Main.getMergedRegionsForRow(sheet, 3);

        Map<String, Map<Integer, Double>> sumDailyPerEmployee = new HashMap<>();


        for (Row row : sheet) {
            if (row == null || row.getPhysicalNumberOfCells() == 0) continue; // Skip null or empty rows
            
            Map<Integer, Double> temp0 =new HashMap<>();
            String name="";
            String type="";
            int date=0;
            Double hour = 0.0;
            Double perHour =  0.0;
            Map<Integer, Double> date_Income =new HashMap<>();
            for (Cell cell : row) {   

                
                if (cell == null || cell.getCellType() == CellType.BLANK || cell.getRowIndex() <=5) continue;

                //extract name in row
                if(cell.getColumnIndex() == 2){
                    name = cell.getStringCellValue();
                }
                //extract date, hour of work, check type of work, wage
                if(cell.getColumnIndex() >= startPointer && cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() != 0){
                    //check type of work:
                    for (Map.Entry<String, ArrayList<Integer>> typeOfWork : wageTypeIndex.entrySet()){
                        if(typeOfWork.getValue().contains(cell.getColumnIndex())){
                            type = typeOfWork.getKey();
                        }
                    }
                    //extreac hour of work
                    hour = (Double) cell.getNumericCellValue();

                    //get wage of employee
                    perHour = (Double) employeePerHour.getOrDefault(name, new HashMap<>()).getOrDefault(type, 0.0);
   
                    //get date
                    for (Map.Entry<Integer, int[]> dateMap : mergedRegions.entrySet()) {
                        int[] range = dateMap.getValue();  // Get the int[] array
                        if (cell.getColumnIndex() >= range[0] && cell.getColumnIndex() <= range[1]) {
                            date = dateMap.getKey();  
                        }
                    }
                    

                }
                // get daily income, add to map
                Double income = perHour*hour;
                date_Income.put(date, (double) income);

            }
            sumDailyPerEmployee.put(name, date_Income);
        }
        return sumDailyPerEmployee;
    }
    

// Get Income of the Month
public Map<String, Object[]> incomeOfMonth(Sheet sheet, Map<String, Map<Integer, Double>> map, int pointer) {
    Map<String, Object[]> output = new HashMap<>();
    Map<String, Double> sumReference = new HashMap<>();

    double sum; // Use primitive `double` to avoid unnecessary unboxing

    // Create map <name: value in column Q>
    for (Row row : sheet) {
        if (isRowEmpty(row) || row.getRowNum() <6) 
            continue; // Skip null or empty rows
        String name = "";
        sum = 0.0; // Use double

        for (Cell cell : row) {
            if (cell.getColumnIndex() > pointer){
                break;
            }
            if (cell.getColumnIndex() == 2) {
                name = cell.getStringCellValue();
            }
            if (cell.getColumnIndex() == pointer) {
                sum = cell.getNumericCellValue();
            }
            
        }

        sumReference.put(name, (double) Math.round(sum));

    }


    // Create map <name: calculated sum> and compare
    for (Map.Entry<String, Map<Integer, Double>> entry : map.entrySet()) {
        sum = 0.0;

        for (Double value : entry.getValue().values()) { 
            sum += value;
        }
        

        Object[] outputValue = new Object[2];
        outputValue[0] = (double) Math.round(sum);
        
        // Ensure sumReference.get(entry.getKey()) is not null before comparison
        Double referenceSum = sumReference.get(entry.getKey());
        outputValue[1] = (referenceSum != null && Double.compare(Math.round(sum), Math.round(referenceSum)) == 0);
        
        output.put(entry.getKey(), outputValue);
        
    }

    return output;
}

    
  


    public static void main(String[] args) {

    }
}
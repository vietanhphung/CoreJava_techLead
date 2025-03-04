
package com.example.DataExtract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.*;
import com.example.Object.Employee;

import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import java.util.regex.Pattern;




public class ExtractEmployeeData {

    private ArrayList<Employee> employees;

    public ExtractEmployeeData() {
        this.employees = new ArrayList<>();
    }


    public Map<Integer, String> getWageTypeColumn(Row row) {
        Map<Integer, String> col_typeName = new HashMap<>();
    
        for (Cell cell : row) {
            int index = cell.getColumnIndex();  
            String type = cell.getStringCellValue().trim(); 
            Pattern pattern = Pattern.compile(".*WK.*", Pattern.CASE_INSENSITIVE);
    
            // Ensure index is in the expected range
            if (index > 2 && index < 16) {
                if (!type.equals("$")) {  
                    if (pattern.matcher(type).matches()){
                        col_typeName.put(index+2, "WK"); //WK-D and WK-N to WK
                        break;
                    }
                    else {col_typeName.put(index+1, type);}
                }
                
            }
        }
        return col_typeName;
    }
    

    public void createEmployees(Sheet sheet){
        String employeeNum ;
        String employeeName ;
        Map<String, Double> wageType = new HashMap<>();
        Map<Integer, String> col_typeName = new HashMap<>();


        for (Row row : sheet) { 
            employeeNum = "";
            employeeName = "";
            wageType = new HashMap<>();
            if (row == null) continue; 
            if (row.getRowNum() == 5) {col_typeName = getWageTypeColumn(row);}  
            if (row.getRowNum() > 5){ 
                for (Cell cell : row){
                    //extract data to create Employee Objects
                    if(cell.getColumnIndex() == 1) {employeeNum = cell.getStringCellValue();}
                    if(cell.getColumnIndex() == 2) {employeeName = cell.getStringCellValue();}
                    if(col_typeName.containsKey(cell.getColumnIndex())){
                        wageType.put(col_typeName.get(cell.getColumnIndex()), cell.getNumericCellValue());
                    }
                }
                
            }
            if (!employeeNum.isEmpty() && !employeeName.isEmpty()) {
                Employee employee = new Employee(employeeName, employeeNum, new ArrayList<>(), wageType);
                if (employees == null) employees = new ArrayList<>(); 
                employees.add(employee);
            }
        }

    }

    public List<Employee> getEmployees() {
        return employees;
    }




}
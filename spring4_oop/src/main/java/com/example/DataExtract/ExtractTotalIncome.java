package com.example.DataExtract;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellType;


public class ExtractTotalIncome {

    private Sheet sheet;

    public ExtractTotalIncome(Sheet sheet){
        this.sheet = sheet;
    }

    public Map<String, Double> getTotalIncome(int columnIndex){
        int startRowIndex = 6;
        Map<String, Double> output = new HashMap<>();
        for(Row row : sheet){
            if(row.getRowNum() < startRowIndex || isRowEmpty(row)){continue;}
            String employeeCode = row.getCell(1).getStringCellValue();
            Double value = row.getCell(columnIndex).getNumericCellValue();
            output.put(employeeCode,value);
            
        }
        return output;
    }


 



    //----------helper methods
    private boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // Found a non-blank cell
            }
        }
        return true; // All cells are blank
    }

  

}

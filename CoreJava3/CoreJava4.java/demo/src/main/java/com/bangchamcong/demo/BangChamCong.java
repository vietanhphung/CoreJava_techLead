import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class BangChamCong {

    public void readExcelFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator <Row> rowIterator = sheet.iterator();
            
            wageTypeFullyDeclared(sheet);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



// helper methods---------------


    public static Boolean wageTypeFullyDeclared(Sheet sheet){
        Row row = sheet.getRow(6);
        Iterator<Cell> cellIterator = row.Iterator();
        Set<String> set = new HashSet<>();
        Set<String> tempSet = new HashMap<>();
        Boolean collectedAllWageTypes = false;
        int dailyWageCol;
        while(cell.hasNext()){
            if (cell.getColumnValue() > 3 && !cell.getValue().equals("$") && !collectedAllWageTypes){
                set.add(cell.getValue());
            }
            if (cell.getValue().toLowerCase() == "tổng lương"){
                collectedAllWageTypes = true;
                dailyWageCol = cell.getColumnValue() + 1;
            }
            if (collectedAllWageTypes && cell.getColumnValue() >= dailyWageCol ){
                if (tempSet.size() != set.size()){
                    tempSet.add(cell.getValue);
                }
                else{
                    if (tempSet != set){
                        System.out.println("Wage Type Column not fully defined");
                        return false;
                    }
                }
            }
            
        }
        return true;
        
    }











    public static void main(String[] args) {
        BangChamCong reader = new BangChamCong();
        reader.readExcelFile("BangCong.xlsx");

    }
}

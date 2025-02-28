package com.spring.main.controller;

import com.spring.main.service.Spring4Service_DailyIncomePerPerson;

import com.spring.main.service.Spring4Service_Main;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

@Controller
@RequestMapping("/spring4")
public class Spring4Controller {

    private static final String FILE_PATH = "/Users/connor/Desktop/files/project/CoreJava_techLead/java_spring/BangCong.xlsx";

    @GetMapping("/report")
    public String getReport(Model model) {
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Map<String, Map<Integer,Integer>> dateHourSum = (Spring4Service_Main.displayEmployeeHourTable(sheet));
            Map<String,Integer> hourSum = Spring4Service_Main.hourSum(dateHourSum);
    
            // Get Employee Hour Table
            Spring4Service_DailyIncomePerPerson incomeService = new Spring4Service_DailyIncomePerPerson(sheet);
            Map<String, Map<Integer, Double>> sumDaily = incomeService.getSumDailyPerEmployee(sheet, 18);
            Map<String, Object[]> incomeMap = incomeService.incomeOfMonth(sheet, sumDaily, 16);

            TreeSet<Integer> dateColumns = new TreeSet<>();
            sumDaily.values().forEach(map -> dateColumns.addAll(map.keySet()));

            // Add attributes for Thymeleaf
            model.addAttribute("sumHours", hourSum);
            model.addAttribute("dateHourSum", dateHourSum);
            model.addAttribute("incomeData", incomeMap);
            model.addAttribute("sumDaily", sumDaily);
            model.addAttribute("dateColumns", dateColumns);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error reading Excel file: " + e.getMessage());
        }

        return "spring4";
    }
}

package com.example.Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Employee implements Iterable<WorkDay> {
    private String name;
    private String employeeNumber;
    private ArrayList<WorkDay> workDay;
    private Map<String, Double> typeWage;

    public Employee(String name, String employeeNumber, ArrayList<WorkDay> workDay, Map<String, Double> typeWage) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.workDay = workDay;
        this.typeWage = typeWage;
    }

    //---Setters and Getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public ArrayList<WorkDay> getWorkDay() {
        return workDay;
    }

    public void setWorkDays(ArrayList<WorkDay> workDay) {
        this.workDay = workDay;
    }

    public Map<String, Double> getTypeWage() {
        return typeWage;
    }

    public void setTypeWage(Map<String, Double> typeWage) {
        this.typeWage = typeWage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", workDay=" + workDay.toString() +
                ", typeWage=" + typeWage.toString() +
                '}';
    }

    @Override
    public Iterator<WorkDay> iterator() {
        return workDay.iterator();
    }
}

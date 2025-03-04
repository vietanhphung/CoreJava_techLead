package com.example.Object;

import java.util.Map;



public class WorkDay {
    private String date;
    private Map<String, Double> hourWageType;

    public WorkDay(String date,  Map<String, Double> hourWageType) {
        this.date = date;
        this.hourWageType = hourWageType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public Map<String, Double> getHourWageType() {
        return hourWageType;
    }

    public void setHourWageType(Map<String, Double> hourWageType) {
        this.hourWageType = hourWageType;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "date=" + date +
                ", hourWageType=" + hourWageType +
                '}';
    }
}
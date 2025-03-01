

package com.java_sql.main.service.service_spring;
import java.util.List;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class Spring1Service {

    public double sum(Double a, Double b) {
        return a + b;
    }


    //2
    public int len(String s) {
        return (s.length());
    }


    //3
    public <T extends Double> Double sqr(Double a) {
        return (a.doubleValue() * a.doubleValue());
    }


    //4
    public Double  largestNum(List<Double> list){
        Double max = list.get(0);
        for(Double i : list){
            if(i.doubleValue() > max.doubleValue()){
                max = i;
            }
        }
        return max;
    }


    //5
    public String shortestString(List<String> list){
        String min = list.get(0);
        for(String i : list){
            if(i.length() < min.length()){
                min = i;
            }
        }
        return (min);
    }


    //6
    public List<Double> sortList(List<Double> list){
        list.sort(null);
            return list;
        }
    

    //7
    public List<String> sortString(List<String> list){
        list.sort(null);
        return list;
    }

    //8
    public Double median(List<Double> list){
        list.sort(null);
        int s = list.size();
        if (s %2 == 0){
            return ( list.get(s/2).doubleValue() + list.get(s/2 - 1).doubleValue() ) / 2;
        }
        return list.get(list.size()/2);
    }

    //9
    public Integer wordCount(String s){
        String[] w = s.split("\s");
        return w.length;
    }

    //10
    public Integer aCount(List<String> list){
        int count = 0;
        for(String i : list){
            if(i.contains("a")){
                count++;
            }
        }
        return count;
    }




    
}
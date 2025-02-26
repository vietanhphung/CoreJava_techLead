
package com.spring.main.service;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class Spring2Service {

    //18
    public  int count(Set<Integer> s){
        return s.size();
    }
    
    //16
    public  String list(Set<Integer> a){
        List<Integer> list = new ArrayList<>();
        for( int i : a){
            list.add(i);
        }
        list.sort(null);
        return (list.get(0) + " and " + list.get(list.size() -1)).toString();
    }


    //15
        public  Set<Integer> overLap(Set<Integer> a, Set<Integer> b){
        Set<Integer> set = new HashSet<>();
        for( int i : a){
            set.add(i);
        }
        for(int i : b){
            set.add(i);
        }
        return (set);
    }

    //14
    public  List<Integer> overLap2(Set<Integer> a, Set<Integer> b){
        List<Integer> overLap = new ArrayList<>();
        for( int i : a){
            if(b.contains(i)){
                overLap.add(i);
            }
        }
        return (overLap);
    }


    //13
    public  List<Integer> duplicate( List<Integer> list){
        List<Integer> l = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();

        for(int i : list){
            if (set.contains(i)){
                l.add(i);
            }
            else{
                set.add(i);
            }
        }
        return (l);
    }

    //12 -11 separate files
    //10
    public  Map<String, Integer> gradeDistribution(Map<String, Double> grades) {
        Map<String, Integer> output = new HashMap<String, Integer>();
        int high = 0;
        int faill = 0;
        int pass = 0;

        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            if (entry.getValue() >= 8.0) {
                high++;
            } else if (entry.getValue() >= 5.0 && entry.getValue() < 8.0) {
                pass++;
            } else {
                faill++;
            }
        }
        output.put("pass with high score", high);
        output.put("pass", pass);
        output.put("faill", faill);
        return output;
    }

    
    




}


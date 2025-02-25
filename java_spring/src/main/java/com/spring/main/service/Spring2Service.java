
package com.spring.main.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class Spring2Service {

    //18
    public static int count(Set<Integer> s){
        return s.size();
    }
    
    //16
    public static void list(Set<Integer> a){
        List<Integer> list = new ArrayList<>();
        for( int i : a){
            list.add(i);
        }
        list.sort(null);
        System.out.println(list.get(0) + " and " + list.get(list.size() -1));
    }


    //15
        public static void overLap(Set<Integer> a, Set<Integer> b){
        Set<Integer> set = new HashSet<>();
        for( int i : a){
            set.add(i);
        }
        for(int i : b){
            set.add(i);
        }
        System.out.println(set);
    }

    //14
    public static void overLap2(Set<Integer> a, Set<Integer> b){
        List<Integer> overLap = new ArrayList<>();
        for( int i : a){
            if(b.contains(i)){
                overLap.add(i);
            }
        }
        System.out.println(overLap);
    }


    //13
    public static void duplicate( List<Integer> list){
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
        System.out.println(l);
    }

    //11
    



}


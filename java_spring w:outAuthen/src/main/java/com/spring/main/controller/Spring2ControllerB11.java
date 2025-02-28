package com.spring.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spring2/b11")
public class Spring2ControllerB11 {

    private final Dictionary dictionary;

    public Spring2ControllerB11() {
        // Sample Data
        Map<String, String> sampleData = new HashMap<>();
        sampleData.put("apple", "A fruit that is red or green.");
        sampleData.put("java", "A popular programming language.");
        sampleData.put("spring", "A Java framework for building applications.");
        this.dictionary = new Dictionary(sampleData);
    }

    @GetMapping("/add")
    public String addWord(String a, String b){
        dictionary.add(a,b);
        return "OK";
    }

    @GetMapping("/get")
    public String getMethodName(@RequestParam String param) {
        return dictionary.get(param);
    }
    




    public static class Dictionary{
        Map<String, String> dict;;
        
        public Dictionary(Map<String, String> dict){
            this.dict = dict;
        }
        
        public void add(String key, String value){
            dict.put(key, value);
        }

        public String get(String key){
            return dict.get(key);
        }

    }

}
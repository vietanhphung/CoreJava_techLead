package com.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.main.service.Spring2Service;




@RestController
@RequestMapping("/spring2/b8")
public class Spring2ControllerB8 {

 
    private final Map<Integer, String> inventory;


    public Spring2ControllerB8(){
        this.inventory = new HashMap<Integer, String>();
    }

    @RequestMapping("/add")
    public String add(int id, String name){
        this.inventory.put(id, name);
        return ("Success");
    }

    @RequestMapping("/display")
    public String display(){
        for (Map.Entry<Integer, String> entry : this.inventory.entrySet()) {
            return ("ID: " + entry.getKey() + " Name: " + entry.getValue()).toString();
        }
        return ("end");
    }

    @RequestMapping("/getInfo")
    public String getInfo(int id){
        if (!this.inventory.containsKey(id)){
            return "ID not found";
        }
        return this.inventory.get(id);
    }


    @RequestMapping("/remove")
    public String remove(int id){
        if (!this.inventory.containsKey(id)){
            return ("ID not found");
        }
        this.inventory.remove(id);
        return ("Success");
    }

    @RequestMapping("/update")
    public String update(int id, String info){
        if (!this.inventory.containsKey(id)){
            return ("ID not found");
        }
        this.inventory.put(id, info);
        return ("Success");
    }




}
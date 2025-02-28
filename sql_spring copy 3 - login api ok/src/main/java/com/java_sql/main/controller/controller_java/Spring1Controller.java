package com.java_sql.main.controller.controller_java;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java_sql.main.service.service_spring.Spring1Service;




@RestController
@RequestMapping("/spring1")
public class Spring1Controller {


    private final Spring1Service spring1Service;
    public Spring1Controller(Spring1Service spring1Service){
        this.spring1Service = spring1Service;
    }


    // -------------------------api url -----------------------
    
    @GetMapping("/b1")
    public Double b1(@RequestParam Double a, @RequestParam Double b){
        return spring1Service.sum(a,b);
    }

    @GetMapping("/b2")
    public int b2(@RequestParam String s){
        return spring1Service.len(s);
    }

    @GetMapping("/b3")
    public Double b3(@RequestParam Double a){
        return spring1Service.sqr(a);
    }

    @GetMapping("/b4")
    public Double largestNum(@RequestParam List<Double> list) {
        return spring1Service.largestNum(list);
    }
    

    @GetMapping("/b5")
    public String b5(@RequestParam List<String> list){
        return spring1Service.shortestString(list);
    }

    @GetMapping("/b6")
    public List<Double> b6(@RequestParam List<Double> list){
        return spring1Service.sortList(list);
    }


    @GetMapping("/b7")
    public List<String> b7(@RequestParam List<String> list){
        return spring1Service.sortString(list);
    }


    @GetMapping("/b8")
    public Double b8(@RequestParam List<Double> li){
        return spring1Service.median(li);
    }

    @GetMapping("/b9")
    public int b9(@RequestParam String s){
        return spring1Service.wordCount(s);
    }

    @GetMapping("/b10")
    public int b10(@RequestParam List<String> s){
        return spring1Service.aCount(s);
    }
}
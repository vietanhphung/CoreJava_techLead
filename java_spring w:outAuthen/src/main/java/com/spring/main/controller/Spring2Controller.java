package com.spring.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.main.service.Spring2Service;
import java.util.Set;
import java.util.Map;





@RestController
@RequestMapping("/spring2")
public class Spring2Controller {


    private final Spring2Service spring2Service;
    public Spring2Controller(Spring2Service spring2Service){
        this.spring2Service = spring2Service;
    }



    //------------------controllers--------------


    @GetMapping("/b18")
    public int count(@RequestParam Set<Integer> s) {
        return spring2Service.count(s);
    }
    

    @GetMapping("/16")
    public String list(@RequestParam Set<Integer> l) {
        return spring2Service.list(l).toString();
    }
    

    @GetMapping("/15")
    public Set<Integer> overLap(@RequestParam Set<Integer> a,@RequestParam Set<Integer> b ){
        return spring2Service.overLap(a,b);
    }

    @GetMapping("/14")
    public List<Integer> overLap2(@RequestParam Set<Integer> a,@RequestParam Set<Integer> b ) {
        return spring2Service.overLap2(a, b);
    }
    
    @GetMapping("/13")
    public List<Integer> duplicate(@RequestParam List<Integer> l){
        return spring2Service.duplicate(l);
    }

    @GetMapping("/10")
    public Map<String, Integer> gradeDistribution( @RequestParam Map<String, Double> m){
         return spring2Service.gradeDistribution(m);
    }


    /*
    http://localhost:8080/spring2/b18?s=1&s=2&s=3&s=4
    http://localhost:8080/spring2/16?l=5&l=2&l=8&l=10
    http://localhost:8080/spring2/15?a=1&a=2&a=3&b=3&b=4&b=5
    http://localhost:8080/spring2/14?a=1&a=2&a=3&b=2&b=3&b=4
    http://localhost:8080/spring2/13?l=1&l=2&l=2&l=3&l=3&l=3&l=4
    http://localhost:8080/spring2/10


    B8
     http://localhost:8080/spring2/b8/add?id=1&name=Apple
     http://localhost:8080/spring2/b8/display
     http://localhost:8080/spring2/b8/getInfo?id=1
     http://localhost:8080/spring2/b8/remove?id=1
     http://localhost:8080/spring2/b8/update?id=1&info=Banana


    B9
     http://localhost:8080/spring2/b9/upload

    B11
     http://localhost:8080/spring2/b11/add?a=python&b=A powerful programming language.
     http://localhost:8080/spring2/b11/get?param=java
     http://localhost:8080/spring2/b11/get?param=unknown


    B12

     http://localhost:8080/spring2/b12/sortByName
     http://localhost:8080/spring2/b12/sortByPrice
     http://localhost:8080/spring2/b12/sortByManufactureDate
     http://localhost:8080/spring2/b12/sortByPriceAndDate







        
     */



}
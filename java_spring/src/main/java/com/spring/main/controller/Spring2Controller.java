package com.spring.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.main.service.Spring2Service;




@RestController
@RequestMapping("/spring2")
public class Spring2Controller {


    private final Spring2Service spring2Service;
    public Spring2Controller(Spring2Service spring2Service){
        this.spring2Service = spring2Service;
    }



    //------------------controllers--------------


    @GetMapping("/b18")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    



}
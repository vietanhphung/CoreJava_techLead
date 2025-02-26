package com.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.main.service.Spring3Service;




@RestController
@RequestMapping("/spring3")
public class Spring3Controller {

    private final Spring3Service service;
    public Spring3Controller (Spring3Service service){
        this.service = service;
    }

    
    @GetMapping("/convert")
    public String infixToPostfix(@RequestParam String param) {
        return service.infixToPostfix(param);
    }

    @GetMapping("/evaluate")
    public Double evaluatePostfix(@RequestParam String param) {
        return service.evaluatePostfix(param);
    }
    
    
}


/*UTF8 encoded link
    (3+5)*2     http://localhost:8080/spring3/convert?param=%283%2B5%29*2
    10 + 2 * 6  http://localhost:8080/spring3/convert?param=10%2B2*6
    3 5 + 2     http://localhost:8080/spring3/evaluate?param=3%205%20%2B%202%20*
    10 2 6 * +  http://localhost:8080/spring3/evaluate?param=10%202%206%20*%20%2B
    ( 10 + 2 ) * ( 6 / 3 )  http://localhost:8080/spring3/convert?param=%28%2010%20%2B%202%20%29%20*%20%28%206%20/%203%20%29
    10 2 + 6 3 / *  http://localhost:8080/spring3/evaluate?param=10%202%20%2B%206%203%20/%20*


 */
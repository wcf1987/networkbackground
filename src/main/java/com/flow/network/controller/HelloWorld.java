package com.flow.network.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @RequestMapping("/hello")
    public String hello(){
        //System.out.println("测试");
        return "Hello 1234";
    }
}

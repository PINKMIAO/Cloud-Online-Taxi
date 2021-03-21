package com.baven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify-code")
public class TestController {

    @GetMapping("/test")
    public String getMsg() {
        return "TestController 8001";   
    }

}

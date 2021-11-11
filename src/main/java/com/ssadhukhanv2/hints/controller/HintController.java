package com.ssadhukhanv2.hints.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HintController {
    @GetMapping("/")
    public String getHints(){
        return "Hello.!!";
    }
}

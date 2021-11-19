package com.ssadhukhanv2.hints.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HintController {
    @GetMapping("/")
    public String getHints() {
        return "Hello.!!";
    }

    @GetMapping("/portfolio")
    public String getPortfolio() {
        return "Hello from Portfolio.!!";
    }

    @GetMapping("/addHint")
    public String getHint() {
        return "Add Hint from here.!!";
    }

    @GetMapping("/updateHint")
    public String updateHint() {
        return "Hint Updated!!";
    }

    @GetMapping("/deleteHint")
    public String deleteHint() {
        return "Deleted.!!";
    }

}

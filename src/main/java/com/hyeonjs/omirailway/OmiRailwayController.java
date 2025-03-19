package com.hyeonjs.omirailway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OmiRailwayController {

    @GetMapping("/")
    public String getHelloWorld() {
        return "Hello World";
    }

}

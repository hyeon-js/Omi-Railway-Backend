package com.hyeonjs.omirailway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class OmiRailwayController {

    @GetMapping("/omi")
    public String getRunningInfo(@RequestParam(required = false) String line) {
        if (line == null) return "{\"error\":\"필수 파라미터 누락\"}";
        switch(line) {
            case "honsen":
                return "[\"본선\"]";
            case "yokaichi":
                return "[\"요카이치선\"]";
            case "taga":
                return "[\"타가선\"]";
        }
        return "{\"error\":\"올바르지 않은 파라미터 값\"}";
    }

}

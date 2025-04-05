package com.hyeonjs.omirailway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class OmiRailwayController {

    private final TrainLocatinService service = new TrainLocatinService();

    @GetMapping("/omi")
    @ResponseBody
    public ApiResponse getRunningInfo(@RequestParam(required = false) String line) {
        if (line == null) return new ApiResponse("필수 파라미터 누락");
        Station[] result = service.getLineInfo(line);
        if (result == null) return new ApiResponse("올바르지 않은 파라미터 값");
        return new ApiResponse(result);
    }


}

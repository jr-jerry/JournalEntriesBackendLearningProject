package com.G_Tech.day2LearningSpringBoot.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthController {
    @GetMapping("/health-check")
    public String controllerEndPoint(){
        return "Status ";

    }
}

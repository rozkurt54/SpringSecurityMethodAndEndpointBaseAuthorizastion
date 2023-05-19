package com.example.ss_2022_c6_e1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String demo() {
        return "Demo";
    }

    @GetMapping("/demo1")
    public String demo1() {
        return "Demo1";
    }

    @GetMapping("/demo2")
    public String demo2() {
        return "Demo2";
    }
}

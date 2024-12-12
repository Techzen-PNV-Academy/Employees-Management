package com.student.pnv.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloController {
    @RequestMapping("/hello")
    public String hello(@RequestParam(defaultValue = "") String name) {
        return "Hello world " + name;
    }
}

package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StaticWebController {

    @GetMapping("/")
    public String index(Map<String,Object> model) {
        model.put("variable","My thymeleaf model");
        model.put("num",2);
        return "index";
    }

}

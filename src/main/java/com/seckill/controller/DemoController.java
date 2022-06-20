package com.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","xxxx");
        return "hello";
    }
}

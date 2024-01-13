package com.example.application.DemoController;

import com.example.application.DemoService.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class DemoController {
    @Autowired
    DemoService service;

    @RequestMapping(value = "")
    @ResponseBody
    public String home(@RequestParam String orders) {
        return orders;
    }
}

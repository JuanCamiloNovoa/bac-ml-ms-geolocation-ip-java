package com.mercadolibre.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

    @GetMapping("/home")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/results")
    public String results(@RequestParam String ip, Model model) {
        model.addAttribute("ip", ip);
        return "results";
    }
}

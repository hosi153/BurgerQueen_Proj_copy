package com.example.burgerqueen_proj.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/home")
    public String viewHome(Model model){
        //model.addAttribute()
        return "home";
    }


}

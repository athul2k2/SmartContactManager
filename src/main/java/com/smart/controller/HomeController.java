package com.smart.controller;

import com.smart.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home - SmartContactManager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About - SmartContactManager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","Signup - SmartContactManager");
        model.addAttribute("user",new User());
        return "signup";
    }

    @RequestMapping(value = "/do_register" , method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value = "agreement",defaultValue = "false")
                               Boolean agreement,Model model){
        System.out.println("User:=>"+ user);
        System.out.println("Agreement;=>"+ agreement);


        return "signup";
    }



}

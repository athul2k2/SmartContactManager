package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

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
    public String signup(Model model) {
        model.addAttribute("title", "Signup - SmartContactManager");
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "signup";
    }

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "agreement", defaultValue = "false") Boolean agreement,
                             Model model,
                             HttpSession session) {
        try {
            if (!agreement) {
                throw new Exception("You must agree to the terms and conditions");
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);

            System.out.println("User:=>" + user);
            System.out.println("Agreement:=>" + agreement);

            this.userRepo.save(user);

            // Clear the form
            model.addAttribute("user", new User());
            // Add success message to model
            model.addAttribute("message", new Message("Successfully Registered!!", "alert-success"));
            
            return "signup";
            
        } catch (Exception e) {
            e.printStackTrace();
            // Keep form data on error
            model.addAttribute("user", user);
            // Add error message to model
            model.addAttribute("message", new Message("Error: " + e.getMessage(), "alert-danger"));
            return "signup";
        }

    }



}

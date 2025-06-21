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
    public String signup(Model model, HttpSession session){
        model.addAttribute("title","Signup - SmartContactManager");
        model.addAttribute("user",new User());
        
        // Add message from session to model if exists
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        
        return "signup";
    }

    @RequestMapping(value = "/do_register" , method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value = "agreement",defaultValue = "false")
                               Boolean agreement,Model model,
                               HttpSession session){

        try {
            if(!agreement){
                System.out.println("You have not agreed the terms and conditions");
                throw new Exception("you have not agreed the T&C");
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);

            System.out.println("User:=>"+ user);
            System.out.println("Agreement;=>"+ agreement);

            this.userRepo.save(user);

            model.addAttribute("user",new User());
            session.setAttribute("message",new Message("Successfully Registered !!","alert-success"));

            return "signup";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
            return "signup";
        }

    }



}

package com.smart.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - SmartContactManager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - SmartContactManager");
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
    public String registerUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result1,
            @RequestParam(value = "agreement", required = false) Boolean agreement,
            Model model,
            HttpSession session) {

        model.addAttribute("title", "Signup - SmartContactManager");

        try {
            // Check agreement first
            if (agreement == null || !agreement) {
                model.addAttribute("message", new Message("You must agree to the terms and conditions", "alert-danger"));
                return "signup";
            }

            // Manual validation for required fields (in case validation annotations fail)
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                model.addAttribute("message", new Message("Name is required", "alert-danger"));
                return "signup";
            }

            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                model.addAttribute("message", new Message("Email is required", "alert-danger"));
                return "signup";
            }

            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                model.addAttribute("message", new Message("Password is required", "alert-danger"));
                return "signup";
            }

            // Check for validation errors
            if (result1.hasErrors()) {
                System.out.println("Validation errors: " + result1.toString());
                model.addAttribute("user", user);
                model.addAttribute("message", new Message("Please correct the errors below", "alert-danger"));
                return "signup";
            }

            // Trim whitespace from fields
            user.setName(user.getName().trim());
            user.setEmail(user.getEmail().trim());

            //Password setting
            String rawPassword = user.getPassword().trim();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);

            // Handle about field - set to empty string if null
            if (user.getAbout() == null) {
                user.setAbout("");
            } else {
                user.setAbout(user.getAbout().trim());
            }

            // If we get here, validation passed
            user.setRole("ROLE_USER");
            user.setEnabled(true);

            try {
                this.userRepo.save(user);
                model.addAttribute("message", new Message("Successfully Registered!!", "alert-success"));
                model.addAttribute("user", new User()); // Clear the form
                return "signup";
            } catch (Exception e) {
                // Handle database errors (e.g., duplicate email)
                if (e.getMessage() != null && e.getMessage().contains("constraint")) {
                    model.addAttribute("message", new Message("Email already exists. Please use a different email.", "alert-danger"));
                } else {
                    model.addAttribute("message", new Message("An error occurred. Please try again.", "alert-danger"));
                }
                model.addAttribute("user", user); // Preserve form data
                return "signup";
            }

        } catch (Exception e) {
            logger.error("Error during user registration: ", e);
            model.addAttribute("message", new Message("An unexpected error occurred. Please try again later.", "alert-danger"));
            model.addAttribute("user", user); // Preserve form data
            return "signup";
        }
    }

}

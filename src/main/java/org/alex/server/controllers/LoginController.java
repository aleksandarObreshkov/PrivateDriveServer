package org.alex.server.controllers;

import org.alex.server.models.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String tryLogin(Model model, @ModelAttribute LoginModel loginModel){
        return "redirect:/gallery";
    }
}

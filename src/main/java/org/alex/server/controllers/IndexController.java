package org.alex.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"","/", "/index"})
public class IndexController {

    @GetMapping("/")
    public String redirectToLogin(){
        return "redirect:/login";
    }
}

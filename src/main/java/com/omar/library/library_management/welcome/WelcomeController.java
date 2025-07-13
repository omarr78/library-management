package com.omar.library.library_management.welcome;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
    @RequestMapping("/")
    public String welcome(ModelMap model) {
        model.addAttribute("name",getLoggedInUsername());
        return "welcome";
    }
    private String getLoggedInUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}



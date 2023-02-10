package ru.kata.spring.boot_security.demo.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    @GetMapping("*/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }
	
	@GetMapping("/")
    	public String redirect() {
           return "redirect:/orders";
    	}
}

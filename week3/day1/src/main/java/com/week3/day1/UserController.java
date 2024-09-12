package com.week3.day1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("username", "Murat");
        model.addAttribute("listOfItems", List.of(1, 2, 3, 4, 5, 6));
        return "hello";
    }
}

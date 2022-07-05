package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Add User");
        model.addAttribute("header", "Add New User");
        return "addUser";
    }

    @PostMapping("/users/save")
    public String addUser(User user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("title", "Edit User");
            model.addAttribute("header", "Edit User");
            return "addUser";
        } catch (UserNotFoundException e) {
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
            service.delete(id);
        return "redirect:/users";
    }
}


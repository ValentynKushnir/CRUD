package me.controllers;

import me.dto.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import me.service.UserService;
import me.service.UserServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {

    private UserService userService = new UserServiceImpl();

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(format3, false));
    }
    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("listUsers", userService.getListOfAllUsers());
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")UserEntity user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/users";
        }
        if(user.getId() == 0) {
            this.userService.saveUser(user);
        }
        else {
            this.userService.updateUser(user);
        }
        return "redirect:/users";
    }
    @RequestMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/users";
    }
    @RequestMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id")int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listUsers", userService.getListOfAllUsers());
        return "users";
    }
    @RequestMapping(value = "/userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "userdata";
    }
    @RequestMapping(value = "/fillFakeData")
    public String fillFakeData(Model model) {
        userService.fillWithFakeData();
        model.addAttribute("listUser", userService.getListOfAllUsers());
        return "redirect:/users";
    }
    @RequestMapping(value = "/clearTable")
    public String clearTable() {
        userService.clearDataBase();
        return "redirect:/users";
    }
}

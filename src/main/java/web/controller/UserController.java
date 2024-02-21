package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService; }

    @GetMapping("/")
    public String showUsersTable(Model model){
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

    @GetMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping("/new_user")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/editUser")
    public String editUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit_user";
    }

    @GetMapping("/deleteUser")
    public String removeUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "delete_user";
    }

    @PostMapping("/editUser")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }
        userService.editUser(user);
        return "redirect:/";
    }

    @PostMapping ("/deleteUser")
    public String deleteUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/deleteUser";
        }
        userService.deleteUser(user.getId());
        return "redirect:/";
    }
}

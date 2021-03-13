package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.AddUserForm;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddUserPage extends Page {
    private final UserService userService;

    public AddUserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String addUserGet(Model model) {
        model.addAttribute("addUserForm", new AddUserForm());
        return "AddUserPage";
    }

    @PostMapping("/addUser")
    public String addUserPost(@Valid @ModelAttribute("addUserForm") AddUserForm addUserForm,
                                BindingResult bindingResult,
                                HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddUserPage";
        }
        User user = new User();
        String fullName = addUserForm.getName() + " " + addUserForm.getSurname() + " " + addUserForm.getPatronymic();
        user.setFullName(fullName);
        userService.addUser(user);
        putMessage(httpSession, "You add new user with id "  + user.getId());

        return "redirect:/";
    }
}

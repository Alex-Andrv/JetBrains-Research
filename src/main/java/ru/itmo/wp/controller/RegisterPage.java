package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.form.validator.LibrarianCredentialsRegisterValidator;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.LibrarianService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterPage extends Page {
    private final LibrarianService librarianService;
    private final LibrarianCredentialsRegisterValidator librarianCredentialsRegisterValidator;

    public RegisterPage(LibrarianService librarianService, LibrarianCredentialsRegisterValidator librarianCredentialsRegisterValidator) {
        this.librarianService = librarianService;
        this.librarianCredentialsRegisterValidator = librarianCredentialsRegisterValidator;
    }

    @InitBinder("registerForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(librarianCredentialsRegisterValidator);
    }

    @Guest
    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("registerForm", new LibrarianCredentials());
        return "RegisterPage";
    }

    @Guest
    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("registerForm") LibrarianCredentials registerForm,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "RegisterPage";
        }

        setLibrarian(httpSession, librarianService.register(registerForm));
        putMessage(httpSession, "Congrats, you have been registered!");

        return "redirect:/";
    }
}

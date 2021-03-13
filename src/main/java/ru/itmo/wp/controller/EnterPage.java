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
import ru.itmo.wp.form.validator.LibrarianCredentialsEnterValidator;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.LibrarianService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EnterPage extends Page {
    private final LibrarianService librarianService;
    private final LibrarianCredentialsEnterValidator librarianCredentialsEnterValidator;

    public EnterPage(LibrarianService librarianService, LibrarianCredentialsEnterValidator librarianCredentialsEnterValidator) {
        this.librarianService = librarianService;
        this.librarianCredentialsEnterValidator = librarianCredentialsEnterValidator;
    }

    @InitBinder("enterForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(librarianCredentialsEnterValidator);
    }

    @Guest
    @GetMapping("/enter")
    public String enter(Model model) {
        model.addAttribute("enterForm", new LibrarianCredentials());
        return "EnterPage";
    }

    @Guest
    @PostMapping("/enter")
    public String enter(@Valid @ModelAttribute("enterForm") LibrarianCredentials enterForm,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "EnterPage";
        }

        setLibrarian(httpSession, librarianService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()));
        putMessage(httpSession, "Hello, " + getLibrarian(httpSession).getLogin());

        return "redirect:/";
    }
}

package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.wp.form.AddUserForm;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.security.Unregistered;
import ru.itmo.wp.service.BookService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexPage extends Page {
    private BookService bookService;

    IndexPage(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"", "/"})
    public String indexLibrarian(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "IndexPage";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetLibrarian(httpSession);
        return "redirect:/";
    }
}
